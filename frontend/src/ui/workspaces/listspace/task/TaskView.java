package ui.workspaces.listspace.task;

import components.TodoElement;
import observable.IReadOnlyObservable;
import observable.Observable;
import observable.ObservableManager;
import components.task.Task;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import models.MainModel;
import ui.util.Viewable;
import ui.workspaces.archivespace.Archiver;
import ui.workspaces.listspace.ListView;
import util.graph.ObservableVertex;
import util.graph.ObservableVertexChange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskView extends GridPane implements Viewable {


    @FXML
    private Button archiveButton;

    @FXML
    private Button toggleBtn;

    @FXML
    private HBox buttonContainer;

    @FXML
    private HBox remainingContainer;

    @FXML
    private Label taskName;

    @FXML
    private CheckBox completedCheckBox;

    @FXML
    private Label tasksRemainingLabel;
    private static final String tasksRemainingFormat = "%d remaining";

    @FXML
    private ListView listView;

    private final Observable<ObservableVertex<TodoElement>> _rootTask = new Observable<>();
    public final IReadOnlyObservable<ObservableVertex<TodoElement>> rootTask = _rootTask;

    private ObservableVertex<TodoElement> taskVertex;
    private final ObservableManager observableManager = new ObservableManager();

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



        observableManager.addListener(_rootTask, this::onRootTaskChange);

        setOnMouseClicked((e) -> {
            e.consume();
            MainModel.model.editVertex.setValue(_rootTask.getValue());
        });


        if (listView.isTodoEmpty())
            toggleBtn.setVisible(false);

        // Set styling for hover
        List<Node> gridChildren = new ArrayList<>(getChildren());
        gridChildren.remove(listView);
        gridChildren.forEach(e -> {
            e.setOnMouseEntered(event -> {
                remainingContainer.setStyle("-fx-border-color: cadetblue;");
                buttonContainer.setStyle("-fx-border-color: cadetblue;");
            });
            e.setOnMouseExited(event -> {
                remainingContainer.setStyle("-fx-border-color: transparent;");
                buttonContainer.setStyle("-fx-border-color: transparent;");
            });
        });
    }

    /**
     * handler for Toggling todos and rotating btn
     * @param e mouse event
     */
    public void toggleBtnHandler(ActionEvent e) {
        listView.toggleTodo();

        if(listView.isTodoShown())
            toggleBtn.setRotate(0);
        else
            toggleBtn.setRotate(270);
    }

    private void onRootTaskChange(ObservableVertex<TodoElement> rootTask) {

        if(rootTask == null) {
            taskName.setText("No Task");
            tasksRemainingLabel.setText("N/A");
            completedCheckBox.selectedProperty().setValue(false);
            return;
        }

        observableManager.addListener(_rootTask.getValue(), this::onRemainingTasksChange);
        observableManager.addListener(_rootTask.getValue().getElement().name, this::onTaskNameChange);
        observableManager.addListener(((Task) _rootTask.getValue().getElement()).isCompleted, this::onTaskCompletedChange);
        observableManager.addListener(((Task) _rootTask.getValue().getElement()).isBookmarked, this::onBookMarkChange);
        completedCheckBox.selectedProperty().addListener(this::onTaskCompleted_click);
        listView.setRootVertex(rootTask);

    }

    @FXML
    private void onBookmark_click(ActionEvent e)  {
        if(_rootTask.getValue() == null)
            return;
        Task task = ((Task) _rootTask.getValue().getElement());
        task.setBookmark(!task.isBookmarked()); // toggles bookmark bool
    }

    private void onBookMarkChange(boolean bookmarked) {
        if(bookmarked) { // set the Syt
            taskName.setId("NameBookmarked");
        }
        else {
            taskName.setId("Name"); // a css stile sheet, label node has two styleclasses
        }

    }

    @FXML
    private void onArchive_click(ActionEvent e){
        if(_rootTask.getValue() == null)
            return;

        Task task = ((Task) _rootTask.getValue().getElement()); //Pulls the current task that is invoking this method
        boolean curArchive = task.isArchived(); //Pulls the 'isArchived'
        task.setArchive(!curArchive); //Sets the current task's archive boolean
        System.out.println("Setting archive on " + task.getName() + " to " + task.isArchived.getValue());

        this.managedProperty().bindBidirectional(this.visibleProperty());
        this.setVisible(curArchive); //Hides the task at hand
        callToAdd();

        //We create a new value
        Archiver newElement = new Archiver(this.getRootTask(), task);
        MainModel.model.archiverList.add(newElement);
        System.out.println(MainModel.model.archiverList.contains(newElement));
    }

    private void callToAdd(){

    }

    private void onTaskCompleted_click(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
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

        _rootTask.getValue().getGraph().removeVertex(_rootTask.getValue());

        System.out.println("Removing vertex. Graph size: " + _rootTask.getValue().getGraph().getVertices().size());
    }

    @FXML
    private void onRemoveGraphBtn_click(ActionEvent e) {
        if(_rootTask.getValue() == null)
            return;

        _rootTask.getValue().getGraph().removeVertexReachable(_rootTask.getValue());

        System.out.println("Removing vertex. Graph size: " + _rootTask.getValue().getGraph().getVertices().size());
    }


    private void onTaskNameChange(String name) {
        taskName.setText(name);
    }

    private void onRemainingTasksChange(ObservableVertexChange<TodoElement> change) {

        List<ObservableVertex<TodoElement>> numTaskQueryRes = _rootTask.getValue().getGraph().query(
                (e) -> e instanceof Task,
                _rootTask.getValue()
        );

        tasksRemainingLabel.setText(String.format(tasksRemainingFormat, numTaskQueryRes.size()));

        int totalElements = _rootTask.getValue().getGraph().getOutDegree(_rootTask.getValue());

        toggleBtn.setVisible(totalElements > 0);
    }

    public void setRootTask(ObservableVertex<TodoElement> rootTask) {
        if(!(rootTask.getElement() instanceof Task))
            throw new IllegalArgumentException("TaskView() - rootVertex.getElement() is not of type Task");

        _rootTask.setValue(rootTask);
    }

    public ObservableVertex<TodoElement> getRootTask() {
        return _rootTask.getValue();
    }

    @Override
    public Node node() {
        return this;
    }

    @Override
    public void registerListeners() {
        observableManager.startListen();
        listView.registerListeners();
    }

    @Override
    public void unregisterListeners() {
        observableManager.stopListen();
        listView.unregisterListeners();
    }
}
