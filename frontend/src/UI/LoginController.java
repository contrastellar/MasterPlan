package UI;

import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class LoginController { //View-Model (Contains UI Logic)
    @FXML private Text actionTarget; //Inherited from the .JXML file that this is a 'controller' for
    @FXML private Button loginButton; //Inherited from the .JXML file that this is a 'controller' for

    @FXML protected void handleSubmitButtonAction() throws NullPointerException {
        actionTarget.setText("Signing you in. Please wait.");
        ConsoleDebug.alert("Transition started.");
        NavManager manager = MainApp.navManager;
        manager.changeScene("/UI/view/MainView.fxml", loginButton);
        System.out.println("Transition should've occurred.");
    }
}
