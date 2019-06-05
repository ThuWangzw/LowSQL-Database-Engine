import jdk.nashorn.internal.runtime.RecompilableScriptFunctionData;

import java.util.ArrayList;

public class HashTable {
    TableAttribute index_attrs;
    RecordList[] hashtable;
    HashTable(TableAttribute index_attrs, int record_num){
        this.index_attrs = index_attrs;
        hashtable = new RecordList[record_num*4/3];
        for(int i=0; i<hashtable.length; i++){
            hashtable[i] = null;
        }
    }

    public void add(Record record){
        int key = record2key(record, index_attrs);
        int bucket = ((key%hashtable.length)+hashtable.length)%hashtable.length;
        if(hashtable[bucket] == null){
            hashtable[bucket] = new RecordList();
        }
        hashtable[bucket].records.add(record);
    }

    public ArrayList<Record> find(Record record, TableAttribute attribute) {
        int key = record2key(record, attribute);
        int bucket = ((key%hashtable.length)+hashtable.length)%hashtable.length;
        if(hashtable[bucket] == null){
            return new ArrayList<>();
        }
        return hashtable[bucket].records;
    }

    public int record2key(Record record, TableAttribute attribute){
        Field[] fds = record.getFields();
        int key_length = 0;
        key_length = attribute.getLengthLimit();
        byte[] key = new byte[key_length],temp;
        int cur_len = 0;
        for (Field f : fds){
            if(attribute.getAttributeName().equals(f.getAttribute().getAttributeName())){
                temp = f.toBytes();
                System.arraycopy(temp,0,key,cur_len,temp.length);
                break;
            }
        }
        int res = 0;
        for(int i=0; i<key.length; i++){
            byte one = key[i];
            switch (i%4){
                case 0:
                    res += one&0xff;
                    break;
                case 1:
                    res += (one&0xff)<<8;
                    break;
                case 2:
                    res += (one&0xff)<<16;
                    break;
                case 3:
                    res += (one&0xff)<<24;
                    break;
            }
        }
        return res;

    }

    class RecordList{
        RecordList(){
            records = new ArrayList<>();
        }
        public ArrayList<Record> records;
    }
}
