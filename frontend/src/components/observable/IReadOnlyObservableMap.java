package components.observable;


import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public interface IReadOnlyObservableMap<K, V> extends IObservable {

    public int size();

    public boolean isEmpty();

    public boolean containsKey(Object key);

    public boolean containsValue(Object value);

    public V get(Object key);

    public Set<K> keySet();

    public Collection<V> values();

    public Set<Map.Entry<K, V>> entrySet();

}
