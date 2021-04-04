package ui.account.login;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import ui.account.login.create.CreationView;
import ui.account.login.toolbar.ToolbarController;
import ui.account.login.toolbar.toolbarTab.ToolbarTab;

public class LoginViewController {
    @FXML
    private BorderPane mainContainer;                 // Main container instance
    @FXML private BorderPane toolbar;                       // Toolbar instance
    @FXML private ToolbarController toolbarController;      // Toolbar controller instance

    private String currentView = "";                        // Current center view as String

    /**
     * Initializes Tab handlers to manage view
     */
    @FXML public void initialize() {
        // Initialize start active tab
        updateCenter(toolbarController.getINIT_ACTIVE());
        currentView = toolbarController.getINIT_ACTIVE();

        // Set event handlers for tabs to update center view
        ((VBox) toolbar.getCenter()).getChildren().forEach(tab -> {
            ((ToolbarTab) tab).setOnAction(e -> {
                toolbarController.setInactive();
                ((ToolbarTab) tab).setActive();
                updateCenter(((ToolbarTab) tab).getText());
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
            case "LOGIN":
                mainContainer.setCenter(new LoginView()); //TODO implement and change this so 'new Login() is called
                break;
            case "CREATE":
                mainContainer.setCenter(new CreationView()); //TODO implement and change this so 'new Create() is called

        }
        System.out.printf("%s\n", activeTab);
        currentView = activeTab;
    }
}
