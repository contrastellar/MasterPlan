package util.graph;


import components.observable.IListener;
import components.observable.IObservable;

import java.util.*;

public class ObservableGraph<T> implements IGraph<T>, IObservable<ObservableGraphChange<T>> {
    protected final IGraph<T> graph;
    private final Set<IListener<ObservableGraphChange<T>>> listeners = new HashSet<>();
    private final Map<IVertex<T>, ObservableVertex<T>> vertexToObservable = new HashMap<>();


    public ObservableGraph(IGraph<T> graph) {
        this.graph = graph;

        for(IVertex<T> v : graph.getVertices())
            vertexToObservable.put(v, new ObservableVertex<>(this, v));

    }

    public void startListen(IListener<ObservableGraphChange<T>> listener) {
        ObservableGraphChange<T> change = new ObservableGraphChange<>();
        change.addedVertices = new ArrayList<>();

        for(var vertex : graph.getVertices())
            change.addedVertices.add(vertex);

        listeners.add(listener);
        listener.onChange(change);
    }

    public void stopListen(IListener<ObservableGraphChange<T>> listener) {
        listeners.remove(listener);
    }

    private void updateListeners(ObservableGraphChange<T> change) {
        for(var listener : listeners)
            listener.onChange(change);
    }

    protected ObservableVertex<T> validateVertex(IVertex<T> v) {
        ObservableVertex<T> retV;

        if((retV = vertexToObservable.get(v)) != null)
            return retV;

        if(!(v instanceof ObservableVertex))
            throw new IllegalArgumentException("Given vertex is not or does not have a corresponding ObservableVertex");

        return (ObservableVertex<T>) v;
    }

    protected List<ObservableVertex<T>> convertIterableToObsVertexList(Iterable<? extends IVertex<T>> vertices) {
        ArrayList<ObservableVertex<T>> retList = new ArrayList<>();

        for(IVertex<T> vertex : vertices)
            retList.add(vertexToObservable.get(vertex));

        return retList;
    }

    protected Iterable<ObservableVertex<T>> convertIterableToObsVertex(Iterable<? extends IVertex<T>> vertices) {
        return () -> new Iterator<>() {
            private final Iterator<? extends IVertex<T>> iterator = vertices.iterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ObservableVertex<T> next() {
                return vertexToObservable.get(iterator.next());
            }
        };
    }

    @Override
    public Iterable<ObservableVertex<T>> getVertices() {
        return convertIterableToObsVertex(graph.getVertices());
    }

    @Override
    public Iterable<ObservableVertex<T>> getOutVertices(IVertex<T> v)  {
        ObservableVertex<T> obsV = validateVertex(v);
        return convertIterableToObsVertex(graph.getOutVertices(obsV.vertex));
    }

    @Override
    public int getOutDegree(IVertex<T> v) {
        ObservableVertex<T> obsV = validateVertex(v);
        return graph.getOutDegree(obsV.vertex);
    }

    @Override
    public Iterable<ObservableVertex<T>> getInVertices(IVertex<T> v)  {
        ObservableVertex<T> obsV = validateVertex(v);
        return convertIterableToObsVertex(graph.getInVertices(obsV.vertex));
    }

    @Override
    public int getInDegree(IVertex<T> v) {
        ObservableVertex<T> obsV = validateVertex(v);
        return graph.getInDegree(obsV.vertex);
    }

    @Override
    public List<ObservableVertex<T>> query(IQuery<T> queryFunc) {
        return convertIterableToObsVertexList(graph.query(queryFunc));
    }

    @Override
    public List<ObservableVertex<T>> query(IQuery<T> queryFunc, IVertex<T> v) {
        ObservableVertex<T> obsV = validateVertex(v);
        return convertIterableToObsVertexList(graph.query(queryFunc, obsV.vertex));
    }

    @Override
    public List<ObservableVertex<T>> queryReachable(IQuery<T> queryFunc, IVertex<T> v) {
        ObservableVertex<T> obsV = validateVertex(v);
        return convertIterableToObsVertexList(graph.queryReachable(queryFunc, obsV.vertex));
    }

    @Override
    public int size() {
        return graph.size();
    }

