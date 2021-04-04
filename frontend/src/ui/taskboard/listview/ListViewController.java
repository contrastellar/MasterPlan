package ui.taskboard.listview;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import ui.taskboard.listview.category.Category;

public class ListViewController {
    @FXML VBox taskList;

    @FXML
    private void initialize() {
        ((Category) taskList.getChildren().get(0)).addSubTodo(new Category());
        ((Category) taskList.getChildren().get(0)).addSubTodo(new Category());
        ((Category) taskList.getChildren().get(0)).addSubTodo(new Category());
        ((Category) taskList.getChildren().get(0)).addSubTodo(new Category());
        ((Category) taskList.getChildren().get(0)).addSubTodo(new Category());

    }
}
