package util.graph;

import components.observable.IListener;
import components.observable.IObservable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ObservableVertex<T> implements IVertex<T>, IObservable<ObservableVertexChange<T>> {

    private final Set<IListener<ObservableVertexChange<T>>> listeners = new HashSet<>();
    protected final ObservableGraph<T> obsGraph;
    protected final IVertex<T> vertex;


    protected ObservableVertex(ObservableGraph<T> obsGraph, IVertex<T> vertex) {
        this.obsGraph = obsGraph;
        this.vertex = vertex;
    }


    @Override
    public void startListen(IListener<ObservableVertexChange<T>> listener) {
        listeners.add(listener);

        ObservableVertexChange<T> change = new ObservableVertexChange<>();

        change.addedEdges = new ArrayList<>();
        for(var v : obsGraph.convertIterableToObsVertex( obsGraph.graph.getOutVertices(vertex) ))
            change.addedEdges.add(v);

        listener.onChange(change);
    }

    @Override
    public void stopListen(IListener<ObservableVertexChange<T>> listener) {
        listeners.remove(listener);
    }

    protected void updateListeners(ObservableVertexChange<T> change) {
        for(var listener : listeners)
            listener.onChange(change);
    }

    @Override
    public T getElement() {
        return vertex.getElement();
    }

    @Override
    public ObservableGraph<T> getGraph() {
        return obsGraph;
    }

}
