package ui.taskboard.listview;

import components.Category;
import components.observable.IReadOnlyObservable;
import components.observable.Observable;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import ui.taskboard.listview.category.CategoryView;
import util.graph.ObservableVertex;

public class ListView extends VBox {

    private final Observable<ObservableVertex<Category>> _rootVertex;
    public final IReadOnlyObservable<ObservableVertex<Category>> rootVertex;

    public ListView(ObservableVertex<Category> rootVertex) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        if(rootVertex == null)
            throw new IllegalArgumentException("ListViewController.setRootView() - rootView can not be null");

        this._rootVertex = new Observable<>(rootVertex);
        this.rootVertex = _rootVertex;

        this._rootVertex.startListen(this::rootVertexChanged);
    }

    private void initialize() {
        getChildren().add(0, new ListViewHeader(_rootVertex.getValue()));
    }

    public ObservableVertex<Category> getRootVertex() {
        return _rootVertex.getValue();
    }

    public void setRootVertex(ObservableVertex<Category> rootVertex) {
        if(rootVertex == null)
            throw new IllegalArgumentException("ListViewController.setRootView() - rootView can not be null");

        _rootVertex.setValue(rootVertex);
    }

    private void rootVertexChanged(ObservableVertex<Category> rootVertex) {
        // update ui
    }

}
