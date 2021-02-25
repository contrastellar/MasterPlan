package MVVM;

public interface IReadOnly<T> extends IObservable {
    public T getValue();
}
