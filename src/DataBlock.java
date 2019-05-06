
public class DataBlock {
    //data: contain a number of records -- in slotted page structure
    //other values such as record_number, free_space, can be analysed and calculated
    private byte data[];
    private int record_number = -1;
    private int end_of_free_space = -1;
    private int page_id = -1;       //page id is the unique id of every data block

    //initialize by creating new data block
    public DataBlock(int p_id){
        data = new byte[Util.DiskBlockSize];
        record_number = 0;
        end_of_free_space = Util.DiskBlockSize;
        page_id = p_id;
        System.arraycopy(Util.int2byte(0),0,data,0,4);
        System.arraycopy(Util.int2byte(end_of_free_space),0,data,4,4);
    }

    // initialize by data from file
    public DataBlock(byte[] read_data,int p_id){
        data = read_data;
        page_id = p_id;
        //analyse data
        byte[] temp = new byte[4];
        System.arraycopy(data,0,temp,0,4);
        record_number = Util.byte2int(temp);
        System.arraycopy(data,4,temp,0,4);
        end_of_free_space = Util.byte2int(temp);

    }

    public Boolean insertOneRecord(Record record){
        //return true if the record is successfully inserted
        int size = record.getSize();
        if (size <= end_of_free_space - 8 * record_number - 16) return false;
        System.arraycopy(record.toBytes(),0,data,end_of_free_space - size,size);
        System.arraycopy(Util.int2byte(end_of_free_space),0,data,8 + 8 * record_number,4);
        System.arraycopy(Util.int2byte(size),0,data,12 + 8 * record_number,4);
        end_of_free_space -= size;
        record_number += 1;
        System.arraycopy(Util.int2byte(record_number),0,data,0,4);
        System.arraycopy(Util.int2byte(end_of_free_space),0,data,4,4);
        return true;
    }

    public static void main(String[] args){
        System.out.println("-- DataBlock --");
    }
}
