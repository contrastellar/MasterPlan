package components.observable;

import util.graph.*;

import java.util.*;


// TODO: convert IVertex to ObservableVertex
public class ObservableGraph<T> implements IObservable, IGraph<T>{

    private final IGraph<T> graph;
    private final Set<IListener> listeners = new HashSet<>();
    private final Map<IVertexAdjList<T>, ObservableVertexAdjList<T>> vertexToObservable = new HashMap<>();

    public ObservableGraph(IGraph<T> graph) {
        this.graph = graph;

        for(IVertexAdjList<T> v : graph.getVertices())
            vertexToObservable.put(v, new ObservableVertexAdjList<>(this, v));
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

    protected ObservableVertexAdjList<T> validateVertex(IVertexAdjList<T> v) {
        ObservableVertexAdjList<T> retV;

        if((retV = vertexToObservable.get(v)) != null)
            return retV;

        if(!(v instanceof ObservableVertexAdjList))
            throw new IllegalArgumentException("v is not an ObservableVertex");

        return (ObservableVertexAdjList<T>) v;
    }

    protected List<ObservableVertexAdjList<T>> convertListToObsVertex(List<? extends IVertexAdjList<T>> vertices){
        ArrayList<ObservableVertexAdjList<T>> retList = new ArrayList<>();

        for(IVertexAdjList<T> vertex : vertices)
            retList.add(vertexToObservable.get(vertex));

        return retList;
    }

    protected Iterable<ObservableVertexAdjList<T>> convertIterableToObsVertex(Iterable<? extends IVertexAdjList<T>> vertices) {
        return new Iterable<>() {
            private final Iterator<? extends IVertexAdjList<T>> iterator = vertices.iterator();

            @Override
            public Iterator<ObservableVertexAdjList<T>> iterator() {
                return new Iterator<>() {
                    @Override
                    public boolean hasNext() {
                        return iterator.hasNext();
                    }

                    @Override
                    public ObservableVertexAdjList<T> next() {
                        return vertexToObservable.get(iterator().next());
                    }
                };
            }
        };
    }

    @Override // IGraph
    public List<ObservableVertexAdjList<T>> query(IQuery<T> queryFunc) {
        return convertListToObsVertex( graph.query(queryFunc) );
    }

    @Override // IGraph
    public Iterable<ObservableVertexAdjList<T>> getVertices() {
        return convertIterableToObsVertex(graph.getVertices());
    }

    @Override // IGraph
    public ObservableVertexAdjList<T> addVertex(T t) {
        IVertexAdjList<T> v = graph.addVertex(t);

        ObservableVertexAdjList<T> obsV;

        if(!vertexToObservable.containsValue(v)) {
            obsV = new ObservableVertexAdjList<>(this, v);
            vertexToObservable.put(v, obsV);
        }
        else {
            obsV = vertexToObservable.get(v);
        }

        updateListeners();

        return obsV;
    }

    @Override // IGraph
    public void removeVertex(IVertexAdjList<T> v) {
        ObservableVertexAdjList<T> obsV = validateVertex(v);

        graph.removeVertex(obsV.vRep);
        vertexToObservable.remove(obsV);

        updateListeners();
        obsV.updateListeners();
    }

    @Override // IGraph
    public void sort(Comparator<T> c) {
        graph.sort(c);
        updateListeners();
    }
}
