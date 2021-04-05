package ui.taskboard.listview;

import components.Category;
import components.TodoElement;
import components.observable.IReadOnlyObservable;
import components.observable.Observable;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import util.graph.ObservableVertex;

import java.io.IOException;

public class ListView extends VBox {

    private final Observable<ObservableVertex<TodoElement>> _rootVertex = new Observable<>();
    private final IReadOnlyObservable<ObservableVertex<TodoElement>> rootVertex = _rootVertex;

    private final ListViewHeader listViewHeader;
    private final ListViewContainer listViewContainer;

    public Button addTaskBtn;
    public Button addCatBtn;

    public ListView() {

        loadFXML();

        listViewHeader = new ListViewHeader();
        listViewContainer = new ListViewContainer();

        listViewContainer.setId("listContainer");

        getChildren().addAll(listViewHeader, listViewContainer);

        _rootVertex.startListen(this::onRootVertexChange);

    }

    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void onRootVertexChange(ObservableVertex<TodoElement> rootVertex) {
        listViewHeader.setRootCategory(rootVertex);
        listViewContainer.setRootVertex(rootVertex);
    }

    public void setRootVertex(ObservableVertex<TodoElement> rootVertex) {
        if(!(rootVertex.getElement() instanceof Category))
            throw new IllegalArgumentException("ListView() - rootVertex is not of type Category");

        _rootVertex.setValue(rootVertex);
    }

    public ObservableVertex<TodoElement> getRootVertex() {
        return _rootVertex.getValue();
    }

}
