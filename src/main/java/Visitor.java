import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import javafx.scene.control.Tab;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Visitor extends LowSQLBaseVisitor {
    private Server server;
    private DatabaseManager current_database;
    private Visitor(){
        server = new Server();
        current_database = server.getOneDatabase("test");
    }
    public static void main(String[] args) throws Exception {
        try {
            File file = new File("command.sql");
            FileInputStream fileInputStream = new FileInputStream(file);
            ANTLRInputStream input = new ANTLRInputStream(fileInputStream);
            LowSQLLexer lexer = new LowSQLLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            LowSQLParser parser = new LowSQLParser(tokens);
            ParseTree tree = parser.parse();

            Visitor visitor = new Visitor();
            visitor.visit(tree);
            System.out.println(tree.toStringTree(parser));
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Object visitCreate_table_stmt(LowSQLParser.Create_table_stmtContext ctx) {
        List<ParseTree> nodes = ctx.children;
        String tablename = (String) this.visit(nodes.get(2));
        ArrayList<TableAttribute> attributes = new ArrayList<>();
        ArrayList<String> primary_keys = new ArrayList<>();
        for(int i=0; i<nodes.size(); i++){
            ParseTree node = nodes.get(i);
            if(node instanceof LowSQLParser.Column_defContext){
                TableAttribute attribute = (TableAttribute) this.visit(node);
                attribute.setTable_name(tablename);
                attributes.add(attribute);
            }
            if(node instanceof LowSQLParser.Table_constraintContext){
                primary_keys = (ArrayList<String>)this.visit(node);
            }
        }

        for(String key : primary_keys){
            Boolean find = false;
            for(TableAttribute attribute : attributes){
                if(attribute.getAttributeName().equals(key)){
                    attribute.setIs_primary_key(true);
                    find = true;
                    break;
                }
            }
            if(!find){
                throw new RuntimeException("primary key error, no attribute: "+key);
            }
        }
        Object[] attributesary = attributes.toArray();
        TableAttribute[] attributes1 = new TableAttribute[attributesary.length];
        for(int i=0; i<attributesary.length; i++){
            attributes1[i] = (TableAttribute)attributesary[i];
        }
        TableSchema schema = new TableSchema(tablename, attributes1);
        TableManager tableManager = new TableManager(current_database.getDatabaseName(), tablename, schema);
        current_database.addTable(tableManager);
//        add index
        return new String("Create table success.");
    }

    @Override
    public Object visitName(LowSQLParser.NameContext ctx) {
        List<ParseTree> nodes = ctx.children;
        return nodes.get(0).getText();
    }

    @Override
    public Object visitColumn_def(LowSQLParser.Column_defContext ctx) {
        List<ParseTree> nodes = ctx.children;
        String attribute = (String) visit(nodes.get(0));
        ArrayList<Integer> types = (ArrayList<Integer>) visit(nodes.get(1));

        if(nodes.size() == 3){
//            not null
        }
        return new TableAttribute(null ,attribute, (int)types.get(0), (int)types.get(1), false);
    }

    @Override
    public Object visitType_name(LowSQLParser.Type_nameContext ctx) {
        ArrayList<Integer> type = new ArrayList<>();
        List<ParseTree> nodes = ctx.children;
        String type_name = (String) visit(nodes.get(0));
        if(type_name.toLowerCase().equals("string") || type_name.toLowerCase().equals("varchar")){
            type.add(Util.VARCHAR);
            type.add((int)visit(nodes.get(2)));
        }
        else if(type_name.toLowerCase().equals(new String("int")) || type_name.toLowerCase().equals("integer")){
            type.add(Util.INT);
            type.add(0);
        }
        else if(type_name.toLowerCase().equals("float")){
            type.add(Util.FLOAT);
            type.add(0);
        }
        else if(type_name.toLowerCase().equals("double")){
            type.add(Util.DOUBLE);
            type.add(0);
        }
        else if(type_name.toLowerCase().equals("char")){
            type.add(Util.CHAR);
            type.add(0);
        }
        else {
            throw new RuntimeException("unrecognized type "+type_name);
        }
        return type;
    }

    @Override
    public Object visitTable_constraint(LowSQLParser.Table_constraintContext ctx) {
        List<ParseTree> nodes = ctx.children;
        ArrayList<String> keys = new ArrayList<>();
        for(ParseTree node : nodes){
            if(node instanceof LowSQLParser.Indexed_columnContext){
                keys.add((String) visit(node));
            }
        }
        return keys;
    }

    @Override
    public Object visitSigned_number(LowSQLParser.Signed_numberContext ctx) {
        return (int)Integer.parseInt((String) visit(ctx.children.get(0)));
    }

    @Override
    public Object visitTerminal(TerminalNode node) {
        return node.getSymbol().getText();
    }

    @Override
    public Object visitIndexed_column(LowSQLParser.Indexed_columnContext ctx) {
        return visit(ctx.children.get(0));
    }

    @Override
    public Object visitDrop_table_stmt(LowSQLParser.Drop_table_stmtContext ctx) {
        Boolean if_exists = false;
        LowSQLParser.NameContext namenode;
        if(ctx.children.size()==3){
            namenode = (LowSQLParser.NameContext) ctx.children.get(2);
        }else {
            namenode = (LowSQLParser.NameContext) ctx.children.get(4);
            if_exists = true;
        }
        String name = (String) visit(namenode);
        ArrayList<TableManager> tables = current_database.getTables();
        Boolean find = false;
        for(TableManager table : tables){
            if(table.getTableName().equals(name)){
                find = true;
                break;
            }
        }
        if(find){
            current_database.deleteTable(name);
            return new String("Drop table success.");
        }
        if(if_exists){
            return new String("Drop table success.");
        }
        else {
            throw new RuntimeException("no table named "+name);
        }
    }

    @Override
    public Object visitShow_table_stmt(LowSQLParser.Show_table_stmtContext ctx) {
        LowSQLParser.NameContext namenode = (LowSQLParser.NameContext) ctx.children.get(2);
        String name = (String) visit(namenode);
        ArrayList<TableManager> tables = current_database.getTables();
        TableManager showTable = null;
        for(TableManager table : tables){
            if(table.getTableName().equals(name)){
                showTable = table;
                break;
            }
        }
       if(showTable != null){
            String res = new String("");
            for(TableAttribute attribute : showTable.getSchema().getAttrubutes()){
                res += attribute.toString()+"\r\n";
            }
            return res;
       }
       else {
           throw new RuntimeException("no table named "+name);
       }
    }

    @Override
    public Object visitInsert_stmt(LowSQLParser.Insert_stmtContext ctx) {
        List<ParseTree> nodes = ctx.children;
        TableManager table = (TableManager)visit(nodes.get(2));
        return super.visitInsert_stmt(ctx);
    }
}
