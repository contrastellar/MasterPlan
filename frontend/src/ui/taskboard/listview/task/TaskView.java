package ui.taskboard.listview.task;

import components.TodoElement;
import components.observable.IReadOnlyObservable;
import components.observable.Observable;

import components.task.Task;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import ui.taskboard.listview.ListView;
import util.graph.ObservableVertex;
import util.graph.ObservableVertexChange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskView extends GridPane {

    @FXML
    private Button toggleBtn;
    @FXML
    private HBox toggleContainer;
    @FXML
    private HBox remainingContainer;
    @FXML
    private HBox buttonContainer;

    @FXML
    private Label taskName;

    @FXML
    private CheckBox completedCheckBox;

    @FXML
    private Label tasksRemainingLabel;
    private static final String tasksRemainingFormat = "%d remaining";

    @FXML
    private ListView listView;

    @FXML
    private Button removeVertexBtn, removeGraphBtn;

    private final Observable<ObservableVertex<TodoElement>> _rootTask = new Observable<>();
    public final IReadOnlyObservable<ObservableVertex<TodoElement>> rootTask = _rootTask;
    private ObservableVertex<TodoElement> taskVertex;

    /**
     * Constructs Category component with loader
     */
    public TaskView() {
        loadFXML();
    }

    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TaskView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void initialize() {

        _rootTask.startListen(this::onRootTaskChange);

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

    private void onRootTaskChange(ObservableVertex<TodoElement> rootTask) {

        if(rootTask == null) {
            taskName.setText("No Task");
            tasksRemainingLabel.setText("N/A");
            completedCheckBox.selectedProperty().setValue(false);
            return;
        }

        rootTask.startListen(this::onRemainingTasksChange);
        rootTask.getElement().name.startListen(this::onTaskNameChange);
        Task task = (Task) rootTask.getElement();
        task.isCompleted.startListen(this::onTaskCompletedChange);
        completedCheckBox.selectedProperty().addListener(this::onCheckBox_click);

        listView.setRootVertex(rootTask);
    }

    private void onCheckBox_click(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
        if(_rootTask.getValue() == null)
            return;

        Task task = (Task) _rootTask.getValue().getElement();
        task.setCompleted(newVal);

    }

    private void onTaskCompletedChange(boolean completed) {
        if(completed) {
            taskName.setTextFill(Color.GREEN);
        }
        else {
            taskName.setTextFill(Color.BLACK);
        }
    }

    @FXML
    private void onRemoveVertexBtn_click(ActionEvent e) {
        if(_rootTask.getValue() == null)
            return;

    }

    @FXML
    private void onRemoveGraphBtn_click(ActionEvent e) {
        if(_rootTask.getValue() == null)
            return;

    }

    private void onTaskNameChange(String name) {
        taskName.setText(name);
    }

    private void onRemainingTasksChange(ObservableVertexChange<TodoElement> change) {
        List<ObservableVertex<TodoElement>> remainingVertices =
                _rootTask.getValue().getGraph().query((element) -> element instanceof Task, _rootTask.getValue());
        tasksRemainingLabel.setText(String.format(tasksRemainingFormat, remainingVertices.size()));

        boolean hasChildren = false;
        for (var vertex : _rootTask.getValue().getGraph().getOutVertices(_rootTask.getValue()))
            hasChildren = true;
        if (!hasChildren) toggleBtn.setVisible(false);
        else toggleBtn.setVisible(true);
    }

    public void setRootTask(ObservableVertex<TodoElement> rootTask) {
        if(!(rootTask.getElement() instanceof Task))
            throw new IllegalArgumentException("TaskView() - rootVertex.getElement() is not of type Task");

        _rootTask.setValue(rootTask);
    }

    public ObservableVertex<TodoElement> getRootTask() {
        return _rootTask.getValue();
    }

}
