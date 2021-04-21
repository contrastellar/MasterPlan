package ui.taskboard.editbar;

import components.Category;
import components.TodoElement;
import components.observable.IReadOnlyObservable;
import components.observable.Observable;
import components.observable.ObservableManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import util.graph.ObservableVertex;

import java.io.IOException;

public class CategoryEditBar extends VBox implements IEditBar {

    public final Observable<ObservableVertex<TodoElement>> _categoryVertex = new Observable<>();
    private final IReadOnlyObservable<ObservableVertex<TodoElement>> categoryVertex = _categoryVertex;

    private final ObservableManager observableManager = new ObservableManager();

    // DESCRIPTION inputs
    @FXML private TextField titleInput;

    // DATE inputs
    @FXML private Label createdDateInput;

    @FXML private TextArea description;



    public CategoryEditBar() {
        loadFXML();
    }

    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CategoryEditBar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void onConfirmChangesBtn_click(ActionEvent ae) {
        if(categoryVertex.getValue() == null)
            return;

        Category cat = (Category) this.categoryVertex.getValue().getElement();

        cat.setDescription(description.getText());

        cat.setName(titleInput.getText());
    }


    @FXML
    private void initialize() {
        observableManager.addListener(_categoryVertex, this::onCategoryChange);
    }

    private void onCategoryChange(ObservableVertex<TodoElement> catVertex) {

        if (categoryVertex == null) {
            description.setText("");
            titleInput.setText("");
            createdDateInput.setText("");
        }
        else {
            Category cat = (Category) this.categoryVertex.getValue().getElement();
            description.setText(cat.getDescription());

            titleInput.setText(cat.getName());

            createdDateInput.setText(cat.creationDate.toString());
        }
    }


    public void setEditVertex(ObservableVertex<TodoElement> categoryVertex) {
        if(categoryVertex != null && !(categoryVertex.getElement() instanceof Category))
            throw new IllegalArgumentException("EditBar.onTaskChange() - taskVertex.getElement() must be of type Task");

        this._categoryVertex.setValue(categoryVertex);
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
