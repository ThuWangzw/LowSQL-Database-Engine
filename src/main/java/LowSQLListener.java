// Generated from D:/learn/LowSQL-Database-Engine/src/main/java\LowSQL.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LowSQLParser}.
 */
public interface LowSQLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(LowSQLParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(LowSQLParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#error}.
	 * @param ctx the parse tree
	 */
	void enterError(LowSQLParser.ErrorContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#error}.
	 * @param ctx the parse tree
	 */
	void exitError(LowSQLParser.ErrorContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#sql_stmt_list}.
	 * @param ctx the parse tree
	 */
	void enterSql_stmt_list(LowSQLParser.Sql_stmt_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#sql_stmt_list}.
	 * @param ctx the parse tree
	 */
	void exitSql_stmt_list(LowSQLParser.Sql_stmt_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#sql_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSql_stmt(LowSQLParser.Sql_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#sql_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSql_stmt(LowSQLParser.Sql_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#create_table_stmt}.
	 * @param ctx the parse tree
	 */
	void enterCreate_table_stmt(LowSQLParser.Create_table_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#create_table_stmt}.
	 * @param ctx the parse tree
	 */
	void exitCreate_table_stmt(LowSQLParser.Create_table_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#show_table_in_database}.
	 * @param ctx the parse tree
	 */
	void enterShow_table_in_database(LowSQLParser.Show_table_in_databaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#show_table_in_database}.
	 * @param ctx the parse tree
	 */
	void exitShow_table_in_database(LowSQLParser.Show_table_in_databaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#drop_database_stmt}.
	 * @param ctx the parse tree
	 */
	void enterDrop_database_stmt(LowSQLParser.Drop_database_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#drop_database_stmt}.
	 * @param ctx the parse tree
	 */
	void exitDrop_database_stmt(LowSQLParser.Drop_database_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#show_databases}.
	 * @param ctx the parse tree
	 */
	void enterShow_databases(LowSQLParser.Show_databasesContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#show_databases}.
	 * @param ctx the parse tree
	 */
	void exitShow_databases(LowSQLParser.Show_databasesContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#use_database_stmt}.
	 * @param ctx the parse tree
	 */
	void enterUse_database_stmt(LowSQLParser.Use_database_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#use_database_stmt}.
	 * @param ctx the parse tree
	 */
	void exitUse_database_stmt(LowSQLParser.Use_database_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#create_database_stmt}.
	 * @param ctx the parse tree
	 */
	void enterCreate_database_stmt(LowSQLParser.Create_database_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#create_database_stmt}.
	 * @param ctx the parse tree
	 */
	void exitCreate_database_stmt(LowSQLParser.Create_database_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#simple_select_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSimple_select_stmt(LowSQLParser.Simple_select_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#simple_select_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSimple_select_stmt(LowSQLParser.Simple_select_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#delete_stmt}.
	 * @param ctx the parse tree
	 */
	void enterDelete_stmt(LowSQLParser.Delete_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#delete_stmt}.
	 * @param ctx the parse tree
	 */
	void exitDelete_stmt(LowSQLParser.Delete_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#update_stmt}.
	 * @param ctx the parse tree
	 */
	void enterUpdate_stmt(LowSQLParser.Update_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#update_stmt}.
	 * @param ctx the parse tree
	 */
	void exitUpdate_stmt(LowSQLParser.Update_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#complex_select_stmt}.
	 * @param ctx the parse tree
	 */
	void enterComplex_select_stmt(LowSQLParser.Complex_select_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#complex_select_stmt}.
	 * @param ctx the parse tree
	 */
	void exitComplex_select_stmt(LowSQLParser.Complex_select_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#attributes}.
	 * @param ctx the parse tree
	 */
	void enterAttributes(LowSQLParser.AttributesContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#attributes}.
	 * @param ctx the parse tree
	 */
	void exitAttributes(LowSQLParser.AttributesContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#two_attributes}.
	 * @param ctx the parse tree
	 */
	void enterTwo_attributes(LowSQLParser.Two_attributesContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#two_attributes}.
	 * @param ctx the parse tree
	 */
	void exitTwo_attributes(LowSQLParser.Two_attributesContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#compare_stmt}.
	 * @param ctx the parse tree
	 */
	void enterCompare_stmt(LowSQLParser.Compare_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#compare_stmt}.
	 * @param ctx the parse tree
	 */
	void exitCompare_stmt(LowSQLParser.Compare_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#compare_symbol}.
	 * @param ctx the parse tree
	 */
	void enterCompare_symbol(LowSQLParser.Compare_symbolContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#compare_symbol}.
	 * @param ctx the parse tree
	 */
	void exitCompare_symbol(LowSQLParser.Compare_symbolContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#drop_table_stmt}.
	 * @param ctx the parse tree
	 */
	void enterDrop_table_stmt(LowSQLParser.Drop_table_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#drop_table_stmt}.
	 * @param ctx the parse tree
	 */
	void exitDrop_table_stmt(LowSQLParser.Drop_table_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#table_constraint}.
	 * @param ctx the parse tree
	 */
	void enterTable_constraint(LowSQLParser.Table_constraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#table_constraint}.
	 * @param ctx the parse tree
	 */
	void exitTable_constraint(LowSQLParser.Table_constraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(LowSQLParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(LowSQLParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#indexed_column}.
	 * @param ctx the parse tree
	 */
	void enterIndexed_column(LowSQLParser.Indexed_columnContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#indexed_column}.
	 * @param ctx the parse tree
	 */
	void exitIndexed_column(LowSQLParser.Indexed_columnContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#column_def}.
	 * @param ctx the parse tree
	 */
	void enterColumn_def(LowSQLParser.Column_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#column_def}.
	 * @param ctx the parse tree
	 */
	void exitColumn_def(LowSQLParser.Column_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#type_name}.
	 * @param ctx the parse tree
	 */
	void enterType_name(LowSQLParser.Type_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#type_name}.
	 * @param ctx the parse tree
	 */
	void exitType_name(LowSQLParser.Type_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#signed_number}.
	 * @param ctx the parse tree
	 */
	void enterSigned_number(LowSQLParser.Signed_numberContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#signed_number}.
	 * @param ctx the parse tree
	 */
	void exitSigned_number(LowSQLParser.Signed_numberContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#column_constraint}.
	 * @param ctx the parse tree
	 */
	void enterColumn_constraint(LowSQLParser.Column_constraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#column_constraint}.
	 * @param ctx the parse tree
	 */
	void exitColumn_constraint(LowSQLParser.Column_constraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#show_table_stmt}.
	 * @param ctx the parse tree
	 */
	void enterShow_table_stmt(LowSQLParser.Show_table_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#show_table_stmt}.
	 * @param ctx the parse tree
	 */
	void exitShow_table_stmt(LowSQLParser.Show_table_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#insert_stmt}.
	 * @param ctx the parse tree
	 */
	void enterInsert_stmt(LowSQLParser.Insert_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#insert_stmt}.
	 * @param ctx the parse tree
	 */
	void exitInsert_stmt(LowSQLParser.Insert_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#table_schema}.
	 * @param ctx the parse tree
	 */
	void enterTable_schema(LowSQLParser.Table_schemaContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#table_schema}.
	 * @param ctx the parse tree
	 */
	void exitTable_schema(LowSQLParser.Table_schemaContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#insert_one_data}.
	 * @param ctx the parse tree
	 */
	void enterInsert_one_data(LowSQLParser.Insert_one_dataContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#insert_one_data}.
	 * @param ctx the parse tree
	 */
	void exitInsert_one_data(LowSQLParser.Insert_one_dataContext ctx);
	/**
	 * Enter a parse tree produced by {@link LowSQLParser#literal_value}.
	 * @param ctx the parse tree
	 */
	void enterLiteral_value(LowSQLParser.Literal_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link LowSQLParser#literal_value}.
	 * @param ctx the parse tree
	 */
	void exitLiteral_value(LowSQLParser.Literal_valueContext ctx);
}