package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

public class MainApp extends Application {
    /**
     * navManager call for the controllers for the views beyond the login screen.
     */
    public static NavManager navManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        // load UI resources
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage.setTitle("MasterPlan");
        stage.setScene(scene);
        stage.show();
        navManager = new NavManager(stage);
    }

    @Override
    public void stop() {
        // release UI resources
    }

}