package ui.taskboard.listview;

import components.Category;
import components.TodoElement;
import components.observable.IReadOnlyObservable;
import components.observable.Observable;
import components.observable.ObservableManager;
import components.task.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import ui.custom.Viewable;
import util.graph.ObservableGraphChange;
import util.graph.ObservableVertex;
import util.graph.ObservableVertexChange;

import java.io.IOException;

public class ListSpaceViewHeader extends HBox implements Viewable {

    @FXML
    private Label headerName;

    @FXML
    private Label categoryContents;
    private static final String categoryContentsPattern = "%d Categories | %d Tasks";

    private final Observable<ObservableVertex<TodoElement>> _rootCategory = new Observable<>();
    public final IReadOnlyObservable<ObservableVertex<TodoElement>> rootCategory = _rootCategory;

    private final ObservableManager observableManager = new ObservableManager();

    public ListSpaceViewHeader() {
        loadFXML();
    }


    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListViewHeader.fxml"));
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
        _rootCategory.startListen(this::onRootCategoryChange);
    }

    private void onRootCategoryChange(ObservableVertex<TodoElement> rootCategory) {
        if(rootCategory == null) {
            headerName.setText("No category");
            categoryContents.setText("N/A");
            return;
        }

        if(!(rootCategory.getElement() instanceof Category))
            throw new IllegalArgumentException("ListViewHeader.onRootCategoryChange() - rootCategory.getElement is not of type Category");

        observableManager.addListener(rootCategory, this::onRootCategoryOutVerticesChange);
        observableManager.addListener(rootCategory.getGraph(), this::onRootCategoryOutVerticesRemoved);
        observableManager.addListener(rootCategory.getElement().name, this::onRootCategoryNameChange);
    }

    private void onRootCategoryNameChange(String name) {
        headerName.setText(name);
    }

    private void onRootCategoryOutVerticesChange(ObservableVertexChange<TodoElement> change) {
        if(change.removedEdgesSize() != 0 && change.addedEdgesSize() != 0)
            rootCategoryOutVerticesChanged();
    }

    private void onRootCategoryOutVerticesRemoved(ObservableGraphChange<TodoElement> change) {
        if(change.removedVerticesSize() != 0 || change.addedVerticesSize() != 0)
            rootCategoryOutVerticesChanged();
    }

    private void rootCategoryOutVerticesChanged() {
        int numCategories = 0;
        int numTasks = 0;

        for(var vertex : _rootCategory.getValue().getGraph().getOutVertices(_rootCategory.getValue())) {
            if(vertex.getElement() instanceof Category)
                numCategories++;
            else if(vertex.getElement() instanceof Task)
                numTasks++;
        }

        categoryContents.setText(String.format(categoryContentsPattern, numCategories, numTasks));
    }


    public void setRootCategory(ObservableVertex<TodoElement> rootCategory) {
        _rootCategory.setValue(rootCategory);
    }

    public ObservableVertex<TodoElement> getRootCategory() {
        return _rootCategory.getValue();
    }


    @Override
    public Node node() {
        return this;
    }

    @Override
    public void registerListeners() {
        observableManager.startListen();
    }

    @Override
    public void unregisterListeners() {
        observableManager.stopListen();
    }
}
