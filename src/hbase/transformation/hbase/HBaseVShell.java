package hbase.transformation.hbase;

import hbase.transformation.antlr4.VShellLexer;
import hbase.transformation.antlr4.VShellParser;
import hbase.transformation.connectionPool.HBaseConnection;
import hbase.transformation.notaql.NotaQLProcess;
import hbase.transformation.notaql.NotaQLTransformation;
import hbase.transformation.notaql.hbase.InputTable;
import hbase.transformation.utils.ViewStore;
import jline.console.ConsoleReader;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.StringsCompleter;
import org.antlr.runtime.CommonToken;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


import org.apache.log4j.ConsoleAppender;
/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 *
 * @date January 22, 2015
 *
 * @brief Main Application class to run the HBase Virtual Console Shell. The shell supports SCAN,GET and DEFINE operation
 * on the HBase table. Virtual table can be created by executing DEFINE operation. The shell supports NotaQL Scripts.
 * Example of DEFINE command : define 'virtualTableName', 'HBaseTableName', "NotaQLScript"
 * Example of NotaQL Script : "IN: IN.Born != '1950', OUT._r<-IN._r, OUT.salary <- IN.Salary;"
 */

public class HBaseVShell {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private static final Logger logger = Logger.getLogger(HBaseVShell.class.getName());



    private static Configuration conf = null;

    private static Map<String,String> virtualTableMapping = new HashMap<String, String>();

    private static Map<String, HBaseViewDefinition> virtualTableMappingNotaQL = new HashMap<String, HBaseViewDefinition>();

    private static LinkedHashMap<String, HBaseViewDefinition> storeView = new LinkedHashMap<String, HBaseViewDefinition>();

    private static ArrayList<String> HbaseTableList = new ArrayList<String>();

    static Date date = new Date();
    static SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
    static File file = new File(System.getProperty("user.home")+File.separator+"log4j"+File.separator+"hbaseVShell-log."+ft.format(date));

    static{

        conf = HBaseConnection.getConf();
    }


    public enum hbaseVShellOp{
        scan,get,define,drop,list
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
     * Scan a real base table of Hbase Table
     */
    public static ResultScanner getAllRecord (String tableName)  {
        ResultScanner ss = null;
        long rowCount = 0;

        try{
            final long startTime = System.nanoTime();
            HTable table = new HTable(conf, tableName);
            Scan s = new Scan();

            ss = table.getScanner(s);

            String heading1 = "ROW";
            String heading2 = "COLUMN+CELL";

            System.out.print("\n");
            System.out.printf( "%-25s %-25s\n", heading1, heading2);

            String key = new String("~");
            String keyFlag = new String("~");

            for (Result result : ss) {
                key = "~";
                rowCount++;
                for (KeyValue kv : result.raw()) {

                    if (key.compareTo(keyFlag) == 0) {
                        key = Bytes.toString(kv.getRow());
                    }
                    String p = "column=" + Bytes.toString(kv.getFamily()) + ":" + Bytes.toString(kv.getQualifier()) +", timestamp="+System.currentTimeMillis()+", value=" + Bytes.toString(kv.getValue());
                    System.out.printf("%-25s %-25s\n", key, p);
                }

      }
            final long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            double seconds = (double)elapsedTime / 1000000000.0;
            System.out.println("\n"+rowCount+" row(s) in "+new DecimalFormat("#.####").format(seconds)+" seconds");

            ss.close();
            System.out.print("\n");

        } catch (IOException ex){
//            System.out.println(ex.getMessage());
            System.out.println("\nERROR: "+ex);
//            System.out.println("\nAn Error Occurred, to view more details go to "+file);
            System.out.println("\nTo view more details go to log file : "+file+"\n");
            logger.error("\"An error occurred!!\"", ex);
        }
        return ss;
    }

