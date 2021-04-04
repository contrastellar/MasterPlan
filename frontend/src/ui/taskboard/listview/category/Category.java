package ui.taskboard.listview.category;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Category extends GridPane {
    @FXML GridPane catContainer;    // Category top-level grid
    @FXML VBox subContainer;        // Subcategory/Subtask container

    /**
     * Constructs Category component with loader
     */
    public Category() {
        // Set fxml root and controller to this instance
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Category.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        // Attempt to load resource
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void initialize() {

    }

    public void addSubTodo(Node n) {
        subContainer.getChildren().add(n);
    }
}