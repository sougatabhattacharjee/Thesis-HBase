package hbase.transformation.notaql.visitors;

import hbase.transformation.notaql.antlr4.NotaQLBaseVisitor;
import hbase.transformation.notaql.antlr4.NotaQLParser;
import hbase.transformation.notaql.hbase.AtomValue;
import hbase.transformation.notaql.splitting.ListSplit;
import hbase.transformation.notaql.splitting.Split;
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
 * @brief Application class processes functionality to evaluate split functions.
 */

public class SplittableVDataVisitor extends NotaQLBaseVisitor<List<AtomValue<?>>> {
    private VDataVisitor dataVisitor;

    public SplittableVDataVisitor(VDataVisitor dataVisitor) {
        this.dataVisitor = dataVisitor;
    }

    /**
     * In case we encounter vData: just provide it as a list
     * @param ctx
     * @return
     */
    @Override
    public List<AtomValue<?>> visitVDataSplittableVData(@NotNull NotaQLParser.VDataSplittableVDataContext ctx) {
        return Arrays.<AtomValue<?>>asList(dataVisitor.visit(ctx.vData()));
    }

    /**
     * In case we encounter a split function: evaluate its arguments and then evaluate the split function.
     * @param ctx
     * @return
     */
    @Override
    public List<AtomValue<?>> visitSplitFunctionSplittableVData(@NotNull NotaQLParser.SplitFunctionSplittableVDataContext ctx) {
        final AtomValue<?> baseValue = this.dataVisitor.visit(ctx.baseVData);

        if(baseValue == null)
            return new LinkedList<AtomValue<?>>();

        final AtomValue<?>[] arguments = new AtomValue[ctx.vData().size()-1];

        for(int i = 1; i < ctx.vData().size(); i++) {
            arguments[i-1] = this.dataVisitor.visit(ctx.vData(i));
        }

        switch (ctx.splittingFunction.getType()) {
            case NotaQLParser.SplitFunction:
                return new Split().split(baseValue, arguments);
            case NotaQLParser.ListSplitFunction:
                return new ListSplit().split(baseValue, arguments);

            default:
                throw new ParseCancellationException("unknown operator: " + NotaQLParser.tokenNames[ctx.splittingFunction.getType()]);
        }
    }
}
