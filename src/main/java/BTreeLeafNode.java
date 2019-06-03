import javax.xml.crypto.Data;
import java.util.ArrayList;

/* Leaf Node - Slotted Page Structure
|----------k---------|---------2---------|----------2--------|---...----|-----4----|-----4-----|
|      search key    |location of pointer| number of pointer |   ...    |  page id | record id |
|--------------------|-------------------|-------------------|---...----|----------|-----------|
 */

public class BTreeLeafNode extends BTreeNode{
    public BTreeLeafNode(int m,int id,int p_id,int l_id,int r_id,int prior_id,int next_id,int p_n,int n_n,IndexBuffer bf,TableAttribute[] attrs,String db_name,String table_name){
        super(m,id,p_id,l_id,r_id,prior_id,next_id,p_n,n_n,bf,attrs,db_name,table_name);
        System.arraycopy(Util.byte2bytes((byte)1),0,index_data,0,1);
        type = 1;
    }

    public BTreeLeafNode(int m,int n_id,byte[]data,IndexBuffer bf,TableAttribute[] attrs,String db_name,String table_name){
        super(m,n_id,data,bf,attrs,db_name,table_name);
        free_space = Util.DiskBlockSize - (19 + key_number * (key_length + 4) + Util.DiskBlockSize - 1 - end_of_free_space);
        IndexDataFromByte();
    }

    public void deleteAllKeyPointer(int index){
        if(index == key_number - 1 && parent_id != 0 && next_id == 0){
            BTreeInternalNode parent_node = (BTreeInternalNode) buffer.getNode(parent_id,DB_name,table_name,index_attrs);
            parent_node.updateKeyPointer(keys.get(index),keys.get(index - 1),getHeadNode().node_id);
        }

        short[] l_n = getLocationAndNumber(index);
        short p_location = l_n[0],p_number = l_n[1];

        short[] t;
        for(int i = 0; i < key_number; i++){
            t = getLocationAndNumber(i);
            if(t[0] < p_location){
                System.arraycopy(Util.short2byte((short)(t[0] + p_number * 8)),0,index_data,19 + (key_length + 4) * i + key_length,2);
            }
        }

        keys.remove(index);
        System.arraycopy(index_data,19 + (index + 1)*(key_length + 4),index_data,19 + index * (key_length + 4),(key_number - 1 - index) * (key_length + 4));

        int temp_len = p_location - p_number * 8 - end_of_free_space;
        byte[] temp = new byte[temp_len];
        System.arraycopy(index_data,end_of_free_space + 1,temp,0,temp_len);
        end_of_free_space += p_number * 8;
        System.arraycopy(temp,0,index_data,end_of_free_space + 1,temp_len);
        free_space += p_number * 8 + key_length + 4;
        updateKeyNumber(key_number - 1);
        System.arraycopy(Util.short2byte((short)end_of_free_space),0,index_data,3,2);
        is_changed = true;
        if(key_number == 0)
            deleteNode();
    }

    public void deleteOneKeyPointer(int key_index,int pointer_reverse_index){
        short[] l_n = getLocationAndNumber(key_index);
        short p_location = l_n[0],p_number = l_n[1];

        short[] t;
        for(int i = 0; i < key_number; i++){
            t = getLocationAndNumber(i);
            if(t[0] < p_location){
                System.arraycopy(Util.short2byte((short)(t[0] + 8)),0,index_data,19 + (key_length + 4) * i + key_length,2);
            }
        }
        System.arraycopy(Util.short2byte((short)(p_number - 1)),0,index_data,19 + key_index * (key_length + 4) + key_length + 2,2);

        int temp_len = p_location - (pointer_reverse_index + 1) * 8 - end_of_free_space;
        byte[] temp = new byte[temp_len];
        System.arraycopy(index_data,end_of_free_space + 1,temp,0,temp_len);
        end_of_free_space +=  8;
        System.arraycopy(temp,0,index_data,end_of_free_space + 1,temp_len);
        free_space += 8;
        System.arraycopy(Util.short2byte((short)end_of_free_space),0,index_data,3,2);
        is_changed = true;
    }

