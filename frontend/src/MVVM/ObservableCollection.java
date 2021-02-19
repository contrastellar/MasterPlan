package MVVM;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class ObservableCollection<T> implements IObservable, Collection<T>
{

    Collection<T> collection;
    HashSet<IObserver> observers = new HashSet<>();

    public ObservableCollection(Collection<T> collection)
    {
        this.collection = collection;
    }

    @Override
    public void StartObserve(IObserver observer)
    {
        observers.add(observer);
        observer.OnChange();
    }

    @Override
    public void StopObserve(IObserver observer)
    {
        observers.remove(observer);
    }

    private void updateObservers()
    {
        for(IObserver observer : observers)
            observer.OnChange();
    }

    @Override
    public int size() {
        return collection.size();
    }

    @Override
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return collection.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return collection.iterator();
    }

    @Override
    public Object[] toArray() {
        return collection.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        return collection.toArray(t1s);
    }

    @Override
    public boolean add(T t) {
        boolean b = collection.add(t);
        updateObservers();
        return b;
    }

    @Override
    public boolean remove(Object o) {
        boolean b = collection.remove(o);
        updateObservers();
        return b;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return this.collection.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {

        boolean b = this.collection.addAll(collection);
        updateObservers();
        return b;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean b = this.collection.removeAll(collection);
        updateObservers();
        return b;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean b = this.collection.retainAll(collection);
        updateObservers();
        return b;
    }

    @Override
    public void clear() {
        this.collection.clear();
        updateObservers();
    }
}
