// Generated from hbase/transformation/antlr4/VShell.g4 by ANTLR 4.5
package hbase.transformation.antlr4;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link VShellParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface VShellVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link VShellParser#parse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParse(VShellParser.ParseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code scanOperation}
	 * labeled alternative in {@link VShellParser#vshell}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScanOperation(VShellParser.ScanOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code getOperation}
	 * labeled alternative in {@link VShellParser#vshell}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetOperation(VShellParser.GetOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code defineOperation}
	 * labeled alternative in {@link VShellParser#vshell}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefineOperation(VShellParser.DefineOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dropOperation}
	 * labeled alternative in {@link VShellParser#vshell}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropOperation(VShellParser.DropOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code listOperation}
	 * labeled alternative in {@link VShellParser#vshell}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListOperation(VShellParser.ListOperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link VShellParser#baseTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseTable(VShellParser.BaseTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link VShellParser#virtualTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVirtualTable(VShellParser.VirtualTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link VShellParser#rowKey}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRowKey(VShellParser.RowKeyContext ctx);
	/**
	 * Visit a parse tree produced by {@link VShellParser#notaqlScript}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotaqlScript(VShellParser.NotaqlScriptContext ctx);
	/**
	 * Visit a parse tree produced by {@link VShellParser#space}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpace(VShellParser.SpaceContext ctx);
}