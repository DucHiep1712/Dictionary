package com.dictionary.controllers;

import com.dictionary.functions.Dictionary;
import com.dictionary.functions.DictionaryManagement;
import com.dictionary.functions.Word;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static javafx.fxml.FXMLLoader.NULL_KEYWORD;

public class Scene2Controller implements Initializable {

    @FXML
    public Tooltip tooltip;

    @FXML
    public Button soundBtn;

    @FXML
    private ListView<String> myListView = new ListView<>();

    @FXML
    private TextArea definitionText;

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
<<<<<<< Updated upstream
        try {
            dictionary.listWord.clear();
            dictionaryManagement.insertFromFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
=======
//        dictionary.listWord.clear();
        definitionText.setEditable(false);
        if (dictionary.listWord.isEmpty()) dictionaryManagement.insertFromMySQL();
>>>>>>> Stashed changes

        definitionText.setVisible(false);
        soundBtn.setVisible(false);

        setList();

        //Ham xu ly khi bam vao tu trong danh sach
        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                try {
                    definitionText.setVisible(true);
                    current = myListView.getSelectionModel().getSelectedItem();
                    definitionText.setText("- " + dictionary.searchWord(current));

                    soundBtn.setVisible(true);
                    tooltip.setShowDelay(Duration.seconds(0.25));

                    System.setProperty("mbrola.base", "D:/java/Dictionaries/mbrola");
                    System.setProperty("freetts.voices", "de.dfki.lt.freetts.en.us.MbrolaVoiceDirectory");
                    freettsVM = VoiceManager.getInstance();

                    freettsVoice = freettsVM.getVoice("mbrola_us2");

                    freettsVoice.allocate();

                    soundBtn.setOnAction(event -> freettsVoice.speak(current));
                }
                catch (Exception e) {
                    System.out.println(":D");
                }
            }
        });

        //Lay tu trong khung tim kiem moi khi thay doi
        searchBox.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                try {
                    if (!(t1.equals(NULL_KEYWORD) || t1.equals(""))) {
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
                catch (Exception e){
                    System.out.println(":D");
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
