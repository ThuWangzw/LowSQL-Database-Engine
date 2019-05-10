
public class DataBlock {
    //data: contain a number of records -- in slotted page structure
    //other values such as record_number, free_space, can be analysed and calculated
    private byte data[];
    private int record_number = -1;
    private int end_of_free_space = -1;
    private int page_id = -1;       //page id is the unique id of every data block
    TableSchema schema;
    private Boolean is_revised;     // to show whether there is a difference between the file and the memory


    //initialize by creating new data block
    public DataBlock(int p_id,TableSchema sa){
        is_revised = false;
        schema = sa;
        data = new byte[Util.DiskBlockSize];
        record_number = 0;
        end_of_free_space = Util.DiskBlockSize - 1;
        page_id = p_id;
        System.arraycopy(Util.int2byte(0),0,data,0,4);
        System.arraycopy(Util.int2byte(end_of_free_space),0,data,4,4);
    }

    // initialize by data from file
    public DataBlock(byte[] read_data,int p_id,TableSchema sa){
        is_revised = false;
        schema = sa;
        data = read_data;
        page_id = p_id;
        //analyse data
        byte[] temp = new byte[4];
        System.arraycopy(data,0,temp,0,4);
        record_number = Util.byte2int(temp);
        System.arraycopy(data,4,temp,0,4);
        end_of_free_space = Util.byte2int(temp);

    }

    public int getPageId(){return page_id;}

    public byte[] getData(){return data;}

    public TableSchema getSchema(){return schema;}

    public Boolean insertOneRecord(Record record){
        //return true if the record is successfully inserted
        int size = record.getSize();
        //the record has to be valid
        if(!record.isValid()) return false;
        //can not insert to free space
        if (size > end_of_free_space - 8 * record_number - 15) return false;
        System.arraycopy(record.toBytes(),0,data,end_of_free_space - size + 1,size);
        System.arraycopy(Util.int2byte(end_of_free_space),0,data,8 + 8 * record_number,4);
        System.arraycopy(Util.int2byte(size),0,data,12 + 8 * record_number,4);
        end_of_free_space -= size;
        record_number += 1;
        System.arraycopy(Util.int2byte(record_number),0,data,0,4);
        System.arraycopy(Util.int2byte(end_of_free_space),0,data,4,4);
        is_revised = true;
        return true;
    }

    public Record extractOneRecord(int record_id){
        if (record_id >= record_number) return null;
        int[] rd = extractOneRecordHeader(record_id);
        int record_size = rd[1],record_location = rd[0];
        byte[] record_bytes = new byte[record_size];
        System.arraycopy(data,record_location - record_size + 1,record_bytes,0,record_size);
        return new Record(record_bytes,schema);
    }

    public Record[] extractAllRecords(){
        Record[] records = new Record[record_number];
        for(int i = 0; i < record_number; i++){
            records[i] = extractOneRecord(i);
        }
        return records;
    }

    public int[] extractOneRecordHeader(int record_id){
        //record_location; record_size
        byte[] temp = new byte[4];
        System.arraycopy(data,8 + record_id * 8,temp,0,4);
        int record_location = Util.byte2int(temp);
        System.arraycopy(data,12 + record_id * 8,temp,0,4);
        int record_size = Util.byte2int(temp);
        return new int[]{record_location,record_size};
    }

    public Boolean deleteOneRecord(int record_id){
        //return true if the record is successfully deleted
        if (record_id >= record_number) return false;

        //for all situations
        int[] rd = extractOneRecordHeader(record_id);
        int record_location = rd[0],record_size = rd[1];
        if (record_id == record_number - 1){
            //the record is the leftest one of all records
            end_of_free_space = record_location;
        }else{
            //there are records on the left of the record
            //for header parts
            System.arraycopy(data,16 + record_id * 8,data,8 + record_id * 8,8 * (record_number - record_id -1));
            byte[] record_loc = new byte[4];
            for (int i = record_id;i < record_number - 1; i++){
                System.arraycopy(data,8 + i * 8,record_loc,0,4);
                System.arraycopy(Util.int2byte(Util.byte2int(record_loc) + record_size),0,data,8 + i * 8,4);
            }
            //for record parts
            int len = record_location - record_size - end_of_free_space;
            byte[] temp = new byte[len];
            System.arraycopy(data,end_of_free_space + 1, temp,0,len);
            System.arraycopy(temp,0,data,end_of_free_space + 1 + record_size,len);
            end_of_free_space += record_size;
        }
        //update record_number and end_of_free_space
        record_number -= 1;
        System.arraycopy(Util.int2byte(record_number),0,data,0,4);
        System.arraycopy(Util.int2byte(end_of_free_space),0,data,4,4);
        is_revised = true;
        return true;
    }

    public Boolean updateOneRecord(int record_id,Record record){
        return deleteOneRecord(record_id) && insertOneRecord(record);
    }

    public static void main(String[] args){
        System.out.println("-- DataBlock --");
    }
}
