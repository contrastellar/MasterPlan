package ui.tag;

import components.Tag;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CreateTagDialogue {

    public static final String DIALOGUE_TITLE = "Add Tag";

    public static Tag showAndWait() {

        Stage stage = new Stage();

        CreateTagView createTagView = new CreateTagView();

        Scene scene = new Scene(createTagView);

        stage.setScene(scene);
        stage.setTitle(DIALOGUE_TITLE);
        stage.initModality(Modality.NONE);

        stage.showAndWait();

        return createTagView.getCreatedTag();
    }
    
}
