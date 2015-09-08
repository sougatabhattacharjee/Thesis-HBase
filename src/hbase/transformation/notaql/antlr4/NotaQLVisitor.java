// Generated from hbase/transformation/notaql/antlr4/NotaQL.g4 by ANTLR 4.5
package hbase.transformation.notaql.antlr4;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link NotaQLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface NotaQLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link NotaQLParser#notaql}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotaql(NotaQLParser.NotaqlContext ctx);
	/**
	 * Visit a parse tree produced by {@link NotaQLParser#transformation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransformation(NotaQLParser.TransformationContext ctx);
	/**
	 * Visit a parse tree produced by {@link NotaQLParser#rowSpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRowSpec(NotaQLParser.RowSpecContext ctx);
	/**
	 * Visit a parse tree produced by {@link NotaQLParser#cellSpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCellSpec(NotaQLParser.CellSpecContext ctx);
	/**
	 * Visit a parse tree produced by {@link NotaQLParser#aggFun}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggFun(NotaQLParser.AggFunContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rowInput}
	 * labeled alternative in {@link NotaQLParser#input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRowInput(NotaQLParser.RowInputContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cellInput}
	 * labeled alternative in {@link NotaQLParser#input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCellInput(NotaQLParser.CellInputContext ctx);
	/**
	 * Visit a parse tree produced by the {@code colIdInput}
	 * labeled alternative in {@link NotaQLParser#input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColIdInput(NotaQLParser.ColIdInputContext ctx);
	/**
	 * Visit a parse tree produced by the {@code colIdColPath}
	 * labeled alternative in {@link NotaQLParser#outputCol}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColIdColPath(NotaQLParser.ColIdColPathContext ctx);
	/**
	 * Visit a parse tree produced by the {@code resolvedColPath}
	 * labeled alternative in {@link NotaQLParser#outputCol}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResolvedColPath(NotaQLParser.ResolvedColPathContext ctx);
	/**
	 * Visit a parse tree produced by the {@code currentCellVData}
	 * labeled alternative in {@link NotaQLParser#vData}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCurrentCellVData(NotaQLParser.CurrentCellVDataContext ctx);
	/**
	 * Visit a parse tree produced by the {@code inputVData}
	 * labeled alternative in {@link NotaQLParser#vData}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInputVData(NotaQLParser.InputVDataContext ctx);
	/**
	 * Visit a parse tree produced by the {@code genericFunctionVData}
	 * labeled alternative in {@link NotaQLParser#vData}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGenericFunctionVData(NotaQLParser.GenericFunctionVDataContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bracedVData}
	 * labeled alternative in {@link NotaQLParser#vData}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBracedVData(NotaQLParser.BracedVDataContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomVData}
	 * labeled alternative in {@link NotaQLParser#vData}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomVData(NotaQLParser.AtomVDataContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionVData}
	 * labeled alternative in {@link NotaQLParser#vData}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionVData(NotaQLParser.FunctionVDataContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addedVData}
	 * labeled alternative in {@link NotaQLParser#vData}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddedVData(NotaQLParser.AddedVDataContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multiplicatedVData}
	 * labeled alternative in {@link NotaQLParser#vData}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicatedVData(NotaQLParser.MultiplicatedVDataContext ctx);
	/**
	 * Visit a parse tree produced by the {@code colCountFunction}
	 * labeled alternative in {@link NotaQLParser#vDataFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColCountFunction(NotaQLParser.ColCountFunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code genericFunction}
	 * labeled alternative in {@link NotaQLParser#vDataGenericFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGenericFunction(NotaQLParser.GenericFunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code vDataSplittableVData}
	 * labeled alternative in {@link NotaQLParser#splittableVData}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVDataSplittableVData(NotaQLParser.VDataSplittableVDataContext ctx);
	/**
	 * Visit a parse tree produced by the {@code splitFunctionSplittableVData}
	 * labeled alternative in {@link NotaQLParser#splittableVData}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSplitFunctionSplittableVData(NotaQLParser.SplitFunctionSplittableVDataContext ctx);
	/**
	 * Visit a parse tree produced by the {@code colExistencePredicate}
	 * labeled alternative in {@link NotaQLParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColExistencePredicate(NotaQLParser.ColExistencePredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code relationalPredicate}
	 * labeled alternative in {@link NotaQLParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalPredicate(NotaQLParser.RelationalPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equalityPredicate}
	 * labeled alternative in {@link NotaQLParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityPredicate(NotaQLParser.EqualityPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code orPredicate}
	 * labeled alternative in {@link NotaQLParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrPredicate(NotaQLParser.OrPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negatedPredicate}
	 * labeled alternative in {@link NotaQLParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegatedPredicate(NotaQLParser.NegatedPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bracedPredicate}
	 * labeled alternative in {@link NotaQLParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBracedPredicate(NotaQLParser.BracedPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andPredicate}
	 * labeled alternative in {@link NotaQLParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndPredicate(NotaQLParser.AndPredicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link NotaQLParser#inRowPredicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInRowPredicate(NotaQLParser.InRowPredicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link NotaQLParser#outRowPredicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutRowPredicate(NotaQLParser.OutRowPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringColId}
	 * labeled alternative in {@link NotaQLParser#colId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringColId(NotaQLParser.StringColIdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code genericColId}
	 * labeled alternative in {@link NotaQLParser#colId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGenericColId(NotaQLParser.GenericColIdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numberAtom}
	 * labeled alternative in {@link NotaQLParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberAtom(NotaQLParser.NumberAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringAtom}
	 * labeled alternative in {@link NotaQLParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringAtom(NotaQLParser.StringAtomContext ctx);
}