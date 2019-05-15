import java.util.ArrayList;

/* B Tree block header
Every block shall have the header

|-------1-------|-------2------------|----2-------------|----2----|----2----|----2----|---2---|---2----|---2---|---2--|
|     Type      |Number of Search key|end of free space |parent id|left id  |right id |prior id|next id|prior n|next n|
|---------------|--------------------|------------------|---------|---------|---------|-------|--------|-------|------|

Type: 0 - internal node; 1 - leaf node; -1 empty block

 */

abstract public class BTreeNode {
    int M;
    ArrayList<byte[]> keys;
    byte[] index_data;
    //corresponding to the header
    int key_number;
    int node_id;
    int parent_id;
    int left_bro_id;
    int right_bro_id;
    int next_id;            //next id == 0 means all node data are stored in this block
    int prior_id;
    int prior_key_number;
    int next_key_number;
    int end_of_free_space;

    Boolean is_changed; //whether data in memory is different from data on disk
    IndexBuffer buffer;
    TableAttribute[] index_attrs;

    int free_space;
    int key_length;


    //create an empty node
    public BTreeNode(int m,int id,int p_id,int l_id,int r_id,int the_prior_id,int the_next_id,
                     int p_number,int n_number,IndexBuffer bf,TableAttribute[] attrs){
        //set
        M = m;
        node_id = id;
        parent_id = p_id;
        left_bro_id = l_id;
        right_bro_id = r_id;
        index_data = new byte[Util.DiskBlockSize];
        buffer = bf;
        index_attrs = attrs;
        next_id = the_next_id;
        prior_id = the_prior_id;
        prior_key_number = p_number;
        next_key_number = n_number;
        key_number = 0;
        end_of_free_space = Util.DiskBlockSize - 1;

        //calculate
        free_space = Util.DiskBlockSize - 19;
        getKeyLength();

        //header initialization
        System.arraycopy(Util.short2byte((short)0),0,index_data,1,2);
        System.arraycopy(Util.short2byte((short)end_of_free_space),0,index_data,3,2);
        System.arraycopy(Util.short2byte((short)p_id),0,index_data,5,2);
        System.arraycopy(Util.short2byte((short)l_id),0,index_data,7,2);
        System.arraycopy(Util.short2byte((short)r_id),0,index_data,9,2);
        System.arraycopy(Util.short2byte((short)prior_id),0,index_data,11,2);
        System.arraycopy(Util.short2byte((short)next_id),0,index_data,13,2);
        System.arraycopy(Util.short2byte((short)prior_key_number),0,index_data,15,2);
        System.arraycopy(Util.short2byte((short)next_key_number),0,index_data,17,2);

        is_changed = false;
    }

    //load node from file




    public byte[] getIndexData(){
        return index_data;
    }

    public void getKeyLength(){
        key_length = 0;
        for (TableAttribute cur:index_attrs){
            key_length += cur.getLengthLimit();
        }
    }

    public int compare2key(byte[] left,byte[] right){
        int type,len = 0,string_len = 0;
        byte[] bits8 = new byte[8];
        for (TableAttribute cur:index_attrs){
            type = cur.getType();

            if(type == Util.INT){
                System.arraycopy(left,len,bits8,0,4);
                int l = Util.byte2int(bits8);
                System.arraycopy(right,len,bits8,0,4);
                int r = Util.byte2int(bits8);
                len += 4;
                if(l < r)
                    return Util.L;
                else if (l > r)
                    return Util.G;
            }else if (type == Util.DOUBLE){
                System.arraycopy(left,len,bits8,0,8);
                double l = Util.byte2Double(bits8);
                System.arraycopy(right,len,bits8,0,8);
                double r = Util.byte2Double(bits8);
                len += 8;
                if(l < r)
                    return Util.L;
                else if (l > r)
                    return Util.G;
            }else if (type == Util.FLOAT){
                System.arraycopy(left,len,bits8,0,4);
                double l = Util.byte2float(bits8);
                System.arraycopy(right,len,bits8,0,4);
                double r = Util.byte2float(bits8);
                len += 4;
                if(l < r)
                    return Util.L;
                else if (l > r)
                    return Util.G;

            }else if (type == Util.LONG){
                System.arraycopy(left,len,bits8,0,8);
                double l = Util.byte2Long(bits8);
                System.arraycopy(right,len,bits8,0,8);
                double r = Util.byte2Long(bits8);
                len += 8;
                if(l < r)
                    return Util.L;
                else if (l > r)
                    return Util.G;
            }else if (type == Util.CHAR){
                System.arraycopy(left,len,bits8,0,1);
                char l = (char)bits8[0];
                System.arraycopy(right,len,bits8,0,1);
                char r = (char)bits8[0];
                len += 1;
                if(l < r)
                    return Util.L;
                else if (l > r)
                    return Util.G;
            }else {
                string_len = cur.getLengthLimit();
                String l = new String(left,len,string_len), r = new String(right,len,string_len);
                if (l.compareTo(r) < 0)
                    return Util.L;
                else if (l.compareTo(r) > 0)
                    return Util.G;
            }
        }
        return Util.E;
    }

