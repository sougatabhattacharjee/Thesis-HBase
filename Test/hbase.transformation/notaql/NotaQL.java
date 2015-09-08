package hbase.transformation.notaql;

import hbase.transformation.hbase.HBaseVShell;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableNotEnabledException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date April 07, 2015
 * @brief Application class to test the whole application
 */
public class NotaQL {

    public static final String inTable = "NotaQLTest";
//    public static final String inView = "ViewTest";

    private Configuration conf;
    private HBaseAdmin admin;
    private String host;

    /**
     * Identity check
     * @throws Exception
     */

    @Test
    public void testQ1() throws Exception {

        String inView = "v1";

        InitData(); //create table data first

        String notaQL = "\"OUT._r <- IN._r, OUT.$(IN._c) <- IN._v;\"";

        String command = "define \""+inView+"\", \""+inTable+"\", "+notaQL;

       HBaseVShell.IN(command);
    }

    /**
     * Selection and Column-Family Projection
     * @throws Exception
     */
    @Test
    public void testQ2() throws Exception {

        String inView = "v2";

        InitData(); //create table data first

        String notaQL = "\"IN: IN.b < '20', OUT._r<-IN._r, OUT.$(IN.cf1:_c) <- IN._v;\"";

        String command = "define \""+inView+"\", \""+inTable+"\", "+notaQL;

        HBaseVShell.IN(command);
    }

    /**
     * Complex Selection and Column Projection
     * @throws Exception
     */
    @Test
    public void testQ3() throws Exception {

        String inView = "v3";

        InitData(); //create table data first

        String notaQL = "\"IN: !(IN.b < '20') || (IN.x='10' && IN.a <= '5' * IN.b), OUT._r<-IN._r, OUT.a <- IN.a;\"";

//        String notaQL = "\"IN: IN.a <= '5' * IN.b, OUT._r<-IN._r, OUT.a <- IN.a;\"";

        String command = "define \""+inView+"\", \""+inTable+"\", "+notaQL;

        HBaseVShell.IN(command);
    }


    /**
     * Horizontal Aggregation
     * @throws Exception
     */

    @Test
    public void testQ4() throws Exception {

        String inView = "v4";

        InitData(); //create table data first

        String notaQL = "\"OUT._r <- IN._r, OUT.sum <- SUM(IN.cf1:_v);\"";

//        String notaQL = "\"OUT._r <- IN._r, OUT.sum <- SUM(IN.a);\"";

//        String notaQL = "\"OUT._r <- IN._r, OUT.min <- COUNT(IN._v);\"";

        String command = "define \""+inView+"\", \""+inTable+"\", "+notaQL;

        HBaseVShell.IN(command);
    }


    /**
     * Grouping and Vertical Aggregation
     * @throws Exception
     */
    @Test
    public void testQ5() throws Exception {

        String inView = "v5";

        InitData(); //create table data first

        String notaQL = "\"OUT._r <- IN.cf1:b, OUT.count <- COUNT();\"";

        String command = "define \""+inView+"\", \""+inTable+"\", "+notaQL;

        HBaseVShell.IN(command);
    }


    /**
     * Multi-Vertical Aggregation
     * @throws Exception
     */
    @Test
    public void testQ6() throws Exception {

        String inView = "v6";

        InitData(); //create table data first

        String notaQL = "\"OUT._r <- 'all', OUT.$(IN._c) <- AVG(IN.cf1:_v);\"";

//        String notaQL = "\"OUT._r <- IN._r, OUT.$(IN._c) <- IN.cf1:_v;\"";

        String command = "define \""+inView+"\", \""+inTable+"\", "+notaQL;

        HBaseVShell.IN(command);
    }


    /**
     * Transposition Check 1
     * @throws Exception
     */
    @Test
    public void testQ7() throws Exception {

        String inView = "v7";

        InitData(); //create table data first

        String notaQL = "\"OUT._r <- IN.cf1:_c, OUT.$(IN._r) <- IN._v;\"";

        String command = "define \""+inView+"\", \""+inTable+"\", "+notaQL;

        HBaseVShell.IN(command);
    }


    /**
     * Transposition Check 2
     * @throws Exception
     */
    @Test
    public void testQ8() throws Exception {

        String inView = "v8";

        InitData(); //create table data first

        String notaQL = "\"OUT._r <- IN._r, OUT.$(IN.x) <- IN.b;\"";

        String command = "define \""+inView+"\", \""+inTable+"\", "+notaQL;

        HBaseVShell.IN(command);
    }


    /**
     * Complex Aggregation
     * @throws Exception
     */
    @Test
    public void testQ9() throws Exception {

        String inView = "v9";

        InitData(); //create table data first

        String notaQL = "\"OUT._r <- IN.cf1:_c, OUT.cf1:x <- SUM(IN.x/(COL_COUNT(IN.cf1)));\"";

        String command = "define \""+inView+"\", \""+inTable+"\", "+notaQL;

        HBaseVShell.IN(command);
    }


    /**
     * Complete Aggregation (Horizontal + Vertical)
     * @throws Exception
     */
    @Test
    public void testQ10() throws Exception {

        String inView = "v10";

        InitData(); //create table data first

        String notaQL = "\"OUT._r <- 'all', OUT.sum <- SUM(IN._v);\"";

        String command = "define \""+inView+"\", \""+inTable+"\", "+notaQL;

        HBaseVShell.IN(command);
    }