    public void deleteOnePointer(byte[] key,DataPointer pt){
        //locate first
        int total_key_number = prior_key_number + next_key_number + key_number;

        //locate
        if(next_id != 0){
            BTreeLeafNode next_node = (BTreeLeafNode) buffer.getNode(next_id,DB_name,table_name,index_attrs);
            if(compare2key(next_node.keys.get(0),key) != Util.G) {
                next_node.deleteOnePointer(key,pt);
                return;
            }
        }
        if(prior_id != 0) {
            BTreeLeafNode prior_node = (BTreeLeafNode) buffer.getNode(prior_id,DB_name,table_name,index_attrs);
            if (compare2key(prior_node.keys.get(prior_node.key_number - 1), key) != Util.L) {
                prior_node.deleteOnePointer(key,pt);
                return;
            }
        }
        //in current block
        int index = BinarySearch(key, 0, key_number - 1);
        if (index == key_number || compare2key(key, keys.get(index)) != Util.E) {
            return;
        }
        DataPointer[] stored_pointer = getPointer(index);
        if(stored_pointer.length == 1){
            if(stored_pointer[0].record_id == pt.record_id && stored_pointer[0].page_id == pt.page_id)
                deleteOneKey(key);
            return;
        }
        for (int i = 0 ; i < stored_pointer.length; i++){
            if (stored_pointer[i].record_id == pt.record_id && stored_pointer[i].page_id == pt.page_id){
                deleteOneKeyPointer(index,i);
                break;
            }
        }
    }


    public void borrowFromRightBrother(int index){
        BTreeLeafNode right_bro = (BTreeLeafNode) buffer.getNode(right_bro_id,DB_name,table_name,index_attrs);
        //borrow from right bro node
        DataPointer[] pointers = right_bro.getPointer(0);
        byte[] right_min_key = right_bro.keys.get(0);
        right_bro.deleteAllKeyPointer(0);
        deleteAllKeyPointer(index);
        for(int i = 0; i < pointers.length; i++){
            insertOneKeyPointer(right_min_key,pointers[i].page_id,pointers[i].record_id);
        }
    }

    public void borrowFromLeftBrother(int index){
        BTreeLeafNode left_bro = (BTreeLeafNode) buffer.getNode(left_bro_id,DB_name,table_name,index_attrs);
        BTreeLeafNode left_bro_end = (BTreeLeafNode)left_bro.getTailNode();
        //borrow from left bro node
        DataPointer[] pointers = left_bro_end.getPointer(left_bro_end.key_number - 1);
        byte[] left_biggest_key = left_bro_end.getBiggestKey();
        left_bro_end.deleteAllKeyPointer(left_bro_end.key_number - 1);
        deleteAllKeyPointer(index);
        for(int i = 0; i < pointers.length; i++){
            insertOneKeyPointer(left_biggest_key,pointers[i].page_id,pointers[i].record_id);
        }
    }


    public void mergeWithRightNode(int index){
        deleteAllKeyPointer(index);
        //already delete the key
        BTreeLeafNode right_bro = (BTreeLeafNode) buffer.getNode(right_bro_id,DB_name,table_name,index_attrs);

        if (right_bro.parent_id != parent_id){
            int origin_parent_id = parent_id;
            updateParent(right_bro.parent_id);
            BTreeInternalNode parent_node = (BTreeInternalNode) buffer.getNode(right_bro.parent_id,DB_name,table_name,index_attrs);
            parent_node.updateKeyPointer(right_bro.getBiggestKey(),null,getHeadNode().node_id);
            parent_node = (BTreeInternalNode) buffer.getNode(origin_parent_id,DB_name,table_name,index_attrs);
            parent_node.deleteOneKey(getBiggestKey());
        }else{
            BTreeInternalNode parent_node = (BTreeInternalNode) buffer.getNode(parent_id,DB_name,table_name,index_attrs);
            parent_node.deleteOneKey(right_bro.getBiggestKey());
        }

        //right bro
        for (int i = 0; i < right_bro.key_number; i++){
            DataPointer[] pts = right_bro.getPointer(i);
            for(int j = 0; j < pts.length; j++){
                insertOneKeyPointer(right_bro.keys.get(i),pts[j].page_id,pts[j].record_id);
            }
        }
        if (right_bro.next_id != 0){
            BTreeLeafNode p = right_bro;
            do{
                p = (BTreeLeafNode)buffer.getNode(p.next_id,DB_name,table_name,index_attrs);
                for (int i = 0; i < p.key_number; i++){
                   DataPointer[] pts = p.getPointer(i);
                   for(int j = 0; j < pts.length; j++){
                       insertOneKeyPointer(p.keys.get(i),pts[j].page_id,pts[j].record_id);
                   }
                }
            }while (p.next_id != 0);
        }
        right_bro.deleteNodeList();
    }

