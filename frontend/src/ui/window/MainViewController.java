package ui.window;

import components.Category;
import components.TodoElement;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import ui.taskboard.ListSpaceView;
import ui.window.toolbar.ToolbarController;
import ui.window.toolbar.tab.Tab;
import util.graph.Graph;
import util.graph.ObservableGraph;
import util.graph.ObservableVertex;

/**
 * MainView Controller
 */
public class MainViewController {
    @FXML private BorderPane mainContainer;                 // Main container instance
    @FXML private BorderPane toolbar;                       // Toolbar instance
    @FXML private ToolbarController toolbarController;      // Toolbar controller instance

    private String currentView = "";                        // Current center view as String

    private final ObservableGraph<TodoElement> obsGraph = new ObservableGraph<>(new Graph<>());
    private final ObservableVertex<Category> rootVertex = obsGraph.addVertex(new Category("Main"));


    /**
     * Initializes Tab handlers to manage view
     */
    @FXML public void initialize() {
        // Initialize start active tab
        updateCenter(toolbarController.getINIT_ACTIVE());
        currentView = toolbarController.getINIT_ACTIVE();

        // Set event handlers for tabs to update center view
        ((VBox) toolbar.getCenter()).getChildren().forEach(tab -> {
            ((Tab) tab).setOnAction(e -> {
                toolbarController.setInactive();
                ((Tab) tab).setActive();
                updateCenter(((Tab) tab).getText());
            });
        });
    }

    /**
     * Sets center view based on activeTab text
     * @param activeTab Button text from active Tab
     */
    private void updateCenter(String activeTab) {
        // Prevents clicking on active tab
        if (currentView.equals(activeTab))
            return;

        // Switch center view based on active tab name
        switch (activeTab) {
            case "HOME":
                mainContainer.setCenter(new ListSpaceView(rootVertex));
                System.out.println("HOME");
                break;
            case "SETTINGS":
                mainContainer.setCenter(new VBox());
                break;
            case "WORKSPACE":
                mainContainer.setCenter(new VBox());
        }
        currentView = activeTab;
    }
}
