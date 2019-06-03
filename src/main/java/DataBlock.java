

//  number of records  - 4| End of free space - 4| actual number - 4|
public class DataBlock {
    //data: contain a number of records -- in slotted page structure
    //other values such as record_number, free_space, can be analysed and calculated
    String DB_name;
    String table_name;
    private byte data[];
    private int record_number = -1;
    private int actual_number = -1;
    private int end_of_free_space = -1;
    private int page_id = -1;       //page id is the unique id of every data block
    TableSchema schema;
    Boolean is_revised;     // to show whether there is a difference between the file and the memory
    private Record record;


    //initialize by creating new data block
    public DataBlock(String d_name,String t_name,int p_id,TableSchema sa){
        DB_name = d_name;
        table_name = t_name;
        is_revised = true;
        schema = sa;
        data = new byte[Util.DiskBlockSize];
        record_number = 0;
        actual_number = 0;
        end_of_free_space = Util.DiskBlockSize - 1;
        page_id = p_id;
        System.arraycopy(Util.int2byte(0),0,data,0,4);
        System.arraycopy(Util.int2byte(end_of_free_space),0,data,4,4);
        System.arraycopy(Util.int2byte(0),0,data,8,4);
    }

    // initialize by data from file
    public DataBlock(String d_name,String t_name,byte[] read_data,int p_id,TableSchema sa){
        DB_name = d_name;
        table_name = t_name;
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
        System.arraycopy(data,8,temp,0,4);
        actual_number = Util.byte2int(temp);
    }

    public int getPageId(){return page_id;}

    public byte[] getData(){return data;}

    public TableSchema getSchema(){return schema;}

    public int getActualNumber() {return actual_number;}

    public int insertOneRecord(Record record){
        //return true if the record is successfully inserted
        //the record has to be valid
        if(!record.isValid()) {
            throw new IllegalArgumentException("the insert value is not valid");
        }
        if(!isFreeSpaceEnough(record))
            throw new IllegalArgumentException("No enough space to insert");

        int insert_id = record_number;
        if(actual_number < record_number){
            for(int i = 0; i < record_number; i++){
                if(extractOneRecordHeader(i)[0] == 0){
                   insert_id = i;
                   break;
                }
            }
        }

        int size = record.getSize();
        System.arraycopy(record.toBytes(),0,data,end_of_free_space - size + 1,size);
        System.arraycopy(Util.int2byte(end_of_free_space),0,data,12 + 8 * insert_id,4);
        System.arraycopy(Util.int2byte(size),0,data,16 + 8 * insert_id,4);
        end_of_free_space -= size;
        System.arraycopy(Util.int2byte(end_of_free_space),0,data,4,4);
        actual_number += 1;
        System.arraycopy(Util.int2byte(actual_number),0,data,8,4);
        if(insert_id == record_number){
            record_number += 1;
            System.arraycopy(Util.int2byte(record_number),0,data,0,4);
        }
        is_revised = true;
        return insert_id;
    }

    public Boolean isFreeSpaceEnough(Record record){
        int record_size = record.getSize();
        if(actual_number < record_number){
            if(record_size > end_of_free_space - 8 * record_number - 12)
                return false;
            return true;
        }
        if(record_size > end_of_free_space - 8 * record_number - 20)
            return false;
        else
            return true;
    }


    public Record extractOneRecord(int record_id){
        if (record_id >= record_number) return null;
        int[] rd = extractOneRecordHeader(record_id);
        int record_size = rd[1],record_location = rd[0];
        if(record_location != 0){
            byte[] record_bytes = new byte[record_size];
            System.arraycopy(data,record_location - record_size + 1,record_bytes,0,record_size);
            return new Record(record_bytes,schema);
        }
        return null;
    }

    public Record[] extractAllRecords(){
        Record[] records = new Record[actual_number];
        int j = 0;
        Record tp;
        for(int i = 0; i < record_number; i++){
            tp = extractOneRecord(i);
            if(tp != null){
                records[j] = tp;
                j += 1;
            }
        }
        return records;
    }

    public int[] extractOneRecordHeader(int record_id){
        //record_location; record_size
        byte[] temp = new byte[4];
        System.arraycopy(data,12 + record_id * 8,temp,0,4);
        int record_location = Util.byte2int(temp);
        System.arraycopy(data,16 + record_id * 8,temp,0,4);
        int record_size = Util.byte2int(temp);
        return new int[]{record_location,record_size};
    }

    public Boolean deleteOneRecord(int record_id){
        //return true if the record is successfully deleted
        if (record_id >= record_number)
            throw new IllegalArgumentException("record id is too large");

        //for all situations
        int[] rd = extractOneRecordHeader(record_id);
        int record_location = rd[0],record_size = rd[1];
        //there are records on the left of the record
        //for header parts
        int[] tp;
        for (int i = 0;i < record_number; i++){
            tp = extractOneRecordHeader(i);
            if(tp[0] != 0 && tp[0] < record_location)
                System.arraycopy(Util.int2byte(tp[0] + record_size),0,data,12 + i * 8,4);
        }
        //for record parts
        int len = record_location - record_size - end_of_free_space;
        byte[] temp = new byte[len];
        System.arraycopy(data,end_of_free_space + 1, temp,0,len);
        System.arraycopy(temp,0,data,end_of_free_space + 1 + record_size,len);

        //update record_number and end_of_free_space
        if(record_id == record_number - 1){
            record_number -= 1;
            System.arraycopy(Util.int2byte(record_number),0,data,0,4);
        }else{
            System.arraycopy(Util.int2byte(0),0,data,12 + record_id * 8,4);
        }
        end_of_free_space += record_size;
        System.arraycopy(Util.int2byte(end_of_free_space),0,data,4,4);
        actual_number -= 1;
        System.arraycopy(Util.int2byte(actual_number),0,data,8,4);
        is_revised = true;
        return true;
    }

    public int updateOneRecord(int record_id,Record record){
        if(deleteOneRecord(record_id))
            return insertOneRecord(record);
        throw new IllegalArgumentException("Delete fails when updating");
    }

    public int getEndOfFreeSpace(){return end_of_free_space;}
    public int getRecordNumber(){return record_number;}

    public static void main(String[] args){
        System.out.println("-- DataBlock --");
    }
}
