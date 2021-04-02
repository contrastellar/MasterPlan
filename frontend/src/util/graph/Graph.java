package util.graph;

import java.util.*;

/*
    Graph<TodoElement, Vertex<TodoElement>> graph

    ObservableGraph<TodoElement> obsGraph = new ObservableGraph<>()

    Graph<TodoElement, ObvservableVertex<TodoElement>>

*/

public class Graph<T> implements IGraph<T> {

    public class Vertex implements IVertex<T> {

        private final T element;
        private final List<Vertex> outVertices = new LinkedList<>();
        private final List<Vertex> inVertices = new LinkedList<>();

        public Vertex(T element) {
            this.element = element;
        }

        @Override
        public void sort(Comparator<T> c) {
            Comparator<Vertex> cVertex = (v1, v2) -> c.compare(v1.element, v2.element);

            outVertices.sort(cVertex);
        }

        @Override
        public void sortRecursive(Comparator<T> c) {
            Comparator<Vertex> cVertex = (v1, v2) -> c.compare(v1.element, v2.element);

            sortRecursive(cVertex, new HashSet<>());
        }

        private void sortRecursive(Comparator<Vertex> c, Set<Vertex> sorted) {
            if(sorted.contains(this))
                return;
            else
                sorted.add(this);

            inVertices.sort(c);
            outVertices.sort(c);

            for(Vertex v : outVertices)
                v.sortRecursive(c, sorted);
        }

        @Override
        public List<Vertex> query(IQuery<T> queryFunc) {

            ArrayList<Vertex> queryRes = new ArrayList<>();

            for(Vertex v : outVertices)
                if(queryFunc.query(v.element))
                    queryRes.add(v);

            return queryRes;
        }

        @Override
        public List<Vertex> queryRecursive(IQuery<T> queryFunc) {
            ArrayList<Vertex> queryRes = new ArrayList<>();
            HashSet<Vertex> queried = new HashSet<>();

            queryRecursive(queryFunc, queryRes, queried);

            return queryRes;
        }

        private void queryRecursive(IQuery<T> queryFunc, List<Vertex> queryRes, Set<Vertex> queried) {
            if(queried.contains(this))
                return;
            else
                queried.add(this);

            for(Vertex v : outVertices)
                if(queryFunc.query(v.element))
                    queryRes.add(v);
        }

        protected void addDirectedEdge(Vertex v) {
            outVertices.add(v);
            v.inVertices.add(this);
        }

        protected void removeDirectedEdge(Vertex v) {
            outVertices.remove(v);
            v.inVertices.remove(this);
        }

        @Override
        public T getElement() { return element; }

        @Override
        public Iterable<Vertex> getOutVertices() { return outVertices; }

        @Override
        public Iterable<Vertex> getInVertices() { return inVertices; }

        @Override
        public Graph<T> getGraph() { return Graph.this; }

    }

    private final Vertex rootVertex;
    private final Map<T, Vertex> elementToVertex = new HashMap<>();

    public Graph(T rootElement) {
        if(rootElement == null)
            throw new IllegalArgumentException("rootElement can not be null");

        rootVertex = new Vertex(rootElement);
        elementToVertex.put(rootElement, rootVertex);
    }

    private Vertex validateIVertex(IVertex<T> v) {
        if(!(v instanceof Graph.Vertex))
            throw new IllegalArgumentException("given vertex is not an instance of Graph.Vertex");

        return (Vertex) v;
    }

    private void validateVertex(Vertex v) {

        if(v == null)
            throw new IllegalArgumentException("given vertex can not be null");

        if(elementToVertex.get(v.getElement()) != v)
            throw new IllegalArgumentException("given vertex does not exist in graph");

    }

    @Override
    public Vertex addVertex(T element) {
        if(element == null)
            throw new IllegalArgumentException("element can not be null");

        Vertex vertex;

        // this statement ensures a 1-1 mapping between elements and vertices
        if((vertex = elementToVertex.get(element)) != null)
            return vertex;

        vertex = new Vertex(element);
        rootVertex.addDirectedEdge(vertex);
        elementToVertex.put(element, vertex);
        return vertex;
    }

