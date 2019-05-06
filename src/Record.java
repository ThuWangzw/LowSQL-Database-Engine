public class Record {
    private Field[] fields;
    private int size;

    //read a record from file
    public Record(byte[] data,int[] type){
        size = data.length;
        //split bytes and further analyse to get the fields
        int len = type.length;
        int curpos = 0;
        int cursize;
        fields = new Field[len];
        for (int i = 0; i < len; i++){
            if (type[i] < Util.VARCHAR){
                cursize = Util.DataTypeSize[type[i]];
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
            fields[i] = new Field(type[i],temp);
            curpos += cursize;
        }
    }

    //add a new record
    public Record(Field[] fd) {
        // One record in the database
        fields = fd;
        size = 0;
        for (Field f : fields){
            size += f.getSize();
        }
    }

    public int getSize(){return size;}

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
}
