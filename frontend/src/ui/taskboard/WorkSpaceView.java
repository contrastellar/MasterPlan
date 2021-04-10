package ui.taskboard;

import components.Category;
import components.TodoElement;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import models.MainModel;
import ui.taskboard.listview.ListSpaceView;
import util.graph.ObservableVertex;

import java.io.IOException;

/**
 * Taskview custom component
 */
public class WorkSpaceView extends SplitPane {

    public final ListSpaceView listSpaceView;

    private final MainModel mainModel;

    /**
     * Constructs Taskview component with loader
     */
    public WorkSpaceView(MainModel mainModel) {
        this.mainModel = mainModel;
        mainModel.selectedVertex.startListen(this::onSelectedRootChange);

        loadFXML();

        listSpaceView = new ListSpaceView(mainModel);

        getItems().add(0, listSpaceView);
    }

    private void onSelectedRootChange(ObservableVertex<TodoElement> selectedRoot) {
        if(selectedRoot == null)
            throw new IllegalArgumentException("ListSpaceView() - rootCategory can not be null");

        if(!(selectedRoot.getElement() instanceof Category))
            throw new IllegalArgumentException("ListSpaceView() - rootCategory.getElement() must be of type Category");
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

}
