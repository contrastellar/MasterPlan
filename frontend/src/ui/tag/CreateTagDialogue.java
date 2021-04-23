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

    public static final double MIN_WIDTH = 250;
    public static final double MIN_HEIGHT = 300;

    public static Tag showAndWait() {

        Stage stage = new Stage();

        CreateTagView createTagView = new CreateTagView(stage);

        Scene scene = new Scene(createTagView);

        stage.setScene(scene);
        stage.setTitle(DIALOGUE_TITLE);
        stage.initModality(Modality.NONE);

        createTagView.registerListeners();

        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setWidth(MIN_WIDTH);
        stage.setHeight(MIN_HEIGHT);

        stage.showAndWait();

        createTagView.unregisterListeners();

        return createTagView.getCreatedTag();
    }
    
}
