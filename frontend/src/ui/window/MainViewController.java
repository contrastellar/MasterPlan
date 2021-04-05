package ui.window;

import components.task.Task;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import models.MainModel;
import ui.taskboard.ListSpaceView;

/**
 * MainView Controller
 */
public class MainViewController {
    @FXML private BorderPane mainContainer;

    private final MainModel mainModel = new MainModel();

    ListSpaceView listSpaceView = new ListSpaceView(mainModel.obsRootVertex);

    /**
     * Initializes Tab handlers to manage view
     */
    @FXML
    public void initialize() {
        switchViewToListSpace();
    }

    @FXML
    private void switchViewToListSpace() {
        mainContainer.setCenter(listSpaceView);
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
