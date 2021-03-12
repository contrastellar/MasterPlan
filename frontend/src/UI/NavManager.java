package UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

/**
 * A class to handle moving between scenes in the application.
 */
public class NavManager {
    private final Stack<SceneType> navStack = new Stack<>();
    private final Stage stage;

    public NavManager(Stage stageMade){
        stage = stageMade;
    }

    /**
     * Change scene of window to designated type.
     * @param scene type of scene to move to
     */
    public void changeScene(SceneType scene) throws IOException {

        Scene root = FXMLLoader.load(getClass().getResource(scene.getFilename()));
        stage.setScene(root);

    }

    /**
     * Go back one scene, similar to the back button on browsers.
     */
    public void backScene(Button button) throws IOException {
        if(navStack.empty())
            return;

        SceneType backScene = navStack.pop();

        changeScene(backScene);
    }
}
