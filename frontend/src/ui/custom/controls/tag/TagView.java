package ui.custom.controls.tag;

import components.Tag;
import components.observable.IReadOnlyObservable;
import components.observable.Observable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.IOException;

public class TagView extends HBox {

    private final Observable<Tag> _tag = new Observable<>();
    public final IReadOnlyObservable<Tag> tag = _tag;

    @FXML
    private Label tagName;

    public TagView(TagDisplayView tagDisplayView) {
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
        _tag.startListen(this::onTagChange);
    }

    private void onTagChange(Tag tag) {
        if(tag == null) {
            tagName.setText("TagView.tag.getValue() - null");
            changeBackgroundColor(Color.WHITE);
            // TODO: stop listening from the previous tag
        }
        else {
            tag.name.startListen(this::onTagNameChange);
            tag.color.startListen(this::onTagColorChange);
        }

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

    public void setTag(Tag tag) {
        this._tag.setValue(tag);
    }

    public Tag getTag() {
        return tag.getValue();
    }

    @FXML
    private void onMouseEnter() {
        // TODO: add remove button
    }

    @FXML
    private void onMouseExit() {
        // TODO: remove remove button
    }






}
