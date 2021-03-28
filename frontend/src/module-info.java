module MasterPlan {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.apache.commons.collections4;

    opens UI to javafx.fxml;
    opens UI.view to javafx.fxml;

    exports UI;
    exports UI.view;
}