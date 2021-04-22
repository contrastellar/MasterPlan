package ui.workspaces;

import components.TodoElement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import models.MainModel;
import observable.ObservableManager;
import ui.util.Viewable;
import ui.workspaces.editbar.EditBarContainer;
import ui.workspaces.listspace.ListSpaceView;
import util.graph.ObservableVertex;

import java.io.IOException;

/**
 *
 */
public class WorkSpaceView extends SplitPane implements Viewable {

    @FXML
    private ListSpaceView listSpaceView;

    @FXML
    private EditBarContainer editBarContainer;

    private final ObservableManager observableManager = new ObservableManager();

    public WorkSpaceView() {
        loadFXML();
    }


    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WorkSpaceView.fxml"));
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
        observableManager.addListener(MainModel.model.editVertex, this::onEditVertexChange);
    }

    private void onEditVertexChange(ObservableVertex<TodoElement> vertex) {
        if(vertex == null) {
            editBarContainer.unregisterListeners();
            getItems().remove(editBarContainer);
            System.out.println("vertex null");
        }
        else{
            System.out.println(vertex.getElement().getName());
            editBarContainer.registerListeners();
            getItems().add(editBarContainer);
        }
    }

    /**
     *
     */
    public void showArchive() {

    }

    @Override
    public Node node() {
        return this;
    }

    @Override
    public void registerListeners() {
        observableManager.startListen();
        editBarContainer.registerListeners();
        listSpaceView.registerListeners();
    }

    @Override
    public void unregisterListeners() {
        observableManager.stopListen();
        editBarContainer.unregisterListeners();
        listSpaceView.unregisterListeners();
    }
}
