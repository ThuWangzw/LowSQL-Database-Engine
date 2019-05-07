public class TableManager {
    private String DB_name;
    private String table_name;
    private TableSchema schema;
    //storage
    private DataStorage storage;
    //index will go here

    /* metadata

     |   Table size --4 | Table Name - 28 | Attribute Number -- 4|
     |   Attribute name -- 28|unused -- 1|PK --1|limit --1|type -- 1|
     |   Attribute name2 ...

     table size is the number of bytes of all the table schema(including table size itself)
     */

    public TableManager(String d_name,byte[] metadata){
        //table metadata
        byte[] bits4 = new byte[4];
        DB_name = d_name;
        int i,limit = 4 + Util.TableNameMaxLength;
        for(i = 4; i < limit;i++){
            if (metadata[i] == 0)
                break;
        }
        byte[] t_name = new byte[i - 4];
        System.arraycopy(metadata,4,t_name,0,i-4);
        table_name = new String(t_name);
        System.arraycopy(metadata, 4 + Util.TableNameMaxLength,bits4,0,4);
        int attr_number = Util.byte2int(bits4);
        //schema
        int len = metadata.length - 8 - Util.TableNameMaxLength;
        byte[] sc = new byte[len];
        System.arraycopy(metadata,8 + Util.TableNameMaxLength,sc,0,len);
        schema = new TableSchema(table_name,attr_number,sc);
        //storage
        storage = new DataStorage(d_name,table_name,schema);
    }

    public byte[] toMetaByte(){
        int meta_bytes = schema.getAttributeNumber() * (Util.AttributeNameMaxLength + 4 ) + 8 + Util.TableNameMaxLength;
        byte[] result = new byte[meta_bytes];
        System.arraycopy(Util.int2byte(meta_bytes),0,result,0,4);
        byte[] name_byte = (table_name + "\0").getBytes();
        System.arraycopy(name_byte,0,result,4,Util.TableNameMaxLength);
        System.arraycopy(Util.int2byte(schema.getAttributeNumber()),0,result,4 + Util.TableNameMaxLength,4);
        return result;
    }

}
