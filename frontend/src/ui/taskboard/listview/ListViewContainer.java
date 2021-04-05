package ui.taskboard.listview;

import components.Category;
import components.TodoElement;
import components.task.Task;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import ui.taskboard.listview.Task.TaskView;
import ui.taskboard.listview.category.CategoryView;
import util.graph.IVertex;
import util.graph.ObservableVertex;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ListViewContainer extends VBox {

    private final Map<IVertex<? extends TodoElement>, Node> vertexToNode = new HashMap<>();


    public ListViewContainer() { }


    public void addVertex(ObservableVertex<? extends TodoElement> vertex) {

        Node node = null;

        if(vertex.getElement() instanceof Category)
            node = new CategoryView((ObservableVertex<Category>) vertex);
        else if(vertex.getElement() instanceof Task)
            node = new TaskView((ObservableVertex<Task>) vertex);
        else
            throw new UnsupportedOperationException("not implemented");

        getChildren().add(node);
        vertexToNode.put(vertex, node);
    }

    public void removeVertex(ObservableVertex<? extends TodoElement> vertex) {
        Node node = vertexToNode.get(vertex);

        if(node == null)
            throw new IllegalArgumentException("ListViewContainer.removeVertex() - vertex does not exist in this ListViewContainer");

        getChildren().remove(node);
        vertexToNode.remove(vertex);
    }

    public void sort(Comparator<? extends TodoElement> c) {
        // TODO: Implement
        throw new UnsupportedOperationException("not yet implemented");
    }
}
