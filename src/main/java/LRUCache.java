import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache <K,V> extends LinkedHashMap<K, V>{
    private int max_number;

    LRUCache(int capacity,float loadFactor,boolean accessOrder,int max){
        super(capacity,loadFactor,accessOrder);
        max_number = max;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        if (size() > max_number) {
            return true;
        }
        return false;
    }

    public boolean removeOldest(){
        if (size() >= max_number) {
            return true;
        }
        return false;
    }

    public V getOldest(){
        Iterator<Map.Entry<K,V>> iterator= entrySet().iterator();
        if(iterator.hasNext()){
            return iterator.next().getValue();
        }
        return null;

    }
}
