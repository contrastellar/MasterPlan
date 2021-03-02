package UI.view;

import UI.ConsoleDebug;
import UI.MainApp;
import UI.NavManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainViewController {
    @FXML public Button transButton; //Inherited from the .JXML file that this is a 'controller' for

    @FXML protected void UserSettingsOpen() {
        ConsoleDebug.alert("Transition to User Settings started.");
        NavManager manager = MainApp.navManager;
        manager.changeScene("/UI/view/UserSettings.fxml");
        System.out.println("Transition should've occurred.");
    }

    @FXML protected void SettingsOpen() {
        ConsoleDebug.alert("Transition to Settings started.");
        NavManager manager = MainApp.navManager;
        manager.changeScene("/UI/view/SettingsView.fxml");
        System.out.println("Transition should've occurred.");
    }

    @FXML public void newTaskWindowOpen(){

    }
}
