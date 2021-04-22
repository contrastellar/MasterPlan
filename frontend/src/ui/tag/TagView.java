package ui.tag;

import components.Tag;
import observable.IListener;
import observable.ObservableManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import ui.util.Viewable;

import java.io.IOException;

class TagView extends HBox implements Viewable {

    public final Tag tag;
    private IListener<TagView> removeTagCallback = null;
    private final Button removeButton = new Button();

    private final ObservableManager observableManager = new ObservableManager();

    @FXML
    private Label tagName;

    public TagView(Tag tag) {
        this.tag = tag;

        observableManager.addListener(tag.name, this::onTagNameChange);
        observableManager.addListener(tag.color, this::onTagColorChange);

        loadFXML();
    }

    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TagView.fxml"));
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

    public void setOnRemoveCallback(IListener<TagView> removeTagCallback) {
        this.removeTagCallback = removeTagCallback;
    }

    private void onRemoveButton_click(ActionEvent ae) {
        if(removeTagCallback != null)
            removeTagCallback.onChange(this);
    }

    private void onTagNameChange(String name) {
        tagName.setText(name);
    }

    private void changeBackgroundColor(Color color) {
        setBackground(new Background(new BackgroundFill(color, new CornerRadii(5, true), Insets.EMPTY)));
    }

    private void onTagColorChange(Color color) {
        changeBackgroundColor(color);
    }

    @FXML
    private void onMouseEnter(MouseEvent me) {
        getChildren().add(removeButton);
    }

    @FXML
    private void onMouseExit(MouseEvent me) {
        getChildren().remove(removeButton);
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
