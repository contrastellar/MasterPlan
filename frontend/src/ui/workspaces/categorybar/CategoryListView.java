package ui.workspaces.categorybar;

import components.Category;
import components.TodoElement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import observable.IReadOnlyObservable;
import observable.Observable;
import observable.ObservableManager;
import ui.util.Viewable;
import ui.workspaces.listspace.category.CategoryView;
import util.graph.ObservableGraphChange;
import util.graph.ObservableVertex;
import util.graph.ObservableVertexChange;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class CategoryListView extends VBox implements Viewable {

    @FXML
    private VBox todoContainer;



    private final Observable<ObservableVertex<TodoElement>> _rootVertex = new Observable<>();
    public final IReadOnlyObservable<ObservableVertex<TodoElement>> rootVertex = _rootVertex;

    public final Map<ObservableVertex<TodoElement>, Viewable> vertexToViewable = new HashMap<>();

    private final ObservableManager observableManager = new ObservableManager();


    public CategoryListView() {
        loadFXML();
    }


    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CategoryListView.fxml"));
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
        observableManager.addListener(_rootVertex, this::onRootVertexChange);

        // Binds managed and visible property to hide and reset the layout by just setting
        todoContainer.managedProperty().bindBidirectional(todoContainer.visibleProperty());
    }

    /** Getters and Setters for the visibility of the todoContainer **/
    public void hideTodo() { todoContainer.setVisible(false); };
    public void showTodo() { todoContainer.setVisible(true); };
    public boolean isTodoShown() { return todoContainer.isVisible(); }
    public boolean isTodoHidden() { return !isTodoShown(); }
    public void toggleTodo() {
        if (isTodoShown()) hideTodo();
        else showTodo();
    }

    public boolean isTodoEmpty() {
        return todoContainer.getChildren().isEmpty();
    }

    public void setRootVertex(ObservableVertex<TodoElement> rootVertex) {
        _rootVertex.setValue(rootVertex);
    }

    public ObservableVertex<TodoElement> getRootVertex() {
        return _rootVertex.getValue();
    }

    private void onRootVertexChange(ObservableVertex<TodoElement> rootVertex) {

        vertexToViewable.clear();
        todoContainer.getChildren().clear();

        if(rootVertex == null)
            return;
        // memory leak - doesn't call stopListen on previous rootVertex

        observableManager.addListener(_rootVertex.getValue(), this::onRootVertexOutEdgesChange);
        observableManager.addListener(_rootVertex.getValue().getGraph(), this::onRootVertexRemoval);
    }

    private void onRootVertexOutEdgesChange(ObservableVertexChange<TodoElement> change) {
        for(var vertex : change.getAddedEdges()) {
            addVertex(vertex);
        }
        for(var vertex : change.getRemovedEdges()) {
            removeView(vertex);
        }
        if(change.getSorted()) {
            sort(change.getSortingComparator());
        }
    }

    private void onRootVertexRemoval(ObservableGraphChange<TodoElement> change) {
        if(change.getRemovedVertices().contains(_rootVertex.getValue()))
            return;

        for(var vertex : change.getRemovedVertices())
            if(vertexToViewable.containsKey(vertex))
                removeView(vertex);
    }

    public void addVertex(ObservableVertex<TodoElement> vertex) {
        Viewable viewable;
        if(vertex.getElement() instanceof Category) {
            CategoryView cView = new CategoryView();
            cView.setCategory(vertex);
            cView.registerListeners();
            viewable = cView;
        } else
            throw new UnsupportedOperationException("not implemented");

        todoContainer.getChildren().add(viewable.node());
        vertexToViewable.put(vertex, viewable);
    }

    private void removeView(ObservableVertex<TodoElement> vertex) {
        Viewable viewable = vertexToViewable.get(vertex);
        viewable.unregisterListeners();
        todoContainer.getChildren().remove(viewable.node());
        vertexToViewable.remove(vertex);
    }


    private void sort(Comparator<TodoElement> c) {
        // TODO: Implement
        throw new UnsupportedOperationException("not yet implemented");
    }


    @Override
    public Node node() {
        return this;
    }


    @Override
    public void registerListeners() {
        observableManager.startListen();
    }

    @Override
    public void unregisterListeners() {
        observableManager.stopListen();
        for(var viewable : vertexToViewable.values())
            viewable.unregisterListeners();
        vertexToViewable.clear();
    }
}