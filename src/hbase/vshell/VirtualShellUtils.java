package hbase.vshell;

import hbase.transformation.antlr4.VShellLexer;
import hbase.transformation.antlr4.VShellParser;
import hbase.transformation.hbase.VShellParseVisitor;
import hbase.transformation.hbase.VShellSyntaxBean;
import jline.console.ConsoleReader;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.StringsCompleter;
import hbase.vshell.VirtualShellLogger;
import org.antlr.runtime.CommonToken;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Recognizer;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Date;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date May 11, 2015
 * @brief Application class to test
 */
public class VirtualShellUtils {

    private static final Logger logger = Logger.getLogger(VirtualShellUtils.class.getName());

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    static String stringDate = "";


    public static void BeforeShellProcessing() {
        logger.info("Welcome to the HBase Virtual Shell!!");
        // Instantiate a Date object
        Date date = new Date();

        // display time and date using toString()
        stringDate = String.format("Current Date/Time : %tc", date);

        System.out.println("****************Welcome to HBase Virtual Shell********************");
        System.out.println("INFO HBase Virtual Shell; Type 'help<RETURN>' for list of supported commands.\n" +
                "Type \"exit<RETURN>\" to leave the HBase Virtual Shell\n" +
                "Version 0.98.7-hadoop2, " + stringDate);
        System.out.print("\n");
    }


