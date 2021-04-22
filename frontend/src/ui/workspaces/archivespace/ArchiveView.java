package ui.workspaces.archivespace;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import ui.util.Viewable;
import ui.workspaces.listspace.ListSpaceView;

import java.io.IOException;

/**
 * This class is 'created' every time its opened.
 * This is done to ensure that archived tasks are 'loaded'.
 * Doing this, allows us to save memory on the graph being used.
 * This class is very similar to WorkSpaceView
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

    }

    /**
     * This will unlisten(?)
     */
    @Override
    public void unregisterListeners() {

    }
}
