package components.util.observable;

public interface IReadOnly<T> extends IObservable {
    public T getValue();
}
