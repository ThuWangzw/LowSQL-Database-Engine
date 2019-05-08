public class TableSchema {
    private String table_name;
    private int attribute_number;
    private TableAttribute[] attributes;

    /* metadata

     |   Attribute name -- 28|unused -- 1|PK --1|limit --1|type -- 1|
     |   Attribute name2 ...

     */

    //load from file
    public TableSchema(String t_name, int attr_number, byte[] metadata){
        table_name = t_name;
        attribute_number = attr_number;
        attributes = new TableAttribute[attribute_number];
        int len = Util.AttributeNameMaxLength + 4;
        byte[] temp = new byte[len];
        for (int i = 0; i < attribute_number; i++){
            System.arraycopy(metadata,i * len,temp,0,len);
            attributes[i] = new TableAttribute(table_name,temp);
        }
    }

    //create a new schema
    public TableSchema(String t_name,TableAttribute[] attrs){
        table_name = t_name;
        attributes = attrs;
        attribute_number = attributes.length;
    }

    public int getAttributeNumber(){return attribute_number;}

    public TableAttribute[] getAttrubutes(){return attributes;}

    public TableAttribute getOneAttribute(String attr_name){
        for (int i = 0; i < attribute_number; i++){
            if(attr_name.equals(attributes[i].getAttributeName())){
                return attributes[i];
            }
        }
        return null;
    }

    public byte[] toMetaByte(){
        int len = Util.AttributeNameMaxLength + 4;
        byte[] result = new byte[attribute_number * len];
        for (int i = 0 ; i < attribute_number; i++){
            System.arraycopy(attributes[i].toMetaByte(),0,result,i * len,len);
        }
        return result;
    }
}
