package hbase.transformation.notaql;

import hbase.vshell.VirtualShellUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date June 21, 2015
 * @brief Application class to test
 */
public class Employee {
    public static final String inTable = "Employee";

    private Configuration conf;
    private HBaseAdmin admin;
    private String host;

    @Test
    public void testemp_v() throws Exception {

        String inView = "emp_v1";

        InitData(); //create table data first

        String notaQL = "\"IN: IN.Company = 'IBM', OUT._r<-IN._r, OUT.salary <- MAX(IN.Salary:_v);\"";

        String command = "define \""+inView+"\", \""+inTable+"\", "+notaQL;

        VirtualShellUtils.test(command);
    }


    @Test
    public void testemp_v2() throws Exception {

        String inView = "emp_v2";

        InitData(); //create table data first

        String notaQL = "\"IN: Email, OUT._r<-IN._r, OUT.$(IN._c) <- IN._v;\"";

        String command = "define \""+inView+"\", \""+inTable+"\", "+notaQL;

        VirtualShellUtils.test(command);
    }

    @Before
    public void setUp() throws Exception {

        this.conf = HBaseConfiguration.create();
        this.conf.clear();


        this.host = System.getenv("notaql.hbase.host");

        if(host == null)
            host = "localhost";


        conf.set("hbase.zookeeper.quorum", host);
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.master", host + ":60000");
        conf.set("hbase.client.scanner.timeout.period", "600000");
        conf.set("hbase.rpc.timeout", "600000");
        conf.set("hbase.rpc.shortoperation.timeout", "600000");
    }

    private void deleteTable(String name) throws IOException {
        try {
            this.admin.disableTable(name);
        } catch(TableNotEnabledException ignored) {

        }
        this.admin.deleteTable(name);

    }

    public void InitData() throws IOException, InterruptedException {
        this.admin = new HBaseAdmin(conf);

        // Make sure the table data is clean
        try {
            deleteTable(Employee.inTable);
        } catch(Exception ignored) {
        }

        // create table
        HTableDescriptor descriptor = new HTableDescriptor(TableName.valueOf(Employee.inTable));
        descriptor.addFamily(new HColumnDescriptor("info"));
        descriptor.addFamily(new HColumnDescriptor("Salary"));

        admin.createTable(descriptor);

        HTable table = new HTable(conf, Employee.inTable);

        Put put = new Put(Bytes.toBytes("John"));

        put.add(Bytes.toBytes("info"), Bytes.toBytes("YOB"), Bytes.toBytes("1950"));
        put.add(Bytes.toBytes("info"), Bytes.toBytes("Company"), Bytes.toBytes("IBM"));
        put.add(Bytes.toBytes("Salary"), Bytes.toBytes("Basic"), Bytes.toBytes("3000"));
        put.add(Bytes.toBytes("Salary"), Bytes.toBytes("Other"), Bytes.toBytes("700"));
//        put.add(Bytes.toBytes("info"), Bytes.toBytes("words"), Bytes.toBytes("6"));

        table.put(put);

        put = new Put(Bytes.toBytes("Susi"));

        put.add(Bytes.toBytes("info"), Bytes.toBytes("YOB"), Bytes.toBytes("1960"));
        put.add(Bytes.toBytes("info"), Bytes.toBytes("Company"), Bytes.toBytes("IBM"));
        put.add(Bytes.toBytes("Salary"), Bytes.toBytes("Basic"), Bytes.toBytes("4000"));
        put.add(Bytes.toBytes("Salary"), Bytes.toBytes("Other"), Bytes.toBytes("750"));
//        put.add(Bytes.toBytes("info"), Bytes.toBytes("words"), Bytes.toBytes("5"));

        table.put(put);

        put = new Put(Bytes.toBytes("Arthur"));

        put.add(Bytes.toBytes("info"), Bytes.toBytes("YOB"), Bytes.toBytes("1970"));
        put.add(Bytes.toBytes("info"), Bytes.toBytes("Company"), Bytes.toBytes("EMC"));
        put.add(Bytes.toBytes("info"), Bytes.toBytes("Email"), Bytes.toBytes("arthur@emc.com"));
        put.add(Bytes.toBytes("Salary"), Bytes.toBytes("Basic"), Bytes.toBytes("3500"));
        put.add(Bytes.toBytes("Salary"), Bytes.toBytes("Other"), Bytes.toBytes("800"));

        table.put(put);


        put = new Put(Bytes.toBytes("Peter"));

        put.add(Bytes.toBytes("info"), Bytes.toBytes("YOB"), Bytes.toBytes("1965"));
        put.add(Bytes.toBytes("info"), Bytes.toBytes("Company"), Bytes.toBytes("IBM"));
        put.add(Bytes.toBytes("Salary"), Bytes.toBytes("Basic"), Bytes.toBytes("2500"));
        put.add(Bytes.toBytes("Salary"), Bytes.toBytes("Other"), Bytes.toBytes("1000"));

        table.put(put);


        table.flushCommits();
        table.close();
    }

}
