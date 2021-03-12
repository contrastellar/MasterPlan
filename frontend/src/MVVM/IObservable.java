package MVVM;

public interface IObservable {

    void addListener(IListener listener);

    void removeListener(IListener listener);

}
