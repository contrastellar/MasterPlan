module MasterPlan {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires json.simple;

    opens ui                                  to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.workspaces.archivespace.task     to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.workspaces.archivespace.category to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.tag                              to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.workspaces.archivespace          to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.account.settings                 to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.account.settings.menu            to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.account.login                    to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.account.login.create             to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.util.icon                        to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.workspaces                       to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.workspaces.editbar               to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.window                           to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.window.statusbar                 to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.window.toolbar                   to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.window.toolbar.tab               to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.workspaces.listspace             to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.workspaces.listspace.category    to javafx.graphics, javafx.controls, javafx.fxml;
    opens ui.workspaces.listspace.task        to javafx.graphics, javafx.controls, javafx.fxml;
}