package util.graph;

import java.util.HashMap;
import java.util.HashSet;

import components.Completable;
import components.TodoElement;

// TODO: pretty much everything
public class Graph<T extends TodoElement & Completable, C extends TodoElement> {

    private final HashMap<TodoElement, DependencyExpression<T> > graph;

    public Graph(C root) {
        this.graph = new HashMap<>();
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private void validateVertex(TodoElement v) {
        if(!graph.containsKey(v))
            throw new IllegalArgumentException("Vertex does not exist in graph");
    }

    public void addCompletableVertex(T t) {
        validateVertex(t);

    }

    public void addVertex(C c) {
        validateVertex(c);

    }

    private void checkForCycles(TodoElement v1, TodoElement v2) {
        // if cycle, throw custom exception
    }

    public void addLogicalDependencyToVertex(DependencyExpression<T> expr, TodoElement vertex) {

        // TODO: check for cycles

    }

}
