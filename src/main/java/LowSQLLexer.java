// Generated from D:/learn/LowSQL-Database-Engine/src/main/java\LowSQL.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LowSQLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, K_DATABASES=9, 
		K_CREATE=10, K_TABLE=11, K_PRIMARY=12, K_KEY=13, K_NOT=14, K_NULL=15, 
		K_DROP=16, K_IF=17, K_EXISTS=18, K_SHOW=19, K_INSERT=20, K_INTO=21, K_VALUES=22, 
		K_SELECT=23, K_FROM=24, K_WHERE=25, K_DELETE=26, K_UPDATE=27, K_SET=28, 
		K_JOIN=29, K_ON=30, K_DATABASE=31, K_USE=32, K_INDEX=33, LT=34, GT=35, 
		LE=36, GE=37, EQ=38, LG=39, IDENTIFIER=40, STRING_LITERAL=41, INTEGER_LITERAL=42, 
		NUMERIC_LITERAL=43, SPACES=44, UNEXPECTED_CHAR=45;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "K_DATABASES", 
			"K_CREATE", "K_TABLE", "K_PRIMARY", "K_KEY", "K_NOT", "K_NULL", "K_DROP", 
			"K_IF", "K_EXISTS", "K_SHOW", "K_INSERT", "K_INTO", "K_VALUES", "K_SELECT", 
			"K_FROM", "K_WHERE", "K_DELETE", "K_UPDATE", "K_SET", "K_JOIN", "K_ON", 
			"K_DATABASE", "K_USE", "K_INDEX", "LT", "GT", "LE", "GE", "EQ", "LG", 
			"IDENTIFIER", "STRING_LITERAL", "INTEGER_LITERAL", "NUMERIC_LITERAL", 
			"SPACES", "UNEXPECTED_CHAR", "DIGIT", "A", "B", "C", "D", "E", "F", "G", 
			"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", 
			"V", "W", "X", "Y", "Z"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'('", "','", "')'", "'.'", "'*'", "'+'", "'-'", null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, "'<'", 
			"'>'", "'<='", "'>='", "'='", "'<>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "K_DATABASES", 
			"K_CREATE", "K_TABLE", "K_PRIMARY", "K_KEY", "K_NOT", "K_NULL", "K_DROP", 
			"K_IF", "K_EXISTS", "K_SHOW", "K_INSERT", "K_INTO", "K_VALUES", "K_SELECT", 
			"K_FROM", "K_WHERE", "K_DELETE", "K_UPDATE", "K_SET", "K_JOIN", "K_ON", 
			"K_DATABASE", "K_USE", "K_INDEX", "LT", "GT", "LE", "GE", "EQ", "LG", 
			"IDENTIFIER", "STRING_LITERAL", "INTEGER_LITERAL", "NUMERIC_LITERAL", 
			"SPACES", "UNEXPECTED_CHAR"
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


	public LowSQLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "LowSQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2/\u01e7\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21"+
		"\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24"+
		"\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26"+
		"\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35"+
		"\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3"+
		" \3 \3 \3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3$\3$\3%\3%\3%\3&\3"+
		"&\3&\3\'\3\'\3(\3(\3(\3)\3)\3)\3)\7)\u0149\n)\f)\16)\u014c\13)\3)\3)\3"+
		")\3)\3)\7)\u0153\n)\f)\16)\u0156\13)\3)\3)\3)\7)\u015b\n)\f)\16)\u015e"+
		"\13)\3)\3)\3)\7)\u0163\n)\f)\16)\u0166\13)\5)\u0168\n)\3*\3*\3*\3*\7*"+
		"\u016e\n*\f*\16*\u0171\13*\3*\3*\3+\5+\u0176\n+\3+\6+\u0179\n+\r+\16+"+
		"\u017a\3,\5,\u017e\n,\3,\6,\u0181\n,\r,\16,\u0182\3,\3,\7,\u0187\n,\f"+
		",\16,\u018a\13,\5,\u018c\n,\3,\3,\5,\u0190\n,\3,\6,\u0193\n,\r,\16,\u0194"+
		"\5,\u0197\n,\3,\3,\6,\u019b\n,\r,\16,\u019c\3,\3,\5,\u01a1\n,\3,\6,\u01a4"+
		"\n,\r,\16,\u01a5\5,\u01a8\n,\5,\u01aa\n,\3-\3-\3-\3-\3.\3.\3/\3/\3\60"+
		"\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67"+
		"\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3=\3=\3>\3>\3?\3?\3@\3@\3A\3A\3B\3"+
		"B\3C\3C\3D\3D\3E\3E\3F\3F\3G\3G\3H\3H\3I\3I\2\2J\3\3\5\4\7\5\t\6\13\7"+
		"\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O"+
		")Q*S+U,W-Y.[/]\2_\2a\2c\2e\2g\2i\2k\2m\2o\2q\2s\2u\2w\2y\2{\2}\2\177\2"+
		"\u0081\2\u0083\2\u0085\2\u0087\2\u0089\2\u008b\2\u008d\2\u008f\2\u0091"+
		"\2\3\2%\3\2$$\3\2bb\3\2__\5\2C\\aac|\6\2\62;C\\aac|\3\2))\4\2--//\5\2"+
		"\13\r\17\17\"\"\3\2\62;\4\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4\2GGgg\4\2HH"+
		"hh\4\2IIii\4\2JJjj\4\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2"+
		"QQqq\4\2RRrr\4\2SSss\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4"+
		"\2ZZzz\4\2[[{{\4\2\\\\||\2\u01e4\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2"+
		"\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2"+
		"\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2"+
		"\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2"+
		"\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2"+
		"\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O"+
		"\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2"+
		"\2\2\3\u0093\3\2\2\2\5\u0095\3\2\2\2\7\u0097\3\2\2\2\t\u0099\3\2\2\2\13"+
		"\u009b\3\2\2\2\r\u009d\3\2\2\2\17\u009f\3\2\2\2\21\u00a1\3\2\2\2\23\u00a3"+
		"\3\2\2\2\25\u00ad\3\2\2\2\27\u00b4\3\2\2\2\31\u00ba\3\2\2\2\33\u00c2\3"+
		"\2\2\2\35\u00c6\3\2\2\2\37\u00ca\3\2\2\2!\u00cf\3\2\2\2#\u00d4\3\2\2\2"+
		"%\u00d7\3\2\2\2\'\u00de\3\2\2\2)\u00e3\3\2\2\2+\u00ea\3\2\2\2-\u00ef\3"+
		"\2\2\2/\u00f6\3\2\2\2\61\u00fd\3\2\2\2\63\u0102\3\2\2\2\65\u0108\3\2\2"+
		"\2\67\u010f\3\2\2\29\u0116\3\2\2\2;\u011a\3\2\2\2=\u011f\3\2\2\2?\u0122"+
		"\3\2\2\2A\u012b\3\2\2\2C\u012f\3\2\2\2E\u0135\3\2\2\2G\u0137\3\2\2\2I"+
		"\u0139\3\2\2\2K\u013c\3\2\2\2M\u013f\3\2\2\2O\u0141\3\2\2\2Q\u0167\3\2"+
		"\2\2S\u0169\3\2\2\2U\u0175\3\2\2\2W\u01a9\3\2\2\2Y\u01ab\3\2\2\2[\u01af"+
		"\3\2\2\2]\u01b1\3\2\2\2_\u01b3\3\2\2\2a\u01b5\3\2\2\2c\u01b7\3\2\2\2e"+
		"\u01b9\3\2\2\2g\u01bb\3\2\2\2i\u01bd\3\2\2\2k\u01bf\3\2\2\2m\u01c1\3\2"+
		"\2\2o\u01c3\3\2\2\2q\u01c5\3\2\2\2s\u01c7\3\2\2\2u\u01c9\3\2\2\2w\u01cb"+
		"\3\2\2\2y\u01cd\3\2\2\2{\u01cf\3\2\2\2}\u01d1\3\2\2\2\177\u01d3\3\2\2"+
		"\2\u0081\u01d5\3\2\2\2\u0083\u01d7\3\2\2\2\u0085\u01d9\3\2\2\2\u0087\u01db"+
		"\3\2\2\2\u0089\u01dd\3\2\2\2\u008b\u01df\3\2\2\2\u008d\u01e1\3\2\2\2\u008f"+
		"\u01e3\3\2\2\2\u0091\u01e5\3\2\2\2\u0093\u0094\7=\2\2\u0094\4\3\2\2\2"+
		"\u0095\u0096\7*\2\2\u0096\6\3\2\2\2\u0097\u0098\7.\2\2\u0098\b\3\2\2\2"+
		"\u0099\u009a\7+\2\2\u009a\n\3\2\2\2\u009b\u009c\7\60\2\2\u009c\f\3\2\2"+
		"\2\u009d\u009e\7,\2\2\u009e\16\3\2\2\2\u009f\u00a0\7-\2\2\u00a0\20\3\2"+
		"\2\2\u00a1\u00a2\7/\2\2\u00a2\22\3\2\2\2\u00a3\u00a4\5e\63\2\u00a4\u00a5"+
		"\5_\60\2\u00a5\u00a6\5\u0085C\2\u00a6\u00a7\5_\60\2\u00a7\u00a8\5a\61"+
		"\2\u00a8\u00a9\5_\60\2\u00a9\u00aa\5\u0083B\2\u00aa\u00ab\5g\64\2\u00ab"+
		"\u00ac\5\u0083B\2\u00ac\24\3\2\2\2\u00ad\u00ae\5c\62\2\u00ae\u00af\5\u0081"+
		"A\2\u00af\u00b0\5g\64\2\u00b0\u00b1\5_\60\2\u00b1\u00b2\5\u0085C\2\u00b2"+
		"\u00b3\5g\64\2\u00b3\26\3\2\2\2\u00b4\u00b5\5\u0085C\2\u00b5\u00b6\5_"+
		"\60\2\u00b6\u00b7\5a\61\2\u00b7\u00b8\5u;\2\u00b8\u00b9\5g\64\2\u00b9"+
		"\30\3\2\2\2\u00ba\u00bb\5}?\2\u00bb\u00bc\5\u0081A\2\u00bc\u00bd\5o8\2"+
		"\u00bd\u00be\5w<\2\u00be\u00bf\5_\60\2\u00bf\u00c0\5\u0081A\2\u00c0\u00c1"+
		"\5\u008fH\2\u00c1\32\3\2\2\2\u00c2\u00c3\5s:\2\u00c3\u00c4\5g\64\2\u00c4"+
		"\u00c5\5\u008fH\2\u00c5\34\3\2\2\2\u00c6\u00c7\5y=\2\u00c7\u00c8\5{>\2"+
		"\u00c8\u00c9\5\u0085C\2\u00c9\36\3\2\2\2\u00ca\u00cb\5y=\2\u00cb\u00cc"+
		"\5\u0087D\2\u00cc\u00cd\5u;\2\u00cd\u00ce\5u;\2\u00ce \3\2\2\2\u00cf\u00d0"+
		"\5e\63\2\u00d0\u00d1\5\u0081A\2\u00d1\u00d2\5{>\2\u00d2\u00d3\5}?\2\u00d3"+
		"\"\3\2\2\2\u00d4\u00d5\5o8\2\u00d5\u00d6\5i\65\2\u00d6$\3\2\2\2\u00d7"+
		"\u00d8\5g\64\2\u00d8\u00d9\5\u008dG\2\u00d9\u00da\5o8\2\u00da\u00db\5"+
		"\u0083B\2\u00db\u00dc\5\u0085C\2\u00dc\u00dd\5\u0083B\2\u00dd&\3\2\2\2"+
		"\u00de\u00df\5\u0083B\2\u00df\u00e0\5m\67\2\u00e0\u00e1\5{>\2\u00e1\u00e2"+
		"\5\u008bF\2\u00e2(\3\2\2\2\u00e3\u00e4\5o8\2\u00e4\u00e5\5y=\2\u00e5\u00e6"+
		"\5\u0083B\2\u00e6\u00e7\5g\64\2\u00e7\u00e8\5\u0081A\2\u00e8\u00e9\5\u0085"+
		"C\2\u00e9*\3\2\2\2\u00ea\u00eb\5o8\2\u00eb\u00ec\5y=\2\u00ec\u00ed\5\u0085"+
		"C\2\u00ed\u00ee\5{>\2\u00ee,\3\2\2\2\u00ef\u00f0\5\u0089E\2\u00f0\u00f1"+
		"\5_\60\2\u00f1\u00f2\5u;\2\u00f2\u00f3\5\u0087D\2\u00f3\u00f4\5g\64\2"+
		"\u00f4\u00f5\5\u0083B\2\u00f5.\3\2\2\2\u00f6\u00f7\5\u0083B\2\u00f7\u00f8"+
		"\5g\64\2\u00f8\u00f9\5u;\2\u00f9\u00fa\5g\64\2\u00fa\u00fb\5c\62\2\u00fb"+
		"\u00fc\5\u0085C\2\u00fc\60\3\2\2\2\u00fd\u00fe\5i\65\2\u00fe\u00ff\5\u0081"+
		"A\2\u00ff\u0100\5{>\2\u0100\u0101\5w<\2\u0101\62\3\2\2\2\u0102\u0103\5"+
		"\u008bF\2\u0103\u0104\5m\67\2\u0104\u0105\5g\64\2\u0105\u0106\5\u0081"+
		"A\2\u0106\u0107\5g\64\2\u0107\64\3\2\2\2\u0108\u0109\5e\63\2\u0109\u010a"+
		"\5g\64\2\u010a\u010b\5u;\2\u010b\u010c\5g\64\2\u010c\u010d\5\u0085C\2"+
		"\u010d\u010e\5g\64\2\u010e\66\3\2\2\2\u010f\u0110\5\u0087D\2\u0110\u0111"+
		"\5}?\2\u0111\u0112\5e\63\2\u0112\u0113\5_\60\2\u0113\u0114\5\u0085C\2"+
		"\u0114\u0115\5g\64\2\u01158\3\2\2\2\u0116\u0117\5\u0083B\2\u0117\u0118"+
		"\5g\64\2\u0118\u0119\5\u0085C\2\u0119:\3\2\2\2\u011a\u011b\5q9\2\u011b"+
		"\u011c\5{>\2\u011c\u011d\5o8\2\u011d\u011e\5y=\2\u011e<\3\2\2\2\u011f"+
		"\u0120\5{>\2\u0120\u0121\5y=\2\u0121>\3\2\2\2\u0122\u0123\5e\63\2\u0123"+
		"\u0124\5_\60\2\u0124\u0125\5\u0085C\2\u0125\u0126\5_\60\2\u0126\u0127"+
		"\5a\61\2\u0127\u0128\5_\60\2\u0128\u0129\5\u0083B\2\u0129\u012a\5g\64"+
		"\2\u012a@\3\2\2\2\u012b\u012c\5\u0087D\2\u012c\u012d\5\u0083B\2\u012d"+
		"\u012e\5g\64\2\u012eB\3\2\2\2\u012f\u0130\5o8\2\u0130\u0131\5y=\2\u0131"+
		"\u0132\5e\63\2\u0132\u0133\5g\64\2\u0133\u0134\5\u008dG\2\u0134D\3\2\2"+
		"\2\u0135\u0136\7>\2\2\u0136F\3\2\2\2\u0137\u0138\7@\2\2\u0138H\3\2\2\2"+
		"\u0139\u013a\7>\2\2\u013a\u013b\7?\2\2\u013bJ\3\2\2\2\u013c\u013d\7@\2"+
		"\2\u013d\u013e\7?\2\2\u013eL\3\2\2\2\u013f\u0140\7?\2\2\u0140N\3\2\2\2"+
		"\u0141\u0142\7>\2\2\u0142\u0143\7@\2\2\u0143P\3\2\2\2\u0144\u014a\7$\2"+
		"\2\u0145\u0149\n\2\2\2\u0146\u0147\7$\2\2\u0147\u0149\7$\2\2\u0148\u0145"+
		"\3\2\2\2\u0148\u0146\3\2\2\2\u0149\u014c\3\2\2\2\u014a\u0148\3\2\2\2\u014a"+
		"\u014b\3\2\2\2\u014b\u014d\3\2\2\2\u014c\u014a\3\2\2\2\u014d\u0168\7$"+
		"\2\2\u014e\u0154\7b\2\2\u014f\u0153\n\3\2\2\u0150\u0151\7b\2\2\u0151\u0153"+
		"\7b\2\2\u0152\u014f\3\2\2\2\u0152\u0150\3\2\2\2\u0153\u0156\3\2\2\2\u0154"+
		"\u0152\3\2\2\2\u0154\u0155\3\2\2\2\u0155\u0157\3\2\2\2\u0156\u0154\3\2"+
		"\2\2\u0157\u0168\7b\2\2\u0158\u015c\7]\2\2\u0159\u015b\n\4\2\2\u015a\u0159"+
		"\3\2\2\2\u015b\u015e\3\2\2\2\u015c\u015a\3\2\2\2\u015c\u015d\3\2\2\2\u015d"+
		"\u015f\3\2\2\2\u015e\u015c\3\2\2\2\u015f\u0168\7_\2\2\u0160\u0164\t\5"+
		"\2\2\u0161\u0163\t\6\2\2\u0162\u0161\3\2\2\2\u0163\u0166\3\2\2\2\u0164"+
		"\u0162\3\2\2\2\u0164\u0165\3\2\2\2\u0165\u0168\3\2\2\2\u0166\u0164\3\2"+
		"\2\2\u0167\u0144\3\2\2\2\u0167\u014e\3\2\2\2\u0167\u0158\3\2\2\2\u0167"+
		"\u0160\3\2\2\2\u0168R\3\2\2\2\u0169\u016f\7)\2\2\u016a\u016e\n\7\2\2\u016b"+
		"\u016c\7)\2\2\u016c\u016e\7)\2\2\u016d\u016a\3\2\2\2\u016d\u016b\3\2\2"+
		"\2\u016e\u0171\3\2\2\2\u016f\u016d\3\2\2\2\u016f\u0170\3\2\2\2\u0170\u0172"+
		"\3\2\2\2\u0171\u016f\3\2\2\2\u0172\u0173\7)\2\2\u0173T\3\2\2\2\u0174\u0176"+
		"\7/\2\2\u0175\u0174\3\2\2\2\u0175\u0176\3\2\2\2\u0176\u0178\3\2\2\2\u0177"+
		"\u0179\5]/\2\u0178\u0177\3\2\2\2\u0179\u017a\3\2\2\2\u017a\u0178\3\2\2"+
		"\2\u017a\u017b\3\2\2\2\u017bV\3\2\2\2\u017c\u017e\7/\2\2\u017d\u017c\3"+
		"\2\2\2\u017d\u017e\3\2\2\2\u017e\u0180\3\2\2\2\u017f\u0181\5]/\2\u0180"+
		"\u017f\3\2\2\2\u0181\u0182\3\2\2\2\u0182\u0180\3\2\2\2\u0182\u0183\3\2"+
		"\2\2\u0183\u018b\3\2\2\2\u0184\u0188\7\60\2\2\u0185\u0187\5]/\2\u0186"+
		"\u0185\3\2\2\2\u0187\u018a\3\2\2\2\u0188\u0186\3\2\2\2\u0188\u0189\3\2"+
		"\2\2\u0189\u018c\3\2\2\2\u018a\u0188\3\2\2\2\u018b\u0184\3\2\2\2\u018b"+
		"\u018c\3\2\2\2\u018c\u0196\3\2\2\2\u018d\u018f\5g\64\2\u018e\u0190\t\b"+
		"\2\2\u018f\u018e\3\2\2\2\u018f\u0190\3\2\2\2\u0190\u0192\3\2\2\2\u0191"+
		"\u0193\5]/\2\u0192\u0191\3\2\2\2\u0193\u0194\3\2\2\2\u0194\u0192\3\2\2"+
		"\2\u0194\u0195\3\2\2\2\u0195\u0197\3\2\2\2\u0196\u018d\3\2\2\2\u0196\u0197"+
		"\3\2\2\2\u0197\u01aa\3\2\2\2\u0198\u019a\7\60\2\2\u0199\u019b\5]/\2\u019a"+
		"\u0199\3\2\2\2\u019b\u019c\3\2\2\2\u019c\u019a\3\2\2\2\u019c\u019d\3\2"+
		"\2\2\u019d\u01a7\3\2\2\2\u019e\u01a0\5g\64\2\u019f\u01a1\t\b\2\2\u01a0"+
		"\u019f\3\2\2\2\u01a0\u01a1\3\2\2\2\u01a1\u01a3\3\2\2\2\u01a2\u01a4\5]"+
		"/\2\u01a3\u01a2\3\2\2\2\u01a4\u01a5\3\2\2\2\u01a5\u01a3\3\2\2\2\u01a5"+
		"\u01a6\3\2\2\2\u01a6\u01a8\3\2\2\2\u01a7\u019e\3\2\2\2\u01a7\u01a8\3\2"+
		"\2\2\u01a8\u01aa\3\2\2\2\u01a9\u017d\3\2\2\2\u01a9\u0198\3\2\2\2\u01aa"+
		"X\3\2\2\2\u01ab\u01ac\t\t\2\2\u01ac\u01ad\3\2\2\2\u01ad\u01ae\b-\2\2\u01ae"+
		"Z\3\2\2\2\u01af\u01b0\13\2\2\2\u01b0\\\3\2\2\2\u01b1\u01b2\t\n\2\2\u01b2"+
		"^\3\2\2\2\u01b3\u01b4\t\13\2\2\u01b4`\3\2\2\2\u01b5\u01b6\t\f\2\2\u01b6"+
		"b\3\2\2\2\u01b7\u01b8\t\r\2\2\u01b8d\3\2\2\2\u01b9\u01ba\t\16\2\2\u01ba"+
		"f\3\2\2\2\u01bb\u01bc\t\17\2\2\u01bch\3\2\2\2\u01bd\u01be\t\20\2\2\u01be"+
		"j\3\2\2\2\u01bf\u01c0\t\21\2\2\u01c0l\3\2\2\2\u01c1\u01c2\t\22\2\2\u01c2"+
		"n\3\2\2\2\u01c3\u01c4\t\23\2\2\u01c4p\3\2\2\2\u01c5\u01c6\t\24\2\2\u01c6"+
		"r\3\2\2\2\u01c7\u01c8\t\25\2\2\u01c8t\3\2\2\2\u01c9\u01ca\t\26\2\2\u01ca"+
		"v\3\2\2\2\u01cb\u01cc\t\27\2\2\u01ccx\3\2\2\2\u01cd\u01ce\t\30\2\2\u01ce"+
		"z\3\2\2\2\u01cf\u01d0\t\31\2\2\u01d0|\3\2\2\2\u01d1\u01d2\t\32\2\2\u01d2"+
		"~\3\2\2\2\u01d3\u01d4\t\33\2\2\u01d4\u0080\3\2\2\2\u01d5\u01d6\t\34\2"+
		"\2\u01d6\u0082\3\2\2\2\u01d7\u01d8\t\35\2\2\u01d8\u0084\3\2\2\2\u01d9"+
		"\u01da\t\36\2\2\u01da\u0086\3\2\2\2\u01db\u01dc\t\37\2\2\u01dc\u0088\3"+
		"\2\2\2\u01dd\u01de\t \2\2\u01de\u008a\3\2\2\2\u01df\u01e0\t!\2\2\u01e0"+
		"\u008c\3\2\2\2\u01e1\u01e2\t\"\2\2\u01e2\u008e\3\2\2\2\u01e3\u01e4\t#"+
		"\2\2\u01e4\u0090\3\2\2\2\u01e5\u01e6\t$\2\2\u01e6\u0092\3\2\2\2\32\2\u0148"+
		"\u014a\u0152\u0154\u015c\u0164\u0167\u016d\u016f\u0175\u017a\u017d\u0182"+
		"\u0188\u018b\u018f\u0194\u0196\u019c\u01a0\u01a5\u01a7\u01a9\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}