package ui.window.toolbar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import ui.window.toolbar.tab.Tab;

/**
 * Toolbar Controller
 */
public class ToolbarController {
    @FXML private VBox tabBar;          // TabBar instance
    @FXML private Tab homeTab;          // HomeTab instance
    @FXML private Tab workspaceTab;     // WorkspaceTab instance
    @FXML private Tab settingsTab;      // SettingsTab instance



    /**
     * Initializes initial active tab
     */
    @FXML
    private void initialize() {
    }


}
