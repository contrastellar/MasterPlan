package MVVM;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ObservableManager
{
    private final HashMap<IObservable, HashSet<IObserver>> ObservableToObserver = new HashMap<>();
    private boolean observing = false;

    public ObservableManager() {  }

    public void addObserver(IObservable observable, IObserver observer)
    {
        HashSet<IObserver> observers = ObservableToObserver.computeIfAbsent(observable, k -> new HashSet<>());
        observers.add(observer);

        if(observing)
            observable.startObserve(observer);
    }

    public void removeObserver(IObservable observable, IObserver observer)
    {
        HashSet<IObserver> observers = ObservableToObserver.get(observable);
        if(observers != null)
        {
            if(observing)
                observable.stopObserve(observer);

            observers.remove(observer);
        }
    }

    public void removeAllObservers(IObservable observable)
    {
        if(observing)
        {
            HashSet<IObserver> observers = ObservableToObserver.get(observable);
            if(observers != null)
            {
                for(IObserver observer : observers)
                    observable.stopObserve(observer);
            }
        }
        ObservableToObserver.remove(observable);
    }

    public void startObserve()
    {
    	observing = true;
        for(Map.Entry<IObservable, HashSet<IObserver>> entry : ObservableToObserver.entrySet())
        {
            for(IObserver observer : entry.getValue())
                entry.getKey().startObserve(observer);
        }
    }

    public void stopObserve()
    {
        observing = false;
        for(Map.Entry<IObservable, HashSet<IObserver>> entry : ObservableToObserver.entrySet())
        {
            for(IObserver observer : entry.getValue())
                entry.getKey().stopObserve(observer);
        }
    }
}
