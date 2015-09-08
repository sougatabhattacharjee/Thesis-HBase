package hbase.transformation.notaql;

import hbase.transformation.connectionPool.HBaseConnection;
import hbase.transformation.notaql.hbase.InputTable;
import hbase.transformation.notaql.hbase.InputTable;
import hbase.transformation.notaql.antlr4.NotaQLLexer;
import hbase.transformation.notaql.antlr4.NotaQLParser;
import hbase.transformation.notaql.NotaQLTransformation;
import org.antlr.v4.runtime.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.vhbase.client.NotaQLTransform;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date February 19, 2015
 * @brief Application class to process notaql script
 */
public class NotaQLProcess implements java.io.Serializable{

    private static Configuration conf = null;
    public static final String DEFAULT_COLUMN_FAMILY = "Default";



    private NotaQLLexer lexer;
    private NotaQLParser parser;
    private NotaQLParser.NotaqlContext notaQLContext;


    private NotaQLParser.TransformationContext transformationContext;

    private InputTable in = null;
    private NotaQLTransformation notaQLTransformation;
    private NotaQLTransform notaQLTransform;


    static{

        conf = HBaseConnection.getConf();
    }

    /**
     * Constructs the NotaQL object for the specified expression.
     * @param notaqlScript    A NotaQL expression
     * //@param baseTable  input table
     */

    public NotaQLProcess(String notaqlScript) throws Exception {

        this.parse(notaqlScript);
        //this.process("EmployeeNotaQL");
    }

    public NotaQLProcess() {

    }

    /**
     * Parses the input string as a NotaQL expression and prepares everthing essential for transformation.
     * build lexer and parser with the appropriate error listener
    *  You need to call this before calling the process method
     * @param notaQL    A NotaQL expression
     */
    private void parse(String notaQL) {
        this.lexer = new NotaQLLexer(new ANTLRInputStream(notaQL));
        this.parser = new NotaQLParser(new CommonTokenStream(lexer));

        this.parser.addErrorListener(new NotaQLErrorListener());

        // parse notaql expression
        this.notaQLContext = this.parser.notaql();

//        transformationContext = notaQLContext.transformation();
//        notaQLTransformation = new NotaQLTransformation(transformationContext);
    }


    /**
     * Takes the input table id and executes the parsed notaql query.
     * @param inTableId     Identifier of the input table
     * @throws IOException  If something goes wrong in HBase
     */
    public void process(String inTableId) throws IOException {
        // get input table
        in = new InputTable(conf, inTableId);

        // satisfy the transformation
        notaQLTransformation.transformation(in);

    }

    public NotaQLLexer getLexer() {
        return lexer;
    }

    public void setLexer(NotaQLLexer lexer) {
        this.lexer = lexer;
    }

    public NotaQLParser getParser() {
        return parser;
    }

    public void setParser(NotaQLParser parser) {
        this.parser = parser;
    }

    public NotaQLParser.NotaqlContext getNotaQLContext() {
        return notaQLContext;
    }

    public void setNotaQLContext(NotaQLParser.NotaqlContext notaQLContext) {
        this.notaQLContext = notaQLContext;
    }

    public NotaQLParser.TransformationContext getTransformationContext() {
        return transformationContext;
    }

    public void setTransformationContext(NotaQLParser.TransformationContext transformationContext) {
        this.transformationContext = transformationContext;
    }

    public InputTable getIn() {
        return in;
    }

    public void setIn(InputTable in) {
        this.in = in;
    }

    public NotaQLTransformation getNotaQLTransformation() {
        return notaQLTransformation;
    }

    public void setNotaQLTransformation(NotaQLTransformation notaQLTransformation) {
        this.notaQLTransformation = notaQLTransformation;
    }


    public NotaQLTransform getNotaQLTransform() {
        return notaQLTransform;
    }

    public void setNotaQLTransform(NotaQLTransform notaQLTransform) {
        this.notaQLTransform = notaQLTransform;
    }

    /**
     * This class handles basic error output and stops the parser
     */
    private static class NotaQLErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
            String token = "";
            if(offendingSymbol instanceof CommonToken) {
                token = NotaQLParser.tokenNames[((CommonToken) offendingSymbol).getType()];
            }
            throw new IllegalStateException("failed to parse notaql grammar due to " + msg + "; Offending symbol: " + offendingSymbol.getClass().toString() +": "+ token, e);
        }
    }
}
