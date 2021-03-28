package UI;

import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

public class LoginController { //View-Model (Contains UI Logic)
    @FXML private Text actionTarget; //Inherited from the .JXML file that this is a 'controller' for
    @FXML private Button loginButton; //Inherited from the .JXML file that this is a 'controller' for

    private NavManager navManager;

    public LoginController() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(SceneType.LOGIN_SCENE.getFilename()));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);


    }

    @FXML protected void handleSubmitButtonAction() throws NullPointerException {
        actionTarget.setText("Signing you in. Please wait.");
        ConsoleDebug.alert("Transition started.");
        System.out.println("Transition should've occurred.");
    }
}