    public void mergeWithLeftNode(int index){
        deleteAllKeyPointer(index);
        BTreeLeafNode left_bro = (BTreeLeafNode) buffer.getNode(left_bro_id,DB_name,table_name,index_attrs);

        BTreeInternalNode parent_node = (BTreeInternalNode) buffer.getNode(parent_id,DB_name,table_name,index_attrs);
        parent_node.deleteOneKey(left_bro.getBiggestKey());

        for (int i = 0; i < left_bro.key_number; i++){
            DataPointer[] pts = left_bro.getPointer(i);
            for(int j = 0; j < pts.length; j++){
                insertOneKeyPointer(left_bro.keys.get(i),pts[j].page_id,pts[j].record_id);
            }
        }
        if (left_bro.next_id != 0){
            BTreeLeafNode p = left_bro;
            do{
                p = (BTreeLeafNode)buffer.getNode(p.next_id,DB_name,table_name,index_attrs);
                for (int i = 0; i < p.key_number; i++){
                    DataPointer[] pts = p.getPointer(i);
                    for(int j = 0; j < pts.length; j++){
                        insertOneKeyPointer(p.keys.get(i),pts[j].page_id,pts[j].record_id);
                    }
                }
            }while (p.next_id != 0);
        }

        left_bro.deleteNodeList();
    }

    public void deleteOneKey(byte[] key) {
        //locate first
        int total_key_number = prior_key_number + next_key_number + key_number;

        //locate
        if(next_id != 0){
            BTreeLeafNode next_node = (BTreeLeafNode) buffer.getNode(next_id,DB_name,table_name,index_attrs);
            if(compare2key(next_node.keys.get(0),key) != Util.G) {
                next_node.deleteOneKey(key);
                return;
            }
        }
        if(prior_id != 0) {
            BTreeLeafNode prior_node = (BTreeLeafNode) buffer.getNode(prior_id,DB_name,table_name,index_attrs);
            if (compare2key(prior_node.keys.get(prior_node.key_number - 1), key) != Util.L) {
                prior_node.deleteOneKey(key);
                return;
            }
        }
        //in current block
        int index = BinarySearch(key, 0, key_number - 1);
        if (index == key_number || compare2key(key, keys.get(index)) != Util.E) {
            return;
        }
        int threshold = (int) Math.ceil((double) M / 2);
        if (parent_id != 0 && total_key_number == threshold) {
            if (right_bro_id != 0) {
                //not the rightest node of the layer
                BTreeLeafNode right_bro = (BTreeLeafNode) buffer.getNode(right_bro_id,DB_name,table_name,index_attrs);
                if (right_bro.key_number + right_bro.next_key_number > threshold) {
                    borrowFromRightBrother(index);
                } else {
                    //merge with the right one
                    mergeWithRightNode(index);
                }
            } else {
                //the rightest node of the layer
                BTreeLeafNode left_bro = (BTreeLeafNode) buffer.getNode(left_bro_id,DB_name,table_name,index_attrs);
                if (left_bro.key_number + left_bro.next_key_number > threshold) {
                    borrowFromLeftBrother(index);
                }else{
                    //merge with the left one
                    mergeWithLeftNode(index);
                }
            }
        }else{
            deleteAllKeyPointer(index);
        }
    }