    /**
     * Get a row of Hbase Table
     */
    public static void getOneRecord (String tableName, String rowKey) throws IOException{
        HTable table = new HTable(conf, tableName);
        Get get = new Get(rowKey.getBytes());
        Result rs = table.get(get);

        String heading1 = "COLUMN";
        String heading2 = "CELL";

        System.out.println("rs.raw()[0].getTimestamp() = " + rs.raw()[0].getTimestamp());
        
        System.out.print("\n");
        System.out.printf( "%-25s %-25s\n", heading1, heading2);

        String key = new String("~");
        String keyFlag = new String("~");

        key = "~";
        long rowCount = 0;
        final long startTime = System.nanoTime();

        for (KeyValue kv : rs.raw()) {


            System.out.println("kv.getTimestamp() = " + kv.getTimestamp());

            rowCount++;
            if (key.compareTo(keyFlag)==0)
            {
                key = Bytes.toString(kv.getRow());
            }
            String p1 = Bytes.toString(kv.getFamily()) + ":" + Bytes.toString(kv.getQualifier());
            String p2 = "timestamp="+System.currentTimeMillis()+", value=" + Bytes.toString(kv.getValue());
            System.out.printf("%-25s %-25s\n", p1, p2);
        }
        final long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        double seconds = (double)elapsedTime / 1000000000.0;
        System.out.println("\n"+rowCount+" row(s) in "+new DecimalFormat("#.####").format(seconds)+" seconds");

        System.out.print("\n");
    }




