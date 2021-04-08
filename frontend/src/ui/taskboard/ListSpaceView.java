package ui.taskboard;

import components.Category;
import components.TodoElement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import models.MainModel;
import ui.taskboard.listview.ListView;
import util.graph.ObservableVertex;

import java.io.IOException;

/**
 * Taskview custom component
 */
public class ListSpaceView extends SplitPane {

    public final ListView listView;

    private final MainModel mainModel;

    /**
     * Constructs Taskview component with loader
     */
    public ListSpaceView(MainModel mainModel) {
        this.mainModel = mainModel;
        mainModel.selectedVertex.startListen(this::onSelectedRootChange);

        loadFXML();

        listView = new ListView(mainModel);

        getItems().add(0, listView);
    }

    private void onSelectedRootChange(ObservableVertex<TodoElement> selectedRoot) {
        if(selectedRoot == null)
            throw new IllegalArgumentException("ListSpaceView() - rootCategory can not be null");

        if(!(selectedRoot.getElement() instanceof Category))
            throw new IllegalArgumentException("ListSpaceView() - rootCategory.getElement() must be of type Category");
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
