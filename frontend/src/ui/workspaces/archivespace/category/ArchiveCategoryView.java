package ui.workspaces.archivespace.category;

import components.Category;
import components.TodoElement;
import components.task.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import models.MainModel;
import observable.IReadOnlyObservable;
import observable.Observable;
import observable.ObservableManager;
import ui.util.Viewable;
import util.graph.ObservableVertex;
import util.graph.ObservableVertexChange;

import java.io.IOException;
import java.util.List;

public class ArchiveCategoryView extends GridPane implements Viewable {

    @FXML
    private Button toggleBtn;

    @FXML
    private HBox buttonContainer;
    @FXML
    private HBox toggleContainer;
    @FXML
    private HBox titleContainer;

    @FXML
    private HBox remainingContainer;

    @FXML
    private Label categoryName;




    private final Observable<ObservableVertex<TodoElement>> _categoryVertex = new Observable<>();
    public final IReadOnlyObservable<ObservableVertex<TodoElement>> categoryVertex = _categoryVertex;



    private final ObservableManager observableManager = new ObservableManager();


    public ArchiveCategoryView() {
        loadFXML();
    }

    public ArchiveCategoryView(ObservableVertex<TodoElement> vertex) {
        loadFXML();
        setCategory(vertex);
    }


    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ArchiveCategoryView.fxml"));
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

        observableManager.addListener(_categoryVertex, this::onCategoryVertexChange);

        setOnMouseClicked((e) -> {
            e.consume();
            MainModel.model.editVertex.setValue(_categoryVertex.getValue());
        });



        getChildren().forEach(e -> {
            e.setOnMouseEntered(event -> {
                buttonContainer.setStyle("-fx-border-color: cadetblue;");
            });
            e.setOnMouseExited(event -> {
                buttonContainer.setStyle("-fx-border-color: transparent;");
            });
        });
    }


    private void onCategoryVertexChange(ObservableVertex<TodoElement> categoryVertex) {
        if(categoryVertex == null) {
            categoryName.setText("No Category");
            return;
        }
        Category cat = (Category) categoryVertex.getElement();

        observableManager.addListener(categoryVertex, this::onAdjacenceyListChange);
        observableManager.addListener(cat.name, this::onCategoryNameChange);
        observableManager.addListener(cat.backgroundColor, this::onCategoryColorChange);
    }

    private void onAdjacenceyListChange(ObservableVertexChange<TodoElement> change) {

        List<ObservableVertex<TodoElement>> numTaskQueryRes = _categoryVertex.getValue().getGraph().query(
                (e) -> e instanceof Task,
                _categoryVertex.getValue()
        );

        int totalElements = _categoryVertex.getValue().getGraph().getOutDegree(_categoryVertex.getValue());

        toggleBtn.setVisible(totalElements > 0);
    }

    @FXML
    private void onRemoveVertexBtn_click(ActionEvent e) {
        if(_categoryVertex.getValue() == null)
            return;

        _categoryVertex.getValue().getGraph().removeVertex(_categoryVertex.getValue());

        System.out.println("Removing vertex. Graph size:" + _categoryVertex.getValue().getGraph().getVertices().size());
    }

    @FXML
    private void onRemoveGraphBtn_click(ActionEvent e) {
        if(_categoryVertex.getValue() == null)
            return;

        _categoryVertex.getValue().getGraph().removeVertexReachable(_categoryVertex.getValue());

        System.out.println("Removing vertex. Graph size: " + _categoryVertex.getValue().getGraph().getVertices().size());
    }

    @FXML
    private void onArchive_click(ActionEvent e)  {
        if(_categoryVertex.getValue() == null)
            return;

        Category cat = ((Category) _categoryVertex.getValue().getElement());
        boolean curArchive = cat.isArchived();
        cat.setArchive(!curArchive);

        this.managedProperty().bindBidirectional(this.visibleProperty());
        this.setVisible(curArchive); //Hides the task at hand
    }


    private void onCategoryNameChange(String name) {
        categoryName.setText(name);
    }

    private void onCategoryColorChange(Color color) {
        if (color == null)
            return;
        toggleContainer.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        buttonContainer.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        titleContainer.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void setCategory(ObservableVertex<TodoElement> categoryVertex) {
        if(!(categoryVertex.getElement() instanceof Category))
            throw new IllegalArgumentException("CategoryView() - rootVertex.getElement() is not of type Category");

        _categoryVertex.setValue(categoryVertex);
    }

    public ObservableVertex<TodoElement> getCategory() {
        return _categoryVertex.getValue();
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
