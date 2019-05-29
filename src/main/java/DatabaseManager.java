import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class DatabaseManager {
    //every database <-> one instance
    private String DB_name;
    private int table_number;
    private ArrayList<TableManager> tables;

/* Structure of Meta Data

     |Database Size -- 4 |   Database Name -- 28 |  Number of table  -- 4               |
     |   Table Size -- 4 |   Table name -- 28    |  Number of attributes --4            |
     |   Attribute name -- 28|unused -- 1|PK --1|limit --1|type -- 1|
     |   Attribute name2 ...
     ...
     | Table Size2 -- 4  |   Table name2 ...
     ...
     ...
     |Database2 Size -- 4 |   Database Name2 ...

     for PK; 1 = is primary key; 0 = is not primary key;
     for limit; for varchar and string, it stands for the max length
 */
    //load from file
    public DatabaseManager(byte[] metadata){
        byte[] bits4 = new byte[4];
        //Database Name
        int i,limit = 4 + Util.DatabaseNameMaxLength;
        for (i = 4; i < limit; i++){
            if (metadata[i] == 0)
                break;
        }
        byte[] db_name = new byte[i-4];
        System.arraycopy(metadata,4,db_name,0,i-4);
        DB_name = new String(db_name);
        //Table
        tables = new ArrayList<>();
        //table number
        System.arraycopy(metadata,4 + Util.DatabaseNameMaxLength,bits4,0,4);
        table_number = Util.byte2int(bits4);
        if (table_number > 0) {
            //tables
            int curpos = Util.DatabaseNameMaxLength + 8, table_size = 0;
            for (i = 0; i < table_number; i++) {
                System.arraycopy(metadata, curpos, bits4, 0, 4);
                table_size = Util.byte2int(bits4);
                byte[] t_meta = new byte[table_size];
                System.arraycopy(metadata, curpos, t_meta, 0, table_size);
                tables.add(new TableManager(DB_name, t_meta));
                curpos += table_size;
            }
        }
    }

    //create a new database instance
    public DatabaseManager(String db_name){
        DB_name = db_name;
        tables = new ArrayList<>();
        table_number = 0;
    }

    public byte[] toMetaByte(){
        ArrayList<byte[]> all_table_meta_bytes = new ArrayList<>();
        for (int i = 0; i < table_number; i++){
            all_table_meta_bytes.add(tables.get(i).toMetaByte());
        }
        int tot_length = Util.DatabaseNameMaxLength + 8;
        for(byte[] cur : all_table_meta_bytes){
            tot_length += cur.length;
        }
        byte[] tot_bytes = new byte[tot_length];
        System.arraycopy(Util.int2byte(tot_length),0,tot_bytes,0,4);
        byte[] name_byte = (DB_name + "\0").getBytes();
        int copy_len = (name_byte.length < Util.DatabaseNameMaxLength) ? name_byte.length : Util.DatabaseNameMaxLength;
        System.arraycopy(name_byte,0,tot_bytes,4,copy_len);
        System.arraycopy(Util.int2byte(table_number),0,tot_bytes,4 + Util.DatabaseNameMaxLength,4);
        int curpos = Util.DatabaseNameMaxLength + 8;
        for(byte[] cur : all_table_meta_bytes){
            System.arraycopy(cur,0,tot_bytes,curpos,cur.length);
            curpos += cur.length;
        }
        return tot_bytes;
    }


    public void addTable (TableManager t){
        //check if there are any tables that have the same name
        String t_name = t.getTableName();
        for (TableManager cur:tables){
            if(t_name.equals(cur.getTableName())){
                return;
            }
        }
        tables.add(t);
        table_number += 1;
    }

    public void deleteTable (String table_name){
        for(Iterator<TableManager> it = tables.iterator(); it.hasNext(); ){
            TableManager cur = it.next();
            if(table_name.equals(cur.getTableName())){
                it.remove();
                table_number -= 1;
                return;
            }
        }
    }

    public String getDatabaseName(){return DB_name;}

    public ArrayList<TableManager> getTables(){return tables;}

    public TableManager getOneTable(String table_name){
        for (TableManager cur:tables) {
            if (table_name.equals(cur.getTableName())) {
                return cur;
            }
        }
        return null;
    }

    public static void main(String[] args){
        System.out.println("-- DatabaseManager --");

    }
}
