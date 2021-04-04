package ui.account.login.create;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;

import java.io.IOException;

public class CreationView extends SplitPane {

    public CreationView(){
        // Set fxml root and controller to this instance
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UsrCreation.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        // Attempt to load resource
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