    public void insertOneKeyPointer(byte[] new_key,int page_id,int record_id){
        if(key_number == 0){
            //insert directly
            keys.add(new_key);
            System.arraycopy(new_key,0,index_data,19,key_length);
            System.arraycopy(Util.short2byte((short)end_of_free_space),0,index_data,19 + key_length,2);
            System.arraycopy(Util.short2byte((short)1),0,index_data,21 + key_length,2);
            System.arraycopy(Util.int2byte(page_id),0,index_data,end_of_free_space - 7,4);
            System.arraycopy(Util.int2byte(record_id),0,index_data,end_of_free_space - 3,4);
            free_space -= (12 + key_length);
            end_of_free_space -= 8;
            is_changed = true;
            updateKeyNumber(key_number + 1);
            System.arraycopy(Util.short2byte((short)end_of_free_space),0,index_data,3,2);
            return;
        }

        int total_key_number = prior_key_number + next_key_number + key_number;

        if (total_key_number == key_number){
            //in one block
            int insert_index = BinarySearch(new_key,0,keys.size() - 1);
            if (insert_index != key_number && compare2key(new_key,keys.get(insert_index)) == Util.E){
                //new key already in leaf node  -- add pointer
                //check if there is enough space to insert
                if (free_space >= 8){
                    insertPointerWithExistedKey(insert_index,page_id,record_id);
                }else{
                    //data overflow
                    //create a new node
                    BTreeLeafNode new_node = new BTreeLeafNode(M,buffer.getFreeId(DB_name,table_name,index_attrs),parent_id,left_bro_id,
                            right_bro_id,node_id,0,prior_key_number + key_number,0,buffer,index_attrs,DB_name,table_name);
                    byte[] biggest_key = keys.get(key_number - 1);
                    DataPointer[] pt = getPointer(key_number -  1);
                    updateNextBro(new_node.node_id);
                    buffer.addNewNode(new_node);
                    deleteAllKeyPointer(key_number - 1);
                    for(int i = 0 ; i < pt.length;i++){
                        new_node.insertOneKeyPointer(biggest_key,pt[i].page_id,pt[i].record_id);
                    }
                    insertOneKeyPointer(new_key,page_id,record_id);
                }
            }else{
                //in one block, new key not in leaf node
                if(total_key_number == M){
                    //split
                    byte[] old_biggest = getBiggestKey();
                    int mid = (int) Math.ceil((double)M/2) - 1;
                    BTreeLeafNode split_node;
                    if(M % 2 == 1){
                        if(compare2key(keys.get(mid),new_key) == Util.G)
                            split_node = splitAt(mid-1);
                         else
                            split_node = splitAt(mid);
                    }else{
                        split_node = splitAt(mid);
                    }
                    if(parent_id != 0) {
                        BTreeInternalNode parent_node = (BTreeInternalNode)buffer.getNode(parent_id,DB_name,table_name,index_attrs);
                        parent_node.insertOneKeyPointer(split_node.getBiggestKey(), split_node.node_id);
                    }
                    if(compare2key(keys.get(mid),new_key) == Util.G){
                        insertOneKeyPointer(new_key,page_id,record_id);
                    }else{
                        split_node.insertOneKeyPointer(new_key,page_id,record_id);
                    }
                }else{
                    //check if there is enough space to insert
                    if (free_space >= 12 + key_length){
                        //enough space to insert
                        insertKeyPointer(insert_index,new_key,page_id,record_id);
                    }else{
                        //no enough space to insert
                        BTreeLeafNode new_node = new BTreeLeafNode(M,buffer.getFreeId(DB_name,table_name,index_attrs),parent_id,left_bro_id,right_bro_id,node_id,0,
                                key_number + prior_key_number,0,buffer,index_attrs,DB_name,table_name);
                        byte[] biggest_key = keys.get(key_number - 1);
                        DataPointer[] pt = getPointer(key_number -  1);
                        updateNextBro(new_node.node_id);
                        buffer.addNewNode(new_node);
                        deleteAllKeyPointer(key_number - 1);
                        for(int i = 0 ; i < pt.length;i++){
                            new_node.insertOneKeyPointer(biggest_key,pt[i].page_id,pt[i].record_id);
                        }
                        insertOneKeyPointer(new_key,page_id,record_id);
                    }
                }
            }
        }
        else{
            //node in multiple blocks
            int insert_index = BinarySearch(new_key,0,keys.size() - 1);
            if (insert_index != key_number && compare2key(new_key,keys.get(insert_index)) == Util.E){
                //new key already in leaf node  -- add pointer
                //check if there is enough space to insert
                if (free_space >= 8){
                    insertPointerWithExistedKey(insert_index,page_id,record_id);
                }else{
                    //data overflow
                    //get the next node
                    BTreeLeafNode new_node;
                    Boolean create_new_node = false;
                    if(next_id != 0){
                        new_node =(BTreeLeafNode) buffer.getNode(next_id,DB_name,table_name,index_attrs);
                        if(new_node.free_space < getPairTotalSize(key_number - 1)){
                            new_node = new BTreeLeafNode(M,buffer.getFreeId(DB_name,table_name,index_attrs),parent_id,left_bro_id,right_bro_id,node_id,0,key_number+prior_key_number,0,buffer,index_attrs,DB_name,table_name);
                            buffer.addNewNode(new_node);
                            create_new_node = true;
                        }
                    }else{
                        new_node = new BTreeLeafNode(M,buffer.getFreeId(DB_name,table_name,index_attrs),parent_id,left_bro_id,right_bro_id,node_id,0,key_number+prior_key_number,0,buffer,index_attrs,DB_name,table_name);
                        buffer.addNewNode(new_node);
                        create_new_node = true;
                    }
                    if(create_new_node && next_id != 0){
                        BTreeNode origin_next_node = buffer.getNode(next_id,DB_name,table_name,index_attrs);
                        origin_next_node.updatePriorBro(new_node.node_id);
                        new_node.updateNextBro(origin_next_node.node_id);
                        new_node.updateNextKeyNumber(origin_next_node.key_number + origin_next_node.next_key_number);
                    }
                    updateNextBro(new_node.node_id);
                    byte[] biggest_key = keys.get(key_number - 1);
                    DataPointer[] pt = getPointer(key_number -  1);
                    deleteAllKeyPointer(key_number - 1);
                    for(int i = 0 ; i < pt.length;i++){
                        new_node.insertOneKeyPointer(biggest_key,pt[i].page_id,pt[i].record_id);
                    }
                    insertOneKeyPointer(new_key,page_id,record_id);
                }
            }else{
                //in many block, new key not in leaf node
                if(total_key_number == M){
                    int mid = (int) Math.ceil((double)M/2) - 1;
                    if (mid >= key_number + prior_key_number){
                        //this node do not split
                        BTreeLeafNode next_node = (BTreeLeafNode) buffer.getNode(next_id,DB_name,table_name,index_attrs);
                        next_node.insertOneKeyPointer(new_key,page_id,record_id);
                        return;
                    }else if (mid < prior_key_number) {
                        BTreeLeafNode prior_node = (BTreeLeafNode) buffer.getNode(prior_id,DB_name,table_name,index_attrs);
                        prior_node.insertOneKeyPointer(new_key,page_id,record_id);
                        return;
                    }else {
                     //this node split
                        BTreeLeafNode split_node;
                        byte[] old_biggest = getBiggestKey();
                        int origin_next_id = next_id;
                        if(M % 2 == 1) {
                            if (compare2key(keys.get(mid - prior_key_number), new_key) == Util.G) {
                                if(mid == prior_key_number ){
                                    //special case - split
                                    BTreeLeafNode prior_node = (BTreeLeafNode) buffer.getNode(prior_id,DB_name,table_name,index_attrs);
                                    prior_node.updateNextBro(0);
                                    prior_node.updateNextKeyNumber(0);
                                    prior_node.updateNumberToLeft();
                                    //bro
                                    BTreeLeafNode head_node = (BTreeLeafNode) prior_node.getHeadNode();
                                    BTreeLeafNode origin_right_node = (BTreeLeafNode) buffer.getNode(prior_node.right_bro_id,DB_name,table_name,index_attrs);
                                    origin_right_node.updateLeftBro(node_id);
                                    prior_node.updateRightBro(node_id);

                                    updatePriorBro(0);
                                    updatePriorKeyNumber(0);
                                    updateNumberToRight();
                                    updateLeftBro(head_node.node_id);

                                    if(parent_id != 0){
                                        BTreeInternalNode parent_node = (BTreeInternalNode)buffer.getNode(parent_id,DB_name,table_name,index_attrs);
                                        parent_node.insertOneKeyPointer(prior_node.getBiggestKey(),head_node.node_id);
                                        parent_node.updateKeyPointer(getBiggestKey(),null,node_id);
                                    }else{
                                        createRootNode(prior_node,this);
                                    }
                                    prior_node.insertOneKeyPointer(new_key,page_id,record_id);
                                    return;
                                }
                                split_node = splitAt(mid - prior_key_number - 1);
                            }else{
                                split_node = splitAt(mid - prior_key_number);
                            }
                        }else{
                            split_node = splitAt(mid - prior_key_number);
                        }

                        if(parent_id != 0){
                            BTreeInternalNode parent_node = (BTreeInternalNode)buffer.getNode(parent_id,DB_name,table_name,index_attrs);
                            if(origin_next_id != 0)
                                parent_node.updateKeyPointer(old_biggest,getBiggestKey(),getHeadNode().node_id);
                            parent_node.insertOneKeyPointer(split_node.getBiggestKey(),split_node.node_id);
                        }
                        if(compare2key(keys.get(mid - prior_key_number),new_key) == Util.G){
                            insertOneKeyPointer(new_key,page_id,record_id);
                        }else{
                            split_node.insertOneKeyPointer(new_key,page_id,record_id);
                        }
                    }
                }else{
                    if(next_id != 0){
                        BTreeLeafNode next_node = (BTreeLeafNode) buffer.getNode(next_id,DB_name,table_name,index_attrs);
                        if(compare2key(next_node.keys.get(0),new_key) != Util.G) {
                            next_node.insertOneKeyPointer(new_key, page_id, record_id);
                            return;
                        }
                    }
                    if(prior_id != 0){
                        BTreeLeafNode prior_node = (BTreeLeafNode) buffer.getNode(prior_id,DB_name,table_name,index_attrs);
                        if(compare2key(prior_node.keys.get(prior_node.key_number - 1),new_key) != Util.L){
                            prior_node.insertOneKeyPointer(new_key,page_id,record_id);
                            return;
                        }
                    }
                    //check if there is enough space to insert
                    if (free_space >= 12 + key_length){
                        //enough space to insert
                        insertKeyPointer(insert_index,new_key,page_id,record_id);
                    }else{
                        //no enough space to insert
                        BTreeLeafNode new_node;
                        Boolean create_new_node = false;
                        if(next_id != 0){
                            new_node =(BTreeLeafNode) buffer.getNode(next_id,DB_name,table_name,index_attrs);
                            if(new_node.free_space < getPairTotalSize(key_number - 1)){
                                new_node = new BTreeLeafNode(M,buffer.getFreeId(DB_name,table_name,index_attrs),parent_id,left_bro_id,right_bro_id,node_id,0,key_number+prior_key_number,0,buffer,index_attrs,DB_name,table_name);
                                buffer.addNewNode(new_node);
                                create_new_node = true;
                            }
                        }else{
                            new_node = new BTreeLeafNode(M,buffer.getFreeId(DB_name,table_name,index_attrs),parent_id,left_bro_id,right_bro_id,node_id,0,key_number+prior_key_number,0,buffer,index_attrs,DB_name,table_name);
                            buffer.addNewNode(new_node);
                            create_new_node = true;
                        }
                        if(create_new_node && next_id != 0){
                            BTreeNode origin_next_node = buffer.getNode(next_id,DB_name,table_name,index_attrs);
                            origin_next_node.updatePriorBro(new_node.node_id);
                            new_node.updateNextBro(origin_next_node.node_id);
                            new_node.updateNextKeyNumber(origin_next_node.key_number + origin_next_node.next_key_number);
                        }
                        updateNextBro(new_node.node_id);
                        byte[] biggest_key = keys.get(key_number - 1);
                        DataPointer[] pt = getPointer(key_number -  1);
                        deleteAllKeyPointer(key_number - 1);
                        for(int i = 0 ; i < pt.length;i++){
                            new_node.insertOneKeyPointer(biggest_key,pt[i].page_id,pt[i].record_id);
                        }
                        insertOneKeyPointer(new_key,page_id,record_id);
                    }
                }
            }
        }
    }

