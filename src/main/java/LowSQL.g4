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
    )
 ;

//| delete_stmt
//    | select_stmt
//    | update_stmt
create_table_stmt
 : K_CREATE K_TABLE name ( '(' column_def ( ',' column_def )* ( ',' table_constraint )* ')')
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
 : name+ ( '(' signed_number ')'
         | '(' signed_number ',' signed_number ')' )?
 ;

signed_number
 : ( '+' | '-' )? NUMERIC_LITERAL
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
 | STRING_LITERAL
 | K_NULL
 ;

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


IDENTIFIER
 : '"' (~'"' | '""')* '"'
 | '`' (~'`' | '``')* '`'
 | '[' ~']'* ']'
 | [a-zA-Z_] [a-zA-Z_0-9]*;

STRING_LITERAL
 : '\'' ( ~'\'' | '\'\'' )* '\''
 ;

NUMERIC_LITERAL
 : DIGIT+ ( '.' DIGIT* )? ( E [-+]? DIGIT+ )?
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