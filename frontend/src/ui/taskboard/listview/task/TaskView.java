package ui.taskboard.listview.task;

import components.TodoElement;
import components.observable.IReadOnlyObservable;
import components.observable.Observable;
import components.task.Task;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import ui.taskboard.listview.ListView;
import util.graph.ObservableVertex;
import util.graph.ObservableVertexChange;

import java.io.IOException;
import java.util.List;

public class TaskView extends GridPane {

    @FXML
    private Label taskName;

    @FXML
    private CheckBox completedCheckBox;

    @FXML
    private Label tasksRemainingLabel;
    private static final String tasksRemainingFormat = "%d remaining";

    @FXML
    private final ListView listView;

    private final Observable<ObservableVertex<TodoElement>> _rootTask = new Observable<>();
    public final IReadOnlyObservable<ObservableVertex<TodoElement>> rootTask = _rootTask;

    /**
     * Constructs Category component with loader
     */
    public TaskView() {

        loadFXML();

        listView = new ListView();

        GridPane.setColumnIndex(listView, 1);
        GridPane.setColumnSpan(listView, 2);
        GridPane.setRowIndex(listView, 2);

        getChildren().add(listView);

        _rootTask.startListen(this::onRootTaskChange);
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

    private void onRootTaskChange(ObservableVertex<TodoElement> rootTask) {

        if(rootTask == null) {
            taskName.setText("No Task");
            tasksRemainingLabel.setText("N/A");
            completedCheckBox.selectedProperty().setValue(false);
            return;
        }

        rootTask.startListen(this::onTaskRemainingTasksChange);
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
            taskName.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
            taskName.setTextFill(Color.GREEN);
        }
        else {
            taskName.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
            taskName.setTextFill(Color.BLACK);
        }
    }

    private void onTaskNameChange(String name) {
        taskName.setText(name);
    }

    private void onTaskRemainingTasksChange(ObservableVertexChange<TodoElement> change) {

        List<ObservableVertex<TodoElement>> remainingVertices =
                _rootTask.getValue().getGraph().query((element) -> element instanceof Task, _rootTask.getValue());
        tasksRemainingLabel.setText(String.format(tasksRemainingFormat, remainingVertices.size()));
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
