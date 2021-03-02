package MVVM;

import java.util.HashSet;

public class Observable<T> implements IObservable, IReadOnly<T>, IWriteOnly<T>
{
    private final HashSet<IListener> listeners = new HashSet<>();
    private T value = null;

    public Observable() {  }
    public Observable(T value) { this.value = value; }

    public T getValue() { return value; }
    public void setValue(T value)
    {
        this.value = value;
        for(IListener listener : listeners)
            listener.onChange();
    }

    @Override
    public void addListener(IListener listener)
    {
        listeners.add(listener);
        listener.onChange();
    }

    @Override
    public void removeListener(IListener listener)
    {
        listeners.remove(listener);
    }
}
