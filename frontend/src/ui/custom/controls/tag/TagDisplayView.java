package ui.custom.controls.tag;

import components.Tag;
import components.TodoElement;
import components.observable.IReadOnlyObservable;
import components.observable.Observable;
import components.observable.ObservableManager;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import ui.custom.Viewable;
import util.graph.ObservableVertex;

import java.util.Collection;

public class TagDisplayView extends FlowPane implements Viewable {

    private final Observable<ObservableVertex<TodoElement>> _vertex = new Observable<>();
    public final IReadOnlyObservable<ObservableVertex<TodoElement>> vertex = _vertex;

    public TagDisplayView() {
        _vertex.startListen(this::onVertexChange);

        setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void onVertexChange(ObservableVertex<TodoElement> vertex) {
        if(vertex == null)
            return;

        vertex.getElement().tags.startListen(this::onTagsChange);
    }

    private void onTagRemoved(TagView tagView) {
        vertex.getValue().getElement().tags.remove(tagView.tag);
    }

    // TODO: make this more efficient
    private void onTagsChange(Collection<Tag> tags) {
        for(Node node : getChildren()) {
            TagView tagView = (TagView) node;
            tagView.unregisterListeners();
        }

        getChildren().clear();

        for(Tag tag : tags) {
            TagView tagView = new TagView(tag);
            tagView.setOnRemoveCallback(this::onTagRemoved);
            tagView.registerListeners();

            getChildren().add(tagView);
        }
    }

    public void setVertex(ObservableVertex<TodoElement> vertex) {
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

    }

    @Override
    public void unregisterListeners() {

    }
}
