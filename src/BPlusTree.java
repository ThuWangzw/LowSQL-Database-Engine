import java.util.ArrayList;
import java.util.List;

public class BPlusTree<K extends Comparable<? super K>, V> {
    public static void main(String[] args){
//        test
        System.out.println("test");
        BPlusTree<Integer, Integer> tree = new BPlusTree<>();
        for(int i=20; i>=1; i--){
            tree.insert(i, i);
        }

        for(int i=1; i<=20; i++){
            tree.delete(i, i);
        }
//        tree.delete(12, 12);
        System.out.println("test done");
    }

    private Node root;
    private int M = 3;
    ArrayList<Integer> findstack = new ArrayList<>();

    public void insert(K key, V value){
//        find the node and insert
        if(root == null){
            root = new LeafNode();
        }
        findstack.clear();
        LeafNode leaf = root.find_insert(key, value);
        leaf.split();
    }

    public void delete(K key, V value){
        findstack.clear();
        LeafNode node = root.find_delete(key, value);
        if(node == null){
            System.out.println("ERROR: can\'t find key.");
        }else {
            node.merge();
        }
    }

    private abstract class Node {
        ArrayList<K> keys = new ArrayList<>();
        Node parent = null;
        Node left_bro = null;
        Node right_bro = null;

        abstract public LeafNode find_insert(K key, V val);

        abstract public LeafNode find_delete(K key, V val);

        abstract public void split();

        abstract public void merge();
    }

    private class InternalNode extends Node{
        ArrayList<Node> vals = new ArrayList<>();

        int findkey(K key){
            if(keys.size() == 0){
                System.out.println("ERROR: keys cant be empty in Node.findkey()");
            }
            for(int i=0; i<keys.size(); i++){
                if(key.compareTo(keys.get(i)) < 0){
                    return i;
                }
            }
            return keys.size();
        }

        @Override
        public LeafNode find_insert(K key, V val){
            int idx = findkey(key);
            Node node = vals.get(idx);
            findstack.add(idx);
            return node.find_insert(key, val);
        }

        @Override
        public LeafNode find_delete(K key, V val){
            int idx = findkey(key);
            Node node = vals.get(idx);
            findstack.add(idx);
            return node.find_delete(key, val);
        }

        @Override
        public void split() {
            if(keys.size() < M) return;
            int right_begin = M/2+1;
            int right_end = M+1;
            InternalNode left = new InternalNode();
            left.keys = new ArrayList<K>(keys.subList(0, right_begin-1));
            left.vals = new ArrayList<Node>(vals.subList(0, right_begin));

            InternalNode right = new InternalNode();
            right.keys = new ArrayList<K>(keys.subList(right_begin, right_end-1));
            right.vals = new ArrayList<Node>(vals.subList(right_begin, right_end));

            left.left_bro = this.left_bro;
            left.right_bro = right;
            if(left.left_bro != null){
                left.left_bro.right_bro = left;
            }

            right.left_bro = left;
            right.right_bro = this.right_bro;
            if(right.right_bro != null){
                right.right_bro.left_bro = right;
            }

            if(parent == null){
                InternalNode newparent = new InternalNode();
                newparent.keys.add(keys.get(right_begin-1));
                newparent.vals.add(left);
                newparent.vals.add(right);
                root = newparent;
                for(Node child : left.vals){
                    child.parent = left;
                }
                for(Node child : right.vals){
                    child.parent = right;
                }
                left.parent = right.parent = newparent;
            }else {
                InternalNode tparent = (InternalNode)parent;
                int idx = findstack.remove(findstack.size()-1);
                tparent.keys.add(idx, keys.get(right_begin-1));
                tparent.vals.set(idx, left);
                tparent.vals.add(idx+1, right);
                left.parent = right.parent = tparent;
                for(Node child : left.vals){
                    child.parent = left;
                }
                for(Node child : right.vals){
                    child.parent = right;
                }
                parent.split();
            }
        }

