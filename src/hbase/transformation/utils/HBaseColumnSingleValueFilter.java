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
 * @date November 14, 2014
 *
 * @brief Application class to test HBase row and column filters. Print all the qualifiers in single line.
 */

public class HBaseColumnSingleValueFilter {


    public String operationValue(String op)
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

    public void columnFilter(String tableName, String columnFamily, String qualifier, String Operation, String value) throws IOException
    {
        HTable table = new HTable(conf, tableName);

        List<Filter> filters = new ArrayList<Filter>();

        Filter famFilter = new FamilyFilter(CompareFilter.CompareOp.EQUAL,
                new BinaryComparator(Bytes.toBytes(columnFamily)));
        filters.add(famFilter);

        Filter colFilter = new QualifierFilter(CompareFilter.CompareOp.EQUAL,
                new BinaryComparator(Bytes.toBytes(qualifier)));

        filters.add(colFilter);

        Filter valFilter = new ValueFilter(CompareFilter.CompareOp.valueOf(operationValue(Operation)),
                new BinaryComparator(Bytes.toBytes(value)));

        filters.add(valFilter);

        FilterList fl = new FilterList( FilterList.Operator.MUST_PASS_ALL, filters);


        Scan scan = new Scan();
        scan.addColumn(Bytes.toBytes(columnFamily),Bytes.toBytes("EmpCode"));
        scan.addColumn(Bytes.toBytes(columnFamily),Bytes.toBytes("EmpName"));
        scan.addColumn(Bytes.toBytes(columnFamily),Bytes.toBytes("EmpSal"));
//        scan.addFamily(Bytes.toBytes(columnFamily));
        scan.setFilter(fl);
        ResultScanner scanner = table.getScanner(scan);
        System.out.println("Scanning table... ");
        for (Result result : scanner) {

            for (KeyValue kv : result.raw()) {
                byte[] qualifierList = kv.getQualifier();
                System.out.println(Bytes.toString(kv.getFamily()));
                for(byte ql : qualifierList)
                {
                    System.out.println(Bytes.toString(kv.getQualifier()));
                    System.out.println("Key: " + Bytes.toString(kv.getRow())  + ", Value: " +Bytes.toString(kv.getValue()));
                }
//                System.out.println("key:"+kv +", Key: " + Bytes.toString(kv.getRow())  + ", Value: " +Bytes.toString(kv.getValue()));
            }
        }

        scanner.close();
        System.out.println("Completed ");//singlecolumnvaluefilter
    }


    public ResultScanner buildScanner(String keyPrefix,String value,HTable ht) throws IOException {
        FilterList allFilters=new FilterList();
        allFilters.addFilter(new PrefixFilter(Bytes.toBytes(keyPrefix)));
        SingleColumnValueFilter filter=new SingleColumnValueFilter(Bytes.toBytes("trans-tags"),Bytes.toBytes("qual2"), CompareFilter.CompareOp.EQUAL,Bytes.toBytes(value));
        filter.setFilterIfMissing(true);
        allFilters.addFilter(filter);
        Scan scan=new Scan();
        scan.addFamily(Bytes.toBytes("EmpCode"));
        scan.addFamily(Bytes.toBytes("trans-type"));
        scan.addFamily(Bytes.toBytes("trans-date"));
        scan.addFamily(Bytes.toBytes("trans-tags"));
        scan.addFamily(Bytes.toBytes("trans-group"));
        scan.setFilter(allFilters);
        return ht.getScanner(scan);
    }

    public static void main(String[] args) throws IOException {
        //Configuration conf = HBaseConfiguration.create();
        HBaseColumnSingleValueFilter hbase = new HBaseColumnSingleValueFilter();
        String columnFamily = "Information";
//        String tableName = args[0];
//        String familys = args[1];
//        String Operation = args[2];
//        String value = args[3];
        //hbase.buildScanner();
        hbase.columnFilter("Employee","Information","EmpSal","<","50000");
        //hbase.(tableName, columnFamily, familys, Operation, value);
    }
}
