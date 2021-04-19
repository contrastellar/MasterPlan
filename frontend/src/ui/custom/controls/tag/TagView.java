package ui.custom.controls.tag;

import components.Tag;
import components.observable.IListener;
import components.observable.IReadOnlyObservable;
import components.observable.Observable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.IOException;

class TagView extends HBox {

    public final Tag tag;

    private IListener<TagView> removeTagCallback = null;

    @FXML
    private Label tagName;

    public TagView(Tag tag) {
        this.tag = tag;

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
        tag.name.startListen(this::onTagNameChange);
        tag.color.startListen(this::onTagColorChange);
    }

    public void setOnRemoveCallback(IListener<TagView> removeTagCallback) {
        this.removeTagCallback = removeTagCallback;
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
        // TODO: add remove button
    }

    @FXML
    private void onMouseExit(MouseEvent me) {
        // TODO: remove remove button
    }






}
