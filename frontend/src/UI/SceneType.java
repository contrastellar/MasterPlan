package UI;

public enum SceneType {

    /* scene enum types */
    LOGIN_SCENE("Login.fxml"),
    MAIN_SCENE("/UI/view/MainView.fxml");


    /* enum data */
    private final String filename;


    private SceneType(String filename) {
        this.filename = filename;
    }

    public String getFilename() { return filename; }

}
