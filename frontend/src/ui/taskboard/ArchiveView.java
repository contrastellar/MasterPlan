package ui.taskboard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import ui.custom.Viewable;
import ui.taskboard.listview.ListSpaceView;

import java.io.IOException;

/**
 * @Author Gabby S.
 */
public class ArchiveView extends SplitPane implements Viewable {

    @FXML
    private ListSpaceView listSpaceView;

    public ArchiveView() {
        loadFXML();
    }

    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ArchiveView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Node node() {
        return this;
    }

    /**
     * This will point to our ListView
     */
    @Override
    public void registerListeners() {
        //TODO registerListeners
    }

    /**
     * This will unlisten(?)
     */
    @Override
    public void unregisterListeners() {
        //TODO unregListeners
    }
}
