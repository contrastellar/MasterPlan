package ui.taskboard;

import components.Category;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import ui.taskboard.listview.ListView;
import util.graph.ObservableVertex;

import java.io.IOException;

/**
 * Taskview custom component
 */
public class ListSpaceView extends SplitPane {


    private final ObservableVertex<Category> rootCategory;

    /**
     * Constructs Taskview component with loader
     */
    public ListSpaceView(ObservableVertex<Category> rootCategory) {
        this.rootCategory = rootCategory;

        // Set fxml root and controller to this instance
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListSpaceView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        // Attempt to load resource
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void initialize() {
        getChildren().add(0, new ListView(rootCategory));
    }
}
