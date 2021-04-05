module MasterPlan {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens ui to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.account.settings to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.custom.icon to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.taskboard to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.taskboard.taskedit to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.window to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.window.statusbar to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.window.toolbar to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.window.toolbar.tab to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.taskboard.listview to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.taskboard.listview.category to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.taskboard.listview.task to javafx.graphics, javafx.controls, javafx.fxml;
}