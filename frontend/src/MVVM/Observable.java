package MVVM;

import java.util.HashSet;

public class Observable<T> implements IObservable, IReadOnly<T>, IWriteOnly<T>
{
    private final HashSet<IObserver> observers = new HashSet<>();
    private T value = null;

    public Observable() {  }
    public Observable(T value) { this.value = value; }

    public T getValue() { return value; }
    public void setValue(T value)
    {
        this.value = value;
        for(IObserver observer : observers)
            observer.onChange();
    }

    @Override
    public void startObserve(IObserver observer)
    {
        observers.add(observer);
        observer.onChange();
    }

    @Override
    public void stopObserve(IObserver observer)
    {
        observers.remove(observer);
    }
}
