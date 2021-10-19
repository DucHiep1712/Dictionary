package com.dictionary.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Scene1Controller implements Initializable {

    @FXML
    Tooltip tooltip1, tooltip2, tooltip3;

    @FXML
    private Button homeBtn, fixBtn, ggBtn;

    @FXML
    private AnchorPane container;

    //Ham hien khung hinh
    @FXML
    private void showComponent(String path) {
        try {
            AnchorPane Component = FXMLLoader.load(getClass().getResource(path));
            setNode(Component);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Ham them con tro den khung hinh
    private void setNode(Node node) {
        container.getChildren().clear();
        container.getChildren().add((Node) node);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tooltip1.setShowDelay(Duration.seconds(0.25));
        tooltip2.setShowDelay(Duration.seconds(0.25));
        tooltip3.setShowDelay(Duration.seconds(0.25));

        //Cac ham xu ly nut bam
        homeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("/views/Scene2.fxml");
            }
        });

        fixBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("/views/Scene3.fxml");
            }
        });

        this.showComponent("/views/Scene2.fxml");
    }
}
