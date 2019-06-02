import java.util.ArrayList;

/* Internal Node Body Structure
|--------2---------|-------k--------|  ...
| Pointer of Nodes |   search key   |
|------------------|----------------|

if the node has to be store in two blocks.
the end of first block should end with: search key ;while the start of the second block should start with pointer
 */


public class BTreeInternalNode extends BTreeNode {
    ArrayList<Short> pointers = new ArrayList<>();

    //create a new Internal node
    public BTreeInternalNode(int m,int id,int p_id,int l_id,int r_id,int prior_id,int next_id,int prior_number,
                             int next_number,IndexBuffer bf,TableAttribute[] attrs,String db_name,String table_name){
        super(m,id,p_id,l_id,r_id,prior_id,next_id,prior_number,next_number,bf,attrs,db_name,table_name);
        System.arraycopy(Util.byte2bytes((byte)0),0,index_data,0,1);
        type = 0;
    }

    public BTreeInternalNode(int m,int n_id,byte[]data,IndexBuffer bf,TableAttribute[] attrs,String db_name,String table_name){
        super(m,n_id,data,bf,attrs,db_name,table_name);
        free_space = Util.DiskBlockSize - (19 + key_number * (key_length + 2));
        IndexDataFromByte();
        savePointer();
    }


    public void borrowFromRightBrother(int index){
        BTreeInternalNode right_bro = (BTreeInternalNode) buffer.getNode(right_bro_id,DB_name,table_name,index_attrs);
        //borrow from right bro node
        short pt = right_bro.getPointer(0);
        byte[] right_min_key = right_bro.keys.get(0);
        right_bro.deleteKeyPointer(0);
        deleteKeyPointer(index);
        buffer.getNode(pt,DB_name,table_name,index_attrs).updateParent(getHeadNode().node_id);
        insertOneKeyPointer(right_min_key,pt);
    }


    public void borrowFromLeftBrother(int index){
        BTreeInternalNode left_bro = (BTreeInternalNode) buffer.getNode(left_bro_id,DB_name,table_name,index_attrs);
        BTreeInternalNode left_bro_end = (BTreeInternalNode)left_bro.getTailNode();
        //borrow from left bro node
        short pt = left_bro_end.getPointer(left_bro_end.key_number - 1);
        byte[] left_biggest_key = left_bro_end.getBiggestKey();
        left_bro_end.deleteKeyPointer(left_bro_end.key_number - 1);
        deleteKeyPointer(index);
        buffer.getNode(pt,DB_name,table_name,index_attrs).updateParent(getHeadNode().node_id);
        insertOneKeyPointer(left_biggest_key,pt);
    }

    public void mergeWithRightNode(int index){
        deleteKeyPointer(index);
        //already delete the key
        BTreeInternalNode right_bro = (BTreeInternalNode) buffer.getNode(right_bro_id,DB_name,table_name,index_attrs);

        if (right_bro.parent_id != parent_id){
            int origin_parent = parent_id;
            updateParent(right_bro.parent_id);
            BTreeInternalNode parent_node = (BTreeInternalNode) buffer.getNode(right_bro.parent_id,DB_name,table_name,index_attrs);
            parent_node.updateKeyPointer(right_bro.getBiggestKey(),null,getHeadNode().node_id);
            parent_node = (BTreeInternalNode) buffer.getNode(origin_parent,DB_name,table_name,index_attrs);

            parent_node.deleteOneKey(getBiggestKey());
        }else{
            BTreeInternalNode parent_node = (BTreeInternalNode) buffer.getNode(parent_id,DB_name,table_name,index_attrs);
            parent_node.deleteOneKey(right_bro.getBiggestKey());
        }

        //right bro
        short right_kid;
        for (int i = 0; i < right_bro.key_number; i++){
            right_kid = right_bro.getPointer(i);
            buffer.getNode(right_kid,DB_name,table_name,index_attrs).updateParent(getHeadNode().node_id);
            insertOneKeyPointer(right_bro.keys.get(i),right_kid);
        }
        if (right_bro.next_id != 0){
            BTreeInternalNode p = right_bro;
            do{
                p = (BTreeInternalNode)buffer.getNode(p.next_id,DB_name,table_name,index_attrs);
                for (int i = 0; i < p.key_number; i++){
                    insertOneKeyPointer(p.keys.get(i),p.getPointer(i));
                }
            }while (p.next_id != 0);
        }

        right_bro.deleteNodeList();
    }

