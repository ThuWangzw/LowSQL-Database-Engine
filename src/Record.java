public class Record {
    private Field[] fields;
    private int size;  //bytes of the whole record
    private TableSchema schema;

    //read a record from file
    public Record(byte[] data,TableSchema sa){
        schema = sa;
        size = data.length;
        //split bytes and further analyse to get the fields
        int len = schema.getAttributeNumber();
        TableAttribute[] attrs = schema.getAttrubutes();
        int curpos = 0;
        int cursize;
        fields = new Field[len];
        int the_type;
        for (int i = 0; i < len; i++){
            the_type = attrs[i].getType();
            if ( the_type < Util.VARCHAR){
                cursize = Util.DataTypeSize[the_type];
            }
            else{
                int j;
                for (j = curpos; j < size; j++){
                    if (data[j] == 0)
                        break;
                }
                cursize = j + 1 - curpos;
            }
            byte[] temp = new byte[cursize];
            System.arraycopy(data,curpos,temp,0,cursize);
            fields[i] = new Field(attrs[i],temp);
            curpos += cursize;
        }
    }

    //add a new record
    public Record(Field[] fd,TableSchema sa) {
        schema = sa;
        // One record in the database
        fields = fd;
        size = 0;
        for (Field f : fields){
            size += f.getSize();
        }
    }

    public int getSize(){return size;}

    public TableSchema getSchema(){return schema;}

    public Field[] getFields(){return fields;}

    public byte[] toBytes(){
        byte[] data = new byte[size];
        int current_pos = 0;
        for (Field f : fields){
            System.arraycopy(f.toBytes(),0,data,current_pos,f.getSize());
            current_pos += f.getSize();
        }
        return data;
    }

    public Object[] getValue(){
        int len = fields.length;
        Object[] obj = new Object[len];

        for (int i = 0; i < len; i++ ){
           obj[i] = fields[i].getValue();
        }
        return obj;
    }

    public Boolean isValid(){
        int len = fields.length;
        Boolean valid = true;
        for(int i = 0; i < len; i++){
            valid = valid && fields[i].isValid();
        }
        return valid;
    }
}
