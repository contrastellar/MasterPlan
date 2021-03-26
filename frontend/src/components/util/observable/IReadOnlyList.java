package components.util.observable;

import java.util.*;

public interface IReadOnlyList<T> extends IObservable {

    public int size();

    public boolean isEmpty();

    public boolean contains(Object o);

    public Iterator<T> iterator();

    public Object[] toArray();

    public <T1> T1[] toArray(T1[] a);

    public boolean containsAll(Collection<?> c);

    public T get(int index);

    public int indexOf(Object o);

    public int lastIndexOf(Object o);

    public ListIterator<T> listIterator();

    public ListIterator<T> listIterator(int index);

    public List<T> subList(int fromIndex, int toIndex);

}
