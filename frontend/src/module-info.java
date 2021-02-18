module MasterPlan {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens UI to javafx.fxml;

    exports UI;
}