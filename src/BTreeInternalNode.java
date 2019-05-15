import java.util.ArrayList;

/* Internal Node Body Structure
|--------2---------|-------k--------|  ...
| Pointer of Nodes |   search key   |
|------------------|----------------|

if the node has to be store in two blocks.
the end of first block should end with: search key ;while the start of the second block should start with pointer
 */


public class BTreeInternalNode extends BTreeNode {
    //create a new Internal node
    public BTreeInternalNode(int m,int id,int p_id,int l_id,int r_id,int prior_id,int next_id,int prior_number,int next_number,IndexBuffer bf,TableAttribute[] attrs){
        super(m,id,p_id,l_id,r_id,prior_id,next_id,prior_number,next_number,bf,attrs);
        System.arraycopy(Util.byte2bytes((byte)0),0,index_data,0,1);
    }


    public void borrowFromRightBrother(int index){
        BTreeInternalNode right_bro = (BTreeInternalNode) buffer.getNode(right_bro_id);
        //borrow from right bro node
        short pt = right_bro.getPointer(0);
        byte[] right_min_key = new byte[key_length];
        System.arraycopy(right_bro.index_data,21,right_min_key,0,key_length);
        right_bro.deleteKeyPointer(0);
        byte[] old_key = getBiggestKey();
        deleteKeyPointer(index);
        //parent node
        BTreeInternalNode parent_node = (BTreeInternalNode)buffer.getNode(parent_id);
        parent_node.updateKeyPointer(old_key,right_min_key,getHeadNode().node_id);
        insertOneKeyPointer(right_min_key,pt);
    }


    public void borrowFromLeftBrother(int index){
        BTreeInternalNode left_bro = (BTreeInternalNode) buffer.getNode(left_bro_id);
        BTreeInternalNode left_bro_end = (BTreeInternalNode)left_bro.getTailNode();
        //borrow from left bro node
        short pt = left_bro_end.getPointer(left_bro_end.key_number - 1);
        byte[] left_biggest_key = left_bro_end.getBiggestKey();
        left_bro_end.deleteKeyPointer(left_bro_end.key_number - 1);
        deleteKeyPointer(index);
        //parent node
        BTreeInternalNode parent_node = (BTreeInternalNode)buffer.getNode(left_bro.parent_id);
        parent_node.updateKeyPointer(left_biggest_key,left_bro.getBiggestKey(),left_bro.node_id);
        insertOneKeyPointer(left_biggest_key,pt);
    }

    public void mergeWithRightNode(int index){
        byte[] origin_left_key = getBiggestKey();
        deleteKeyPointer(index);
        //already delete the key
        BTreeInternalNode right_bro = (BTreeInternalNode) buffer.getNode(right_bro_id);
        //right bro
        for (int i = 0; i < right_bro.key_length; i++){
            insertOneKeyPointer(keys.get(i),right_bro.getPointer(i));
        }
        if (right_bro.next_id != 0){
            BTreeInternalNode p = right_bro;
            do{
                p = (BTreeInternalNode)buffer.getNode(p.next_id);
                for (int i = 0; i < p.key_length; i++){
                    insertOneKeyPointer(keys.get(i),p.getPointer(i));
                }
            }while (p.next_id != 0);
        }
        if (right_bro.parent_id != parent_id){
            int origin_parent = parent_id;
            updateParent(right_bro.parent_id);
            BTreeInternalNode parent_node = (BTreeInternalNode) buffer.getNode(right_bro.parent_id);
            parent_node.updateKeyPointer(right_bro.getBiggestKey(),null,getHeadNode().node_id);
            parent_node = (BTreeInternalNode) buffer.getNode(origin_parent);

            parent_node.deleteOneKey(origin_left_key);
        }else{
            BTreeInternalNode parent_node = (BTreeInternalNode) buffer.getNode(parent_id);
            parent_node.updateKeyPointer(right_bro.getBiggestKey(),null,getHeadNode().node_id);

            parent_node.deleteOneKey(origin_left_key);
        }
        right_bro.deleteNodeList();
    }

