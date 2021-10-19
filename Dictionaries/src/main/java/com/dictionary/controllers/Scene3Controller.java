package com.dictionary.controllers;

import com.dictionary.functions.Dictionary;
import com.dictionary.functions.DictionaryManagement;
import com.dictionary.functions.Word;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ResourceBundle;

public class Scene3Controller implements Initializable {
    @FXML
    TextField addWordBox, removeWordBox;

    @FXML
    TextArea wordMeaningArea;

    @FXML
    Button addWordBtn, removeWordBtn;

    @FXML
    Label alertAddSuccess, alertAddEmpty, alertAddExplainEmpty, alertAddExist, alertRemoveSuccess, alertRemoveNotExist, alertRemoveEmpty;

    private Dictionary dictionary = new Dictionary();
    private DictionaryManagement dictionaryManagement = new DictionaryManagement();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alertAddSuccess.setVisible(false);
        alertAddEmpty.setVisible(false);
        alertAddExplainEmpty.setVisible(false);
        alertAddExist.setVisible(false);
        alertRemoveSuccess.setVisible(false);
        alertRemoveNotExist.setVisible(false);
        alertRemoveEmpty.setVisible(false);

        try {
            dictionary.listWord.clear();
            dictionaryManagement.insertFromFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Khi tro chuot den cac nut bam
        addWordBtn.hoverProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue) {
                addWordBtn.setLayoutX(162);
                addWordBtn.setPrefWidth(80);
                addWordBtn.setText("Thêm từ!");
            } else {
                addWordBtn.setText("Hmm?");
            }
        });

        removeWordBtn.hoverProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue) {
                removeWordBtn.setLayoutX(547);
                removeWordBtn.setPrefWidth(80);
                removeWordBtn.setText("Xóa từ!");
            } else {
                removeWordBtn.setText("Hmm?");
            }
        });

        //Khi bam nut
        addWordBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String target = addWordBox.getText();
                String explain = wordMeaningArea.getText();
                boolean exist = false;
                if (target.equals("") || addWordBox.getProperties().isEmpty()) {
                    invi();
                    alertAddEmpty.setVisible(true);
                } else if (explain.equals("") || wordMeaningArea.getProperties().isEmpty()) {
                    invi();
                    alertAddExplainEmpty.setVisible(true);
                } else {
                    for (Word w : dictionary.listWord) {
                        if (w.getWord_target().equals(target)) {
                            invi();
                            alertAddExist.setVisible(true);
                            exist = true;
                            break;
                        }
                    }
                    if (exist == false) {
                        invi();
                        dictionary.listWord.add(new Word(target, explain));
                        try {
                            String newWord = target + "\t" + explain + "\n";
                            Files.write(Paths.get("src/main/resources/utils/dictionaries.txt"), newWord.getBytes(), StandardOpenOption.APPEND);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        alertAddSuccess.setVisible(true);
                    }
                }
            }
        });

        removeWordBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (removeWordBox.getText().equals("")) {
                    invi();
                    alertRemoveEmpty.setVisible(true);
                } else {
                    String remove = removeWordBox.getText();
                    if (dictionary.searchWord(remove).equals("Word not found!")) {
                        invi();
                        alertRemoveNotExist.setVisible(true);
                    } else {
                        try {
                            String[] store = new String[dictionary.listWord.size()];
                            int pos = 0;
                            PrintWriter pw = new PrintWriter(new FileWriter("src/main/resources/utils/dictionaries.txt"));

                            for (Word w : dictionary.listWord) {
                                if (!w.getWord_target().equals(remove)) {
                                    String stringLine = w.getWord_target() + "\t" + w.getWord_explain() + "\n";
                                    store[pos] = stringLine;
                                    pos++;
                                }
                            }

                            for (int i = 0; i < pos; i++) {
                                pw.write(store[i]);
                            }
                            pw.close();
                            invi();
                            alertRemoveSuccess.setVisible(true);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    //An cac thong bao
    public void invi() {
        alertAddSuccess.setVisible(false);
        alertAddEmpty.setVisible(false);
        alertAddExplainEmpty.setVisible(false);
        alertAddExist.setVisible(false);
        alertRemoveSuccess.setVisible(false);
        alertRemoveNotExist.setVisible(false);
        alertRemoveEmpty.setVisible(false);
    }
}
