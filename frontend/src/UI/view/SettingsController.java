package UI.view;

import UI.ConsoleDebug;
import UI.MainApp;
import UI.NavManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SettingsController {
    @FXML private Button BackArrow; //Inherited from the .JXML file that this is a 'controller' for
    @FXML public Button transButton;

    @FXML protected void BackPage(){
        ConsoleDebug.debug("Backing up");
        NavManager manager = MainApp.navManager;
        manager.backScene();
        System.out.println("Transition should've occurred.");
    }
}
