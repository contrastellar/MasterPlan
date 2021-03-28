package components.observable;

public interface IReadOnlyObservable<T> extends IObservable {
    public T getValue();
}
