package hbase.transformation.notaql.visitors;

import hbase.transformation.notaql.antlr4.NotaQLBaseVisitor;
import hbase.transformation.notaql.antlr4.NotaQLParser;
import hbase.transformation.notaql.hbase.AtomValue;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date March 05, 2015
 * @brief visitor class to satisfy atoms values
 */
public class AtomVisitor extends NotaQLBaseVisitor<AtomValue<?>> {

    /**
     * Remove the single quotes from the atom and return it as a value
     *
     * @param ctx
     * @return
     */
    @Override
    public AtomValue<String> visitStringAtom(@NotNull NotaQLParser.StringAtomContext ctx) {
        final String string = ctx.String().getText().replace("\\'", "'");
        return new AtomValue<String>(string.substring(1, string.length() - 1));
    }

    @Override
    public AtomValue<Number> visitNumberAtom(@NotNull NotaQLParser.NumberAtomContext ctx) {
        final String string;

        if (ctx.Float() != null)
            string = ctx.Float().getText();
        else
            string = ctx.Int().getText();

        return new AtomValue<Number>(Double.valueOf(string.substring(1, string.length() - 1)));
    }
}
