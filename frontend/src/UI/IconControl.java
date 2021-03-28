package UI;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class IconControl extends VBox {

    @FXML
    public final ImageView imageView = new ImageView();

    @FXML
    public final Label label = new Label();

    public IconControl() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("IconControl.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String getFilename() {
        return imageView.getImage().getUrl();
    }

    public void setFilename(String value) {
        imageView.setImage(new Image(value));
    }

    public String getLabelText() { return label.getText(); }

    public void setLabelText(String value) { label.setText(value); }



}
