package components.observable;

import util.graph.*;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// TODO: convert IVertex to ObservableVertex
public class ObservableGraph<T> implements IObservable, IGraph<T>{

    private final IGraph<T> graph;
    private final Set<IListener> listeners = new HashSet<>();

    public ObservableGraph(IGraph<T> graph) {
        this.graph = graph;
    }

    @Override
    public void addListener(IListener listener) {
        listeners.add(listener);
        listener.onChange();
    }

    @Override
    public void removeListener(IListener listener) {
        listeners.remove(listener);
    }

    private void updateListeners() {
        for(IListener listener : listeners)
            listener.onChange();
    }

    @Override
    public List<? extends IVertex<T>> query(IQuery<T> queryFunc) {
        return graph.query(queryFunc);
    }

    @Override
    public List<? extends IVertex<T>> query(IQuery<T> queryFunc, IVertex<T> v) {
        return graph.query(queryFunc, v);
    }

    @Override
    public List<? extends IVertex<T>> queryRecursive(IQuery<T> queryFunc, IVertex<T> v) {
        return graph.queryRecursive(queryFunc, v);
    }

    @Override
    public Iterable<? extends IVertex<T>> getVertices() {
        return graph.getVertices();
    }

    @Override
    public IVertex<T> addVertex(T t) {
        IVertex<T> v = graph.addVertex(t);
        updateListeners();
        return v;
    }

    @Override
    public void removeVertex(IVertex<T> v) {
        graph.removeVertex(v);
        updateListeners();
    }

    @Override
    public void addDirectedEdge(IVertex<T> v1, IVertex<T> v2) {
        graph.addDirectedEdge(v1, v2);
        updateListeners();
    }

    @Override
    public void removeDirectedEdge(IVertex<T> v1, IVertex<T> v2) {
        graph.removeDirectedEdge(v1, v2);
        updateListeners();
    }

    @Override
    public void sort(Comparator<T> c) {
        graph.sort(c);
        updateListeners();
    }

    @Override
    public void sort(Comparator<T> c, IVertex<T> v) {
        graph.sort(c, v);
        updateListeners();
    }

    @Override
    public void sortRecursive(Comparator<T> c, IVertex<T> v) {
        graph.sortRecursive(c, v);
        updateListeners();
    }
}