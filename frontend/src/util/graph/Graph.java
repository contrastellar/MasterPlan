package util.graph;

import java.util.*;

import components.Category;
import components.TodoElement;

public class Graph implements IGraph {

    // static becuase vertices are shared amongst all graph classes
    public static class Vertex implements IVertex {

        private final TodoElement element;

        // private DependencyExpression expr;
        // private final List<Vertex> children = new ArrayList<>();
        private final ArrayList<Vertex> children = new ArrayList<>();

        private Vertex(TodoElement element) {
            if(element == null)
                throw new IllegalArgumentException("element cannot be null");

            this.element = element;
        }

        public TodoElement getElement() { return element; }
        // note: purposefully not including setElement(). Otherwise, you would need to update elementToVertex

        private void addChild(Vertex v) { this.children.add(v); }
        private void removeChild(Vertex v) { this.children.remove(v); }

        public Iterable<Vertex> getChildren(){ return children; }

        private void sort(Comparator<Vertex> c) { this.children.sort(c); }
    }


    private final Vertex rootVertex;
    private final List<Vertex> vertices;

    // TODO: use a bidirectional 1:1 map
    // something like: http://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/BidiMap.html
    private final Map<TodoElement, Vertex> elementToVertex;


    public Graph() {
        Category rootCategory = new Category();
        rootCategory.setName("Main");

        this.vertices = new ArrayList<>();
        this.elementToVertex = new HashMap<>();

        this.rootVertex = addVertex(rootCategory);
    }

    public Graph(Graph graph, Vertex rootVertex) {

        if(graph == null)
            throw new IllegalArgumentException("graph can not be null");

        if(rootVertex == null)
            throw new IllegalArgumentException("rootVertex can not be null");

        if(!graph.vertices.contains(rootVertex))
            throw new IllegalArgumentException("rootVertex must be a vertex from the given graph");

        this.vertices = graph.vertices;
        this.elementToVertex = graph.elementToVertex;
        this.rootVertex = rootVertex;

        throw new UnsupportedOperationException("not implemented yet");
    }

    // O(n)
    private void validateVertex(Vertex v) {
        // TODO: currently O(n), make O(1)
        if(!elementToVertex.containsValue(v))
            throw new IllegalArgumentException("vertex does not exist in graph");
    }

    // amortized O(1)
    public Vertex addVertex(TodoElement e) {

        if(e == null)
            throw new IllegalArgumentException("null element is not allowed");

        if(elementToVertex.containsKey(e))
            throw new IllegalArgumentException("duplicate elements are not allowed");

        Vertex v = new Vertex(e);

        vertices.add(v);
        elementToVertex.put(e, v);

        return v;
    }

    // O(n^2)
    public void removeVertex(Vertex v) {

        validateVertex(v);

        if(v == rootVertex)
            throw new IllegalArgumentException("can not remove root vertex");

        // TODO: find a better way (solution: use doubly linked list)
        vertices.remove(v);
        elementToVertex.remove(v.element);

        // TODO: find a better way (solution: ?) (currently O(n^2)
        for(Vertex vertex : vertices) {
            vertex.children.remove(v);
        }
    }

    // O(V + E), or O(n)
    private void ensureNoCycles(Vertex v1, Vertex v2) {

        if(DFS(v1, v2))
            throw new IllegalArgumentException("cycles are not allowed");

    }

    // O(V + E), or O(n)
    // returns true if Vertex v1 is reachable from Vertex v2
    private boolean DFS(Vertex v1, Vertex v2) {
        if(v1.equals(v2))
            return true;

        for(Vertex v : v1.children)
            if(DFS(v1, v)) return true;

        return false;
    }

    // O(n + n + n + 1) = O(n)
    public void addDirectedEdge(Vertex from, Vertex to) {
        validateVertex(from);
        validateVertex(to);

        ensureNoCycles(from, to);

        from.children.add(to);
    }

    // O(n + n + n) = O(n)
    public void removeDirectedEdge(Vertex v1, Vertex v2) {
        validateVertex(v1);
        validateVertex(v2);

        v1.removeChild(v2);
    }

    // O(?) -> not good
    public void sort(Comparator<Vertex> c) {

        vertices.sort(c);

        for(Vertex v : vertices)
            v.sort(c);
    }

    public Iterable<Vertex> getVertices() { return vertices; }

    public Vertex getRootVertex() { return rootVertex; }

    public static class GraphCycleException extends Exception {  }

}
