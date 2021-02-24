package MVVM;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class ObservableCollection<T> implements IObservable, Collection<T> {

    private final Collection<T> collection;
    private final HashSet<IListener> listeners = new HashSet<>();

    public ObservableCollection(Collection<T> collection) {
        this.collection = collection;
    }

    public void startListen(IListener listener) {
        listeners.add(listener);
        listener.onChange();
    }

    public void stopListen(IListener listener) {
        listeners.remove(listener);
    }

    private void updateListeners() {
        for(IListener listener : listeners)
            listener.onChange();
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
        return collection.<T1>toArray(t1s);
    }

    @Override
    public boolean add(T t) {
        boolean changed = collection.add(t);
        if(changed) updateListeners();
        return changed;
    }

    @Override
    public boolean remove(Object o) {
        boolean changed = collection.remove(o);
        if(changed) updateListeners();
        return changed;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return this.collection.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean changed = this.collection.addAll(collection);
        if(changed) updateListeners();
        return changed;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean changed = this.collection.removeAll(collection);
        if(changed) updateListeners();
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean changed = this.collection.retainAll(collection);
        if(changed) updateListeners();
        return changed;
    }

    @Override
    public void clear() {
        this.collection.clear();
        updateListeners();
    }
}