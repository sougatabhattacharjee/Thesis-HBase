package hbase.transformation.notaql.visitors;

import hbase.transformation.notaql.hbase.AtomValue;
import hbase.transformation.notaql.hbase.Column;
import hbase.transformation.notaql.hbase.IRow;
import hbase.transformation.notaql.antlr4.NotaQLParser;
import org.antlr.v4.runtime.misc.NotNull;
/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date March 05, 2015
 * @brief visitor evaluates cell predicates
 */
public class CellPredicateVisitor extends RowPredicateVisitor {
    private Column curColId;
    private AtomValue<?> curVal;

    /**
     * Evaluate the given cell predicate for the given row, column id and value
     * @param ctx
     * @param row
     * @param curColId
     * @param curVal
     * @return
     */
    public boolean evaluate(NotaQLParser.PredicateContext ctx, IRow row, Column curColId, AtomValue curVal) {
        this.curColId = curColId;
        this.curVal = curVal;
        return super.evaluate(ctx, row, new VDataVisitor(row, curColId, curVal));
    }

    /**
     * Tests if the column is the currently selected one.
     * This overrides the behaviour in the {@link hbase.transformation.notaql.visitors.RowPredicateVisitor}
     *
     * @param ctx
     * @return
     */
    @Override
    public Boolean visitColExistencePredicate(@NotNull NotaQLParser.ColExistencePredicateContext ctx) {
        return this.visit(ctx.colId());

    }

    @Override
    public Boolean visitStringColId(@NotNull NotaQLParser.StringColIdContext ctx) {
        final AtomValue<String> colName = new AtomValue<String>(ctx.colName.getText());
        if(ctx.colFamily != null) {
            final AtomValue colFamily = new AtomValue<String>(ctx.colFamily.getText());
            return curColId.equals(new Column(colFamily, colName));
        }

        return curColId.getColumnName().equals(colName);
    }

    @Override
    public Boolean visitGenericColId(@NotNull NotaQLParser.GenericColIdContext ctx) {
        final AtomVisitor atomVisitor = new AtomVisitor();

        final AtomValue<?> colName = atomVisitor.visit(ctx.colName);
        if(ctx.colFamily != null) {
            final AtomValue<?> colFamily = atomVisitor.visit(ctx.colFamily);
            return curColId.equals(new Column(colFamily, colName));
        }

        return curColId.getColumnName().equals(colName);
    }
}
