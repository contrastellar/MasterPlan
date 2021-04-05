package ui.taskboard.listview;

import components.Category;
import components.observable.IReadOnlyObservable;
import components.observable.Observable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import util.graph.ObservableVertex;

public class ListViewHeader extends VBox {

    @FXML
    private Label headerName;

    @FXML
    private Label numCategories;

    @FXML
    private Label numTasks;

    private final Observable<ObservableVertex<Category>> _rootCategory;
    public final IReadOnlyObservable<ObservableVertex<Category>> rootCategory;

    public ListViewHeader(ObservableVertex<Category> rootCategory) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListViewHeader.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);


        if(rootCategory == null)
            throw new IllegalArgumentException("ListViewHeaderController() - rootCategory can not be null");

        this._rootCategory = new Observable<>(rootCategory);
        this.rootCategory = _rootCategory;

        _rootCategory.startListen(this::onRootCategoryChange);

    }

    private void onRootCategoryChange(ObservableVertex<Category> rootCategory) {
        headerName.setText(rootCategory.getElement().getName());
    }

    public ObservableVertex<Category> getRootCategory() {
        return _rootCategory.getValue();
    }

    public void setRootCategory(ObservableVertex<Category> cat) {
        if(cat == null)
            throw new IllegalArgumentException("ListViewHeaderController.setRootCategory() - rootCategory can not be null");

        _rootCategory.setValue(cat);
    }









}
