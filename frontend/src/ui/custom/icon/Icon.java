package ui.custom.icon;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Icon Custom Component
 */
public class Icon extends Region {

    private final SVGPath icon = new SVGPath();

    public final double ICON_SIZE_DEFAULT = 20;

    public Icon() {
        setShape(icon);
        setIconColor(Color.WHITE);
        setIconSize(ICON_SIZE_DEFAULT);
    }

    public void setIconSize(double size) {
        setMinWidth(size);
        setMinHeight(size);
        setPrefWidth(size);
        setPrefHeight(size);
        setMaxWidth(size);
        setMaxHeight(size);
    }

    public double getIconSize() {
        return getWidth();
    }

    public void setIconColor(Color iconColor) {
        setBackground(new Background(new BackgroundFill(iconColor, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public Color getIconColor() {
        return (Color) getBackground().getFills().get(0).getFill();
    }

    /**
     * Sets icon shape from filename
     * @param filename name of svg file ("home.svg")
     */
    public void setIcon(String filename) {
        File file = new File("frontend/assets/icons/" + filename);
        String fileData;

        try {
            fileData = Files.readString(Paths.get(file.getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        icon.setContent(fileData);
    }

    /**
     * Returns icon shape as SVG String
     * @return icon shape as SVG String
     */
    public String getIcon() {
        return icon.getContent();
    }
}
