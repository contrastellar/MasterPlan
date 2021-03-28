package UI;

import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

public class TabControl extends HBox {

    private final IconControl icon = new IconControl();

    public TabControl() {
        this.getChildren().add(icon);
    }

    public String getFilename() {
        return icon.getFilename();
    }

    public void setFilename(String value) {
        icon.setFilename(value);
    }

    public String getLabelText() { return icon.getLabelText(); }

    public void setLabelText(String value) { icon.setLabelText(value); }

}
