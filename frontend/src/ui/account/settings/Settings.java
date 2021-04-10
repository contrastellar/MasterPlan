package ui.account.settings;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import ui.navigation.ParentInfo;

import java.io.IOException;

public class Settings {

    @FXML
    private Pane container;

    @FXML
    private ImagePattern userImage;

    @FXML
    private Label username;


    @FXML
    private void userAccountClicked(MouseEvent mouseEvent) {
        container.getChildren().clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("./login/LoginView.fxml"));

        Parent parent;
        try {
             parent = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        container.getChildren().add(parent);
    }

}