    public int BinarySearch(byte[] search_key,int left, int right){
        if (left > right) return (int) Math.ceil((right + left)/2 );
        int mid =  (int) Math.floor((left + right) / 2);
        int result = compare2key(keys.get(mid),search_key);
        if (result == Util.E){
            return mid;
        }else if (result == Util.L){
            return BinarySearch(search_key,mid + 1,right);
        }else{
            return BinarySearch(search_key,left,mid - 1);
        }
    }


    public void updateKeyNumber(int new_key_number){
        int delta = new_key_number - key_number;
        if (delta != 0){
            key_number = new_key_number;
            is_changed = true;
            System.arraycopy(Util.short2byte((short)key_number),0,index_data,1,2);

            BTreeNode temp;
            if(prior_id != 0){
                temp = this;
                while(temp.prior_id != 0){
                    temp = buffer.getNode(temp.prior_id);
                    temp.next_key_number += delta;
                    System.arraycopy(Util.short2byte((short)temp.next_key_number),0,index_data,17,2);
                    temp.is_changed = true;
                }
            }

            if(next_id != 0){
                temp = this;
                while(temp.next_id != 0){
                    temp = buffer.getNode(temp.next_id);
                    temp.prior_key_number += delta;
                    System.arraycopy(Util.short2byte((short)temp.prior_key_number),0,index_data,15,2);
                    temp.is_changed = true;
                }
            }
        }
    }

    public void updateNextKeyNumber(int next){
        if (next != next_key_number){
            next_key_number = next;
            System.arraycopy(Util.short2byte((short)next),0,index_data,17,2);
            is_changed = true;
        }
    }

    public void updatePriorKeyNumber(int prior){
        if( prior != prior_key_number){
            prior_key_number = prior;
            System.arraycopy(Util.short2byte((short)prior),0,index_data,15,2);
            is_changed = true;
        }
    }

    public void updateNumberToRight(){
        BTreeNode p, q;
        if (next_id != 0){
            p = buffer.getNode(next_id);
            updateNextKeyNumber(p.key_number + p.next_key_number);
            q = this;
            do{
                p = buffer.getNode(q.next_id);
                p.updatePriorKeyNumber(q.prior_key_number + q.key_number);
                q = p;
            }while(q.next_id != 0);
        }
    }

    public void updateNumberToLeft(){
        BTreeNode p, q;
        if (prior_id != 0){
            p = buffer.getNode(prior_id);
            updatePriorKeyNumber(p.key_number + p.prior_key_number);
            q = this;
            do{
                p = buffer.getNode(q.prior_id);
                p.updateNextKeyNumber(q.next_key_number + q.key_number);
                q = p;
            }while(q.prior_id != 0);
        }
    }

    public void updateLeftBro(int new_left){
        BTreeNode temp;
        if (new_left != left_bro_id){
            left_bro_id = new_left;
            System.arraycopy(Util.short2byte((short)new_left),0,index_data,7,2);
            is_changed = true;

            if(next_id != 0){
                temp = this;
                while(temp.next_id != 0){
                    temp = buffer.getNode(temp.next_id);
                    temp.left_bro_id = new_left;
                    System.arraycopy(Util.short2byte((short)new_left),0,temp.index_data,7,2);
                    temp.is_changed = true;
                }
            }

            if(prior_id != 0){
                temp = this;
                while(temp.prior_id != 0){
                    temp = buffer.getNode(temp.prior_id);
                    temp.left_bro_id = new_left;
                    System.arraycopy(Util.short2byte((short)new_left),0,temp.index_data,7,2);
                    temp.is_changed = true;
                }
            }
        }
    }

    public void updateRightBro(int new_right){
        BTreeNode temp;
        if (new_right != right_bro_id){
            right_bro_id = new_right;
            System.arraycopy(Util.short2byte((short)new_right),0,index_data,7,2);
            is_changed = true;

            if(next_id != 0){
                temp = this;
                while(temp.next_id != 0){
                    temp = buffer.getNode(temp.next_id);
                    temp.right_bro_id = new_right;
                    System.arraycopy(Util.short2byte((short)new_right),0,temp.index_data,7,2);
                    temp.is_changed = true;
                }
            }

            if(prior_id != 0){
                temp = this;
                while(temp.prior_id != 0){
                    temp = buffer.getNode(temp.prior_id);
                    temp.right_bro_id = new_right;
                    System.arraycopy(Util.short2byte((short)new_right),0,temp.index_data,7,2);
                    temp.is_changed = true;
                }
            }
        }
    }

