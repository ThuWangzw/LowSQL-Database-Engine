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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, K_CREATE=8, K_TABLE=9, 
		K_PRIMARY=10, K_KEY=11, K_NOT=12, K_NULL=13, K_DROP=14, K_IF=15, K_EXISTS=16, 
		K_SHOW=17, K_INSERT=18, K_INTO=19, K_VALUES=20, K_SELECT=21, K_FROM=22, 
		K_WHERE=23, LT=24, GT=25, LE=26, GE=27, EQ=28, LG=29, IDENTIFIER=30, STRING_LITERAL=31, 
		INTEGER_LITERAL=32, NUMERIC_LITERAL=33, SPACES=34, UNEXPECTED_CHAR=35;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "K_CREATE", "K_TABLE", 
			"K_PRIMARY", "K_KEY", "K_NOT", "K_NULL", "K_DROP", "K_IF", "K_EXISTS", 
			"K_SHOW", "K_INSERT", "K_INTO", "K_VALUES", "K_SELECT", "K_FROM", "K_WHERE", 
			"LT", "GT", "LE", "GE", "EQ", "LG", "IDENTIFIER", "STRING_LITERAL", "INTEGER_LITERAL", 
			"NUMERIC_LITERAL", "SPACES", "UNEXPECTED_CHAR", "DIGIT", "A", "B", "C", 
			"D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", 
			"R", "S", "T", "U", "V", "W", "X", "Y", "Z"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'('", "','", "')'", "'*'", "'+'", "'-'", null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "'<'", "'>'", "'<='", "'>='", "'='", "'<>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, "K_CREATE", "K_TABLE", 
			"K_PRIMARY", "K_KEY", "K_NOT", "K_NULL", "K_DROP", "K_IF", "K_EXISTS", 
			"K_SHOW", "K_INSERT", "K_INTO", "K_VALUES", "K_SELECT", "K_FROM", "K_WHERE", 
			"LT", "GT", "LE", "GE", "EQ", "LG", "IDENTIFIER", "STRING_LITERAL", "INTEGER_LITERAL", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2%\u0194\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3"+
		"\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3"+
		"\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3"+
		"\30\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3\35\3"+
		"\35\3\36\3\36\3\36\3\37\3\37\3\37\3\37\7\37\u00fc\n\37\f\37\16\37\u00ff"+
		"\13\37\3\37\3\37\3\37\3\37\3\37\7\37\u0106\n\37\f\37\16\37\u0109\13\37"+
		"\3\37\3\37\3\37\7\37\u010e\n\37\f\37\16\37\u0111\13\37\3\37\3\37\3\37"+
		"\7\37\u0116\n\37\f\37\16\37\u0119\13\37\5\37\u011b\n\37\3 \3 \3 \3 \7"+
		" \u0121\n \f \16 \u0124\13 \3 \3 \3!\6!\u0129\n!\r!\16!\u012a\3\"\6\""+
		"\u012e\n\"\r\"\16\"\u012f\3\"\3\"\7\"\u0134\n\"\f\"\16\"\u0137\13\"\5"+
		"\"\u0139\n\"\3\"\3\"\5\"\u013d\n\"\3\"\6\"\u0140\n\"\r\"\16\"\u0141\5"+
		"\"\u0144\n\"\3\"\3\"\6\"\u0148\n\"\r\"\16\"\u0149\3\"\3\"\5\"\u014e\n"+
		"\"\3\"\6\"\u0151\n\"\r\"\16\"\u0152\5\"\u0155\n\"\5\"\u0157\n\"\3#\3#"+
		"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3"+
		".\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65"+
		"\3\66\3\66\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3=\3=\3>\3>\3?\3?\2"+
		"\2@\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36"+
		";\37= ?!A\"C#E$G%I\2K\2M\2O\2Q\2S\2U\2W\2Y\2[\2]\2_\2a\2c\2e\2g\2i\2k"+
		"\2m\2o\2q\2s\2u\2w\2y\2{\2}\2\3\2%\3\2$$\3\2bb\3\2__\5\2C\\aac|\6\2\62"+
		";C\\aac|\3\2))\4\2--//\5\2\13\r\17\17\"\"\3\2\62;\4\2CCcc\4\2DDdd\4\2"+
		"EEee\4\2FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4\2KKkk\4\2LLll\4\2MMmm\4"+
		"\2NNnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSss\4\2TTtt\4\2UUuu\4\2VVv"+
		"v\4\2WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2\\\\||\2\u018f\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2"+
		"\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2"+
		"\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3"+
		"\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\3\177\3"+
		"\2\2\2\5\u0081\3\2\2\2\7\u0083\3\2\2\2\t\u0085\3\2\2\2\13\u0087\3\2\2"+
		"\2\r\u0089\3\2\2\2\17\u008b\3\2\2\2\21\u008d\3\2\2\2\23\u0094\3\2\2\2"+
		"\25\u009a\3\2\2\2\27\u00a2\3\2\2\2\31\u00a6\3\2\2\2\33\u00aa\3\2\2\2\35"+
		"\u00af\3\2\2\2\37\u00b4\3\2\2\2!\u00b7\3\2\2\2#\u00be\3\2\2\2%\u00c3\3"+
		"\2\2\2\'\u00ca\3\2\2\2)\u00cf\3\2\2\2+\u00d6\3\2\2\2-\u00dd\3\2\2\2/\u00e2"+
		"\3\2\2\2\61\u00e8\3\2\2\2\63\u00ea\3\2\2\2\65\u00ec\3\2\2\2\67\u00ef\3"+
		"\2\2\29\u00f2\3\2\2\2;\u00f4\3\2\2\2=\u011a\3\2\2\2?\u011c\3\2\2\2A\u0128"+
		"\3\2\2\2C\u0156\3\2\2\2E\u0158\3\2\2\2G\u015c\3\2\2\2I\u015e\3\2\2\2K"+
		"\u0160\3\2\2\2M\u0162\3\2\2\2O\u0164\3\2\2\2Q\u0166\3\2\2\2S\u0168\3\2"+
		"\2\2U\u016a\3\2\2\2W\u016c\3\2\2\2Y\u016e\3\2\2\2[\u0170\3\2\2\2]\u0172"+
		"\3\2\2\2_\u0174\3\2\2\2a\u0176\3\2\2\2c\u0178\3\2\2\2e\u017a\3\2\2\2g"+
		"\u017c\3\2\2\2i\u017e\3\2\2\2k\u0180\3\2\2\2m\u0182\3\2\2\2o\u0184\3\2"+
		"\2\2q\u0186\3\2\2\2s\u0188\3\2\2\2u\u018a\3\2\2\2w\u018c\3\2\2\2y\u018e"+
		"\3\2\2\2{\u0190\3\2\2\2}\u0192\3\2\2\2\177\u0080\7=\2\2\u0080\4\3\2\2"+
		"\2\u0081\u0082\7*\2\2\u0082\6\3\2\2\2\u0083\u0084\7.\2\2\u0084\b\3\2\2"+
		"\2\u0085\u0086\7+\2\2\u0086\n\3\2\2\2\u0087\u0088\7,\2\2\u0088\f\3\2\2"+
		"\2\u0089\u008a\7-\2\2\u008a\16\3\2\2\2\u008b\u008c\7/\2\2\u008c\20\3\2"+
		"\2\2\u008d\u008e\5O(\2\u008e\u008f\5m\67\2\u008f\u0090\5S*\2\u0090\u0091"+
		"\5K&\2\u0091\u0092\5q9\2\u0092\u0093\5S*\2\u0093\22\3\2\2\2\u0094\u0095"+
		"\5q9\2\u0095\u0096\5K&\2\u0096\u0097\5M\'\2\u0097\u0098\5a\61\2\u0098"+
		"\u0099\5S*\2\u0099\24\3\2\2\2\u009a\u009b\5i\65\2\u009b\u009c\5m\67\2"+
		"\u009c\u009d\5[.\2\u009d\u009e\5c\62\2\u009e\u009f\5K&\2\u009f\u00a0\5"+
		"m\67\2\u00a0\u00a1\5{>\2\u00a1\26\3\2\2\2\u00a2\u00a3\5_\60\2\u00a3\u00a4"+
		"\5S*\2\u00a4\u00a5\5{>\2\u00a5\30\3\2\2\2\u00a6\u00a7\5e\63\2\u00a7\u00a8"+
		"\5g\64\2\u00a8\u00a9\5q9\2\u00a9\32\3\2\2\2\u00aa\u00ab\5e\63\2\u00ab"+
		"\u00ac\5s:\2\u00ac\u00ad\5a\61\2\u00ad\u00ae\5a\61\2\u00ae\34\3\2\2\2"+
		"\u00af\u00b0\5Q)\2\u00b0\u00b1\5m\67\2\u00b1\u00b2\5g\64\2\u00b2\u00b3"+
		"\5i\65\2\u00b3\36\3\2\2\2\u00b4\u00b5\5[.\2\u00b5\u00b6\5U+\2\u00b6 \3"+
		"\2\2\2\u00b7\u00b8\5S*\2\u00b8\u00b9\5y=\2\u00b9\u00ba\5[.\2\u00ba\u00bb"+
		"\5o8\2\u00bb\u00bc\5q9\2\u00bc\u00bd\5o8\2\u00bd\"\3\2\2\2\u00be\u00bf"+
		"\5o8\2\u00bf\u00c0\5Y-\2\u00c0\u00c1\5g\64\2\u00c1\u00c2\5w<\2\u00c2$"+
		"\3\2\2\2\u00c3\u00c4\5[.\2\u00c4\u00c5\5e\63\2\u00c5\u00c6\5o8\2\u00c6"+
		"\u00c7\5S*\2\u00c7\u00c8\5m\67\2\u00c8\u00c9\5q9\2\u00c9&\3\2\2\2\u00ca"+
		"\u00cb\5[.\2\u00cb\u00cc\5e\63\2\u00cc\u00cd\5q9\2\u00cd\u00ce\5g\64\2"+
		"\u00ce(\3\2\2\2\u00cf\u00d0\5u;\2\u00d0\u00d1\5K&\2\u00d1\u00d2\5a\61"+
		"\2\u00d2\u00d3\5s:\2\u00d3\u00d4\5S*\2\u00d4\u00d5\5o8\2\u00d5*\3\2\2"+
		"\2\u00d6\u00d7\5o8\2\u00d7\u00d8\5S*\2\u00d8\u00d9\5a\61\2\u00d9\u00da"+
		"\5S*\2\u00da\u00db\5O(\2\u00db\u00dc\5q9\2\u00dc,\3\2\2\2\u00dd\u00de"+
		"\5U+\2\u00de\u00df\5m\67\2\u00df\u00e0\5g\64\2\u00e0\u00e1\5c\62\2\u00e1"+
		".\3\2\2\2\u00e2\u00e3\5w<\2\u00e3\u00e4\5Y-\2\u00e4\u00e5\5S*\2\u00e5"+
		"\u00e6\5m\67\2\u00e6\u00e7\5S*\2\u00e7\60\3\2\2\2\u00e8\u00e9\7>\2\2\u00e9"+
		"\62\3\2\2\2\u00ea\u00eb\7@\2\2\u00eb\64\3\2\2\2\u00ec\u00ed\7>\2\2\u00ed"+
		"\u00ee\7?\2\2\u00ee\66\3\2\2\2\u00ef\u00f0\7@\2\2\u00f0\u00f1\7?\2\2\u00f1"+
		"8\3\2\2\2\u00f2\u00f3\7?\2\2\u00f3:\3\2\2\2\u00f4\u00f5\7>\2\2\u00f5\u00f6"+
		"\7@\2\2\u00f6<\3\2\2\2\u00f7\u00fd\7$\2\2\u00f8\u00fc\n\2\2\2\u00f9\u00fa"+
		"\7$\2\2\u00fa\u00fc\7$\2\2\u00fb\u00f8\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fc"+
		"\u00ff\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u0100\3\2"+
		"\2\2\u00ff\u00fd\3\2\2\2\u0100\u011b\7$\2\2\u0101\u0107\7b\2\2\u0102\u0106"+
		"\n\3\2\2\u0103\u0104\7b\2\2\u0104\u0106\7b\2\2\u0105\u0102\3\2\2\2\u0105"+
		"\u0103\3\2\2\2\u0106\u0109\3\2\2\2\u0107\u0105\3\2\2\2\u0107\u0108\3\2"+
		"\2\2\u0108\u010a\3\2\2\2\u0109\u0107\3\2\2\2\u010a\u011b\7b\2\2\u010b"+
		"\u010f\7]\2\2\u010c\u010e\n\4\2\2\u010d\u010c\3\2\2\2\u010e\u0111\3\2"+
		"\2\2\u010f\u010d\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0112\3\2\2\2\u0111"+
		"\u010f\3\2\2\2\u0112\u011b\7_\2\2\u0113\u0117\t\5\2\2\u0114\u0116\t\6"+
		"\2\2\u0115\u0114\3\2\2\2\u0116\u0119\3\2\2\2\u0117\u0115\3\2\2\2\u0117"+
		"\u0118\3\2\2\2\u0118\u011b\3\2\2\2\u0119\u0117\3\2\2\2\u011a\u00f7\3\2"+
		"\2\2\u011a\u0101\3\2\2\2\u011a\u010b\3\2\2\2\u011a\u0113\3\2\2\2\u011b"+
		">\3\2\2\2\u011c\u0122\7)\2\2\u011d\u0121\n\7\2\2\u011e\u011f\7)\2\2\u011f"+
		"\u0121\7)\2\2\u0120\u011d\3\2\2\2\u0120\u011e\3\2\2\2\u0121\u0124\3\2"+
		"\2\2\u0122\u0120\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0125\3\2\2\2\u0124"+
		"\u0122\3\2\2\2\u0125\u0126\7)\2\2\u0126@\3\2\2\2\u0127\u0129\5I%\2\u0128"+
		"\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u0128\3\2\2\2\u012a\u012b\3\2"+
		"\2\2\u012bB\3\2\2\2\u012c\u012e\5I%\2\u012d\u012c\3\2\2\2\u012e\u012f"+
		"\3\2\2\2\u012f\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0138\3\2\2\2\u0131"+
		"\u0135\7\60\2\2\u0132\u0134\5I%\2\u0133\u0132\3\2\2\2\u0134\u0137\3\2"+
		"\2\2\u0135\u0133\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u0139\3\2\2\2\u0137"+
		"\u0135\3\2\2\2\u0138\u0131\3\2\2\2\u0138\u0139\3\2\2\2\u0139\u0143\3\2"+
		"\2\2\u013a\u013c\5S*\2\u013b\u013d\t\b\2\2\u013c\u013b\3\2\2\2\u013c\u013d"+
		"\3\2\2\2\u013d\u013f\3\2\2\2\u013e\u0140\5I%\2\u013f\u013e\3\2\2\2\u0140"+
		"\u0141\3\2\2\2\u0141\u013f\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0144\3\2"+
		"\2\2\u0143\u013a\3\2\2\2\u0143\u0144\3\2\2\2\u0144\u0157\3\2\2\2\u0145"+
		"\u0147\7\60\2\2\u0146\u0148\5I%\2\u0147\u0146\3\2\2\2\u0148\u0149\3\2"+
		"\2\2\u0149\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u0154\3\2\2\2\u014b"+
		"\u014d\5S*\2\u014c\u014e\t\b\2\2\u014d\u014c\3\2\2\2\u014d\u014e\3\2\2"+
		"\2\u014e\u0150\3\2\2\2\u014f\u0151\5I%\2\u0150\u014f\3\2\2\2\u0151\u0152"+
		"\3\2\2\2\u0152\u0150\3\2\2\2\u0152\u0153\3\2\2\2\u0153\u0155\3\2\2\2\u0154"+
		"\u014b\3\2\2\2\u0154\u0155\3\2\2\2\u0155\u0157\3\2\2\2\u0156\u012d\3\2"+
		"\2\2\u0156\u0145\3\2\2\2\u0157D\3\2\2\2\u0158\u0159\t\t\2\2\u0159\u015a"+
		"\3\2\2\2\u015a\u015b\b#\2\2\u015bF\3\2\2\2\u015c\u015d\13\2\2\2\u015d"+
		"H\3\2\2\2\u015e\u015f\t\n\2\2\u015fJ\3\2\2\2\u0160\u0161\t\13\2\2\u0161"+
		"L\3\2\2\2\u0162\u0163\t\f\2\2\u0163N\3\2\2\2\u0164\u0165\t\r\2\2\u0165"+
		"P\3\2\2\2\u0166\u0167\t\16\2\2\u0167R\3\2\2\2\u0168\u0169\t\17\2\2\u0169"+
		"T\3\2\2\2\u016a\u016b\t\20\2\2\u016bV\3\2\2\2\u016c\u016d\t\21\2\2\u016d"+
		"X\3\2\2\2\u016e\u016f\t\22\2\2\u016fZ\3\2\2\2\u0170\u0171\t\23\2\2\u0171"+
		"\\\3\2\2\2\u0172\u0173\t\24\2\2\u0173^\3\2\2\2\u0174\u0175\t\25\2\2\u0175"+
		"`\3\2\2\2\u0176\u0177\t\26\2\2\u0177b\3\2\2\2\u0178\u0179\t\27\2\2\u0179"+
		"d\3\2\2\2\u017a\u017b\t\30\2\2\u017bf\3\2\2\2\u017c\u017d\t\31\2\2\u017d"+
		"h\3\2\2\2\u017e\u017f\t\32\2\2\u017fj\3\2\2\2\u0180\u0181\t\33\2\2\u0181"+
		"l\3\2\2\2\u0182\u0183\t\34\2\2\u0183n\3\2\2\2\u0184\u0185\t\35\2\2\u0185"+
		"p\3\2\2\2\u0186\u0187\t\36\2\2\u0187r\3\2\2\2\u0188\u0189\t\37\2\2\u0189"+
		"t\3\2\2\2\u018a\u018b\t \2\2\u018bv\3\2\2\2\u018c\u018d\t!\2\2\u018dx"+
		"\3\2\2\2\u018e\u018f\t\"\2\2\u018fz\3\2\2\2\u0190\u0191\t#\2\2\u0191|"+
		"\3\2\2\2\u0192\u0193\t$\2\2\u0193~\3\2\2\2\30\2\u00fb\u00fd\u0105\u0107"+
		"\u010f\u0117\u011a\u0120\u0122\u012a\u012f\u0135\u0138\u013c\u0141\u0143"+
		"\u0149\u014d\u0152\u0154\u0156\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}