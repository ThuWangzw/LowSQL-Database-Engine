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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, K_CREATE=9, 
		K_TABLE=10, K_PRIMARY=11, K_KEY=12, K_NOT=13, K_NULL=14, K_DROP=15, K_IF=16, 
		K_EXISTS=17, K_SHOW=18, K_INSERT=19, K_INTO=20, K_VALUES=21, K_SELECT=22, 
		K_FROM=23, K_WHERE=24, K_DELETE=25, K_UPDATE=26, K_SET=27, K_JOIN=28, 
		K_ON=29, LT=30, GT=31, LE=32, GE=33, EQ=34, LG=35, IDENTIFIER=36, STRING_LITERAL=37, 
		INTEGER_LITERAL=38, NUMERIC_LITERAL=39, SPACES=40, UNEXPECTED_CHAR=41;
	public static final int
		RULE_parse = 0, RULE_error = 1, RULE_sql_stmt_list = 2, RULE_sql_stmt = 3, 
		RULE_create_table_stmt = 4, RULE_simple_select_stmt = 5, RULE_delete_stmt = 6, 
		RULE_update_stmt = 7, RULE_complex_select_stmt = 8, RULE_attributes = 9, 
		RULE_two_attributes = 10, RULE_compare_stmt = 11, RULE_compare_symbol = 12, 
		RULE_drop_table_stmt = 13, RULE_table_constraint = 14, RULE_name = 15, 
		RULE_indexed_column = 16, RULE_column_def = 17, RULE_type_name = 18, RULE_signed_number = 19, 
		RULE_column_constraint = 20, RULE_show_table_stmt = 21, RULE_insert_stmt = 22, 
		RULE_table_schema = 23, RULE_insert_one_data = 24, RULE_literal_value = 25;
	private static String[] makeRuleNames() {
		return new String[] {
			"parse", "error", "sql_stmt_list", "sql_stmt", "create_table_stmt", "simple_select_stmt", 
			"delete_stmt", "update_stmt", "complex_select_stmt", "attributes", "two_attributes", 
			"compare_stmt", "compare_symbol", "drop_table_stmt", "table_constraint", 
			"name", "indexed_column", "column_def", "type_name", "signed_number", 
			"column_constraint", "show_table_stmt", "insert_stmt", "table_schema", 
			"insert_one_data", "literal_value"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'('", "','", "')'", "'.'", "'*'", "'+'", "'-'", null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "'<'", "'>'", "'<='", "'>='", 
			"'='", "'<>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "K_CREATE", "K_TABLE", 
			"K_PRIMARY", "K_KEY", "K_NOT", "K_NULL", "K_DROP", "K_IF", "K_EXISTS", 
			"K_SHOW", "K_INSERT", "K_INTO", "K_VALUES", "K_SELECT", "K_FROM", "K_WHERE", 
			"K_DELETE", "K_UPDATE", "K_SET", "K_JOIN", "K_ON", "LT", "GT", "LE", 
			"GE", "EQ", "LG", "IDENTIFIER", "STRING_LITERAL", "INTEGER_LITERAL", 
			"NUMERIC_LITERAL", "SPACES", "UNEXPECTED_CHAR"
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
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_CREATE) | (1L << K_DROP) | (1L << K_SHOW) | (1L << K_INSERT) | (1L << K_SELECT) | (1L << K_DELETE) | (1L << K_UPDATE) | (1L << UNEXPECTED_CHAR))) != 0)) {
				{
				setState(54);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case K_CREATE:
				case K_DROP:
				case K_SHOW:
				case K_INSERT:
				case K_SELECT:
				case K_DELETE:
				case K_UPDATE:
					{
					setState(52);
					sql_stmt_list();
					}
					break;
				case UNEXPECTED_CHAR:
					{
					setState(53);
					error();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(59);
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
			setState(61);
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
			setState(64);
			sql_stmt();
			setState(73);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(66); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(65);
						match(T__0);
						}
						}
						setState(68); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__0 );
					setState(70);
					sql_stmt();
					}
					} 
				}
				setState(75);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(76);
				match(T__0);
				}
				}
				setState(81);
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
		public Complex_select_stmtContext complex_select_stmt() {
			return getRuleContext(Complex_select_stmtContext.class,0);
		}
		public Delete_stmtContext delete_stmt() {
			return getRuleContext(Delete_stmtContext.class,0);
		}
		public Update_stmtContext update_stmt() {
			return getRuleContext(Update_stmtContext.class,0);
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
			setState(90);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(82);
				create_table_stmt();
				}
				break;
			case 2:
				{
				setState(83);
				drop_table_stmt();
				}
				break;
			case 3:
				{
				setState(84);
				show_table_stmt();
				}
				break;
			case 4:
				{
				setState(85);
				insert_stmt();
				}
				break;
			case 5:
				{
				setState(86);
				simple_select_stmt();
				}
				break;
			case 6:
				{
				setState(87);
				complex_select_stmt();
				}
				break;
			case 7:
				{
				setState(88);
				delete_stmt();
				}
				break;
			case 8:
				{
				setState(89);
				update_stmt();
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
			setState(92);
			match(K_CREATE);
			setState(93);
			match(K_TABLE);
			setState(94);
			name();
			{
			setState(95);
			match(T__1);
			setState(96);
			column_def();
			setState(101);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(97);
					match(T__2);
					setState(98);
					column_def();
					}
					} 
				}
				setState(103);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(104);
				match(T__2);
				setState(105);
				table_constraint();
				}
				}
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(111);
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
			setState(113);
			match(K_SELECT);
			setState(114);
			attributes();
			setState(115);
			match(K_FROM);
			setState(116);
			name();
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(117);
				match(K_WHERE);
				setState(118);
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
			setState(121);
			match(K_DELETE);
			setState(122);
			match(K_FROM);
			setState(123);
			name();
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(124);
				match(K_WHERE);
				setState(125);
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

	public static class Update_stmtContext extends ParserRuleContext {
		public TerminalNode K_UPDATE() { return getToken(LowSQLParser.K_UPDATE, 0); }
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public TerminalNode K_SET() { return getToken(LowSQLParser.K_SET, 0); }
		public TerminalNode EQ() { return getToken(LowSQLParser.EQ, 0); }
		public Literal_valueContext literal_value() {
			return getRuleContext(Literal_valueContext.class,0);
		}
		public TerminalNode K_WHERE() { return getToken(LowSQLParser.K_WHERE, 0); }
		public Compare_stmtContext compare_stmt() {
			return getRuleContext(Compare_stmtContext.class,0);
		}
		public Update_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_update_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterUpdate_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitUpdate_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitUpdate_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Update_stmtContext update_stmt() throws RecognitionException {
		Update_stmtContext _localctx = new Update_stmtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_update_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			match(K_UPDATE);
			setState(129);
			name();
			setState(130);
			match(K_SET);
			setState(131);
			name();
			setState(132);
			match(EQ);
			setState(133);
			literal_value();
			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(134);
				match(K_WHERE);
				setState(135);
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

	public static class Complex_select_stmtContext extends ParserRuleContext {
		public TerminalNode K_SELECT() { return getToken(LowSQLParser.K_SELECT, 0); }
		public Two_attributesContext two_attributes() {
			return getRuleContext(Two_attributesContext.class,0);
		}
		public TerminalNode K_FROM() { return getToken(LowSQLParser.K_FROM, 0); }
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public TerminalNode K_JOIN() { return getToken(LowSQLParser.K_JOIN, 0); }
		public TerminalNode K_ON() { return getToken(LowSQLParser.K_ON, 0); }
		public TerminalNode EQ() { return getToken(LowSQLParser.EQ, 0); }
		public TerminalNode K_WHERE() { return getToken(LowSQLParser.K_WHERE, 0); }
		public Compare_stmtContext compare_stmt() {
			return getRuleContext(Compare_stmtContext.class,0);
		}
		public Complex_select_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_complex_select_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterComplex_select_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitComplex_select_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitComplex_select_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Complex_select_stmtContext complex_select_stmt() throws RecognitionException {
		Complex_select_stmtContext _localctx = new Complex_select_stmtContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_complex_select_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(K_SELECT);
			setState(139);
			two_attributes();
			setState(140);
			match(K_FROM);
			setState(141);
			name();
			setState(142);
			match(K_JOIN);
			setState(143);
			name();
			setState(144);
			match(K_ON);
			setState(145);
			name();
			setState(146);
			match(T__4);
			setState(147);
			name();
			setState(148);
			match(EQ);
			setState(149);
			name();
			setState(150);
			match(T__4);
			setState(151);
			name();
			setState(154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(152);
				match(K_WHERE);
				setState(153);
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
		enterRule(_localctx, 18, RULE_attributes);
		int _la;
		try {
			setState(165);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(156);
				match(T__5);
				}
				break;
			case T__1:
			case IDENTIFIER:
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(157);
				name();
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(158);
					match(T__2);
					setState(159);
					name();
					}
					}
					setState(164);
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

	public static class Two_attributesContext extends ParserRuleContext {
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public Two_attributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_two_attributes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterTwo_attributes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitTwo_attributes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitTwo_attributes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Two_attributesContext two_attributes() throws RecognitionException {
		Two_attributesContext _localctx = new Two_attributesContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_two_attributes);
		int _la;
		try {
			setState(181);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(167);
				match(T__5);
				}
				break;
			case T__1:
			case IDENTIFIER:
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(168);
				name();
				setState(169);
				match(T__4);
				setState(170);
				name();
				setState(178);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(171);
					match(T__2);
					setState(172);
					name();
					setState(173);
					match(T__4);
					setState(174);
					name();
					}
					}
					setState(180);
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
		enterRule(_localctx, 22, RULE_compare_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			name();
			setState(184);
			compare_symbol();
			setState(185);
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
		enterRule(_localctx, 24, RULE_compare_symbol);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
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
		enterRule(_localctx, 26, RULE_drop_table_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(K_DROP);
			setState(190);
			match(K_TABLE);
			setState(193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_IF) {
				{
				setState(191);
				match(K_IF);
				setState(192);
				match(K_EXISTS);
				}
			}

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
		enterRule(_localctx, 28, RULE_table_constraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			match(K_PRIMARY);
			setState(198);
			match(K_KEY);
			setState(199);
			match(T__1);
			setState(200);
			indexed_column();
			setState(205);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(201);
				match(T__2);
				setState(202);
				indexed_column();
				}
				}
				setState(207);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(208);
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
		enterRule(_localctx, 30, RULE_name);
		try {
			setState(216);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(210);
				match(IDENTIFIER);
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(211);
				match(STRING_LITERAL);
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 3);
				{
				setState(212);
				match(T__1);
				setState(213);
				name();
				setState(214);
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
		enterRule(_localctx, 32, RULE_indexed_column);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
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
		enterRule(_localctx, 34, RULE_column_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			name();
			setState(222);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << IDENTIFIER) | (1L << STRING_LITERAL))) != 0)) {
				{
				setState(221);
				type_name();
				}
			}

			setState(227);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==K_NOT) {
				{
				{
				setState(224);
				column_constraint();
				}
				}
				setState(229);
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
		enterRule(_localctx, 36, RULE_type_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			name();
			setState(235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(231);
				match(T__1);
				setState(232);
				signed_number();
				setState(233);
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
		enterRule(_localctx, 38, RULE_signed_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6 || _la==T__7) {
				{
				setState(237);
				_la = _input.LA(1);
				if ( !(_la==T__6 || _la==T__7) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(240);
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
		enterRule(_localctx, 40, RULE_column_constraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			match(K_NOT);
			setState(243);
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
		enterRule(_localctx, 42, RULE_show_table_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			match(K_SHOW);
			setState(246);
			match(K_TABLE);
			setState(247);
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
		enterRule(_localctx, 44, RULE_insert_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			match(K_INSERT);
			setState(250);
			match(K_INTO);
			setState(251);
			table_schema();
			{
			setState(252);
			match(K_VALUES);
			setState(253);
			match(T__1);
			setState(254);
			insert_one_data();
			setState(255);
			match(T__3);
			setState(263);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(256);
				match(T__2);
				setState(257);
				match(T__1);
				setState(258);
				insert_one_data();
				setState(259);
				match(T__3);
				}
				}
				setState(265);
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
		enterRule(_localctx, 46, RULE_table_schema);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			name();
			setState(278);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(267);
				match(T__1);
				setState(268);
				name();
				setState(273);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(269);
					match(T__2);
					setState(270);
					name();
					}
					}
					setState(275);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(276);
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
		enterRule(_localctx, 48, RULE_insert_one_data);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			literal_value();
			setState(285);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(281);
				match(T__2);
				setState(282);
				literal_value();
				}
				}
				setState(287);
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
		enterRule(_localctx, 50, RULE_literal_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3+\u0125\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\3\2\3\2\7\29\n\2\f\2\16\2<\13\2\3\2\3\2\3\3\3\3\3"+
		"\3\3\4\3\4\6\4E\n\4\r\4\16\4F\3\4\7\4J\n\4\f\4\16\4M\13\4\3\4\7\4P\n\4"+
		"\f\4\16\4S\13\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5]\n\5\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\7\6f\n\6\f\6\16\6i\13\6\3\6\3\6\7\6m\n\6\f\6\16\6p\13\6"+
		"\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\5\7z\n\7\3\b\3\b\3\b\3\b\3\b\5\b\u0081"+
		"\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u008b\n\t\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u009d\n\n\3\13\3\13\3"+
		"\13\3\13\7\13\u00a3\n\13\f\13\16\13\u00a6\13\13\5\13\u00a8\n\13\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00b3\n\f\f\f\16\f\u00b6\13\f\5\f\u00b8"+
		"\n\f\3\r\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\5\17\u00c4\n\17\3\17"+
		"\3\17\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u00ce\n\20\f\20\16\20\u00d1\13"+
		"\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00db\n\21\3\22\3\22"+
		"\3\23\3\23\5\23\u00e1\n\23\3\23\7\23\u00e4\n\23\f\23\16\23\u00e7\13\23"+
		"\3\24\3\24\3\24\3\24\3\24\5\24\u00ee\n\24\3\25\5\25\u00f1\n\25\3\25\3"+
		"\25\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\7\30\u0108\n\30\f\30\16\30\u010b\13\30\3"+
		"\31\3\31\3\31\3\31\3\31\7\31\u0112\n\31\f\31\16\31\u0115\13\31\3\31\3"+
		"\31\5\31\u0119\n\31\3\32\3\32\3\32\7\32\u011e\n\32\f\32\16\32\u0121\13"+
		"\32\3\33\3\33\3\33\2\2\34\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&"+
		"(*,.\60\62\64\2\5\3\2 %\3\2\t\n\4\2\20\20\')\2\u012c\2:\3\2\2\2\4?\3\2"+
		"\2\2\6B\3\2\2\2\b\\\3\2\2\2\n^\3\2\2\2\fs\3\2\2\2\16{\3\2\2\2\20\u0082"+
		"\3\2\2\2\22\u008c\3\2\2\2\24\u00a7\3\2\2\2\26\u00b7\3\2\2\2\30\u00b9\3"+
		"\2\2\2\32\u00bd\3\2\2\2\34\u00bf\3\2\2\2\36\u00c7\3\2\2\2 \u00da\3\2\2"+
		"\2\"\u00dc\3\2\2\2$\u00de\3\2\2\2&\u00e8\3\2\2\2(\u00f0\3\2\2\2*\u00f4"+
		"\3\2\2\2,\u00f7\3\2\2\2.\u00fb\3\2\2\2\60\u010c\3\2\2\2\62\u011a\3\2\2"+
		"\2\64\u0122\3\2\2\2\669\5\6\4\2\679\5\4\3\28\66\3\2\2\28\67\3\2\2\29<"+
		"\3\2\2\2:8\3\2\2\2:;\3\2\2\2;=\3\2\2\2<:\3\2\2\2=>\7\2\2\3>\3\3\2\2\2"+
		"?@\7+\2\2@A\b\3\1\2A\5\3\2\2\2BK\5\b\5\2CE\7\3\2\2DC\3\2\2\2EF\3\2\2\2"+
		"FD\3\2\2\2FG\3\2\2\2GH\3\2\2\2HJ\5\b\5\2ID\3\2\2\2JM\3\2\2\2KI\3\2\2\2"+
		"KL\3\2\2\2LQ\3\2\2\2MK\3\2\2\2NP\7\3\2\2ON\3\2\2\2PS\3\2\2\2QO\3\2\2\2"+
		"QR\3\2\2\2R\7\3\2\2\2SQ\3\2\2\2T]\5\n\6\2U]\5\34\17\2V]\5,\27\2W]\5.\30"+
		"\2X]\5\f\7\2Y]\5\22\n\2Z]\5\16\b\2[]\5\20\t\2\\T\3\2\2\2\\U\3\2\2\2\\"+
		"V\3\2\2\2\\W\3\2\2\2\\X\3\2\2\2\\Y\3\2\2\2\\Z\3\2\2\2\\[\3\2\2\2]\t\3"+
		"\2\2\2^_\7\13\2\2_`\7\f\2\2`a\5 \21\2ab\7\4\2\2bg\5$\23\2cd\7\5\2\2df"+
		"\5$\23\2ec\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2hn\3\2\2\2ig\3\2\2\2j"+
		"k\7\5\2\2km\5\36\20\2lj\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2oq\3\2\2"+
		"\2pn\3\2\2\2qr\7\6\2\2r\13\3\2\2\2st\7\30\2\2tu\5\24\13\2uv\7\31\2\2v"+
		"y\5 \21\2wx\7\32\2\2xz\5\30\r\2yw\3\2\2\2yz\3\2\2\2z\r\3\2\2\2{|\7\33"+
		"\2\2|}\7\31\2\2}\u0080\5 \21\2~\177\7\32\2\2\177\u0081\5\30\r\2\u0080"+
		"~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\17\3\2\2\2\u0082\u0083\7\34\2\2\u0083"+
		"\u0084\5 \21\2\u0084\u0085\7\35\2\2\u0085\u0086\5 \21\2\u0086\u0087\7"+
		"$\2\2\u0087\u008a\5\64\33\2\u0088\u0089\7\32\2\2\u0089\u008b\5\30\r\2"+
		"\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\21\3\2\2\2\u008c\u008d"+
		"\7\30\2\2\u008d\u008e\5\26\f\2\u008e\u008f\7\31\2\2\u008f\u0090\5 \21"+
		"\2\u0090\u0091\7\36\2\2\u0091\u0092\5 \21\2\u0092\u0093\7\37\2\2\u0093"+
		"\u0094\5 \21\2\u0094\u0095\7\7\2\2\u0095\u0096\5 \21\2\u0096\u0097\7$"+
		"\2\2\u0097\u0098\5 \21\2\u0098\u0099\7\7\2\2\u0099\u009c\5 \21\2\u009a"+
		"\u009b\7\32\2\2\u009b\u009d\5\30\r\2\u009c\u009a\3\2\2\2\u009c\u009d\3"+
		"\2\2\2\u009d\23\3\2\2\2\u009e\u00a8\7\b\2\2\u009f\u00a4\5 \21\2\u00a0"+
		"\u00a1\7\5\2\2\u00a1\u00a3\5 \21\2\u00a2\u00a0\3\2\2\2\u00a3\u00a6\3\2"+
		"\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a8\3\2\2\2\u00a6"+
		"\u00a4\3\2\2\2\u00a7\u009e\3\2\2\2\u00a7\u009f\3\2\2\2\u00a8\25\3\2\2"+
		"\2\u00a9\u00b8\7\b\2\2\u00aa\u00ab\5 \21\2\u00ab\u00ac\7\7\2\2\u00ac\u00b4"+
		"\5 \21\2\u00ad\u00ae\7\5\2\2\u00ae\u00af\5 \21\2\u00af\u00b0\7\7\2\2\u00b0"+
		"\u00b1\5 \21\2\u00b1\u00b3\3\2\2\2\u00b2\u00ad\3\2\2\2\u00b3\u00b6\3\2"+
		"\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6"+
		"\u00b4\3\2\2\2\u00b7\u00a9\3\2\2\2\u00b7\u00aa\3\2\2\2\u00b8\27\3\2\2"+
		"\2\u00b9\u00ba\5 \21\2\u00ba\u00bb\5\32\16\2\u00bb\u00bc\5\64\33\2\u00bc"+
		"\31\3\2\2\2\u00bd\u00be\t\2\2\2\u00be\33\3\2\2\2\u00bf\u00c0\7\21\2\2"+
		"\u00c0\u00c3\7\f\2\2\u00c1\u00c2\7\22\2\2\u00c2\u00c4\7\23\2\2\u00c3\u00c1"+
		"\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c6\5 \21\2\u00c6"+
		"\35\3\2\2\2\u00c7\u00c8\7\r\2\2\u00c8\u00c9\7\16\2\2\u00c9\u00ca\7\4\2"+
		"\2\u00ca\u00cf\5\"\22\2\u00cb\u00cc\7\5\2\2\u00cc\u00ce\5\"\22\2\u00cd"+
		"\u00cb\3\2\2\2\u00ce\u00d1\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2"+
		"\2\2\u00d0\u00d2\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d2\u00d3\7\6\2\2\u00d3"+
		"\37\3\2\2\2\u00d4\u00db\7&\2\2\u00d5\u00db\7\'\2\2\u00d6\u00d7\7\4\2\2"+
		"\u00d7\u00d8\5 \21\2\u00d8\u00d9\7\6\2\2\u00d9\u00db\3\2\2\2\u00da\u00d4"+
		"\3\2\2\2\u00da\u00d5\3\2\2\2\u00da\u00d6\3\2\2\2\u00db!\3\2\2\2\u00dc"+
		"\u00dd\5 \21\2\u00dd#\3\2\2\2\u00de\u00e0\5 \21\2\u00df\u00e1\5&\24\2"+
		"\u00e0\u00df\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e5\3\2\2\2\u00e2\u00e4"+
		"\5*\26\2\u00e3\u00e2\3\2\2\2\u00e4\u00e7\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5"+
		"\u00e6\3\2\2\2\u00e6%\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e8\u00ed\5 \21\2"+
		"\u00e9\u00ea\7\4\2\2\u00ea\u00eb\5(\25\2\u00eb\u00ec\7\6\2\2\u00ec\u00ee"+
		"\3\2\2\2\u00ed\u00e9\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\'\3\2\2\2\u00ef"+
		"\u00f1\t\3\2\2\u00f0\u00ef\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f2\3\2"+
		"\2\2\u00f2\u00f3\7(\2\2\u00f3)\3\2\2\2\u00f4\u00f5\7\17\2\2\u00f5\u00f6"+
		"\7\20\2\2\u00f6+\3\2\2\2\u00f7\u00f8\7\24\2\2\u00f8\u00f9\7\f\2\2\u00f9"+
		"\u00fa\5 \21\2\u00fa-\3\2\2\2\u00fb\u00fc\7\25\2\2\u00fc\u00fd\7\26\2"+
		"\2\u00fd\u00fe\5\60\31\2\u00fe\u00ff\7\27\2\2\u00ff\u0100\7\4\2\2\u0100"+
		"\u0101\5\62\32\2\u0101\u0109\7\6\2\2\u0102\u0103\7\5\2\2\u0103\u0104\7"+
		"\4\2\2\u0104\u0105\5\62\32\2\u0105\u0106\7\6\2\2\u0106\u0108\3\2\2\2\u0107"+
		"\u0102\3\2\2\2\u0108\u010b\3\2\2\2\u0109\u0107\3\2\2\2\u0109\u010a\3\2"+
		"\2\2\u010a/\3\2\2\2\u010b\u0109\3\2\2\2\u010c\u0118\5 \21\2\u010d\u010e"+
		"\7\4\2\2\u010e\u0113\5 \21\2\u010f\u0110\7\5\2\2\u0110\u0112\5 \21\2\u0111"+
		"\u010f\3\2\2\2\u0112\u0115\3\2\2\2\u0113\u0111\3\2\2\2\u0113\u0114\3\2"+
		"\2\2\u0114\u0116\3\2\2\2\u0115\u0113\3\2\2\2\u0116\u0117\7\6\2\2\u0117"+
		"\u0119\3\2\2\2\u0118\u010d\3\2\2\2\u0118\u0119\3\2\2\2\u0119\61\3\2\2"+
		"\2\u011a\u011f\5\64\33\2\u011b\u011c\7\5\2\2\u011c\u011e\5\64\33\2\u011d"+
		"\u011b\3\2\2\2\u011e\u0121\3\2\2\2\u011f\u011d\3\2\2\2\u011f\u0120\3\2"+
		"\2\2\u0120\63\3\2\2\2\u0121\u011f\3\2\2\2\u0122\u0123\t\4\2\2\u0123\65"+
		"\3\2\2\2\358:FKQ\\gny\u0080\u008a\u009c\u00a4\u00a7\u00b4\u00b7\u00c3"+
		"\u00cf\u00da\u00e0\u00e5\u00ed\u00f0\u0109\u0113\u0118\u011f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}