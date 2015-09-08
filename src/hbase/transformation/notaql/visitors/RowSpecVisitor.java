package hbase.transformation.notaql.visitors;


import hbase.transformation.notaql.antlr4.NotaQLBaseVisitor;
import hbase.transformation.notaql.antlr4.NotaQLParser;
import hbase.transformation.notaql.hbase.AtomValue;
import hbase.transformation.notaql.hbase.Column;
import hbase.transformation.notaql.hbase.IRow;
import hbase.transformation.notaql.hbase.Row;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.Arrays;
import java.util.Map;
/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date March 05, 2015
 * @brief Application class processes the column filter and provides the valid input cells for a given row. OUT._r<-IN.Company
 */

public class RowSpecVisitor extends NotaQLBaseVisitor<IRow> {
    private final NotaQLParser.TransformationContext ctx;
    private IRow inputRow;
    private boolean cellInputFound;

    /**
     * Construct the filter by providing the transformation context
     * @param ctx
     */
    public RowSpecVisitor(NotaQLParser.TransformationContext ctx) {
        this.ctx = ctx;
    }

    /**
     * Provide the row with the filtered input cells. This may even be empty in case it is never used.
     *
     * @param inputRow    The unfiltered input row
     * @return
     */
    public IRow filter(IRow inputRow) {
        this.inputRow = inputRow;
        this.cellInputFound = false;
        final IRow outputRow = this.visit(ctx);
        if(this.cellInputFound)
            return outputRow != null ? outputRow : new Row(inputRow.getRowKey());

        return this.inputRow;
    }

    /**
     * Evaluates the predicate of the provided cell and outputs only valid ones.
     *
     * @param ctx
     * @return
     */
    @Override
    public IRow visitCellInput(@NotNull NotaQLParser.CellInputContext ctx) {

      // make sure only the first cell input may have an input
        if (this.cellInputFound && (ctx.predicate() != null || ctx.colFamilyFilter != null))
            throw new ParseCancellationException("Only the first occurring cell input (IN._c, IN._v, IN.columnid) may feature a predicate.");

        this.cellInputFound = true;

        if(ctx.predicate() == null && ctx.colFamilyFilter == null)
            return inputRow;

        final Row outputRow = new Row(inputRow.getRowKey());

        final CellPredicateVisitor predicateVisitor = new CellPredicateVisitor();

        // For each column check if it shall be considered for the output
        for (Map.Entry<Column,AtomValue<?>> column : inputRow.entrySet()) {
            // skip if the column family filter is not true
            if(ctx.colFamilyFilter != null && !column.getKey().getColumnFamily().equals(ctx.colFamilyFilter.getText()))
                continue;

            // skip if the predicate is false
            if(ctx.predicate() != null) {
                if (!predicateVisitor.evaluate(ctx.predicate(), inputRow, column.getKey(), column.getValue()))
                    continue;
            }

            outputRow.put(column.getKey(), column.getValue());

        }

        return outputRow;
    }

    /**
     * In case we encounter a ColId input we treat this as a filter as well.
     *
     * @param ctx
     * @return
     */
    @Override
    public IRow visitColIdInput(@NotNull NotaQLParser.ColIdInputContext ctx) {
//       System.out.println("2nd");
        // make sure only the first cell input may have an input
        if (this.cellInputFound)
            throw new ParseCancellationException("Only the first occurring cell input (IN._c, IN._v, IN.columnid) may feature a predicate.");

        this.cellInputFound = true;

        return this.visit(ctx.colId());
    }

    @Override
    public IRow visitStringColId(@NotNull NotaQLParser.StringColIdContext ctx) {
//        System.out.println("3rdd");

        boolean flag = false;

        final Row outputRow = new Row(inputRow.getRowKey());

//        this.cellInputFound = true;

        // For each column check if it shall be considered for the output
        for (Map.Entry<Column,AtomValue<?>> column : inputRow.entrySet()) {

//            System.out.println("column.getKey().getTimeStamp() = " + column.getKey().getTimeStamp());
            // skip if the column family filter is not true
            if(ctx.colFamily != null && !column.getKey().getColumnFamily().getAtomValue().equals(ctx.colFamily.getText()))
                continue;



//            System.out.println("column.getKey() = " + column.getKey());
//            System.out.println("column.getValue() = " + column.getValue());

            
            if (!column.getKey().getColumnName().getAtomValue().equals(ctx.colName.getText()))
            {
              continue;
            }

            flag = true;
            this.cellInputFound = true;

            
            outputRow.put(column.getKey(), column.getValue());
//            System.out.println("column.getKey().toString() = " + column.getKey().toString());

        }

       if(this.ctx.cellSpec().aggFun()==null){
        if(!flag) {

            this.cellInputFound = true;

            final Row outRow = new Row(inputRow.getRowKey());

            final AtomValue colName = new AtomValue<String>(ctx.colName.getText());

            final AtomValue<?> outVal = new AtomValue<Integer>(0);

            Column newColumn;

            if (ctx.colFamily != null) {
                final AtomValue colFamily = new AtomValue<String>(ctx.colFamily.getText());

                newColumn = new Column(colFamily, colName);

                outputRow.put(newColumn,outVal);

            }
            else {
                newColumn = new Column(colName);

                outputRow.put(newColumn, outVal);
            }
        }
       }

//        System.out.println("outputRow.size() = " + outputRow.size());

        return outputRow;
    }



    /**
     * For a transformation look for filters in the rowspec and after that in the cell spec
     * @param ctx
     * @return
     */
    @Override
    public IRow visitTransformation(@NotNull NotaQLParser.TransformationContext ctx) {
        final IRow rowResult = visit(ctx.rowSpec());
        if(cellInputFound)
            return rowResult;

        return visit(ctx.cellSpec());
    }

    @Override
    public IRow visitRowSpec(@NotNull NotaQLParser.RowSpecContext ctx) {
        return this.visit(ctx.splittableVData());
    }

    @Override
    public IRow visitInputVData(@NotNull NotaQLParser.InputVDataContext ctx) {
        return this.visit(ctx.input());
    }

    @Override
    public IRow visitCellSpec(@NotNull NotaQLParser.CellSpecContext ctx) {
        return visit(ctx.outputCol());
    }

    @Override
    public IRow visitResolvedColPath(@NotNull NotaQLParser.ResolvedColPathContext ctx) {
        return visit(ctx.splittableVData());
    }

    @Override
    public IRow visitVDataSplittableVData(@NotNull NotaQLParser.VDataSplittableVDataContext ctx) {
        return this.visit(ctx.vData());
    }

    @Override
    public IRow visitSplitFunctionSplittableVData(@NotNull NotaQLParser.SplitFunctionSplittableVDataContext ctx) {
        return this.visit(ctx.baseVData);
    }
}
