package MVVM;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ObservableManager {
    private final HashMap<IObservable, HashSet<IListener>> ObservableToListener = new HashMap<>();
    private boolean listening = false;

    public ObservableManager() {  }

    public void startListen() {
        listening = true;
        for(Map.Entry<IObservable, HashSet<IListener>> entry : ObservableToListener.entrySet()) {
            for(IListener listener : entry.getValue())
                entry.getKey().startListen(listener);
        }
    }

    public void stopListen() {
        listening = false;
        for(Map.Entry<IObservable, HashSet<IListener>> entry : ObservableToListener.entrySet()) {
            for(IListener listener : entry.getValue())
                entry.getKey().stopListen(listener);
        }
    }

    public void addListener(IObservable observable, IListener listener) {
        HashSet<IListener> listeners = ObservableToListener.computeIfAbsent(observable, k -> new HashSet<>());
        listeners.add(listener);

        if(listening)
            observable.startListen(listener);
    }

    public void removeListener(IObservable observable, IListener listener) {
        HashSet<IListener> listeners = ObservableToListener.get(observable);
        if(listeners != null) {
            if(listening)
                observable.stopListen(listener);

            listeners.remove(listener);
        }
    }

    public void removeAllListeners(IObservable observable)
    {
        if(listening) {
            HashSet<IListener> listeners = ObservableToListener.get(observable);
            if(listeners != null) {
                for(IListener listener : listeners)
                    observable.stopListen(listener);
            }
        }

        ObservableToListener.remove(observable);
    }
}
