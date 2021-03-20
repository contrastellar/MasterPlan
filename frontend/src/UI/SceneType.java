package UI;

public enum SceneType {

    /* scene enum types */
    LOGIN_SCENE("Login.fxml"),
    MAIN_SCENE("/UI/view/MainView.fxml"),
    USER_SETTINGS_SCENE("/UI/view/UserSettings.fxml"),
    USER_SETTINGS_VIEW_SCENE("/UI/view/SettingsView.fxml");


    /* enum data */
    private final String filename;


    private SceneType(String filename) {
        this.filename = filename;
    }

    public String getFilename() { return filename; }

}
