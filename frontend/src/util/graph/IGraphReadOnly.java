package util.graph;

import components.TodoElement;

import java.util.Comparator;

public interface IGraphReadOnly {

    public Iterable<Graph.Vertex> getVertices();

    public Graph.Vertex getRootVertex();

}
