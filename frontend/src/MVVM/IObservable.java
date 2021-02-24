package MVVM;

public interface IObservable
{
    void startListen(IListener listener);
    void stopListen(IListener listener);
}