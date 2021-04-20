package ui.window;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import models.MainModel;
import ui.taskboard.WorkSpaceView;

/**
 * MainView Controller
 */
public class MainViewController {
    @FXML private BorderPane mainContainer;

    private final MainModel mainModel = new MainModel();

    private WorkSpaceView workSpaceView = new WorkSpaceView(mainModel);

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
        mainContainer.setCenter(workSpaceView.node());
    }

    @FXML
    private void switchViewToSettings() {
        mainContainer.setCenter(new VBox());
    }

    @FXML
    private void switchViewToWorkspace() {
        mainContainer.setCenter(new VBox());
    }

    @FXML
    private void switchViewToArchive() {
        mainContainer.setCenter(new VBox());
    }

}
