package hbase.transformation.notaql.hbase;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

/**
* Created by IntelliJ IDEA 14.0
*
* @author sougata
* @date March 05, 2015
* @brief Iterates over the rows of a specified input hbase table
*/
public class InputTable implements InputTableRow {
    private final HTable table;
    private final Scan scan;
    private final ResultScanner scanner;
    private final Iterator<Result> iterator;

    public InputTable(Configuration conf, String table) throws IOException {
        this.table = new HTable(conf, table);
        this.scan = new Scan();
        this.scanner = this.table.getScanner(this.scan);
        this.iterator = this.scanner.iterator();
    }


    //TODO Lazy Transformation like spark.
    /*
     * Transformations in Spark are “lazy”, meaning that they do not compute their results right away. Instead, they just “remember” the operation to be performed and the dataset (e.g., file) to which the operation is to be performed. The transformations are only actually computed when an action is called and the result is returned to the driver program. This design enables Spark to run more efficiently. For example, if a big file was transformed in various ways and passed to first action, Spark would only process and return the result for the first line, rather than do the work for the entire file.
     */
    @Override
    public Iterator<Row> iterator() {
        return new Iterator<Row>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Row next() {
                final Result result = iterator.next();

                final NavigableMap<byte[], NavigableMap<byte[], byte[]>> inCols = result.getNoVersionMap();
                //print(inCols);
//for (Cell c : result.listCells()){
//    c.
//}


                final String rowKey = Bytes.toString(result.getRow());
                final Row outRow = new Row(AtomValue.parse(rowKey));

                int i = 0;

                for (Map.Entry<byte[], NavigableMap<byte[], byte[]>> colFamily : inCols.entrySet()) {

                    final AtomValue<?> colFamilyName = AtomValue.parse(Bytes.toString(colFamily.getKey()));

                    for (Map.Entry<byte[], byte[]> col : colFamily.getValue().entrySet()) {

//                        System.out.print("timestamp = " + (result.raw()[i++].getTimestamp())+" ");

                        final AtomValue<?> timeStamp = AtomValue.parse(result.raw()[i++].getTimestamp()+"");

                        final AtomValue<?> columnName = AtomValue.parse(Bytes.toString(col.getKey()));
                        final AtomValue<?> value = AtomValue.parse(Bytes.toString(col.getValue()));

//                        System.out.print("colFamilyName = " + colFamilyName+"  ");
//                        System.out.print("columnName = " + columnName+"\n");

                        outRow.put(new Column(colFamilyName, columnName, timeStamp), value);
                    }
                }
                return outRow;
            }

            @Override
            public void remove() {
                iterator.remove();
            }
        };
    }

    //TODO print only first 100 rows. on press key next 100 and so on..
    // public static void rowPrint(List<IRow> rowLists){