      public static void processInputString(VShellSyntaxBean vsb) throws IOException {

        String operation = "";


        try {
            operation = vsb.getOperation().trim();
            //System.out.println(">>>>>>>>>>>>>>"+vsb.getInputTable()+">>>>>>>>>>>");

            if(!containsOperation(operation))
                System.out.println("\nNoMethodError: undefined method '"+operation+"'\n");
            else
            {
                // scan 'tableName'
                if(operation.equalsIgnoreCase("scan"))
                {


                    try {
                        String tableName = vsb.getInputTable().trim();

                        //check if it is a virtual table
                        if(virtualTableMapping.containsKey(tableName))
                        {
                            //printVirtualTable(tableName);

                            NotaQLProcess notaql = virtualTableMappingNotaQL.get(tableName).getNotaql();
                            notaql.setTransformationContext(notaql.getNotaQLContext().transformation());
                            notaql.setNotaQLTransformation(new NotaQLTransformation(notaql.getTransformationContext()));
                            notaql.setIn(new InputTable(conf, virtualTableMappingNotaQL.get(tableName).getBaseTable()));
                            notaql.getNotaQLTransformation().transformation(notaql.getIn());

                            //NotaQLProcess.process(virtualTableMappingNotaQL.get(tableName).);
                        }

                        //check if it is stored in disc
                        else if(ViewStore.readView().containsKey(tableName)){

                            storeView =  ViewStore.readView();
//                            System.out.println("storeView.get(\"v\").getNotaqlScript() = " + storeView.get(tableName).getNotaqlScript());

                            virtualTableMappingNotaQL.put(tableName, new HBaseViewDefinition(storeView.get(tableName).getBaseTable(), storeView.get(tableName).getNotaqlScript()));

                            NotaQLProcess notaql = virtualTableMappingNotaQL.get(tableName).getNotaql();
                            notaql.setTransformationContext(notaql.getNotaQLContext().transformation());
                            notaql.setNotaQLTransformation(new NotaQLTransformation(notaql.getTransformationContext()));
                            notaql.setIn(new InputTable(conf, virtualTableMappingNotaQL.get(tableName).getBaseTable()));
                            notaql.getNotaQLTransformation().transformation(notaql.getIn());


                        }
                        else {
                            if (HBaseConnection.tableExist(tableName)) {
                                getAllRecord(tableName);
                            } else
                                throw new HBaseConnection.TableNotFoundException("\nERROR: Unknown Table or View \"" + tableName + "\"!\n");
                        }

                    }
                    catch(HBaseConnection.TableNotFoundException ex)
                    {
                        System.out.printf( "%-25s %-25s\n", "ROW", "COLUMN+CELL");
                        System.out.println(ex.getMessage());
//                        System.out.println("\nAn Error Occurred, to view more details go to "+file);
                        logger.error("\"An error occurred!!\"", ex);
                    }

                    catch(Exception ex)
                    {
//                        final JPanel panel = new JPanel();
//                        JOptionPane.showMessageDialog(panel, ex.toString(), "An Error Occurred!!", JOptionPane.ERROR_MESSAGE);

                        System.out.println("\nERROR: "+ex);
//                        System.out.println("\nAn Error Occurred, to view more details go to "+file);
                        System.out.println("\nTo view more details go to log file : "+file+"\n");
                        logger.error("\"An error occurred!!\"", ex);
//                        System.out.println("Wrong Input Format for 'scan' operation !!");
//                        System.out.println("Input Format for 'scan': scan \"tableName\"");
                    }
                }

                // get 'tableName', 'row'
                if(operation.equalsIgnoreCase("get"))
                {
                    try {
                        String tableName = vsb.getInputTable().trim();
                        String rowKey = vsb.getRowKey().trim();

                        if(virtualTableMapping.containsKey(tableName))
                        {
                            //getOneVirtualRow(tableName,rowKey);
                        }
                        else {
                            if(HBaseConnection.tableExist(tableName))
                                getOneRecord(tableName, rowKey);
                            else throw new HBaseConnection.TableNotFoundException("\nERROR: Unknown Table or View \"" + tableName + "\"!\n");
                        }
                    }
                    catch(HBaseConnection.TableNotFoundException ex)
                    {
                        System.out.printf( "%-25s %-25s\n", "COLUMN", "CELL");
                        System.out.println(ex.getMessage());
//                        System.out.println("\nAn Error Occurred, to view more details go to "+file);
                        logger.error("\"An error occurred!!\"", ex);
                    }
                    catch(Exception ex)
                    {
//                        System.out.println(ex.getMessage());
                        System.out.println("\nERROR: "+ex);
//                        System.out.println("\nAn Error Occurred, to view more details go to "+file);
                        System.out.println("\nTo view more details go to log file : "+file+"\n");
                        logger.error("\"An error occurred!!\"", ex);
//                        System.out.println("Wrong Input Format for 'get' operation !!");
//                        System.out.println("Input Format for 'get': get \"tableName\", \"rowKey\" ");
                    }
                }

        /* define 'virtualTableName', 'hbaseTableName', "notaQL script"
        define 'virtualTableName', 'hbaseTableName', "OUT.r <- IN.r : born>1950, OUT.c <- IN.c : salary, OUT.v <- IN.v;"
        define 'virtualTableName', 'hbaseTableName', "IN-FILTER:born>1950, OUT.r<-IN.r, OUT.c<-IN.c:salary, OUT.v <- IN.v;"
        define 'v', 'EmployeeNotaQL', "IN-FILTER:Born>1950, OUT.r<-IN.r, OUT.c<-IN.c:Salary, OUT.v <- IN.v;"
        define 'v', 'EmployeeNotaQL', "IN-FILTER:Born>1950, OUT.r<-IN.r, OUT.c<-IN.c:Born, OUT.v <- IN.v;"
        define 'v', 'EmployeeNotaQL', "IN: IN.born > '1950', OUT._r<-IN._r, OUT.salary <- IN.salary;"
        define "v", "EmployeeNotaQL", "IN: IN.Born > '1950', OUT._r<-IN._r, OUT.salary <- IN.Salary;"
        define "v", "EmployeeNotaQL", "IN: IN.Born != '1950', OUT._r<-IN._r, OUT.salary <- IN.Salary;"
         */

                if(operation.equalsIgnoreCase("define"))
                {
                    try {
                        String virtualTableName="";
                        String hbaseTableName = "";
                        String notaQLScript="";

                        virtualTableName = vsb.getVirtualTable().trim();
                        hbaseTableName = vsb.getInputTable().trim();
                        notaQLScript = vsb.getNotaqlScript().trim();

                        HbaseTableList.add("'" + virtualTableName + "'");   //argument completer

                        try{

                            if(HBaseConnection.tableExist(hbaseTableName)) {

                                virtualTableMappingNotaQL.put(virtualTableName, new HBaseViewDefinition(hbaseTableName, notaQLScript));

                            }
                            else throw new HBaseConnection.TableNotFoundException("\nERROR: Unknown Table or View \"" + hbaseTableName + "\"!\n");
                        }
                        catch(HBaseConnection.TableNotFoundException ex)
                        {
                            System.out.println(ex.getMessage());
//                            System.out.println("\nAn Error Occurred, to view more details go to "+file);
                            logger.error("\"An error occurred!!\"", ex);
                        }
                        catch (Exception ex)
                        {
                            //ex.printStackTrace();
//                            System.out.println(ex.getMessage());
                            System.out.println("\nERROR: "+ex);
//                            System.out.println("\nAn Error Occurred, to view more details go to "+file);
                            System.out.println("\nTo view more details go to log file : "+file+"\n");
                            logger.error("\"An error occurred!!\"", ex);
                        }

                        //store view definition into disc for future use
                        HBaseViewDefinition hbd = new HBaseViewDefinition();
                        hbd.setBaseTable(hbaseTableName);
                        hbd.setNotaqlScript(notaQLScript);

                        //before put, get the existing map from disc
                        storeView = ViewStore.readView();

                        storeView.put(virtualTableName,hbd);
                        ViewStore.saveView(storeView);

                        //internal mapping between virtual and physical table
                        virtualTableMapping.put(virtualTableName,hbaseTableName);

                    }
                    catch(Exception ex)
                    {
//                        System.out.println(ex.getMessage());
                        System.out.println("\nERROR: "+ex);
//                        System.out.println("\nAn Error Occurred, to view more details go to "+file);
                        System.out.println("\nTo view more details go to log file : "+file+"\n");
                        logger.error("\"An error occurred!!\"", ex);
//                        System.out.println("Wrong Input Format for 'define' operation !!");
//                        System.out.println("Input Format for 'define': define \"virtualTableName\", \"hbaseTableName\", \"notaQL script\" ");
                    }
                }


                //drop a table or view
                if(operation.equalsIgnoreCase("drop"))
                {

                    try {
                        String tableName = vsb.getInputTable().trim();

                        //check if it is a virtual table
                        if(virtualTableMapping.containsKey(tableName))
                        {
                            final long startTime = System.nanoTime();
                            virtualTableMapping.remove(tableName);              //remove from internal mapping
                            virtualTableMappingNotaQL.remove(tableName);       //remove from notaQL mapping
                            if(ViewStore.readView().containsKey(tableName)){
                                storeView =  ViewStore.readView();
                                storeView.remove(tableName);     //remove from disc
                                ViewStore.saveView(storeView);   //write the updated info into disc
                            }
                            final long endTime = System.nanoTime();
                            long elapsedTime = endTime - startTime;
                            double seconds = (double)elapsedTime / 1000000000.0;
                            System.out.println("0 row(s) in "+new DecimalFormat("#.####").format(seconds)+" seconds");

                        }

                        //check if it is stored in disc
                        else if(ViewStore.readView().containsKey(tableName)){

                            final long startTime = System.nanoTime();
                            storeView =  ViewStore.readView();
                            storeView.remove(tableName);     //remove from disc
                            ViewStore.saveView(storeView);   //write the updated info into disc
                            final long endTime = System.nanoTime();
                            long elapsedTime = endTime - startTime;
                            double seconds = (double)elapsedTime / 1000000000.0;
                            System.out.println("0 row(s) in "+new DecimalFormat("#.####").format(seconds)+" seconds");
                        }

                        else {
                            if (HBaseConnection.tableExist(tableName)) {

                                final long startTime = System.nanoTime();
                                // Instantiating HBaseAdmin class
                                HBaseAdmin admin = new HBaseAdmin(conf);

                                // disabling table named emp
                                admin.disableTable(tableName);

                                // Deleting emp
                                admin.deleteTable(tableName);
                                final long endTime = System.nanoTime();
                                long elapsedTime = endTime - startTime;
                                double seconds = (double)elapsedTime / 1000000000.0;
                                System.out.println("0 row(s) in "+new DecimalFormat("#.####").format(seconds)+" seconds");

                            } else
                                throw new HBaseConnection.TableNotFoundException("\nERROR: Table or View \""+ tableName +"\" does not exist!\n");
                        }

                    }
                    catch(HBaseConnection.TableNotFoundException ex)
                    {
                        System.out.println(ex.getMessage());
//                        System.out.println("\nAn Error Occurred, to view more details go to "+file);
                        logger.error("\"An error occurred!!\"", ex);
                    }

                    catch(Exception ex)
                    {
                        //ex.printStackTrace();
//                        System.out.println(ex.getMessage());
                        System.out.println("\nERROR: "+ex);
//                        System.out.println("\nAn Error Occurred, to view more details go to "+file);
                        System.out.println("\nTo view more details go to log file : "+file+"\n");
                        logger.error("\"An error occurred!!\"", ex);
//                        System.out.println("Wrong Input Format for 'drop' operation !!");
//                        System.out.println("Input Format for 'scan': drop \"tableName\"");
                    }

                }

                //list all tables and views
                if(operation.equalsIgnoreCase("list"))
                {
                    long rowCount = 0;

                    try {

                        final long startTime = System.nanoTime();
                        System.out.print("\n");
                        System.out.println("TABLE and VIRTUAL TABLE");
                        // Instantiating HBaseAdmin class
                        HBaseAdmin admin = new HBaseAdmin(conf);

                        // Getting all the list of tables using HBaseAdmin object
                        HTableDescriptor[] tableDescriptor = admin.listTables();

                        // printing all the table names.
                        for (int i=0; i<tableDescriptor.length;i++ ){
                            System.out.println(tableDescriptor[i].getNameAsString());
                            rowCount++;
                        }


                        //print all the views
                        storeView =  ViewStore.readView();
                        Iterator it = storeView.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry)it.next();
                            System.out.println(pair.getKey() +" (view on "+((HBaseViewDefinition)pair.getValue()).getBaseTable()+")");
                            System.out.println("*** " + ((HBaseViewDefinition)pair.getValue()).getNotaqlScript());
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
                        //ex.printStackTrace();
//                        System.out.println(ex.getMessage());
                        System.out.println("\nERROR: "+ex);
//                        System.out.println("\nAn Error Occurred, to view more details go to "+file);
                        System.out.println("\nTo view more details go to log file : "+file+"\n");
                        logger.error("\"An error occurred!!\"", ex);
                    }

                }

            }
        }
        catch(Exception ex)
        {
//            System.out.println(ex.getMessage());
            System.out.println("\nERROR: "+ex);
//            System.out.println("\nAn Error Occurred, to view more details go to "+file);
            System.out.println("\nTo view more details go to log file : "+file+"\n");
            logger.error("\"An error occurred!!\"", ex);
//            System.out.println("NameError: undefined local variable or method!");
        }

    }



