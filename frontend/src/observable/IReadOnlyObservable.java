package observable;

public interface IReadOnlyObservable<T> extends IObservable<T> {
    public T getValue();
}
