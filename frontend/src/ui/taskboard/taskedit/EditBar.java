package ui.taskboard.taskedit;


import components.observable.Observable;
import components.task.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.MainModel;

public class EditBar {
    private final MainModel mainModel;
    private final Observable<Task> task = new Observable();

    // DESCRIPTION inputs
    @FXML private TextField titleInput;
    @FXML private TextField authorInput;
    @FXML private TextArea notesInput;

    // WORKSPACE input
    @FXML private ChoiceBox workspaceInput;

    // DATE inputs
    @FXML private DatePicker createdDateInput;
    @FXML private DatePicker dueDateInput;

    // STATUS
    @FXML private Label statusLabel;

    // TAGs input
    @FXML private TextArea tagsInput;

    public EditBar(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    @FXML
    private void onConfirmChangesBtn_click() {
        if(task == null)
            return;



    }

    private void onTaskChange(Task task) {

        if(task == null) {
            // TODO: set edit bar ui to defaults/blank
        }
        else{
            // TODO: update edit bar ui with task data
        }

    }

}