//         long rowCount = 0;
//         String heading1 = "ROW";
//         String heading2 = "COLUMN+CELL";
//
//         System.out.printf( "%-25s %-25s\n", heading1, heading2);
////         System.out.println("");
//
//         final long startTime = System.nanoTime();
//         for(IRow row : rowLists) {
//             rowCount++;
//             for (Map.Entry<Column, AtomValue<?>> column : row.entrySet()) {
//
//                 final Column inColumnId = column.getKey();
//                 final AtomValue<?> inValue = column.getValue();
//
//                 String p = "column=" + inColumnId +", timestamp="+System.currentTimeMillis()+", value=" + inValue;
//
//                 System.out.printf( "%-25s %-25s\n", row.getRowKey().getAtomValue(), p);
////                 System.out.println("");
//
//             }
//
//            // System.out.println("");
//         }
//         final long endTime = System.nanoTime();
//         long elapsedTime = endTime - startTime;
//         double seconds = (double)elapsedTime / 1000000000.0;
//         System.out.println(rowCount+" row(s) in "+new DecimalFormat("#.####").format(seconds)+" seconds");
//}

    public static void rowPrint(AggregationTable<AtomValue<?>, Column, AtomValue<?>> rowLists){
        long rowCount = 0;
        String heading1 = "ROW";
        String heading2 = "COLUMN+CELL";
        System.out.print("\n");
        System.out.printf( "%-25s %-25s\n", heading1, heading2);
        final long startTime = System.nanoTime();
        String inColumnId="";
        String inValue="";

        for (Map.Entry<AtomValue<?>, Map<Column, List<AtomValue<?>>>> row : rowLists.rowMap().entrySet()) {
            rowCount++;

            for (Map.Entry<Column, List<AtomValue<?>>> col : row.getValue().entrySet()) {
                if(col.getValue().size() != 1) {
                    System.err.println(col.getKey().toString() + ": " + col.getValue().toString() + " Wrong number of elements in list!");
                } else {
                    inColumnId = col.getKey().toString();
                    inValue = col.getValue().get(0).toString();

                }
                //TODO take timestamp from base table, print maximum timestamp for each row in the case of aggregation
                String p = "column=" + inColumnId +", timestamp="+col.getKey().getTimeStamp()+", value=" + inValue;
                System.out.printf( "%-25s %-25s\n", row.getKey().getAtomValue(), p);
            }
        }
        final long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        double seconds = (double)elapsedTime / 1000000000.0;
        System.out.println("\n"+rowCount+" row(s) in "+new DecimalFormat("#.####").format(seconds)+" seconds");
        System.out.print("\n");
    }

//    public static void rowPrint(CellTable<AtomValue<?>, Column, AtomValue<?>> rowLists){
//        long rowCount = 0;
//        String heading1 = "ROW";
//        String heading2 = "COLUMN+CELL";
//        System.out.printf( "%-25s %-25s\n", heading1, heading2);
//        final long startTime = System.nanoTime();
//        String inColumnId="";
//        String inValue="";
//
//        for (Map.Entry<AtomValue<?>, Map<Column, List<AtomValue<?>>>> row : rowLists.rowMap().entrySet()) {
//            rowCount++;
//
//            for (Map.Entry<Column, List<AtomValue<?>>> col : row.getValue().entrySet()) {
//                if(col.getValue().size() != 1) {
//                    System.err.println(col.getKey().toString() + ": " + col.getValue().toString() + " Wrong number of elements in list!");
//                } else {
//                    inColumnId = col.getKey().toString();
//                    inValue = col.getValue().get(0).toString();
//                    //System.out.println(col.getKey().toString() + ": " + col.getValue().get(0).toString());
//                }
//
//                String p = "column=" + inColumnId +", timestamp="+System.currentTimeMillis()+", value=" + inValue;
//                System.out.printf( "%-25s %-25s\n", row.getKey().getAtomValue(), p);
//            }
//        }
//        final long endTime = System.nanoTime();
//        long elapsedTime = endTime - startTime;
//        double seconds = (double)elapsedTime / 1000000000.0;
//        System.out.println(rowCount+" row(s) in "+new DecimalFormat("#.####").format(seconds)+" seconds");
//
//    }
     
     
     public static void print(NavigableMap<byte[], NavigableMap<byte[], byte[]>> inCols){
         System.out.println("inCols.firstKe = " + inCols.size());

         NavigableMap<String, Integer> nav = new TreeMap<String, Integer>();
         nav.put("key1", 1);
         nav.put("key2", 2);
         nav.put("key3", 3);
         System.out.printf("The Whole:%s", nav.size());
         
     }

     public String[] getColumnsInColumnFamily(Result r, String ColumnFamily)
     {

         NavigableMap<byte[], byte[]> familyMap = r.getFamilyMap(Bytes.toBytes(ColumnFamily));
         String[] Quantifers = new String[familyMap.size()];

         int counter = 0;
         for(byte[] bQunitifer : familyMap.keySet())
         {
             Quantifers[counter++] = Bytes.toString(bQunitifer);

         }

         return Quantifers;
     }

}
