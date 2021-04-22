package ui.tag;

import components.Tag;
import observable.ObservableManager;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import ui.util.Viewable;

public class EditTagView implements Viewable {

    @FXML
    private TextField tagNameInput;

    private Tag tag = null;

    private final ObservableManager observableManager = new ObservableManager();

    public EditTagView() {

    }

    private void loadFXML() {

    }

    @FXML
    private void initialize() {

    }

    public void setTag(Tag tag) {

    }

    public Tag getTag() {
        return tag;
    }

    @Override
    public Node node() {
        return null;
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
