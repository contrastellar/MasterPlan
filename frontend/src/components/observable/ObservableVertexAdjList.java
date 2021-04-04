package components.observable;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.graph.IGraph;
import util.graph.IQuery;
import util.graph.IVertexAdjList;

public class ObservableVertexAdjList<T> implements IObservable, IVertexAdjList<T> {

    protected final IVertexAdjList<T> vRep; // each adjacency list has a representative
    private final ObservableGraph<T> obsGraph;
    private final Set<IListener> listeners = new HashSet<>();

    protected ObservableVertexAdjList(ObservableGraph<T> obsGraph, IVertexAdjList<T> vRep) {
        if (obsGraph == null)
            throw new IllegalArgumentException("ObservableVertex() - Cannot construct on a null obsGraph");
        if (vRep == null)
            throw new IllegalArgumentException("ObservableVertex() - Cannot construct an observable obsGraph");
        this.obsGraph = obsGraph;
        this.vRep = vRep;
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

    protected void updateListeners() {
        for(IListener listener : listeners)
            listener.onChange();
    }

    @Override // IVertex
    public T getElement() {
        return vRep.getElement();
    }


    @Override // IVertex
    public void addDirectedEdge(IVertexAdjList<T> v) {
        ObservableVertexAdjList<T> obsV = obsGraph.validateVertex(v);

        vRep.addDirectedEdge(obsV.vRep);

        updateListeners();
    }

    @Override // IVertex
    public void removeDirectedEdge(IVertexAdjList<T> v) {
        ObservableVertexAdjList<T> obsV = obsGraph.validateVertex(v);

        vRep.removeDirectedEdge(obsV.vRep);

        updateListeners();
    }

    @Override // IVertex
    public void sort(Comparator<T> c) {
        vRep.sort(c);
        updateListeners();
    }

    @Override  // IVertex
    public void sortReachable(Comparator<T> c) {
        vRep.sortReachable(c);
        updateListeners();
    }

    @Override // IVertex
    public List<ObservableVertexAdjList<T>> query(IQuery<T> queryFunc) {
        var retList = obsGraph.convertListToObsVertex(vRep.query(queryFunc));
        updateListeners();
        return retList;
    }

    @Override // IVertex
    public List<ObservableVertexAdjList<T>> queryReachable(IQuery<T> queryFunc) {
        var retList = obsGraph.convertListToObsVertex(vRep.queryReachable(queryFunc));
        updateListeners();
        return retList;
    }

    @Override // IVertex
    public Iterable<ObservableVertexAdjList<T>> getOutVertices() {
        return obsGraph.convertIterableToObsVertex( vRep.getOutVertices() );
    }

    @Override // IVertex
    public Iterable<ObservableVertexAdjList<T>> getInVertices() {
        return obsGraph.convertIterableToObsVertex( vRep.getInVertices() );
    }

    @Override // IVertex
   public IGraph<T> getGraph() {
        return obsGraph;
    }

    @Override
    public boolean adjListContains(IVertexAdjList<T> v) {
        ObservableVertexAdjList<T> vertex = obsGraph.validateVertex(v);
        return vRep.adjListContains(vertex);
    }



}