    public static void ShellProcessing() throws IOException {
        BeforeShellProcessing();
        try {
            ConsoleReader console = new ConsoleReader();
            console.setHistoryEnabled(true);
            console.setExpandEvents(false);
            console.flush();


            StringsCompleter hbaseOps = new StringsCompleter("drop", "list", "scan", "get");
            StringsCompleter hbaseTables = new StringsCompleter(VirtualShell.hbaseTableLists());

            StringsCompleter hbaseDefine = new StringsCompleter("define");
            StringsCompleter hbaseVtabs = new StringsCompleter("\"VirtualTable\",");
            StringsCompleter hbaseTabs = new StringsCompleter(VirtualShell.hbaseTableLists());

            StringsCompleter hbaseHelp = new StringsCompleter("help", "exit", "clear");
            StringsCompleter hbaseHelpComs = new StringsCompleter("\"define\"", "\"scan\"", "\"get\"", "\"drop\"", "\"list\"");


            ArgumentCompleter hbaseCompleter = new ArgumentCompleter(hbaseOps, hbaseTables);
            ArgumentCompleter hbaseCompleter1 = new ArgumentCompleter(hbaseDefine, hbaseVtabs, hbaseTabs);
            ArgumentCompleter hbaseCompleter2 = new ArgumentCompleter(hbaseHelp, hbaseHelpComs);

            console.addCompleter(hbaseCompleter);
            console.addCompleter(hbaseCompleter1);
            console.addCompleter(hbaseCompleter2);



            int lineNumber = 0;

            String count = "";


            while (true) {
                console.flush();
                String command = "";
                if (lineNumber < 10) count = "00"+ lineNumber++ +"";
//                    command = console.readLine("hbase(vshell):00" + lineNumber++ + "> ");
                if (lineNumber >= 10 && lineNumber < 100) count = "0"+ lineNumber++ +"";
//                    command = console.readLine("hbase(vshell):0" + lineNumber++ + "> ");
                if (lineNumber >= 100 && lineNumber < 1000) count = "" +lineNumber++ +"";
//                    command = console.readLine("hbase(vshell):" + lineNumber++ + "> ");

                command = console.readLine("hbase(vshell):" + count + "> ");


                if ("clear".equalsIgnoreCase(command) || "clear ".equalsIgnoreCase(command)) {
                    console.clearScreen();
                    continue;
                }

                if ("exit".equalsIgnoreCase(command) || "exit ".equalsIgnoreCase(command)) {
                    System.out.println("\nHBase Virtual Shell Closed!!");
                    break;
                }
                if ("help".equalsIgnoreCase(command) || "help ".equalsIgnoreCase(command)) {
                    System.out.println("HBase Virtual Shell, version 0.98.7-hadoop2.5, " + stringDate + " \n"
                            + "Currently five types of operation supported: scan, get, define, drop, list.\n"
                            + "Type 'help \"command\"', (e.g. 'help \"get\"' -- " + ANSI_RED + "the double quotes are necessary" + ANSI_RESET + ") for help on a specific command.");

                    continue;

                }

                if ("help \"scan\"".equalsIgnoreCase(command) || "help \"scan\" ".equalsIgnoreCase(command) || "help 'scan'".equalsIgnoreCase(command)) {
                    System.out.println("Scan a HBase table and Virtual table or View; pass table or view name. \n"
                                    + "Example of scan : \n"
                                    + "hbase vshell> scan \"tableName\" \n"
                                    + "hbase vshell> scan \"Employee\" \n"
                                    + "hbase vshell> scan \"ViewName\" \n\n"
                                    + ANSI_RED + "Note: Only Double quotes are accepted!!" + ANSI_RESET + "\n"
                    );
                    continue;
                }

                if ("help \"get\"".equalsIgnoreCase(command) || "help \"get\" ".equalsIgnoreCase(command) || "help 'get'".equalsIgnoreCase(command)) {
                    System.out.println("Get row or cell contents; pass table or View name, and row. \n"
                                    + "Example of get : \n"
                                    + "hbase vshell> get \"tableName\", \"rowKey\"\n"
                                    + "hbase vshell> get \"ns1:t1\", \"r1\" \n"
                                    + "hbase vshell> get \"Employee\", \"119736\" \n\n"
                                    + ANSI_RED + "Note: Only Double quotes are accepted!!" + ANSI_RESET + "\n"
                    );
                    continue;
                }

                    if ("help \"define\"".equalsIgnoreCase(command) || "help \"define\" ".equalsIgnoreCase(command) || "help 'define'".equalsIgnoreCase(command)) {
                    System.out.println("Define a Virtual table or View based on real HBase table; pass Virtual table or View name , hbase Table Name and notaQL Script. \n"
                                    + "Example of define : \n"
                                    + "hbase vshell> define \"virtualTableName\", \"hbaseTableName\", \"notaQL script\" \n"
                                    + "hbase vshell> define \"virtualTableName\", \"hbaseTableName\", \"IN: IN.Born > '1950', OUT._r<-IN._r, OUT.salary <- IN.Salary;\" \n"
                                    + "hbase vshell> define \"v\", \"EmployeeNotaQL\", \"IN: IN.Born > '1950', OUT._r<-IN._r, OUT.salary <- IN.Salary;\" \n\n"
                                    + ANSI_RED + "Note: Only Double quotes are accepted!!" + ANSI_RESET + "\n"
                    );
                    continue;
                }

                if ("help \"drop\"".equalsIgnoreCase(command) || "help \"drop\" ".equalsIgnoreCase(command)  || "help 'drop'".equalsIgnoreCase(command)) {
                    System.out.println("Drop a table or view; pass table or view name. \n"
                                    + "Example of drop : \n"
                                    + "hbase vshell> drop \"tableName\" \n"
                                    + "hbase vshell> drop \"viewName\" \n"
                                    + "hbase vshell> drop \"Employee\" \n"
                                    + "hbase vshell> drop \"View\" \n\n"
                                    + ANSI_RED + "Note: Only Double quotes are accepted!!" + ANSI_RESET + "\n"
                    );
                    continue;
                }

                if ("help \"list\"".equalsIgnoreCase(command) || "help \"list\" ".equalsIgnoreCase(command) || "help 'list'".equalsIgnoreCase(command)) {
                    System.out.println("List all HBase tables and Views. \n"
                                    + "Example : \n"
                                    + "hbase vshell> list \n"
                    );
                    continue;
                }

                if (!command.isEmpty()) {

                    try {
                        // check the VShell syntax first, if VShell syntax is correct then proess

                    VirtualShell.processInputString(parse(command));
                    } catch (IllegalStateException ex) {
                        System.out.println(ex.getMessage());
                        logger.error("\"An error occurred!!\"", ex);
                    } catch (Exception ex) {
//                    System.out.println(ex.getMessage());
                        System.out.println("\nERROR: " + ex);
                        System.out.println("\nWrong Input Format, to view more details go to log file : " + VirtualShellLogger.log);
                        logger.error("\"An error occurred!!\"", ex);
                    }

                }

            }

        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
            System.out.println("\nERROR: " + ex);
//            System.out.println("\nAn Error Occurred, to view more details go to "+file);
            System.out.println("\nTo view more details go to log file : " + VirtualShellLogger.log + "\n");
            logger.error("\"An error occurred!!\"", ex);
        }

    }


    /**
     *
     * @param inputString
     * @return
     */
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

    //test
    public static void test(String command) throws IOException {
        VirtualShell.processInputString(parse(command));
    }
}