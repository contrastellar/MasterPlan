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

    private final String INIT_ACTIVE = "HOME"; // Initial active tab name

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
            if (((Tab) e).getText().equals(INIT_ACTIVE))
                ((Tab) e).setActive();
        });
    }

    /**
     * Removes active styling from all tabs then re-adds active styling to clicked tab
     * @param e Event of clicked Tab Button
     */
    public void setActive(ActionEvent e) {
        setInactive();
        ((Button) e.getTarget()).getStyleClass().add("active");
        ((Tab) ((Button) e.getTarget()).getParent()).setIconColor("black");
    }

    /**
     * Removes active class and resets icon color from all tabs in the tabBar
     */
    public void setInactive() {
        tabBar.getChildren().forEach(e -> {
            ((Tab) e).removeActive();
            ((Tab) e).resetIconColor();
        });
    }
}
