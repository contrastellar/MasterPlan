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

    @FXML
    private ListViewHeader listViewHeader;

    @FXML
    private ListView listView;

    private final MainModel mainModel;

    public ListSpaceView(MainModel mainModel) {
        this.mainModel = mainModel;
        loadFXML();
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
    private void initialize() {
        mainModel.selectedVertex.startListen(this::onRootVertexChange);
    }

    private void onRootVertexChange(ObservableVertex<TodoElement> rootVertex) {
        if(!(rootVertex.getElement() instanceof Category))
            throw new IllegalArgumentException("ListView() - rootVertex is not of type Category");

        listViewHeader.setRootCategory(rootVertex);
        listView.setRootVertex(rootVertex);
    }

}
