package components.observable;


import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.graph.IGraph;
import util.graph.IQuery;
import util.graph.IVertex;

// TODO: Observable
public class ObservableVertex<T> implements IObservable, IVertex<T> {

    protected final IVertex<T> vertex;
    private final ObservableGraph<T> obsGraph;
    private final Set<IListener> listeners = new HashSet<>();

    protected ObservableVertex(ObservableGraph<T> obsGraph, IVertex<T> vertex) {
        if (obsGraph == null)
            throw new IllegalArgumentException("ObservableVertex() - Cannot construct on a null obsGraph");
        if (vertex == null)
            throw new IllegalArgumentException("ObservableVertex() - Cannot construct an observable obsGraph");
        this.obsGraph = obsGraph;
        this.vertex = vertex;
    }

    @Override // IObservable
    public void addListener(IListener listener) {
        listeners.add(listener);
        listener.onChange();
    }


    @Override // IObservable
    public void removeListener(IListener listener) {
        listeners.remove(listener);
    }

    private void updateListeners() {
        for(IListener listener : listeners)
            listener.onChange();
    }

    @Override
    public T getElement() {
        return vertex.getElement();
    }

    @Override // IVertex
    public void sort(Comparator<T> c) {
        obsGraph.sort(c, this);
        updateListeners();
    }

    @Override // IVertex
    public void sortRecursive(Comparator<T> c) {
        obsGraph.sortRecursive(c, this);
        updateListeners();
    }

    @Override // IVertex
    public List<ObservableVertex<T>> query(IQuery<T> queryFunc) {
        List<ObservableVertex<T>> list = obsGraph.query(queryFunc, this);
        updateListeners();
        return list;
    }

    @Override // IVertex
    public List<ObservableVertex<T>> queryRecursive(IQuery<T> queryFunc) {
        List<ObservableVertex<T>> list = obsGraph.queryRecursive(queryFunc, this);
        updateListeners();
        return list;
    }

    @Override // IVertex
    public Iterable<ObservableVertex<T>> getOutVertices() {
        return obsGraph.convertIterableToObsVertex( vertex.getOutVertices() );
    }

    @Override // IVertex
    public Iterable<ObservableVertex<T>> getInVertices() {
        return obsGraph.convertIterableToObsVertex( vertex.getInVertices() );
    }

    @Override // IVertex
   public IGraph<T> getGraph() {
        return obsGraph;
    }

}

