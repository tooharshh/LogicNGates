module project.logicgatesimulator {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens project.logicgatesimulator to javafx.fxml;
    exports project.logicgatesimulator;
}
