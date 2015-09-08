package hbase.transformation.notaql.visitors;

import hbase.transformation.notaql.hbase.Column;
import hbase.transformation.notaql.hbase.IRow;
import hbase.transformation.notaql.hbase.Row;
import hbase.transformation.notaql.hbase.AtomValue;
import hbase.transformation.notaql.antlr4.NotaQLParser;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Map;
/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date March 05, 2015
 * @brief Analyses atoms and colIds and provides the appropriate values for them
 */

public class ValueVisitor extends AtomVisitor {
    private final IRow row;

    /**
     * Constructs a ValueVisitor and binds it to the given row
     *
     * @param row
     */
    public ValueVisitor(IRow row) {
        this.row = row;
    }

    /**
     * Provides the value for a given ColId context. Null if it doesn't exist.
     *
     * @param ctx
     * @return
     */
    @Override
    public AtomValue<?> visitStringColId(@NotNull NotaQLParser.StringColIdContext ctx) {
        final AtomValue<?> colName = new AtomValue<String>(ctx.colName.getText());

        if (ctx.colFamily != null) {
            final AtomValue<?> colFamily = new AtomValue<String>(ctx.colFamily.getText());
            final Map<Column, AtomValue<?>> cols = this.row.getColumnFamily(colFamily);

            // in case the requested column family doesn't exist at all the result is null
            if (cols == null)
                return null;

            final AtomValue<?> val = cols.get(new Column(colFamily, colName));

            // otherwise it depends on the fact if the column exists
            if (val == null)
                return null;

            return val;

        } else {
            // treat no colFamily as wildcard
            final AtomValue<?> val = this.row.get(colName, null);
            if (val != null)
                return val;
        }

        return null;
    }

    /**
     * Provides the value for a given ColId context. Null if it doesn't exist.
     *
     * @param ctx
     * @return
     */
    @Override
    public AtomValue<?> visitGenericColId(@NotNull NotaQLParser.GenericColIdContext ctx) {
        final AtomVisitor atomVisitor = new AtomVisitor();

        final AtomValue<?> colName = atomVisitor.visit(ctx.colName);

        if (ctx.colFamily != null) {
            final AtomValue<?> colFamily = atomVisitor.visit(ctx.colFamily);
            final Map<Column, AtomValue<?>> cols = this.row.getColumnFamily(colFamily);

            // in case the requested column family doesn't exist at all the result is null
            if (cols == null)
                return null;

            final AtomValue<?> val = cols.get(new Column(colFamily, colName));

            // otherwise it depends on the fact if the column exists
            if (val == null)
                return null;

            return val;

        } else {
            // treat no colFamily as wildcard
            final AtomValue<?> val = this.row.get(colName, null);
            if (val != null)
                return val;
        }

        return null;
    }
}
