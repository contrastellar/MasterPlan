package UI;

import java.io.IOException;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController { //View-Model (Contains UI Logic)
    @FXML private Text actionTarget; //Inhereted from the JXML file that this is a 'controller' for
    @FXML private Button loginButton; //Inhereted from the JXML file that this is a 'controller' for

    @FXML protected void handleSubmitButtonAction (ActionEvent event) throws NullPointerException, IOException {
        Stage stage;
        Scene root = null;
        actionTarget.setText("Signing you in. Please wait.");
        ConsoleDebug.alert("Transition started.");
        NavManager manager = MainApp.navManager;
        manager.changeScene("/UI/view/MainView.fxml", loginButton);
        System.out.println("Transition should've occured.");
    }
}
