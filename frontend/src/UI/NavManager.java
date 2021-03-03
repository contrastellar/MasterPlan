package UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

/**
 * A class to handle moving between scenes in the application.
 */
public class NavManager {
    Stack<String> navStack;
    Stage stage;

    /**
     * Constructor for the NavManager
     */
    public NavManager(Stage stageMade){
        navStack = new Stack<>();
        stage = stageMade;
    }

    /**
     * Change scene of window to designated type.
     * @param fxmlFile name of file to move to.
     */
    public void changeScene(String fxmlFile) {
        Scene root = null;
        try{
            root = FXMLLoader.load(getClass().getResource(fxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException. Cannot read file. (Add relevant 'exports' line in module-info.java");
        } finally {
            navStack.push(fxmlFile);
            stage.setScene(root);
            stage.show();
        }
    }

    /**
     * Go back one scene, similar to the back button on browsers.
     */
    public void backScene(Button button){
        Stage stage;
        Scene root = null;
        stage = (Stage) button.getScene().getWindow();
        navStack.pop();
        String nextScreen = navStack.pop();
        try{
            root = FXMLLoader.load(getClass().getResource(nextScreen));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException. Cannot read file. (Add relevant 'exports' line in module-info.java");

        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("NullPointerException. Cannot find file.");
        }
        navStack.push(nextScreen);
        stage.setScene(root);
        stage.show();
    }
}