    public static ArrayList<String> hbaseTableLists() throws IOException {

        HBaseAdmin hbadmin = new HBaseAdmin(conf);
        HTableDescriptor[] tabdesc = hbadmin.listTables();
        for(int i=0; i<tabdesc.length; i++) {
//            System.out.println("Table = " + new String(tabdesc [i].getName()));

            HbaseTableList.add("'" + new String(tabdesc[i].getName()) + "'");
        }

        return HbaseTableList;
    }


    public static void processInputString() throws IOException
    {
        logger.info("Welcome to the HBase Virtual Shell!!");
        // Instantiate a Date object
        Date date = new Date();

        // display time and date using toString()
        String str = String.format("Current Date/Time : %tc", date );
        //System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48

        System.out.println("****************Welcome to HBase Virtual Shell********************");
        System.out.println("INFO HBase Virtual Shell; Type 'help<RETURN>' for list of supported commands.\n" +
                "Type \"exit<RETURN>\" to leave the HBase Virtual Shell\n" +
                "Version 0.98.7-hadoop2, "+str);
        System.out.print("\n");



        try {
            ConsoleReader console = new ConsoleReader();
            console.setHistoryEnabled(true);
            console.setExpandEvents(false);
            console.flush();

            int lineNumber = 0;

            //TODO tab completer with double quote and rowkey

            StringsCompleter hbaseOps = new StringsCompleter("scan", "get");
            StringsCompleter hbaseTables = new StringsCompleter(hbaseTableLists());

            StringsCompleter hbaseDefine = new StringsCompleter("define");
            StringsCompleter hbaseVtabs = new StringsCompleter("'VirtualTable',");
            StringsCompleter hbaseTabs = new StringsCompleter(hbaseTableLists());


            ArgumentCompleter hbaseCompleter = new ArgumentCompleter(hbaseOps, hbaseTables);
            ArgumentCompleter hbaseCompleter1 = new ArgumentCompleter(hbaseDefine, hbaseVtabs, hbaseTabs);

            console.addCompleter(hbaseCompleter);
            console.addCompleter(hbaseCompleter1);



//        List<Completer> completors = new LinkedList<Completer>();
//        completors.add(
//                new AggregateCompleter(
//                        new ArgumentCompleter(new StringsCompleter("scan","get"), new StringsCompleter("'"), new StringsCompleter(hbaseTableLists())),
//                        new ArgumentCompleter(new StringsCompleter("scan","get"), new StringsCompleter("\""), new StringsCompleter(hbaseTableLists())),
//                        new ArgumentCompleter(new StringsCompleter("show"), new StringsCompleter("aaa", "access-expression", "access-lists", "accounting", "adjancey"), new NullCompleter()),
//                        new ArgumentCompleter(new StringsCompleter("show"), new StringsCompleter("ip"), new StringsCompleter("access-lists", "accounting", "admission", "aliases", "arp"), new NullCompleter()),
//                        new ArgumentCompleter(new StringsCompleter("show"), new StringsCompleter("ip"), new StringsCompleter("interface"), new StringsCompleter("ATM", "Async", "BVI"), new NullCompleter())
//                )
//        );
//        for (Completer c : completors) {
//            console.addCompleter(c);
//        }

        while (true)
        {
            console.flush();
            String command = "" ;
            if(lineNumber<10)
                command = console.readLine("hbase(vshell):00"+lineNumber+++"> ");
            if(lineNumber>=10 && lineNumber<100)
                command = console.readLine("hbase(vshell):0"+lineNumber+++"> ");
            if(lineNumber>=100 && lineNumber<1000)
                command = console.readLine("hbase(vshell):"+lineNumber+++"> ");
            if ("clear".equalsIgnoreCase(command))
            {
                console.clearScreen();
                continue;
            }

            if ("exit".equalsIgnoreCase(command))
            {
                System.out.println("\nHBase Virtual Shell Closed!!");
                break;
            }
            if ("help".equalsIgnoreCase(command))
            {
                System.out.println("HBase Virtual Shell, version 0.98.7-hadoop2.5, "+str+" \n"
                        + "Currently five types of operation supported: scan, get, define, drop, list.\n"
                        + "Type 'help \"command\"', (e.g. 'help \"get\"' -- "+ ANSI_RED+"the double quotes are necessary"+ANSI_RESET+") for help on a specific command.");

                continue;

            }

            if ("help \"scan\"".equalsIgnoreCase(command) || "help 'scan'".equalsIgnoreCase(command))
            {
                System.out.println("Scan a HBase table and Virtual table or View; pass table or view name. \n"
                        + "Example of scan : \n"
                        + "hbase vshell> scan \"tableName\" \n"
                        + "hbase vshell> scan \"Employee\" \n"
                        + "hbase vshell> scan \"ViewName\" \n\n"
                        + ANSI_RED +"Note: Only Double quotes are accepted!!" +ANSI_RESET +"\n"
                );
                continue;
            }

            if ("help \"get\"".equalsIgnoreCase(command) || "help 'get'".equalsIgnoreCase(command))
            {
                System.out.println("Get row or cell contents; pass table or View name, and row. \n"
                        + "Example of get : \n"
                        + "hbase vshell> get \"tableName\", \"rowKey\"\n"
                        + "hbase vshell> get \"ns1:t1\", \"r1\" \n"
                        + "hbase vshell> get \"Employee\", \"119736\" \n\n"
                        + ANSI_RED +"Note: Only Double quotes are accepted!!" +ANSI_RESET +"\n"
                        );
                continue;
            }

            if ("help \"define\"".equalsIgnoreCase(command) || "help 'define'".equalsIgnoreCase(command))
            {
                System.out.println("Define a Virtual table or View based on real HBase table; pass Virtual table or View name , hbase Table Name and notaQL Script. \n"
                        + "Example of define : \n"
                        + "hbase vshell> define \"virtualTableName\", \"hbaseTableName\", \"notaQL script\" \n"
                        + "hbase vshell> define \"virtualTableName\", \"hbaseTableName\", \"IN: IN.Born > '1950', OUT._r<-IN._r, OUT.salary <- IN.Salary;\" \n"
                        + "hbase vshell> define \"v\", \"EmployeeNotaQL\", \"IN: IN.Born > '1950', OUT._r<-IN._r, OUT.salary <- IN.Salary;\" \n\n"
                        + ANSI_RED +"Note: Only Double quotes are accepted!!" +ANSI_RESET +"\n"
                        );
                continue;
            }

            if ("help \"drop\"".equalsIgnoreCase(command) || "help 'drop'".equalsIgnoreCase(command))
            {
                System.out.println("Drop a table or view; pass table or view name. \n"
                                + "Example of drop : \n"
                                + "hbase vshell> drop \"tableName\" \n"
                                + "hbase vshell> drop \"viewName\" \n"
                                + "hbase vshell> drop \"Employee\" \n"
                                + "hbase vshell> drop \"View\" \n\n"
                                + ANSI_RED +"Note: Only Double quotes are accepted!!" +ANSI_RESET +"\n"
                );
                continue;
            }

            if ("help \"list\"".equalsIgnoreCase(command) || "help 'list'".equalsIgnoreCase(command))
            {
                System.out.println("List all HBase tables and Views. \n"
                                + "Example : \n"
                                + "hbase vshell> list \n"
                );
                continue;
            }

            if(!command.isEmpty()) {

                try {
                    // check the VShell syntax first
                    // if VShell syntax is correct then proess
                    IN(command);
//                    processInputString(parse(command));
                }
                catch(IllegalStateException ex)
                {
                    System.out.println(ex.getMessage());
                    logger.error("\"An error occurred!!\"", ex);
                }
                catch (Exception ex)
                {
//                    System.out.println(ex.getMessage());
                    System.out.println("\nERROR: "+ex);
                    System.out.println("\nWrong Input Format, to view more details go to log file : "+file);
                    logger.error("\"An error occurred!!\"", ex);
                }

            }

        }

        }
        catch (Exception ex)
        {
//            System.out.println(ex.getMessage());
            System.out.println("\nERROR: "+ex);
//            System.out.println("\nAn Error Occurred, to view more details go to "+file);
            System.out.println("\nTo view more details go to log file : "+file+"\n");
            logger.error("\"An error occurred!!\"", ex);
        }

    }


