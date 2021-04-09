package ui.taskboard.listview;

import components.Category;
import components.TodoElement;
import components.task.Task;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import models.MainModel;

import util.graph.ObservableVertex;

import java.io.IOException;

public class ListSpaceView extends VBox {

    private final ListViewHeader listViewHeader;
    private final ListView listView;

    private final MainModel mainModel;

    public ListSpaceView(MainModel mainModel) {
        this.mainModel = mainModel;

        loadFXML();

        // TODO put these in fxml
        listViewHeader = new ListViewHeader();
        listView = new ListView();

        listView.setId("listContainer");

        getChildren().addAll(listViewHeader, listView);
        mainModel.selectedVertex.startListen(this::onRootVertexChange);

    }

    @FXML
    private void initialize() {

    }

    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListSpaceView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void addTask() {
        Task task = new Task("Task");
        mainModel.obsGraph.addVertex(task, mainModel.selectedVertex.getValue());
    }

    @FXML
    private void addCategory() {
        Category category = new Category("Category");
        mainModel.obsGraph.addVertex(category, mainModel.selectedVertex.getValue());
    }

    private void onRootVertexChange(ObservableVertex<TodoElement> rootVertex) {
        if(!(rootVertex.getElement() instanceof Category))
            throw new IllegalArgumentException("ListView() - rootVertex is not of type Category");
        listViewHeader.setRootCategory(rootVertex);
        listView.setRootVertex(rootVertex);
    }

}
