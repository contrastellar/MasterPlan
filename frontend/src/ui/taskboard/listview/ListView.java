package ui.taskboard.listview;

import components.Category;
import components.TodoElement;
import components.observable.IReadOnlyObservable;
import components.observable.Observable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import models.MainModel;
import util.graph.ObservableVertex;

import java.io.IOException;

public class ListView extends VBox {

    private final ListViewHeader listViewHeader;
    private final ListViewContainer listViewContainer;

    public Button addTaskBtn;
    public Button addCatBtn;

    private final MainModel mainModel;

    public ListView(MainModel mainModel) {
        this.mainModel = mainModel;

        loadFXML();

        listViewHeader = new ListViewHeader();
        listViewContainer = new ListViewContainer();

        listViewContainer.setId("listContainer");

        getChildren().addAll(listViewHeader, listViewContainer);
        mainModel.selectedVertex.startListen(this::onRootVertexChange);

    }

    @FXML
    private void initialize() {

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

    private void onRootVertexChange(ObservableVertex<TodoElement> rootVertex) {
        if(!(rootVertex.getElement() instanceof Category))
            throw new IllegalArgumentException("ListView() - rootVertex is not of type Category");
        listViewHeader.setRootCategory(rootVertex);
        listViewContainer.setRootVertex(rootVertex);
    }

}
