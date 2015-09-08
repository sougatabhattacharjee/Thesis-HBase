package hbase.transformation.notaql.visitors;

import hbase.transformation.notaql.hbase.AtomValue;
import hbase.transformation.notaql.hbase.Column;
import hbase.transformation.notaql.hbase.IRow;
import hbase.transformation.notaql.antlr4.NotaQLBaseVisitor;
import hbase.transformation.notaql.antlr4.NotaQLParser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date March 05, 2015
 * @brief visitor evaluates the output column specification (cellSpec)
 */

public class CellSpecVisitor extends NotaQLBaseVisitor<List<Column>> {
    private final NotaQLParser.OutputColContext outputColContext;

    private IRow curRow;
    private Column curColId;
    private AtomValue curVal;

    /**
     * Construct the evaluator for a given output col specification
     *
     * @param outputColContext
     */
    public CellSpecVisitor(NotaQLParser.OutputColContext outputColContext) {
        this.outputColContext = outputColContext;
    }

    /**
     * Evaluate the provided output column for the given row, column and value
     *
     * @param curRow
     * @param curColId
     * @param curVal
     * @return
     */
    public List<Column> evaluate(IRow curRow, Column curColId, AtomValue curVal) {
        this.curRow = curRow;
        this.curColId = curColId;
        this.curVal = curVal;

        return this.visit(this.outputColContext);
    }

    /**
     * In case we get a string column id: we provide quite simply the constant column id.
     *
     * @param ctx
     * @return
     */
    @Override
    public List<Column> visitStringColId(@NotNull NotaQLParser.StringColIdContext ctx) {

        final AtomValue colName = new AtomValue<String>(ctx.colName.getText());

        if (ctx.colFamily != null) {
            final AtomValue colFamily = new AtomValue<String>(ctx.colFamily.getText());

            return Arrays.asList(new Column(colFamily, colName));
        }
        //if colFamily is not provided use the base table column family
        //1st check if it is a column of base table, then return the original column family of base table otherwise return null
        // treat no colFamily as wildcard
        final AtomValue<?> val = this.curRow.get(colName, null);
        if(val!=null)
            return Arrays.asList(new Column(curColId.getColumnFamily(),colName));
        else
            return Arrays.asList(new Column(null,colName));
    }

    /**
     * In case we get a generic column id: use the AtomVisitor to evaluate it.
     *
     * @param ctx
     * @return
     */
    @Override
    public List<Column> visitGenericColId(@NotNull NotaQLParser.GenericColIdContext ctx) {
        final AtomVisitor atomVisitor = new AtomVisitor();

        final AtomValue<?> colName = atomVisitor.visit(ctx.colFamily);

        if (ctx.colFamily != null) {
            final AtomValue<?> colFamily = atomVisitor.visit(ctx.colFamily);

            return Arrays.asList(new Column(colFamily, colName));
        }

        return Arrays.asList(new Column(colName));
    }

    /**
     * In case we have a resolved column path, we recursively evaluate the vdata.
     * After that step we overwrite the column family if it is provided.
     *
     * @param ctx
     * @return
     */
    @Override
    public List<Column> visitResolvedColPath(@NotNull NotaQLParser.ResolvedColPathContext ctx) {
        final List<Column> columnIds = this.visit(ctx.splittableVData());

        if (ctx.colFamily != null) {
            final List<Column> result = new LinkedList<Column>();
            for (Column columnId : columnIds) {
                // FIXME: fix this! A generic ColumFamily should be possible
                result.add(new Column(new AtomValue<String>(ctx.colFamily.getText()), columnId.getColumnName()));
            }

            return result;
        }

        return columnIds;
    }

    @Override
    public List<Column> visitVDataSplittableVData(@NotNull NotaQLParser.VDataSplittableVDataContext ctx) {
        return this.evaluateVData(ctx, ctx.vData());
    }

    @Override
    public List<Column> visitSplitFunctionSplittableVData(@NotNull NotaQLParser.SplitFunctionSplittableVDataContext ctx) {
        return this.evaluateVData(ctx, ctx.baseVData);
    }

    /**
     * Evaluates the SplittableVData for a given VData entry and provides the respective ColumnIDs
     * <p/>
     * This means, that for Inputs it preserves the column family and for the rest it simply does not define the column family.
     *
     * @param splittableVDataContext
     * @param baseVDataContext
     * @return
     */
    private List<Column> evaluateVData(NotaQLParser.SplittableVDataContext splittableVDataContext, NotaQLParser.VDataContext baseVDataContext) {
        final SplittableVDataVisitor dataVisitor = new SplittableVDataVisitor(new VDataVisitor(this.curRow, this.curColId, this.curVal));
        final List<AtomValue<?>> values = dataVisitor.visit(splittableVDataContext);

        // Make sure the column ids have the original column family
        final AtomValue<?> columnFamily;
        if (baseVDataContext instanceof NotaQLParser.InputVDataContext) {
            columnFamily = this.visit(baseVDataContext).get(0).getColumnFamily();
        } else {
            columnFamily = null;
        }

        final List<Column> columnIds = new LinkedList<Column>();

        for (AtomValue<?> value : values) {
            if (columnFamily == null)
                columnIds.add(new Column(value));
            else
                columnIds.add(new Column(columnFamily, value));
        }

        return columnIds;
    }

    /**
     * If we encounter a row input: simply return the row id
     *
     * @param ctx
     * @return
     */
    @Override
    public List<Column> visitRowInput(@NotNull NotaQLParser.RowInputContext ctx) {
//        System.out.println("curColId = " + curColId.getColumnFamily());
        if(curColId.getColumnFamily()==null)
            return Arrays.asList(new Column(curRow.getRowKey()));
        return Arrays.asList(new Column(curColId.getColumnFamily(), curRow.getRowKey()));
    }

    /**
     * If we encounter a cell input (IN._c, IN._r) simply return its value. This keeps the column family for IN._c
     *
     * @param ctx
     * @return
     */
    @Override
    public List<Column> visitCellInput(@NotNull NotaQLParser.CellInputContext ctx) {

        switch (ctx.source.getType()) {
            case NotaQLParser.COL:
                return Arrays.asList(this.curColId);
            case NotaQLParser.VAL:
                return Arrays.asList(new Column(this.curColId.getColumnFamily(), this.curVal));
            default:
                throw new ParseCancellationException("unknown operator: " + NotaQLParser.tokenNames[ctx.source.getType()]);
        }
    }

    /**
     * Provides the value of the given column id.
     *
     * @param ctx
     * @return
     */
    @Override
    public List<Column> visitColIdInput(@NotNull NotaQLParser.ColIdInputContext ctx) {
        final VDataVisitor vDataVisitor = new VDataVisitor(this.curRow, this.curColId, this.curVal);
        final AtomValue<?> value = vDataVisitor.visit(ctx.colId());

        // TODO: we could keep the column family here.
        return Arrays.asList(new Column(value));
    }

    /**
     * Simply evaluate the column id
     *
     * @param ctx
     * @return
     */
    @Override
    public List<Column> visitColIdColPath(@NotNull NotaQLParser.ColIdColPathContext ctx) {
        return this.visit(ctx.colId());
    }



    @Override
    public List<Column> visitInputVData(@NotNull NotaQLParser.InputVDataContext ctx) {
        return this.visit(ctx.input());
    }
}
