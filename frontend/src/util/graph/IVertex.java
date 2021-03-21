package util.graph;

import components.TodoElement;

public interface IVertex {

    public Iterable<Graph.Vertex> getChildren();
    public TodoElement getElement();

}
