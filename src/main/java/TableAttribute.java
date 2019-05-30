public class TableAttribute {
    private String table_name;
    private String attribute_name;
    private int type;
    private int length_limit;
    private Boolean is_primary_key;
    private Boolean not_null;       //default: not null is false, means the value can be null

    /* metadata

    | Attribute name -- 28|not used |PK --1| not-null -- 1|type -- 1 | length limit 4 |

     */

    //load from metadata file
    public TableAttribute(String t_name,byte[] metadata){
        table_name = t_name;
        int i;
        for (i = 0; i < Util.AttributeNameMaxLength; i++){
            if (metadata[i] == 0)
                break;
        }
        byte[] attr_name = new byte[i];
        System.arraycopy(metadata,0,attr_name,0,i);
        attribute_name = new String(attr_name);
        byte[] bits4 = new byte[4];
        System.arraycopy(metadata,Util.AttributeNameMaxLength,bits4,0,4);
        int temp = Util.byte2int(bits4);
        type = temp & 0xff;
        not_null = (( temp >> 8) & 0xff) == 1;
        is_primary_key = ((temp >> 16) & 0xff) == 1;
        System.arraycopy(metadata,Util.AttributeNameMaxLength + 4,bits4,0,4);
        length_limit = Util.byte2int(bits4);
    }

    //create  a new Attribute
    public TableAttribute(String t_name,String attr_name,int the_type,int the_limit, Boolean is_PK,Boolean n_n){
        table_name = t_name;
        attribute_name = attr_name;
        type = the_type;
        length_limit = the_limit;
        is_primary_key = is_PK;
        not_null = n_n;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public int getType(){return type;}

    public void setIs_primary_key(Boolean is_primary_key) {
        this.is_primary_key = is_primary_key;
    }

    public String getAttributeName(){return attribute_name;}
    public int getLengthLimit(){
        if(type < Util.VARCHAR)
            return Util.DataTypeSize[type];
        return length_limit;
    }

    public byte[] toMetaByte(){
        if ( attribute_name.length() > Util.AttributeNameMaxLength)
            throw new IllegalArgumentException("The name of attribute is too long");
        byte[] result = new byte[Util.AttributeNameMaxLength + 8];
        byte[] name_byte = (attribute_name + "\0").getBytes();
        int copy_len = (name_byte.length < Util.AttributeNameMaxLength) ? name_byte.length : Util.AttributeNameMaxLength;
        System.arraycopy(name_byte,0,result,0,copy_len);
        int temp = 0;
        if(is_primary_key){
            temp = (1 << 16);
        }
        if(not_null){
            temp |= (1 << 8);
        }
        temp |= (type & 0xff);
        System.arraycopy(Util.int2byte(temp),0,result,Util.AttributeNameMaxLength,4);
        System.arraycopy(Util.int2byte(length_limit),0,result,Util.AttributeNameMaxLength + 4,4);
        return result;
    }

    public Boolean getPK() {return is_primary_key;}

    public Boolean getNotNull() {return not_null;}

    @Override
    public String toString() {
        String attribute = new String(getAttributeName());
        if(type == Util.CHAR){
            attribute += ",char";
        }else if(type == Util.INT){
            attribute += ",int";
        }else if(type == Util.FLOAT){
            attribute += ",float";
        }else if(type == Util.DOUBLE){
            attribute += ",double";
        }else if(type == Util.LONG){
            attribute += ",long";
        }else if(type == Util.STRING){
            attribute += ",string("+String.valueOf(length_limit)+")";
        }else if(type == Util.VARCHAR){
            attribute += ",varchar("+String.valueOf(length_limit)+")";
        }else {
            attribute += ",invalid type";
        }
        if(is_primary_key){
            attribute += ",primary key";
        }
        return attribute;
    }
}
