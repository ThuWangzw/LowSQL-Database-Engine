public class Record {
    private Field[] fields;
    private int size;

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
}
