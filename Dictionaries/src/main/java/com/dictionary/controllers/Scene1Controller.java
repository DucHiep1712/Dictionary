package com.dictionary.controllers;

import com.dictionary.functions.Dictionary;
import com.dictionary.functions.Word;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.util.*;

public class Scene1Controller implements Initializable {

    @FXML
    private ListView<String> myListView;

    @FXML
    private Label definitionText;

    @FXML
    private TextField searchBox;

    private String current;
    private Map<String, String> holder = new HashMap<>();
    private ArrayList<String> word = new ArrayList<>();
    private ArrayList<String> preview = new ArrayList<>();
    private ArrayList<String> filtered = new ArrayList<>();

    /**
     * Ham thiet ke su thay doi cua danh sach tu.
     *
     * @param arg0 argument 0
     * @param arg1 argument 1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        Scanner scanner = null;

        try {
            scanner = new Scanner(new File("src/main/resources/utils/dictionaries.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        while (scanner.hasNext()) {
            Scanner scan = new Scanner(scanner.nextLine()).useDelimiter("\t");
            String temp1 = scan.next();
            String temp2 = scan.next();

            word.add(temp1);
            holder.put(temp1, " " + temp2);
            if (preview.size() < 20) {
                preview.add(temp1);
            }
        }

        Collections.sort(word);
        Collections.sort(preview);

        myListView.getItems().addAll(preview);

        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                current = myListView.getSelectionModel().getSelectedItem();
                definitionText.setText(holder.get(current));
            }
        });

        searchBox.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.equals("")) {
                    myListView.getItems().clear();
                    filtered.clear();

                    for (String temp : holder.keySet()) {
                        if (temp.indexOf(t1) == 0) {
                            filtered.add(temp);
                        }
                    }

                    for (String temp : filtered) {
                        if (!myListView.getItems().contains(temp)) {
                            myListView.getItems().add(temp);
                        }
                    }
                } else {
                    myListView.getItems().clear();
                    myListView.getItems().addAll(word);
                }
            }
        });
    }
}
