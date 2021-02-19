package UI;

import javafx.application.Application;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        // load UI resources
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainApp.fxml"));

        Scene scene = new Scene(root, 300, 275, Color.DARKGRAY);

        stage.setTitle("MasterPlan");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        // release UI resources
    }
}