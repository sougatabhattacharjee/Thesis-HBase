// Generated from hbase/transformation/notaql/antlr4/NotaQL.g4 by ANTLR 4.5
package hbase.transformation.notaql.antlr4;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link NotaQLParser}.
 */
public interface NotaQLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link NotaQLParser#notaql}.
	 * @param ctx the parse tree
	 */
	void enterNotaql(NotaQLParser.NotaqlContext ctx);
	/**
	 * Exit a parse tree produced by {@link NotaQLParser#notaql}.
	 * @param ctx the parse tree
	 */
	void exitNotaql(NotaQLParser.NotaqlContext ctx);
	/**
	 * Enter a parse tree produced by {@link NotaQLParser#transformation}.
	 * @param ctx the parse tree
	 */
	void enterTransformation(NotaQLParser.TransformationContext ctx);
	/**
	 * Exit a parse tree produced by {@link NotaQLParser#transformation}.
	 * @param ctx the parse tree
	 */
	void exitTransformation(NotaQLParser.TransformationContext ctx);
	/**
	 * Enter a parse tree produced by {@link NotaQLParser#rowSpec}.
	 * @param ctx the parse tree
	 */
	void enterRowSpec(NotaQLParser.RowSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link NotaQLParser#rowSpec}.
	 * @param ctx the parse tree
	 */
	void exitRowSpec(NotaQLParser.RowSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link NotaQLParser#cellSpec}.
	 * @param ctx the parse tree
	 */
	void enterCellSpec(NotaQLParser.CellSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link NotaQLParser#cellSpec}.
	 * @param ctx the parse tree
	 */
	void exitCellSpec(NotaQLParser.CellSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link NotaQLParser#aggFun}.
	 * @param ctx the parse tree
	 */
	void enterAggFun(NotaQLParser.AggFunContext ctx);
	/**
	 * Exit a parse tree produced by {@link NotaQLParser#aggFun}.
	 * @param ctx the parse tree
	 */
	void exitAggFun(NotaQLParser.AggFunContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rowInput}
	 * labeled alternative in {@link NotaQLParser#input}.
	 * @param ctx the parse tree
	 */
	void enterRowInput(NotaQLParser.RowInputContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rowInput}
	 * labeled alternative in {@link NotaQLParser#input}.
	 * @param ctx the parse tree
	 */
	void exitRowInput(NotaQLParser.RowInputContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cellInput}
	 * labeled alternative in {@link NotaQLParser#input}.
	 * @param ctx the parse tree
	 */
	void enterCellInput(NotaQLParser.CellInputContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cellInput}
	 * labeled alternative in {@link NotaQLParser#input}.
	 * @param ctx the parse tree
	 */
	void exitCellInput(NotaQLParser.CellInputContext ctx);
	/**
	 * Enter a parse tree produced by the {@code colIdInput}
	 * labeled alternative in {@link NotaQLParser#input}.
	 * @param ctx the parse tree
	 */
	void enterColIdInput(NotaQLParser.ColIdInputContext ctx);
	/**
	 * Exit a parse tree produced by the {@code colIdInput}
	 * labeled alternative in {@link NotaQLParser#input}.
	 * @param ctx the parse tree
	 */
	void exitColIdInput(NotaQLParser.ColIdInputContext ctx);
	/**
	 * Enter a parse tree produced by the {@code colIdColPath}
	 * labeled alternative in {@link NotaQLParser#outputCol}.
	 * @param ctx the parse tree
	 */
	void enterColIdColPath(NotaQLParser.ColIdColPathContext ctx);
	/**
	 * Exit a parse tree produced by the {@code colIdColPath}
	 * labeled alternative in {@link NotaQLParser#outputCol}.
	 * @param ctx the parse tree
	 */
	void exitColIdColPath(NotaQLParser.ColIdColPathContext ctx);
	/**
	 * Enter a parse tree produced by the {@code resolvedColPath}
	 * labeled alternative in {@link NotaQLParser#outputCol}.
	 * @param ctx the parse tree
	 */
	void enterResolvedColPath(NotaQLParser.ResolvedColPathContext ctx);
	/**
	 * Exit a parse tree produced by the {@code resolvedColPath}
	 * labeled alternative in {@link NotaQLParser#outputCol}.
	 * @param ctx the parse tree
	 */
	void exitResolvedColPath(NotaQLParser.ResolvedColPathContext ctx);
	/**
	 * Enter a parse tree produced by the {@code currentCellVData}
	 * labeled alternative in {@link NotaQLParser#vData}.
	 * @param ctx the parse tree
	 */
	void enterCurrentCellVData(NotaQLParser.CurrentCellVDataContext ctx);
	/**
	 * Exit a parse tree produced by the {@code currentCellVData}
	 * labeled alternative in {@link NotaQLParser#vData}.
	 * @param ctx the parse tree
	 */
	void exitCurrentCellVData(NotaQLParser.CurrentCellVDataContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inputVData}
	 * labeled alternative in {@link NotaQLParser#vData}.
	 * @param ctx the parse tree
	 */
	void enterInputVData(NotaQLParser.InputVDataContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inputVData}
	 * labeled alternative in {@link NotaQLParser#vData}.
	 * @param ctx the parse tree
	 */
	void exitInputVData(NotaQLParser.InputVDataContext ctx);
	/**
	 * Enter a parse tree produced by the {@code genericFunctionVData}
	 * labeled alternative in {@link NotaQLParser#vData}.
	 * @param ctx the parse tree
	 */
	void enterGenericFunctionVData(NotaQLParser.GenericFunctionVDataContext ctx);
	/**
	 * Exit a parse tree produced by the {@code genericFunctionVData}
	 * labeled alternative in {@link NotaQLParser#vData}.
	 * @param ctx the parse tree
	 */
	void exitGenericFunctionVData(NotaQLParser.GenericFunctionVDataContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bracedVData}
	 * labeled alternative in {@link NotaQLParser#vData}.
	 * @param ctx the parse tree
	 */
	void enterBracedVData(NotaQLParser.BracedVDataContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bracedVData}
	 * labeled alternative in {@link NotaQLParser#vData}.
	 * @param ctx the parse tree
	 */
	void exitBracedVData(NotaQLParser.BracedVDataContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomVData}
	 * labeled alternative in {@link NotaQLParser#vData}.
	 * @param ctx the parse tree
	 */
	void enterAtomVData(NotaQLParser.AtomVDataContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomVData}
	 * labeled alternative in {@link NotaQLParser#vData}.
	 * @param ctx the parse tree
	 */
	void exitAtomVData(NotaQLParser.AtomVDataContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionVData}
	 * labeled alternative in {@link NotaQLParser#vData}.
	 * @param ctx the parse tree
	 */
	void enterFunctionVData(NotaQLParser.FunctionVDataContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionVData}
	 * labeled alternative in {@link NotaQLParser#vData}.
	 * @param ctx the parse tree
	 */
	void exitFunctionVData(NotaQLParser.FunctionVDataContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addedVData}
	 * labeled alternative in {@link NotaQLParser#vData}.
	 * @param ctx the parse tree
	 */
	void enterAddedVData(NotaQLParser.AddedVDataContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addedVData}
	 * labeled alternative in {@link NotaQLParser#vData}.
	 * @param ctx the parse tree
	 */
	void exitAddedVData(NotaQLParser.AddedVDataContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multiplicatedVData}
	 * labeled alternative in {@link NotaQLParser#vData}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicatedVData(NotaQLParser.MultiplicatedVDataContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multiplicatedVData}
	 * labeled alternative in {@link NotaQLParser#vData}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicatedVData(NotaQLParser.MultiplicatedVDataContext ctx);
	/**
	 * Enter a parse tree produced by the {@code colCountFunction}
	 * labeled alternative in {@link NotaQLParser#vDataFunction}.
	 * @param ctx the parse tree
	 */
	void enterColCountFunction(NotaQLParser.ColCountFunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code colCountFunction}
	 * labeled alternative in {@link NotaQLParser#vDataFunction}.
	 * @param ctx the parse tree
	 */
	void exitColCountFunction(NotaQLParser.ColCountFunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code genericFunction}
	 * labeled alternative in {@link NotaQLParser#vDataGenericFunction}.
	 * @param ctx the parse tree
	 */
	void enterGenericFunction(NotaQLParser.GenericFunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code genericFunction}
	 * labeled alternative in {@link NotaQLParser#vDataGenericFunction}.
	 * @param ctx the parse tree
	 */
	void exitGenericFunction(NotaQLParser.GenericFunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code vDataSplittableVData}
	 * labeled alternative in {@link NotaQLParser#splittableVData}.
	 * @param ctx the parse tree
	 */
	void enterVDataSplittableVData(NotaQLParser.VDataSplittableVDataContext ctx);
	/**
	 * Exit a parse tree produced by the {@code vDataSplittableVData}
	 * labeled alternative in {@link NotaQLParser#splittableVData}.
	 * @param ctx the parse tree
	 */
	void exitVDataSplittableVData(NotaQLParser.VDataSplittableVDataContext ctx);
	/**
	 * Enter a parse tree produced by the {@code splitFunctionSplittableVData}
	 * labeled alternative in {@link NotaQLParser#splittableVData}.
	 * @param ctx the parse tree
	 */
	void enterSplitFunctionSplittableVData(NotaQLParser.SplitFunctionSplittableVDataContext ctx);
	/**
	 * Exit a parse tree produced by the {@code splitFunctionSplittableVData}
	 * labeled alternative in {@link NotaQLParser#splittableVData}.
	 * @param ctx the parse tree
	 */
	void exitSplitFunctionSplittableVData(NotaQLParser.SplitFunctionSplittableVDataContext ctx);
	/**
	 * Enter a parse tree produced by the {@code colExistencePredicate}
	 * labeled alternative in {@link NotaQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterColExistencePredicate(NotaQLParser.ColExistencePredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code colExistencePredicate}
	 * labeled alternative in {@link NotaQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitColExistencePredicate(NotaQLParser.ColExistencePredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code relationalPredicate}
	 * labeled alternative in {@link NotaQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterRelationalPredicate(NotaQLParser.RelationalPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code relationalPredicate}
	 * labeled alternative in {@link NotaQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitRelationalPredicate(NotaQLParser.RelationalPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equalityPredicate}
	 * labeled alternative in {@link NotaQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterEqualityPredicate(NotaQLParser.EqualityPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equalityPredicate}
	 * labeled alternative in {@link NotaQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitEqualityPredicate(NotaQLParser.EqualityPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orPredicate}
	 * labeled alternative in {@link NotaQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterOrPredicate(NotaQLParser.OrPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orPredicate}
	 * labeled alternative in {@link NotaQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitOrPredicate(NotaQLParser.OrPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code negatedPredicate}
	 * labeled alternative in {@link NotaQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterNegatedPredicate(NotaQLParser.NegatedPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code negatedPredicate}
	 * labeled alternative in {@link NotaQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitNegatedPredicate(NotaQLParser.NegatedPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bracedPredicate}
	 * labeled alternative in {@link NotaQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterBracedPredicate(NotaQLParser.BracedPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bracedPredicate}
	 * labeled alternative in {@link NotaQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitBracedPredicate(NotaQLParser.BracedPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andPredicate}
	 * labeled alternative in {@link NotaQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterAndPredicate(NotaQLParser.AndPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andPredicate}
	 * labeled alternative in {@link NotaQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitAndPredicate(NotaQLParser.AndPredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link NotaQLParser#inRowPredicate}.
	 * @param ctx the parse tree
	 */
	void enterInRowPredicate(NotaQLParser.InRowPredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link NotaQLParser#inRowPredicate}.
	 * @param ctx the parse tree
	 */
	void exitInRowPredicate(NotaQLParser.InRowPredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link NotaQLParser#outRowPredicate}.
	 * @param ctx the parse tree
	 */
	void enterOutRowPredicate(NotaQLParser.OutRowPredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link NotaQLParser#outRowPredicate}.
	 * @param ctx the parse tree
	 */
	void exitOutRowPredicate(NotaQLParser.OutRowPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringColId}
	 * labeled alternative in {@link NotaQLParser#colId}.
	 * @param ctx the parse tree
	 */
	void enterStringColId(NotaQLParser.StringColIdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringColId}
	 * labeled alternative in {@link NotaQLParser#colId}.
	 * @param ctx the parse tree
	 */
	void exitStringColId(NotaQLParser.StringColIdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code genericColId}
	 * labeled alternative in {@link NotaQLParser#colId}.
	 * @param ctx the parse tree
	 */
	void enterGenericColId(NotaQLParser.GenericColIdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code genericColId}
	 * labeled alternative in {@link NotaQLParser#colId}.
	 * @param ctx the parse tree
	 */
	void exitGenericColId(NotaQLParser.GenericColIdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numberAtom}
	 * labeled alternative in {@link NotaQLParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterNumberAtom(NotaQLParser.NumberAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numberAtom}
	 * labeled alternative in {@link NotaQLParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitNumberAtom(NotaQLParser.NumberAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringAtom}
	 * labeled alternative in {@link NotaQLParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterStringAtom(NotaQLParser.StringAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringAtom}
	 * labeled alternative in {@link NotaQLParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitStringAtom(NotaQLParser.StringAtomContext ctx);
}