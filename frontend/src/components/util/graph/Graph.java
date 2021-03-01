package components.util.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.SortedSet;

import components.Completable;
import components.Vertex;

// TODO: pretty much everything
public class Graph<T extends Vertex & Completable, C extends Vertex> {

    // TODO: find a better way
    private interface IBooleanExpression {
        boolean getTruthValue();
    }

    private class Operand implements IBooleanExpression{
        private T t;
        private boolean NOT;

        public Operand() { NOT = false; }
        public Operand(boolean NOT) {
            this.NOT = NOT;
        }

        @Override
        public boolean getTruthValue() {
            return NOT != t.isCompleted();
        }
    }

    public class OrSet implements IBooleanExpression {
        HashSet<Operand> operands;


        @Override
        public boolean getTruthValue() {
            // TODO
            return false;
        }
    }

    public class AndSet implements IBooleanExpression {
        HashSet<HashSet<OrSet>> operands;

        @Override
        public boolean getTruthValue() {
            // TODO
            return false;
        }
    }

    // TODO: implement SortedSet
    private final HashMap<Vertex, HashSet<HashSet<Operand>>> graph;

    public Graph() {
        this.graph = new HashMap<>();
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private boolean evaluateDependencies( HashSet<HashSet<Operand>> dependencies) {
        // TODO
        return false;
    }

    private void validateVertex(Vertex v) {
        if(!graph.containsKey(v))
            throw new IllegalArgumentException("Vertex does not exist in graph");
    }

    public void addCompletableVertex(T t) {
        validateVertex(t);

    }

    public void addVertex(C c) {
        validateVertex(c);

    }

    private void checkForCycles(Vertex v1, Vertex v2) {
        // if cycle, throw custom exception
    }

    public void addExpression(Vertex v1) {


    }

}
