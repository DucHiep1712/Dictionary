module com.dictionary {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.dictionary.controllers to javafx.fxml;
    opens com.dictionary.functions;
    exports com.dictionary.controllers;
    exports com.dictionary.functions;
}