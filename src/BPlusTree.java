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

/* meta data of B tree which may be stored in the first block

|---------4-------------|--------k1---------|---------4----------|--------k2---------|
|Length of Database name|  Database Name    |Length of Table name|     Table Name    |
|-----------------------|-------------------|--------------------|-------------------|

|------------4--------------|----------1----------|-- ... --|
|Number of Index Attributes |Index Attribute Type |   ...   |
|---------------------------|---------------------|-- ... --|

 */


/* B Tree block header
Every block shall have the header

|-------1-------|-------2------------|-------2-------|--------1---------------|--------2--------|
|     Type      |Number of Search key|Free Space Left|Point type to Next Block|   Next page id  |
|---------------|--------------------|---------------|------------------------|-----------------|

Type: 0 - internal node; 1 - leaf node
Free Space Left: bytes
Point type to Next block:
    0 - for leaf and internal nodes, all search keys and pointers are stored in the block
    1 - for leaf and internal nodes, all search keys and pointers can not be stored in a single block, therefore they are
        stored in multiple blocks, the next page id is the next block id that saves the unstored parts of the node
    2 = all leaf nodes shall link as a list,the next page id is the block id of another leaf node

 */

/* Internal Node Body Structure
|--------4---------|--------2-------------|-------k--------|  ...
| Pointer of Nodes | length of search key |   search key   |
|------------------|----------------------|----------------|

If the index is built on Attribute (A1,A2,...,An), Then the search key is value of (a1,a2,..,an),
since the attributes can only be String,Number which are comparable, the search key is comparable

if the node has to be store in two blocks.
the end of first block should end with: search key ;while the start of the second block should start with pointer

 */

/* Leaf Node Body Structure
|-------2------------|----------k---------|---------2---------|----------8----------|---...----|
|length of search key|      search key    | Number of records | Record File Pointer |   ...    |
|--------------------|--------------------|-------------------|---------------------|---...----|

if the node has to be stored in two blocks.
the second block should start with number of records and the following record File Pointer

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