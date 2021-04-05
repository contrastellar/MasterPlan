package ui.taskboard;

import components.Category;
import components.TodoElement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import ui.taskboard.listview.ListView;
import util.graph.ObservableVertex;

import java.io.IOException;

/**
 * Taskview custom component
 */
public class ListSpaceView extends SplitPane {

    private final ListView listView;

    private final ObservableVertex<TodoElement> rootCategory;

    /**
     * Constructs Taskview component with loader
     */
    public ListSpaceView(ObservableVertex<TodoElement> rootCategory) {

        if(rootCategory == null)
            throw new IllegalArgumentException("ListSpaceView() - rootCategory can not be null");

        if(!(rootCategory.getElement() instanceof Category))
            throw new IllegalArgumentException("ListSpaceView() - rootCategory.getElement() must be of type Category");

        this.rootCategory = rootCategory;

        loadFXML();

        listView = new ListView();
        listView.setRootVertex(rootCategory);

        getItems().add(0, listView);
    }

    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListSpaceView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
