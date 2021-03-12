package MVVM;

import java.util.Collection;
import java.util.Iterator;

public interface IReadOnlyObservableSet<T> extends IObservable{

    public int size();

    public boolean isEmpty();

    public boolean contains(Object o);

    public Iterator<T> iterator();

    public Object[] toArray();

    public <T1> T1[] toArray(T1[] a);

    public boolean containsAll(Collection<?> c);

}
