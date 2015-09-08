package hbase.transformation.notaql.visitors;

import hbase.transformation.notaql.antlr4.NotaQLBaseVisitor;
import hbase.transformation.notaql.antlr4.NotaQLParser;
import hbase.transformation.notaql.hbase.AtomValue;
import hbase.transformation.notaql.hbase.IRow;
import hbase.transformation.notaql.hbase.Row;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date March 05, 2015
 * @brief Application class to test
 */
public class RowPredicateVisitor extends NotaQLBaseVisitor<Boolean> {

    protected IRow row;
    protected VDataVisitor vDataVisitor;
    private NotaQLParser.PredicateContext predicateContext;

    /**
     * Evaluate the given predicate for the given row using the given vDataVisitor
     * @param ctx
     * @param row
     * @param vDataVisitor
     * @return
     */
    protected boolean evaluate(NotaQLParser.PredicateContext ctx, IRow row, VDataVisitor vDataVisitor) {
        this.predicateContext = ctx;
        this.row = row;
        this.vDataVisitor = vDataVisitor;

        return this.visit(ctx);
    }

    /**
     * Evaluate the predicate for the given row
     * @param ctx
     * @param row
     * @return
     */
    public boolean evaluate(NotaQLParser.PredicateContext ctx, IRow row) {
        return this.evaluate(ctx, row, new VDataVisitor(row));
    }


    /**
     * Tests if the relational operator is fulfilled by facilitating the {@link VDataVisitor}.
     * @param ctx
     * @return
     */
    @Override
    public Boolean visitRelationalPredicate(@NotNull NotaQLParser.RelationalPredicateContext ctx) {
        final AtomValue<?> left = vDataVisitor.visit(ctx.vData(0));
        final AtomValue<?> right = vDataVisitor.visit(ctx.vData(1));
//        System.out.println("left>>>>>>>>"+left);
//        System.out.println("right>>>>>>>>"+right);
        if(left == null || right == null)
            return false;

        // make sure the values are doubles
        if (!(left.getAtomValue() instanceof Number && right.getAtomValue() instanceof Number))
            return false;

        switch (ctx.op.getType()) {
            case NotaQLParser.LT:
                return ((Number) left.getAtomValue()).doubleValue() < ((Number) right.getAtomValue()).doubleValue();
            case NotaQLParser.LTEQ:
                return ((Number) left.getAtomValue()).doubleValue() <= ((Number) right.getAtomValue()).doubleValue();
            case NotaQLParser.GT:
                return ((Number) left.getAtomValue()).doubleValue() > ((Number) right.getAtomValue()).doubleValue();
            case NotaQLParser.GTEQ:
                return ((Number) left.getAtomValue()).doubleValue() >= ((Number) right.getAtomValue()).doubleValue();
            default:
                throw new RuntimeException("unknown operator: " + NotaQLParser.tokenNames[ctx.op.getType()]);
        }
    }

    /**
     * Tests if the equal operator is fulfilled {@link VDataVisitor}.
     * @param ctx
     * @return
     */
    @Override
    public Boolean visitEqualityPredicate(@NotNull NotaQLParser.EqualityPredicateContext ctx) {
        final AtomValue<?> left = vDataVisitor.visit(ctx.vData(0));
        final AtomValue<?> right = vDataVisitor.visit(ctx.vData(1));

        if(left == null || right == null)
            return false;

        switch (ctx.op.getType()) {
            case NotaQLParser.EQ:
                if(left.getAtomValue() instanceof Number && right.getAtomValue() instanceof Number)
                    return ((Number) left.getAtomValue()).doubleValue() == ((Number) right.getAtomValue()).doubleValue();

                return left.equals(right);
            case NotaQLParser.NEQ:
                if(left.getAtomValue() instanceof Number && right.getAtomValue() instanceof Number)
                    return ((Number) left.getAtomValue()).doubleValue() != ((Number) right.getAtomValue()).doubleValue();

                return !left.equals(right);
            default:
                throw new RuntimeException("unknown operator: " + NotaQLParser.tokenNames[ctx.op.getType()]);
        }
    }

    /**
     * satisfy the and operator
     * @param ctx
     * @return
     */
    @Override
    public Boolean visitAndPredicate(@NotNull NotaQLParser.AndPredicateContext ctx) {
        NotaQLParser.PredicateContext left = ctx.predicate(0);
        NotaQLParser.PredicateContext right = ctx.predicate(1);
        return visit(left) && visit(right);
    }

    /**
     * satisfy the or operator
     * @param ctx
     * @return
     */
    @Override
    public Boolean visitOrPredicate(@NotNull NotaQLParser.OrPredicateContext ctx) {
        NotaQLParser.PredicateContext left = ctx.predicate(0);
        NotaQLParser.PredicateContext right = ctx.predicate(1);
        return visit(left) || visit(right);
    }


    @Override
    public Boolean visitNegatedPredicate(NotaQLParser.NegatedPredicateContext ctx) {
        NotaQLParser.PredicateContext predicate = ctx.predicate();
        return !visit(predicate);
    }

    /**
     * satisfy braced predicate ()
     * @param ctx
     * @return
     */
    @Override
    public Boolean visitBracedPredicate(@NotNull NotaQLParser.BracedPredicateContext ctx) {
        return visit(ctx.predicate());
    }

    /**
     * Tests if the column exists
     * @param ctx
     * @return
     */
    @Override
    public Boolean visitColExistencePredicate(@NotNull NotaQLParser.ColExistencePredicateContext ctx) {
        final ValueVisitor valueVisitor = new ValueVisitor(this.row);

        return valueVisitor.visit(ctx.colId()) != null;
    }
}
