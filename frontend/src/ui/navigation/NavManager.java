package ui.navigation;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class NavManager {

    private final Stage mainWindow;

    public NavManager(Stage mainWindow) {
        if(mainWindow == null)
            throw new IllegalArgumentException("mainWindow can not be null");

        if(mainWindow.getScene() == null)
            throw new IllegalArgumentException("mainWindow.getScene() can not be null");

        this.mainWindow = mainWindow;
    }

    public void changeScene(ParentInfo parentInfo) {
        mainWindow.getScene().setRoot(parentInfo.getParent());
    }

}
