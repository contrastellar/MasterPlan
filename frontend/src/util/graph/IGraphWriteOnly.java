package util.graph;

import components.TodoElement;

import java.util.Comparator;

public interface IGraphWriteOnly {

    public Graph.Vertex addVertex(TodoElement e);

    public void removeVertex(Graph.Vertex v);

    public void addDirectedEdge(Graph.Vertex from, Graph.Vertex to);

    public void removeDirectedEdge(Graph.Vertex v1, Graph.Vertex v2);

    public void sort(Comparator<Graph.Vertex> c);

}
