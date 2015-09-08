package hbase.transformation.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 *
 * @date January 14, 2015
 *
 * @brief Application class to test HBase SingleColumnValueFilter.
 */

public class HBaseSingleQualifierFilter {

    private static Configuration conf = null;

    static {
        conf = HBaseConfiguration.create();
        conf.clear();

        conf.set("hbase.zookeeper.quorum", "localhost");
        conf.set("hbase.zookeeper.property.clientPort", "2181");

        try {
            HBaseAdmin.checkHBaseAvailable(conf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("HBase is running!");
    }

    public static String operationValue(String op)
    {
        if(op.equalsIgnoreCase("="))
            return "EQUAL";
        if(op.equalsIgnoreCase("!="))
            return "NOT_EQUAL";
        if(op.equalsIgnoreCase("<"))
            return "LESS";
        if(op.equalsIgnoreCase(">"))
            return "GREATER";
        if(op.equalsIgnoreCase("<="))
            return "LESS_OR_EQUAL";
        if(op.equalsIgnoreCase(">="))
            return "GREATER_OR_EQUAL";


        return null;
    }

    public static void selectRowKeyFamilyColumn(String tablename, String rowKey, String family, String column)
            throws IOException
    {
        HTable table = new HTable(conf, tablename);

        List<Filter> filters = new ArrayList<Filter>();

        SingleColumnValueFilter colValFilter = new SingleColumnValueFilter(Bytes.toBytes(family), Bytes.toBytes(column)
                , CompareFilter.CompareOp.valueOf(operationValue(">")), new BinaryComparator(Bytes.toBytes("50000")));
        colValFilter.setFilterIfMissing(false);
        filters.add(colValFilter);

        FilterList fl = new FilterList(filters);


        Scan g = new Scan();
        g.addColumn(family.getBytes(), column.getBytes());

        g.setFilter(fl);

        ResultScanner rs = table.getScanner(g);

        for (Result r : rs) {
            System.out.println("???rowkey:" + new String(r.getRow()));
            for (KeyValue keyValue : r.raw()) {
                System.out.println("??" + new String(keyValue.getFamily())
                        + "====?:" + new String(keyValue.getValue()));
            }
        }

}

    public ResultScanner singleColumnFilter(String tableName, String columnFamily, String qualifier, String Operation, String value) throws IOException
    {
        HTable table = new HTable(conf, tableName);

        List<Filter> filters = new ArrayList<Filter>();

        SingleColumnValueFilter colValFilter = new SingleColumnValueFilter(Bytes.toBytes(columnFamily), Bytes.toBytes(qualifier)
                , CompareFilter.CompareOp.valueOf(operationValue(Operation)), new BinaryComparator(Bytes.toBytes(value)));
        colValFilter.setFilterIfMissing(false);
        filters.add(colValFilter);

        FilterList fl = new FilterList(filters);


        Scan scan = new Scan();
//        scan.addColumn(Bytes.toBytes("Information"), Bytes.toBytes("EmpCode"));
       scan.addColumn(Bytes.toBytes("Information"), Bytes.toBytes("EmpName"));
        scan.addColumn(Bytes.toBytes("Information"), Bytes.toBytes("EmpSal"));

        scan.setFilter(fl);




        ResultScanner scanner = table.getScanner(scan);
//        String key = new String("~");
//        String keyFlag = new String("~");
//        System.out.println("Scanning table... ");
//        for (Result result : scanner) {
//            //System.out.println("getRow:"+Bytes.toString(result.getRow()));
//            key = "~";
//            for (KeyValue kv : result.raw()) {
//
//                if (key.compareTo(keyFlag)==0)
//                {
//                    key = Bytes.toString(kv.getRow());
//                    System.out.print("Key: " + key);
//                }
//                //System.out.print("Family - "+Bytes.toString(kv.getFamily()));
//
//                //System.out.print(", Buffer - "+Bytes.toString(kv.getBuffer() ));
//                //System.out.print(", FamilyOffset - " + kv.getFamilyOffset() );
//                System.out.print(", "+Bytes.toString(kv.getFamily())+"."+Bytes.toString(kv.getQualifier()));
//                System.out.print("=" +Bytes.toString(kv.getValue()));
//            }
//            System.out.println("");
//            System.out.println("-------------------");
//        }
//
//        scanner.close();
//        System.out.println("Completed ");

        return scanner;
    }

    public  static void print(ResultScanner scanner)
    {
                String key = new String("~");
        String keyFlag = new String("~");
        System.out.println("Scanning tablell... ");
        for (Result result : scanner) {
            //System.out.println("getRow:"+Bytes.toString(result.getRow()));
            key = "~";
            for (KeyValue kv : result.raw()) {

                if (key.compareTo(keyFlag)==0)
                {
                    key = Bytes.toString(kv.getRow());
                    System.out.print("Key: " + key);
                }
                //System.out.print("Family - "+Bytes.toString(kv.getFamily()));

                //System.out.print(", Buffer - "+Bytes.toString(kv.getBuffer() ));
                //System.out.print(", FamilyOffset - " + kv.getFamilyOffset() );
                System.out.print(", "+Bytes.toString(kv.getFamily())+"."+Bytes.toString(kv.getQualifier()));
                System.out.print("=" +Bytes.toString(kv.getValue()));
            }
            System.out.println("");
            System.out.println("-------------------");
        }

        scanner.close();
        System.out.println("Completed ");
    }

    public static void main(String[] args) throws IOException {

        HBaseSingleQualifierFilter hbase = new HBaseSingleQualifierFilter();
//        String columnFamily = "Information";
        String tableName = args[0];
        String columnFamily = args[1];
        String qualifier = args[2];
        String Operation = args[3];
        String value = args[4];
        //hbase.columnFilter("Employee","Information","EmpSal","LESS_OR_EQUAL","50000");
        ResultScanner rs = hbase.singleColumnFilter(tableName, columnFamily, qualifier, Operation, value);     //selection and projection
    print(rs);

        //selectRowKeyFamilyColumn("Employee","119736","Information","EmpName");

//        Filter colValFilter2 = new SingleColumnValueFilter(Bytes.toBytes("salary"), Bytes.toBytes("da")
//                , CompareFilter.CompareOp.GREATER_OR_EQUAL, new BinaryComparator(Bytes.toBytes("150")));
//        filters.add(colValFilter2);

        //Filter colValFilter3 = new SingleColumnValueFilter(Bytes.toBytes("ename"), Bytes.toBytes("fname")
        //      , CompareFilter.CompareOp.GREATER_OR_EQUAL, new SubstringComparator("jack"));
        //filters.add(colValFilter3);





    }
}
