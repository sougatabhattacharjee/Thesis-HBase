package hbase.transformation.notaql;

import hbase.transformation.hbase.HBaseVShell;
import hbase.vshell.VirtualShell;
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
public class Split {
    public static final String inTable = "Webinfo";

    private Configuration conf;
    private HBaseAdmin admin;
    private String host;

    @Test
    public void WordCount() throws Exception {

        String inView = "s1";

        InitData(); //create table data first

//        String notaQL = "\"OUT._r <- IN._r, OUT.$(IN._c) <- IN._v;\"";

        String notaQL = "\"OUT._r <- IN._r, OUT.count <- COUNT(IN.body.split(' '));\"";

        String command = "define \""+inView+"\", \""+inTable+"\", "+notaQL;

        VirtualShellUtils.test(command);
    }

    @Test
    public void TermIndex() throws Exception {

        String inView = "s2";

        InitData(); //create table data first

        String notaQL = "\"OUT._r <- IN.body.split(' '), OUT.$(IN._r) <- COUNT();\"";

        String command = "define \""+inView+"\", \""+inTable+"\", "+notaQL;

        VirtualShellUtils.test(command);
    }

    @Test
    public void TermCount() throws Exception {

        String inView = "s3";

        InitData(); //create table data first

        String notaQL = "\"OUT._r <- IN.body.split(' '), OUT.count <- COUNT();\"";

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
            deleteTable(Split.inTable);
        } catch(Exception ignored) {
        }

        // create table
        HTableDescriptor descriptor = new HTableDescriptor(TableName.valueOf(Split.inTable));
        descriptor.addFamily(new HColumnDescriptor("info"));

        admin.createTable(descriptor);

        HTable table = new HTable(conf, Split.inTable);

        Put put = new Put(Bytes.toBytes("Wikipedia"));

        put.add(Bytes.toBytes("info"), Bytes.toBytes("crawled"), Bytes.toBytes("17:35"));
        put.add(Bytes.toBytes("info"), Bytes.toBytes("pr"), Bytes.toBytes("0.333"));
        put.add(Bytes.toBytes("info"), Bytes.toBytes("body"), Bytes.toBytes("here all the information can be found here"));
//        put.add(Bytes.toBytes("info"), Bytes.toBytes("words"), Bytes.toBytes("6"));

        table.put(put);

        put = new Put(Bytes.toBytes("Twitter"));

        put.add(Bytes.toBytes("info"), Bytes.toBytes("crawled"), Bytes.toBytes("17:36"));
        put.add(Bytes.toBytes("info"), Bytes.toBytes("pr"), Bytes.toBytes("0.167"));
        put.add(Bytes.toBytes("info"), Bytes.toBytes("body"), Bytes.toBytes("click here for more information"));
//        put.add(Bytes.toBytes("info"), Bytes.toBytes("words"), Bytes.toBytes("5"));

        table.put(put);

        put = new Put(Bytes.toBytes("Quora"));

        put.add(Bytes.toBytes("info"), Bytes.toBytes("crawled"), Bytes.toBytes("17:38"));
        put.add(Bytes.toBytes("info"), Bytes.toBytes("pr"), Bytes.toBytes("0.160"));
        put.add(Bytes.toBytes("info"), Bytes.toBytes("body"), Bytes.toBytes("click Quora for more information of asked questions here and answered"));
//        put.add(Bytes.toBytes("info"), Bytes.toBytes("words"), Bytes.toBytes("5"));

        table.put(put);



        table.flushCommits();
        table.close();
    }

}
