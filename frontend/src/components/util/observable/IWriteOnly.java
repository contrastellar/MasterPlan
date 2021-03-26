package components.util.observable;

public interface IWriteOnly<T> extends IObservable
{
    public void setValue(T newValue);
}