    public BTreeLeafNode splitAt(int index){
        //root node if parent_id == 0
        if (parent_id == 0){
            BTreeInternalNode root_node = new BTreeInternalNode(M,buffer.getFreeId(DB_name,table_name,index_attrs),0,0,0,0,0,0,0,buffer,index_attrs,DB_name,table_name);
            updateParent(root_node.node_id);
            root_node.insertOneKeyPointer(getBiggestKey(),getHeadNode().node_id);
            buffer.addNewNode(root_node);
            buffer.newRootNode(root_node.node_id,DB_name,table_name,index_attrs);
        }
        //current node include the index, rest stored in new node
        BTreeLeafNode new_node = new BTreeLeafNode(M,buffer.getFreeId(DB_name,table_name,index_attrs),parent_id,0,right_bro_id,0,0,0,0,buffer,index_attrs,DB_name,table_name);
        if(index < key_number - 1){
            byte[] temp_key;
            int origin_key_number = key_number;
            for(int i = index + 1; i < origin_key_number; i++){
                DataPointer[] pt = getPointer(index + 1);
                temp_key = keys.get(index + 1);
                for (int j = 0; j < pt.length; j++){
                    new_node.insertOneKeyPointer(temp_key,pt[j].page_id,pt[j].record_id);
                }
                deleteAllKeyPointer(index + 1);
            }

            BTreeLeafNode temp_node;
            if(next_id != 0){
                //new node next node id
                new_node.updateNextBro(next_id);
                //new node next node - prior id
                temp_node = (BTreeLeafNode) buffer.getNode(new_node.next_id,DB_name,table_name,index_attrs);
                temp_node.updatePriorBro(new_node.node_id);
                //next key number
                new_node.updateNumberToRight();
                //this next id
                updateNextBro(0);
                updateNextKeyNumber(0);
            }
            updateNumberToLeft();

            //neighbor
            if(right_bro_id != 0){
                BTreeLeafNode origin_right_node = (BTreeLeafNode) buffer.getNode(right_bro_id,DB_name,table_name,index_attrs);
                origin_right_node.updateLeftBro(new_node.node_id);
            }
            //right bro node
            updateRightBro(new_node.node_id);
            //left bro
            new_node.updateLeftBro(getHeadNode().node_id);
            buffer.addNewNode(new_node);
            return new_node;
        }else{
            return null;
        }
    }


