package com.dictionary.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Scene4Controller implements Initializable {
    private String src = "en";
    private String to = "vi";

    @FXML
    TextArea srcLangField, toLangField;

    @FXML
    Button switchContainer, cancelBtn, translateBtn;

    @FXML
    Label srcLang, toLang;

    @FXML
    ChoiceBox srcLangChoice, toLangChoice;

    ObservableList<String> langs = FXCollections.observableArrayList();
    Map<String, String> languages = new HashMap<>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        toLangField.setEditable(false);

        languages.put("Chinese (Simplified)", "zh-CN");
        languages.put("Chinese (Traditional)", "zh-TW");
        languages.put("English", "en");
        languages.put("French", "fr");
        languages.put("German", "de");
        languages.put("Russian", "ru");
        languages.put("Spanish", "es");
        languages.put("Vietnamese", "vi");

        for (Map.Entry<String, String> entry : languages.entrySet()) {
            langs.add(entry.getKey());
        }

        srcLangChoice.setItems(langs);
        toLangChoice.setItems(langs);

        //Ham xu ly chi user chon ngon ngu
        ChangeListener<String> srcChangeListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.equals(null)) {
                    src = languages.get(t1);
                    srcLang.setText(t1);
                    if (!(src.equals("zh-CN") || src.equals("zh-TW"))) {
                        srcLang.setLayoutX(150);
                    } else {
                        srcLang.setLayoutX(105);
                    }
                }
            }
        };

        ChangeListener<String> toChangeListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.equals(null)) {
                    to = languages.get(t1);
                    toLang.setText(t1);
                    if (!(to.equals("zh-CN") || to.equals("zh-TW"))) {
                        toLang.setLayoutX(530);
                    } else {
                        toLang.setLayoutX(485);
                    }
                }
            }
        };

        srcLangChoice.getSelectionModel().selectedItemProperty().addListener(srcChangeListener);
        toLangChoice.getSelectionModel().selectedItemProperty().addListener(toChangeListener);

        //Dich
        translateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    toLangField.setText(translate(src, to, srcLangField.getText()));
                } catch (IOException e) {
                    System.out.println(":D");
                }
            }
        });

        //Ham xu ly khi user muon trao ngon ngu
        switchContainer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String temp = srcLang.getText();
                srcLang.setText(toLang.getText());
                toLang.setText(temp);
                srcLang.setLayoutX(152);
                toLang.setLayoutX(505);
                temp = src;
                src = to;
                to = temp;
            }
        });

        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                srcLangField.clear();
            }
        });
    }

    //Ham dich gian tiep qua google script
    private static String translate(String langFrom, String langTo, String text) throws IOException {
        String urlStr = "https://script.google.com/macros/s/AKfycbwTFYdpprwe5vZEvU1PYcvQSyKMvYFN-YTSlmWl7jWkqCtyfrc/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr.replace(" ", "%20"));
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
