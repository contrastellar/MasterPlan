package util.graph;

import java.util.*;

import components.Category;
import components.Completable;
import components.Task.Task;
import components.TodoElement;

// TODO: pretty much everything
public class Graph {

    private final SortedSet<Vertex> _graph = new TreeSet<>();
    public  final ReadOnlyCollection<Vertex> graph;

    public final Vertex rootVertex;

    public class Vertex implements Comparable<Vertex>{

        private TodoElement e;

        // vertex is said to be invalid if _children == null
        private DependencyExpression expr;

        // private SortedSet<TodoElement> _children;
        // public ReadOnlyCollection<TodoElement> children;

        private Vertex(TodoElement e) {
            if(e == null)
                throw new IllegalArgumentException("element cannot be null");

            this.e = e;
        }

        public boolean isCategory() { return e instanceof Category; }
        public boolean isTask()     { return e instanceof Task;     }

        public boolean isValid(){ return _children == null; }

        public void invalidate(){ _children = null; children = null; }

        public TodoElement getElement() { return e; }

        private void addChild(Vertex v) {
            this._children.add(v);
        }

        private void removeChild(Vertex v) {
            this._children.remove(v);
        }

        private void sort(Comparator<Vertex> c) {
            this._children.sort(c);
        }

        @Override
        public int compareTo(Vertex o) {
            return o.e.creationDate.compareTo(e.creationDate);
        }
    }

    public Graph() {
        this.graph = new HashMap<>();

        Category c = new Category();
        c.setName("Main");

        this.rootVertex = graph.addCategory(c);
    }

    public Graph(Graph graph, Category rootCategory) {
        if(!graph.contains(rootCategory))
            throw new IllegalArgumentException("root category must be in the given graph");

        this.graph = graph;
        this.rootCategory = rootCategory;
    }

    private void validateVertex(Vertex v) {
        if(!graph.containsKey(v))
            throw new IllegalArgumentException("Vertex does not exist in graph");
    }

    public Vertex addTask(Task t) {
        Vertex v = new Vertex(t);
        graph.add(v);
        return v;
    }

    public Vertex addCategory(Category c) {
        Vertex v = new Vertex(c);
        graph.add(v);
        return v;
    }

    public void removeVertex(Vertex v) {

        validateVertex(v);

        v.invalidate();

        graph.remove(v);
    }

    // true - can get to end from start
    // false - not ^^
    private boolean DFS(Vertex v1, Vertex v2) {

        if(v1.equals(v2))
            return true;

        for(Vertex v : v1.children)
            DFS(v, v2);

        return false;
    }

    private void checkForCycles(Vertex v1, Vertex v2) throws GraphCycleException {
        if(DFS(v2, v1))
            throw new GraphCycleException();
    }

    public void addEdge(Vertex v1, Vertex v2) throws GraphCycleException {

        validateVertex(v1);
        validateVertex(v2);

        checkForCycles(v1, v2);

        v1.addChild(v2);

    }

    public void removeEdge(Vertex v1, Vertex v2) {

        validateVertex(v1);
        validateVertex(v2);

        v1.removeChild(v2);
    }

    public void sort(Comparator<Vertex> c) {
        graph.sort(c);
        for(Vertex v : graph) {
            v.sort(c);
        }
    }

    public void sortVertex(Vertex v, Comparator<Vertex> c) {
        v.sort(c);
    }

    public static class GraphCycleException extends Exception {  }

}
