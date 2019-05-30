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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, K_CREATE=7, K_TABLE=8, 
		K_PRIMARY=9, K_KEY=10, K_NOT=11, K_NULL=12, K_DROP=13, K_IF=14, K_EXISTS=15, 
		K_SHOW=16, K_INSERT=17, K_INTO=18, K_VALUES=19, IDENTIFIER=20, STRING_LITERAL=21, 
		INTEGER_LITERAL=22, NUMERIC_LITERAL=23, SPACES=24, UNEXPECTED_CHAR=25;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "K_CREATE", "K_TABLE", 
			"K_PRIMARY", "K_KEY", "K_NOT", "K_NULL", "K_DROP", "K_IF", "K_EXISTS", 
			"K_SHOW", "K_INSERT", "K_INTO", "K_VALUES", "IDENTIFIER", "STRING_LITERAL", 
			"INTEGER_LITERAL", "NUMERIC_LITERAL", "SPACES", "UNEXPECTED_CHAR", "DIGIT", 
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", 
			"O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'('", "','", "')'", "'+'", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, "K_CREATE", "K_TABLE", "K_PRIMARY", 
			"K_KEY", "K_NOT", "K_NULL", "K_DROP", "K_IF", "K_EXISTS", "K_SHOW", "K_INSERT", 
			"K_INTO", "K_VALUES", "IDENTIFIER", "STRING_LITERAL", "INTEGER_LITERAL", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\33\u015d\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23"+
		"\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25"+
		"\7\25\u00c5\n\25\f\25\16\25\u00c8\13\25\3\25\3\25\3\25\3\25\3\25\7\25"+
		"\u00cf\n\25\f\25\16\25\u00d2\13\25\3\25\3\25\3\25\7\25\u00d7\n\25\f\25"+
		"\16\25\u00da\13\25\3\25\3\25\3\25\7\25\u00df\n\25\f\25\16\25\u00e2\13"+
		"\25\5\25\u00e4\n\25\3\26\3\26\3\26\3\26\7\26\u00ea\n\26\f\26\16\26\u00ed"+
		"\13\26\3\26\3\26\3\27\6\27\u00f2\n\27\r\27\16\27\u00f3\3\30\6\30\u00f7"+
		"\n\30\r\30\16\30\u00f8\3\30\3\30\7\30\u00fd\n\30\f\30\16\30\u0100\13\30"+
		"\5\30\u0102\n\30\3\30\3\30\5\30\u0106\n\30\3\30\6\30\u0109\n\30\r\30\16"+
		"\30\u010a\5\30\u010d\n\30\3\30\3\30\6\30\u0111\n\30\r\30\16\30\u0112\3"+
		"\30\3\30\5\30\u0117\n\30\3\30\6\30\u011a\n\30\r\30\16\30\u011b\5\30\u011e"+
		"\n\30\5\30\u0120\n\30\3\31\3\31\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34"+
		"\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3"+
		"&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60"+
		"\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\2\2\66\3\3\5\4\7\5"+
		"\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\2\67\29\2;\2=\2?\2A\2C\2E\2G"+
		"\2I\2K\2M\2O\2Q\2S\2U\2W\2Y\2[\2]\2_\2a\2c\2e\2g\2i\2\3\2%\3\2$$\3\2b"+
		"b\3\2__\5\2C\\aac|\6\2\62;C\\aac|\3\2))\4\2--//\5\2\13\r\17\17\"\"\3\2"+
		"\62;\4\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4"+
		"\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSs"+
		"s\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2"+
		"\\\\||\2\u0158\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\3k\3\2\2\2\5m\3\2\2\2\7o\3\2"+
		"\2\2\tq\3\2\2\2\13s\3\2\2\2\ru\3\2\2\2\17w\3\2\2\2\21~\3\2\2\2\23\u0084"+
		"\3\2\2\2\25\u008c\3\2\2\2\27\u0090\3\2\2\2\31\u0094\3\2\2\2\33\u0099\3"+
		"\2\2\2\35\u009e\3\2\2\2\37\u00a1\3\2\2\2!\u00a8\3\2\2\2#\u00ad\3\2\2\2"+
		"%\u00b4\3\2\2\2\'\u00b9\3\2\2\2)\u00e3\3\2\2\2+\u00e5\3\2\2\2-\u00f1\3"+
		"\2\2\2/\u011f\3\2\2\2\61\u0121\3\2\2\2\63\u0125\3\2\2\2\65\u0127\3\2\2"+
		"\2\67\u0129\3\2\2\29\u012b\3\2\2\2;\u012d\3\2\2\2=\u012f\3\2\2\2?\u0131"+
		"\3\2\2\2A\u0133\3\2\2\2C\u0135\3\2\2\2E\u0137\3\2\2\2G\u0139\3\2\2\2I"+
		"\u013b\3\2\2\2K\u013d\3\2\2\2M\u013f\3\2\2\2O\u0141\3\2\2\2Q\u0143\3\2"+
		"\2\2S\u0145\3\2\2\2U\u0147\3\2\2\2W\u0149\3\2\2\2Y\u014b\3\2\2\2[\u014d"+
		"\3\2\2\2]\u014f\3\2\2\2_\u0151\3\2\2\2a\u0153\3\2\2\2c\u0155\3\2\2\2e"+
		"\u0157\3\2\2\2g\u0159\3\2\2\2i\u015b\3\2\2\2kl\7=\2\2l\4\3\2\2\2mn\7*"+
		"\2\2n\6\3\2\2\2op\7.\2\2p\b\3\2\2\2qr\7+\2\2r\n\3\2\2\2st\7-\2\2t\f\3"+
		"\2\2\2uv\7/\2\2v\16\3\2\2\2wx\5;\36\2xy\5Y-\2yz\5? \2z{\5\67\34\2{|\5"+
		"]/\2|}\5? \2}\20\3\2\2\2~\177\5]/\2\177\u0080\5\67\34\2\u0080\u0081\5"+
		"9\35\2\u0081\u0082\5M\'\2\u0082\u0083\5? \2\u0083\22\3\2\2\2\u0084\u0085"+
		"\5U+\2\u0085\u0086\5Y-\2\u0086\u0087\5G$\2\u0087\u0088\5O(\2\u0088\u0089"+
		"\5\67\34\2\u0089\u008a\5Y-\2\u008a\u008b\5g\64\2\u008b\24\3\2\2\2\u008c"+
		"\u008d\5K&\2\u008d\u008e\5? \2\u008e\u008f\5g\64\2\u008f\26\3\2\2\2\u0090"+
		"\u0091\5Q)\2\u0091\u0092\5S*\2\u0092\u0093\5]/\2\u0093\30\3\2\2\2\u0094"+
		"\u0095\5Q)\2\u0095\u0096\5_\60\2\u0096\u0097\5M\'\2\u0097\u0098\5M\'\2"+
		"\u0098\32\3\2\2\2\u0099\u009a\5=\37\2\u009a\u009b\5Y-\2\u009b\u009c\5"+
		"S*\2\u009c\u009d\5U+\2\u009d\34\3\2\2\2\u009e\u009f\5G$\2\u009f\u00a0"+
		"\5A!\2\u00a0\36\3\2\2\2\u00a1\u00a2\5? \2\u00a2\u00a3\5e\63\2\u00a3\u00a4"+
		"\5G$\2\u00a4\u00a5\5[.\2\u00a5\u00a6\5]/\2\u00a6\u00a7\5[.\2\u00a7 \3"+
		"\2\2\2\u00a8\u00a9\5[.\2\u00a9\u00aa\5E#\2\u00aa\u00ab\5S*\2\u00ab\u00ac"+
		"\5c\62\2\u00ac\"\3\2\2\2\u00ad\u00ae\5G$\2\u00ae\u00af\5Q)\2\u00af\u00b0"+
		"\5[.\2\u00b0\u00b1\5? \2\u00b1\u00b2\5Y-\2\u00b2\u00b3\5]/\2\u00b3$\3"+
		"\2\2\2\u00b4\u00b5\5G$\2\u00b5\u00b6\5Q)\2\u00b6\u00b7\5]/\2\u00b7\u00b8"+
		"\5S*\2\u00b8&\3\2\2\2\u00b9\u00ba\5a\61\2\u00ba\u00bb\5\67\34\2\u00bb"+
		"\u00bc\5M\'\2\u00bc\u00bd\5_\60\2\u00bd\u00be\5? \2\u00be\u00bf\5[.\2"+
		"\u00bf(\3\2\2\2\u00c0\u00c6\7$\2\2\u00c1\u00c5\n\2\2\2\u00c2\u00c3\7$"+
		"\2\2\u00c3\u00c5\7$\2\2\u00c4\u00c1\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5"+
		"\u00c8\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c9\3\2"+
		"\2\2\u00c8\u00c6\3\2\2\2\u00c9\u00e4\7$\2\2\u00ca\u00d0\7b\2\2\u00cb\u00cf"+
		"\n\3\2\2\u00cc\u00cd\7b\2\2\u00cd\u00cf\7b\2\2\u00ce\u00cb\3\2\2\2\u00ce"+
		"\u00cc\3\2\2\2\u00cf\u00d2\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d0\u00d1\3\2"+
		"\2\2\u00d1\u00d3\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d3\u00e4\7b\2\2\u00d4"+
		"\u00d8\7]\2\2\u00d5\u00d7\n\4\2\2\u00d6\u00d5\3\2\2\2\u00d7\u00da\3\2"+
		"\2\2\u00d8\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00db\3\2\2\2\u00da"+
		"\u00d8\3\2\2\2\u00db\u00e4\7_\2\2\u00dc\u00e0\t\5\2\2\u00dd\u00df\t\6"+
		"\2\2\u00de\u00dd\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0"+
		"\u00e1\3\2\2\2\u00e1\u00e4\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3\u00c0\3\2"+
		"\2\2\u00e3\u00ca\3\2\2\2\u00e3\u00d4\3\2\2\2\u00e3\u00dc\3\2\2\2\u00e4"+
		"*\3\2\2\2\u00e5\u00eb\7)\2\2\u00e6\u00ea\n\7\2\2\u00e7\u00e8\7)\2\2\u00e8"+
		"\u00ea\7)\2\2\u00e9\u00e6\3\2\2\2\u00e9\u00e7\3\2\2\2\u00ea\u00ed\3\2"+
		"\2\2\u00eb\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ee\3\2\2\2\u00ed"+
		"\u00eb\3\2\2\2\u00ee\u00ef\7)\2\2\u00ef,\3\2\2\2\u00f0\u00f2\5\65\33\2"+
		"\u00f1\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4"+
		"\3\2\2\2\u00f4.\3\2\2\2\u00f5\u00f7\5\65\33\2\u00f6\u00f5\3\2\2\2\u00f7"+
		"\u00f8\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u0101\3\2"+
		"\2\2\u00fa\u00fe\7\60\2\2\u00fb\u00fd\5\65\33\2\u00fc\u00fb\3\2\2\2\u00fd"+
		"\u0100\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0102\3\2"+
		"\2\2\u0100\u00fe\3\2\2\2\u0101\u00fa\3\2\2\2\u0101\u0102\3\2\2\2\u0102"+
		"\u010c\3\2\2\2\u0103\u0105\5? \2\u0104\u0106\t\b\2\2\u0105\u0104\3\2\2"+
		"\2\u0105\u0106\3\2\2\2\u0106\u0108\3\2\2\2\u0107\u0109\5\65\33\2\u0108"+
		"\u0107\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u0108\3\2\2\2\u010a\u010b\3\2"+
		"\2\2\u010b\u010d\3\2\2\2\u010c\u0103\3\2\2\2\u010c\u010d\3\2\2\2\u010d"+
		"\u0120\3\2\2\2\u010e\u0110\7\60\2\2\u010f\u0111\5\65\33\2\u0110\u010f"+
		"\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0110\3\2\2\2\u0112\u0113\3\2\2\2\u0113"+
		"\u011d\3\2\2\2\u0114\u0116\5? \2\u0115\u0117\t\b\2\2\u0116\u0115\3\2\2"+
		"\2\u0116\u0117\3\2\2\2\u0117\u0119\3\2\2\2\u0118\u011a\5\65\33\2\u0119"+
		"\u0118\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u0119\3\2\2\2\u011b\u011c\3\2"+
		"\2\2\u011c\u011e\3\2\2\2\u011d\u0114\3\2\2\2\u011d\u011e\3\2\2\2\u011e"+
		"\u0120\3\2\2\2\u011f\u00f6\3\2\2\2\u011f\u010e\3\2\2\2\u0120\60\3\2\2"+
		"\2\u0121\u0122\t\t\2\2\u0122\u0123\3\2\2\2\u0123\u0124\b\31\2\2\u0124"+
		"\62\3\2\2\2\u0125\u0126\13\2\2\2\u0126\64\3\2\2\2\u0127\u0128\t\n\2\2"+
		"\u0128\66\3\2\2\2\u0129\u012a\t\13\2\2\u012a8\3\2\2\2\u012b\u012c\t\f"+
		"\2\2\u012c:\3\2\2\2\u012d\u012e\t\r\2\2\u012e<\3\2\2\2\u012f\u0130\t\16"+
		"\2\2\u0130>\3\2\2\2\u0131\u0132\t\17\2\2\u0132@\3\2\2\2\u0133\u0134\t"+
		"\20\2\2\u0134B\3\2\2\2\u0135\u0136\t\21\2\2\u0136D\3\2\2\2\u0137\u0138"+
		"\t\22\2\2\u0138F\3\2\2\2\u0139\u013a\t\23\2\2\u013aH\3\2\2\2\u013b\u013c"+
		"\t\24\2\2\u013cJ\3\2\2\2\u013d\u013e\t\25\2\2\u013eL\3\2\2\2\u013f\u0140"+
		"\t\26\2\2\u0140N\3\2\2\2\u0141\u0142\t\27\2\2\u0142P\3\2\2\2\u0143\u0144"+
		"\t\30\2\2\u0144R\3\2\2\2\u0145\u0146\t\31\2\2\u0146T\3\2\2\2\u0147\u0148"+
		"\t\32\2\2\u0148V\3\2\2\2\u0149\u014a\t\33\2\2\u014aX\3\2\2\2\u014b\u014c"+
		"\t\34\2\2\u014cZ\3\2\2\2\u014d\u014e\t\35\2\2\u014e\\\3\2\2\2\u014f\u0150"+
		"\t\36\2\2\u0150^\3\2\2\2\u0151\u0152\t\37\2\2\u0152`\3\2\2\2\u0153\u0154"+
		"\t \2\2\u0154b\3\2\2\2\u0155\u0156\t!\2\2\u0156d\3\2\2\2\u0157\u0158\t"+
		"\"\2\2\u0158f\3\2\2\2\u0159\u015a\t#\2\2\u015ah\3\2\2\2\u015b\u015c\t"+
		"$\2\2\u015cj\3\2\2\2\30\2\u00c4\u00c6\u00ce\u00d0\u00d8\u00e0\u00e3\u00e9"+
		"\u00eb\u00f3\u00f8\u00fe\u0101\u0105\u010a\u010c\u0112\u0116\u011b\u011d"+
		"\u011f\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}