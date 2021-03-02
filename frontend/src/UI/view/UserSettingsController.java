package UI.view;

import UI.ConsoleDebug;
import UI.MainApp;
import UI.NavManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class UserSettingsController {
    @FXML public Button transButton;

    @FXML protected void BackPage(){
        ConsoleDebug.debug("Backing up");
        NavManager manager = MainApp.navManager;
        manager.backScene(transButton);
        System.out.println("Transition should've occurred.");
    }
}
