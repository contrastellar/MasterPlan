package MVVM;

public interface IWriteOnlyObservable<T> extends IObservable {

    public void setValue(T newValue);

}