    /**
     * Value Manipulation
     * @throws Exception
     */
    @Test
    public void testQ11() throws Exception {

        String inView = "v11";

        InitData(); //create table data first

        String notaQL = "\"OUT._r <- IN._r, OUT.$(IN._c?(@ < '30')) <- IN._v + '1';\"";

        String command = "define \""+inView+"\", \""+inTable+"\", "+notaQL;

        HBaseVShell.IN(command);
    }


    /**
     * Drop Projection & Column absence Check
     * @throws Exception
     */
    @Test
    public void testQ12() throws Exception {

        String inView = "v12";

        InitData(); //create table data first

        String notaQL = "\"IN: !y, OUT._r <- IN._r, OUT.$(IN._c?(!a)) <- IN._v;\"";

        String command = "define \""+inView+"\", \""+inTable+"\", "+notaQL;

        HBaseVShell.IN(command);
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

    public void InitData() throws IOException, InterruptedException {
        this.admin = new HBaseAdmin(conf);

        // Make sure the table data is clean
        try {
            deleteTable(NotaQL.inTable);
        } catch(Exception ignored) {
        }

        // create table
        HTableDescriptor descriptor = new HTableDescriptor(NotaQL.inTable);
        descriptor.addFamily(new HColumnDescriptor("cf1"));
        descriptor.addFamily(new HColumnDescriptor("cf2"));

        admin.createTable(descriptor);



        HTable table = new HTable(conf, NotaQL.inTable);



            Put put = new Put(Bytes.toBytes("r1"));

            put.add(Bytes.toBytes("cf1"), Bytes.toBytes("x"), Bytes.toBytes("10"));
            put.add(Bytes.toBytes("cf1"), Bytes.toBytes("a"), Bytes.toBytes("50"));
            put.add(Bytes.toBytes("cf1"), Bytes.toBytes("b"), Bytes.toBytes("10"));

            Thread.sleep(1000);

            put.add(Bytes.toBytes("cf2"), Bytes.toBytes("c"), Bytes.toBytes("10"));
            put.add(Bytes.toBytes("cf2"), Bytes.toBytes("a"), Bytes.toBytes("90"));

            table.put(put);


         put = new Put(Bytes.toBytes("r2"));

        put.add(Bytes.toBytes("cf1"), Bytes.toBytes("x"), Bytes.toBytes("20"));
        put.add(Bytes.toBytes("cf1"), Bytes.toBytes("y"), Bytes.toBytes("20"));
        put.add(Bytes.toBytes("cf1"), Bytes.toBytes("b"), Bytes.toBytes("10"));

        Thread.sleep(1000);

        put.add(Bytes.toBytes("cf2"), Bytes.toBytes("c"), Bytes.toBytes("10"));


        table.put(put);


        put = new Put(Bytes.toBytes("r3"));

        put.add(Bytes.toBytes("cf1"), Bytes.toBytes("x"), Bytes.toBytes("30"));
        put.add(Bytes.toBytes("cf1"), Bytes.toBytes("z"), Bytes.toBytes("40"));
        put.add(Bytes.toBytes("cf1"), Bytes.toBytes("b"), Bytes.toBytes("20"));

        Thread.sleep(1000);

        put.add(Bytes.toBytes("cf2"), Bytes.toBytes("c"), Bytes.toBytes("20"));
        put.add(Bytes.toBytes("cf2"), Bytes.toBytes("a"), Bytes.toBytes("90"));

        table.put(put);

        table.put(put);


        table.flushCommits();
        table.close();
    }

    public List<CF> generatePersons() {
        final List<CF> persons = new LinkedList<CF>();

        //1st row
        CF cf = new CF();

        cf.setRowKey("r1"); cf.setX("10"); cf.setA("50"); cf.setB("10");

        persons.add(cf);

        cf = new CF();

        cf.setRowKey("r1"); cf.setC("10"); cf.setA("10");

        persons.add(cf);


        //2nd row
        cf = new CF();

        cf.setRowKey("r2"); cf.setX("20"); cf.setY("20"); cf.setB("10");

        persons.add(cf);

        cf = new CF();

        cf.setRowKey("r2"); cf.setC("10");

        persons.add(cf);



        //3rd row
        cf = new CF();

        cf.setRowKey("r3"); cf.setX("30"); cf.setZ("40"); cf.setB("20");

        persons.add(cf);

        cf = new CF();

        cf.setRowKey("r3"); cf.setC("20"); cf.setA("90");

        persons.add(cf);


        return persons;
    }

    private void deleteTable(String name) throws IOException {
        try {
            this.admin.disableTable(name);
        } catch(TableNotEnabledException ignored) {

        }
        this.admin.deleteTable(name);

    }

    public class CF {


        private String rowKey = null;
        private String x = null;
        private String y = null;
        private String z = null;
        private String a = null;
        private String b = null;
        private String c = null;

        public void setRowKey(String rowKey) {
            this.rowKey = rowKey;
        }

        public void setX(String x) {
            this.x = x;
        }

        public void setY(String y) {
            this.y = y;
        }

        public void setZ(String z) {
            this.z = z;
        }

        public void setA(String a) {
            this.a = a;
        }

        public void setB(String b) {
            this.b = b;
        }

        public void setC(String c) {
            this.c = c;
        }

        public String getRowKey() {
            return rowKey;
        }

        public String getX() {
            return x;
        }

        public String getY() {
            return y;
        }

        public String getZ() {
            return z;
        }

        public String getA() {
            return a;
        }

        public String getB() {
            return b;
        }
        public String getC() {
            return c;
        }
    }


}