    public void insertPointerWithExistedKey(int index,int page_id,int record_id){
        short[] l_n = getLocationAndNumber(index);
        short p_location = l_n[0],p_number = l_n[1];
        System.arraycopy(Util.short2byte((short)(p_number + 1)),0,index_data,19+index*(key_length + 4) + key_length + 2,2);
        byte[] bits4 = new byte[4];
        int temp_str_len = p_location - 8 * p_number - end_of_free_space;
        System.arraycopy(index_data,end_of_free_space + 1,index_data,end_of_free_space - 7,temp_str_len);
        System.arraycopy(Util.int2byte(page_id),0,index_data,p_location - p_number * 8 - 7,4);
        System.arraycopy(Util.int2byte(record_id),0,index_data,p_location - p_number * 8 - 3,4);
        for (int i = index + 1;i < key_number;i++){
            System.arraycopy(index_data,19 + i * (key_length + 4) + key_length,bits4,0,2);
            System.arraycopy(Util.short2byte((short)(Util.byte2short(bits4)-8)),0,index_data,19 + i * (key_length + 4) + key_length,2);
        }
        end_of_free_space -= 8;
        free_space -= 8;
        is_changed = true;
        System.arraycopy(Util.short2byte((short)end_of_free_space),0,index_data,3,2);
    }


