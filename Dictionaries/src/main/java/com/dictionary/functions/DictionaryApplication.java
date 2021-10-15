package com.dictionary.functions;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class DictionaryApplication extends Application {

    public void runApplication(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/Scene1.fxml")));
        String style = this.getClass().getResource("/utils/style.css").toExternalForm();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(style);

        stage.setTitle("dictionary");
        stage.getIcons().add(new Image("/utils/icons/dictionary-icon.png"));
        stage.setWidth(800);
        stage.setHeight(690);
        stage.setScene(scene);
        stage.show();
    }
}