        @Override
        public void merge() {
            if((keys.size() >= Math.ceil((double) M/2)-1)) return;
            if((this == root)&&(root.keys.size() == 0)){
                root = ((InternalNode) root).vals.get(0);
                return;
            }
            if((left_bro != null) && (left_bro.parent == parent) && (left_bro.keys.size()>Math.ceil((double) M/2)-1)){
                int left_len = left_bro.keys.size();
                K borrow_key = left_bro.keys.remove(left_len-1);
                Node borrow_node = ((InternalNode) left_bro).vals.remove(left_len);

                int idx = findstack.remove(findstack.size()-1);
                K par_key = parent.keys.set(idx-1, borrow_key);

                this.keys.add(0, par_key);
                this.vals.add(0, borrow_node);
                borrow_node.parent = this;
            }
            else if((right_bro != null) && (right_bro.parent == parent) && (right_bro.keys.size()>Math.ceil((double) M/2)-1)){
                K borrow_key = right_bro.keys.remove(0);
                Node borrow_node = ((InternalNode) right_bro).vals.remove(0);

                int idx = findstack.remove(findstack.size()-1);
                K par_key = parent.keys.set(idx, borrow_key);

                this.keys.add(par_key);
                this.vals.add(borrow_node);
                borrow_node.parent = this;
            }else {
                if((left_bro != null) && (left_bro.parent == parent)){
                    int idx = findstack.remove(findstack.size()-1);

                    for(Node node : vals){
                        node.parent = left_bro;
                    }
                    K par_key = parent.keys.remove(idx-1);
                    ((InternalNode) parent).vals.remove(idx);

                    left_bro.keys.add(par_key);
                    left_bro.keys.addAll(keys);

                    ((InternalNode) left_bro).vals.addAll(vals);

                    left_bro.right_bro = right_bro;
                    if(left_bro.right_bro != null){
                        left_bro.right_bro.left_bro = left_bro;
                    }
                    parent.merge();
                }
                else if ((right_bro != null) && (right_bro.parent == parent)){
                    int idx = findstack.remove(findstack.size()-1);

                    for(Node node : ((InternalNode) right_bro).vals){
                        node.parent = this;
                    }
                    K par_key = parent.keys.remove(idx);
                    ((InternalNode) parent).vals.remove(idx+1);

                    keys.add(par_key);
                    keys.addAll(right_bro.keys);
                    vals.addAll(((InternalNode) right_bro).vals);


                    right_bro = right_bro.right_bro;
                    if(right_bro != null){
                        right_bro.left_bro = this;
                    }
                    parent.merge();
                }
                else {
                    System.out.println("ERROR: internal node bug!!");
                }
            }
        }
    }

    private class LeafNode extends Node{
        ArrayList<V> vals = new ArrayList<>();

        int findkey(K key, Boolean is_insert){
            if (is_insert){
                if(keys.size() == 0){
                    keys.add(key);
                    return 0;
                }
                for(int i=0; i<keys.size(); i++){
                    if(key.compareTo(keys.get(i)) < 0){
                        keys.add(i, key);
                        return i;
                    }
                }
                keys.add(key);
                return keys.size()-1;
            }
            else {
                for(int i=0; i<keys.size(); i++){
                    if(key.compareTo(keys.get(i)) == 0){
                        return i;
                    }
                }
                return -1;
            }
        }

        @Override
        public LeafNode find_insert(K key, V val){
            int num = findkey(key, true);
            vals.add(num, val);
            return this;
        }

        @Override
        public void split() {
            if(keys.size() <= M) return;
            int right_begin = (M+1)/2;
            int right_end = M+1;
            LeafNode left = new LeafNode();
            left.keys = new ArrayList<K>(keys.subList(0, right_begin));
            left.vals = new ArrayList<V>(vals.subList(0, right_begin));

            LeafNode right = new LeafNode();
            right.keys = new ArrayList<K>(keys.subList(right_begin, right_end));
            right.vals = new ArrayList<V>(vals.subList(right_begin, right_end));

            left.left_bro = this.left_bro;
            left.right_bro = right;
            if(left.left_bro != null){
                left.left_bro.right_bro = left;
            }

            right.left_bro = left;
            right.right_bro = this.right_bro;
            if(right.right_bro != null){
                right.right_bro.left_bro = right;
            }

            if(parent == null){
                InternalNode newparent = new InternalNode();
                newparent.keys.add(right.keys.get(0));
                newparent.vals.add(left);
                newparent.vals.add(right);
                root = newparent;
                left.parent = right.parent = newparent;
            }else {
                InternalNode tparent = (InternalNode)parent;
                int idx = findstack.remove(findstack.size()-1);
                tparent.keys.add(idx, right.keys.get(0));
                tparent.vals.set(idx, left);
                tparent.vals.add(idx+1, right);
                left.parent = right.parent = tparent;
                parent.split();
            }
        }

