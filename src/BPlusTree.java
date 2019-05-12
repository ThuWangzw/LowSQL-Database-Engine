import java.util.ArrayList;
import java.util.List;

public class BPlusTree<K extends Comparable<? super K>, V> {
    public static void main(String[] args){
//        test
        System.out.println("test");
        BPlusTree<Integer, Integer> tree = new BPlusTree<>();
        for(int i=1; i<=20; i++){
            tree.insert(i, i);
        }
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



    private abstract class Node {
        ArrayList<K> keys = new ArrayList<>();
        Node parent = null;

        abstract public LeafNode find_insert(K key, V val);

        abstract public void split();
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