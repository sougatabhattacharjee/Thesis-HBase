package org.apache.hadoop.vhbase.client;

import hbase.transformation.notaql.NotaQLProcess;
import hbase.transformation.notaql.aggregation.*;
import hbase.transformation.notaql.antlr4.NotaQLBaseVisitor;
import hbase.transformation.notaql.antlr4.NotaQLParser;
import hbase.transformation.notaql.hbase.*;
import hbase.transformation.notaql.visitors.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date June 18, 2015
 * @brief Application class to test
 */
public class NotaQLTransform extends NotaQLBaseVisitor {

    private BaseTable baseTable = null;
    private InputTableRow inputRow;

    private final NotaQLParser.TransformationContext ctx;
    private AggregationTable<AtomValue<?>, Column, AtomValue<?>> target;
    private AggregationTable<AtomValue<?>, Column, AtomValue<?>> aggTable;
    private AggregationTable<AtomValue<?>, Column, AtomValue<?>> aggTableTimeStamp;

    private static AtomValue<?> outTimeStamp = new AtomValue<Long>(0L);

    public static void setOutTimeStamp(AtomValue<?> outTimeStamp) {
        NotaQLTransform.outTimeStamp = outTimeStamp;
    }

    private Map<?,?> cell;

    /**
     * Constructs the transformation visitor for the given TransformationContext
     * @param ctx
     */
    public NotaQLTransform(@NotNull NotaQLParser.TransformationContext ctx) {
        this.target = new AggregationTable<AtomValue<?>, Column, AtomValue<?>>();
        this.aggTable = new AggregationTable<AtomValue<?>, Column, AtomValue<?>>();
        this.aggTableTimeStamp = new AggregationTable<AtomValue<?>, Column, AtomValue<?>>();
        this.ctx = ctx;
    }


    public Iterator<Result> transform(ResultScanner rs) throws IOException {

        baseTable = new BaseTable(rs);

        Map<String, List<Cell>> vCellLists = transformation(baseTable);

        Iterator<String> keys = vCellLists.keySet().iterator();

        return new Iterator<Result>() {
            @Override
            public boolean hasNext() {
                return keys.hasNext();
            }

            @Override
            public Result next() {
                String key = keys.next();
                List<Cell> cells = (List<Cell>) vCellLists.get(key);
                Result result = Result.create(cells);
                return result;
            }
        };
    }

    public Result transform(ResultScanner rs, String rowKey) throws IOException {

        baseTable = new BaseTable(rs);

        Map<String, List<Cell>> vCellLists = transformation(baseTable);
        Result result = null;
        if(vCellLists.containsKey(rowKey)) {
            List<Cell> cells = (List<Cell>) vCellLists.get(rowKey);
            result = Result.create(cells);
        }
        else{
            result = Result.create(new Cell[]{});
        }
        return result;
    }

    public Map<String, List<Cell>> transformation(InputTableRow inputRow) {

        this.inputRow = inputRow;

        final RowSpecVisitor rowSpec = new RowSpecVisitor(this.ctx);
        final CellSpecVisitor cellEvaluator = new CellSpecVisitor(this.ctx.cellSpec().outputCol());
        final RowPredicateVisitor rowPredicate = new RowPredicateVisitor();

        for (IRow row : this.inputRow) {

            final AtomValue<?> rowId = row.getRowKey();

            // Evaluate the row predicate and skip row if it evaluates to false
            if (this.ctx.inRowPredicate() != null) {

                if (!rowPredicate.evaluate(this.ctx.inRowPredicate().predicate(), row))
                    continue;
              }

            final IRow filteredRow = rowSpec.filter(row);

            for (Map.Entry<Column, AtomValue<?>> column : filteredRow.entrySet()) {
                final Column inColumnId = column.getKey();
                final AtomValue<?> inValue = column.getValue();

                final VDataVisitor dataVisitor = new VDataVisitor(row, inColumnId, inValue);
                final SplittableVDataVisitor splittableVDataVisitor = new SplittableVDataVisitor(dataVisitor);

                // actually retrieve the value of the appropriate row id
                final List<AtomValue<?>> outRows = splittableVDataVisitor.visit(this.ctx.rowSpec().splittableVData());
                // retrieve the column id. If no family is provided: just use the default one.
                final List<Column> outCols = cellEvaluator.evaluate(row, inColumnId, inValue);

                // retrieve the value
                final AtomValue<?> outVal;

                // in case we have an aggregation function which does not require any value: use a dummy value
                if(this.ctx.cellSpec().splittableVData() == null) {
                    outVal = new AtomValue<Integer>(0);
                    outTimeStamp =  inColumnId.getTimeStamp();
                } else {
                    dataVisitor.timeStampCheck(true);
                    outVal = dataVisitor.visit(this.ctx.cellSpec().splittableVData());
                    //TODO WHAT TO DO WHEN outVal is empty, this means wrong column name
                }

                //if value is null skip the column
                if(outVal==null)
                    continue;

                // append to the aggregation table
                for (AtomValue<?> outRow : outRows) {
                    for (Column outCol : outCols) {
                        if (outCol.getColumnFamily() == null)
                            outCol = new Column(new AtomValue<String>(NotaQLProcess.DEFAULT_COLUMN_FAMILY), outCol.getColumnName(), column.getKey().getTimeStamp());

                        //if aggregation function used, then store in different table to compute the aggregation
                        if(this.ctx.cellSpec().aggFun() != null) {
                            this.aggTableTimeStamp.appendTimeStamp(outRow, outCol, outTimeStamp);
                            this.aggTable.append(outRow, outCol, outVal);
                        }
                        else {
                            this.target.append(outRow, new Column(outCol.getColumnFamily(), outCol.getColumnName(), outTimeStamp), outVal);
                        }
                    }
                }
            }
        }


        this.evaluateAggFun(ctx.cellSpec());

        Map<String, List<Cell>> vCellLists = parseOutputTable(this.target);

        return vCellLists;
    }

