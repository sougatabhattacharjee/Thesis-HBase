package hbase.vshell;

import hbase.transformation.connectionPool.HBaseConnection;
import hbase.transformation.hbase.VShellSyntaxBean;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.vhbase.client.*;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;



/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date May 11, 2015
 * @brief Virtual Shell class calls all the API operations Scan, Get, List, Drop and Define.
 */
public class VirtualShell {

    private static final Logger logger = Logger.getLogger(VirtualShell.class.getName());

    private static volatile Configuration conf = null;

    static Date date = new Date();
    static SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
    static File file = new File(System.getProperty("user.home")+File.separator+"log4j"+File.separator+"hbaseVShell-log."+ft.format(date));

    private static VirtualHbaseAdmin virtualHbaseAdmin;

    private static ArrayList<String> HbaseTableList = new ArrayList<String>();

    static{

        conf = HBaseConnection.getConf();
    }

    public enum hbaseVShellOp{
        scan,get,define,drop,list
    }

    public static ArrayList<String> hbaseTableLists() throws IOException {

        HBaseAdmin hbadmin = new HBaseAdmin(conf);
        HTableDescriptor[] tabdesc = hbadmin.listTables();
        for(int i=0; i<tabdesc.length; i++) {
//            System.out.println("Table = " + new String(tabdesc [i].getName()));

            HbaseTableList.add("\"" + new String(tabdesc[i].getName()) + "\"");
        }

        return HbaseTableList;
    }