    public Vertex addVertex(T element, Vertex parentVertex) {
        if(element == null)
            throw new IllegalArgumentException("element can not be null");

        validateVertex(parentVertex);

        if(elementToVertex.containsKey(element))
            throw new IllegalArgumentException("duplicate elements are not allowed");

        Vertex vertex = new Vertex(element);
        parentVertex.addDirectedEdge(vertex);

        return vertex;
    }

    @Override
    public void removeVertex(IVertex<T> v) {
        Vertex vertex = validateIVertex(v);

        removeVertex(vertex);
    }

    public void removeVertex(Vertex v) {
        validateVertex(v);

        if(v == rootVertex)
            throw new IllegalArgumentException("given vertex is root vertex; can not remove root vertex");

        elementToVertex.remove(v.element);
    }

    // returns true if v2 is reachable from v1, false otherwise
    private boolean DFS(Vertex v1, Vertex v2) {
        return DFS(v1, v2, new HashSet<>());
    }

    // returns true if v2 is reachable from v1, false otherwise
    private boolean DFS(Vertex v1, Vertex v2, Set<Vertex> visited) {
        if(visited.contains(v2))
            return false;

        if(v1 == v2)
            return true;
        else
            visited.add(v2);

        for(Vertex v : v2.outVertices)
            if(DFS(v1, v))
                return true;

        return false;
    }

    private void checkForCircularity(Vertex v1, Vertex v2) {
        if( DFS(v1, v2) )
            throw new IllegalArgumentException("v1 is reachable from v2, circularity detected");
    }

    @Override
    public void addDirectedEdge(IVertex<T> v1, IVertex<T> v2) {
        Vertex vertex1 = validateIVertex(v1);
        Vertex vertex2 = validateIVertex(v2);

        addDirectedEdge(vertex1, vertex2);
    }

    public void addDirectedEdge(Vertex v1, Vertex v2) {
        validateVertex(v1);
        validateVertex(v2);

        checkForCircularity(v1, v2);

        v1.addDirectedEdge(v2);
    }

    @Override
    public void removeDirectedEdge(IVertex<T> v1, IVertex<T> v2) {
        Vertex vertex1 = validateIVertex(v1);
        Vertex vertex2 = validateIVertex(v2);

        removeDirectedEdge(vertex1, vertex2);
    }

    public void removeDirectedEdge(Vertex v1, Vertex v2) {
        validateVertex(v1);
        validateVertex(v2);

        v1.removeDirectedEdge(v2);
    }

    @Override
    public void sort(Comparator<T> c) {
        // TODO: sort elementsToVertex (or the backing of getVertices())

        for(Vertex v : getVertices())
            v.sort(c);

        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public void sort(Comparator<T> c, IVertex<T> v) {
        Vertex vertex = validateIVertex(v);
        sort(c, vertex);
    }

    public void sort(Comparator<T> c, Vertex v) {
        validateVertex(v);
        v.sort(c);
    }

    @Override
    public void sortRecursive(Comparator<T> c, IVertex<T> v) {
        Vertex vertex = validateIVertex(v);
        sortRecursive(c, vertex);
    }

    public void sortRecursive(Comparator<T> c, Vertex v) {
        validateVertex(v);
        v.sortRecursive(c);
    }

    @Override
    public List<Vertex> query(IQuery<T> queryFunc) {
        ArrayList<Vertex> queryRes = new ArrayList<>();

        for(Vertex v : getVertices())
            if(queryFunc.query(v.element))
                queryRes.add(v);

        return queryRes;
    }

    @Override
    public List<Vertex> query(IQuery<T> queryFunc, IVertex<T> v) {
        Vertex vertex = validateIVertex(v);
        return query(queryFunc, vertex);
    }

    public List<Vertex> query(IQuery<T> queryFunc, Vertex v) {
        validateVertex(v);
        return v.query(queryFunc);
    }

    @Override
    public List<Vertex> queryRecursive(IQuery<T> queryFunc, IVertex<T> v) {
        Vertex vertex = validateIVertex(v);
        return queryRecursive(queryFunc, vertex);
    }

    public List<Vertex> queryRecursive(IQuery<T> queryFunc, Vertex v) {
        validateVertex(v);
        return v.query(queryFunc);
    }

    @Override
    public Iterable<Vertex> getVertices() { return elementToVertex.values(); }

    public Vertex getRootVertex() { return rootVertex; }
}
