package ui.taskboard.listview.category;

import components.Category;
import components.TodoElement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import ui.taskboard.listview.ListViewContainer;
import util.graph.*;
import java.io.IOException;

public class CategoryView extends GridPane {
    @FXML
    private final ListViewContainer subContainer;

    /**
     * Constructs Category component with loader
     * @param rootVertex
     */
    public CategoryView(ObservableVertex<Category> rootVertex) {
        // Set fxml root and controller to this instance
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CategoryView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        // Attempt to load resource
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        // assume we are given ObservableVertex<Category> rootVertex;
        rootVertex.startListen(this::updateCategoryContainer);
        subContainer = new ListViewContainer();
    }
    // pass in observable cateogry vertex. then register a listener called update category, that listens to
    // the vertex. when the vertex updates, we need to update our containers.

    @FXML
    private void initialize() {

    }

    private void updateCategoryContainer(ObservableVertexChange<? extends TodoElement> change) {

        for(var vertex : change.getAddedEdges()) {
            subContainer.addVertex(vertex);
        }

        for(var vertex : change.getRemovedEdges()) {
            subContainer.removeVertex(vertex);
        }

        if(change.getSorted()) {
            subContainer.sort(change.getSortingComparator());
        }

    }

}
