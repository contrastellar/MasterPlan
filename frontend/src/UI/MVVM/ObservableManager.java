package com.company.MVVM;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ObservableManager
{
    private final HashMap<IObservable, HashSet<IObserver>> ObservableToObserver = new HashMap<>();
    private boolean observing = false;

    public ObservableManager() {  }

    public void AddObserver(IObservable observable, IObserver observer)
    {
        HashSet<IObserver> observers = ObservableToObserver.computeIfAbsent(observable, k -> new HashSet<>());
        observers.add(observer);

        if(observing)
            observable.StartObserve(observer);
    }

    public void RemoveObserver(IObservable observable, IObserver observer)
    {
        HashSet<IObserver> observers = ObservableToObserver.get(observable);
        if(observers != null)
        {
            if(observing)
                observable.StopObserve(observer);

            observers.remove(observer);
        }
    }

    public void RemoveAllObservers(IObservable observable)
    {
        if(observing)
        {
            HashSet<IObserver> observers = ObservableToObserver.get(observable);
            if(observers != null)
            {
                for(IObserver observer : observers)
                    observable.StopObserve(observer);
            }
        }
        ObservableToObserver.remove(observable);
    }

    public void StartObserve()
    {
    	observing = true;
        for(Map.Entry<IObservable, HashSet<IObserver>> entry : ObservableToObserver.entrySet())
        {
            for(IObserver observer : entry.getValue())
                entry.getKey().StartObserve(observer);
        }
    }

    public void StopObserve()
    {
        observing = false;
        for(Map.Entry<IObservable, HashSet<IObserver>> entry : ObservableToObserver.entrySet())
        {
            for(IObserver observer : entry.getValue())
                entry.getKey().StopObserve(observer);
        }
    }
}
