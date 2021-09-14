module com.example.server {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.server to javafx.fxml;
    exports com.example.server;
    exports com.example.server.business;
    opens com.example.server.business to javafx.fxml;
    exports com.example.server.Controller;
    opens com.example.server.Controller to javafx.fxml;
}