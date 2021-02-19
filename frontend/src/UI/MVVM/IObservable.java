package UI.MVVM;

public interface IObservable
{
    void StartObserve(IObserver observer);
    void StopObserve(IObserver observer);
}