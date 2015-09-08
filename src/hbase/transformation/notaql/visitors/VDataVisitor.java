package hbase.transformation.notaql.visitors;

import hbase.transformation.notaql.NotaQLTransformation;
import hbase.transformation.notaql.antlr4.NotaQLBaseVisitor;
import hbase.transformation.notaql.antlr4.NotaQLParser;
import hbase.transformation.notaql.hbase.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.apache.hadoop.vhbase.client.NotaQLTransform;

import java.util.Map;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date March 05, 2015
 * @brief Application class to process VData and return its values.
 */
public class VDataVisitor extends NotaQLBaseVisitor<AtomValue<?>> {

    private final Column curColId;
    private final AtomValue<?> curVal;
    private final IRow curRow;
    final AtomVisitor atomVisitor = new AtomVisitor();
    private boolean aggFunctionCheck = false;
    private boolean timeStampFlag = false;

    /**
     * Construct the visitor for a given rowAtomValue<?>
     *
     * @param curRow
     */
    public VDataVisitor(IRow curRow) {
        this(curRow, null, null);
    }

    /**
     * Construct the visitor for a given row and the value which is currently under evaluation
     *
     * @param curRow
     * @param curColId
     * @param curVal
     */
    public VDataVisitor(IRow curRow, Column curColId, AtomValue<?> curVal) {
        this.curColId = curColId;
        this.curVal = curVal;
        this.curRow = curRow;
    }

    @Override
    public AtomValue<?> visitInputVData(@NotNull NotaQLParser.InputVDataContext ctx) {
        return this.visit(ctx.input());
    }

    @Override
    public AtomValue<?> visitRowInput(@NotNull NotaQLParser.RowInputContext ctx) {
        return this.curRow.getRowKey();
    }

    @Override
    public AtomValue<?> visitColIdInput(@NotNull NotaQLParser.ColIdInputContext ctx) {
        return this.visit(ctx.colId());
    } protected VDataVisitor vDataVisitor;

    /**
     * For column ids make sure to return the proper value.
     * NOTE: This will select any matching one in case the column family is not provided.
     * @param ctx
     * @return
     */
    @Override
    public AtomValue<?> visitStringColId(@NotNull NotaQLParser.StringColIdContext ctx) {
        NotaQLParser.TransformationContext ctxAgg;
        final AtomValue<String> colName = new AtomValue<String>(ctx.colName.getText());


        if (ctx.colFamily != null) {
            final AtomValue<String> colFamily = new AtomValue<String>(ctx.colFamily.getText());
            if(timeStampFlag) {
                NotaQLTransformation.setOutTimeStamp(this.curRow.getTimeStamp(colName, colFamily));
                NotaQLTransform.setOutTimeStamp(this.curRow.getTimeStamp(colName, colFamily));
            }
            timeStampFlag = false;
            return this.curRow.get(colName, colFamily);
        }

        if (curColId != null) {
         if(timeStampFlag) {
                NotaQLTransformation.setOutTimeStamp(this.curRow.getTimeStamp(colName, curColId.getColumnFamily()));
                NotaQLTransform.setOutTimeStamp(this.curRow.getTimeStamp(colName, curColId.getColumnFamily()));
            }
            timeStampFlag = false;
            return this.curRow.get(colName, curColId.getColumnFamily());
        }

        if(timeStampFlag) {
            NotaQLTransformation.setOutTimeStamp(this.curRow.getTimeStamp(colName, null));
            NotaQLTransform.setOutTimeStamp(this.curRow.getTimeStamp(colName, null));
        }
        timeStampFlag = false;
        return this.curRow.get(colName, null);


//        //if aggregation function used then for each column check the columnValue
//        if(!aggFunctionCheck) {
//            if (ctx.colFamily != null) {
//                final AtomValue<String> colFamily = new AtomValue<String>(ctx.colFamily.getText());
//                //return this.curRow.get(new Column(colFamily, colName));
//                return this.curRow.get(colName, colFamily);
//            }
//
////            System.out.println("aggFunctionCheck =++++++++++++++++++++++++++== " + aggFunctionCheck);
//
//            if (curColId != null)
//                return this.curRow.get(colName, curColId.getColumnFamily());
//
//            return this.curRow.get(colName, null);
//        }
//        else{
//            if(!colName.toString().equals(curColId.getColumnName().toString()))
//                return new AtomValue<Integer>(0);
//            if (ctx.colFamily != null) {
//                final AtomValue<String> colFamily = new AtomValue<String>(ctx.colFamily.getText());
//                //return this.curRow.get(new Column(colFamily, colName));
//                return this.curRow.get(colName, colFamily);
//            }
//
////            System.out.println("aggFunctionCheck =++++++++++++++++++++++++++== " + aggFunctionCheck);
//
//            if (curColId != null)
//                return this.curRow.get(colName, curColId.getColumnFamily());
//
//            return this.curRow.get(colName, null);
//        }
    }

    /**
     * Extract the value from an atom.
     *
     *
     * @param ctx
     * @return
     */
    @Override
    public AtomValue<?> visitAtomVData(@NotNull NotaQLParser.AtomVDataContext ctx) {
        final AtomVisitor valueVisitor = new AtomVisitor();
        return valueVisitor.visit(ctx.atom());
    }



    /**
     * Evaluate the braces "( vdata )"
     *
     * @param ctx
     * @return
     */
    @Override
    public AtomValue<?> visitBracedVData(@NotNull NotaQLParser.BracedVDataContext ctx) {
        return visit(ctx.vData());
    }

