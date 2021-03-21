package MVVM;

import components.TodoElement;
import util.graph.Graph;
import util.graph.IGraph;

import java.util.Comparator;
import java.util.HashSet;

// TODO
public class ObservableGraph implements IObservable, IGraph {
    private final IGraph graph;
    private final HashSet<IListener> listeners = new HashSet<>();


    public ObservableGraph(IGraph graph) {
        this.graph = graph;
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /* IObservable methods */
    @Override
    public void addListener(IListener listener) {
        listeners.add(listener);
        listener.onChange();
    }

    @Override
    public void removeListener(IListener listener) {
        listeners.remove(listener);
    }

    
    private void updateListeners() {
        for(IListener listener : listeners)
            listener.onChange();
    }

    /* IGraphWriteOnly methods */
    @Override
    public Graph.Vertex addVertex(TodoElement e) {
        Graph.Vertex v = graph.addVertex(e);
        updateListeners();
        return v;
    }

    @Override
    public void removeVertex(Graph.Vertex v) {
        graph.removeVertex(v);
        updateListeners();
    }

    @Override
    public void addDirectedEdge(Graph.Vertex from, Graph.Vertex to) {
        graph.addDirectedEdge(from, to);
        updateListeners();
    }

    @Override
    public void removeDirectedEdge(Graph.Vertex v1, Graph.Vertex v2) {
        graph.removeDirectedEdge(v1, v2);
        updateListeners();
    }

    @Override
    public void sort(Comparator<Graph.Vertex> c) {
        graph.sort(c);
        updateListeners();
    }

    /* IGraphReadOnly methods */
    @Override
    public Iterable<Graph.Vertex> getVertices() {
        return graph.getVertices();
    }

    @Override
    public Graph.Vertex getRootVertex() {
        return graph.getRootVertex();
    }

}