    public void mergeWithLeftNode(int index){
        deleteKeyPointer(index);
        BTreeInternalNode left_bro = (BTreeInternalNode) buffer.getNode(left_bro_id);
        byte[] origin_left_key = left_bro.getBiggestKey();
        for (int i = 0; i < left_bro.key_length; i++){
            insertOneKeyPointer(keys.get(i),left_bro.getPointer(i));
        }
        if (left_bro.next_id != 0){
            BTreeInternalNode p = left_bro;
            do{
                p = (BTreeInternalNode)buffer.getNode(p.next_id);
                for (int i = 0; i < p.key_length; i++){
                    insertOneKeyPointer(keys.get(i),p.getPointer(i));
                }
            }while (p.next_id != 0);
        }
        BTreeInternalNode parent_node = (BTreeInternalNode) buffer.getNode(parent_id);
        parent_node.deleteOneKey(origin_left_key);

        left_bro.deleteNodeList();
    }


    public void deleteOneKey(byte[] key) {
        int total_key_number = prior_key_number + next_key_number + key_number;

        //locate
        if(next_id != 0){
            BTreeInternalNode next_node = (BTreeInternalNode) buffer.getNode(next_id);
            if(compare2key(next_node.keys.get(0),key) != Util.G) {
                next_node.deleteOneKey(key);
                return;
            }
        }
        if(prior_id != 0) {
            BTreeInternalNode prior_node = (BTreeInternalNode) buffer.getNode(prior_id);
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

        int threshold = (int) Math.ceil(M / 2);
        if (parent_id != 0 && total_key_number == threshold) {
            if (right_bro_id != 0) {
                //not the rightest node of the layer
                BTreeInternalNode right_bro = (BTreeInternalNode) buffer.getNode(right_bro_id);
                if (right_bro.key_number + right_bro.next_key_number > threshold) {
                    borrowFromRightBrother(index);
                } else {
                    //merge with the right one
                    deleteKeyPointer(index);
                    mergeWithRightNode(index);
                }
            } else {
                //the rightest node of the layer
                BTreeInternalNode left_bro = (BTreeInternalNode) buffer.getNode(left_bro_id);
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
                    parent_node = (BTreeInternalNode) buffer.getNode(parent_id);
                    parent_node.updateKeyPointer(biggest_key, getBiggestKey(), getHeadNode().node_id);
                }
            }
            else {
                //root node
                deleteKeyPointer(index);
                if(key_number == 1){
                    //update root node
                    BTreeNode n_r = buffer.getNode(getPointer(0));
                    n_r.updateParent(0);
                    buffer.newRootNode(n_r.node_id);
                    deleteNodeList();
                }
            }
        }
    }

