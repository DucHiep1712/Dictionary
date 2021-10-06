package com.company;

import java.io.FileNotFoundException;

public class DictionaryCommandline {
    DictionaryManagement dManagement = new DictionaryManagement();

    public void showAllWords() {
        int count = 1;
        System.out.println("\nNo \t | English \t | Vietnamese");
        for (Word w : Dictionary.listWord) {
            System.out.println(count + " \t | " + w.getWord_target() + " \t | " + w.getWord_explain());
            count++;
        }
    }

    public void dictionaryBasic() {
        dManagement.insertFromCommandline();
        this.showAllWords();
    }

    public void dictionaryAdvanced() throws FileNotFoundException {
        dManagement.insertFromFile();
        dManagement.dictionaryLookup();
        this.showAllWords();
    }
}
