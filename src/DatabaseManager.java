import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DatabaseManager {
    //every database <-> one instance
    private String DB_name;
    private int table_number;
    private TableManager[] tables;

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
        //table number
        System.arraycopy(metadata,4 + Util.DatabaseNameMaxLength,bits4,0,4);
        table_number = Util.byte2int(bits4);

        if (table_number > 0) {
            //tables
            tables = new TableManager[table_number];
            int curpos = Util.DatabaseNameMaxLength + 8, table_size = 0;
            for (i = 0; i < table_number; i++) {
                System.arraycopy(metadata, curpos, bits4, 0, 4);
                table_size = Util.byte2int(bits4);
                byte[] t_meta = new byte[table_size];
                System.arraycopy(metadata, curpos, t_meta, 0, table_size);
                tables[i] = new TableManager(DB_name, t_meta);
                curpos += table_size;
            }
        }else {
            tables = null;
        }
    }

    public void toMetaByte(){

    }


    public void createTable ( ){


    }

    public void dropTable ( ){

    }

    public static void main(String[] args){
        System.out.println("-- DatabaseManager --");
    }
}
