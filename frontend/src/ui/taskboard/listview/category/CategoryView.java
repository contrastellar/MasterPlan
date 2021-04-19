package ui.taskboard.listview.category;

import components.Category;
import components.TodoElement;
import components.observable.IReadOnlyObservable;
import components.observable.Observable;
import components.task.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import ui.custom.icon.Icon;
import ui.taskboard.listview.ListView;
import util.graph.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryView extends GridPane {

    private final ListView listView;

    @FXML
    private Icon toggleBtn;
    @FXML
    private HBox toggleContainer;
    @FXML
    private HBox remainingContainer;
    @FXML
    private HBox buttonContainer;

    @FXML
    private Label categoryName;

    @FXML
    private Label tasksRemainingLabel;
    private static final String tasksRemainingPattern = "%d remaining";

    private final Observable<ObservableVertex<TodoElement>> _rootCategory = new Observable<>();
    public final IReadOnlyObservable<ObservableVertex<TodoElement>> rootCategory = _rootCategory;


    public CategoryView() {

        loadFXML();

        listView = new ListView();
        GridPane.setColumnIndex(listView, 1);
        GridPane.setColumnSpan(listView, 2);
        GridPane.setRowIndex(listView, 2);
        getChildren().add(listView);

        _rootCategory.startListen(this::onRootCategoryChange);

        // Sets toggleBtn clicked handler
        toggleBtn.setOnMouseClicked(this::toggleBtnHandler);
        if (listView.isTodoEmpty())
            toggleBtn.setVisible(false);

        // Set styling for hover
        List<Node> gridChildren = new ArrayList();
        getChildren().forEach(e -> gridChildren.add(e));
        gridChildren.remove(listView);
        gridChildren.forEach(e -> {
            e.setOnMouseEntered(event -> {
                buttonContainer.setStyle("-fx-border-color: cadetblue;");
            });
            e.setOnMouseExited(event -> {
                buttonContainer.setStyle("-fx-border-color: transparent;");
            });
        });
    }

    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CategoryView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * handler for Toggling todos and rotating btn
     * @param e mouse event
     */
    public void toggleBtnHandler(MouseEvent e) {
        if (listView.isTodoEmpty()) return;

        listView.toggleTodo();

        double angle = toggleBtn.getRotate();
        if (angle == 0)
            toggleBtn.setRotate(270);
        else
            toggleBtn.setRotate(0);
    }

    private void onRootCategoryChange(ObservableVertex<TodoElement> rootCategory) {
        if(rootCategory == null) {
            categoryName.setText("No Category");
            tasksRemainingLabel.setText("N/A");
            return;
        }

        listView.setRootVertex(rootCategory);

        rootCategory.startListen(this::onTaskRemainingTasksChange);
        rootCategory.getElement().name.startListen(this::onCategoryNameChange);
    }

    private void onTaskRemainingTasksChange(ObservableVertexChange<TodoElement> change) {

        int remainingTasks = 0;
        boolean hasChildren = false;

        for (var vertex : _rootCategory.getValue().getGraph().getOutVertices(_rootCategory.getValue())) {
            hasChildren = true;
            if (vertex.getElement() instanceof Task)
                remainingTasks++;

            }

        if (!hasChildren) toggleBtn.setVisible(false);
        else toggleBtn.setVisible(true);

        tasksRemainingLabel.setText(String.format(tasksRemainingPattern, remainingTasks));

    }

    private void onCategoryNameChange(String name) {
        categoryName.setText(name);
    }

    public void setRootCategory(ObservableVertex<TodoElement> rootCategory) {
        if(!(rootCategory.getElement() instanceof Category))
            throw new IllegalArgumentException("CategoryView() - rootVertex.getElement() is not of type Category");

        _rootCategory.setValue(rootCategory);
    }

    public ObservableVertex<TodoElement> getRootCategory() {
        return _rootCategory.getValue();
    }

}
