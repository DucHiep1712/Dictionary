module com.dictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires freetts;

    opens dictionary.controllers to javafx.fxml;
    opens dictionary.functions;
    exports dictionary.controllers;
    exports dictionary.functions;
}
