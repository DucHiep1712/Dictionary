package com.dictionary.controllers;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.dictionary.functions.Dictionary;
import com.dictionary.functions.DictionaryManagement;
import com.dictionary.functions.Word;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static javafx.fxml.FXMLLoader.NULL_KEYWORD;

public class Scene2Controller implements Initializable {
    public ImageView searchIcon;
    public Tooltip tooltip;
    public Button soundBtn;
    @FXML
    private ListView<String> myListView = new ListView<>();

    @FXML
    private Label definitionText;

    @FXML
    private TextField searchBox;

    private String current;
    private List<Word> filtered = new ArrayList<>();
    private Dictionary dictionary = new Dictionary();
    private DictionaryManagement dictionaryManagement = new DictionaryManagement();

    ObservableList<String> list = FXCollections.observableArrayList();
    
    VoiceManager freettsVM;
    Voice freettsVoice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            dictionary.listWord.clear();
            dictionaryManagement.insertFromFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        definitionText.setVisible(false);
        soundBtn.setVisible(false);

        setList();

        //Ham xu ly khi bam vao tu trong danh sach
        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                definitionText.setVisible(true);
                current = myListView.getSelectionModel().getSelectedItem();
                definitionText.setText("- " + dictionary.searchWord(current));
                
                soundBtn.setVisible(true);
                tooltip.setShowDelay(Duration.seconds(0.25));

                System.setProperty("mbrola.base", "C:/Users/ASUS/Dictionary1/mbrola");
                System.setProperty("freetts.voices", "de.dfki.lt.freetts.en.us.MbrolaVoiceDirectory");
                freettsVM = VoiceManager.getInstance();

                freettsVoice = freettsVM.getVoice("mbrola_us1");

                freettsVoice.allocate();

                soundBtn.setOnAction(event -> freettsVoice.speak(current));
            }
        });

        //Lay tu trong khung tim kiem moi khi thay doi
        searchBox.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!(searchBox.getText().equals(NULL_KEYWORD) || searchBox.getText().equals(""))) {
                    boolean isNull = true;

                    myListView.getItems().clear();
                    filtered.clear();
                    t1 = t1.trim();

                    String finalT = t1;

                    for (Word w : dictionary.listWord) {
                        if (w.getWord_target().indexOf(t1) == 0) {
                            isNull = false;
                            break;
                        }
                    }

                    if (isNull == false) {
                        filtered = dictionary.listWord.stream().filter(word -> word.getWord_target().indexOf(finalT) == 0).collect(Collectors.toList());

                        for (Word temp : filtered) {
                            if (!myListView.getItems().contains(temp.getWord_target())) {
                                myListView.getItems().add(temp.getWord_target());
                            }
                        }
                    } else {
                        myListView.getItems().add("Từ không tồn tại!");
                    }
                } else {
                    myListView.getItems().clear();
                    setList();
                }
            }
        });
    }

    //Khoi tao danh sach
    private void setList() {
        list.clear();
        dictionary.sort();
        myListView.getItems().clear();

        for (int i = 0; i < Math.min(20, dictionary.listWord.size()); i++) {
            list.add(dictionary.listWord.get(i).getWord_target());
        }

        List<String> remove = new ArrayList<>(myListView.getSelectionModel().getSelectedItems());

        myListView.getItems().removeAll(remove);
        myListView.setItems(list);
    }
}