    private void evaluateAggFun(NotaQLParser.CellSpecContext cellSpec) {
        // aggregate if we have an aggregate function; in any other case test for the fact that only one result is left
        if(this.ctx.cellSpec().aggFun() != null) {
            final List<AtomValue<?>> atoms = new LinkedList<AtomValue<?>>();

            final AtomVisitor atomVisitor = new AtomVisitor();
            for (NotaQLParser.AtomContext atomContext : this.ctx.cellSpec().atom()) {
                atoms.add(atomVisitor.visit(atomContext));
            }
            for (Map.Entry<AtomValue<?>, Map<Column, List<AtomValue<?>>>> row : this.aggTable.rowMap().entrySet()) {
                final AtomValue<?> rowId = row.getKey();
                Column colId = null;
                AtomValue<?> aggregate = null;
                final Map<Column, List<AtomValue<?>>> cols = row.getValue();
                for (Map.Entry<Column, List<AtomValue<?>>> col : cols.entrySet()) {
                    colId = col.getKey();

                    List<AtomValue<?>> timeStamp = this.aggTableTimeStamp.get(rowId, colId);

                    final List<AtomValue<?>> values = col.getValue();
                    aggregate = this.callAggFun(this.ctx.cellSpec().aggFun(), values, atoms.toArray(new AtomValue<?>[atoms.size()]));
                    this.target.put(rowId, new Column(colId.getColumnFamily(), colId.getColumnName(), AtomValue.MAX(timeStamp)), Arrays.<AtomValue<?>>asList(aggregate));
                }
            }
        } else {
            for (List<AtomValue<?>> values : this.aggTable.values()) {
                if(values.size() > 1)
                    throw new ParseCancellationException("no aggregation function was used.");
            }

        }
    }

    /**
     * Aggregates all data currently stored in the target table to one value using the grouped values.
     *
     * @return
     */
    public AtomValue<?> callAggFun(NotaQLParser.AggFunContext funCtx, List<AtomValue<?>> values, AtomValue<?>... atoms) {
        final List<AtomValue<Number>> numberValues;
        final List<AtomValue<Comparable>> comparableValues;
        final List<AtomValue<Object>> objectValues;
        switch (funCtx.fun.getType()) {
            case NotaQLParser.SUM:
                numberValues = AtomValue.convertList(values, Number.class);
                return new Sum().aggregate(numberValues, atoms);
            case NotaQLParser.COUNT:
                return new Count().aggregate(values, atoms);
            case NotaQLParser.MAX:
                comparableValues = AtomValue.convertList(values, Comparable.class);
                return new Max().aggregate(comparableValues, atoms);
            case NotaQLParser.MIN:
                comparableValues = AtomValue.convertList(values, Comparable.class);
                return new Min().aggregate(comparableValues, atoms);
            case NotaQLParser.AVG:
                numberValues = AtomValue.convertList(values, Number.class);
                return new Avg().aggregate(numberValues, atoms);
            default:
                throw new RuntimeException("unknown aggregation function: " + NotaQLParser.tokenNames[funCtx.fun.getType()]);
        }
    }

    public static Map<String, List<Cell>> parseOutputTable(AggregationTable<AtomValue<?>, Column, AtomValue<?>> rowLists){

        String rowKey = "";
        String columnFamily = "";
        String columnQualifier = "";
        long timestamp = 0L;
        String value = "";

        KeyValue keyValue = null;

        List<Cell> cells = null;

        Map<String, List<Cell>> vCellLists = new LinkedHashMap<>();

        for (Map.Entry<AtomValue<?>, Map<Column, List<AtomValue<?>>>> row : rowLists.rowMap().entrySet()) {

            int i=0;
            cells = new ArrayList<Cell>(row.getValue().entrySet().size());
            for (Map.Entry<Column, List<AtomValue<?>>> col : row.getValue().entrySet()) {

                if(col.getValue().size() != 1) {
                    System.err.println(col.getKey().toString() + ": " + col.getValue().toString() + " Wrong number of elements in list!");
                } else {
                    columnFamily = col.getKey().getColumnFamily().toString();
                    columnQualifier = col.getKey().getColumnName().toString();
                    value = col.getValue().get(0).toString();
                }
                AtomValue<?> tStamp = col.getKey().getTimeStamp();
                timestamp = AtomValue.toLong(tStamp.toString());

                rowKey = row.getKey().getAtomValue().toString();

                keyValue = new KeyValue(rowKey.getBytes(), columnFamily.getBytes(), columnQualifier.getBytes(), timestamp, value.getBytes());
                cells.add(keyValue);

            }

            vCellLists.put(rowKey, cells);

        }

        return vCellLists;
    }
}