    public void insertKeyPointer(int index,byte[] new_key,int page_id,int record_id){
        if(key_number != 0 && parent_id != 0 && index == key_number && next_id == 0){
            BTreeInternalNode parent_node = (BTreeInternalNode)buffer.getNode(parent_id,DB_name,table_name,index_attrs);
            parent_node.updateKeyPointer(getBiggestKey(),new_key,getHeadNode().node_id);
        }

        int moving_len = (key_length + 4) * (key_number - index);
        byte[] temp_str = new byte[moving_len];
        System.arraycopy(index_data,19 + index * (key_length + 4),temp_str,0,moving_len);
        System.arraycopy(temp_str,0,index_data,19 + (index + 1) * (key_length + 4),moving_len);
        System.arraycopy(new_key,0,index_data,19+index*(key_length + 4),key_length);
        System.arraycopy(Util.short2byte((short)end_of_free_space),0,index_data,19+index*(key_length + 4) + key_length,2);
        System.arraycopy(Util.short2byte((short)1),0,index_data,19+index*(key_length+4)+key_length + 2,2);

        System.arraycopy(Util.int2byte(page_id),0,index_data,end_of_free_space - 7,4);
        System.arraycopy(Util.int2byte(record_id),0,index_data,end_of_free_space - 3,4);
        if(index == key_number)
            keys.add(new_key);
        else
            keys.add(index,new_key);

        end_of_free_space -= 8;
        free_space -= (12 + key_length);
        updateKeyNumber(key_number + 1);
        System.arraycopy(Util.short2byte((short)end_of_free_space),0,index_data,3,2);
        is_changed = true;
    }

