package ui.taskboard.listview.category;

import components.Category;
import components.TodoElement;
import components.observable.IReadOnlyObservable;
import components.observable.Observable;
import components.task.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import ui.taskboard.listview.ListViewContainer;
import util.graph.*;
import java.io.IOException;

public class CategoryView extends GridPane {

    private final ListViewContainer listViewContainer;

    @FXML
    private Label categoryName;

    @FXML
    private Label tasksRemainingLabel;
    private static final String tasksRemainingPattern = "%d remaining";

    private final Observable<ObservableVertex<TodoElement>> _rootCategory = new Observable<>();
    public final IReadOnlyObservable<ObservableVertex<TodoElement>> rootCategory = _rootCategory;


    public CategoryView() {

        loadFXML();

        _rootCategory.startListen(this::onRootCategoryChange);

        listViewContainer = new ListViewContainer();

        GridPane.setColumnIndex(listViewContainer, 1);
        GridPane.setColumnSpan(listViewContainer, 2);
        GridPane.setRowIndex(listViewContainer, 2);

        getChildren().add(listViewContainer);
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

    private void onRootCategoryChange(ObservableVertex<TodoElement> rootCategory) {
        if(rootCategory == null) {
            categoryName.setText("No Category");
            tasksRemainingLabel.setText("N/A");
            return;
        }

        listViewContainer.setRootVertex(rootCategory);
        rootCategory.startListen(this::onTaskRemainingTasksChange);
        rootCategory.getElement().name.startListen(this::onCategoryNameChange);
    }

    private void onTaskRemainingTasksChange(ObservableVertexChange<TodoElement> change) {

        int remainingTasks = 0;

        for(var vertex : _rootCategory.getValue().getGraph().getOutVertices(_rootCategory.getValue()))
            if(vertex.getElement() instanceof Task)
                remainingTasks++;

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
