package ui.taskboard.listview.category;

import components.Category;
import components.TodoElement;
import components.observable.IReadOnlyObservable;
import components.observable.Observable;
import components.task.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import ui.taskboard.listview.ListView;
import util.graph.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryView extends GridPane {

    @FXML
    private ListView listView;

    @FXML
    private Button toggleBtn;

    @FXML
    private HBox buttonContainer;

    @FXML
    private Label categoryName;

    @FXML
    private Button removeVertexBtn, removeGraphBtn;

    @FXML
    private Label tasksRemainingLabel;
    private static final String tasksRemainingPattern = "%d remaining";

    private final Observable<ObservableVertex<TodoElement>> _categoryVertex = new Observable<>();
    public final IReadOnlyObservable<ObservableVertex<TodoElement>> categoryVertex = _categoryVertex;


    public CategoryView() {
        loadFXML();
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

    @FXML
    private void initialize() {

        _categoryVertex.startListen(this::onCategoryVertexChange);

        if (listView.isTodoEmpty())
            toggleBtn.setVisible(false);

        // Set styling for hover
        List<Node> gridChildren = new ArrayList<>(getChildren());
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

    /**
     * handler for Toggling todos and rotating btn
     * @param e mouse event
     */
    public void toggleBtnHandler(ActionEvent e) {
        if (listView.isTodoEmpty()) return;

        listView.toggleTodo();

        double angle = toggleBtn.getRotate();
        if (angle == 0)
            toggleBtn.setRotate(270);
        else
            toggleBtn.setRotate(0);
    }

    private void onCategoryVertexChange(ObservableVertex<TodoElement> categoryVertex) {
        if(categoryVertex == null) {
            categoryName.setText("No Category");
            tasksRemainingLabel.setText("N/A");
            return;
        }

        listView.setRootVertex(categoryVertex);

        categoryVertex.startListen(this::onTaskRemainingTasksChange);
        categoryVertex.getElement().name.startListen(this::onCategoryNameChange);
    }

    private void onTaskRemainingTasksChange(ObservableVertexChange<TodoElement> change) {
        int remainingTasks = 0;
        boolean hasChildren = false;

        for (var vertex : _categoryVertex.getValue().getGraph().getOutVertices(_categoryVertex.getValue())) {
            hasChildren = true;
            if (vertex.getElement() instanceof Task)
                remainingTasks++;
        }

        if (!hasChildren) toggleBtn.setVisible(false);
        // TODO: Set remove visability
        else toggleBtn.setVisible(true);
        tasksRemainingLabel.setText(String.format(tasksRemainingPattern, remainingTasks));
    }

    @FXML
    private void onRemoveVertexBtn_click(ActionEvent e) {
        if(_categoryVertex.getValue() == null)
            return;
        System.out.println("Hi");
    }

    @FXML
    private void onRemoveGraphBtn_click(ActionEvent e) {
        if(_categoryVertex.getValue() == null)
            return;
        System.out.println("Hi");
    }


    private void onCategoryNameChange(String name) {
        categoryName.setText(name);
    }

    public void setCategory(ObservableVertex<TodoElement> categoryVertex) {
        if(!(categoryVertex.getElement() instanceof Category))
            throw new IllegalArgumentException("CategoryView() - rootVertex.getElement() is not of type Category");

        _categoryVertex.setValue(categoryVertex);
    }

    public ObservableVertex<TodoElement> getCategory() {
        return _categoryVertex.getValue();
    }

}
