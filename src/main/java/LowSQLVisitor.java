// Generated from D:/learn/LowSQL-Database-Engine/src/main/java\LowSQL.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LowSQLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LowSQLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LowSQLParser#parse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParse(LowSQLParser.ParseContext ctx);
	/**
	 * Visit a parse tree produced by {@link LowSQLParser#error}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitError(LowSQLParser.ErrorContext ctx);
	/**
	 * Visit a parse tree produced by {@link LowSQLParser#sql_stmt_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSql_stmt_list(LowSQLParser.Sql_stmt_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link LowSQLParser#sql_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSql_stmt(LowSQLParser.Sql_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LowSQLParser#create_table_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreate_table_stmt(LowSQLParser.Create_table_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LowSQLParser#drop_table_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDrop_table_stmt(LowSQLParser.Drop_table_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LowSQLParser#table_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_constraint(LowSQLParser.Table_constraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link LowSQLParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(LowSQLParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link LowSQLParser#indexed_column}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexed_column(LowSQLParser.Indexed_columnContext ctx);
	/**
	 * Visit a parse tree produced by {@link LowSQLParser#column_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_def(LowSQLParser.Column_defContext ctx);
	/**
	 * Visit a parse tree produced by {@link LowSQLParser#type_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_name(LowSQLParser.Type_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link LowSQLParser#signed_number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSigned_number(LowSQLParser.Signed_numberContext ctx);
	/**
	 * Visit a parse tree produced by {@link LowSQLParser#column_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_constraint(LowSQLParser.Column_constraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link LowSQLParser#show_table_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShow_table_stmt(LowSQLParser.Show_table_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LowSQLParser#insert_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsert_stmt(LowSQLParser.Insert_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LowSQLParser#table_schema}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_schema(LowSQLParser.Table_schemaContext ctx);
	/**
	 * Visit a parse tree produced by {@link LowSQLParser#insert_one_data}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsert_one_data(LowSQLParser.Insert_one_dataContext ctx);
	/**
	 * Visit a parse tree produced by {@link LowSQLParser#literal_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral_value(LowSQLParser.Literal_valueContext ctx);
}