        public void insertOneKeyPointer(byte[] new_key, int pointer_id){
        //new key can't be the same with the origin one
        int total_number = prior_key_number + key_number + next_key_number;

        if (total_number == key_number){
            BTreeInternalNode parent_node = (BTreeInternalNode)buffer.getNode(parent_id);

            //node in one block
            if(total_number == M){
                //split
                byte[] old_biggest = getBiggestKey();
                int mid = (int) Math.ceil(M/2) - 1;
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
                if(compare2key(keys.get(mid),new_key) == Util.G){
                    insertOneKeyPointer(new_key,pointer_id);
                    parent_node.insertOneKeyPointer(getBiggestKey(),node_id);
                    parent_node.updateKeyPointer(split_node.getBiggestKey(),null,split_node.node_id);
                }else{
                    split_node.insertOneKeyPointer(new_key,pointer_id);
                    parent_node.insertOneKeyPointer(getBiggestKey(),node_id);
                    parent_node.updateKeyPointer(old_biggest,split_node.getBiggestKey(),split_node.node_id);
                }
            }else{
                int insert_index = BinarySearch(new_key,0,key_number - 1);
                if (free_space >= 2 + key_length){
                    if(insert_index == key_number){
                        parent_node.updateKeyPointer(getBiggestKey(),new_key,node_id);
                    }
                    insertKeyPointer(insert_index,new_key,pointer_id);
                }else{
                    BTreeInternalNode new_node = new BTreeInternalNode(M,buffer.getFreeId(),parent_id,left_bro_id,right_bro_id,
                            node_id,0,prior_key_number + key_number - 1,0,buffer,index_attrs);
                    byte[] biggest_key = keys.get(key_length - 1);
                    short pt = getPointer(key_length -  1);
                    deleteKeyPointer(key_number - 1);
                    new_node.insertOneKeyPointer(biggest_key,pt);
                    buffer.addNewNode(new_node);
                    next_id = new_node.node_id;
                    insertOneKeyPointer(new_key,pointer_id);
                }
            }
        }else{
            BTreeInternalNode parent_node = (BTreeInternalNode)buffer.getNode(parent_id);
            //node in many blocks
            if (total_number == M){
                int mid = (int) Math.ceil(M/2) - 1;
                BTreeInternalNode split_node;
                if (mid >= key_number + prior_key_number){
                    //this node do not split
                    BTreeInternalNode next_node = (BTreeInternalNode) buffer.getNode(next_id);
                    next_node.insertOneKeyPointer(new_key,pointer_id);
                    return;
                }else if (mid < prior_key_number) {
                    BTreeInternalNode prior_node = (BTreeInternalNode) buffer.getNode(prior_id);
                    prior_node.insertOneKeyPointer(new_key,pointer_id);
                }else{
                    byte[] old_biggest = getBiggestKey();
                    if(M % 2 == 1){
                        if(compare2key(keys.get(mid),new_key) == Util.G){
                            if (mid == prior_key_number){
                                //special case - split
                                BTreeInternalNode prior_node = (BTreeInternalNode) buffer.getNode(prior_id);
                                prior_node.next_id = 0;
                                prior_node.updateNextKeyNumber(0);
                                prior_node.updateNumberToLeft();
                                //bro
                                BTreeInternalNode head_node = (BTreeInternalNode) prior_node.getHeadNode();
                                BTreeInternalNode origin_right_node = (BTreeInternalNode) buffer.getNode(prior_node.right_bro_id);
                                origin_right_node.updateLeftBro(node_id);
                                prior_node.updateRightBro(node_id);

                                prior_id = 0;
                                updatePriorKeyNumber(0);
                                updateNumberToRight();
                                updateLeftBro(head_node.node_id);

                                prior_node.insertOneKeyPointer(new_key,pointer_id);
                                parent_node.insertOneKeyPointer(prior_node.getBiggestKey(),head_node.node_id);
                                parent_node.updateKeyPointer(getBiggestKey(),null,node_id);
                                return;
                            }
                            split_node = splitAt(mid- prior_key_number - 1);
                        }
                        else
                            split_node = splitAt(mid - prior_key_number);
                    }else{
                        split_node = splitAt(mid - prior_key_number);
                    }
                    if(compare2key(keys.get(mid - prior_key_number),new_key) == Util.G){
                        insertOneKeyPointer(new_key,pointer_id);
                        parent_node.insertOneKeyPointer(getBiggestKey(),getHeadNode().node_id);
                        parent_node.updateKeyPointer(split_node.getBiggestKey(),null,split_node.node_id);
                    }else{
                        split_node.insertOneKeyPointer(new_key,pointer_id);
                        parent_node.insertOneKeyPointer(getBiggestKey(),getHeadNode().node_id);
                        parent_node.updateKeyPointer(old_biggest,split_node.getBiggestKey(),split_node.node_id);
                    }
                }
            }else{
                if(next_id != 0){
                    BTreeInternalNode next_node = (BTreeInternalNode) buffer.getNode(next_id);
                    if(compare2key(next_node.keys.get(0),new_key) != Util.G) {
                        next_node.insertOneKeyPointer(new_key, pointer_id);
                        return;
                    }
                }
                if(prior_id != 0){
                    BTreeInternalNode prior_node = (BTreeInternalNode) buffer.getNode(prior_id);
                    if(compare2key(prior_node.keys.get(prior_node.key_number - 1),new_key) != Util.L){
                        prior_node.insertOneKeyPointer(new_key,pointer_id);
                        return;
                    }
                }
                int insert_index = BinarySearch(new_key,0,key_number - 1);
                if (free_space >= 2 + key_length){
                    if(next_id == 0 && insert_index == key_number){
                        parent_node.updateKeyPointer(getBiggestKey(),new_key,getHeadNode().node_id);
                    }
                    insertKeyPointer(insert_index,new_key,pointer_id);
                }else{
                    BTreeInternalNode new_node = (BTreeInternalNode) buffer.getNode(next_id);
                    byte[] biggest_key = keys.get(key_length - 1);
                    short pt = getPointer(key_length -  1);
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
        if(new_key != null){
            System.arraycopy(new_key,0,index_data,19 + index * (2 + key_number) + 2,key_length);
        }
        System.arraycopy(Util.short2byte((short)pointer_id),0,index_data,19 + index*(2 + key_number),2);
        keys.set(index,new_key);
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
        keys.remove(index);
        System.arraycopy(index_data,19 + (index + 1) * (2 + key_length),index_data,19 + (2 + key_length) * index,(2 + key_length) * (key_number - 1 -index));
        free_space += 2 + key_length;
        updateKeyNumber(key_number - 1);
        if(key_number == 0)
            buffer.deleteNode(this);
    }

    public short getPointer(int index){
        byte[] bits2 = new byte[2];
        System.arraycopy(index_data,19 + (2 + key_length)* index,bits2,0,2);
        return Util.byte2short(bits2);
    }

    public void insertKeyPointer(int index,byte[] new_key,int pointer){
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
            BTreeInternalNode root_node = new BTreeInternalNode(M,buffer.getFreeId(),0,0,0,0,0,0,0,buffer,index_attrs);
            parent_id = root_node.node_id;
            root_node.insertOneKeyPointer(getBiggestKey(),node_id);
        }

        BTreeInternalNode new_node = new BTreeInternalNode(M,buffer.getFreeId(),parent_id,0,right_bro_id,0,0,0,0,buffer,index_attrs);
        if (index < key_number -1){
            System.arraycopy(index_data,19 + (2 + key_number)*(index + 1),new_node.index_data,19,(key_number - 1)*(2 + key_length));

            //key
            new_node.key_number = key_number - 1 - index;
            System.arraycopy(Util.short2byte((short)new_node.key_number),0,new_node.index_data,1,2);
            new_node.free_space -= new_node.key_number * (2 + key_length);
            new_node.IndexDataFromByte();

            key_number = index + 1;
            for (int i = index + 1; i < key_number; i++){
                keys.remove(i);
            }
            System.arraycopy(Util.short2byte((short)key_number),0,index_data,1,2);

            BTreeInternalNode temp_node;
            if(next_id != 0){
                //new node next node id
                new_node.next_id = next_id;
                System.arraycopy(Util.short2byte((short)next_id),0,new_node.index_data,13,2);
                //new node next node - prior id
                temp_node = (BTreeInternalNode) buffer.getNode(new_node.next_id);
                temp_node.prior_id = new_node.node_id;
                System.arraycopy(Util.short2byte((short)temp_node.prior_id),0,temp_node.index_data,11,2);
                //next key number
                new_node.updateNumberToRight();
                //this next id
                next_id = 0;
                System.arraycopy(Util.short2byte((short)next_id),0,index_data,13,2);
                updateNextKeyNumber(0);
            }
            updateNumberToLeft();

            //neighbor
            BTreeInternalNode origin_right_node = (BTreeInternalNode) buffer.getNode(right_bro_id);
            origin_right_node.updateLeftBro(new_node.node_id);
            //right bro node
            updateRightBro(new_node.node_id);
            //left bro
            new_node.updateLeftBro(getHeadNode().node_id);
            //sons'parent id
            BTreeNode temp;
            for (int i = 0; i < new_node.key_number; i ++){
                temp = buffer.getNode(new_node.getPointer(i));
                temp.updateParent(new_node.node_id);
            }
            //freespace
            free_space += (key_number - index - 1) * (2 + key_length);
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
