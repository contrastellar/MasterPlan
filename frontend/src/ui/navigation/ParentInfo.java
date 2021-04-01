package ui.navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import ui.taskboard.TaskView;

import java.io.IOException;
import java.lang.reflect.Constructor;

public enum ParentInfo {

    MAIN_SCENE("../window/MainView.fxml"),
    SETTINGS("../account/settings/Settings.fxml"),
    TASK_VIEW(TaskView.class);

    private final ParentProvider parentProvider;

    ParentInfo(String filename) {
        this.parentProvider = () -> {
            try {
                return FXMLLoader.load(getClass().getResource(filename));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    ParentInfo(Class<? extends Parent> parentClazz) {
        this.parentProvider = () -> {
            try {
                // get public no args constructor
                Constructor<? extends Parent> parentCtr = parentClazz.getConstructor();
                return parentCtr.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    ParentInfo(ParentProvider parentProvider) {
        this.parentProvider = parentProvider;
    }

    public Parent getParent() {
        return parentProvider.getParent();
    }
}
