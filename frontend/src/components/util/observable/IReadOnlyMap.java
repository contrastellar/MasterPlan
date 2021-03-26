package components.util.observable;


import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface IReadOnlyMap<K, V> extends IObservable {

    public int size();

    public boolean isEmpty();

    public boolean containsKey(Object key);

    public boolean containsValue(Object value);

    public V get(Object key);

    public Set<K> keySet();

    public Collection<V> values();

    public Set<Map.Entry<K, V>> entrySet();

}
