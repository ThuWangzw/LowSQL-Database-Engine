// Generated from D:/learn/LowSQL-Database-Engine/src/main/java\LowSQL.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LowSQLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, K_CREATE=8, K_TABLE=9, 
		K_PRIMARY=10, K_KEY=11, K_NOT=12, K_NULL=13, K_DROP=14, K_IF=15, K_EXISTS=16, 
		K_SHOW=17, K_INSERT=18, K_INTO=19, K_VALUES=20, K_SELECT=21, K_FROM=22, 
		K_WHERE=23, K_DELETE=24, LT=25, GT=26, LE=27, GE=28, EQ=29, LG=30, IDENTIFIER=31, 
		STRING_LITERAL=32, INTEGER_LITERAL=33, NUMERIC_LITERAL=34, SPACES=35, 
		UNEXPECTED_CHAR=36;
	public static final int
		RULE_parse = 0, RULE_error = 1, RULE_sql_stmt_list = 2, RULE_sql_stmt = 3, 
		RULE_create_table_stmt = 4, RULE_simple_select_stmt = 5, RULE_delete_stmt = 6, 
		RULE_attributes = 7, RULE_compare_stmt = 8, RULE_compare_symbol = 9, RULE_drop_table_stmt = 10, 
		RULE_table_constraint = 11, RULE_name = 12, RULE_indexed_column = 13, 
		RULE_column_def = 14, RULE_type_name = 15, RULE_signed_number = 16, RULE_column_constraint = 17, 
		RULE_show_table_stmt = 18, RULE_insert_stmt = 19, RULE_table_schema = 20, 
		RULE_insert_one_data = 21, RULE_literal_value = 22;
	private static String[] makeRuleNames() {
		return new String[] {
			"parse", "error", "sql_stmt_list", "sql_stmt", "create_table_stmt", "simple_select_stmt", 
			"delete_stmt", "attributes", "compare_stmt", "compare_symbol", "drop_table_stmt", 
			"table_constraint", "name", "indexed_column", "column_def", "type_name", 
			"signed_number", "column_constraint", "show_table_stmt", "insert_stmt", 
			"table_schema", "insert_one_data", "literal_value"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'('", "','", "')'", "'*'", "'+'", "'-'", null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "'<'", "'>'", "'<='", "'>='", "'='", "'<>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, "K_CREATE", "K_TABLE", 
			"K_PRIMARY", "K_KEY", "K_NOT", "K_NULL", "K_DROP", "K_IF", "K_EXISTS", 
			"K_SHOW", "K_INSERT", "K_INTO", "K_VALUES", "K_SELECT", "K_FROM", "K_WHERE", 
			"K_DELETE", "LT", "GT", "LE", "GE", "EQ", "LG", "IDENTIFIER", "STRING_LITERAL", 
			"INTEGER_LITERAL", "NUMERIC_LITERAL", "SPACES", "UNEXPECTED_CHAR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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
	public String getGrammarFileName() { return "LowSQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LowSQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ParseContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(LowSQLParser.EOF, 0); }
		public List<Sql_stmt_listContext> sql_stmt_list() {
			return getRuleContexts(Sql_stmt_listContext.class);
		}
		public Sql_stmt_listContext sql_stmt_list(int i) {
			return getRuleContext(Sql_stmt_listContext.class,i);
		}
		public List<ErrorContext> error() {
			return getRuleContexts(ErrorContext.class);
		}
		public ErrorContext error(int i) {
			return getRuleContext(ErrorContext.class,i);
		}
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitParse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitParse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_CREATE) | (1L << K_DROP) | (1L << K_SHOW) | (1L << K_INSERT) | (1L << K_SELECT) | (1L << K_DELETE) | (1L << UNEXPECTED_CHAR))) != 0)) {
				{
				setState(48);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case K_CREATE:
				case K_DROP:
				case K_SHOW:
				case K_INSERT:
				case K_SELECT:
				case K_DELETE:
					{
					setState(46);
					sql_stmt_list();
					}
					break;
				case UNEXPECTED_CHAR:
					{
					setState(47);
					error();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(53);
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

	public static class ErrorContext extends ParserRuleContext {
		public Token UNEXPECTED_CHAR;
		public TerminalNode UNEXPECTED_CHAR() { return getToken(LowSQLParser.UNEXPECTED_CHAR, 0); }
		public ErrorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_error; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterError(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitError(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitError(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ErrorContext error() throws RecognitionException {
		ErrorContext _localctx = new ErrorContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_error);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			((ErrorContext)_localctx).UNEXPECTED_CHAR = match(UNEXPECTED_CHAR);

			     throw new RuntimeException("UNEXPECTED_CHAR=" + (((ErrorContext)_localctx).UNEXPECTED_CHAR!=null?((ErrorContext)_localctx).UNEXPECTED_CHAR.getText():null));
			   
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

	public static class Sql_stmt_listContext extends ParserRuleContext {
		public List<Sql_stmtContext> sql_stmt() {
			return getRuleContexts(Sql_stmtContext.class);
		}
		public Sql_stmtContext sql_stmt(int i) {
			return getRuleContext(Sql_stmtContext.class,i);
		}
		public Sql_stmt_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sql_stmt_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterSql_stmt_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitSql_stmt_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitSql_stmt_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Sql_stmt_listContext sql_stmt_list() throws RecognitionException {
		Sql_stmt_listContext _localctx = new Sql_stmt_listContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_sql_stmt_list);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			sql_stmt();
			setState(67);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(60); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(59);
						match(T__0);
						}
						}
						setState(62); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__0 );
					setState(64);
					sql_stmt();
					}
					} 
				}
				setState(69);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(70);
				match(T__0);
				}
				}
				setState(75);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class Sql_stmtContext extends ParserRuleContext {
		public Create_table_stmtContext create_table_stmt() {
			return getRuleContext(Create_table_stmtContext.class,0);
		}
		public Drop_table_stmtContext drop_table_stmt() {
			return getRuleContext(Drop_table_stmtContext.class,0);
		}
		public Show_table_stmtContext show_table_stmt() {
			return getRuleContext(Show_table_stmtContext.class,0);
		}
		public Insert_stmtContext insert_stmt() {
			return getRuleContext(Insert_stmtContext.class,0);
		}
		public Simple_select_stmtContext simple_select_stmt() {
			return getRuleContext(Simple_select_stmtContext.class,0);
		}
		public Delete_stmtContext delete_stmt() {
			return getRuleContext(Delete_stmtContext.class,0);
		}
		public Sql_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sql_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterSql_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitSql_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitSql_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Sql_stmtContext sql_stmt() throws RecognitionException {
		Sql_stmtContext _localctx = new Sql_stmtContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_sql_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_CREATE:
				{
				setState(76);
				create_table_stmt();
				}
				break;
			case K_DROP:
				{
				setState(77);
				drop_table_stmt();
				}
				break;
			case K_SHOW:
				{
				setState(78);
				show_table_stmt();
				}
				break;
			case K_INSERT:
				{
				setState(79);
				insert_stmt();
				}
				break;
			case K_SELECT:
				{
				setState(80);
				simple_select_stmt();
				}
				break;
			case K_DELETE:
				{
				setState(81);
				delete_stmt();
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

	public static class Create_table_stmtContext extends ParserRuleContext {
		public TerminalNode K_CREATE() { return getToken(LowSQLParser.K_CREATE, 0); }
		public TerminalNode K_TABLE() { return getToken(LowSQLParser.K_TABLE, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public List<Column_defContext> column_def() {
			return getRuleContexts(Column_defContext.class);
		}
		public Column_defContext column_def(int i) {
			return getRuleContext(Column_defContext.class,i);
		}
		public List<Table_constraintContext> table_constraint() {
			return getRuleContexts(Table_constraintContext.class);
		}
		public Table_constraintContext table_constraint(int i) {
			return getRuleContext(Table_constraintContext.class,i);
		}
		public Create_table_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_create_table_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterCreate_table_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitCreate_table_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitCreate_table_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Create_table_stmtContext create_table_stmt() throws RecognitionException {
		Create_table_stmtContext _localctx = new Create_table_stmtContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_create_table_stmt);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(K_CREATE);
			setState(85);
			match(K_TABLE);
			setState(86);
			name();
			{
			setState(87);
			match(T__1);
			setState(88);
			column_def();
			setState(93);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(89);
					match(T__2);
					setState(90);
					column_def();
					}
					} 
				}
				setState(95);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(96);
				match(T__2);
				setState(97);
				table_constraint();
				}
				}
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(103);
			match(T__3);
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

	public static class Simple_select_stmtContext extends ParserRuleContext {
		public TerminalNode K_SELECT() { return getToken(LowSQLParser.K_SELECT, 0); }
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public TerminalNode K_FROM() { return getToken(LowSQLParser.K_FROM, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode K_WHERE() { return getToken(LowSQLParser.K_WHERE, 0); }
		public Compare_stmtContext compare_stmt() {
			return getRuleContext(Compare_stmtContext.class,0);
		}
		public Simple_select_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_select_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterSimple_select_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitSimple_select_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitSimple_select_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Simple_select_stmtContext simple_select_stmt() throws RecognitionException {
		Simple_select_stmtContext _localctx = new Simple_select_stmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_simple_select_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(K_SELECT);
			setState(106);
			attributes();
			setState(107);
			match(K_FROM);
			setState(108);
			name();
			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(109);
				match(K_WHERE);
				setState(110);
				compare_stmt();
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

	public static class Delete_stmtContext extends ParserRuleContext {
		public TerminalNode K_DELETE() { return getToken(LowSQLParser.K_DELETE, 0); }
		public TerminalNode K_FROM() { return getToken(LowSQLParser.K_FROM, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode K_WHERE() { return getToken(LowSQLParser.K_WHERE, 0); }
		public Compare_stmtContext compare_stmt() {
			return getRuleContext(Compare_stmtContext.class,0);
		}
		public Delete_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delete_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterDelete_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitDelete_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitDelete_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Delete_stmtContext delete_stmt() throws RecognitionException {
		Delete_stmtContext _localctx = new Delete_stmtContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_delete_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(K_DELETE);
			setState(114);
			match(K_FROM);
			setState(115);
			name();
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(116);
				match(K_WHERE);
				setState(117);
				compare_stmt();
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

	public static class AttributesContext extends ParserRuleContext {
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public AttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterAttributes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitAttributes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitAttributes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributesContext attributes() throws RecognitionException {
		AttributesContext _localctx = new AttributesContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_attributes);
		int _la;
		try {
			setState(129);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(120);
				match(T__4);
				}
				break;
			case T__1:
			case IDENTIFIER:
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(121);
				name();
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(122);
					match(T__2);
					setState(123);
					name();
					}
					}
					setState(128);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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

	public static class Compare_stmtContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Compare_symbolContext compare_symbol() {
			return getRuleContext(Compare_symbolContext.class,0);
		}
		public Literal_valueContext literal_value() {
			return getRuleContext(Literal_valueContext.class,0);
		}
		public Compare_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compare_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterCompare_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitCompare_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitCompare_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Compare_stmtContext compare_stmt() throws RecognitionException {
		Compare_stmtContext _localctx = new Compare_stmtContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_compare_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			name();
			setState(132);
			compare_symbol();
			setState(133);
			literal_value();
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

	public static class Compare_symbolContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(LowSQLParser.LT, 0); }
		public TerminalNode GT() { return getToken(LowSQLParser.GT, 0); }
		public TerminalNode LE() { return getToken(LowSQLParser.LE, 0); }
		public TerminalNode GE() { return getToken(LowSQLParser.GE, 0); }
		public TerminalNode EQ() { return getToken(LowSQLParser.EQ, 0); }
		public TerminalNode LG() { return getToken(LowSQLParser.LG, 0); }
		public Compare_symbolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compare_symbol; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterCompare_symbol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitCompare_symbol(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitCompare_symbol(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Compare_symbolContext compare_symbol() throws RecognitionException {
		Compare_symbolContext _localctx = new Compare_symbolContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_compare_symbol);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LT) | (1L << GT) | (1L << LE) | (1L << GE) | (1L << EQ) | (1L << LG))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
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

	public static class Drop_table_stmtContext extends ParserRuleContext {
		public TerminalNode K_DROP() { return getToken(LowSQLParser.K_DROP, 0); }
		public TerminalNode K_TABLE() { return getToken(LowSQLParser.K_TABLE, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode K_IF() { return getToken(LowSQLParser.K_IF, 0); }
		public TerminalNode K_EXISTS() { return getToken(LowSQLParser.K_EXISTS, 0); }
		public Drop_table_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_drop_table_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterDrop_table_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitDrop_table_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitDrop_table_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Drop_table_stmtContext drop_table_stmt() throws RecognitionException {
		Drop_table_stmtContext _localctx = new Drop_table_stmtContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_drop_table_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			match(K_DROP);
			setState(138);
			match(K_TABLE);
			setState(141);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_IF) {
				{
				setState(139);
				match(K_IF);
				setState(140);
				match(K_EXISTS);
				}
			}

			setState(143);
			name();
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

	public static class Table_constraintContext extends ParserRuleContext {
		public TerminalNode K_PRIMARY() { return getToken(LowSQLParser.K_PRIMARY, 0); }
		public TerminalNode K_KEY() { return getToken(LowSQLParser.K_KEY, 0); }
		public List<Indexed_columnContext> indexed_column() {
			return getRuleContexts(Indexed_columnContext.class);
		}
		public Indexed_columnContext indexed_column(int i) {
			return getRuleContext(Indexed_columnContext.class,i);
		}
		public Table_constraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterTable_constraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitTable_constraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitTable_constraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Table_constraintContext table_constraint() throws RecognitionException {
		Table_constraintContext _localctx = new Table_constraintContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_table_constraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(K_PRIMARY);
			setState(146);
			match(K_KEY);
			setState(147);
			match(T__1);
			setState(148);
			indexed_column();
			setState(153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(149);
				match(T__2);
				setState(150);
				indexed_column();
				}
				}
				setState(155);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(156);
			match(T__3);
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

	public static class NameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(LowSQLParser.IDENTIFIER, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(LowSQLParser.STRING_LITERAL, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_name);
		try {
			setState(164);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(158);
				match(IDENTIFIER);
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(159);
				match(STRING_LITERAL);
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 3);
				{
				setState(160);
				match(T__1);
				setState(161);
				name();
				setState(162);
				match(T__3);
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

	public static class Indexed_columnContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Indexed_columnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexed_column; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterIndexed_column(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitIndexed_column(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitIndexed_column(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Indexed_columnContext indexed_column() throws RecognitionException {
		Indexed_columnContext _localctx = new Indexed_columnContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_indexed_column);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			name();
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

	public static class Column_defContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Type_nameContext type_name() {
			return getRuleContext(Type_nameContext.class,0);
		}
		public List<Column_constraintContext> column_constraint() {
			return getRuleContexts(Column_constraintContext.class);
		}
		public Column_constraintContext column_constraint(int i) {
			return getRuleContext(Column_constraintContext.class,i);
		}
		public Column_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterColumn_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitColumn_def(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitColumn_def(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_defContext column_def() throws RecognitionException {
		Column_defContext _localctx = new Column_defContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_column_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			name();
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << IDENTIFIER) | (1L << STRING_LITERAL))) != 0)) {
				{
				setState(169);
				type_name();
				}
			}

			setState(175);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==K_NOT) {
				{
				{
				setState(172);
				column_constraint();
				}
				}
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class Type_nameContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Signed_numberContext signed_number() {
			return getRuleContext(Signed_numberContext.class,0);
		}
		public Type_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterType_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitType_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitType_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_nameContext type_name() throws RecognitionException {
		Type_nameContext _localctx = new Type_nameContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_type_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			name();
			setState(183);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(179);
				match(T__1);
				setState(180);
				signed_number();
				setState(181);
				match(T__3);
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

	public static class Signed_numberContext extends ParserRuleContext {
		public TerminalNode INTEGER_LITERAL() { return getToken(LowSQLParser.INTEGER_LITERAL, 0); }
		public Signed_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_signed_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterSigned_number(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitSigned_number(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitSigned_number(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Signed_numberContext signed_number() throws RecognitionException {
		Signed_numberContext _localctx = new Signed_numberContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_signed_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5 || _la==T__6) {
				{
				setState(185);
				_la = _input.LA(1);
				if ( !(_la==T__5 || _la==T__6) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(188);
			match(INTEGER_LITERAL);
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

	public static class Column_constraintContext extends ParserRuleContext {
		public TerminalNode K_NOT() { return getToken(LowSQLParser.K_NOT, 0); }
		public TerminalNode K_NULL() { return getToken(LowSQLParser.K_NULL, 0); }
		public Column_constraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterColumn_constraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitColumn_constraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitColumn_constraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_constraintContext column_constraint() throws RecognitionException {
		Column_constraintContext _localctx = new Column_constraintContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_column_constraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			match(K_NOT);
			setState(191);
			match(K_NULL);
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

	public static class Show_table_stmtContext extends ParserRuleContext {
		public TerminalNode K_SHOW() { return getToken(LowSQLParser.K_SHOW, 0); }
		public TerminalNode K_TABLE() { return getToken(LowSQLParser.K_TABLE, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Show_table_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_show_table_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterShow_table_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitShow_table_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitShow_table_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Show_table_stmtContext show_table_stmt() throws RecognitionException {
		Show_table_stmtContext _localctx = new Show_table_stmtContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_show_table_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			match(K_SHOW);
			setState(194);
			match(K_TABLE);
			setState(195);
			name();
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

	public static class Insert_stmtContext extends ParserRuleContext {
		public TerminalNode K_INSERT() { return getToken(LowSQLParser.K_INSERT, 0); }
		public TerminalNode K_INTO() { return getToken(LowSQLParser.K_INTO, 0); }
		public Table_schemaContext table_schema() {
			return getRuleContext(Table_schemaContext.class,0);
		}
		public TerminalNode K_VALUES() { return getToken(LowSQLParser.K_VALUES, 0); }
		public List<Insert_one_dataContext> insert_one_data() {
			return getRuleContexts(Insert_one_dataContext.class);
		}
		public Insert_one_dataContext insert_one_data(int i) {
			return getRuleContext(Insert_one_dataContext.class,i);
		}
		public Insert_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insert_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterInsert_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitInsert_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitInsert_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Insert_stmtContext insert_stmt() throws RecognitionException {
		Insert_stmtContext _localctx = new Insert_stmtContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_insert_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			match(K_INSERT);
			setState(198);
			match(K_INTO);
			setState(199);
			table_schema();
			{
			setState(200);
			match(K_VALUES);
			setState(201);
			match(T__1);
			setState(202);
			insert_one_data();
			setState(203);
			match(T__3);
			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(204);
				match(T__2);
				setState(205);
				match(T__1);
				setState(206);
				insert_one_data();
				setState(207);
				match(T__3);
				}
				}
				setState(213);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class Table_schemaContext extends ParserRuleContext {
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public Table_schemaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_schema; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterTable_schema(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitTable_schema(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitTable_schema(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Table_schemaContext table_schema() throws RecognitionException {
		Table_schemaContext _localctx = new Table_schemaContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_table_schema);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			name();
			setState(226);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(215);
				match(T__1);
				setState(216);
				name();
				setState(221);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(217);
					match(T__2);
					setState(218);
					name();
					}
					}
					setState(223);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(224);
				match(T__3);
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

	public static class Insert_one_dataContext extends ParserRuleContext {
		public List<Literal_valueContext> literal_value() {
			return getRuleContexts(Literal_valueContext.class);
		}
		public Literal_valueContext literal_value(int i) {
			return getRuleContext(Literal_valueContext.class,i);
		}
		public Insert_one_dataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insert_one_data; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterInsert_one_data(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitInsert_one_data(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitInsert_one_data(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Insert_one_dataContext insert_one_data() throws RecognitionException {
		Insert_one_dataContext _localctx = new Insert_one_dataContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_insert_one_data);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			literal_value();
			setState(233);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(229);
				match(T__2);
				setState(230);
				literal_value();
				}
				}
				setState(235);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class Literal_valueContext extends ParserRuleContext {
		public TerminalNode NUMERIC_LITERAL() { return getToken(LowSQLParser.NUMERIC_LITERAL, 0); }
		public TerminalNode INTEGER_LITERAL() { return getToken(LowSQLParser.INTEGER_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(LowSQLParser.STRING_LITERAL, 0); }
		public TerminalNode K_NULL() { return getToken(LowSQLParser.K_NULL, 0); }
		public Literal_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterLiteral_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitLiteral_value(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitLiteral_value(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Literal_valueContext literal_value() throws RecognitionException {
		Literal_valueContext _localctx = new Literal_valueContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_literal_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_NULL) | (1L << STRING_LITERAL) | (1L << INTEGER_LITERAL) | (1L << NUMERIC_LITERAL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3&\u00f1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2\3\2\7"+
		"\2\63\n\2\f\2\16\2\66\13\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\6\4?\n\4\r\4\16"+
		"\4@\3\4\7\4D\n\4\f\4\16\4G\13\4\3\4\7\4J\n\4\f\4\16\4M\13\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\5\5U\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6^\n\6\f\6\16\6"+
		"a\13\6\3\6\3\6\7\6e\n\6\f\6\16\6h\13\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\5\7r\n\7\3\b\3\b\3\b\3\b\3\b\5\by\n\b\3\t\3\t\3\t\3\t\7\t\177\n\t\f\t"+
		"\16\t\u0082\13\t\5\t\u0084\n\t\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3"+
		"\f\5\f\u0090\n\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u009a\n\r\f\r\16"+
		"\r\u009d\13\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00a7\n\16\3"+
		"\17\3\17\3\20\3\20\5\20\u00ad\n\20\3\20\7\20\u00b0\n\20\f\20\16\20\u00b3"+
		"\13\20\3\21\3\21\3\21\3\21\3\21\5\21\u00ba\n\21\3\22\5\22\u00bd\n\22\3"+
		"\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3"+
		"\25\3\25\3\25\3\25\3\25\3\25\3\25\7\25\u00d4\n\25\f\25\16\25\u00d7\13"+
		"\25\3\26\3\26\3\26\3\26\3\26\7\26\u00de\n\26\f\26\16\26\u00e1\13\26\3"+
		"\26\3\26\5\26\u00e5\n\26\3\27\3\27\3\27\7\27\u00ea\n\27\f\27\16\27\u00ed"+
		"\13\27\3\30\3\30\3\30\2\2\31\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \""+
		"$&(*,.\2\5\3\2\33 \3\2\b\t\4\2\17\17\"$\2\u00f5\2\64\3\2\2\2\49\3\2\2"+
		"\2\6<\3\2\2\2\bT\3\2\2\2\nV\3\2\2\2\fk\3\2\2\2\16s\3\2\2\2\20\u0083\3"+
		"\2\2\2\22\u0085\3\2\2\2\24\u0089\3\2\2\2\26\u008b\3\2\2\2\30\u0093\3\2"+
		"\2\2\32\u00a6\3\2\2\2\34\u00a8\3\2\2\2\36\u00aa\3\2\2\2 \u00b4\3\2\2\2"+
		"\"\u00bc\3\2\2\2$\u00c0\3\2\2\2&\u00c3\3\2\2\2(\u00c7\3\2\2\2*\u00d8\3"+
		"\2\2\2,\u00e6\3\2\2\2.\u00ee\3\2\2\2\60\63\5\6\4\2\61\63\5\4\3\2\62\60"+
		"\3\2\2\2\62\61\3\2\2\2\63\66\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65\67"+
		"\3\2\2\2\66\64\3\2\2\2\678\7\2\2\38\3\3\2\2\29:\7&\2\2:;\b\3\1\2;\5\3"+
		"\2\2\2<E\5\b\5\2=?\7\3\2\2>=\3\2\2\2?@\3\2\2\2@>\3\2\2\2@A\3\2\2\2AB\3"+
		"\2\2\2BD\5\b\5\2C>\3\2\2\2DG\3\2\2\2EC\3\2\2\2EF\3\2\2\2FK\3\2\2\2GE\3"+
		"\2\2\2HJ\7\3\2\2IH\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2L\7\3\2\2\2MK"+
		"\3\2\2\2NU\5\n\6\2OU\5\26\f\2PU\5&\24\2QU\5(\25\2RU\5\f\7\2SU\5\16\b\2"+
		"TN\3\2\2\2TO\3\2\2\2TP\3\2\2\2TQ\3\2\2\2TR\3\2\2\2TS\3\2\2\2U\t\3\2\2"+
		"\2VW\7\n\2\2WX\7\13\2\2XY\5\32\16\2YZ\7\4\2\2Z_\5\36\20\2[\\\7\5\2\2\\"+
		"^\5\36\20\2][\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`f\3\2\2\2a_\3\2\2"+
		"\2bc\7\5\2\2ce\5\30\r\2db\3\2\2\2eh\3\2\2\2fd\3\2\2\2fg\3\2\2\2gi\3\2"+
		"\2\2hf\3\2\2\2ij\7\6\2\2j\13\3\2\2\2kl\7\27\2\2lm\5\20\t\2mn\7\30\2\2"+
		"nq\5\32\16\2op\7\31\2\2pr\5\22\n\2qo\3\2\2\2qr\3\2\2\2r\r\3\2\2\2st\7"+
		"\32\2\2tu\7\30\2\2ux\5\32\16\2vw\7\31\2\2wy\5\22\n\2xv\3\2\2\2xy\3\2\2"+
		"\2y\17\3\2\2\2z\u0084\7\7\2\2{\u0080\5\32\16\2|}\7\5\2\2}\177\5\32\16"+
		"\2~|\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081"+
		"\u0084\3\2\2\2\u0082\u0080\3\2\2\2\u0083z\3\2\2\2\u0083{\3\2\2\2\u0084"+
		"\21\3\2\2\2\u0085\u0086\5\32\16\2\u0086\u0087\5\24\13\2\u0087\u0088\5"+
		".\30\2\u0088\23\3\2\2\2\u0089\u008a\t\2\2\2\u008a\25\3\2\2\2\u008b\u008c"+
		"\7\20\2\2\u008c\u008f\7\13\2\2\u008d\u008e\7\21\2\2\u008e\u0090\7\22\2"+
		"\2\u008f\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0092"+
		"\5\32\16\2\u0092\27\3\2\2\2\u0093\u0094\7\f\2\2\u0094\u0095\7\r\2\2\u0095"+
		"\u0096\7\4\2\2\u0096\u009b\5\34\17\2\u0097\u0098\7\5\2\2\u0098\u009a\5"+
		"\34\17\2\u0099\u0097\3\2\2\2\u009a\u009d\3\2\2\2\u009b\u0099\3\2\2\2\u009b"+
		"\u009c\3\2\2\2\u009c\u009e\3\2\2\2\u009d\u009b\3\2\2\2\u009e\u009f\7\6"+
		"\2\2\u009f\31\3\2\2\2\u00a0\u00a7\7!\2\2\u00a1\u00a7\7\"\2\2\u00a2\u00a3"+
		"\7\4\2\2\u00a3\u00a4\5\32\16\2\u00a4\u00a5\7\6\2\2\u00a5\u00a7\3\2\2\2"+
		"\u00a6\u00a0\3\2\2\2\u00a6\u00a1\3\2\2\2\u00a6\u00a2\3\2\2\2\u00a7\33"+
		"\3\2\2\2\u00a8\u00a9\5\32\16\2\u00a9\35\3\2\2\2\u00aa\u00ac\5\32\16\2"+
		"\u00ab\u00ad\5 \21\2\u00ac\u00ab\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00b1"+
		"\3\2\2\2\u00ae\u00b0\5$\23\2\u00af\u00ae\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1"+
		"\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\37\3\2\2\2\u00b3\u00b1\3\2\2"+
		"\2\u00b4\u00b9\5\32\16\2\u00b5\u00b6\7\4\2\2\u00b6\u00b7\5\"\22\2\u00b7"+
		"\u00b8\7\6\2\2\u00b8\u00ba\3\2\2\2\u00b9\u00b5\3\2\2\2\u00b9\u00ba\3\2"+
		"\2\2\u00ba!\3\2\2\2\u00bb\u00bd\t\3\2\2\u00bc\u00bb\3\2\2\2\u00bc\u00bd"+
		"\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bf\7#\2\2\u00bf#\3\2\2\2\u00c0\u00c1"+
		"\7\16\2\2\u00c1\u00c2\7\17\2\2\u00c2%\3\2\2\2\u00c3\u00c4\7\23\2\2\u00c4"+
		"\u00c5\7\13\2\2\u00c5\u00c6\5\32\16\2\u00c6\'\3\2\2\2\u00c7\u00c8\7\24"+
		"\2\2\u00c8\u00c9\7\25\2\2\u00c9\u00ca\5*\26\2\u00ca\u00cb\7\26\2\2\u00cb"+
		"\u00cc\7\4\2\2\u00cc\u00cd\5,\27\2\u00cd\u00d5\7\6\2\2\u00ce\u00cf\7\5"+
		"\2\2\u00cf\u00d0\7\4\2\2\u00d0\u00d1\5,\27\2\u00d1\u00d2\7\6\2\2\u00d2"+
		"\u00d4\3\2\2\2\u00d3\u00ce\3\2\2\2\u00d4\u00d7\3\2\2\2\u00d5\u00d3\3\2"+
		"\2\2\u00d5\u00d6\3\2\2\2\u00d6)\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d8\u00e4"+
		"\5\32\16\2\u00d9\u00da\7\4\2\2\u00da\u00df\5\32\16\2\u00db\u00dc\7\5\2"+
		"\2\u00dc\u00de\5\32\16\2\u00dd\u00db\3\2\2\2\u00de\u00e1\3\2\2\2\u00df"+
		"\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e2\3\2\2\2\u00e1\u00df\3\2"+
		"\2\2\u00e2\u00e3\7\6\2\2\u00e3\u00e5\3\2\2\2\u00e4\u00d9\3\2\2\2\u00e4"+
		"\u00e5\3\2\2\2\u00e5+\3\2\2\2\u00e6\u00eb\5.\30\2\u00e7\u00e8\7\5\2\2"+
		"\u00e8\u00ea\5.\30\2\u00e9\u00e7\3\2\2\2\u00ea\u00ed\3\2\2\2\u00eb\u00e9"+
		"\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec-\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ee"+
		"\u00ef\t\4\2\2\u00ef/\3\2\2\2\31\62\64@EKT_fqx\u0080\u0083\u008f\u009b"+
		"\u00a6\u00ac\u00b1\u00b9\u00bc\u00d5\u00df\u00e4\u00eb";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}