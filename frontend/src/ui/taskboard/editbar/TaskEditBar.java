package ui.taskboard.editbar;


import components.TodoElement;
import components.observable.IReadOnlyObservable;
import components.observable.Observable;
import components.observable.ObservableManager;
import components.task.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import util.graph.ObservableVertex;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class TaskEditBar extends VBox implements IEditBar {

    public final Observable<ObservableVertex<TodoElement>> _taskVertex = new Observable<>();
    private final IReadOnlyObservable<ObservableVertex<TodoElement>> taskVertex = _taskVertex;

    private final ObservableManager observableManager = new ObservableManager();


    // DESCRIPTION inputs
    @FXML private TextField titleInput;
    @FXML private TextArea description;

    // DATE inputs
    @FXML private Label createdDateInput;
    @FXML private DatePicker dueDateInput;

    // STATUS
    @FXML private Label statusLabel;
    private static final String STATUS_LABEL_PATTERN = "Status: %s";


    public TaskEditBar() {
        loadFXML();
    }

    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TaskEditBar.fxml"));
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
        observableManager.addListener(_taskVertex, this::onTaskChange);
    }

    @FXML
    private void onConfirmChangesBtn_click(ActionEvent ae) {
        if(taskVertex.getValue() == null)
            return;

        Task task = (Task) this.taskVertex.getValue().getElement();

        task.setName(titleInput.getText());
        task.setDescription(description.getText());


        LocalDate date = dueDateInput.getValue();
        if (date != null) task.setDueDate(toCalendar(date));

    }

    private void onTaskChange(ObservableVertex<TodoElement> taskVertex) {

        if(taskVertex == null) {
            titleInput.setText("");
            description.setText("");
            createdDateInput.setText("");
            statusLabel.setText("");
        }
        else {
            Task task = (Task) taskVertex.getElement();

            titleInput.setText(task.getName());

            description.setText(task.getDescription());

            LocalDate date = dueDateInput.getValue();
            if (date != null) task.setDueDate(toCalendar(date));

            createdDateInput.setText(task.creationDate.toString());

            String status = task.isCompleted() ? String.format(STATUS_LABEL_PATTERN, "true") : String.format(STATUS_LABEL_PATTERN, "false");
            statusLabel.setText(status);


        }
    }

    public static Calendar toCalendar(LocalDate localDate) {
        Date date = localToDate(localDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static Date localToDate(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
          .atZone(ZoneId.systemDefault())
          .toInstant());
    }


    public void setEditVertex(ObservableVertex<TodoElement> taskVertex) {
        if(taskVertex != null && !(taskVertex.getElement() instanceof Task))
            throw new IllegalArgumentException("EditBar.onTaskChange() - taskVertex.getElement() must be of type Task");

        this._taskVertex.setValue(taskVertex);
    }


    public Node node() {
        return this;
    }

    public void registerListeners() {
        observableManager.startListen();
    }

    public void unregisterListeners() {

        observableManager.stopListen();

    }

}
