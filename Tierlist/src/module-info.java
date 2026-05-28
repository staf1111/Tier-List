module Tierlist {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;

    opens application.controller to javafx.fxml;
    opens application.controller.modal to javafx.fxml;
    
    opens services.screenManager to javafx.fxml, javafx.controls;
    
    opens application to javafx.graphics, javafx.fxml, javafx.controls;
    
    exports application;
}