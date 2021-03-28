package components.observable;

public interface IObservable {

    void addListener(IListener listener);

    void removeListener(IListener listener);

}
