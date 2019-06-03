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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, K_DATABASES=9, 
		K_CREATE=10, K_TABLE=11, K_PRIMARY=12, K_KEY=13, K_NOT=14, K_NULL=15, 
		K_DROP=16, K_IF=17, K_EXISTS=18, K_SHOW=19, K_INSERT=20, K_INTO=21, K_VALUES=22, 
		K_SELECT=23, K_FROM=24, K_WHERE=25, K_DELETE=26, K_UPDATE=27, K_SET=28, 
		K_JOIN=29, K_ON=30, K_DATABASE=31, K_USE=32, LT=33, GT=34, LE=35, GE=36, 
		EQ=37, LG=38, IDENTIFIER=39, STRING_LITERAL=40, INTEGER_LITERAL=41, NUMERIC_LITERAL=42, 
		SPACES=43, UNEXPECTED_CHAR=44;
	public static final int
		RULE_parse = 0, RULE_error = 1, RULE_sql_stmt_list = 2, RULE_sql_stmt = 3, 
		RULE_create_table_stmt = 4, RULE_show_table_in_database = 5, RULE_drop_database_stmt = 6, 
		RULE_show_databases = 7, RULE_use_database_stmt = 8, RULE_create_database_stmt = 9, 
		RULE_simple_select_stmt = 10, RULE_delete_stmt = 11, RULE_update_stmt = 12, 
		RULE_complex_select_stmt = 13, RULE_attributes = 14, RULE_two_attributes = 15, 
		RULE_compare_stmt = 16, RULE_compare_symbol = 17, RULE_drop_table_stmt = 18, 
		RULE_table_constraint = 19, RULE_name = 20, RULE_indexed_column = 21, 
		RULE_column_def = 22, RULE_type_name = 23, RULE_signed_number = 24, RULE_column_constraint = 25, 
		RULE_show_table_stmt = 26, RULE_insert_stmt = 27, RULE_table_schema = 28, 
		RULE_insert_one_data = 29, RULE_literal_value = 30;
	private static String[] makeRuleNames() {
		return new String[] {
			"parse", "error", "sql_stmt_list", "sql_stmt", "create_table_stmt", "show_table_in_database", 
			"drop_database_stmt", "show_databases", "use_database_stmt", "create_database_stmt", 
			"simple_select_stmt", "delete_stmt", "update_stmt", "complex_select_stmt", 
			"attributes", "two_attributes", "compare_stmt", "compare_symbol", "drop_table_stmt", 
			"table_constraint", "name", "indexed_column", "column_def", "type_name", 
			"signed_number", "column_constraint", "show_table_stmt", "insert_stmt", 
			"table_schema", "insert_one_data", "literal_value"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'('", "','", "')'", "'.'", "'*'", "'+'", "'-'", null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, "'<'", "'>'", 
			"'<='", "'>='", "'='", "'<>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "K_DATABASES", 
			"K_CREATE", "K_TABLE", "K_PRIMARY", "K_KEY", "K_NOT", "K_NULL", "K_DROP", 
			"K_IF", "K_EXISTS", "K_SHOW", "K_INSERT", "K_INTO", "K_VALUES", "K_SELECT", 
			"K_FROM", "K_WHERE", "K_DELETE", "K_UPDATE", "K_SET", "K_JOIN", "K_ON", 
			"K_DATABASE", "K_USE", "LT", "GT", "LE", "GE", "EQ", "LG", "IDENTIFIER", 
			"STRING_LITERAL", "INTEGER_LITERAL", "NUMERIC_LITERAL", "SPACES", "UNEXPECTED_CHAR"
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
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_CREATE) | (1L << K_DROP) | (1L << K_SHOW) | (1L << K_INSERT) | (1L << K_SELECT) | (1L << K_DELETE) | (1L << K_UPDATE) | (1L << K_USE) | (1L << UNEXPECTED_CHAR))) != 0)) {
				{
				setState(64);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case K_CREATE:
				case K_DROP:
				case K_SHOW:
				case K_INSERT:
				case K_SELECT:
				case K_DELETE:
				case K_UPDATE:
				case K_USE:
					{
					setState(62);
					sql_stmt_list();
					}
					break;
				case UNEXPECTED_CHAR:
					{
					setState(63);
					error();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(68);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(69);
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
			setState(71);
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
			setState(74);
			sql_stmt();
			setState(83);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(76); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(75);
						match(T__0);
						}
						}
						setState(78); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__0 );
					setState(80);
					sql_stmt();
					}
					} 
				}
				setState(85);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(86);
				match(T__0);
				}
				}
				setState(91);
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
		public Create_database_stmtContext create_database_stmt() {
			return getRuleContext(Create_database_stmtContext.class,0);
		}
		public Drop_database_stmtContext drop_database_stmt() {
			return getRuleContext(Drop_database_stmtContext.class,0);
		}
		public Use_database_stmtContext use_database_stmt() {
			return getRuleContext(Use_database_stmtContext.class,0);
		}
		public Show_databasesContext show_databases() {
			return getRuleContext(Show_databasesContext.class,0);
		}
		public Show_table_in_databaseContext show_table_in_database() {
			return getRuleContext(Show_table_in_databaseContext.class,0);
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
			setState(105);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(92);
				create_table_stmt();
				}
				break;
			case 2:
				{
				setState(93);
				drop_table_stmt();
				}
				break;
			case 3:
				{
				setState(94);
				show_table_stmt();
				}
				break;
			case 4:
				{
				setState(95);
				insert_stmt();
				}
				break;
			case 5:
				{
				setState(96);
				simple_select_stmt();
				}
				break;
			case 6:
				{
				setState(97);
				complex_select_stmt();
				}
				break;
			case 7:
				{
				setState(98);
				delete_stmt();
				}
				break;
			case 8:
				{
				setState(99);
				update_stmt();
				}
				break;
			case 9:
				{
				setState(100);
				create_database_stmt();
				}
				break;
			case 10:
				{
				setState(101);
				drop_database_stmt();
				}
				break;
			case 11:
				{
				setState(102);
				use_database_stmt();
				}
				break;
			case 12:
				{
				setState(103);
				show_databases();
				}
				break;
			case 13:
				{
				setState(104);
				show_table_in_database();
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
			setState(107);
			match(K_CREATE);
			setState(108);
			match(K_TABLE);
			setState(109);
			name();
			{
			setState(110);
			match(T__1);
			setState(111);
			column_def();
			setState(116);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(112);
					match(T__2);
					setState(113);
					column_def();
					}
					} 
				}
				setState(118);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(119);
				match(T__2);
				setState(120);
				table_constraint();
				}
				}
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(126);
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

	public static class Show_table_in_databaseContext extends ParserRuleContext {
		public TerminalNode K_SHOW() { return getToken(LowSQLParser.K_SHOW, 0); }
		public TerminalNode K_DATABASE() { return getToken(LowSQLParser.K_DATABASE, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Show_table_in_databaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_show_table_in_database; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterShow_table_in_database(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitShow_table_in_database(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitShow_table_in_database(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Show_table_in_databaseContext show_table_in_database() throws RecognitionException {
		Show_table_in_databaseContext _localctx = new Show_table_in_databaseContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_show_table_in_database);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			match(K_SHOW);
			setState(129);
			match(K_DATABASE);
			setState(130);
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

	public static class Drop_database_stmtContext extends ParserRuleContext {
		public TerminalNode K_DROP() { return getToken(LowSQLParser.K_DROP, 0); }
		public TerminalNode K_DATABASE() { return getToken(LowSQLParser.K_DATABASE, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Drop_database_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_drop_database_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterDrop_database_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitDrop_database_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitDrop_database_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Drop_database_stmtContext drop_database_stmt() throws RecognitionException {
		Drop_database_stmtContext _localctx = new Drop_database_stmtContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_drop_database_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			match(K_DROP);
			setState(133);
			match(K_DATABASE);
			setState(134);
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

	public static class Show_databasesContext extends ParserRuleContext {
		public TerminalNode K_SHOW() { return getToken(LowSQLParser.K_SHOW, 0); }
		public TerminalNode K_DATABASES() { return getToken(LowSQLParser.K_DATABASES, 0); }
		public Show_databasesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_show_databases; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterShow_databases(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitShow_databases(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitShow_databases(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Show_databasesContext show_databases() throws RecognitionException {
		Show_databasesContext _localctx = new Show_databasesContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_show_databases);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(K_SHOW);
			setState(137);
			match(K_DATABASES);
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

	public static class Use_database_stmtContext extends ParserRuleContext {
		public TerminalNode K_USE() { return getToken(LowSQLParser.K_USE, 0); }
		public TerminalNode K_DATABASE() { return getToken(LowSQLParser.K_DATABASE, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Use_database_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_use_database_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterUse_database_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitUse_database_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitUse_database_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Use_database_stmtContext use_database_stmt() throws RecognitionException {
		Use_database_stmtContext _localctx = new Use_database_stmtContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_use_database_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			match(K_USE);
			setState(140);
			match(K_DATABASE);
			setState(141);
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

	public static class Create_database_stmtContext extends ParserRuleContext {
		public TerminalNode K_CREATE() { return getToken(LowSQLParser.K_CREATE, 0); }
		public TerminalNode K_DATABASE() { return getToken(LowSQLParser.K_DATABASE, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Create_database_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_create_database_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).enterCreate_database_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LowSQLListener ) ((LowSQLListener)listener).exitCreate_database_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LowSQLVisitor ) return ((LowSQLVisitor<? extends T>)visitor).visitCreate_database_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Create_database_stmtContext create_database_stmt() throws RecognitionException {
		Create_database_stmtContext _localctx = new Create_database_stmtContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_create_database_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(K_CREATE);
			setState(144);
			match(K_DATABASE);
			setState(145);
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
		enterRule(_localctx, 20, RULE_simple_select_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			match(K_SELECT);
			setState(148);
			attributes();
			setState(149);
			match(K_FROM);
			setState(150);
			name();
			setState(153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(151);
				match(K_WHERE);
				setState(152);
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
		enterRule(_localctx, 22, RULE_delete_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(K_DELETE);
			setState(156);
			match(K_FROM);
			setState(157);
			name();
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(158);
				match(K_WHERE);
				setState(159);
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
		enterRule(_localctx, 24, RULE_update_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			match(K_UPDATE);
			setState(163);
			name();
			setState(164);
			match(K_SET);
			setState(165);
			name();
			setState(166);
			match(EQ);
			setState(167);
			literal_value();
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(168);
				match(K_WHERE);
				setState(169);
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
		enterRule(_localctx, 26, RULE_complex_select_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			match(K_SELECT);
			setState(173);
			two_attributes();
			setState(174);
			match(K_FROM);
			setState(175);
			name();
			setState(176);
			match(K_JOIN);
			setState(177);
			name();
			setState(178);
			match(K_ON);
			setState(179);
			name();
			setState(180);
			match(T__4);
			setState(181);
			name();
			setState(182);
			match(EQ);
			setState(183);
			name();
			setState(184);
			match(T__4);
			setState(185);
			name();
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(186);
				match(K_WHERE);
				setState(187);
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
		enterRule(_localctx, 28, RULE_attributes);
		int _la;
		try {
			setState(199);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(190);
				match(T__5);
				}
				break;
			case T__1:
			case IDENTIFIER:
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(191);
				name();
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(192);
					match(T__2);
					setState(193);
					name();
					}
					}
					setState(198);
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
		enterRule(_localctx, 30, RULE_two_attributes);
		int _la;
		try {
			setState(215);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(201);
				match(T__5);
				}
				break;
			case T__1:
			case IDENTIFIER:
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(202);
				name();
				setState(203);
				match(T__4);
				setState(204);
				name();
				setState(212);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(205);
					match(T__2);
					setState(206);
					name();
					setState(207);
					match(T__4);
					setState(208);
					name();
					}
					}
					setState(214);
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
		enterRule(_localctx, 32, RULE_compare_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			name();
			setState(218);
			compare_symbol();
			setState(219);
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
		enterRule(_localctx, 34, RULE_compare_symbol);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
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
		enterRule(_localctx, 36, RULE_drop_table_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			match(K_DROP);
			setState(224);
			match(K_TABLE);
			setState(227);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_IF) {
				{
				setState(225);
				match(K_IF);
				setState(226);
				match(K_EXISTS);
				}
			}

			setState(229);
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
		enterRule(_localctx, 38, RULE_table_constraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			match(K_PRIMARY);
			setState(232);
			match(K_KEY);
			setState(233);
			match(T__1);
			setState(234);
			indexed_column();
			setState(239);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(235);
				match(T__2);
				setState(236);
				indexed_column();
				}
				}
				setState(241);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(242);
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
		enterRule(_localctx, 40, RULE_name);
		try {
			setState(250);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(244);
				match(IDENTIFIER);
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(245);
				match(STRING_LITERAL);
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 3);
				{
				setState(246);
				match(T__1);
				setState(247);
				name();
				setState(248);
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
		enterRule(_localctx, 42, RULE_indexed_column);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
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
		enterRule(_localctx, 44, RULE_column_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			name();
			setState(256);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << IDENTIFIER) | (1L << STRING_LITERAL))) != 0)) {
				{
				setState(255);
				type_name();
				}
			}

			setState(261);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==K_NOT) {
				{
				{
				setState(258);
				column_constraint();
				}
				}
				setState(263);
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
		enterRule(_localctx, 46, RULE_type_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
			name();
			setState(269);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(265);
				match(T__1);
				setState(266);
				signed_number();
				setState(267);
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
		enterRule(_localctx, 48, RULE_signed_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6 || _la==T__7) {
				{
				setState(271);
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

			setState(274);
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
		enterRule(_localctx, 50, RULE_column_constraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			match(K_NOT);
			setState(277);
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
		enterRule(_localctx, 52, RULE_show_table_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
			match(K_SHOW);
			setState(280);
			match(K_TABLE);
			setState(281);
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
		enterRule(_localctx, 54, RULE_insert_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
			match(K_INSERT);
			setState(284);
			match(K_INTO);
			setState(285);
			table_schema();
			{
			setState(286);
			match(K_VALUES);
			setState(287);
			match(T__1);
			setState(288);
			insert_one_data();
			setState(289);
			match(T__3);
			setState(297);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(290);
				match(T__2);
				setState(291);
				match(T__1);
				setState(292);
				insert_one_data();
				setState(293);
				match(T__3);
				}
				}
				setState(299);
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
		enterRule(_localctx, 56, RULE_table_schema);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(300);
			name();
			setState(312);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(301);
				match(T__1);
				setState(302);
				name();
				setState(307);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(303);
					match(T__2);
					setState(304);
					name();
					}
					}
					setState(309);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(310);
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
		enterRule(_localctx, 58, RULE_insert_one_data);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(314);
			literal_value();
			setState(319);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(315);
				match(T__2);
				setState(316);
				literal_value();
				}
				}
				setState(321);
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
		enterRule(_localctx, 60, RULE_literal_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(322);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3.\u0147\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\3\2\7\2C\n\2\f\2\16\2F\13\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\6\4O\n\4\r\4"+
		"\16\4P\3\4\7\4T\n\4\f\4\16\4W\13\4\3\4\7\4Z\n\4\f\4\16\4]\13\4\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5l\n\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\7\6u\n\6\f\6\16\6x\13\6\3\6\3\6\7\6|\n\6\f\6\16\6\177\13"+
		"\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u009c\n\f\3\r\3\r\3\r"+
		"\3\r\3\r\5\r\u00a3\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00ad"+
		"\n\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\5\17\u00bf\n\17\3\20\3\20\3\20\3\20\7\20\u00c5\n\20\f"+
		"\20\16\20\u00c8\13\20\5\20\u00ca\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\7\21\u00d5\n\21\f\21\16\21\u00d8\13\21\5\21\u00da\n\21\3"+
		"\22\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\24\5\24\u00e6\n\24\3\24"+
		"\3\24\3\25\3\25\3\25\3\25\3\25\3\25\7\25\u00f0\n\25\f\25\16\25\u00f3\13"+
		"\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u00fd\n\26\3\27\3\27"+
		"\3\30\3\30\5\30\u0103\n\30\3\30\7\30\u0106\n\30\f\30\16\30\u0109\13\30"+
		"\3\31\3\31\3\31\3\31\3\31\5\31\u0110\n\31\3\32\5\32\u0113\n\32\3\32\3"+
		"\32\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3"+
		"\35\3\35\3\35\3\35\3\35\3\35\7\35\u012a\n\35\f\35\16\35\u012d\13\35\3"+
		"\36\3\36\3\36\3\36\3\36\7\36\u0134\n\36\f\36\16\36\u0137\13\36\3\36\3"+
		"\36\5\36\u013b\n\36\3\37\3\37\3\37\7\37\u0140\n\37\f\37\16\37\u0143\13"+
		"\37\3 \3 \3 \2\2!\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62"+
		"\64\668:<>\2\5\3\2#(\3\2\t\n\4\2\21\21*,\2\u014e\2D\3\2\2\2\4I\3\2\2\2"+
		"\6L\3\2\2\2\bk\3\2\2\2\nm\3\2\2\2\f\u0082\3\2\2\2\16\u0086\3\2\2\2\20"+
		"\u008a\3\2\2\2\22\u008d\3\2\2\2\24\u0091\3\2\2\2\26\u0095\3\2\2\2\30\u009d"+
		"\3\2\2\2\32\u00a4\3\2\2\2\34\u00ae\3\2\2\2\36\u00c9\3\2\2\2 \u00d9\3\2"+
		"\2\2\"\u00db\3\2\2\2$\u00df\3\2\2\2&\u00e1\3\2\2\2(\u00e9\3\2\2\2*\u00fc"+
		"\3\2\2\2,\u00fe\3\2\2\2.\u0100\3\2\2\2\60\u010a\3\2\2\2\62\u0112\3\2\2"+
		"\2\64\u0116\3\2\2\2\66\u0119\3\2\2\28\u011d\3\2\2\2:\u012e\3\2\2\2<\u013c"+
		"\3\2\2\2>\u0144\3\2\2\2@C\5\6\4\2AC\5\4\3\2B@\3\2\2\2BA\3\2\2\2CF\3\2"+
		"\2\2DB\3\2\2\2DE\3\2\2\2EG\3\2\2\2FD\3\2\2\2GH\7\2\2\3H\3\3\2\2\2IJ\7"+
		".\2\2JK\b\3\1\2K\5\3\2\2\2LU\5\b\5\2MO\7\3\2\2NM\3\2\2\2OP\3\2\2\2PN\3"+
		"\2\2\2PQ\3\2\2\2QR\3\2\2\2RT\5\b\5\2SN\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3"+
		"\2\2\2V[\3\2\2\2WU\3\2\2\2XZ\7\3\2\2YX\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\"+
		"\3\2\2\2\\\7\3\2\2\2][\3\2\2\2^l\5\n\6\2_l\5&\24\2`l\5\66\34\2al\58\35"+
		"\2bl\5\26\f\2cl\5\34\17\2dl\5\30\r\2el\5\32\16\2fl\5\24\13\2gl\5\16\b"+
		"\2hl\5\22\n\2il\5\20\t\2jl\5\f\7\2k^\3\2\2\2k_\3\2\2\2k`\3\2\2\2ka\3\2"+
		"\2\2kb\3\2\2\2kc\3\2\2\2kd\3\2\2\2ke\3\2\2\2kf\3\2\2\2kg\3\2\2\2kh\3\2"+
		"\2\2ki\3\2\2\2kj\3\2\2\2l\t\3\2\2\2mn\7\f\2\2no\7\r\2\2op\5*\26\2pq\7"+
		"\4\2\2qv\5.\30\2rs\7\5\2\2su\5.\30\2tr\3\2\2\2ux\3\2\2\2vt\3\2\2\2vw\3"+
		"\2\2\2w}\3\2\2\2xv\3\2\2\2yz\7\5\2\2z|\5(\25\2{y\3\2\2\2|\177\3\2\2\2"+
		"}{\3\2\2\2}~\3\2\2\2~\u0080\3\2\2\2\177}\3\2\2\2\u0080\u0081\7\6\2\2\u0081"+
		"\13\3\2\2\2\u0082\u0083\7\25\2\2\u0083\u0084\7!\2\2\u0084\u0085\5*\26"+
		"\2\u0085\r\3\2\2\2\u0086\u0087\7\22\2\2\u0087\u0088\7!\2\2\u0088\u0089"+
		"\5*\26\2\u0089\17\3\2\2\2\u008a\u008b\7\25\2\2\u008b\u008c\7\13\2\2\u008c"+
		"\21\3\2\2\2\u008d\u008e\7\"\2\2\u008e\u008f\7!\2\2\u008f\u0090\5*\26\2"+
		"\u0090\23\3\2\2\2\u0091\u0092\7\f\2\2\u0092\u0093\7!\2\2\u0093\u0094\5"+
		"*\26\2\u0094\25\3\2\2\2\u0095\u0096\7\31\2\2\u0096\u0097\5\36\20\2\u0097"+
		"\u0098\7\32\2\2\u0098\u009b\5*\26\2\u0099\u009a\7\33\2\2\u009a\u009c\5"+
		"\"\22\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\27\3\2\2\2\u009d"+
		"\u009e\7\34\2\2\u009e\u009f\7\32\2\2\u009f\u00a2\5*\26\2\u00a0\u00a1\7"+
		"\33\2\2\u00a1\u00a3\5\"\22\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3"+
		"\31\3\2\2\2\u00a4\u00a5\7\35\2\2\u00a5\u00a6\5*\26\2\u00a6\u00a7\7\36"+
		"\2\2\u00a7\u00a8\5*\26\2\u00a8\u00a9\7\'\2\2\u00a9\u00ac\5> \2\u00aa\u00ab"+
		"\7\33\2\2\u00ab\u00ad\5\"\22\2\u00ac\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2"+
		"\u00ad\33\3\2\2\2\u00ae\u00af\7\31\2\2\u00af\u00b0\5 \21\2\u00b0\u00b1"+
		"\7\32\2\2\u00b1\u00b2\5*\26\2\u00b2\u00b3\7\37\2\2\u00b3\u00b4\5*\26\2"+
		"\u00b4\u00b5\7 \2\2\u00b5\u00b6\5*\26\2\u00b6\u00b7\7\7\2\2\u00b7\u00b8"+
		"\5*\26\2\u00b8\u00b9\7\'\2\2\u00b9\u00ba\5*\26\2\u00ba\u00bb\7\7\2\2\u00bb"+
		"\u00be\5*\26\2\u00bc\u00bd\7\33\2\2\u00bd\u00bf\5\"\22\2\u00be\u00bc\3"+
		"\2\2\2\u00be\u00bf\3\2\2\2\u00bf\35\3\2\2\2\u00c0\u00ca\7\b\2\2\u00c1"+
		"\u00c6\5*\26\2\u00c2\u00c3\7\5\2\2\u00c3\u00c5\5*\26\2\u00c4\u00c2\3\2"+
		"\2\2\u00c5\u00c8\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7"+
		"\u00ca\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c9\u00c0\3\2\2\2\u00c9\u00c1\3\2"+
		"\2\2\u00ca\37\3\2\2\2\u00cb\u00da\7\b\2\2\u00cc\u00cd\5*\26\2\u00cd\u00ce"+
		"\7\7\2\2\u00ce\u00d6\5*\26\2\u00cf\u00d0\7\5\2\2\u00d0\u00d1\5*\26\2\u00d1"+
		"\u00d2\7\7\2\2\u00d2\u00d3\5*\26\2\u00d3\u00d5\3\2\2\2\u00d4\u00cf\3\2"+
		"\2\2\u00d5\u00d8\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7"+
		"\u00da\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d9\u00cb\3\2\2\2\u00d9\u00cc\3\2"+
		"\2\2\u00da!\3\2\2\2\u00db\u00dc\5*\26\2\u00dc\u00dd\5$\23\2\u00dd\u00de"+
		"\5> \2\u00de#\3\2\2\2\u00df\u00e0\t\2\2\2\u00e0%\3\2\2\2\u00e1\u00e2\7"+
		"\22\2\2\u00e2\u00e5\7\r\2\2\u00e3\u00e4\7\23\2\2\u00e4\u00e6\7\24\2\2"+
		"\u00e5\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e8"+
		"\5*\26\2\u00e8\'\3\2\2\2\u00e9\u00ea\7\16\2\2\u00ea\u00eb\7\17\2\2\u00eb"+
		"\u00ec\7\4\2\2\u00ec\u00f1\5,\27\2\u00ed\u00ee\7\5\2\2\u00ee\u00f0\5,"+
		"\27\2\u00ef\u00ed\3\2\2\2\u00f0\u00f3\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1"+
		"\u00f2\3\2\2\2\u00f2\u00f4\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f4\u00f5\7\6"+
		"\2\2\u00f5)\3\2\2\2\u00f6\u00fd\7)\2\2\u00f7\u00fd\7*\2\2\u00f8\u00f9"+
		"\7\4\2\2\u00f9\u00fa\5*\26\2\u00fa\u00fb\7\6\2\2\u00fb\u00fd\3\2\2\2\u00fc"+
		"\u00f6\3\2\2\2\u00fc\u00f7\3\2\2\2\u00fc\u00f8\3\2\2\2\u00fd+\3\2\2\2"+
		"\u00fe\u00ff\5*\26\2\u00ff-\3\2\2\2\u0100\u0102\5*\26\2\u0101\u0103\5"+
		"\60\31\2\u0102\u0101\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0107\3\2\2\2\u0104"+
		"\u0106\5\64\33\2\u0105\u0104\3\2\2\2\u0106\u0109\3\2\2\2\u0107\u0105\3"+
		"\2\2\2\u0107\u0108\3\2\2\2\u0108/\3\2\2\2\u0109\u0107\3\2\2\2\u010a\u010f"+
		"\5*\26\2\u010b\u010c\7\4\2\2\u010c\u010d\5\62\32\2\u010d\u010e\7\6\2\2"+
		"\u010e\u0110\3\2\2\2\u010f\u010b\3\2\2\2\u010f\u0110\3\2\2\2\u0110\61"+
		"\3\2\2\2\u0111\u0113\t\3\2\2\u0112\u0111\3\2\2\2\u0112\u0113\3\2\2\2\u0113"+
		"\u0114\3\2\2\2\u0114\u0115\7+\2\2\u0115\63\3\2\2\2\u0116\u0117\7\20\2"+
		"\2\u0117\u0118\7\21\2\2\u0118\65\3\2\2\2\u0119\u011a\7\25\2\2\u011a\u011b"+
		"\7\r\2\2\u011b\u011c\5*\26\2\u011c\67\3\2\2\2\u011d\u011e\7\26\2\2\u011e"+
		"\u011f\7\27\2\2\u011f\u0120\5:\36\2\u0120\u0121\7\30\2\2\u0121\u0122\7"+
		"\4\2\2\u0122\u0123\5<\37\2\u0123\u012b\7\6\2\2\u0124\u0125\7\5\2\2\u0125"+
		"\u0126\7\4\2\2\u0126\u0127\5<\37\2\u0127\u0128\7\6\2\2\u0128\u012a\3\2"+
		"\2\2\u0129\u0124\3\2\2\2\u012a\u012d\3\2\2\2\u012b\u0129\3\2\2\2\u012b"+
		"\u012c\3\2\2\2\u012c9\3\2\2\2\u012d\u012b\3\2\2\2\u012e\u013a\5*\26\2"+
		"\u012f\u0130\7\4\2\2\u0130\u0135\5*\26\2\u0131\u0132\7\5\2\2\u0132\u0134"+
		"\5*\26\2\u0133\u0131\3\2\2\2\u0134\u0137\3\2\2\2\u0135\u0133\3\2\2\2\u0135"+
		"\u0136\3\2\2\2\u0136\u0138\3\2\2\2\u0137\u0135\3\2\2\2\u0138\u0139\7\6"+
		"\2\2\u0139\u013b\3\2\2\2\u013a\u012f\3\2\2\2\u013a\u013b\3\2\2\2\u013b"+
		";\3\2\2\2\u013c\u0141\5> \2\u013d\u013e\7\5\2\2\u013e\u0140\5> \2\u013f"+
		"\u013d\3\2\2\2\u0140\u0143\3\2\2\2\u0141\u013f\3\2\2\2\u0141\u0142\3\2"+
		"\2\2\u0142=\3\2\2\2\u0143\u0141\3\2\2\2\u0144\u0145\t\4\2\2\u0145?\3\2"+
		"\2\2\35BDPU[kv}\u009b\u00a2\u00ac\u00be\u00c6\u00c9\u00d6\u00d9\u00e5"+
		"\u00f1\u00fc\u0102\u0107\u010f\u0112\u012b\u0135\u013a\u0141";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}