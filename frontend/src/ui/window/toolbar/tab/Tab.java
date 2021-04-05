package ui.window.toolbar.tab;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import ui.custom.icon.Icon;

/**
 * Custom toolbar tab component containing a button with an icon
 */
public class Tab extends RadioButton {

    private final Icon icon;

    private Color unselectedIconColor = Color.BLACK;
    private Color unselectedTextColor = Color.BLACK;

    private Color selectedIconColor = Color.WHITE;
    private Color selectedTextColor = Color.WHITE;


    /**
     * Constructs Tab component with loader
     */
    public Tab() {
        this.icon = new Icon();
        setGraphic(icon);

        icon.setIconColor(unselectedIconColor);
        this.setTextFill(unselectedTextColor);

        getStyleClass().remove("radio-button");
        getStyleClass().add("toggle-button");

        setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        selectedProperty().addListener(this::selectedChanged);
        setContentDisplay(ContentDisplay.TOP);
    }

    private void selectedChanged(ObservableValue<? extends Boolean> observableValue, boolean prevVal, boolean newVal) {
        if(newVal) {
            // setBackground(new Background(new BackgroundFill(unselectedIconColor, CornerRadii.EMPTY, Insets.EMPTY)));
            icon.setIconColor(selectedIconColor);
            this.setTextFill(selectedTextColor);
        }
        else {
            // setBackground(new Background(new BackgroundFill(selectedTextColor, CornerRadii.EMPTY, Insets.EMPTY)));
            icon.setIconColor(unselectedIconColor);
            this.setTextFill(unselectedTextColor);
        }
    }

    public double getIconSize() {
        return icon.getIconSize();
    }

    public void setIconSize(double size) {
        icon.setIconSize(size);
    }

    public Color getSelectedIconColor() {
        return selectedIconColor;
    }

    public void setSelectedIconColor(Color selectedIconColor) {
        this.selectedIconColor = selectedIconColor;
        if(isSelected())
            icon.setIconColor(selectedIconColor);
    }

    public Color getSelectedTextColor() {
        return selectedTextColor;
    }

    public void setSelectedTextColor(Color selectedTextColor) {
        this.selectedTextColor = selectedTextColor;
        if(isSelected())
            this.setTextFill(selectedTextColor);
    }

    public Color getUnselectedIconColor() {
        return unselectedIconColor;
    }

    public void setUnselectedIconColor(Color unselectedIconColor) {
        this.unselectedIconColor = unselectedIconColor;
        if(!isSelected()) {
            icon.setIconColor(unselectedIconColor);
        }
    }

    public Color getUnselectedTextColor() {
        return unselectedTextColor;
    }

    public void setUnselectedTextColor(Color unselectedTextColor) {
        this.unselectedTextColor = unselectedTextColor;
        if(!isSelected())
            this.setTextFill(unselectedTextColor);
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
