import java.util.ArrayList;
import java.util.List;
public class BPlusTree<K extends Comparable<? super K>> {
    public static void main(String[] args){
//        test
        System.out.println("test");
    }


    private abstract class Node {
        List<K> keys;
    }

    private class InternalNode extends Node{

    }

    private class LeafNode extends Node{

    }
}