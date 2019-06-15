grammar LowSQL;

parse
 : ( sql_stmt_list | error )* EOF
 ;

error
 : UNEXPECTED_CHAR
   {
     throw new RuntimeException("UNEXPECTED_CHAR=" + $UNEXPECTED_CHAR.text);
   }
 ;

sql_stmt_list
 : sql_stmt ( ';'+ sql_stmt )* ';'*
 ;

sql_stmt
 :  ( create_table_stmt
    | drop_table_stmt
    | show_table_stmt
    | insert_stmt
    | simple_select_stmt
    | complex_select_stmt
    | create_index_stmt
    | delete_stmt
    | update_stmt
    | create_database_stmt
    | drop_database_stmt
    | use_database_stmt
    | show_databases
    | show_table_in_database
    )
 ;
create_table_stmt
 : K_CREATE K_TABLE name ( '(' column_def ( ',' column_def )* ( ',' table_constraint )* ')')
 ;

show_table_in_database
 : K_SHOW K_DATABASE name
 ;

drop_database_stmt
 : K_DROP K_DATABASE name
  ;

create_index_stmt
 : K_CREATE K_INDEX K_ON name ( '(' name ( ',' name )* ')' )
 ;

show_databases
 : K_SHOW K_DATABASES
 ;

use_database_stmt
 : K_USE K_DATABASE name
 ;

create_database_stmt
 : K_CREATE K_DATABASE name
 ;

simple_select_stmt
 : K_SELECT attributes K_FROM name (K_WHERE compare_stmt)?
 ;

delete_stmt
 : K_DELETE K_FROM name
   (K_WHERE compare_stmt)?
 ;

update_stmt
 : K_UPDATE name
   K_SET name '=' literal_value ( K_WHERE compare_stmt )?
 ;

complex_select_stmt
 : K_SELECT two_attributes K_FROM name K_JOIN name K_ON name'.'name compare_symbol name'.'name ( K_WHERE compare_stmt )?
 ;
attributes
 : '*'
 | name (',' name)*
 ;

two_attributes
 : '*'
 | name'.'name (',' name'.'name)*
 ;

compare_stmt
: (name|(name '.' name)) compare_symbol literal_value
;

compare_symbol
: (   LT
    | GT
    | LE
    | GE
    | EQ
    | LG)
;

drop_table_stmt
 : K_DROP K_TABLE ( K_IF K_EXISTS )? name
 ;

table_constraint
 : K_PRIMARY K_KEY '(' indexed_column ( ',' indexed_column )* ')'
 ;

name
 : IDENTIFIER
 | STRING_LITERAL
 | '(' name ')'
    ;

indexed_column
 : name
 ;

column_def
 : name type_name? column_constraint*
 ;

type_name
 : name ( '(' signed_number ')' )?
 ;

signed_number
 : ( '+' | '-' )? INTEGER_LITERAL
 ;

column_constraint
 : K_NOT K_NULL
  ;

show_table_stmt
 : K_SHOW K_TABLE name
;

insert_stmt
 : K_INSERT K_INTO table_schema
   ( K_VALUES '(' insert_one_data ')' ( ',' '(' insert_one_data ')' )*
   )
 ;

table_schema
: name ( '(' name ( ',' name )* ')' )?
;

insert_one_data
 : literal_value ( ',' literal_value )*
 ;



literal_value
 : NUMERIC_LITERAL
 | INTEGER_LITERAL
 | STRING_LITERAL
 | K_NULL
 ;

K_DATABASES : D A T A B A S E S;
K_CREATE : C R E A T E;
K_TABLE : T A B L E;
K_PRIMARY : P R I M A R Y;
K_KEY : K E Y;
K_NOT : N O T;
K_NULL : N U L L;
K_DROP : D R O P;
K_IF : I F;
K_EXISTS : E X I S T S;
K_SHOW : S H O W;
K_INSERT : I N S E R T;
K_INTO : I N T O;
K_VALUES: V A L U E S;
K_SELECT: S E L E C T;
K_FROM: F R O M;
K_WHERE: W H E R E;
K_DELETE : D E L E T E;
K_UPDATE: U P D A T E;
K_SET: S E T;
K_JOIN: J O I N;
K_ON: O N;
K_DATABASE : D A T A B A S E;
K_USE : U S E;
K_INDEX : I N D E X;

LT : '<';
GT : '>';
LE : '<=';
GE : '>=';
EQ : '=';
LG : '<>';

IDENTIFIER
 : '"' (~'"' | '""')* '"'
 | '`' (~'`' | '``')* '`'
 | '[' ~']'* ']'
 | [a-zA-Z_] [a-zA-Z_0-9]*;

STRING_LITERAL
 : '\'' ( ~'\'' | '\'\'' )* '\''
 ;

INTEGER_LITERAL
:'-'? DIGIT+
;

NUMERIC_LITERAL
 : '-'? DIGIT+ ( '.' DIGIT* )? ( E [-+]? DIGIT+ )?
 | '.' DIGIT+ ( E [-+]? DIGIT+ )?
 ;

SPACES
 : [ \u000B\t\r\n] -> channel(HIDDEN)
 ;

UNEXPECTED_CHAR
 : .
 ;



fragment DIGIT : [0-9];

fragment A : [aA];
fragment B : [bB];
fragment C : [cC];
fragment D : [dD];
fragment E : [eE];
fragment F : [fF];
fragment G : [gG];
fragment H : [hH];
fragment I : [iI];
fragment J : [jJ];
fragment K : [kK];
fragment L : [lL];
fragment M : [mM];
fragment N : [nN];
fragment O : [oO];
fragment P : [pP];
fragment Q : [qQ];
fragment R : [rR];
fragment S : [sS];
fragment T : [tT];
fragment U : [uU];
fragment V : [vV];
fragment W : [wW];
fragment X : [xX];
fragment Y : [yY];
fragment Z : [zZ];