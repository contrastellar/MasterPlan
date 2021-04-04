package ui.account.login.toolbar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import ui.account.login.toolbar.toolbarTab.ToolbarTab;

/**
 * Toolbar Controller
 */
public class ToolbarController {
    @FXML private VBox tabBar;          // TabBar instance
    @FXML private ToolbarTab homeTab;          // HomeTab instance
    @FXML private ToolbarTab workspaceTab;     // WorkspaceTab instance
    @FXML private ToolbarTab settingsTab;      // SettingsTab instance

    private final String INIT_ACTIVE = "LOGIN"; // Initial active tab name

    /**
     * Gets initial active tab name
     * @return active tab name ("HOME", "SETTINGS", etc.)
     */
    public String getINIT_ACTIVE() { return INIT_ACTIVE; }

    /**
     * Initializes initial active tab
     */
    @FXML
    private void initialize() {
        tabBar.getChildren().forEach(e -> {
            if (((ToolbarTab) e).getText().equals(INIT_ACTIVE))
                ((ToolbarTab) e).setActive();
        });
    }

    /**
     * Removes active styling from all tabs then re-adds active styling to clicked tab
     * @param e Event of clicked Tab Button
     */
    public void setActive(ActionEvent e) {
        setInactive();
        ((Button) e.getTarget()).getStyleClass().add("active");
        ((ToolbarTab) ((Button) e.getTarget()).getParent()).setIconColor("black");
    }

    /**
     * Removes active class and resets icon color from all tabs in the tabBar
     */
    public void setInactive() {
        tabBar.getChildren().forEach(e -> {
            ((ToolbarTab) e).removeActive();
            ((ToolbarTab) e).resetIconColor();
        });
    }
}
