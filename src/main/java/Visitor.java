import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.*;
import java.util.*;
public class Visitor extends LowSQLBaseVisitor {
    private Server server;
    private DatabaseManager current_database;
    OutputStream writer;

    public static void main(String[] args) {
        Hashtable<Integer, Integer> hashtable = new Hashtable<>();
        for(int i=0; i<10; i++){
            hashtable.put(new Integer(i), new Integer(i));
        }
        for(int i=0; i<10; i++){
            hashtable.put(new Integer(i), new Integer(i+10));
        }
        System.out.println(hashtable.get(5));
    }
    public Visitor(){

    }

    public void setServer(Server server) {
        this.server = server;
        current_database = server.getCurrentDatabase();
        writer = server.getWriter();
    }

    private TableManager current_table;

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
            server.index_buffer.createIndex(current_database.getDatabaseName(), tablename, primary_schema, 666);
            server.index_buffer.saveAll();
        }
        try {
            writer.write("Create table success.\r\n".getBytes());
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
            server.data_buffer.deleteDataFile(current_database.getDatabaseName(), name);
            server.index_buffer.deleteIndex(current_database.getDatabaseName(), name);

            try {
                writer.write("Drop table success.\r\n".getBytes());
                writer.flush();
            }
            catch (IOException e){
                System.out.println("IO exception");
            }
            return null;
        }
        if(if_exists){
            try {
                writer.write("Drop table success.\r\n".getBytes());
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
               writer.write(res.getBytes());
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

    public void writeOneResult(DataPointer pointer, ArrayList<Integer> attributes) throws IOException{
        Record record = server.data_buffer.getNode(current_database.getDatabaseName(), current_table.getTableName(), pointer.page_id).extractOneRecord(pointer.record_id);
        Field[] fields = record.getFields();
        for(Integer i:attributes){
            writer.write((fields[i].getValue().toString()+",").getBytes());
        }
        writer.write(("\r\n").getBytes());
        writer.flush();
    }

    public void writeOneResult(Record record, ArrayList<Integer> attributes) throws IOException {
        Field[] fields = record.getFields();
        for(Integer i:attributes){
            writer.write((fields[i].getValue().toString()+",").toString().getBytes());
        }
        writer.write(("\r\n").toString().getBytes());
        writer.flush();
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
        try{
            for(Integer atttibute : attributes){
                writer.write((current_table.getSchema().getAttrubutes()[atttibute].getAttributeName()+",").getBytes());
            }
            writer.write("\r\n".getBytes());
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
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

                int[] queryRes = indexTree.query(target);
                int nodeid = queryRes[0];
                int keyidx = queryRes[1];

                BTreeLeafNode node = (BTreeLeafNode) server.index_buffer.getNode(nodeid, current_database.getDatabaseName(), current_table.getTableName(), _index);
                if(query.type == Util.E){
//                    a='1'
                    if((keyidx == node.key_number)||(node.compare2key(node.record2key(target), node.keys.get(keyidx)) != Util.E)){
                        return null;
                    }
                    DataPointer[] results = node.getPointer(keyidx);
                    try{
                        for(DataPointer result : results){
                            writeOneResult(result, attributes);
                        }
                    }
                    catch (IOException e){
                        System.out.println("write error");
                        return null;
                    }
                }
                else {

                    if(query.type == Util.L){
                        writeListResult(node, keyidx-1, true, attributes);
                    }
                    else if(query.type == Util.G){
                        int startidx = keyidx;
                        if((keyidx != node.key_number)||(node.compare2key(node.record2key(target), node.keys.get(keyidx)) == Util.E)){
                            startidx = keyidx+1;
                        }
                        writeListResult(node, startidx, false, attributes);
                    }
                    else if(query.type == Util.NE){
                        //left
                        writeListResult(node, keyidx-1, true, attributes);
                        //right
                        int startidx = keyidx;
                        if((keyidx != node.key_number)||(node.compare2key(node.record2key(target), node.keys.get(keyidx)) == Util.E)){
                            startidx = keyidx+1;
                        }
                        writeListResult(node, startidx, false, attributes);
                    }
                    else if(query.type == Util.LE){
                        if(keyidx == node.key_number){
                            writeListResult(node, node.key_number-1, true, attributes);
                        }
                        else if(node.compare2key(node.record2key(target), node.keys.get(keyidx)) == Util.E){
                            writeListResult(node, keyidx, true, attributes);
                        }
                        else {
                            writeListResult(node, keyidx-1, true, attributes);
                        }
                    }
                    else if(query.type == Util.GE){
                        writeListResult(node, keyidx, false, attributes);
                    }
                    else {
                        throw new RuntimeException("No query type!");
                    }
                }
            }
            else {
                String databaseName = current_database.getDatabaseName();
                String curtableName = current_table.getTableName();
                long blockNumber = server.data_buffer.getDataStorage(databaseName, curtableName).block_number;
                for(int i=0; i<blockNumber; i++){
                    Record[] records = server.data_buffer.getNode(databaseName, curtableName, i).extractAllRecords();
                    for(Record record : records){
                        for(Field field:record.getFields()){
                            if(field.getAttribute().getAttributeName().equals(query.attributeName)){
                                int compareRes = field.compareTo(new Field(query.value, index));
                                if((compareRes<0)&&((query.type==Util.L)||(query.type==Util.LE)||(query.type==Util.NE))){
                                    try{
                                        writeOneResult(record, attributes);
                                    }
                                    catch (IOException e){
                                        System.out.println("write error");
                                        return null;
                                    }
                                }
                                if((compareRes==0)&&((query.type==Util.LE)||(query.type==Util.GE)||(query.type==Util.E))){
                                    try{
                                        writeOneResult(record, attributes);
                                    }
                                    catch (IOException e){
                                        System.out.println("write error");
                                        return null;
                                    }
                                }
                                if((compareRes>0)&&((query.type==Util.G)||(query.type==Util.GE)||(query.type==Util.NE))){
                                    try{
                                        writeOneResult(record, attributes);
                                    }
                                    catch (IOException e){
                                        System.out.println("write error");
                                        return null;
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
        else {
            //select all
            String databaseName = current_database.getDatabaseName();
            String curtableName = current_table.getTableName();
            long blockNumber = server.data_buffer.getDataStorage(databaseName, curtableName).block_number;
            for(int i=0; i<blockNumber; i++){
                Record[] records = server.data_buffer.getNode(databaseName, curtableName, i).extractAllRecords();
                for(Record record : records){
                    try{
                        writeOneResult(record, attributes);
                    }
                    catch (IOException e){
                        System.out.println("write error");
                        return null;
                    }
                }
            }
        }
        return null;
    }

    void writeListResult(BTreeLeafNode leaf, int startidx, boolean is_left, ArrayList<Integer> attributes){
        while (true){
            if(is_left){
                for(int i=startidx; i>=0; i--){
                    DataPointer[] results = leaf.getPointer(i);
                    try{
                        for(DataPointer result : results){
                            writeOneResult(result, attributes);
                        }
                    }
                    catch (IOException e){
                        System.out.println("write error");
                        return;
                    }
                }
                if(leaf.hasPrior()){
                    leaf = (BTreeLeafNode) leaf.prior();
                    startidx = leaf.key_number-1;
                }
                else {
                    return;
                }
            }
            else {
                for(int i=startidx; i<leaf.key_number; i++){
                    DataPointer[] results = leaf.getPointer(i);
                    try{
                        for(DataPointer result : results){
                            writeOneResult(result, attributes);
                        }
                    }
                    catch (IOException e){
                        System.out.println("write error");
                        return;
                    }
                }
                if(leaf.hasNext()){
                    leaf = (BTreeLeafNode) leaf.next();
                    startidx = 0;
                }
                else {
                    return ;
                }
            }
        }
    }

    ArrayList<DataPointer> getDeleteList(BTreeLeafNode leaf, int startidx, boolean is_left){
        ArrayList<DataPointer> pointers = new ArrayList<>();
        while (true){
            if(is_left){
                for(int i=startidx; i>=0; i--){
                    pointers.addAll(Arrays.asList(leaf.getPointer(i)));
                }
                if(leaf.hasPrior()){
                    leaf = (BTreeLeafNode) leaf.prior();
                    startidx = leaf.key_number-1;
                }
                else {
                    return pointers;
                }
            }
            else {
                for(int i=startidx; i<leaf.key_number; i++){
                    pointers.addAll(Arrays.asList(leaf.getPointer(i)));
                }
                if(leaf.hasNext()){
                    leaf = (BTreeLeafNode) leaf.next();
                    startidx = 0;
                }
                else {
                    return pointers;
                }
            }
        }
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

    @Override
    public Object visitDelete_stmt(LowSQLParser.Delete_stmtContext ctx) {
        List<ParseTree> nodes = ctx.children;
        String tableName = (String)visit(nodes.get(2));
        TableManager table = current_database.getOneTable(tableName);
        if(table == null){
            throw new RuntimeException("No table named "+tableName);
        }
        current_table = table;
        _Query query = null;
        if(nodes.size() > 3){
            query = (_Query)visit(nodes.get(4));
        }
        ArrayList<DataPointer> pointers = new ArrayList<>();
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

                int[] queryRes = indexTree.query(target);
                int nodeid = queryRes[0];
                int keyidx = queryRes[1];
                BTreeLeafNode node = (BTreeLeafNode) server.index_buffer.getNode(nodeid, current_database.getDatabaseName(), current_table.getTableName(), _index);
                if(query.type == Util.E){
//                    a='1'
                    if((keyidx == node.key_number)||(node.compare2key(node.record2key(target), node.keys.get(keyidx)) != Util.E)){
                        return null;
                    }
                    pointers.addAll(Arrays.asList(node.getPointer(keyidx)));
                }
                else {
                    if(query.type == Util.L){
                        pointers.addAll(getDeleteList(node, keyidx-1, true));
                    }
                    else if(query.type == Util.G){
                        int startidx = keyidx;
                        if((keyidx != node.key_number)||(node.compare2key(node.record2key(target), node.keys.get(keyidx)) == Util.E)){
                            startidx = keyidx+1;
                        }
                        pointers.addAll(getDeleteList(node, startidx, false));
                    }
                    else if(query.type == Util.NE){
                        //left
                        pointers.addAll(getDeleteList(node, keyidx-1, true));
                        //right
                        int startidx = keyidx;
                        if((keyidx != node.key_number)||(node.compare2key(node.record2key(target), node.keys.get(keyidx)) == Util.E)){
                            startidx = keyidx+1;
                        }
                        pointers.addAll(getDeleteList(node, startidx, false));
                    }
                    else if(query.type == Util.LE){
                        if(keyidx == node.key_number){
                            pointers.addAll(getDeleteList(node, node.key_number-1, true));
                        }
                        else if(node.compare2key(node.record2key(target), node.keys.get(keyidx)) == Util.E){
                            pointers.addAll(getDeleteList(node, keyidx, true));
                        }
                        else {
                            pointers.addAll(getDeleteList(node, keyidx-1, true));
                        }
                    }
                    else if(query.type == Util.GE){
                        pointers.addAll(getDeleteList(node, keyidx, false));
                    }
                    else {
                        throw new RuntimeException("No query type!");
                    }
                }
            }
            else {
                String databaseName = current_database.getDatabaseName();
                String curtableName = current_table.getTableName();
                long blockNumber = server.data_buffer.getDataStorage(databaseName, curtableName).block_number;
                for(int i=0; i<blockNumber; i++){
                    Record[] records = server.data_buffer.getNode(databaseName, curtableName, i).extractAllRecords();
                    for(int j=0; j<records.length; j++){
                        Record record = records[j];
                        for(Field field:record.getFields()){
                            if(field.getAttribute().getAttributeName().equals(query.attributeName)){
                                int compareRes = field.compareTo(new Field(query.value, index));
                                if((compareRes<0)&&((query.type==Util.L)||(query.type==Util.LE)||(query.type==Util.NE))){
                                    pointers.add(new DataPointer(i, j));
                                }
                                if((compareRes==0)&&((query.type==Util.LE)||(query.type==Util.GE)||(query.type==Util.E))){
                                    pointers.add(new DataPointer(i, j));
                                }
                                if((compareRes>0)&&((query.type==Util.G)||(query.type==Util.GE)||(query.type==Util.NE))){
                                    pointers.add(new DataPointer(i, j));
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
        else {
            //delete all
            for(int i=0; i<server.data_buffer.getDataStorage(current_database.getDatabaseName(), current_table.getTableName()).block_number; i++){
                for(int j=0; j<server.data_buffer.getNode(current_database.getDatabaseName(), current_table.getTableName(), i).getRecordNumber(); j++){
                    Record record = server.data_buffer.getNode(current_database.getDatabaseName(), current_table.getTableName(), i).extractOneRecord(j);
                    if(record != null) pointers.add(new DataPointer(i, j));
                }
            }
        }
        //get data
        for(DataPointer pointer : pointers){
            Record record = server.data_buffer.getNode(current_database.getDatabaseName(), current_table.getTableName(), pointer.page_id).extractOneRecord(pointer.record_id);
            //delete in all index-tree
            for(BTree btree : server.index_buffer.getBTrees(current_database.getDatabaseName(), current_table.getTableName())){
                btree.delete(record,pointer);
            }
            //delete data
            server.data_buffer.getNode(current_database.getDatabaseName(), current_table.getTableName(), pointer.page_id).deleteOneRecord(pointer.record_id);
        }
        return null;
    }

    @Override
    public Object visitComplex_select_stmt(LowSQLParser.Complex_select_stmtContext ctx) {
        List<ParseTree> nodes = ctx.children;
        String tableAname = (String)visit(nodes.get(3));
        String tableBname = (String)visit(nodes.get(5));
        TableManager tableA = current_database.getOneTable(tableAname);
        TableManager tableB = current_database.getOneTable(tableBname);
        if((tableA==null)||(tableB==null)){
            throw new RuntimeException("Table not found;");
        }
        int tableARecordnum = server.data_buffer.getDataStorageRecordNum(current_database.getDatabaseName(), tableA.getTableName());
        int tableBRecordnum = server.data_buffer.getDataStorageRecordNum(current_database.getDatabaseName(), tableB.getTableName());
        if(tableARecordnum>tableBRecordnum){
            TableManager tmp = tableA;
            tableA = tableB;
            tableB = tmp;

            String nametmp = tableAname;
            tableAname = tableBname;
            tableBname = nametmp;

            int numtmp = tableARecordnum;
            tableARecordnum = tableBRecordnum;
            tableBRecordnum = numtmp;
        }

//        get attributes
        ArrayList<Integer> AAttributes = new ArrayList<>();
        ArrayList<Integer> BAttributes = new ArrayList<>();
        ArrayList<Integer> attributes = new ArrayList<>();
        ArrayList<String> attributesName = (ArrayList<String>) visit(nodes.get(1));

        for(int i=0; i<attributesName.size();){
            String tablename = attributesName.get(i);
            String attrname = attributesName.get(i+1);
            boolean attrfind = false;
            if(tablename.equals(tableAname)){
                for(int j=0; j< tableA.getSchema().getAttrubutes().length; j++){
                    TableAttribute attribute = tableA.getSchema().getAttrubutes()[j];
                    if(attribute.getAttributeName().equals(attrname)){
                        attrfind = true;
                        AAttributes.add(new Integer(j));
                    }
                }
            }
            else if(tablename.equals(tableBname)){
                for(int j=0; j< tableB.getSchema().getAttrubutes().length; j++){
                    TableAttribute attribute = tableB.getSchema().getAttrubutes()[j];
                    if(attribute.getAttributeName().equals(attrname)){
                        attrfind = true;
                        BAttributes.add(new Integer(j));
                    }
                }
            }
            else {
                throw new RuntimeException("Table not found.");
            }
            if(!attrfind){
                throw new RuntimeException("Table not found.");
            }
            i+=2;
        }
//        get join-table schema
        TableAttribute[] joinAttributes = new TableAttribute[tableA.getSchema().getAttrubutes().length+tableB.getSchema().getAttrubutes().length];
        String table1name = (String)visit(nodes.get(7));
        String table1join = (String)visit(nodes.get(9));
        Integer type = (Integer)visit(nodes.get(10));
        String table2name = (String)visit(nodes.get(11));
        String table2join = (String)visit(nodes.get(13));
        int tableAJoinIdx = -1;
        int tableBJoinIdx = -1;
        if(table1name.equals(tableAname)){
            for(int i=0; i<tableA.getSchema().getAttrubutes().length; i++){
                TableAttribute attribute = tableA.getSchema().getAttrubutes()[i];
                if(attribute.getAttributeName().equals(table1join)){
                    tableAJoinIdx = i;
                    break;
                }
            }
            if(!table2name.equals(tableBname)){
                throw new RuntimeException("Table not found");
            }
            for(int i=0; i<tableB.getSchema().getAttrubutes().length; i++){
                TableAttribute attribute = tableB.getSchema().getAttrubutes()[i];
                if(attribute.getAttributeName().equals(table2join)){
                    tableBJoinIdx = i;
                    break;
                }
            }
            if((tableAJoinIdx==-1)||(tableBJoinIdx==-1)){
                throw new RuntimeException("Table not found");
            }
        }
        else if(table1name.equals(tableBname)){
            for(int i=0; i<tableB.getSchema().getAttrubutes().length; i++){
                TableAttribute attribute = tableB.getSchema().getAttrubutes()[i];
                if(attribute.getAttributeName().equals(table1join)){
                    tableBJoinIdx = i;
                    break;
                }
            }
            if(!table2name.equals(tableAname)){
                throw new RuntimeException("Table not found");
            }
            for(int i=0; i<tableA.getSchema().getAttrubutes().length; i++){
                TableAttribute attribute = tableA.getSchema().getAttrubutes()[i];
                if(attribute.getAttributeName().equals(table2join)){
                    tableAJoinIdx = i;
                    break;
                }
            }
            if((tableAJoinIdx==-1)||(tableBJoinIdx==-1)){
                throw new RuntimeException("Table not found");
            }
        }
        else {
            throw new RuntimeException("Table not found");
        }
        int joinidx = 0;
        for(int i=0; i<tableA.getSchema().getAttrubutes().length; i++){
            for(Integer j : AAttributes){
                if(j==i){
                    attributes.add(joinidx);
                    break;
                }
            }
            TableAttribute attribute = tableA.getSchema().getAttrubutes()[i];
            joinAttributes[joinidx++] = attribute;
        }
        for(int i=0; i<tableB.getSchema().getAttrubutes().length; i++){
            for(Integer j : BAttributes){
                if(j==i){
                    attributes.add(joinidx);
                    break;
                }
            }
            TableAttribute attribute = tableB.getSchema().getAttrubutes()[i];
            joinAttributes[joinidx++] = attribute;
        }
        TableSchema joinSchema = new TableSchema(null, joinAttributes);

        _Query query = null;
        if(nodes.size() > 14) query = (_Query)visit(nodes.get(15));

        try{
            for(Integer atttibute : attributes){
                writer.write((joinAttributes[atttibute]+",").getBytes());
            }
            writer.write("\r\n".getBytes());
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
//        where
        boolean find = false;
        int whereIdx = -1;
        if(query != null){
            for(int i=0; i<joinAttributes.length; i++){
                TableAttribute attribute = joinAttributes[i];
                if(attribute.getAttributeName().equals(query.attributeName)){
                    find = true;
                    whereIdx = i;
                    break;
                }
            }
            if(!find){
                throw new RuntimeException("Table not found.");
            }
        }

        // which method


        //nested-loop join
        boolean isAouter = true;
        TableAttribute[] AindexAttrs = new TableAttribute[1];
        AindexAttrs[0] = new TableAttribute(tableA.getTableName(), table1name.equals(tableA.getTableName())?table1join:table2join, -1, -1, false, false);
        TableSchema Aindex_schema = new TableSchema(tableA.getTableName(), AindexAttrs);
        TableAttribute[] BindexAttrs = new TableAttribute[1];
        BindexAttrs[0] = new TableAttribute(tableA.getTableName(), table2name.equals(tableB.getTableName())?table2join:table1join, -1, -1, false, false);
        TableSchema Bindex_schema = new TableSchema(tableB.getTableName(), BindexAttrs);
        BTree Atree = server.index_buffer.getBTree(current_database.getDatabaseName(), tableAname, Aindex_schema);
        BTree Btree = server.index_buffer.getBTree(current_database.getDatabaseName(), tableBname, Bindex_schema);
        if((Atree!=null)&&(Btree==null)) isAouter = false;
        TableManager outerTable = isAouter?tableA:tableB;
        TableManager innerTable = isAouter?tableB:tableA;
        for(int i=0; i<server.data_buffer.getDataStorage(current_database.getDatabaseName(), outerTable.getTableName()).block_number; i++){
            for(Record record:server.data_buffer.getNode(current_database.getDatabaseName(), outerTable.getTableName(), i).extractAllRecords()){
                if(((isAouter)&&(Btree != null)) || ((!isAouter)&&(Atree!=null))){
//                    index-nested-loop
                    if(isAouter){
                        Field[] falsefield = new Field[1];
                        falsefield[0] = new Field(record.getFields()[tableAJoinIdx].getValue(), tableB.getSchema().getAttrubutes()[tableBJoinIdx]);
                        Record falserecord = new Record(falsefield, null);
                        int[] queryres =  Btree.query(falserecord);
                        BTreeLeafNode node = (BTreeLeafNode) server.index_buffer.getNode(queryres[0], current_database.getDatabaseName(), innerTable.getTableName(), BindexAttrs);
                        if(queryres[1] == node.key_number) continue;
                        if((node.compare2key(node.keys.get(queryres[1]), node.record2key(falserecord)) == Util.E)){
                            for(DataPointer pointer:node.getPointer(queryres[1])){
                                Record innerrecord = server.data_buffer.getNode(current_database.getDatabaseName(), innerTable.getTableName(), pointer.page_id).extractOneRecord(pointer.record_id);
                                Field[] target = new Field[record.getFields().length+innerrecord.getFields().length];
                                System.arraycopy(record.getFields(), 0, target, 0, record.getFields().length);
                                System.arraycopy(innerrecord.getFields(), 0, target, record.getFields().length, innerrecord.getFields().length);
                                Record joinrecord = new Record(target, joinSchema);
                                if(query == null){
                                    try{
                                        writeOneResult(joinrecord, attributes);
                                    }
                                    catch (IOException e){
                                        System.out.println(e.getMessage());
                                    }
                                }
                                else {
                                    int compareRes = joinrecord.getFields()[whereIdx].compareTo(new Field(query.value, joinAttributes[whereIdx]));
                                    if((compareRes<0)&&((query.type==Util.L)||(query.type==Util.LE)||(query.type==Util.NE))){
                                        try{
                                            writeOneResult(joinrecord, attributes);
                                        }
                                        catch (IOException e){
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    if((compareRes==0)&&((query.type==Util.E)||(query.type==Util.LE)||(query.type==Util.GE))){
                                        try{
                                            writeOneResult(joinrecord, attributes);
                                        }
                                        catch (IOException e){
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    if((compareRes>0)&&((query.type==Util.G)||(query.type==Util.GE)||(query.type==Util.NE))){
                                        try{
                                            writeOneResult(joinrecord, attributes);
                                        }
                                        catch (IOException e){
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else {
                        Field[] falsefield = new Field[1];
                        falsefield[0] = new Field(record.getFields()[tableBJoinIdx].getValue(), tableA.getSchema().getAttrubutes()[tableAJoinIdx]);
                        Record falserecord = new Record(falsefield, null);
                        int[] queryres =  Atree.query(falserecord);
                        BTreeLeafNode node = (BTreeLeafNode) server.index_buffer.getNode(queryres[0], current_database.getDatabaseName(), innerTable.getTableName(), AindexAttrs);
                        if(queryres[1] == node.key_number) continue;

                        if(node.compare2key(node.keys.get(queryres[1]), node.record2key(falserecord)) == Util.E){
                            for(DataPointer pointer:node.getPointer(queryres[1])){
                                Record innerrecord = server.data_buffer.getNode(current_database.getDatabaseName(), innerTable.getTableName(), pointer.page_id).extractOneRecord(pointer.record_id);
                                Field[] target = new Field[record.getFields().length+innerrecord.getFields().length];
                                System.arraycopy(innerrecord.getFields(), 0, target, 0, innerrecord.getFields().length);
                                System.arraycopy(record.getFields(), 0, target, innerrecord.getFields().length, record.getFields().length);
                                Record joinrecord = new Record(target, joinSchema);
                                if(query == null){
                                    try{
                                        writeOneResult(joinrecord, attributes);
                                    }
                                    catch (IOException e){
                                        System.out.println(e.getMessage());
                                    }
                                }
                                else {
                                    int compareRes = joinrecord.getFields()[whereIdx].compareTo(new Field(query.value, joinAttributes[whereIdx]));
                                    if((compareRes<0)&&((query.type==Util.L)||(query.type==Util.LE)||(query.type==Util.NE))){
                                        try{
                                            writeOneResult(joinrecord, attributes);
                                        }
                                        catch (IOException e){
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    if((compareRes==0)&&((query.type==Util.E)||(query.type==Util.LE)||(query.type==Util.GE))){
                                        try{
                                            writeOneResult(joinrecord, attributes);
                                        }
                                        catch (IOException e){
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    if((compareRes>0)&&((query.type==Util.G)||(query.type==Util.GE)||(query.type==Util.NE))){
                                        try{
                                            writeOneResult(joinrecord, attributes);
                                        }
                                        catch (IOException e){
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else {
//                    common-nested-loop
                    for(int j=0; j<server.data_buffer.getDataStorage(current_database.getDatabaseName(), innerTable.getTableName()).block_number; j++){
                        for(Record innerrecord : server.data_buffer.getNode(current_database.getDatabaseName(), innerTable.getTableName(), j).extractAllRecords()){
                            if(!isAouter){
                                throw new RuntimeException("Internal error.");
                            }
                            if(record.getFields()[tableAJoinIdx].compareTo(innerrecord.getFields()[tableBJoinIdx]) != 0) continue;
                            Field[] target = new Field[record.getFields().length+innerrecord.getFields().length];
                            System.arraycopy(record.getFields(), 0, target, 0, record.getFields().length);
                            System.arraycopy(innerrecord.getFields(), 0, target, record.getFields().length, innerrecord.getFields().length);
                            Record joinrecord = new Record(target, joinSchema);
                            if(query == null){
                                try{
                                    writeOneResult(joinrecord, attributes);
                                }
                                catch (IOException e){
                                    System.out.println(e.getMessage());
                                }
                            }
                            else {
                                int compareRes = joinrecord.getFields()[whereIdx].compareTo(new Field(query.value, joinAttributes[whereIdx]));
                                if((compareRes<0)&&((query.type==Util.L)||(query.type==Util.LE)||(query.type==Util.NE))){
                                    try{
                                        writeOneResult(joinrecord, attributes);
                                    }
                                    catch (IOException e){
                                        System.out.println(e.getMessage());
                                    }
                                }
                                if((compareRes==0)&&((query.type==Util.E)||(query.type==Util.LE)||(query.type==Util.GE))){
                                    try{
                                        writeOneResult(joinrecord, attributes);
                                    }
                                    catch (IOException e){
                                        System.out.println(e.getMessage());
                                    }
                                }
                                if((compareRes>0)&&((query.type==Util.G)||(query.type==Util.GE)||(query.type==Util.NE))){
                                    try{
                                        writeOneResult(joinrecord, attributes);
                                    }
                                    catch (IOException e){
                                        System.out.println(e.getMessage());
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }

        // hash-join
//        HashMap<byte[], Record> hashMap = new HashMap<>();
//        for(int i=0; i<server.data_buffer.getDataStorage(current_database.getDatabaseName(), tableA.getTableName()).block_number; i++){
//            for(Record record:server.data_buffer.getNode(current_database.getDatabaseName(), tableA.getTableName(), i).extractAllRecords()){
//
//            }
//        }

        return null;
    }

    @Override
    public Object visitTwo_attributes(LowSQLParser.Two_attributesContext ctx) {
        ArrayList<String> arrays = new ArrayList<>();
        for(ParseTree node : ctx.children){
            if(node instanceof LowSQLParser.NameContext){
                arrays.add((String)visit(node));
            }
        }
        return arrays;
    }

    @Override
    public Object visitUpdate_stmt(LowSQLParser.Update_stmtContext ctx) {
        List<ParseTree> nodes = ctx.children;
        String tableName = (String)visit(nodes.get(1));
        TableManager table = current_database.getOneTable(tableName);
        if(table == null){
            throw new RuntimeException("No table named "+tableName);
        }
        current_table = table;
        String setAttr = (String)visit(nodes.get(3));
        Object setVal = visit(nodes.get(5));
        boolean find = false;
        int setIdx = -1;
        for(int i=0; i<current_table.getSchema().getAttrubutes().length; i++){
            TableAttribute attribute = current_table.getSchema().getAttrubutes()[i];
            if(attribute.getAttributeName().equals(setAttr)){
                find = true;
                setIdx = i;
                break;
            }
        }
        if(!find){
            throw new RuntimeException("Table not found.");
        }

        _Query query = (_Query)visit(nodes.get(7));
        ArrayList<DataPointer> pointers = new ArrayList<>();
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

                int[] queryRes = indexTree.query(target);
                int nodeid = queryRes[0];
                int keyidx = queryRes[1];
                BTreeLeafNode node = (BTreeLeafNode) server.index_buffer.getNode(nodeid, current_database.getDatabaseName(), current_table.getTableName(), _index);
                if(query.type == Util.E){
//                    a='1'
                    if((keyidx == node.key_number)||(node.compare2key(node.record2key(target), node.keys.get(keyidx)) != Util.E)){
                        return null;
                    }
                    pointers.addAll(Arrays.asList(node.getPointer(keyidx)));
                }
                else {
                    if(query.type == Util.L){
                        pointers.addAll(getDeleteList(node, keyidx-1, true));
                    }
                    else if(query.type == Util.G){
                        int startidx = keyidx;
                        if((keyidx != node.key_number)||(node.compare2key(node.record2key(target), node.keys.get(keyidx)) == Util.E)){
                            startidx = keyidx+1;
                        }
                        pointers.addAll(getDeleteList(node, startidx, false));
                    }
                    else if(query.type == Util.NE){
                        //left
                        pointers.addAll(getDeleteList(node, keyidx-1, true));
                        //right
                        int startidx = keyidx;
                        if((keyidx != node.key_number)||(node.compare2key(node.record2key(target), node.keys.get(keyidx)) == Util.E)){
                            startidx = keyidx+1;
                        }
                        pointers.addAll(getDeleteList(node, startidx, false));
                    }
                    else if(query.type == Util.LE){
                        if(keyidx == node.key_number){
                            pointers.addAll(getDeleteList(node, node.key_number-1, true));
                        }
                        else if(node.compare2key(node.record2key(target), node.keys.get(keyidx)) == Util.E){
                            pointers.addAll(getDeleteList(node, keyidx, true));
                        }
                        else {
                            pointers.addAll(getDeleteList(node, keyidx-1, true));
                        }
                    }
                    else if(query.type == Util.GE){
                        pointers.addAll(getDeleteList(node, keyidx, false));
                    }
                    else {
                        throw new RuntimeException("No query type!");
                    }
                }
            }
        else {
                String databaseName = current_database.getDatabaseName();
                String curtableName = current_table.getTableName();
                long blockNumber = server.data_buffer.getDataStorage(databaseName, curtableName).block_number;
                for(int i=0; i<blockNumber; i++){
                    Record[] records = server.data_buffer.getNode(databaseName, curtableName, i).extractAllRecords();
                    for(int j=0; j<records.length; j++){
                        Record record = records[j];
                        for(Field field:record.getFields()){
                            if(field.getAttribute().getAttributeName().equals(query.attributeName)){
                                int compareRes = field.compareTo(new Field(query.value, index));
                                if((compareRes<0)&&((query.type==Util.L)||(query.type==Util.LE)||(query.type==Util.NE))){
                                    pointers.add(new DataPointer(i, j));
                                }
                                if((compareRes==0)&&((query.type==Util.LE)||(query.type==Util.GE)||(query.type==Util.E))){
                                    pointers.add(new DataPointer(i, j));
                                }
                                if((compareRes>0)&&((query.type==Util.G)||(query.type==Util.GE)||(query.type==Util.NE))){
                                    pointers.add(new DataPointer(i, j));
                                }
                                break;
                            }
                        }
                    }
                }
            }
        //get data
        ArrayList<Record> records = new ArrayList<>();
        for(DataPointer pointer : pointers){
            Record record = server.data_buffer.getNode(current_database.getDatabaseName(), current_table.getTableName(), pointer.page_id).extractOneRecord(pointer.record_id);
            //delete in all index-tree
            for(BTree btree : server.index_buffer.getBTrees(current_database.getDatabaseName(), current_table.getTableName())){
                btree.delete(record,pointer);
            }
            //delete data
            server.data_buffer.getNode(current_database.getDatabaseName(), current_table.getTableName(), pointer.page_id).deleteOneRecord(pointer.record_id);
            //push new data
            record.getFields()[setIdx].setValue(setVal);
            records.add(record);
        }
        //insert
        for(Record record : records){
            DataPointer dataPointer = server.data_buffer.getDataStorage(current_database.getDatabaseName(), current_table.getTableName()).insert(record);
            for(BTree bTree : server.index_buffer.getBTrees(current_database.getDatabaseName(), current_table.getTableName())){
                bTree.insert(record, dataPointer.page_id, dataPointer.record_id);
            }
        }
        try{
            writer.write((new String("update ")+String.valueOf(records.size())+new String(" rows.")).getBytes());
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Object visitCreate_database_stmt(LowSQLParser.Create_database_stmtContext ctx) {
        String newName = (String)visit(ctx.children.get(2));
        DatabaseManager newDatabase = new DatabaseManager(newName);
        server.addDatabase(newDatabase);
        return null;
    }

    @Override
    public Object visitDrop_database_stmt(LowSQLParser.Drop_database_stmtContext ctx) {
        String dropName = (String)visit(ctx.children.get(2));
        server.deleteDatabase(dropName);
        return null;
    }

    @Override
    public Object visitUse_database_stmt(LowSQLParser.Use_database_stmtContext ctx) {
        String name = (String)visit(ctx.children.get(2));
        server.setCurrentDatabase(name);
        current_database = server.getOneDatabase(name);
        return null;
    }

    @Override
    public Object visitShow_databases(LowSQLParser.Show_databasesContext ctx) {
        try{
            for(DatabaseManager database:server.getDatabases()){
                writer.write((database.getDatabaseName()+"\r\n").toString().getBytes());
            }
            writer.flush();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Object visitShow_table_in_database(LowSQLParser.Show_table_in_databaseContext ctx) {
        String databaseName = (String)visit(ctx.children.get(2));
        DatabaseManager database = server.getOneDatabase(databaseName);
        try{
            for(TableManager table:database.getTables()){
                writer.write((table.getTableName()+"\r\n").toString().getBytes());
            }
            writer.flush();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
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