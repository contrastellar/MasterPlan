package ui.window.toolbar.tab;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import ui.custom.icon.Icon;
import java.io.IOException;

/**
 * Custom toolbar tab component containing a button with an icon
 */
public class Tab extends HBox {
    @FXML private Button button;    // Nested Tab Button
    @FXML private Icon icon;        // Button Icon

    /**
     * Constructs Tab component with loader
     */
    public Tab() {
        // Set fxml root and controller to this instance
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Tab.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        // Attempt to load resource
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Adds active style class to active and sets active icon color
     */
    public void setActive() {
        button.getStyleClass().add("active");
        icon.setIconColor("black");
    }

    /**
     * Removes active class from Tab
     */
    public void removeActive() {
       for (int i = 0; i < button.getStyleClass().size(); i++)
           if (button.getStyleClass().get(i) == "active")
               button.getStyleClass().remove(i);
    }

    /**
     * Resets Tab icon color to original
     */
    public void resetIconColor() {
        icon.resetIconColor();
    }

    /**
     * Gets onAction EventHandler
     * @return EventHandler<ActionEvent> handler
     */
    public final EventHandler<ActionEvent> getOnAction() {
        return button.getOnAction();
    }

    /**
     * Sets button on action handler
     * @param handler handler EventHandler<ActionEvent>
     */
    public final void setOnAction(EventHandler<ActionEvent> handler) {
        button.setOnAction(handler);
    }

    /**
     * Gets text of Tab Button
     * @return text
     */
    public String getText() {
        return button.getText();
    }

    /**
     * Sets text of Tab Button
     * @param text text
     */
    public void setText(String text) {
        button.setText(text);
    }

    /**
     * Sets icon color from string
     * @param color string ('white', '#ffffff', etc.)
     */
    public void setIconColor(String color) {
        icon.setIconColor(color);
    }

    /**
     * Gets current icon color
     * @return color as String
     */
    public String getIconColor() {
        return icon.getIconColor();
    }

    /**
     * Gets icon from Region Shape
     * @return SVG String of icon
     */
    public String getIcon() {
        return icon.getIcon();
    }

    /**
     * Sets Tab icon from file name
     * <Tab icon="home.svg"/>
     * @param filename svg filename
     */
    public void setIcon(String filename) {
        icon.setIcon(filename);
    }
}
