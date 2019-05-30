import javafx.beans.binding.ObjectExpression;
import javafx.scene.control.Tab;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class Visitor extends LowSQLBaseVisitor {
    static private Server server;
    private DatabaseManager current_database;
    private Visitor(){
        server = new Server();
        current_database = server.getOneDatabase("test");
    }
    static private OutputStreamWriter writer;
    private TableManager current_table;
    public static void main(String[] args) throws Exception {
        writer = new OutputStreamWriter(System.out);
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
            server.data_buffer.saveAll();
            server.index_buffer.saveAll();
        }
        catch (RuntimeException e){
            writer.write(e.getMessage());
            writer.flush();
        }
        catch (IOException e){
            writer.write(e.getMessage());
            writer.flush();
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
        server.data_buffer.addDataStorage(tableManager);
        server.WriteMetaData();
//        add index
        if(primary_keys.size()>0){
            TableSchema primary_schema = tableManager.createIndexSchema(primary_keys);
            server.index_buffer.createIndex(current_database.getDatabaseName(), tablename, primary_schema, 10000);
            server.index_buffer.saveAll();
        }
        try {
            writer.write("Create table success.");
            writer.flush();
        }
        catch (IOException e){
            System.out.println("IO exception");
        }
        return null;
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
        boolean not_null = false;
        if(nodes.size() == 3){
//            not null
            not_null = true;
        }
        return new TableAttribute(null ,attribute, (int)types.get(0), (int)types.get(1), false, not_null);
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
            server.WriteMetaData();
            server.data_buffer.deleteDataFile(current_database.getDatabaseName(), current_table.getTableName());
            //TODO delete index
//            server.index_buffer.deleteIndex(current_database.getDatabaseName(), current_table.getTableName());

            try {
                writer.write("Drop table success.");
                writer.flush();
            }
            catch (IOException e){
                System.out.println("IO exception");
            }
            return null;
        }
        if(if_exists){
            try {
                writer.write("Drop table success.");
                writer.flush();
            }
            catch (IOException e){
                System.out.println("IO exception");
            }
            return null;
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
           try {
               writer.write(res);
           }
           catch (IOException e){
               System.out.println("IO exception");
           }
           return null;
       }
       else {
           throw new RuntimeException("no table named "+name);
       }
    }

    @Override
    public Object visitInsert_stmt(LowSQLParser.Insert_stmtContext ctx) {
        List<ParseTree> nodes = ctx.children;
        current_table = (TableManager)visit(nodes.get(2));
        for(int i=0; i<nodes.size(); i++){
            ParseTree node = nodes.get(i);
            if(node instanceof LowSQLParser.Insert_one_dataContext){
                Record record = (Record) visit(node);
                DataPointer dataPointer = server.data_buffer.getDataStorage(current_database.getDatabaseName(), current_table.getTableName()).insert(record);
                for(BTree bTree : server.index_buffer.getBTrees(current_database.getDatabaseName(), current_table.getTableName())){
                    bTree.insert(record, dataPointer.page_id, dataPointer.record_id);
                }
            }
        }
//        server.data_buffer.saveAll();
//        server.index_buffer.saveAll();
        try {
            writer.write("Insert table success.\r\n");
            writer.flush();
        }
        catch (IOException e){
            System.out.println("IO exception");
        }
        return null;
    }

    @Override
    public Object visitTable_schema(LowSQLParser.Table_schemaContext ctx) {
        List<ParseTree> nodes = ctx.children;
        String tableName = (String) visit(nodes.get(0));
//        check if table exists
        TableManager table = current_database.getOneTable(tableName);
        if(table==null){
            throw new RuntimeException("Table not exists");
        }
//        check if schema right
        for(int i=1;i<nodes.size(); i++){
            ParseTree node = nodes.get(i);

            if(node instanceof LowSQLParser.NameContext){
                String attributeName = (String)visit(node);
                TableAttribute attribute = table.getSchema().getOneAttribute(attributeName);
                if(attribute == null){
                    throw new RuntimeException("No attribute "+attributeName+" in table.");
                }
            }
        }
        return table;
    }

    @Override
    public Object visitInsert_one_data(LowSQLParser.Insert_one_dataContext ctx) {
        List<ParseTree> nodes = ctx.children;
//        ArrayList<Object> fields = new ArrayList<>();
        TableAttribute[] attributess = current_table.getSchema().getAttrubutes();
        Field[] fields = new Field[current_table.getSchema().getAttrubutes().length];
        int fieldIdx = 0;
        for(int i=0; i<nodes.size(); i++){
            ParseTree node = nodes.get(i);
            if(node instanceof LowSQLParser.Literal_valueContext){
//                fields.add(visit(node));
                if(fieldIdx==fields.length){
                    throw new RuntimeException("Redundant attributes.");
                }
                fields[fieldIdx] = new Field(visit(node), attributess[fieldIdx]);
                fieldIdx++;
            }
        }
        if(fieldIdx < attributess.length){
            //TODO: add is_null
//            for(int i=fieldIdx; i>attributess.length; i++){
//                if(attributess[i].)
//            }
//            String msg = new String("");
//            for(int i=fieldIdx; i<attributess.length; i++){
//                msg += attributess[i].getAttributeName()+", ";
//            }
//            throw new RuntimeException(msg+"can not be null.");
            for(int i=fieldIdx; i<attributess.length; i++){
                fields[i] = new Field(null, attributess[i]);
            }
        }

        return new Record(fields, current_table.getSchema());
    }

    @Override
    public Object visitLiteral_value(LowSQLParser.Literal_valueContext ctx) {
        String value = (String)visit(ctx.children.get(0));
        if(ctx.start.getType() == LowSQLParser.STRING_LITERAL){
            return value.substring(1, value.length()-1);
        }
        else if(ctx.start.getType() == LowSQLParser.NUMERIC_LITERAL){
            return Double.parseDouble(value);
        }
        else if(ctx.start.getType() == LowSQLParser.INTEGER_LITERAL){
            return Integer.parseInt(value);
        }
        else if(ctx.start.getType() == LowSQLParser.K_NULL){
            return null;
        }
        return null;
    }

    @Override
    public Object visitSimple_select_stmt(LowSQLParser.Simple_select_stmtContext ctx) {
        List<ParseTree> nodes = ctx.children;
//        check table exists
        String tableName = (String)visit(nodes.get(3));
        TableManager table = current_database.getOneTable(tableName);
        if(table == null){
            throw new RuntimeException("No table named "+tableName);
        }
        current_table = table;
//        get attributes list
        ArrayList<Integer> attributes = (ArrayList<Integer>)visit(nodes.get(1));
//          parse where
        _Query query = null;
        if(nodes.size() > 4){
            query = (_Query)visit(nodes.get(5));
        }
//        find if index
        if(query != null){
            TableAttribute index = current_table.getSchema().getOneAttribute(query.attributeName);
            TableAttribute[] _index = new TableAttribute[1];
            _index[0] = index;
            TableSchema indexSchema = new TableSchema(current_table.getTableName(), _index);

            BTree indexTree = server.index_buffer.getBTree(current_database.getDatabaseName(), current_table.getTableName(), indexSchema);
            if(indexTree != null){
//                search by index
                Field[] fields = new Field[1];
                fields[0] = new Field(query.value, index);
                Record target = new Record(fields, indexSchema);

            }
        }
        return super.visitSimple_select_stmt(ctx);
    }

    @Override
    public Object visitAttributes(LowSQLParser.AttributesContext ctx) {
        List<ParseTree> nodes = ctx.children;
        String firstname = (String)visit(nodes.get(0));
        ArrayList<Integer> attributes = new ArrayList<>();
        if(firstname.equals(new String("*"))){
            for(int i=0; i<current_table.getSchema().getAttrubutes().length; i++ ){
                attributes.add(new Integer(i));
            }
            return attributes;
        }

        TableAttribute[] tableAttributes = current_table.getSchema().getAttrubutes();
        for(ParseTree node:nodes){
            if(node instanceof LowSQLParser.NameContext){
                String attrName = (String)visit(node);
                boolean find = false;
                for(int i=0; i<tableAttributes.length; i++){
                    if(tableAttributes[i].getAttributeName().equals(attrName)){
                        find = true;
                        attributes.add(new Integer(i));
                        break;
                    }
                }
                if(!find){
                    throw new RuntimeException("Attribute error!");
                }
            }
        }
        return attributes;
    }

    @Override
    public Object visitCompare_stmt(LowSQLParser.Compare_stmtContext ctx) {
        List<ParseTree> nodes = ctx.children;
        String attributeName = (String)visit(nodes.get(0));
        Integer type = (Integer)visit(nodes.get(1));
        Object value = visit(nodes.get(2));
//        check attribute right
        TableAttribute tableAttribute = current_table.getSchema().getOneAttribute(attributeName);
        if(tableAttribute == null){
            throw new RuntimeException("Attribute error!");
        }
//        check attribute comparable
        if((type!=Util.E)&&(tableAttribute.getType() == Util.VARCHAR || tableAttribute.getType() == Util.STRING)){
            throw new RuntimeException("Attribute error!");
        }
        return new _Query(attributeName, (int)type, value);
    }

    @Override
    public Object visitCompare_symbol(LowSQLParser.Compare_symbolContext ctx) {
        if(ctx.getStart().getType() == LowSQLParser.EQ){
            return Util.E;
        }
        if(ctx.getStart().getType() == LowSQLParser.LT){
            return Util.L;
        }
        if(ctx.getStart().getType() == LowSQLParser.GT){
            return Util.G;
        }
        if(ctx.getStart().getType() == LowSQLParser.LE){
            return Util.LE;
        }
        if(ctx.getStart().getType() == LowSQLParser.GE){
            return Util.GE;
        }
        if(ctx.getStart().getType() == LowSQLParser.LG){
            return  Util.NE;
        }
        throw new RuntimeException("Invalid comparing");
    }
}

class _Query{
    public String attributeName;
    public int type;
    Object value;

    _Query(String attributeName, int type, Object value){
        this.attributeName = attributeName;
        this.type = type;
        this.value = value;
    }
}