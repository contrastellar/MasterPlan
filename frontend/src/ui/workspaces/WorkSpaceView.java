package ui.workspaces;

import components.Category;
import components.TodoElement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import models.MainModel;
import observable.ObservableManager;
import ui.util.Viewable;
import ui.workspaces.categorybar.CategoryListView;
import ui.workspaces.editbar.EditBarContainer;
import ui.workspaces.listspace.ListSpaceView;
import util.graph.ObservableGraphChange;
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

    @FXML
    private CategoryListView categoryListView;

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
        observableManager.addListener(MainModel.model.obsGraph, this::onCategoryChange);
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
            if(!getItems().contains(editBarContainer))
                getItems().add(editBarContainer);
        }
    }

    private void onCategoryChange(ObservableGraphChange<TodoElement> change) {
        if (categoryListView.getRootVertex() ==null )
            return;
        else if(!(categoryListView.getRootVertex().getElement() instanceof Category))
            return;

            categoryListView.setRootVertex( categoryListView.getRootVertex());
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
        categoryListView.registerListeners();
    }

    @Override
    public void unregisterListeners() {
        observableManager.stopListen();
        editBarContainer.unregisterListeners();
        listSpaceView.unregisterListeners();
        categoryListView.unregisterListeners();
    }
}
