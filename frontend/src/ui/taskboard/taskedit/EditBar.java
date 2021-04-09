package ui.taskboard.taskedit;


import components.Category;
import components.TodoElement;
import components.observable.IReadOnlyObservable;
import components.observable.Observable;
import components.task.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.MainModel;
import util.graph.IVertex;
import util.graph.ObservableVertex;

import java.util.List;

public class EditBar {

    private final MainModel mainModel;
    public final Observable<ObservableVertex<TodoElement>> _taskVertex = new Observable<>();
    private final IReadOnlyObservable<ObservableVertex<TodoElement>> taskVertex = _taskVertex;

    // DESCRIPTION inputs
    @FXML private TextField titleInput;
    @FXML private TextField authorInput;
    @FXML private TextArea notesInput;

    // WORKSPACE input
    @FXML private ChoiceBox<String> workspaceInput;

    // DATE inputs
    @FXML private DatePicker createdDateInput;
    @FXML private DatePicker dueDateInput;

    // STATUS
    @FXML private Label statusLabel;
    private static final String STATUS_LABEL_PATTERN = "Status: %s";

    // TAGs input
    @FXML private TextArea tagsInput;

    public EditBar(MainModel mainModel) {
        this.mainModel = mainModel;
        taskVertex.startListen(this::onTaskChange);
    }

    @FXML
    private void onConfirmChangesBtn_click() {
        if(taskVertex.getValue() == null)
            return;

        Task task = (Task) this.taskVertex.getValue().getElement();

        task.setName(titleInput.getText());
        task.setAuthor(authorInput.getText());
        task.setNotes(notesInput.getText());
        // TODO: to set the workspace, we need the vertex corresponding to the task
        // TODO: we don't set createDate...
        // TODO: set due date
        // TODO: retrieve from tagsInput


    }

    private void onTaskChange(ObservableVertex<TodoElement> taskVertex) {

        if(taskVertex == null) {
            titleInput.setText("");
            authorInput.setText("");
            notesInput.setText("");
            workspaceInput.getItems().clear();
            // TODO: set createdDateInput
            // TODO: set dueDateInput
            statusLabel.setText("");
            tagsInput.setText("");
        }
        else {


            Task task = (Task) taskVertex.getElement();

            titleInput.setText(task.getNotes());

            authorInput.setText(task.getAuthor());

            notesInput.setText(task.getNotes());

            List<ObservableVertex<TodoElement>> workspaces = mainModel.obsGraph.query((element) -> {
                return element instanceof Category;
            });

            workspaceInput.getItems().clear();
            for(var workspace : workspaces) {
                workspaceInput.getItems().add(workspace.getElement().name.getValue());
            }

            // TODO: set createdDateInput
            // TODO: set dueDateInput

            String status = task.isCompleted() ? String.format(STATUS_LABEL_PATTERN, "true") : String.format(STATUS_LABEL_PATTERN, "false");
            statusLabel.setText(status);

            // TODO: set tagsInput (tagsInput shouldn't be a text area)
        }
    }

    public void setTaskVertex(ObservableVertex<TodoElement> taskVertex) {
        if(taskVertex != null && !(taskVertex.getElement() instanceof Task))
            throw new IllegalArgumentException("EditBar.onTaskChange() - taskVertex.getElement() must be of type Task");

        this._taskVertex.setValue(taskVertex);
    }

}
