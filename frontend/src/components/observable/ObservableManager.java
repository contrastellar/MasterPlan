package components.observable;

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
                entry.getKey().addListener(listener);
        }
    }

    public void stopListen() {
        listening = false;
        for(Map.Entry<IObservable, HashSet<IListener>> entry : ObservableToListener.entrySet()) {
            for(IListener listener : entry.getValue())
                entry.getKey().removeListener(listener);
        }
    }

    public void addListener(IObservable observable, IListener listener) {
        HashSet<IListener> listeners = ObservableToListener.computeIfAbsent(observable, k -> new HashSet<>());
        listeners.add(listener);

        if(listening)
            observable.addListener(listener);
    }

    public void removeListener(IObservable observable, IListener listener) {
        HashSet<IListener> listeners = ObservableToListener.get(observable);
        if(listeners != null) {
            if(listening)
                observable.removeListener(listener);

            listeners.remove(listener);
        }
    }

    public void removeAllListeners(IObservable observable)
    {
        if(listening) {
            HashSet<IListener> listeners = ObservableToListener.get(observable);
            if(listeners != null) {
                for(IListener listener : listeners)
                    observable.removeListener(listener);
            }
        }

        ObservableToListener.remove(observable);
    }

    public boolean isListening() { return listening; }
}