    //beginning
    public static void IN(String command) throws IOException {

        processInputString(parse(command));
    }


    private static VShellSyntaxBean parse(String inputString)
    {
        VShellLexer vShellLexer;
        VShellParser vShellParser;
        VShellParser.VshellContext vshellContext;
        VShellSyntaxBean vsb = null;

        vShellLexer = new VShellLexer(new ANTLRInputStream(inputString));

        vShellParser = new VShellParser(new CommonTokenStream(vShellLexer));
        vShellParser.removeErrorListeners();
        vShellParser.addErrorListener(VShellErrorListener.INSTANCE);
        //vshellContext = vShellParser.vshell();

        VShellParseVisitor visitor = new VShellParseVisitor(inputString);
        vsb = visitor.visitParse(vShellParser.parse());

//        System.out.println(" = " + answer.getInputTable());

            return vsb;

    }

    /**
     * This class handles basic error output and stops the VShellparser
     */

    public static class VShellErrorListener extends BaseErrorListener {
        public static VShellErrorListener INSTANCE = new VShellErrorListener();

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                int line, int charPositionInLine,
                                String msg, org.antlr.v4.runtime.RecognitionException e) {
            String token = "";
            if(offendingSymbol instanceof CommonToken) {
                token = VShellParser.tokenNames[((CommonToken) offendingSymbol).getType()];
            }

            throw new IllegalStateException("\nWrong Input Format due to " + msg + "; Offending symbol: " + offendingSymbol.getClass().toString() +": "+ token, e);
        }
    }





    public static class ThrowingErrorListener extends BaseErrorListener {

        public static final ThrowingErrorListener INSTANCE = new ThrowingErrorListener();

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e)
                throws ParseCancellationException {
            throw new ParseCancellationException("line " + line + ":" + charPositionInLine + " " + msg);
        }
    }

 }
