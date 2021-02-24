package UI;

import java.util.concurrent.TimeUnit;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainAppController {
    @FXML private Text actiontarget;

    @FXML protected void handleSubmitButtonAction (ActionEvent event) {
        Stage stage;
        Parent root;
        actiontarget.setText("Signing you in. Please wait.");


    }
}
