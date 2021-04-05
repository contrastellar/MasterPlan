package ui.taskboard.listview.task;

import components.TodoElement;
import components.observable.IReadOnlyObservable;
import components.observable.Observable;
import components.task.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import ui.taskboard.listview.ListViewContainer;
import util.graph.ObservableVertex;
import util.graph.ObservableVertexChange;

import java.io.IOException;
import java.util.List;

public class TaskView extends GridPane {

    @FXML
    private Label taskName;

    @FXML
    private Label tasksRemainingLabel;
    private static final String tasksRemainingPattern = "%d remaining";

    @FXML
    private final ListViewContainer listViewContainer;

    private final Observable<ObservableVertex<TodoElement>> _rootTask = new Observable<>();
    public final IReadOnlyObservable<ObservableVertex<TodoElement>> rootTask = _rootTask;

    /**
     * Constructs Category component with loader
     */
    public TaskView() {

        loadFXML();

        _rootTask.startListen(this::onRootTaskChange);

        listViewContainer = new ListViewContainer();

        GridPane.setColumnIndex(listViewContainer, 1);
        GridPane.setColumnSpan(listViewContainer, 2);
        GridPane.setRowIndex(listViewContainer, 2);

        getChildren().add(listViewContainer);
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
            return;
        }

        listViewContainer.setRootVertex(rootTask);
        rootTask.startListen(this::onTaskRemainingTasksChange);
        rootTask.getElement().name.startListen(this::onTaskNameChange);
    }


    private void onTaskNameChange(String name) {
        taskName.setText(name);
    }

    private void onTaskRemainingTasksChange(ObservableVertexChange<TodoElement> change) {
        List<ObservableVertex<TodoElement>> remainingVertices =
                _rootTask.getValue().getGraph().query((element) -> element instanceof Task, _rootTask.getValue());

        tasksRemainingLabel.setText(String.format(tasksRemainingPattern, remainingVertices.size()));
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
