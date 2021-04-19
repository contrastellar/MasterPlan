package components.observable;

import java.util.HashSet;

public class Observable<T> implements IObservable<T>, IReadOnlyObservable<T>, IWriteOnlyObservable<T>
{
    private final HashSet<IListener<T>> listeners = new HashSet<>();
    private final HashSet<IValueChangePairListener<T>> listeners2 = new HashSet<>();
    private T value = null;

    public Observable() {  }
    public Observable(T value) { this.value = value; }

    @Override
    public void startListen(IListener<T> listener) {
        listeners.add(listener);
        listener.onChange(value);
    }

    public void startListen(IValueChangePairListener<T> listener) {
        listeners2.add(listener);
        listener.onChange(value, value);
    }

    @Override
    public void stopListen(IListener<T> listener) {
        listeners.remove(listener);
    }

    public void stopListen(IValueChangePairListener<T> listener) {

    }

    public T getValue() { return value; }

    public void setValue(T value) {
        this.value = value;
        for(IListener<T> listener : listeners)
            listener.onChange(value);
    }


}
