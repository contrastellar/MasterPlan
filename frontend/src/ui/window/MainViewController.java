package ui.window;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import ui.custom.Viewable;
import ui.taskboard.WorkSpaceView;

/**
 * MainView Controller
 */
public class MainViewController {

    @FXML
    private BorderPane mainContainer;

    private final WorkSpaceView workSpaceView = new WorkSpaceView();

    private Viewable viewable = null;

    /**
     * Initializes Tab handlers to manage view
     */
    @FXML
    public void initialize() {
        switchViewToListSpace();
    }

    @FXML
    private void switchViewToListSpace() {
        workSpaceView.registerListeners();
        mainContainer.setCenter(workSpaceView);
    }

    @FXML
    private void switchViewToSettings() {
        switchViewable(null);
    }

    @FXML
    private void switchViewToWorkspace() {
        switchViewable(null);
    }

    @FXML
    private void switchViewToArchive() {
        switchViewable(null);
    }

    private void switchViewable(Viewable viewable) {
        if (this.viewable != null)
            this.viewable.unregisterListeners();

        if (viewable != null) {
            viewable.registerListeners();
            mainContainer.setCenter(viewable.node());
        }
        else
            mainContainer.setCenter(null);

        this.viewable = viewable;
        //mainContainer.setCenter(workSpaceView);
    }

}
