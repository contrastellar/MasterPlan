package ui.tag;

import components.Tag;
import components.TodoElement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import observable.*;
import ui.util.Viewable;
import util.graph.ObservableVertex;

import java.io.IOException;

public class TagDisplayView extends FlowPane implements Viewable {

    @FXML
    private Button addTagBtn;

    private final Observable<ObservableVertex<TodoElement>> _vertex = new Observable<>();
    public final IReadOnlyObservable<ObservableVertex<TodoElement>> vertex = _vertex;

    private final ObservableManager observableManager = new ObservableManager();
    private final IListener<ObservableList<Tag>> tagsListener = this::onTagsChange;

    public TagDisplayView() {
        loadFXML();
        setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TagDisplayView.fxml"));
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

    }

    @FXML
    private void onAddTagBtn_click() {
        if(_vertex.getValue() == null)
            return;

        Tag tag = CreateTagDialogue.showAndWait();

        if(tag != null)
            _vertex.getValue().getElement().tags.add(tag);
    }

    private void onTagRemoved(TagView tagView) {
        vertex.getValue().getElement().tags.remove(tagView.tag);
    }

    // TODO: make this more efficient
    private void onTagsChange(ObservableList<Tag> tags) {
        for(Node node : getChildren()) {
            if(node instanceof Viewable) {
                ((Viewable) node).unregisterListeners();
                getChildren().remove(node);
            }
        }

        for(Tag tag : tags) {
            TagView tagView = new TagView(tag);
            tagView.setOnRemoveCallback(this::onTagRemoved);
            tagView.registerListeners();

            getChildren().add(0, tagView);
        }
    }

    private void clearDisplayView() {
        getChildren().clear();
        getChildren().add(addTagBtn);
    }

    public void setVertex(ObservableVertex<TodoElement> vertex) {
        if(_vertex.getValue() != null) {
            observableManager.removeListener(_vertex.getValue().getElement().tags, tagsListener);
        }

        if(vertex != null) {
            observableManager.addListener(vertex.getElement().tags, tagsListener);
        }

        this._vertex.setValue(vertex);
    }

    public ObservableVertex<TodoElement> getVertex() {
        return _vertex.getValue();
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