    public static boolean containsOperation(String test) {
       for (hbaseVShellOp c : hbaseVShellOp.values()) {
            if (c.name().equalsIgnoreCase(test)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Scan a view
     */
    public static void vScan(String viewName) throws Exception {
        VirtualResultScanner virtualResultScanner = null;
        long rowCount = 0;
        try {
            final long startTime = System.nanoTime();
            VirtualHTable vTable = new VirtualHTable(conf, viewName);
            Scan scan = new Scan();
            virtualResultScanner = vTable.getScanner(scan);

            String heading1 = "ROW";
            String heading2 = "COLUMN+CELL";

            System.out.print("\n");
            System.out.printf( "%-25s %-25s\n", heading1, heading2);

            for (Result result : virtualResultScanner) {
                rowCount++;
                for (Cell rowKV : result.rawCells()) {
                    String rowKey = new String(CellUtil.cloneRow(rowKV));
                    String columnFamily = new String(CellUtil.cloneFamily(rowKV));
                    String columnQualifier = new String(CellUtil.cloneQualifier(rowKV));
                    String value = new String(CellUtil.cloneValue(rowKV));

                    String p = "column=" + columnFamily + ":" + columnQualifier +", timestamp="+rowKV.getTimestamp()+", value=" + value;
                    System.out.printf("%-25s %-25s\n", rowKey, p);
                }
            }
            final long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            double seconds = (double)elapsedTime / 1000000000.0;
            System.out.println("\n"+rowCount+" row(s) in "+new DecimalFormat("#.####").format(seconds)+" seconds");

            virtualResultScanner.close();
            System.out.print("\n");
        }
        catch (Exception ex){
            System.out.println("\nERROR: "+ex);
            System.out.println("\nTo view more details go to log file : "+file+"\n");
            logger.error("\"An error occurred!!\"", ex);
        }
    }

    /**
     * Scan a view
     */
    public static void Scan(String viewName) throws Exception {
        ResultScanner virtualResultScanner = null;
        long rowCount = 0;
        try {
            final long startTime = System.nanoTime();
            HTable vTable = new HTable(conf, viewName);
            Scan scan = new Scan();
            virtualResultScanner = vTable.getScanner(scan);

            String heading1 = "ROW";
            String heading2 = "COLUMN+CELL";

            System.out.print("\n");
            System.out.printf( "%-25s %-25s\n", heading1, heading2);

            for (Result result : virtualResultScanner) {
                rowCount++;
                for (Cell rowKV : result.rawCells()) {
                    String rowKey = new String(CellUtil.cloneRow(rowKV));
                    String columnFamily = new String(CellUtil.cloneFamily(rowKV));
                    String columnQualifier = new String(CellUtil.cloneQualifier(rowKV));
                    String value = new String(CellUtil.cloneValue(rowKV));

                    String p = "column=" + columnFamily + ":" + columnQualifier +", timestamp="+rowKV.getTimestamp()+", value=" + value;
                    System.out.printf("%-25s %-25s\n", rowKey, p);
                }
            }
            final long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            double seconds = (double)elapsedTime / 1000000000.0;
            System.out.println("\n"+rowCount+" row(s) in "+new DecimalFormat("#.####").format(seconds)+" seconds");

            virtualResultScanner.close();
            System.out.print("\n");
        }
        catch (Exception ex){
            System.out.println("\nERROR: "+ex);
            System.out.println("\nTo view more details go to log file : "+file+"\n");
            logger.error("\"An error occurred!!\"", ex);
        }
    }

    /**
     * Get a row of view and HBase Table
     */
    public static void vGet(String viewName, String rowKey) throws Exception {

        long rowCount = 0;
        try {
            final long startTime = System.nanoTime();
            VirtualHTable vTable = new VirtualHTable(conf, viewName);
            Get get = new Get(rowKey.getBytes());
            Result result = vTable.get1(get);

            String heading1 = "ROW";
            String heading2 = "COLUMN+CELL";

            System.out.print("\n");
            System.out.printf( "%-25s %-25s\n", heading1, heading2);


                for (Cell rowKV : result.rawCells()) {
                    rowCount++;
                    String row = new String(CellUtil.cloneRow(rowKV));
                    String columnFamily = new String(CellUtil.cloneFamily(rowKV));
                    String columnQualifier = new String(CellUtil.cloneQualifier(rowKV));
                    String value = new String(CellUtil.cloneValue(rowKV));

                    String p = "column=" + columnFamily + ":" + columnQualifier + ", timestamp=" + rowKV.getTimestamp() + ", value=" + value;
                    System.out.printf("%-25s %-25s\n", row, p);
                }

            final long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            double seconds = (double)elapsedTime / 1000000000.0;
            System.out.println("\n"+rowCount+" row(s) in "+new DecimalFormat("#.####").format(seconds)+" seconds");

            System.out.print("\n");

        }
        catch (Exception ex){
            System.out.println("\nERROR: " + ex);
            System.out.println("\nTo view more details go to log file : "+file+"\n");
            logger.error("\"An error occurred!!\"", ex);
        }
    }

    /**
     * Get a row of view and HBase Table
     */
    public static void Get(String viewName, String rowKey) throws Exception {

        long rowCount = 0;
        try {
            final long startTime = System.nanoTime();
            HTable vTable = new HTable(conf, viewName);
            Get get = new Get(rowKey.getBytes());
            Result result = vTable.get(get);

            String heading1 = "ROW";
            String heading2 = "COLUMN+CELL";

            System.out.print("\n");
            System.out.printf( "%-25s %-25s\n", heading1, heading2);


            for (Cell rowKV : result.rawCells()) {
                rowCount++;
                String row = new String(CellUtil.cloneRow(rowKV));
                String columnFamily = new String(CellUtil.cloneFamily(rowKV));
                String columnQualifier = new String(CellUtil.cloneQualifier(rowKV));
                String value = new String(CellUtil.cloneValue(rowKV));

                String p = "column=" + columnFamily + ":" + columnQualifier + ", timestamp=" + rowKV.getTimestamp() + ", value=" + value;
                System.out.printf("%-25s %-25s\n", row, p);
            }

            final long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            double seconds = (double)elapsedTime / 1000000000.0;
            System.out.println("\n"+rowCount+" row(s) in "+new DecimalFormat("#.####").format(seconds)+" seconds");

            System.out.print("\n");

        }
        catch (Exception ex){
            System.out.println("\nERROR: " + ex);
            System.out.println("\nTo view more details go to log file : "+file+"\n");
            logger.error("\"An error occurred!!\"", ex);
        }
    }

    public static void processInputString(VShellSyntaxBean vsb) throws IOException {

        String operation = "";

        try {
            operation = vsb.getOperation().trim();

            if(!containsOperation(operation))
                System.out.println("\nNoMethodError: undefined method '"+operation+"'\n");
            else
            {
                if(operation.equalsIgnoreCase("scan"))
                {
                    try {
                        String tableName = vsb.getInputTable().trim();
                        virtualHbaseAdmin = new VirtualHbaseAdmin(conf);

                        //check if the hbase table or view exists
                        if (virtualHbaseAdmin.tableExists(tableName)) {
                            Scan(tableName);
                        }
                        else if (virtualHbaseAdmin.viewExists(tableName)){
                            vScan(tableName);
                        }
                         else
                            throw new HBaseConnection.TableNotFoundException("Table or View \"" + tableName + "\" does not exist!");
                    }
                    catch(HBaseConnection.TableNotFoundException ex)
                    {
                        System.out.printf( "%-25s %-25s\n", "ROW", "COLUMN+CELL");
                        System.out.println(ex.getMessage());
                        logger.error("\"An error occurred!!\"", ex);
                    }

                    catch(Exception ex)
                    {
                        System.out.println("\nERROR: "+ex);
                        System.out.println("\nTo view more details go to log file : "+file+"\n");
                        logger.error("\"An error occurred!!\"", ex);
                    }
                }

                if(operation.equalsIgnoreCase("get"))
                {
                    try {
                        virtualHbaseAdmin = new VirtualHbaseAdmin(conf);
                        String tableName = vsb.getInputTable().trim();
                        String rowKey = vsb.getRowKey().trim();

                        //check if the hbase table or view exists
                        if (virtualHbaseAdmin.tableExists(tableName)) {
                            Get(tableName, rowKey);
                        }
                        else if(virtualHbaseAdmin.viewExists(tableName)){
                            vGet(tableName, rowKey);
                        }
                         else
                            throw new HBaseConnection.TableNotFoundException("Table or View \"" + tableName + "\" does not exist!");
                    }
                    catch(HBaseConnection.TableNotFoundException ex)
                    {
                        System.out.printf( "%-25s %-25s\n", "ROW", "COLUMN+CELL");
                        System.out.println(ex.getMessage());
                        logger.error("\"An error occurred!!\"", ex);
                    }

                    catch(Exception ex)
                    {
                        System.out.println("\nERROR: "+ex);
                        System.out.println("\nTo view more details go to log file : "+file+"\n");
                        logger.error("\"An error occurred!!\"", ex);
                    }
                }

                if(operation.equalsIgnoreCase("define"))
                {
                    try {
                        String virtualTableName=vsb.getVirtualTable().trim();
                        String hbaseTableName = vsb.getInputTable().trim();
                        String notaQLScript=vsb.getNotaqlScript().trim();

                        virtualHbaseAdmin = new VirtualHbaseAdmin(conf);

                        HViewDescriptor viewDescriptor = new HViewDescriptor(virtualTableName);
                        viewDescriptor.addBaseTable(hbaseTableName);
                        viewDescriptor.addNotaqlString(notaQLScript);

                        virtualHbaseAdmin.createView(viewDescriptor);
                    }
                    catch(Exception ex)
                    {
                        System.out.println("\nERROR: "+ex.getMessage());
                        System.out.println("\nTo view more details go to log file : "+file+"\n");
                        logger.error("\"An error occurred!!\"", ex);
                    }
                }

                //drop a table or view
                if(operation.equalsIgnoreCase("drop"))
                {
                    try {
                        String tableName = vsb.getInputTable().trim();

                        virtualHbaseAdmin = new VirtualHbaseAdmin(conf);

                        final long startTime = System.nanoTime();

                        //check if it is a hbase table
                        if (virtualHbaseAdmin.tableExists(tableName)) {

                            virtualHbaseAdmin.disableTable(tableName);

                            virtualHbaseAdmin.deleteTable(tableName);
                        }
                        //check if it is a virtual table or view
                        else if (virtualHbaseAdmin.viewExists(tableName)) {

                            virtualHbaseAdmin.deleteView(tableName);
                        } else
                            throw new HBaseConnection.TableNotFoundException("Table or View \"" + tableName + "\" does not exist!");


                        final long endTime = System.nanoTime();
                        long elapsedTime = endTime - startTime;
                        double seconds = (double) elapsedTime / 1000000000.0;
                        System.out.println("0 row(s) in " + new DecimalFormat("#.####").format(seconds) + " seconds");

                    }
                    catch(Exception ex)
                    {
                        System.out.println("\nERROR: "+ex.getMessage());
                        System.out.println("\nTo view more details go to log file : "+file+"\n");
                        logger.error("\"An error occurred!!\"", ex);
                    }
                }

                //list all tables and views
                if(operation.equalsIgnoreCase("list"))
                {
                    long rowCount = 0;

                    try {
                        final long startTime = System.nanoTime();
                        System.out.print("\n");
                        System.out.println("HBase TABLES and VIEWS");

                        virtualHbaseAdmin = new VirtualHbaseAdmin(conf);

                        // Getting all the list of tables using VirtualHbaseAdmin object
                        HTableDescriptor[] tableDescriptor = virtualHbaseAdmin.listTables();

                        // printing all the table names.
                        for (int i=0; i<tableDescriptor.length;i++ ){
                            System.out.println(tableDescriptor[i].getNameAsString());
                            rowCount++;
                        }

                       // Getting all the list of Views
                        HViewDescriptor[] viewDescriptor = virtualHbaseAdmin.listViews();

                        // printing all the views.
                        for (int i=0; i<viewDescriptor.length;i++ ){
                            System.out.println(viewDescriptor[i].getViewName() +" (view on "+ VirtualShellUtils.ANSI_BLUE +viewDescriptor[i].getBaseTable()+ VirtualShellUtils.ANSI_RESET +")");
                            System.out.println(VirtualShellUtils.ANSI_RED + "*** " + viewDescriptor[i].getNotaqlString() + VirtualShellUtils.ANSI_RESET);
                            rowCount++;
                        }

                        final long endTime = System.nanoTime();
                        long elapsedTime = endTime - startTime;
                        double seconds = (double)elapsedTime / 1000000000.0;
                        System.out.println("\n"+rowCount+" row(s) in "+new DecimalFormat("#.####").format(seconds)+" seconds");
                        System.out.print("\n");
                    }

                    catch(Exception ex)
                    {
                        System.out.println("\nERROR: "+ex);
                        System.out.println("\nTo view more details go to log file : "+file+"\n");
                        logger.error("\"An error occurred!!\"", ex);
                    }

                }

            }
        }
        catch(Exception ex)
        {
            System.out.println("\nERROR: "+ex);
            System.out.println("\nTo view more details go to log file : "+file+"\n");
            logger.error("\"An error occurred!!\"", ex);
        }

    }


    //beginning
    public static void IN() throws IOException {
        VirtualShellUtils.ShellProcessing();
    }



}
