// Generated from hbase/transformation/antlr4/VShell.g4 by ANTLR 4.5
package hbase.transformation.antlr4;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class VShellParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, Scan=6, Get=7, Define=8, Drop=9, 
		List=10, StringValue=11, WS=12;
	public static final int
		RULE_parse = 0, RULE_vshell = 1, RULE_baseTable = 2, RULE_virtualTable = 3, 
		RULE_rowKey = 4, RULE_notaqlScript = 5, RULE_space = 6;
	public static final String[] ruleNames = {
		"parse", "vshell", "baseTable", "virtualTable", "rowKey", "notaqlScript", 
		"space"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "','", "' '", "'\t'", "'\r'", "'scan'", "'get'", "'define'", 
		"'drop'", "'list'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, "Scan", "Get", "Define", "Drop", "List", 
		"StringValue", "WS"
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
	public String getGrammarFileName() { return "VShell.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public VShellParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ParseContext extends ParserRuleContext {
		public VshellContext vshell() {
			return getRuleContext(VshellContext.class,0);
		}
		public TerminalNode EOF() { return getToken(VShellParser.EOF, 0); }
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VShellListener ) ((VShellListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VShellListener ) ((VShellListener)listener).exitParse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VShellVisitor ) return ((VShellVisitor<? extends T>)visitor).visitParse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
			vshell();
			setState(15);
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

	public static class VshellContext extends ParserRuleContext {
		public VshellContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vshell; }
	 
		public VshellContext() { }
		public void copyFrom(VshellContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ScanOperationContext extends VshellContext {
		public TerminalNode Scan() { return getToken(VShellParser.Scan, 0); }
		public BaseTableContext baseTable() {
			return getRuleContext(BaseTableContext.class,0);
		}
		public List<SpaceContext> space() {
			return getRuleContexts(SpaceContext.class);
		}
		public SpaceContext space(int i) {
			return getRuleContext(SpaceContext.class,i);
		}
		public ScanOperationContext(VshellContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VShellListener ) ((VShellListener)listener).enterScanOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VShellListener ) ((VShellListener)listener).exitScanOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VShellVisitor ) return ((VShellVisitor<? extends T>)visitor).visitScanOperation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GetOperationContext extends VshellContext {
		public TerminalNode Get() { return getToken(VShellParser.Get, 0); }
		public BaseTableContext baseTable() {
			return getRuleContext(BaseTableContext.class,0);
		}
		public RowKeyContext rowKey() {
			return getRuleContext(RowKeyContext.class,0);
		}
		public List<SpaceContext> space() {
			return getRuleContexts(SpaceContext.class);
		}
		public SpaceContext space(int i) {
			return getRuleContext(SpaceContext.class,i);
		}
		public GetOperationContext(VshellContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VShellListener ) ((VShellListener)listener).enterGetOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VShellListener ) ((VShellListener)listener).exitGetOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VShellVisitor ) return ((VShellVisitor<? extends T>)visitor).visitGetOperation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DropOperationContext extends VshellContext {
		public TerminalNode Drop() { return getToken(VShellParser.Drop, 0); }
		public BaseTableContext baseTable() {
			return getRuleContext(BaseTableContext.class,0);
		}
		public List<SpaceContext> space() {
			return getRuleContexts(SpaceContext.class);
		}
		public SpaceContext space(int i) {
			return getRuleContext(SpaceContext.class,i);
		}
		public DropOperationContext(VshellContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VShellListener ) ((VShellListener)listener).enterDropOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VShellListener ) ((VShellListener)listener).exitDropOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VShellVisitor ) return ((VShellVisitor<? extends T>)visitor).visitDropOperation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ListOperationContext extends VshellContext {
		public TerminalNode List() { return getToken(VShellParser.List, 0); }
		public List<SpaceContext> space() {
			return getRuleContexts(SpaceContext.class);
		}
		public SpaceContext space(int i) {
			return getRuleContext(SpaceContext.class,i);
		}
		public ListOperationContext(VshellContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VShellListener ) ((VShellListener)listener).enterListOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VShellListener ) ((VShellListener)listener).exitListOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VShellVisitor ) return ((VShellVisitor<? extends T>)visitor).visitListOperation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DefineOperationContext extends VshellContext {
		public TerminalNode Define() { return getToken(VShellParser.Define, 0); }
		public VirtualTableContext virtualTable() {
			return getRuleContext(VirtualTableContext.class,0);
		}
		public BaseTableContext baseTable() {
			return getRuleContext(BaseTableContext.class,0);
		}
		public NotaqlScriptContext notaqlScript() {
			return getRuleContext(NotaqlScriptContext.class,0);
		}
		public List<SpaceContext> space() {
			return getRuleContexts(SpaceContext.class);
		}
		public SpaceContext space(int i) {
			return getRuleContext(SpaceContext.class,i);
		}
		public DefineOperationContext(VshellContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VShellListener ) ((VShellListener)listener).enterDefineOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VShellListener ) ((VShellListener)listener).exitDefineOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VShellVisitor ) return ((VShellVisitor<? extends T>)visitor).visitDefineOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VshellContext vshell() throws RecognitionException {
		VshellContext _localctx = new VshellContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_vshell);
		int _la;
		try {
			setState(96);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				_localctx = new ScanOperationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(18);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0)) {
					{
					setState(17);
					space();
					}
				}

				setState(20);
				match(Scan);
				{
				setState(21);
				space();
				}
				setState(22);
				baseTable();
				setState(24);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(23);
					match(T__0);
					}
				}

				setState(27);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0)) {
					{
					setState(26);
					space();
					}
				}

				}
				break;
			case 2:
				_localctx = new GetOperationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(30);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0)) {
					{
					setState(29);
					space();
					}
				}

				setState(32);
				match(Get);
				{
				setState(33);
				space();
				}
				setState(34);
				baseTable();
				setState(35);
				match(T__1);
				setState(39);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0)) {
					{
					{
					setState(36);
					space();
					}
					}
					setState(41);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(42);
				rowKey();
				setState(44);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(43);
					match(T__0);
					}
				}

				setState(47);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0)) {
					{
					setState(46);
					space();
					}
				}

				}
				break;
			case 3:
				_localctx = new DefineOperationContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(50);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0)) {
					{
					setState(49);
					space();
					}
				}

				setState(52);
				match(Define);
				{
				setState(53);
				space();
				}
				setState(54);
				virtualTable();
				setState(55);
				match(T__1);
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0)) {
					{
					{
					setState(56);
					space();
					}
					}
					setState(61);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(62);
				baseTable();
				setState(63);
				match(T__1);
				setState(67);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0)) {
					{
					{
					setState(64);
					space();
					}
					}
					setState(69);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(70);
				notaqlScript();
				setState(72);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(71);
					match(T__0);
					}
				}

				setState(75);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0)) {
					{
					setState(74);
					space();
					}
				}

				}
				break;
			case 4:
				_localctx = new DropOperationContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(78);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0)) {
					{
					setState(77);
					space();
					}
				}

				setState(80);
				match(Drop);
				{
				setState(81);
				space();
				}
				setState(82);
				baseTable();
				setState(84);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(83);
					match(T__0);
					}
				}

				setState(87);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0)) {
					{
					setState(86);
					space();
					}
				}

				}
				break;
			case 5:
				_localctx = new ListOperationContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(90);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0)) {
					{
					setState(89);
					space();
					}
				}

				setState(92);
				match(List);
				setState(94);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0)) {
					{
					setState(93);
					space();
					}
				}

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

	public static class BaseTableContext extends ParserRuleContext {
		public TerminalNode StringValue() { return getToken(VShellParser.StringValue, 0); }
		public BaseTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseTable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VShellListener ) ((VShellListener)listener).enterBaseTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VShellListener ) ((VShellListener)listener).exitBaseTable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VShellVisitor ) return ((VShellVisitor<? extends T>)visitor).visitBaseTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BaseTableContext baseTable() throws RecognitionException {
		BaseTableContext _localctx = new BaseTableContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_baseTable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(StringValue);
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

	public static class VirtualTableContext extends ParserRuleContext {
		public TerminalNode StringValue() { return getToken(VShellParser.StringValue, 0); }
		public VirtualTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_virtualTable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VShellListener ) ((VShellListener)listener).enterVirtualTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VShellListener ) ((VShellListener)listener).exitVirtualTable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VShellVisitor ) return ((VShellVisitor<? extends T>)visitor).visitVirtualTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VirtualTableContext virtualTable() throws RecognitionException {
		VirtualTableContext _localctx = new VirtualTableContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_virtualTable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(StringValue);
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

	public static class RowKeyContext extends ParserRuleContext {
		public TerminalNode StringValue() { return getToken(VShellParser.StringValue, 0); }
		public RowKeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rowKey; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VShellListener ) ((VShellListener)listener).enterRowKey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VShellListener ) ((VShellListener)listener).exitRowKey(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VShellVisitor ) return ((VShellVisitor<? extends T>)visitor).visitRowKey(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RowKeyContext rowKey() throws RecognitionException {
		RowKeyContext _localctx = new RowKeyContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_rowKey);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(StringValue);
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

	public static class NotaqlScriptContext extends ParserRuleContext {
		public TerminalNode StringValue() { return getToken(VShellParser.StringValue, 0); }
		public NotaqlScriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notaqlScript; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VShellListener ) ((VShellListener)listener).enterNotaqlScript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VShellListener ) ((VShellListener)listener).exitNotaqlScript(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VShellVisitor ) return ((VShellVisitor<? extends T>)visitor).visitNotaqlScript(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NotaqlScriptContext notaqlScript() throws RecognitionException {
		NotaqlScriptContext _localctx = new NotaqlScriptContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_notaqlScript);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(StringValue);
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

	public static class SpaceContext extends ParserRuleContext {
		public SpaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_space; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VShellListener ) ((VShellListener)listener).enterSpace(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VShellListener ) ((VShellListener)listener).exitSpace(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VShellVisitor ) return ((VShellVisitor<? extends T>)visitor).visitSpace(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpaceContext space() throws RecognitionException {
		SpaceContext _localctx = new SpaceContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_space);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(107); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(106);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0)) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(109); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\16r\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\3\5\3\25\n\3"+
		"\3\3\3\3\3\3\3\3\5\3\33\n\3\3\3\5\3\36\n\3\3\3\5\3!\n\3\3\3\3\3\3\3\3"+
		"\3\3\3\7\3(\n\3\f\3\16\3+\13\3\3\3\3\3\5\3/\n\3\3\3\5\3\62\n\3\3\3\5\3"+
		"\65\n\3\3\3\3\3\3\3\3\3\3\3\7\3<\n\3\f\3\16\3?\13\3\3\3\3\3\3\3\7\3D\n"+
		"\3\f\3\16\3G\13\3\3\3\3\3\5\3K\n\3\3\3\5\3N\n\3\3\3\5\3Q\n\3\3\3\3\3\3"+
		"\3\3\3\5\3W\n\3\3\3\5\3Z\n\3\3\3\5\3]\n\3\3\3\3\3\5\3a\n\3\5\3c\n\3\3"+
		"\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\6\bn\n\b\r\b\16\bo\3\b\2\2\t\2\4\6"+
		"\b\n\f\16\2\3\3\2\5\7\u0080\2\20\3\2\2\2\4b\3\2\2\2\6d\3\2\2\2\bf\3\2"+
		"\2\2\nh\3\2\2\2\fj\3\2\2\2\16m\3\2\2\2\20\21\5\4\3\2\21\22\7\2\2\3\22"+
		"\3\3\2\2\2\23\25\5\16\b\2\24\23\3\2\2\2\24\25\3\2\2\2\25\26\3\2\2\2\26"+
		"\27\7\b\2\2\27\30\5\16\b\2\30\32\5\6\4\2\31\33\7\3\2\2\32\31\3\2\2\2\32"+
		"\33\3\2\2\2\33\35\3\2\2\2\34\36\5\16\b\2\35\34\3\2\2\2\35\36\3\2\2\2\36"+
		"c\3\2\2\2\37!\5\16\b\2 \37\3\2\2\2 !\3\2\2\2!\"\3\2\2\2\"#\7\t\2\2#$\5"+
		"\16\b\2$%\5\6\4\2%)\7\4\2\2&(\5\16\b\2\'&\3\2\2\2(+\3\2\2\2)\'\3\2\2\2"+
		")*\3\2\2\2*,\3\2\2\2+)\3\2\2\2,.\5\n\6\2-/\7\3\2\2.-\3\2\2\2./\3\2\2\2"+
		"/\61\3\2\2\2\60\62\5\16\b\2\61\60\3\2\2\2\61\62\3\2\2\2\62c\3\2\2\2\63"+
		"\65\5\16\b\2\64\63\3\2\2\2\64\65\3\2\2\2\65\66\3\2\2\2\66\67\7\n\2\2\67"+
		"8\5\16\b\289\5\b\5\29=\7\4\2\2:<\5\16\b\2;:\3\2\2\2<?\3\2\2\2=;\3\2\2"+
		"\2=>\3\2\2\2>@\3\2\2\2?=\3\2\2\2@A\5\6\4\2AE\7\4\2\2BD\5\16\b\2CB\3\2"+
		"\2\2DG\3\2\2\2EC\3\2\2\2EF\3\2\2\2FH\3\2\2\2GE\3\2\2\2HJ\5\f\7\2IK\7\3"+
		"\2\2JI\3\2\2\2JK\3\2\2\2KM\3\2\2\2LN\5\16\b\2ML\3\2\2\2MN\3\2\2\2Nc\3"+
		"\2\2\2OQ\5\16\b\2PO\3\2\2\2PQ\3\2\2\2QR\3\2\2\2RS\7\13\2\2ST\5\16\b\2"+
		"TV\5\6\4\2UW\7\3\2\2VU\3\2\2\2VW\3\2\2\2WY\3\2\2\2XZ\5\16\b\2YX\3\2\2"+
		"\2YZ\3\2\2\2Zc\3\2\2\2[]\5\16\b\2\\[\3\2\2\2\\]\3\2\2\2]^\3\2\2\2^`\7"+
		"\f\2\2_a\5\16\b\2`_\3\2\2\2`a\3\2\2\2ac\3\2\2\2b\24\3\2\2\2b \3\2\2\2"+
		"b\64\3\2\2\2bP\3\2\2\2b\\\3\2\2\2c\5\3\2\2\2de\7\r\2\2e\7\3\2\2\2fg\7"+
		"\r\2\2g\t\3\2\2\2hi\7\r\2\2i\13\3\2\2\2jk\7\r\2\2k\r\3\2\2\2ln\t\2\2\2"+
		"ml\3\2\2\2no\3\2\2\2om\3\2\2\2op\3\2\2\2p\17\3\2\2\2\25\24\32\35 ).\61"+
		"\64=EJMPVY\\`bo";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}