        @Override
        public LeafNode find_delete(K key, V val){
            int idx = findkey(key, false);
            if(idx==-1) return null;
            else{
                keys.remove(idx);
                vals.remove(idx);
                return this;
            }
        }

        @Override
        public void merge() {
            if((keys.size() >= Math.ceil((double) M/2)-1) || (this == root)){
                return;
            }
            if((left_bro != null) && (left_bro.parent == parent) && (left_bro.keys.size()>Math.ceil((double) M/2)-1)){
                int left_len = left_bro.keys.size();
                K borrow_key = left_bro.keys.remove(left_len-1);
                V borrow_v = ((LeafNode) left_bro).vals.remove(left_len-1);

                keys.add(0, borrow_key);
                vals.add(0, borrow_v);

                int idx = findstack.remove(findstack.size()-1);
                parent.keys.set(idx-1, borrow_key);
            }
            else if((right_bro != null) && (right_bro.parent == parent) && (right_bro.keys.size()>Math.ceil((double) M/2)-1)){
                K borrow_key = right_bro.keys.remove(0);
                V borrow_v = ((LeafNode) right_bro).vals.remove(0);

                keys.add(borrow_key);
                vals.add(borrow_v);

                int idx = findstack.remove(findstack.size()-1);
                parent.keys.set(idx, right_bro.keys.get(0));
            }else {
                if((left_bro != null) && (left_bro.parent == parent)){
                    int idx = findstack.remove(findstack.size()-1);
                    parent.keys.remove(idx-1);
                    ((InternalNode) parent).vals.remove(idx);
                    left_bro.keys.addAll(this.keys);
                    ((LeafNode) left_bro).vals.addAll(this.vals);
                    left_bro.right_bro = this.right_bro;
                    if(this.right_bro != null){
                        left_bro.right_bro.left_bro = left_bro;
                    }
                    parent.merge();
                }else if((right_bro != null) && (right_bro.parent == parent)){
                    int idx = findstack.remove(findstack.size()-1);
                    parent.keys.remove(idx);
                    ((InternalNode) parent).vals.remove(idx+1);
                    keys.addAll(right_bro.keys);
                    vals.addAll(((LeafNode) right_bro).vals);
                    right_bro = right_bro.right_bro;
                    if(right_bro != null){
                        right_bro.left_bro = this;
                    }
                    parent.merge();
                }else {
                    System.out.println("ERROR: leafnode bug!!");
                }
            }
        }
    }

}

class Savedata{
    public int page_id;
    public int record_id;

    public Savedata(int page_id, int record_id){
        this.page_id = page_id;
        this.record_id = record_id;
    }

    public int getPage_id(){
        return page_id;
    }

    public int getRecord_id(){
        return record_id;
    }
}

/* meta data of B tree which may be stored in the first block

|---------4-------------|--------k1---------|---------4----------|--------k2---------|
|Length of Database name|  Database Name    |Length of Table name|     Table Name    |
|-----------------------|-------------------|--------------------|-------------------|

|------------4--------------|----------1----------|-- ... --|
|Number of Index Attributes |Index Attribute Type |   ...   |
|---------------------------|---------------------|-- ... --|

 */



//当索引需要更新时，我会给你的对应函数传递k个 DataUpdateInfo 类的实例，该类实例你直接接收并使用即可
//每一行数据的索引是两个整数int，即 该行数据所在页page_id 和 该行数据在所在页的所在行record_id
//每个索引树的 搜索键 (search-key) 可能不同，你需要在索引树确立search_key, 一行数据有k个属性，使用整数i表示使用第i+1个属性作为search key
//DataUpdateInfo 中的 search_key 是对应行数据的实际search key
//DataUpdateInfo 中的 type 有三个取值，分别对应 Insert,Delete,Update
//对于Insert, DataUpdateInfo 中的new_page_id,new_record_id是新插入的数据的指针，search key是新插入数据对应的search key
//对于Delete, DataUpdateInfo 中的old_page_id,old_record_id是删除数据的指针，search key是删除数据对应的search key
//对于Update, DataUpdateInfo 中的old_page_id,old_record_id是要更改数据的指针，new_page_id,new_record_id是数据更新的指针，search key是更新数据对应的search key
//对于某些属性的索引，数据的search key可能会重复
//索引树也需要持久化，需要完成数据的读写接口