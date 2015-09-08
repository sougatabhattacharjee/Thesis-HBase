// Generated from hbase/transformation/antlr4/VShell.g4 by ANTLR 4.5
package hbase.transformation.antlr4;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link VShellParser}.
 */
public interface VShellListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link VShellParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(VShellParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link VShellParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(VShellParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code scanOperation}
	 * labeled alternative in {@link VShellParser#vshell}.
	 * @param ctx the parse tree
	 */
	void enterScanOperation(VShellParser.ScanOperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code scanOperation}
	 * labeled alternative in {@link VShellParser#vshell}.
	 * @param ctx the parse tree
	 */
	void exitScanOperation(VShellParser.ScanOperationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code getOperation}
	 * labeled alternative in {@link VShellParser#vshell}.
	 * @param ctx the parse tree
	 */
	void enterGetOperation(VShellParser.GetOperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code getOperation}
	 * labeled alternative in {@link VShellParser#vshell}.
	 * @param ctx the parse tree
	 */
	void exitGetOperation(VShellParser.GetOperationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code defineOperation}
	 * labeled alternative in {@link VShellParser#vshell}.
	 * @param ctx the parse tree
	 */
	void enterDefineOperation(VShellParser.DefineOperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code defineOperation}
	 * labeled alternative in {@link VShellParser#vshell}.
	 * @param ctx the parse tree
	 */
	void exitDefineOperation(VShellParser.DefineOperationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dropOperation}
	 * labeled alternative in {@link VShellParser#vshell}.
	 * @param ctx the parse tree
	 */
	void enterDropOperation(VShellParser.DropOperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dropOperation}
	 * labeled alternative in {@link VShellParser#vshell}.
	 * @param ctx the parse tree
	 */
	void exitDropOperation(VShellParser.DropOperationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code listOperation}
	 * labeled alternative in {@link VShellParser#vshell}.
	 * @param ctx the parse tree
	 */
	void enterListOperation(VShellParser.ListOperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code listOperation}
	 * labeled alternative in {@link VShellParser#vshell}.
	 * @param ctx the parse tree
	 */
	void exitListOperation(VShellParser.ListOperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link VShellParser#baseTable}.
	 * @param ctx the parse tree
	 */
	void enterBaseTable(VShellParser.BaseTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link VShellParser#baseTable}.
	 * @param ctx the parse tree
	 */
	void exitBaseTable(VShellParser.BaseTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link VShellParser#virtualTable}.
	 * @param ctx the parse tree
	 */
	void enterVirtualTable(VShellParser.VirtualTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link VShellParser#virtualTable}.
	 * @param ctx the parse tree
	 */
	void exitVirtualTable(VShellParser.VirtualTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link VShellParser#rowKey}.
	 * @param ctx the parse tree
	 */
	void enterRowKey(VShellParser.RowKeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link VShellParser#rowKey}.
	 * @param ctx the parse tree
	 */
	void exitRowKey(VShellParser.RowKeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link VShellParser#notaqlScript}.
	 * @param ctx the parse tree
	 */
	void enterNotaqlScript(VShellParser.NotaqlScriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link VShellParser#notaqlScript}.
	 * @param ctx the parse tree
	 */
	void exitNotaqlScript(VShellParser.NotaqlScriptContext ctx);
	/**
	 * Enter a parse tree produced by {@link VShellParser#space}.
	 * @param ctx the parse tree
	 */
	void enterSpace(VShellParser.SpaceContext ctx);
	/**
	 * Exit a parse tree produced by {@link VShellParser#space}.
	 * @param ctx the parse tree
	 */
	void exitSpace(VShellParser.SpaceContext ctx);
}