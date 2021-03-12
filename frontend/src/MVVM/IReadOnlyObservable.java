package MVVM;

public interface IReadOnlyObservable<T> extends IObservable {
    public T getValue();
}
