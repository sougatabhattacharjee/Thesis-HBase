package hbase.transformation.hbase;

import hbase.transformation.antlr4.VShellBaseVisitor;
import hbase.transformation.antlr4.VShellParser;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date February 27, 2015
 * @brief Application class to test
 */
public class VShellParseVisitor extends VShellBaseVisitor<VShellSyntaxBean> {

    private String inputString;

    private String operation;
    private String inputTable;
    private String virtualTable;
    private String notaqlScript;
    private String rowKey;


    private Map<String, String> vars = new HashMap<String, String>();

    public VShellParseVisitor(String inputString) {
        this.inputString = inputString;
    }

    VShellSyntaxBean vsh;

    @Override
    public VShellSyntaxBean visitScanOperation(@NotNull VShellParser.ScanOperationContext ctx) {

        operation = ctx.Scan().getText();
        inputTable = ctx.baseTable().getText();
        vsh = new VShellSyntaxBean(operation,inputTable);

        return vsh;
    }

    @Override
    public VShellSyntaxBean visitGetOperation(@NotNull VShellParser.GetOperationContext ctx) {

        operation = ctx.Get().getText();
        inputTable = ctx.baseTable().getText();
        rowKey = ctx.rowKey().getText();
        vsh = new VShellSyntaxBean(operation,inputTable,rowKey);

        return vsh;
    }

    @Override
    public VShellSyntaxBean visitDefineOperation(@NotNull VShellParser.DefineOperationContext ctx) {

        operation = ctx.Define().getText();
        virtualTable = ctx.virtualTable().getText();
        inputTable = ctx.baseTable().getText();
        notaqlScript = ctx.notaqlScript().getText();
        vsh = new VShellSyntaxBean(operation,virtualTable,inputTable,notaqlScript);

        return vsh;
    }

    @Override
    public VShellSyntaxBean visitDropOperation(@NotNull VShellParser.DropOperationContext ctx) {

        operation = ctx.Drop().getText();
        inputTable = ctx.baseTable().getText();
        vsh = new VShellSyntaxBean(operation,inputTable);

        return vsh;
    }

    @Override
    public VShellSyntaxBean visitListOperation(@NotNull VShellParser.ListOperationContext ctx) {

        operation = ctx.List().getText();
        vsh = new VShellSyntaxBean(operation,inputTable);

        return vsh;
    }

    @Override
    public VShellSyntaxBean visitParse(@NotNull VShellParser.ParseContext ctx) {
        return super.visit(ctx.vshell());
    }
}
