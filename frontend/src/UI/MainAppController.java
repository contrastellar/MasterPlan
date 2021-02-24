package UI;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainAppController {
    @FXML private Text actiontarget;
    @FXML private Button loginButton;

    @FXML protected void handleSubmitButtonAction (ActionEvent event) throws InterruptedException, IOException {
        Stage stage;
        Scene root = null;
        actiontarget.setText("Signing you in. Please wait.");
        //Transition between scenes.
        ConsoleDebug.debug("Scene transition in 2s");
        Thread.sleep(2000);
        ConsoleDebug.alert("Transition started.");
        stage = (Stage) loginButton.getScene().getWindow();
        try {
            root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        }catch(IOException e){
            ConsoleDebug.alert("BAD CLASS.");
            System.exit(-2);
        }
        stage.setScene(root);
        stage.show();
    }
}
