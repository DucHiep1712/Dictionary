package com.dictionary.controllers;

import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;

import java.io.FileNotFoundException;
import java.io.File;
import java.net.URL;

import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;

import java.util.*;

public class Scene1Controller implements Initializable {

    @FXML
    private ListView<String> myListView;

    @FXML
    private Label definitionText;

    private String current;

    /**
     * Ham thiet ke su thay doi cua danh sach tu.
     *
     * @param arg0 argument 0
     * @param arg1 argument 1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        Map<String, String> holder = new HashMap<>();
        ArrayList<String> word = new ArrayList<>();

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("src/main/resources/utils/dictionaries.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        while (scanner.hasNext()) {
            String stringWord = scanner.nextLine();
            String temp1 = "";
            String temp2 = "";
            int i;

            for (i = 0; i < stringWord.length(); i++) {
                if (stringWord.charAt(i) == ' ') {
                    break;
                }
                temp1 += stringWord.charAt(i);
            }

            for (; i < stringWord.length(); i++) {
                if (stringWord.charAt(i) != ' ') {
                    break;
                }
            }

            for (; i < stringWord.length(); i++) {
                temp2 += stringWord.charAt(i);
            }

            word.add(temp1);
            holder.put(temp1, temp2);
        }

        myListView.getItems().addAll(word);

        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                current = myListView.getSelectionModel().getSelectedItem();
                definitionText.setText("Definition: " + holder.get(current));
            }
        });
    }
}
