// Generated from hbase/transformation/notaql/antlr4/NotaQL.g4 by ANTLR 4.5
package hbase.transformation.notaql.antlr4;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class NotaQLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, SplitFunction=12, ListSplitFunction=13, SL_COMMENT=14, 
		ARROW=15, IN=16, OUT=17, ROW=18, COL=19, VAL=20, EQ=21, NEQ=22, LT=23, 
		LTEQ=24, GT=25, GTEQ=26, AND=27, OR=28, PLUS=29, MINUS=30, MULT=31, DIV=32, 
		Int=33, Float=34, String=35, AVG=36, COUNT=37, MAX=38, MIN=39, SUM=40, 
		IMPLODE=41, ColName=42, GenFunc=43, OPTWS=44, WS=45, NL=46;
	public static final int
		RULE_notaql = 0, RULE_transformation = 1, RULE_rowSpec = 2, RULE_cellSpec = 3, 
		RULE_aggFun = 4, RULE_input = 5, RULE_outputCol = 6, RULE_vData = 7, RULE_vDataFunction = 8, 
		RULE_vDataGenericFunction = 9, RULE_splittableVData = 10, RULE_predicate = 11, 
		RULE_inRowPredicate = 12, RULE_outRowPredicate = 13, RULE_colId = 14, 
		RULE_atom = 15;
	public static final String[] ruleNames = {
		"notaql", "transformation", "rowSpec", "cellSpec", "aggFun", "input", 
		"outputCol", "vData", "vDataFunction", "vDataGenericFunction", "splittableVData", 
		"predicate", "inRowPredicate", "outRowPredicate", "colId", "atom"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "','", "'.'", "'('", "')'", "':'", "'?('", "'$('", "'@'", 
		"'COL_COUNT('", "'!'", "'split'", "'splitList'", null, "'<-'", "'IN'", 
		"'OUT'", "'_r'", "'_c'", "'_v'", "'='", "'!='", "'<'", "'<='", "'>'", 
		"'>='", "'&&'", "'||'", "'+'", "'-'", "'*'", "'/'", null, null, null, 
		"'AVG'", "'COUNT'", "'MAX'", "'MIN'", "'SUM'", "'IMPLODE'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		"SplitFunction", "ListSplitFunction", "SL_COMMENT", "ARROW", "IN", "OUT", 
		"ROW", "COL", "VAL", "EQ", "NEQ", "LT", "LTEQ", "GT", "GTEQ", "AND", "OR", 
		"PLUS", "MINUS", "MULT", "DIV", "Int", "Float", "String", "AVG", "COUNT", 
		"MAX", "MIN", "SUM", "IMPLODE", "ColName", "GenFunc", "OPTWS", "WS", "NL"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "NotaQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public NotaQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class NotaqlContext extends ParserRuleContext {
		public TransformationContext transformation() {
			return getRuleContext(TransformationContext.class,0);
		}
		public TerminalNode EOF() { return getToken(NotaQLParser.EOF, 0); }
		public NotaqlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notaql; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterNotaql(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitNotaql(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitNotaql(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NotaqlContext notaql() throws RecognitionException {
		NotaqlContext _localctx = new NotaqlContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_notaql);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			transformation();
			setState(34);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(33);
				match(T__0);
				}
			}

			setState(36);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TransformationContext extends ParserRuleContext {
		public RowSpecContext rowSpec() {
			return getRuleContext(RowSpecContext.class,0);
		}
		public CellSpecContext cellSpec() {
			return getRuleContext(CellSpecContext.class,0);
		}
		public InRowPredicateContext inRowPredicate() {
			return getRuleContext(InRowPredicateContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(NotaQLParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(NotaQLParser.NL, i);
		}
		public OutRowPredicateContext outRowPredicate() {
			return getRuleContext(OutRowPredicateContext.class,0);
		}
		public TransformationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transformation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterTransformation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitTransformation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitTransformation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TransformationContext transformation() throws RecognitionException {
		TransformationContext _localctx = new TransformationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_transformation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			_la = _input.LA(1);
			if (_la==IN) {
				{
				setState(38);
				inRowPredicate();
				setState(39);
				match(T__1);
				setState(43);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(40);
					match(NL);
					}
					}
					setState(45);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(48);
			rowSpec();
			setState(49);
			match(T__1);
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(50);
				match(NL);
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(56);
			cellSpec();
			setState(65);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(57);
				match(T__1);
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(58);
					match(NL);
					}
					}
					setState(63);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(64);
				outRowPredicate();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RowSpecContext extends ParserRuleContext {
		public TerminalNode OUT() { return getToken(NotaQLParser.OUT, 0); }
		public TerminalNode ROW() { return getToken(NotaQLParser.ROW, 0); }
		public TerminalNode ARROW() { return getToken(NotaQLParser.ARROW, 0); }
		public SplittableVDataContext splittableVData() {
			return getRuleContext(SplittableVDataContext.class,0);
		}
		public RowSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rowSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterRowSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitRowSpec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitRowSpec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RowSpecContext rowSpec() throws RecognitionException {
		RowSpecContext _localctx = new RowSpecContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_rowSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(OUT);
			setState(68);
			match(T__2);
			setState(69);
			match(ROW);
			setState(70);
			match(ARROW);
			setState(71);
			splittableVData();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CellSpecContext extends ParserRuleContext {
		public OutputColContext outputCol() {
			return getRuleContext(OutputColContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(NotaQLParser.ARROW, 0); }
		public SplittableVDataContext splittableVData() {
			return getRuleContext(SplittableVDataContext.class,0);
		}
		public AggFunContext aggFun() {
			return getRuleContext(AggFunContext.class,0);
		}
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
		}
		public CellSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cellSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterCellSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitCellSpec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitCellSpec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CellSpecContext cellSpec() throws RecognitionException {
		CellSpecContext _localctx = new CellSpecContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_cellSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			outputCol();
			setState(74);
			match(ARROW);
			setState(90);
			switch (_input.LA(1)) {
			case T__3:
			case T__8:
			case T__9:
			case IN:
			case Int:
			case Float:
			case String:
			case ColName:
				{
				setState(75);
				splittableVData();
				}
				break;
			case AVG:
			case COUNT:
			case MAX:
			case MIN:
			case SUM:
			case IMPLODE:
				{
				setState(76);
				aggFun();
				setState(77);
				match(T__3);
				setState(79);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__8) | (1L << T__9) | (1L << IN) | (1L << Int) | (1L << Float) | (1L << String) | (1L << ColName))) != 0)) {
					{
					setState(78);
					splittableVData();
					}
				}

				setState(85);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(81);
					match(T__1);
					setState(82);
					atom();
					}
					}
					setState(87);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(88);
				match(T__4);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AggFunContext extends ParserRuleContext {
		public Token fun;
		public TerminalNode AVG() { return getToken(NotaQLParser.AVG, 0); }
		public TerminalNode COUNT() { return getToken(NotaQLParser.COUNT, 0); }
		public TerminalNode MAX() { return getToken(NotaQLParser.MAX, 0); }
		public TerminalNode MIN() { return getToken(NotaQLParser.MIN, 0); }
		public TerminalNode SUM() { return getToken(NotaQLParser.SUM, 0); }
		public TerminalNode IMPLODE() { return getToken(NotaQLParser.IMPLODE, 0); }
		public AggFunContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggFun; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterAggFun(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitAggFun(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitAggFun(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AggFunContext aggFun() throws RecognitionException {
		AggFunContext _localctx = new AggFunContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_aggFun);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			((AggFunContext)_localctx).fun = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AVG) | (1L << COUNT) | (1L << MAX) | (1L << MIN) | (1L << SUM) | (1L << IMPLODE))) != 0)) ) {
				((AggFunContext)_localctx).fun = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InputContext extends ParserRuleContext {
		public InputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_input; }
	 
		public InputContext() { }
		public void copyFrom(InputContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class RowInputContext extends InputContext {
		public TerminalNode IN() { return getToken(NotaQLParser.IN, 0); }
		public TerminalNode ROW() { return getToken(NotaQLParser.ROW, 0); }
		public RowInputContext(InputContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterRowInput(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitRowInput(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitRowInput(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CellInputContext extends InputContext {
		public Token colFamilyFilter;
		public Token source;
		public TerminalNode IN() { return getToken(NotaQLParser.IN, 0); }
		public TerminalNode COL() { return getToken(NotaQLParser.COL, 0); }
		public TerminalNode VAL() { return getToken(NotaQLParser.VAL, 0); }
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public TerminalNode ColName() { return getToken(NotaQLParser.ColName, 0); }
		public CellInputContext(InputContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterCellInput(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitCellInput(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitCellInput(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ColIdInputContext extends InputContext {
		public TerminalNode IN() { return getToken(NotaQLParser.IN, 0); }
		public ColIdContext colId() {
			return getRuleContext(ColIdContext.class,0);
		}
		public ColIdInputContext(InputContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterColIdInput(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitColIdInput(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitColIdInput(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InputContext input() throws RecognitionException {
		InputContext _localctx = new InputContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_input);
		int _la;
		try {
			setState(113);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				_localctx = new RowInputContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(94);
				match(IN);
				setState(95);
				match(T__2);
				setState(96);
				match(ROW);
				}
				break;
			case 2:
				_localctx = new CellInputContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(97);
				match(IN);
				setState(98);
				match(T__2);
				setState(101);
				_la = _input.LA(1);
				if (_la==ColName) {
					{
					setState(99);
					((CellInputContext)_localctx).colFamilyFilter = match(ColName);
					setState(100);
					match(T__5);
					}
				}

				setState(103);
				((CellInputContext)_localctx).source = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==COL || _la==VAL) ) {
					((CellInputContext)_localctx).source = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(108);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(104);
					match(T__6);
					setState(105);
					predicate(0);
					setState(106);
					match(T__4);
					}
					break;
				}
				}
				break;
			case 3:
				_localctx = new ColIdInputContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(110);
				match(IN);
				setState(111);
				match(T__2);
				setState(112);
				colId();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OutputColContext extends ParserRuleContext {
		public OutputColContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_outputCol; }
	 
		public OutputColContext() { }
		public void copyFrom(OutputColContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ResolvedColPathContext extends OutputColContext {
		public Token colFamily;
		public TerminalNode OUT() { return getToken(NotaQLParser.OUT, 0); }
		public SplittableVDataContext splittableVData() {
			return getRuleContext(SplittableVDataContext.class,0);
		}
		public TerminalNode ColName() { return getToken(NotaQLParser.ColName, 0); }
		public ResolvedColPathContext(OutputColContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterResolvedColPath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitResolvedColPath(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitResolvedColPath(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ColIdColPathContext extends OutputColContext {
		public TerminalNode OUT() { return getToken(NotaQLParser.OUT, 0); }
		public ColIdContext colId() {
			return getRuleContext(ColIdContext.class,0);
		}
		public ColIdColPathContext(OutputColContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterColIdColPath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitColIdColPath(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitColIdColPath(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OutputColContext outputCol() throws RecognitionException {
		OutputColContext _localctx = new OutputColContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_outputCol);
		int _la;
		try {
			setState(128);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				_localctx = new ColIdColPathContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(115);
				match(OUT);
				setState(116);
				match(T__2);
				setState(117);
				colId();
				}
				break;
			case 2:
				_localctx = new ResolvedColPathContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(118);
				match(OUT);
				setState(119);
				match(T__2);
				setState(122);
				_la = _input.LA(1);
				if (_la==ColName) {
					{
					setState(120);
					((ResolvedColPathContext)_localctx).colFamily = match(ColName);
					setState(121);
					match(T__5);
					}
				}

				setState(124);
				match(T__7);
				setState(125);
				splittableVData();
				setState(126);
				match(T__4);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VDataContext extends ParserRuleContext {
		public VDataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vData; }
	 
		public VDataContext() { }
		public void copyFrom(VDataContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CurrentCellVDataContext extends VDataContext {
		public CurrentCellVDataContext(VDataContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterCurrentCellVData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitCurrentCellVData(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitCurrentCellVData(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InputVDataContext extends VDataContext {
		public InputContext input() {
			return getRuleContext(InputContext.class,0);
		}
		public InputVDataContext(VDataContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterInputVData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitInputVData(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitInputVData(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GenericFunctionVDataContext extends VDataContext {
		public VDataGenericFunctionContext vDataGenericFunction() {
			return getRuleContext(VDataGenericFunctionContext.class,0);
		}
		public GenericFunctionVDataContext(VDataContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterGenericFunctionVData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitGenericFunctionVData(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitGenericFunctionVData(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BracedVDataContext extends VDataContext {
		public VDataContext vData() {
			return getRuleContext(VDataContext.class,0);
		}
		public BracedVDataContext(VDataContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterBracedVData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitBracedVData(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitBracedVData(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AtomVDataContext extends VDataContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public AtomVDataContext(VDataContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterAtomVData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitAtomVData(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitAtomVData(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunctionVDataContext extends VDataContext {
		public VDataFunctionContext vDataFunction() {
			return getRuleContext(VDataFunctionContext.class,0);
		}
		public FunctionVDataContext(VDataContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterFunctionVData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitFunctionVData(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitFunctionVData(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddedVDataContext extends VDataContext {
		public Token op;
		public List<VDataContext> vData() {
			return getRuleContexts(VDataContext.class);
		}
		public VDataContext vData(int i) {
			return getRuleContext(VDataContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(NotaQLParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(NotaQLParser.MINUS, 0); }
		public AddedVDataContext(VDataContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterAddedVData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitAddedVData(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitAddedVData(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultiplicatedVDataContext extends VDataContext {
		public Token op;
		public List<VDataContext> vData() {
			return getRuleContexts(VDataContext.class);
		}
		public VDataContext vData(int i) {
			return getRuleContext(VDataContext.class,i);
		}
		public TerminalNode MULT() { return getToken(NotaQLParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(NotaQLParser.DIV, 0); }
		public MultiplicatedVDataContext(VDataContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterMultiplicatedVData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitMultiplicatedVData(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitMultiplicatedVData(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VDataContext vData() throws RecognitionException {
		return vData(0);
	}

	private VDataContext vData(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		VDataContext _localctx = new VDataContext(_ctx, _parentState);
		VDataContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_vData, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			switch (_input.LA(1)) {
			case IN:
				{
				_localctx = new InputVDataContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(131);
				input();
				}
				break;
			case T__8:
				{
				_localctx = new CurrentCellVDataContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(132);
				match(T__8);
				}
				break;
			case Int:
			case Float:
			case String:
				{
				_localctx = new AtomVDataContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(133);
				atom();
				}
				break;
			case T__9:
				{
				_localctx = new FunctionVDataContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(134);
				vDataFunction();
				}
				break;
			case ColName:
				{
				_localctx = new GenericFunctionVDataContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(135);
				vDataGenericFunction();
				}
				break;
			case T__3:
				{
				_localctx = new BracedVDataContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(136);
				match(T__3);
				setState(137);
				vData(0);
				setState(138);
				match(T__4);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(150);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(148);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						_localctx = new MultiplicatedVDataContext(new VDataContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_vData);
						setState(142);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(143);
						((MultiplicatedVDataContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MULT || _la==DIV) ) {
							((MultiplicatedVDataContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(144);
						vData(3);
						}
						break;
					case 2:
						{
						_localctx = new AddedVDataContext(new VDataContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_vData);
						setState(145);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(146);
						((AddedVDataContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
							((AddedVDataContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(147);
						vData(2);
						}
						break;
					}
					} 
				}
				setState(152);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class VDataFunctionContext extends ParserRuleContext {
		public VDataFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vDataFunction; }
	 
		public VDataFunctionContext() { }
		public void copyFrom(VDataFunctionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ColCountFunctionContext extends VDataFunctionContext {
		public Token colFamily;
		public TerminalNode IN() { return getToken(NotaQLParser.IN, 0); }
		public TerminalNode ColName() { return getToken(NotaQLParser.ColName, 0); }
		public ColCountFunctionContext(VDataFunctionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterColCountFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitColCountFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitColCountFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VDataFunctionContext vDataFunction() throws RecognitionException {
		VDataFunctionContext _localctx = new VDataFunctionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_vDataFunction);
		int _la;
		try {
			_localctx = new ColCountFunctionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(T__9);
			setState(157);
			_la = _input.LA(1);
			if (_la==IN) {
				{
				setState(154);
				match(IN);
				setState(155);
				match(T__2);
				setState(156);
				((ColCountFunctionContext)_localctx).colFamily = match(ColName);
				}
			}

			setState(159);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VDataGenericFunctionContext extends ParserRuleContext {
		public VDataGenericFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vDataGenericFunction; }
	 
		public VDataGenericFunctionContext() { }
		public void copyFrom(VDataGenericFunctionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class GenericFunctionContext extends VDataGenericFunctionContext {
		public Token FunctionName;
		public TerminalNode ColName() { return getToken(NotaQLParser.ColName, 0); }
		public VDataContext vData() {
			return getRuleContext(VDataContext.class,0);
		}
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
		}
		public GenericFunctionContext(VDataGenericFunctionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterGenericFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitGenericFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitGenericFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VDataGenericFunctionContext vDataGenericFunction() throws RecognitionException {
		VDataGenericFunctionContext _localctx = new VDataGenericFunctionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_vDataGenericFunction);
		int _la;
		try {
			_localctx = new GenericFunctionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(161);
			((GenericFunctionContext)_localctx).FunctionName = match(ColName);
			}
			setState(174);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(162);
				match(T__3);
				setState(164);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__8) | (1L << T__9) | (1L << IN) | (1L << Int) | (1L << Float) | (1L << String) | (1L << ColName))) != 0)) {
					{
					setState(163);
					vData(0);
					}
				}

				setState(170);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(166);
					match(T__1);
					setState(167);
					atom();
					}
					}
					setState(172);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(173);
				match(T__4);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SplittableVDataContext extends ParserRuleContext {
		public SplittableVDataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_splittableVData; }
	 
		public SplittableVDataContext() { }
		public void copyFrom(SplittableVDataContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VDataSplittableVDataContext extends SplittableVDataContext {
		public VDataContext vData() {
			return getRuleContext(VDataContext.class,0);
		}
		public VDataSplittableVDataContext(SplittableVDataContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterVDataSplittableVData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitVDataSplittableVData(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitVDataSplittableVData(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SplitFunctionSplittableVDataContext extends SplittableVDataContext {
		public VDataContext baseVData;
		public Token splittingFunction;
		public List<VDataContext> vData() {
			return getRuleContexts(VDataContext.class);
		}
		public VDataContext vData(int i) {
			return getRuleContext(VDataContext.class,i);
		}
		public TerminalNode SplitFunction() { return getToken(NotaQLParser.SplitFunction, 0); }
		public TerminalNode ListSplitFunction() { return getToken(NotaQLParser.ListSplitFunction, 0); }
		public SplitFunctionSplittableVDataContext(SplittableVDataContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterSplitFunctionSplittableVData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitSplitFunctionSplittableVData(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitSplitFunctionSplittableVData(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SplittableVDataContext splittableVData() throws RecognitionException {
		SplittableVDataContext _localctx = new SplittableVDataContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_splittableVData);
		int _la;
		try {
			setState(189);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				_localctx = new VDataSplittableVDataContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(176);
				vData(0);
				}
				break;
			case 2:
				_localctx = new SplitFunctionSplittableVDataContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(177);
				((SplitFunctionSplittableVDataContext)_localctx).baseVData = vData(0);
				setState(178);
				match(T__2);
				setState(179);
				((SplitFunctionSplittableVDataContext)_localctx).splittingFunction = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==SplitFunction || _la==ListSplitFunction) ) {
					((SplitFunctionSplittableVDataContext)_localctx).splittingFunction = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(180);
				match(T__3);
				setState(184);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__8) | (1L << T__9) | (1L << IN) | (1L << Int) | (1L << Float) | (1L << String) | (1L << ColName))) != 0)) {
					{
					{
					setState(181);
					vData(0);
					}
					}
					setState(186);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(187);
				match(T__4);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredicateContext extends ParserRuleContext {
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
	 
		public PredicateContext() { }
		public void copyFrom(PredicateContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ColExistencePredicateContext extends PredicateContext {
		public ColIdContext colId() {
			return getRuleContext(ColIdContext.class,0);
		}
		public ColExistencePredicateContext(PredicateContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterColExistencePredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitColExistencePredicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitColExistencePredicate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RelationalPredicateContext extends PredicateContext {
		public Token op;
		public List<VDataContext> vData() {
			return getRuleContexts(VDataContext.class);
		}
		public VDataContext vData(int i) {
			return getRuleContext(VDataContext.class,i);
		}
		public TerminalNode LT() { return getToken(NotaQLParser.LT, 0); }
		public TerminalNode LTEQ() { return getToken(NotaQLParser.LTEQ, 0); }
		public TerminalNode GT() { return getToken(NotaQLParser.GT, 0); }
		public TerminalNode GTEQ() { return getToken(NotaQLParser.GTEQ, 0); }
		public RelationalPredicateContext(PredicateContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterRelationalPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitRelationalPredicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitRelationalPredicate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqualityPredicateContext extends PredicateContext {
		public Token op;
		public List<VDataContext> vData() {
			return getRuleContexts(VDataContext.class);
		}
		public VDataContext vData(int i) {
			return getRuleContext(VDataContext.class,i);
		}
		public TerminalNode EQ() { return getToken(NotaQLParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(NotaQLParser.NEQ, 0); }
		public EqualityPredicateContext(PredicateContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterEqualityPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitEqualityPredicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitEqualityPredicate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OrPredicateContext extends PredicateContext {
		public List<PredicateContext> predicate() {
			return getRuleContexts(PredicateContext.class);
		}
		public PredicateContext predicate(int i) {
			return getRuleContext(PredicateContext.class,i);
		}
		public TerminalNode OR() { return getToken(NotaQLParser.OR, 0); }
		public OrPredicateContext(PredicateContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterOrPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitOrPredicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitOrPredicate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NegatedPredicateContext extends PredicateContext {
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public NegatedPredicateContext(PredicateContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterNegatedPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitNegatedPredicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitNegatedPredicate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BracedPredicateContext extends PredicateContext {
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public BracedPredicateContext(PredicateContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterBracedPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitBracedPredicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitBracedPredicate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndPredicateContext extends PredicateContext {
		public List<PredicateContext> predicate() {
			return getRuleContexts(PredicateContext.class);
		}
		public PredicateContext predicate(int i) {
			return getRuleContext(PredicateContext.class,i);
		}
		public TerminalNode AND() { return getToken(NotaQLParser.AND, 0); }
		public AndPredicateContext(PredicateContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterAndPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitAndPredicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitAndPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		return predicate(0);
	}

	private PredicateContext predicate(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PredicateContext _localctx = new PredicateContext(_ctx, _parentState);
		PredicateContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_predicate, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				_localctx = new NegatedPredicateContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(192);
				match(T__10);
				setState(193);
				predicate(5);
				}
				break;
			case 2:
				{
				_localctx = new ColExistencePredicateContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(194);
				colId();
				}
				break;
			case 3:
				{
				_localctx = new BracedPredicateContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(195);
				match(T__3);
				setState(196);
				predicate(0);
				setState(197);
				match(T__4);
				}
				break;
			case 4:
				{
				_localctx = new RelationalPredicateContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(199);
				vData(0);
				setState(200);
				((RelationalPredicateContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LT) | (1L << LTEQ) | (1L << GT) | (1L << GTEQ))) != 0)) ) {
					((RelationalPredicateContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(201);
				vData(0);
				}
				break;
			case 5:
				{
				_localctx = new EqualityPredicateContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(203);
				vData(0);
				setState(204);
				((EqualityPredicateContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==EQ || _la==NEQ) ) {
					((EqualityPredicateContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(205);
				vData(0);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(217);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(215);
					switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
					case 1:
						{
						_localctx = new AndPredicateContext(new PredicateContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_predicate);
						setState(209);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(210);
						match(AND);
						setState(211);
						predicate(5);
						}
						break;
					case 2:
						{
						_localctx = new OrPredicateContext(new PredicateContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_predicate);
						setState(212);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(213);
						match(OR);
						setState(214);
						predicate(4);
						}
						break;
					}
					} 
				}
				setState(219);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class InRowPredicateContext extends ParserRuleContext {
		public TerminalNode IN() { return getToken(NotaQLParser.IN, 0); }
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public InRowPredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inRowPredicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterInRowPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitInRowPredicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitInRowPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InRowPredicateContext inRowPredicate() throws RecognitionException {
		InRowPredicateContext _localctx = new InRowPredicateContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_inRowPredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			match(IN);
			setState(221);
			match(T__5);
			setState(222);
			predicate(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OutRowPredicateContext extends ParserRuleContext {
		public TerminalNode OUT() { return getToken(NotaQLParser.OUT, 0); }
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public OutRowPredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_outRowPredicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterOutRowPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitOutRowPredicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitOutRowPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OutRowPredicateContext outRowPredicate() throws RecognitionException {
		OutRowPredicateContext _localctx = new OutRowPredicateContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_outRowPredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			match(OUT);
			setState(225);
			match(T__5);
			setState(226);
			predicate(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColIdContext extends ParserRuleContext {
		public ColIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_colId; }
	 
		public ColIdContext() { }
		public void copyFrom(ColIdContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class StringColIdContext extends ColIdContext {
		public Token colFamily;
		public Token colName;
		public List<TerminalNode> ColName() { return getTokens(NotaQLParser.ColName); }
		public TerminalNode ColName(int i) {
			return getToken(NotaQLParser.ColName, i);
		}
		public StringColIdContext(ColIdContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterStringColId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitStringColId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitStringColId(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GenericColIdContext extends ColIdContext {
		public AtomContext colFamily;
		public AtomContext colName;
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
		}
		public GenericColIdContext(ColIdContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterGenericColId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitGenericColId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitGenericColId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColIdContext colId() throws RecognitionException {
		ColIdContext _localctx = new ColIdContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_colId);
		try {
			int _alt;
			setState(250);
			switch (_input.LA(1)) {
			case ColName:
				_localctx = new StringColIdContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(230);
				switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
				case 1:
					{
					setState(228);
					((StringColIdContext)_localctx).colFamily = match(ColName);
					setState(229);
					match(T__5);
					}
					break;
				}
				setState(232);
				((StringColIdContext)_localctx).colName = match(ColName);
				setState(241);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(233);
						match(T__1);
						setState(236);
						switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
						case 1:
							{
							setState(234);
							((StringColIdContext)_localctx).colFamily = match(ColName);
							setState(235);
							match(T__5);
							}
							break;
						}
						setState(238);
						((StringColIdContext)_localctx).colName = match(ColName);
						}
						} 
					}
					setState(243);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
				}
				}
				break;
			case Int:
			case Float:
			case String:
				_localctx = new GenericColIdContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(247);
				switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
				case 1:
					{
					setState(244);
					((GenericColIdContext)_localctx).colFamily = atom();
					setState(245);
					match(T__5);
					}
					break;
				}
				setState(249);
				((GenericColIdContext)_localctx).colName = atom();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
	 
		public AtomContext() { }
		public void copyFrom(AtomContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class StringAtomContext extends AtomContext {
		public TerminalNode String() { return getToken(NotaQLParser.String, 0); }
		public StringAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterStringAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitStringAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitStringAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumberAtomContext extends AtomContext {
		public TerminalNode Int() { return getToken(NotaQLParser.Int, 0); }
		public TerminalNode Float() { return getToken(NotaQLParser.Float, 0); }
		public NumberAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).enterNumberAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NotaQLListener ) ((NotaQLListener)listener).exitNumberAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NotaQLVisitor ) return ((NotaQLVisitor<? extends T>)visitor).visitNumberAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_atom);
		int _la;
		try {
			setState(254);
			switch (_input.LA(1)) {
			case Int:
			case Float:
				_localctx = new NumberAtomContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(252);
				_la = _input.LA(1);
				if ( !(_la==Int || _la==Float) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case String:
				_localctx = new StringAtomContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(253);
				match(String);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 7:
			return vData_sempred((VDataContext)_localctx, predIndex);
		case 11:
			return predicate_sempred((PredicateContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean vData_sempred(VDataContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean predicate_sempred(PredicateContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 4);
		case 3:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\60\u0103\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2"+
		"\5\2%\n\2\3\2\3\2\3\3\3\3\3\3\7\3,\n\3\f\3\16\3/\13\3\5\3\61\n\3\3\3\3"+
		"\3\3\3\7\3\66\n\3\f\3\16\39\13\3\3\3\3\3\3\3\7\3>\n\3\f\3\16\3A\13\3\3"+
		"\3\5\3D\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5R\n\5\3"+
		"\5\3\5\7\5V\n\5\f\5\16\5Y\13\5\3\5\3\5\5\5]\n\5\3\6\3\6\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\5\7h\n\7\3\7\3\7\3\7\3\7\3\7\5\7o\n\7\3\7\3\7\3\7\5\7t"+
		"\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b}\n\b\3\b\3\b\3\b\3\b\5\b\u0083\n"+
		"\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u008f\n\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\7\t\u0097\n\t\f\t\16\t\u009a\13\t\3\n\3\n\3\n\3\n\5\n\u00a0"+
		"\n\n\3\n\3\n\3\13\3\13\3\13\5\13\u00a7\n\13\3\13\3\13\7\13\u00ab\n\13"+
		"\f\13\16\13\u00ae\13\13\3\13\5\13\u00b1\n\13\3\f\3\f\3\f\3\f\3\f\3\f\7"+
		"\f\u00b9\n\f\f\f\16\f\u00bc\13\f\3\f\3\f\5\f\u00c0\n\f\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00d2\n\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\7\r\u00da\n\r\f\r\16\r\u00dd\13\r\3\16\3\16\3\16\3\16"+
		"\3\17\3\17\3\17\3\17\3\20\3\20\5\20\u00e9\n\20\3\20\3\20\3\20\3\20\5\20"+
		"\u00ef\n\20\3\20\7\20\u00f2\n\20\f\20\16\20\u00f5\13\20\3\20\3\20\3\20"+
		"\5\20\u00fa\n\20\3\20\5\20\u00fd\n\20\3\21\3\21\5\21\u0101\n\21\3\21\2"+
		"\4\20\30\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2\n\3\2&+\3\2\25\26"+
		"\3\2!\"\3\2\37 \3\2\16\17\3\2\31\34\3\2\27\30\3\2#$\u011a\2\"\3\2\2\2"+
		"\4\60\3\2\2\2\6E\3\2\2\2\bK\3\2\2\2\n^\3\2\2\2\fs\3\2\2\2\16\u0082\3\2"+
		"\2\2\20\u008e\3\2\2\2\22\u009b\3\2\2\2\24\u00a3\3\2\2\2\26\u00bf\3\2\2"+
		"\2\30\u00d1\3\2\2\2\32\u00de\3\2\2\2\34\u00e2\3\2\2\2\36\u00fc\3\2\2\2"+
		" \u0100\3\2\2\2\"$\5\4\3\2#%\7\3\2\2$#\3\2\2\2$%\3\2\2\2%&\3\2\2\2&\'"+
		"\7\2\2\3\'\3\3\2\2\2()\5\32\16\2)-\7\4\2\2*,\7\60\2\2+*\3\2\2\2,/\3\2"+
		"\2\2-+\3\2\2\2-.\3\2\2\2.\61\3\2\2\2/-\3\2\2\2\60(\3\2\2\2\60\61\3\2\2"+
		"\2\61\62\3\2\2\2\62\63\5\6\4\2\63\67\7\4\2\2\64\66\7\60\2\2\65\64\3\2"+
		"\2\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28:\3\2\2\29\67\3\2\2\2:C\5\b"+
		"\5\2;?\7\4\2\2<>\7\60\2\2=<\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@B\3"+
		"\2\2\2A?\3\2\2\2BD\5\34\17\2C;\3\2\2\2CD\3\2\2\2D\5\3\2\2\2EF\7\23\2\2"+
		"FG\7\5\2\2GH\7\24\2\2HI\7\21\2\2IJ\5\26\f\2J\7\3\2\2\2KL\5\16\b\2L\\\7"+
		"\21\2\2M]\5\26\f\2NO\5\n\6\2OQ\7\6\2\2PR\5\26\f\2QP\3\2\2\2QR\3\2\2\2"+
		"RW\3\2\2\2ST\7\4\2\2TV\5 \21\2US\3\2\2\2VY\3\2\2\2WU\3\2\2\2WX\3\2\2\2"+
		"XZ\3\2\2\2YW\3\2\2\2Z[\7\7\2\2[]\3\2\2\2\\M\3\2\2\2\\N\3\2\2\2]\t\3\2"+
		"\2\2^_\t\2\2\2_\13\3\2\2\2`a\7\22\2\2ab\7\5\2\2bt\7\24\2\2cd\7\22\2\2"+
		"dg\7\5\2\2ef\7,\2\2fh\7\b\2\2ge\3\2\2\2gh\3\2\2\2hi\3\2\2\2in\t\3\2\2"+
		"jk\7\t\2\2kl\5\30\r\2lm\7\7\2\2mo\3\2\2\2nj\3\2\2\2no\3\2\2\2ot\3\2\2"+
		"\2pq\7\22\2\2qr\7\5\2\2rt\5\36\20\2s`\3\2\2\2sc\3\2\2\2sp\3\2\2\2t\r\3"+
		"\2\2\2uv\7\23\2\2vw\7\5\2\2w\u0083\5\36\20\2xy\7\23\2\2y|\7\5\2\2z{\7"+
		",\2\2{}\7\b\2\2|z\3\2\2\2|}\3\2\2\2}~\3\2\2\2~\177\7\n\2\2\177\u0080\5"+
		"\26\f\2\u0080\u0081\7\7\2\2\u0081\u0083\3\2\2\2\u0082u\3\2\2\2\u0082x"+
		"\3\2\2\2\u0083\17\3\2\2\2\u0084\u0085\b\t\1\2\u0085\u008f\5\f\7\2\u0086"+
		"\u008f\7\13\2\2\u0087\u008f\5 \21\2\u0088\u008f\5\22\n\2\u0089\u008f\5"+
		"\24\13\2\u008a\u008b\7\6\2\2\u008b\u008c\5\20\t\2\u008c\u008d\7\7\2\2"+
		"\u008d\u008f\3\2\2\2\u008e\u0084\3\2\2\2\u008e\u0086\3\2\2\2\u008e\u0087"+
		"\3\2\2\2\u008e\u0088\3\2\2\2\u008e\u0089\3\2\2\2\u008e\u008a\3\2\2\2\u008f"+
		"\u0098\3\2\2\2\u0090\u0091\f\4\2\2\u0091\u0092\t\4\2\2\u0092\u0097\5\20"+
		"\t\5\u0093\u0094\f\3\2\2\u0094\u0095\t\5\2\2\u0095\u0097\5\20\t\4\u0096"+
		"\u0090\3\2\2\2\u0096\u0093\3\2\2\2\u0097\u009a\3\2\2\2\u0098\u0096\3\2"+
		"\2\2\u0098\u0099\3\2\2\2\u0099\21\3\2\2\2\u009a\u0098\3\2\2\2\u009b\u009f"+
		"\7\f\2\2\u009c\u009d\7\22\2\2\u009d\u009e\7\5\2\2\u009e\u00a0\7,\2\2\u009f"+
		"\u009c\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2\7\7"+
		"\2\2\u00a2\23\3\2\2\2\u00a3\u00b0\7,\2\2\u00a4\u00a6\7\6\2\2\u00a5\u00a7"+
		"\5\20\t\2\u00a6\u00a5\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00ac\3\2\2\2"+
		"\u00a8\u00a9\7\4\2\2\u00a9\u00ab\5 \21\2\u00aa\u00a8\3\2\2\2\u00ab\u00ae"+
		"\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00af\3\2\2\2\u00ae"+
		"\u00ac\3\2\2\2\u00af\u00b1\7\7\2\2\u00b0\u00a4\3\2\2\2\u00b0\u00b1\3\2"+
		"\2\2\u00b1\25\3\2\2\2\u00b2\u00c0\5\20\t\2\u00b3\u00b4\5\20\t\2\u00b4"+
		"\u00b5\7\5\2\2\u00b5\u00b6\t\6\2\2\u00b6\u00ba\7\6\2\2\u00b7\u00b9\5\20"+
		"\t\2\u00b8\u00b7\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba"+
		"\u00bb\3\2\2\2\u00bb\u00bd\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd\u00be\7\7"+
		"\2\2\u00be\u00c0\3\2\2\2\u00bf\u00b2\3\2\2\2\u00bf\u00b3\3\2\2\2\u00c0"+
		"\27\3\2\2\2\u00c1\u00c2\b\r\1\2\u00c2\u00c3\7\r\2\2\u00c3\u00d2\5\30\r"+
		"\7\u00c4\u00d2\5\36\20\2\u00c5\u00c6\7\6\2\2\u00c6\u00c7\5\30\r\2\u00c7"+
		"\u00c8\7\7\2\2\u00c8\u00d2\3\2\2\2\u00c9\u00ca\5\20\t\2\u00ca\u00cb\t"+
		"\7\2\2\u00cb\u00cc\5\20\t\2\u00cc\u00d2\3\2\2\2\u00cd\u00ce\5\20\t\2\u00ce"+
		"\u00cf\t\b\2\2\u00cf\u00d0\5\20\t\2\u00d0\u00d2\3\2\2\2\u00d1\u00c1\3"+
		"\2\2\2\u00d1\u00c4\3\2\2\2\u00d1\u00c5\3\2\2\2\u00d1\u00c9\3\2\2\2\u00d1"+
		"\u00cd\3\2\2\2\u00d2\u00db\3\2\2\2\u00d3\u00d4\f\6\2\2\u00d4\u00d5\7\35"+
		"\2\2\u00d5\u00da\5\30\r\7\u00d6\u00d7\f\5\2\2\u00d7\u00d8\7\36\2\2\u00d8"+
		"\u00da\5\30\r\6\u00d9\u00d3\3\2\2\2\u00d9\u00d6\3\2\2\2\u00da\u00dd\3"+
		"\2\2\2\u00db\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\31\3\2\2\2\u00dd"+
		"\u00db\3\2\2\2\u00de\u00df\7\22\2\2\u00df\u00e0\7\b\2\2\u00e0\u00e1\5"+
		"\30\r\2\u00e1\33\3\2\2\2\u00e2\u00e3\7\23\2\2\u00e3\u00e4\7\b\2\2\u00e4"+
		"\u00e5\5\30\r\2\u00e5\35\3\2\2\2\u00e6\u00e7\7,\2\2\u00e7\u00e9\7\b\2"+
		"\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00f3"+
		"\7,\2\2\u00eb\u00ee\7\4\2\2\u00ec\u00ed\7,\2\2\u00ed\u00ef\7\b\2\2\u00ee"+
		"\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f2\7,"+
		"\2\2\u00f1\u00eb\3\2\2\2\u00f2\u00f5\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3"+
		"\u00f4\3\2\2\2\u00f4\u00fd\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f6\u00f7\5 "+
		"\21\2\u00f7\u00f8\7\b\2\2\u00f8\u00fa\3\2\2\2\u00f9\u00f6\3\2\2\2\u00f9"+
		"\u00fa\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fd\5 \21\2\u00fc\u00e8\3\2"+
		"\2\2\u00fc\u00f9\3\2\2\2\u00fd\37\3\2\2\2\u00fe\u0101\t\t\2\2\u00ff\u0101"+
		"\7%\2\2\u0100\u00fe\3\2\2\2\u0100\u00ff\3\2\2\2\u0101!\3\2\2\2\"$-\60"+
		"\67?CQW\\gns|\u0082\u008e\u0096\u0098\u009f\u00a6\u00ac\u00b0\u00ba\u00bf"+
		"\u00d1\u00d9\u00db\u00e8\u00ee\u00f3\u00f9\u00fc\u0100";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}