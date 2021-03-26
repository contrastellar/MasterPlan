module MasterPlan {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens ui to javafx.fxml;
    opens ui.view to javafx.fxml;

    exports ui;
}