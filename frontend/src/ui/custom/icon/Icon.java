package ui.custom.icon;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Icon Custom Component
 */
public class Icon extends VBox {
    @FXML private SVGPath icon;                 // Icon SVG path shape
    @FXML private Region region;                // Region containing icon

    private String iconColor;                   // Current icon color
    private String defaultColor;                // Original icon color
    private boolean isDefaultColor = false;     // Flag to save original icon color

    /**
     * Constructs Icon component with loader
     */
    public Icon() {
        // Set fxml root and controller to this instance
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Icon.fxml"));
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
     * Set iconColor attribute
     * <Icon iconColor="white"/>
     * @param color color string ('white', '#ffffff', etc.)
     */
    public void setIconColor(String color) {
        // Stores original color
        if (!isDefaultColor) {
            defaultColor = color;
            isDefaultColor = true;
        }

        // Set icon style
        iconColor = color;
        region.setStyle("-fx-background-color: " + iconColor);
    }

    /**
     * Returns current color of icon
     * @return current color as String
     */
    public String getIconColor() {
        return iconColor;
    }

    /**
     * Resets icon color to original color
     */
    public void resetIconColor() {
        iconColor = defaultColor;
        setIconColor(iconColor);
    }

    /**
     * Sets icon shape from filename
     * @param filename name of svg file ("home.svg")
     */
    public void setIcon(String filename) {
        File file = new File("frontend/assets/icons/" + filename);
        String fileData = "";
        try {
            fileData = Files.readString(Paths.get(file.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        icon.contentProperty().set(fileData);
    }

    /**
     * Returns icon shape as SVG String
     * @return icon shape as SVG String
     */
    public String getIcon() {
        return icon.contentProperty().getValue();
    }
}
