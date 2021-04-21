package ui.taskboard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import ui.custom.Viewable;
import ui.taskboard.editbar.EditBarContainer;
import ui.taskboard.listview.ListSpaceView;

import java.io.IOException;

/**
 *
 */
public class WorkSpaceView extends SplitPane implements Viewable {

    @FXML
    private ListSpaceView listSpaceView;

    @FXML
    private EditBarContainer editBarContainer;


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
    }

    /**
     *
     */
    public void showArchive(){

    }

    @Override
    public Node node() {
        return this;
    }

    @Override
    public void registerListeners() {
        editBarContainer.registerListeners();
        listSpaceView.registerListeners();
    }

    @Override
    public void unregisterListeners() {
        editBarContainer.unregisterListeners();
        listSpaceView.unregisterListeners();
    }
}
