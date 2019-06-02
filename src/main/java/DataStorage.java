import java.util.ArrayList;
import java.util.List;
import java.io.RandomAccessFile;


public class DataStorage{
    //Every table in a database has this instance
    String DB_name;
    String table_name;
    RandomAccessFile raf;
    long block_number;      //actual block number in the file
    TableSchema schema;
    DataBuffer buffer;
    ArrayList<Integer> free_blocks;
    int current_free_block_id;

    public DataStorage(String db_name, String t_name, TableSchema sa,DataBuffer bf){
        DB_name = db_name;
        schema = sa;
        table_name = t_name;
        buffer = bf;
        //if the the table is first established, create 1 block of the storage
        try{
            raf = new RandomAccessFile(Util.DataStorageDir + DB_name + "_" + table_name + ".bin","rw");
            if(raf.length() == 0){
                block_number = 0;
                buffer.appendDataBlock(this);
            }else{
                block_number = raf.length() / Util.DiskBlockSize;
            }
            raf.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        //blocks that has free space
        updateFreeBlock();
        current_free_block_id = getPossibleBlock();
    }


    public DataPointer insert(Record record){
        int record_id;
        DataBlock blk;
        do{
            blk = buffer.getNode(DB_name,table_name,current_free_block_id);
            if(blk.isFreeSpaceEnough(record)){
                record_id = blk.insertOneRecord(record);
                return new DataPointer(current_free_block_id,record_id);
            }
            cancelFreeBlock(current_free_block_id);
            current_free_block_id = getPossibleBlock();
        }while(true);
    }

    public void delete(DataPointer pt){
        buffer.getNode(DB_name,table_name,pt.page_id).deleteOneRecord(pt.record_id);
        freshFreeBlock(pt.page_id);
    }

    public void updateFreeBlock(){
        if(free_blocks == null){
            free_blocks = new ArrayList<>();
            int int_number = (int)Math.ceil((double)block_number / 32);
            for(int i = 0; i < int_number; i++){
                free_blocks.add(-1);
            }
        }else{
            if(free_blocks.size() < (int)Math.ceil((double)block_number / 32)){
                free_blocks.add(-1);
            }
        }
    }

    public int getPossibleBlock(){
        int len = free_blocks.size();
        int number,cur = -1,i,j;
        for(i = 0; i < len; i++){
            number = free_blocks.get(i);
            if(number == 0){
                cur = -1;
                continue;
            } else{
                for (j = 31; j >= 0; j--){
                   if (((number >> j) & 1) == 1)
                       break;
                }
                cur = 32 - j;
                break;
            }
        }
        if (cur == -1){
            buffer.appendDataBlock(DB_name,table_name);
            updateFreeBlock();
            return (int)(block_number - 1);
        }else{
                int expected_id = 32 * i - 1 + cur;
                if(expected_id >= block_number){
                    buffer.appendDataBlock(DB_name,table_name);
                    updateFreeBlock();
                }

                return expected_id;
        }
    }

    public void cancelFreeBlock(int id){
        int index = id / 32, k = id - index * 32,number = free_blocks.get(index);
        number -= (1 << (31 - k));
        free_blocks.remove(index);
        free_blocks.add(index,number);
    }

    public void freshFreeBlock(int id){
        int index = id / 32, k = id - index * 32,number = free_blocks.get(index);
        number += 1 << (31 - k);
        free_blocks.remove(index);
        free_blocks.add(index,number);
    }



    public static void main(String[] args){
        System.out.println("-- DataStorage --");
    }

}