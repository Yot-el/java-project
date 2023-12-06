module app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;

    opens app to javafx.fxml;
    opens app.controllers to javafx.fxml;
    opens app.custom to javafx.fxml;
    exports app;
}
