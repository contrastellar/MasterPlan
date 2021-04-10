package ui.taskboard.listview;

import components.Category;
import components.TodoElement;
import components.observable.IReadOnlyObservable;
import components.observable.Observable;
import components.task.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import ui.taskboard.listview.category.CategoryView;
import ui.taskboard.listview.task.TaskView;
import util.graph.IVertex;
import util.graph.ObservableVertex;
import util.graph.ObservableVertexChange;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ListView extends VBox {

    @FXML
    private VBox todoContainer;

    @FXML
    private Button addTaskBtn;

    @FXML
    private Button addCategoryBtn;

    private final Observable<ObservableVertex<TodoElement>> _rootVertex = new Observable<>();
    public final IReadOnlyObservable<ObservableVertex<TodoElement>> rootVertex = _rootVertex;

    private final Map<IVertex<TodoElement>, Node> vertexToNode = new HashMap<>();

    public ListView() {
        loadFXML();
    }

    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void initialize() {
        _rootVertex.startListen(this::onRootVertexChange);

        addTaskBtn.setOnAction(this::addTaskBtn_click);
        addCategoryBtn.setOnAction(this::addCategoryBtn_click);
    }

    private void addTaskBtn_click(ActionEvent e) {
        if(_rootVertex.getValue() == null)
            return;

        Task t = new Task("new task");
        rootVertex.getValue().getGraph().addVertex(t, rootVertex.getValue());
    }

    private void addCategoryBtn_click(ActionEvent e) {
        if(_rootVertex.getValue() == null)
            return;

        Category c = new Category("new category");
        rootVertex.getValue().getGraph().addVertex(c, rootVertex.getValue());
    }

    public void setRootVertex(ObservableVertex<TodoElement> rootVertex) {
        _rootVertex.setValue(rootVertex);
    }

    public ObservableVertex<TodoElement> getRootVertex() {
        return _rootVertex.getValue();
    }

    private void onRootVertexChange(ObservableVertex<TodoElement> rootVertex) {
        vertexToNode.clear();
        todoContainer.getChildren().clear();

        if(rootVertex == null)
            return;

        // memory leak - doesn't call stopListen on previous rootVertex

        rootVertex.startListen(this::onRootVertexOutEdgesChange);
    }

    private void onRootVertexOutEdgesChange(ObservableVertexChange<TodoElement> change) {

        for(var vertex : change.getAddedEdges()) {
            addVertex(vertex);
        }

        for(var vertex : change.getRemovedEdges()) {
            removeVertex(vertex);
        }

        if(change.getSorted()) {
            sort(change.getSortingComparator());
        }
    }

    private void addVertex(ObservableVertex<TodoElement> vertex) {

        Node node;

        if(vertex.getElement() instanceof Category) {
            CategoryView cView = new CategoryView();
            cView.setRootCategory(vertex);
            node = cView;
        }
        else if(vertex.getElement() instanceof Task) {
            TaskView tView = new TaskView();
            tView.setRootTask(vertex);
            node = tView;
        } else
            throw new UnsupportedOperationException("not implemented");

        todoContainer.getChildren().add(node);
        vertexToNode.put(vertex, node);
    }

    private void removeVertex(ObservableVertex<TodoElement> vertex) {
        Node node = vertexToNode.get(vertex);

        if(node == null)
            throw new IllegalArgumentException("ListViewContainer.removeVertex() - vertex does not exist in this ListViewContainer");

        todoContainer.getChildren().remove(node);
        vertexToNode.remove(vertex);
    }

    private void sort(Comparator<TodoElement> c) {
        // TODO: Implement
        throw new UnsupportedOperationException("not yet implemented");
    }


    private void addTask() {

    }

    @FXML
    private void addCategory() {

    }
}
