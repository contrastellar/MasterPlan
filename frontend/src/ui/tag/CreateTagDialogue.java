package ui.tag;

import components.Tag;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CreateTagDialogue {
    
    public static Tag showAndWait() {

        Stage stage = new Stage();

        Parent parent;

        try {
            parent = FXMLLoader.load(Objects.requireNonNull(CreateTagDialogue.class.getResource("CreateTagView.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(parent);

        stage.setScene(scene);

        stage.showAndWait();

        throw new UnsupportedOperationException("not implemented yet");
    }
    
}
