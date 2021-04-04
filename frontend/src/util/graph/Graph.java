package util.graph;

import java.util.*;

/*
    Graph<TodoElement, Vertex<TodoElement>> graph

    ObservableGraph<TodoElement> obsGraph = new ObservableGraph<>()

    Graph<TodoElement, ObvservableVertex<TodoElement>>

*/

public class Graph<T> implements IGraph<T> {

    public class VertexAdjListAdjList implements IVertexAdjList<T> {

        private final T element;
        private final List<VertexAdjListAdjList> outVertices = new LinkedList<>();
        private final List<VertexAdjListAdjList> inVertices = new LinkedList<>();

        public VertexAdjListAdjList(T element) {
            this.element = element;
        }

        @Override // IVertex
        public void sort(Comparator<T> c) {
            Comparator<VertexAdjListAdjList> cVertex = (v1, v2) -> c.compare(v1.element, v2.element);

            outVertices.sort(cVertex);
        }

        @Override // IVertex
        public void sortReachable(Comparator<T> c) {
            Comparator<VertexAdjListAdjList> cVertex = (v1, v2) -> c.compare(v1.element, v2.element);

            sortReachable(cVertex, new HashSet<>());
        }

        private void sortReachable(Comparator<VertexAdjListAdjList> c, Set<VertexAdjListAdjList> sorted) {
            if(sorted.contains(this))
                return;
            else
                sorted.add(this);

            inVertices.sort(c);
            outVertices.sort(c);

            for(VertexAdjListAdjList v : outVertices)
                v.sortReachable(c, sorted);
        }

        /* Queries the adjacency list of a vertex instance */
        @Override // IVertex
        public List<VertexAdjListAdjList> query(IQuery<T> queryFunc) {

            ArrayList<VertexAdjListAdjList> queryRes = new ArrayList<>();

            for(VertexAdjListAdjList v : outVertices)
                if(queryFunc.query(v.element))
                    queryRes.add(v);

            return queryRes;
        }

        /* Queries all reachable vertices of a vertex instance */
        @Override // IVertex
        public List<VertexAdjListAdjList> queryReachable(IQuery<T> queryFunc) {
            ArrayList<VertexAdjListAdjList> queryRes = new ArrayList<>();
            HashSet<VertexAdjListAdjList> queried = new HashSet<>();

            queryReachable(queryFunc, queryRes, queried);

            return queryRes;
        }

        /* Queries all reachable vertices of a vertex instance */
        private void queryReachable(IQuery<T> queryFunc, List<VertexAdjListAdjList> queryRes, Set<VertexAdjListAdjList> queried) {
            if(queried.contains(this))
                return;
            else
                queried.add(this);

            for(VertexAdjListAdjList v : outVertices)
                if(queryFunc.query(v.element))
                    queryRes.add(v);
        }

        // returns true if v2 is reachable from v1, false otherwise
        private boolean DFS(VertexAdjListAdjList v1, VertexAdjListAdjList v2) {
            return DFS(v1, v2, new HashSet<>());
        }

        // returns true if v2 is reachable from v1, false otherwise
        private boolean DFS(VertexAdjListAdjList v1, VertexAdjListAdjList v2, Set<VertexAdjListAdjList> visited) {
            if(visited.contains(v2))
                return false;

            if(v1 == v2)
                return true;
            else
                visited.add(v2);

            for(VertexAdjListAdjList v : v2.outVertices)
                if(DFS(v1, v))
                    return true;

            return false;
        }

        private void checkForCircularity(VertexAdjListAdjList v1, VertexAdjListAdjList v2) {
            if( DFS(v1, v2) )
                throw new IllegalArgumentException("v1 is reachable from v2, circularity detected");
        }

        @Override // IVertex
        public void addDirectedEdge(IVertexAdjList<T> v) {
            VertexAdjListAdjList vertexAdjList = Graph.this.validateIVertex(v);
            addDirectedEdge(vertexAdjList);
        }

        public void addDirectedEdge(VertexAdjListAdjList v) {
            Graph.this.validateVertex(v);

            checkForCircularity(this, v);

            outVertices.add(v);
            v.inVertices.add(this);
        }

        @Override // IVertex
        public void removeDirectedEdge(IVertexAdjList<T> v) {
            VertexAdjListAdjList vertexAdjList = Graph.this.validateIVertex(v);
            removeDirectedEdge(vertexAdjList);
        }

        public void removeDirectedEdge(VertexAdjListAdjList v) {
            Graph.this.validateVertex(v);
            outVertices.remove(v);
            v.inVertices.remove(this);
        }

        @Override // IVertex
        public T getElement() { return element; }

        @Override // IVertex
        public Iterable<VertexAdjListAdjList> getOutVertices() { return outVertices; }

        @Override // IVertex
        public Iterable<VertexAdjListAdjList> getInVertices() { return inVertices; }

        @Override // IVertex
        public Graph<T> getGraph() { return Graph.this; }

        @Override
        public boolean adjListContains(IVertexAdjList<T> v) {
            return outVertices.contains(v);
        }

    }

    /* Begin Graph Class */
    private final Map<T, VertexAdjListAdjList> elementToVertex = new HashMap<>();

    public Graph() { }

    private VertexAdjListAdjList validateIVertex(IVertexAdjList<T> v) {
        if(!(v instanceof Graph.VertexAdjListAdjList))
            throw new IllegalArgumentException("given vertex is not an instance of Graph.Vertex");

        return (VertexAdjListAdjList) v;
    }

    private void validateVertex(VertexAdjListAdjList v) {

        if(v == null)
            throw new IllegalArgumentException("given vertex can not be null");

        if(elementToVertex.get(v.getElement()) != v)
            throw new IllegalArgumentException("given vertex does not exist in graph");

    }

    @Override // IGraphWriteOnly
    public VertexAdjListAdjList addVertex(T element) {
        if(element == null)
            throw new IllegalArgumentException("element can not be null");

        VertexAdjListAdjList vertexAdjList;

        // this statement ensures a 1-1 mapping between elements and vertices
        if((vertexAdjList = elementToVertex.get(element)) != null)
            return vertexAdjList;

        vertexAdjList = new VertexAdjListAdjList(element);
        elementToVertex.put(element, vertexAdjList);
        return vertexAdjList;
    }

    @Override // IGraphWriteOnly
    public void removeVertex(IVertexAdjList<T> v) {
        VertexAdjListAdjList vertexAdjList = validateIVertex(v);
        removeVertex(vertexAdjList);
    }

    public void removeVertex(VertexAdjListAdjList v) {
        validateVertex(v);
        elementToVertex.remove(v.element);
    }



    @Override // IGraphWriteOnly
    public void sort(Comparator<T> c) {
        // TODO: sort elementsToVertex (or the backing of getVertices())

        for(VertexAdjListAdjList v : getVertices())
            v.sort(c);

        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override // IGraphReadOnly
    public List<VertexAdjListAdjList> query(IQuery<T> queryFunc) {
        ArrayList<VertexAdjListAdjList> queryRes = new ArrayList<>();

        for(VertexAdjListAdjList v : getVertices())
            if(queryFunc.query(v.element))
                queryRes.add(v);

        return queryRes;
    }

    @Override // IGraphReadOnly
    public Iterable<VertexAdjListAdjList> getVertices() { return elementToVertex.values(); }

}
