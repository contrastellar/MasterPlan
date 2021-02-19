package UI.MVVM;

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
            observer.OnChange();
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
}
