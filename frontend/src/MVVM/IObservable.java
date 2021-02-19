package MVVM;

public interface IObservable
{
    void startObserve(IObserver observer);
    void stopObserve(IObserver observer);
}