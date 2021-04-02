package components.observable;

import util.graph.*;

import java.util.*;


// TODO: convert IVertex to ObservableVertex
public class ObservableGraph<T> implements IObservable, IGraph<T>{

    private final IGraph<T> graph;
    private final Set<IListener> listeners = new HashSet<>();
    private final Map<IVertex<T>, ObservableVertex<T>> vertexToObservable = new HashMap<>();

    public ObservableGraph(IGraph<T> graph) {
        this.graph = graph;
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

    private ObservableVertex<T> validateVertex(IVertex<T> v) {
        ObservableVertex<T> retV;

        if((retV = vertexToObservable.get(v)) != null)
            return retV;

        if(!(v instanceof ObservableVertex))
            throw new IllegalArgumentException("v is not an ObservableVertex");

        return (ObservableVertex <T>) v;
    }

    protected List<ObservableVertex<T>> convertListToObsVertex(List<? extends IVertex<T>> vertices){
        ArrayList<ObservableVertex<T>> retList = new ArrayList<>();

        for(IVertex<T> vertex : vertices)
            retList.add(vertexToObservable.get(vertex));

        return retList;
    }

    protected Iterable<ObservableVertex<T>> convertIterableToObsVertex(Iterable<? extends IVertex<T>> vertices) {
        return new Iterable<ObservableVertex<T>>() {
            private final Iterator<? extends IVertex<T>> iterator = graph.getVertices().iterator();
            @Override
            public Iterator<ObservableVertex<T>> iterator() {
                return new Iterator<ObservableVertex<T>>() {
                    @Override
                    public boolean hasNext() {
                        return iterator.hasNext();
                    }

                    @Override
                    public ObservableVertex<T> next() {
                        return vertexToObservable.get(iterator().next());
                    }
                };
            }
        };
    }

    @Override // IGraph
    public List<ObservableVertex<T>> query(IQuery<T> queryFunc) {
        return convertListToObsVertex( graph.query(queryFunc) );
    }

    @Override // IGraph
    public List<ObservableVertex<T>> query(IQuery<T> queryFunc, IVertex<T> v) {
        ObservableVertex<T> obsV = validateVertex(v);
        return this.query(queryFunc, obsV);
    }

    public List<ObservableVertex<T>> query(IQuery<T> queryFunc, ObservableVertex<T> v) {
        return convertListToObsVertex( graph.query(queryFunc, v.vertex) );
    }

    @Override // IGraph
    public List<ObservableVertex<T>> queryRecursive(IQuery<T> queryFunc, IVertex<T> v) {
        ObservableVertex<T> obsV = validateVertex(v);
        return this.queryRecursive(queryFunc, obsV);
    }

    public List<ObservableVertex<T>> queryRecursive(IQuery<T> queryFunc, ObservableVertex<T> v) {
        return convertListToObsVertex( graph.queryRecursive(queryFunc, v.vertex) );
    }

    @Override // IGraph
    public Iterable<ObservableVertex<T>> getVertices() {
        return convertIterableToObsVertex(graph.getVertices());
    }

    @Override // IGraph
    public ObservableVertex<T> addVertex(T t) {
        IVertex<T> v = graph.addVertex(t);

        ObservableVertex<T> obsV;

        if(!vertexToObservable.containsValue(v)) {
            obsV = new ObservableVertex<>(this, v);
            vertexToObservable.put(v, obsV);
        } else obsV = vertexToObservable.get(v);

        updateListeners();

        return obsV;
    }

    @Override // IGraph
    public void removeVertex(IVertex<T> v) {
        ObservableVertex<T> obsV = validateVertex(v);

        graph.removeVertex(obsV.vertex);
        vertexToObservable.remove(obsV);

        updateListeners();
    }

    @Override // IGraph
    public void addDirectedEdge(IVertex<T> v1, IVertex<T> v2) {
        ObservableVertex<T> obsV1 = validateVertex(v1);
        ObservableVertex<T> obsV2 = validateVertex(v2);

        graph.addDirectedEdge(obsV1.vertex, obsV2.vertex);

        updateListeners();
    }

    @Override // IGraph
    public void removeDirectedEdge(IVertex<T> v1, IVertex<T> v2) {
        ObservableVertex<T> obsV1 = validateVertex(v1);
        ObservableVertex<T> obsV2 = validateVertex(v2);

        graph.addDirectedEdge(obsV1.vertex, obsV2.vertex);

        updateListeners();
    }

    @Override // IGraph
    public void sort(Comparator<T> c) {
        graph.sort(c);
        updateListeners();
    }

    @Override // IGraph
    public void sort(Comparator<T> c, IVertex<T> v) {
        ObservableVertex<T> obsV = validateVertex(v);

        graph.sort(c, obsV.vertex);
        updateListeners();
    }

    @Override // IGraph
    public void sortRecursive(Comparator<T> c, IVertex<T> v) {
        ObservableVertex<T> obsV = validateVertex(v);

        graph.sortRecursive(c, obsV.vertex);
        updateListeners();
    }
}