    /**
     * Evaluates multiplicative operations (* and /) by evaluating left and right and then executing the operation.
     *
     * TODO: we could keep some types here.
     *
     * @param ctx
     * @return
     */
    @Override
    public AtomValue<Number> visitMultiplicatedVData(@NotNull NotaQLParser.MultiplicatedVDataContext ctx) {
        final AtomValue left = visit(ctx.vData(0));
        final AtomValue right = visit(ctx.vData(1));

        if(left == null || right == null)
            return null;

        if(!(left.getAtomValue() instanceof Number) || !(right.getAtomValue() instanceof Number))
            throw new ParseCancellationException("Multiplication or Division is only possible with numbers.");

        switch (ctx.op.getType()) {
            case NotaQLParser.MULT:
                return new AtomValue<Number>(((Number) left.getAtomValue()).doubleValue() * ((Number) right.getAtomValue()).doubleValue());
            case NotaQLParser.DIV: {
                return new AtomValue<Number>(((Number) left.getAtomValue()).doubleValue() / ((Number) right.getAtomValue()).doubleValue());
            }
            default:
                throw new ParseCancellationException("unknown operator: " + NotaQLParser.tokenNames[ctx.op.getType()]);
        }
    }

    /**
     * Evaluates additive operations (+ and -) by evaluating left and right and then executing the operation.
     *
     * @param ctx
     * @return
     */
    @Override
    public AtomValue<Number> visitAddedVData(@NotNull NotaQLParser.AddedVDataContext ctx) {
        final AtomValue left = visit(ctx.vData(0));
        final AtomValue right = visit(ctx.vData(1));

        if(left == null || right == null)
            return null;

        if(!(left.getAtomValue() instanceof Number) || !(right.getAtomValue() instanceof Number))
            throw new ParseCancellationException("Addition and Subtraction is only possible with numbers.");

        switch (ctx.op.getType()) {
            case NotaQLParser.PLUS:
                return new AtomValue<Number>(((Number) left.getAtomValue()).doubleValue() + ((Number) right.getAtomValue()).doubleValue());
            case NotaQLParser.MINUS:
                return new AtomValue<Number>(((Number) left.getAtomValue()).doubleValue() - ((Number) right.getAtomValue()).doubleValue());
            default:
                throw new ParseCancellationException("unknown operator: " + NotaQLParser.tokenNames[ctx.op.getType()]);
        }
    }


    /**
     * Provide the proper column-names or values
     *
     * @param ctx
     * @return
     */
    @Override
    public AtomValue<?> visitCellInput(@NotNull NotaQLParser.CellInputContext ctx) {
            switch (ctx.source.getType()) {
                case NotaQLParser.COL:
                    if (this.curColId == null)
                        throw new ParseCancellationException("IN_C is not allowed in this rule.");
                    return this.curColId.getColumnName();
                case NotaQLParser.VAL:
                    if (this.curVal == null)
                        throw new ParseCancellationException("IN_V is not allowed in this rule.");
                    if(ctx.colFamilyFilter!=null) {
                        if (ctx.colFamilyFilter.getText().equals(curColId.getColumnFamily().toString())) {
                            if(timeStampFlag) {
                                NotaQLTransformation.setOutTimeStamp(curRow.getTimeStamp(curColId.getColumnName(), curColId.getColumnFamily()));
                                NotaQLTransform.setOutTimeStamp(curRow.getTimeStamp(curColId.getColumnName(), curColId.getColumnFamily()));
                            }
                            timeStampFlag = false;
                            return this.curVal;
                        }
                        else
                            return null;
                    }
                    if(timeStampFlag) {
                        NotaQLTransformation.setOutTimeStamp(curRow.getTimeStamp(curColId.getColumnName(), curColId.getColumnFamily()));
                        NotaQLTransform.setOutTimeStamp(curRow.getTimeStamp(curColId.getColumnName(), curColId.getColumnFamily()));
                    }
                    timeStampFlag = false;
                    return this.curVal;
                default:
                    throw new ParseCancellationException("unknown operator: " + NotaQLParser.tokenNames[ctx.source.getType()]);
            }
    }


    /**
     * Simply forwards the evaluation to the functions
     *
     * @param ctx
     * @return
     */
    @Override
    public AtomValue<?> visitFunctionVData(@NotNull NotaQLParser.FunctionVDataContext ctx) {
        return visit(ctx.vDataFunction());
    }

    @Override
    public AtomValue<?> visitCurrentCellVData(@NotNull NotaQLParser.CurrentCellVDataContext ctx) {
        if(this.curVal == null)
            throw new ParseCancellationException("Cell value reference is just allowed in a cell predicate.");

        return this.curVal;
    }

    /**
     * Provides the count of all columns - column family is optional
     *
     * @param ctx
     * @return
     */
    @Override
    public AtomValue<Integer> visitColCountFunction(@NotNull NotaQLParser.ColCountFunctionContext ctx) {

        if(ctx.colFamily != null) //if column family is not null
        {
            final Map<Column, AtomValue<?>> columnFamily = this.curRow.getColumnFamily(AtomValue.parse(ctx.colFamily.getText()));


            if(columnFamily == null)    //if column family is null
                return new AtomValue<Integer>(0);

            return new AtomValue<Integer>(columnFamily.size());
        }
     return new AtomValue<Integer>(this.curRow.size());
    }


    public void aggCheck(NotaQLParser.TransformationContext ctx) {

        if(ctx.cellSpec().aggFun()!=null)
            aggFunctionCheck = true;
    }

    public void timeStampCheck(Boolean flag) {

        timeStampFlag = flag;
    }

    public AtomValue<?> timeStamp(AtomValue<?> ts) {

        return ts;
    }
}