    public void updateParent(int new_parent){
        BTreeNode temp;
        if (new_parent != parent_id){
            parent_id = new_parent;
            System.arraycopy(Util.short2byte((short)new_parent),0,index_data,5,2);
            is_changed = true;

            if(next_id != 0){
                temp = this;
                while(temp.next_id != 0){
                    temp = buffer.getNode(temp.next_id);
                    temp.parent_id = new_parent;
                    System.arraycopy(Util.short2byte((short)new_parent),0,temp.index_data,5,2);
                    temp.is_changed = true;
                }
            }

            if(prior_id != 0){
                temp = this;
                while(temp.prior_id != 0){
                    temp = buffer.getNode(temp.prior_id);
                    temp.parent_id = new_parent;
                    System.arraycopy(Util.short2byte((short)new_parent),0,temp.index_data,5,2);
                    temp.is_changed = true;
                }
            }
        }
    }


    public BTreeNode getHeadNode(){
        BTreeNode temp = this;
        if (prior_id != 0){
            temp = buffer.getNode(prior_id);
        }
        return temp;
    }

    public BTreeNode getTailNode(){
        BTreeNode temp = this;
        if (next_id != 0){
            temp = buffer.getNode(next_id);
        }
        return temp;
    }

    public byte[] getBiggestKey(){
        BTreeNode p = this;
        if(next_id != 0){
            while(p.next_id != 0){
                p = buffer.getNode(p.next_id);
            }
        }
        return p.keys.get(p.key_number - 1);
    }

    public void HeaderFromByte(){
        byte[] bits4 = new byte[4];
        //key number
        System.arraycopy(index_data,1,bits4,0,2);
        key_number = (int) Util.byte2short(bits4);
        //free space
        System.arraycopy(index_data,3,bits4,0,2);
        end_of_free_space = (int) Util.byte2short(bits4);
        //family id
        System.arraycopy(index_data,5,bits4,0,2);
        parent_id = (int) Util.byte2short(bits4);
        System.arraycopy(index_data,7,bits4,0,2);
        left_bro_id = (int) Util.byte2short(bits4);
        System.arraycopy(index_data,9,bits4,0,2);
        right_bro_id = (int) Util.byte2short(bits4);
        //prior id, next id
        System.arraycopy(index_data,11,bits4,0,2);
        prior_id = (int) Util.byte2short(bits4);
        System.arraycopy(index_data,13,bits4,0,2);
        next_id = (int) Util.byte2short(bits4);
        //prior key number; next key number
        System.arraycopy(index_data,15,bits4,0,2);
        prior_key_number = (int) Util.byte2short(bits4);
        System.arraycopy(index_data,17,bits4,0,2);
        next_key_number = (int) Util.byte2short(bits4);
    }

    abstract public void IndexDataFromByte();


    public void deleteNode(){
        if (prior_id != 0 && next_id != 0){
            BTreeNode prior_node = buffer.getNode(prior_id),next_node = buffer.getNode(next_id);
            prior_node.next_id = next_node.node_id;
            next_node.prior_id = prior_node.node_id;
            prior_node.updateNextKeyNumber(next_key_number);
            prior_node.updateNumberToLeft();
            next_node.updatePriorKeyNumber(prior_key_number);
            next_node.updateNumberToRight();
        }else if (prior_id != 0 && next_id == 0){
            BTreeNode prior_node = buffer.getNode(prior_id);
            prior_node.next_id = 0;
            prior_node.updateNextKeyNumber(0);
            prior_node.updateNumberToLeft();
        }else if(prior_id == 0 && next_id !=0){
            BTreeNode next_node = buffer.getNode(next_id);
            next_node.prior_id = 0;
            next_node.updatePriorKeyNumber(0);
            next_node.updateNumberToRight();
        }
        buffer.deleteNode(this);
    }

    public void deleteNodeList(){
        if (left_bro_id != 0 && right_bro_id != 0){
            BTreeNode left_node = buffer.getNode(left_bro_id),right_node = buffer.getNode(right_bro_id);
            left_node.updateRightBro(right_node.node_id);
            right_node.updateLeftBro(left_node.node_id);
        }else if(left_bro_id != 0 && right_bro_id == 0){
            BTreeNode left_node = buffer.getNode(left_bro_id);
            left_node.updateRightBro(0);
        }else if (left_bro_id == 0 && right_bro_id != 0){
            BTreeNode right_node = buffer.getNode(right_bro_id);
            right_node.updateLeftBro(0);
        }
        BTreeNode temp = this;
        while(temp.next_id != 0){
            temp = buffer.getNode(temp.next_id);
            buffer.deleteNode(temp);
        }
        temp = this;
        while(temp.prior_id != 0){
            temp = buffer.getNode(temp.prior_id);
            buffer.deleteNode(temp);
        }
        buffer.deleteNode(this);
    }


    public static void main(String[] args) {
        System.out.println("-- Node --");
        //BTreeInternalNode n = new BTreeInternalNode();
        //n.getIndexData();
    }


}