    @Override
    public ObservableVertex<T> addVertex(T element) {

        IVertex<T> vertex = graph.addVertex(element);

        ObservableVertex<T> obsVertex = new ObservableVertex<>(this, vertex);
        vertexToObservable.put(vertex, obsVertex);

        ObservableGraphChange<T> change = new ObservableGraphChange<>();
        change.addedVertices = new ArrayList<>();
        change.addedVertices.add(vertex);

        updateListeners(change);

        return obsVertex;
    }

    @Override
    public synchronized ObservableVertex<T> addVertex(T element, IVertex<T> inVertex) {
        ObservableVertex<T> obsInVertex = validateVertex(inVertex);

        IVertex<T> vertex = graph.addVertex(element, obsInVertex.vertex);
        ObservableVertex<T> obsVertex = new ObservableVertex<>(this, vertex);
        vertexToObservable.put(vertex, obsVertex);

        ObservableGraphChange<T> changeGraph = new ObservableGraphChange<>();
        changeGraph.addedVertices = new ArrayList<>();
        changeGraph.addedVertices.add(vertex);

        updateListeners(changeGraph);

        ObservableVertexChange<T> changeVertex = new ObservableVertexChange<>();
        changeVertex.addedEdges = new ArrayList<>();
        changeVertex.addedEdges.add(obsVertex);

        obsInVertex.updateListeners(changeVertex);

        return obsVertex;
    }

    @Override
    public void removeVertex(IVertex<T> v) {
        ObservableVertex<T> obsV = validateVertex(v);

        ObservableVertexChange<T> changeVertex = new ObservableVertexChange<>();
        changeVertex.removedEdges = new ArrayList<>();
        changeVertex.removedEdges.add(obsV);

        List<ObservableVertex<T>> obsVertices = convertIterableToObsVertexList(graph.getInVertices(v));

        graph.removeVertex(obsV.vertex);

        ObservableGraphChange<T> changeGraph = new ObservableGraphChange<>();
        changeGraph.removedVertices = new ArrayList<>();
        changeGraph.removedVertices.add(v);

        updateListeners(changeGraph);

        for(var obsVertex : obsVertices)
            obsVertex.updateListeners(changeVertex);

    }

    @Override
    public void removeVertexReachable(IVertex<T> v) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public void addDirectedEdge(IVertex<T> v1, IVertex<T> v2) {

        ObservableVertex<T> obsV1 = validateVertex(v1);
        ObservableVertex<T> obsV2 = validateVertex(v2);

        graph.addDirectedEdge(obsV1.vertex, obsV2.vertex);

        ObservableVertexChange<T> change = new ObservableVertexChange<>();
        change.addedEdges = new ArrayList<>();
        change.addedEdges.add(obsV2);

        obsV1.updateListeners(change);
    }

    @Override
    public void removeDirectedEdge(IVertex<T> v1, IVertex<T> v2) {
        ObservableVertex<T> obsV1 = validateVertex(v1);
        ObservableVertex<T> obsV2 = validateVertex(v2);

        graph.removeDirectedEdge(obsV1.vertex, obsV2.vertex);

        ObservableVertexChange<T> change = new ObservableVertexChange<>();
        change.removedEdges = new ArrayList<>();
        change.removedEdges.add(obsV2);

        obsV1.updateListeners(change);
    }

    @Override
    public void sort(Comparator<T> c) {
        graph.sort(c);

        ObservableGraphChange<T> change = new ObservableGraphChange<>();
        change.sorted = true;

        updateListeners(change);
    }

    @Override
    public void sort(Comparator<T> c, IVertex<T> v) {
        ObservableVertex<T> obsV = validateVertex(v);

        graph.sort(c, obsV.vertex);

        ObservableVertexChange<T> change = new ObservableVertexChange<>();
        change.sorted = true;
        change.sortingComparator = c;

        obsV.updateListeners(change);
    }

    @Override
    public void sortReachable(Comparator<T> c, IVertex<T> v) {
        ObservableVertex<T> obsV = validateVertex(v);
        graph.sortReachable(c, obsV.vertex);

        ObservableVertexChange<T> change = new ObservableVertexChange<>();
        change.sorted = true;
        change.sortingComparator = c;

        obsV.updateListeners(change);
    }
}
