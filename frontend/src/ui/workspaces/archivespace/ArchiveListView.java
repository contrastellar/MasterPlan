package ui.workspaces.archivespace;

import components.Category;
import components.TodoElement;
import components.task.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import models.MainModel;
import observable.ObservableManager;
import ui.util.Viewable;
import ui.workspaces.archivespace.category.ArchiveCategoryView;
import ui.workspaces.archivespace.task.ArchiveTaskView;
import util.graph.ObservableGraphChange;
import util.graph.ObservableVertex;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ArchiveListView extends VBox implements Viewable {

    private final ObservableManager observableManager = new ObservableManager();

    public final Map<ObservableVertex<TodoElement>, Viewable> vertexToViewable = new HashMap<>();

    private final Map<ObservableVertex<TodoElement>, ArchiveVertexCallback> vertexToCallBack = new HashMap<>();

    public ArchiveListView() {
        loadFXML();
    }

    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ArchiveListView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void initialize() {
        observableManager.addListener(MainModel.model.obsGraph, this::onGraphChange);
    }

    private void onGraphChange(ObservableGraphChange<TodoElement> change) {

        for(var v : change.getAddedVertices()) {
            var callBack = new ArchiveVertexCallback(v, this::onArchiveCallBack);
            callBack.registerListeners();
            vertexToCallBack.put(v, callBack);
        }

        for(var v : change.getRemovedVertices()) {
            var callBack = vertexToCallBack.remove(v);
            callBack.unregisterListeners();
            removeVertex(v);
        }

        if(change.getSorted()) {
            sort(change.getSortingComparator());
        }

    }

    private void onArchiveCallBack(ArchiveVertexCallback.ChangeInfo change) {
        if(change.isArchived)
            addVertex(change.getVertex());
        else
            removeVertex(change.getVertex());
    }

    private void addVertex(ObservableVertex<TodoElement> vertex) {
        Viewable viewable;

        if (vertex.getElement() instanceof Task) {
            viewable = new ArchiveTaskView(vertex);
        }
        else if (vertex.getElement() instanceof Category) {
            viewable = new ArchiveCategoryView(vertex);
        }
        else
            throw new UnsupportedOperationException("change.getVertex().getElement() is not of type Task or Category");

        viewable.registerListeners();
        vertexToViewable.put(vertex, viewable);
        this.getChildren().add(viewable.node());
    }

    private void removeVertex(ObservableVertex<TodoElement> vertex) {
        Viewable viewable = vertexToViewable.get(vertex);

        if(viewable != null) {
            viewable.unregisterListeners();
            vertexToViewable.remove(vertex);
            this.getChildren().remove(viewable.node());
        }
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