    public void mergeWithLeftNode(int index){
        deleteKeyPointer(index);
        BTreeInternalNode left_bro = (BTreeInternalNode) buffer.getNode(left_bro_id,DB_name,table_name,index_attrs);

        BTreeInternalNode parent_node = (BTreeInternalNode) buffer.getNode(parent_id,DB_name,table_name,index_attrs);
        parent_node.deleteOneKey(left_bro.getBiggestKey());

        short left_kid;
        for (int i = 0; i < left_bro.key_number; i++){
            left_kid = left_bro.getPointer(i);
            buffer.getNode(left_kid,DB_name,table_name,index_attrs).updateParent(getHeadNode().node_id);
            insertOneKeyPointer(left_bro.keys.get(i),left_bro.getPointer(i));
        }
        if (left_bro.next_id != 0){
            BTreeInternalNode p = left_bro;
            do{
                p = (BTreeInternalNode)buffer.getNode(p.next_id,DB_name,table_name,index_attrs);
                for (int i = 0; i < p.key_number; i++){
                    insertOneKeyPointer(p.keys.get(i),p.getPointer(i));
                }
            }while (p.next_id != 0);
        }

        left_bro.deleteNodeList();
    }


    public void deleteOneKey(byte[] key) {
        int total_key_number = prior_key_number + next_key_number + key_number;

        //locate
        if(next_id != 0){
            BTreeInternalNode next_node = (BTreeInternalNode) buffer.getNode(next_id,DB_name,table_name,index_attrs);
            if(compare2key(next_node.keys.get(0),key) != Util.G) {
                next_node.deleteOneKey(key);
                return;
            }
        }
        if(prior_id != 0) {
            BTreeInternalNode prior_node = (BTreeInternalNode) buffer.getNode(prior_id,DB_name,table_name,index_attrs);
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
                BTreeInternalNode right_bro = (BTreeInternalNode) buffer.getNode(right_bro_id,DB_name,table_name,index_attrs);
                if (right_bro.key_number + right_bro.next_key_number > threshold) {
                    borrowFromRightBrother(index);
                } else {
                    //merge with the right one
                    mergeWithRightNode(index);
                }
            } else {
                //the rightest node of the layer
                BTreeInternalNode left_bro = (BTreeInternalNode) buffer.getNode(left_bro_id,DB_name,table_name,index_attrs);
                if (left_bro.key_number + left_bro.next_key_number > threshold) {
                    borrowFromLeftBrother(index);
                }else{
                    //merge with the left one
                    mergeWithLeftNode(index);
                }
            }
        }else{
            //delete directly
            BTreeInternalNode parent_node;
            if (parent_id != 0) {
                byte[] biggest_key = getBiggestKey();
                deleteKeyPointer(index);
                if (compare2key(key,biggest_key) == Util.E) {
                    parent_node = (BTreeInternalNode) buffer.getNode(parent_id,DB_name,table_name,index_attrs);
                    parent_node.updateKeyPointer(biggest_key, getBiggestKey(), getHeadNode().node_id);
                }
            }
            else {
                //root node
                deleteKeyPointer(index);
                if(key_number == 1){
                    //update root node
                    BTreeNode n_r = buffer.getNode(getPointer(0),DB_name,table_name,index_attrs);
                    n_r.updateParent(0);
                    buffer.newRootNode(n_r.node_id,DB_name,table_name,index_attrs);
                    deleteNodeList();
                }
            }
        }
    }

        public void insertOneKeyPointer(byte[] new_key, int pointer_id){
        //new key can't be the same with the origin one
        int total_number = prior_key_number + key_number + next_key_number;

        if (total_number == key_number){

            //node in one block
            if(total_number == M){
                //split
                byte[] old_biggest = getBiggestKey();
                int mid = (int) Math.ceil((double)M/2) - 1;
                BTreeInternalNode split_node;
                //add split node to parent node
                if(M % 2 == 1){
                    if(compare2key(keys.get(mid),new_key) == Util.G)
                        split_node = splitAt(mid-1);
                    else
                        split_node = splitAt(mid);
                }else{
                    split_node = splitAt(mid);
                }
                if(parent_id != 0){
                    //insert to left node
                    BTreeInternalNode parent_node = (BTreeInternalNode)buffer.getNode(parent_id,DB_name,table_name,index_attrs);
                    parent_node.insertOneKeyPointer(split_node.getBiggestKey(),split_node.node_id);
                }
                if(compare2key(keys.get(mid),new_key) == Util.G){
                    insertOneKeyPointer(new_key,pointer_id);
                }else{
                    split_node.insertOneKeyPointer(new_key,pointer_id);
                }
                for (int i = 0; i < split_node.key_number; i ++){
                    buffer.getNode(split_node.getPointer(i),DB_name,table_name,index_attrs).updateParent(split_node.node_id);
                }
            }else{
                if(key_number == 0){
                    insertKeyPointer(0,new_key,pointer_id);
                    return;
                }
                int insert_index = BinarySearch(new_key,0,key_number - 1);
                if (free_space >= 2 + key_length){
                    insertKeyPointer(insert_index,new_key,pointer_id);
                }else{
                    BTreeInternalNode new_node = new BTreeInternalNode(M,buffer.getFreeId(DB_name,table_name,index_attrs),parent_id,left_bro_id,right_bro_id,
                            node_id,0,prior_key_number + key_number,0,buffer,index_attrs,DB_name,table_name);
                    byte[] biggest_key = keys.get(key_number - 1);
                    short pt = getPointer(key_number -  1);
                    updateNextBro(new_node.node_id);
                    buffer.addNewNode(new_node);
                    deleteKeyPointer(key_number - 1);
                    new_node.insertOneKeyPointer(biggest_key,pt);
                    insertOneKeyPointer(new_key,pointer_id);
                }
            }
        }else{
            //node in many blocks
            if (total_number == M){
                int mid = (int) Math.ceil((double)M/2) - 1;
                BTreeInternalNode split_node;
                if (mid >= key_number + prior_key_number){
                    //this node do not split
                    BTreeInternalNode next_node = (BTreeInternalNode) buffer.getNode(next_id,DB_name,table_name,index_attrs);
                    next_node.insertOneKeyPointer(new_key,pointer_id);
                    return;
                }else if (mid < prior_key_number) {
                    BTreeInternalNode prior_node = (BTreeInternalNode) buffer.getNode(prior_id,DB_name,table_name,index_attrs);
                    prior_node.insertOneKeyPointer(new_key,pointer_id);
                }else{
                    byte[] old_biggest = getBiggestKey();
                    int old_next_id = next_id;
                    if(M % 2 == 1){
                        if(compare2key(keys.get(mid),new_key) == Util.G){
                            if (mid == prior_key_number){
                                //special case - split
                                BTreeInternalNode prior_node = (BTreeInternalNode) buffer.getNode(prior_id,DB_name,table_name,index_attrs);
                                prior_node.updateNextBro(0);
                                prior_node.updateNextKeyNumber(0);
                                prior_node.updateNumberToLeft();
                                //bro
                                BTreeInternalNode head_node = (BTreeInternalNode) prior_node.getHeadNode();
                                BTreeInternalNode origin_right_node = (BTreeInternalNode) buffer.getNode(prior_node.right_bro_id,DB_name,table_name,index_attrs);
                                origin_right_node.updateLeftBro(node_id);
                                prior_node.updateRightBro(node_id);

                                updatePriorBro(0);
                                updatePriorKeyNumber(0);
                                updateNumberToRight();
                                updateLeftBro(head_node.node_id);

                                prior_node.insertOneKeyPointer(new_key,pointer_id);
                                if(parent_id !=0) {
                                    BTreeInternalNode parent_node = (BTreeInternalNode)buffer.getNode(parent_id,DB_name,table_name,index_attrs);
                                    parent_node.insertOneKeyPointer(prior_node.getBiggestKey(), head_node.node_id);
                                    parent_node.updateKeyPointer(getBiggestKey(), null, node_id);
                                }else{
                                    createRootNode(prior_node,this);
                                }
                                return;
                            }
                            split_node = splitAt(mid- prior_key_number - 1);
                        }
                        else
                            split_node = splitAt(mid - prior_key_number);
                    }else{
                        split_node = splitAt(mid - prior_key_number);
                    }
                    if(parent_id != 0){
                        BTreeInternalNode parent_node = (BTreeInternalNode)buffer.getNode(parent_id,DB_name,table_name,index_attrs);
                        if(old_next_id != 0)
                            parent_node.updateKeyPointer(old_biggest,getBiggestKey(),getHeadNode().node_id);
                        parent_node.insertOneKeyPointer(split_node.getBiggestKey(),split_node.node_id);
                    }
                    if(compare2key(keys.get(mid - prior_key_number),new_key) == Util.G){
                        insertOneKeyPointer(new_key,pointer_id);
                    }else{
                        split_node.insertOneKeyPointer(new_key,pointer_id);
                    }
                    for (int i = 0; i < split_node.key_number; i ++){
                        buffer.getNode(split_node.getPointer(i),DB_name,table_name,index_attrs).updateParent(split_node.node_id);
                    }
                }
            }else{
                if(next_id != 0){
                    BTreeInternalNode next_node = (BTreeInternalNode) buffer.getNode(next_id,DB_name,table_name,index_attrs);
                    if(compare2key(next_node.keys.get(0),new_key) != Util.G) {
                        next_node.insertOneKeyPointer(new_key, pointer_id);
                        return;
                    }
                }
                if(prior_id != 0){
                    BTreeInternalNode prior_node = (BTreeInternalNode) buffer.getNode(prior_id,DB_name,table_name,index_attrs);
                    if(compare2key(prior_node.keys.get(prior_node.key_number - 1),new_key) != Util.L){
                        prior_node.insertOneKeyPointer(new_key,pointer_id);
                        return;
                    }
                }
                int insert_index = BinarySearch(new_key,0,key_number - 1);
                if (free_space >= 2 + key_length){
                    insertKeyPointer(insert_index,new_key,pointer_id);
                }else{
                    BTreeInternalNode new_node = new BTreeInternalNode(M,buffer.getFreeId(DB_name,table_name,index_attrs),parent_id,left_bro_id,right_bro_id,
                            node_id,0,prior_key_number + key_number,0,buffer,index_attrs,DB_name,table_name);
                    if(next_id != 0){
                        BTreeNode origin_next_node = buffer.getNode(next_id,DB_name,table_name,index_attrs);
                        origin_next_node.updatePriorBro(new_node.node_id);
                        new_node.updateNextBro(origin_next_node.node_id);
                        new_node.updateNextKeyNumber(origin_next_node.key_number + origin_next_node.next_key_number);
                    }
                    updateNextBro(new_node.node_id);
                    buffer.addNewNode(new_node);
                    byte[] biggest_key = keys.get(key_number - 1);
                    short pt = getPointer(key_number -  1);
                    deleteKeyPointer(key_number - 1);
                    new_node.insertOneKeyPointer(biggest_key,pt);
                    insertOneKeyPointer(new_key,pointer_id);
                }
            }
        }
    }


    public void updateKeyPointer(byte[] old_key,byte[] new_key,int pointer_id){
        //keep the order
        int index = BinarySearch(old_key,0,key_number - 1);
        if(index == key_number || compare2key(keys.get(index),old_key) != Util.E)
            return;
        if(new_key != null){
            System.arraycopy(new_key,0,index_data,19 + index * (2 + key_length) + 2,key_length);
            keys.set(index,new_key);
        }
        if (index == key_number - 1 && parent_id != 0){
            BTreeInternalNode parent_node = (BTreeInternalNode)buffer.getNode(parent_id,DB_name,table_name,index_attrs);
            parent_node.updateKeyPointer(old_key, new_key,node_id);
        }

        System.arraycopy(Util.short2byte((short)pointer_id),0,index_data,19 + index*(2 + key_length),2);
        is_changed = true;
        savePointer();
    }


    public void updateBiggestKey(byte[] new_key){
        if( compare2key(new_key,keys.get(key_number - 1)) == Util.G){
            System.arraycopy(new_key,0,index_data,19 + (key_number - 1) * (2 + key_number) + 2,key_length);
            keys.set(key_number - 1,new_key);
        }
    }

    public void updatePointerOfBiggestKey(int pointer_id){
        System.arraycopy(Util.short2byte((short)pointer_id),0,index_data,19 + (key_number - 1) *(2 + key_number),2);
    }

    public void deleteKeyPointer(int index){
        if(index == key_number - 1 && parent_id != 0 && next_id == 0){
            BTreeInternalNode parent_node = (BTreeInternalNode) buffer.getNode(parent_id,DB_name,table_name,index_attrs);
            parent_node.updateKeyPointer(keys.get(index),keys.get(index - 1),getHeadNode().node_id);
        }

        keys.remove(index);
        System.arraycopy(index_data,19 + (index + 1) * (2 + key_length),index_data,19 + (2 + key_length) * index,(2 + key_length) * (key_number - 1 -index));
        free_space += 2 + key_length;
        updateKeyNumber(key_number - 1);
        if(key_number == 0)
            buffer.deleteNode(this);
        is_changed = true;

        savePointer();
    }

    public short getPointer(int index){
        byte[] bits2 = new byte[2];
        System.arraycopy(index_data,19 + (2 + key_length)* index,bits2,0,2);
        return Util.byte2short(bits2);
    }

    public void savePointer(){
        pointers = new ArrayList<>();
        for (int i = 0; i < key_number; i++){
            pointers.add(i,getPointer(i));
        }
    }

    public void insertKeyPointer(int index,byte[] new_key,int pointer){
        if(key_number != 0 && index == key_number && parent_id != 0 && next_id == 0){
                BTreeInternalNode parent_node = (BTreeInternalNode)buffer.getNode(parent_id,DB_name,table_name,index_attrs);
                parent_node.updateKeyPointer(getBiggestKey(),new_key,getHeadNode().node_id);
        }

        int moving_len = (key_number - index) * (key_length + 2);
        byte[] temp = new byte[moving_len];
        System.arraycopy(index_data,19 + (2 + key_length) * index,temp,0,moving_len);
        System.arraycopy(temp,0,index_data,19 + (2 + key_length) * (index + 1),moving_len);
        System.arraycopy(Util.short2byte((short)pointer),0,index_data,19 + (2 + key_length) * index,2);
        System.arraycopy(new_key,0,index_data,19 + (2 + key_length) * index + 2,key_length);

        if(index == key_number)
            keys.add(new_key);
        else
            keys.add(index,new_key);
        free_space -= (2 + key_length);
        updateKeyNumber(key_number + 1);
        is_changed = true;

        savePointer();
    }



    public void IndexDataFromByte(){
        keys = new ArrayList<>();
        byte[] temp;
        for (int i = 0; i < key_number ; i++){
            temp = new byte[key_length];
            System.arraycopy(index_data,19 + (2 + key_length) * i + 2,temp,0,key_length);
            keys.add(temp);
        }
    }


    public BTreeInternalNode splitAt(int index){
        //root node if parent_id == 0
        if (parent_id == 0){
            BTreeInternalNode root_node = new BTreeInternalNode(M,buffer.getFreeId(DB_name,table_name,index_attrs),0,0,0,0,0,0,0,buffer,index_attrs,DB_name,table_name);
            updateParent(root_node.node_id);
            root_node.insertOneKeyPointer(getBiggestKey(),node_id);
            buffer.addNewNode(root_node);
            buffer.newRootNode(root_node.node_id,DB_name,table_name,index_attrs);
        }

        BTreeInternalNode new_node = new BTreeInternalNode(M,buffer.getFreeId(DB_name,table_name,index_attrs),parent_id,0,right_bro_id,0,0,0,0,buffer,index_attrs,DB_name,table_name);
        if (index < key_number -1){
            byte[] temp_key;
            short pt;
            int origin_key_number = key_number;
            for(int i = index + 1; i < origin_key_number; i++){
                pt = getPointer(index+1);
                temp_key = keys.get(index+1);
                new_node.insertOneKeyPointer(temp_key,pt);
                deleteKeyPointer(index+1);
            }

            BTreeInternalNode temp_node;
            if(next_id != 0){
                //new node next node id
                new_node.updateNextBro(next_id);
                //new node next node - prior id
                temp_node = (BTreeInternalNode) buffer.getNode(new_node.next_id,DB_name,table_name,index_attrs);
                temp_node.updatePriorBro(new_node.node_id);
                //next key number
                new_node.updateNumberToRight();
                //this next id
                updateNextBro(0);
                updateNextKeyNumber(0);
            }
            updateNumberToLeft();

            //neighbor
            //right bro node
            if(right_bro_id != 0){
                BTreeInternalNode origin_right_node = (BTreeInternalNode) buffer.getNode(right_bro_id,DB_name,table_name,index_attrs);
                origin_right_node.updateLeftBro(new_node.node_id);
            }
            updateRightBro(new_node.node_id);
            //left bro
            new_node.updateLeftBro(getHeadNode().node_id);
            //sons'parent id
            BTreeNode temp;
            for (int i = 0; i < new_node.key_number; i ++){
                temp = buffer.getNode(new_node.getPointer(i),DB_name,table_name,index_attrs);
                temp.updateParent(new_node.node_id);
            }
            //freespace
            buffer.addNewNode(new_node);
            return new_node;
        }else{
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println("-- Internal Node --");
        //BTreeInternalNode n = new BTreeInternalNode();
        //n.getIndexData();
    }



}
