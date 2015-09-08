package hbase.transformation.notaql;

import hbase.transformation.notaql.aggregation.*;
import hbase.transformation.notaql.antlr4.NotaQLBaseVisitor;
import hbase.transformation.notaql.antlr4.NotaQLParser;
import hbase.transformation.notaql.hbase.*;
import hbase.transformation.notaql.visitors.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;

import java.util.*;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date March 05, 2015
 * @brief Application class to test
 */
public class NotaQLTransformation extends NotaQLBaseVisitor {

    private final NotaQLParser.TransformationContext ctx;
    private InputTableRow inputRow;
//    private List<IRow> rowLists = new ArrayList<IRow>();
    private AggregationTable<AtomValue<?>, Column, AtomValue<?>> target;
    private AggregationTable<AtomValue<?>, Column, AtomValue<?>> aggTable;
    private AggregationTable<AtomValue<?>, Column, AtomValue<?>> aggTableTimeStamp;
    private static AtomValue<?> outTimeStamp = new AtomValue<Long>(0L);

    public static void setOutTimeStamp(AtomValue<?> outTimeStamp) {
        NotaQLTransformation.outTimeStamp = outTimeStamp;
    }

     /**
     * Constructs the transformation visitor for the given TransformationContext
     * @param ctx
     */
    public NotaQLTransformation(@NotNull NotaQLParser.TransformationContext ctx) {
        this.target = new AggregationTable<AtomValue<?>, Column, AtomValue<?>>();
        this.aggTable = new AggregationTable<AtomValue<?>, Column, AtomValue<?>>();
        this.aggTableTimeStamp = new AggregationTable<AtomValue<?>, Column, AtomValue<?>>();
        this.ctx = ctx;
    }

    public void transformation(InputTableRow inputRow) {

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

                // apply cell predicate if it exists

             }

            final IRow filteredRow = rowSpec.filter(row);

//            System.out.println("filteredRow.size() = " + filteredRow.size());


            for (Map.Entry<Column, AtomValue<?>> column : filteredRow.entrySet()) {
                final Column inColumnId = column.getKey();
                final AtomValue<?> inValue = column.getValue();

//                System.out.println("inColumnId = " + column.getKey().getTimeStamp());

//                System.out.println("inColumnId = " + inColumnId);
//                System.out.println("inValue = " + inValue);
//                System.out.println("filteredRow = " + filteredRow.getRowKey());
                final VDataVisitor dataVisitor = new VDataVisitor(row, inColumnId, inValue);
                final SplittableVDataVisitor splittableVDataVisitor = new SplittableVDataVisitor(dataVisitor);

                // actually retrieve the value of the appropriate row id
                final List<AtomValue<?>> outRows = splittableVDataVisitor.visit(this.ctx.rowSpec().splittableVData());

//                System.out.println("outRows = " + outRows);
//
//                System.out.println("+ row = " + row);
//                System.out.println(" inColumnId = " + inColumnId);
//                System.out.println("inValue = " + inValue);
                
                // retrieve the column id. If no family is provided: just use the default one.
                final List<Column> outCols = cellEvaluator.evaluate(row, inColumnId, inValue);

                // retrieve the value
                final AtomValue<?> outVal;

                // in case we have an aggregation function which does not require any value: use a dummy value
                if(this.ctx.cellSpec().splittableVData() == null) {
                    outVal = new AtomValue<Integer>(0);
                    outTimeStamp =  inColumnId.getTimeStamp();
                } else {
                    //1st check if aggregate function is used
                    //dataVisitor.aggCheck(this.ctx);
                    dataVisitor.timeStampCheck(true);
                    outVal = dataVisitor.visit(this.ctx.cellSpec().splittableVData());
                    //TODO WHAT TO DO WHEN outVal is empty, this means wrong column name
                }

                //if value is null skip the column
                if(outVal==null)
                    continue;

//                System.out.println("outVal = " + outVal);


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

//                        System.out.println("outRow = " + outRow);
//                        System.out.println("outCol = " + outCol);
//                        System.out.println("outVal = " + outVal);

                    }
                }

            }
       }


        this.evaluateAggFun(ctx.cellSpec());

        InputTable.rowPrint(this.target);


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
//                List<AtomValue<?>> values = new LinkedList<>();
                final Map<Column, List<AtomValue<?>>> cols = row.getValue();
                for (Map.Entry<Column, List<AtomValue<?>>> col : cols.entrySet()) {
                    colId = col.getKey();

                    List<AtomValue<?>> timeStamp = this.aggTableTimeStamp.get(rowId, colId);

                    final List<AtomValue<?>> values = col.getValue();
                    //if values is null then return 0 otherwise return 0 if the values instance of string else return the value
//                    values.add(col.getValue().get(0)==null?new AtomValue<Integer>(0):
//                            (col.getValue().get(0).getAtomValue() instanceof String?new AtomValue<Integer>(0):col.getValue().get(0)));

//                    System.out.println("values = " + values);
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

    public Iterator<Result> transform(ResultScanner rs) {

        Map<?,?> xxx = null;

        Iterator<?> keys = xxx.keySet().iterator();

        return new Iterator<Result>() {
            @Override
            public boolean hasNext() {
                return keys.hasNext();
            }

            @Override
            public Result next() {
                Object key = keys.next();
                Object[] cells = (Object[]) xxx.get(key);
//                Result result = new Result(cells);
                return null;
            }
        };

    }
}