    public short[] getLocationAndNumber(int index){
        byte[] bits4 = new byte[4];
        System.arraycopy(index_data,19+ index*(key_length + 4) + key_length,bits4,0,2);
        short p_location = Util.byte2short(bits4);
        System.arraycopy(index_data,19+ index*(key_length + 4) + key_length + 2,bits4,0,2);
        short p_number = Util.byte2short(bits4);
        return new short[]{p_location,p_number};
    }


    public DataPointer[] getPointer(int index){
        short[] temp = getLocationAndNumber(index);
        byte[] bits4 = new byte[4];
        int p_location = temp[0],p_number = temp[1];

        int page_id = 0,record_id = 0;
        DataPointer[] pt = new DataPointer[p_number];
        //reverse order
        for (short i = 0; i < p_number; i++) {
            System.arraycopy(index_data, p_location - 8 * i - 7, bits4, 0, 4);
            page_id = Util.byte2int(bits4);
            System.arraycopy(index_data,p_location- 8 * i - 3,bits4,0,4);
            record_id = Util.byte2int(bits4);
            pt[i] = new DataPointer(page_id,record_id);
        }
        return pt;
    }

    public int getPairTotalSize(int index){
        return key_length + 4 + getLocationAndNumber(index)[1] * 8;
    }

    public void IndexDataFromByte(){
        //knowing index_attrs
        keys = new ArrayList<>();
        byte[] temp;
        for (int i = 0; i < key_number; i++){
            temp = new byte[key_length];
            System.arraycopy(index_data,19 + i * (key_length + 4),temp,0,key_length);
            keys.add(temp);
        }
    }
}
