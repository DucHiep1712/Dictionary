package com.company;

public class DictionaryCommandline {

    public void showAllWords() {
        int count = 1;
        System.out.println("\nNo \t | English \t | Vietnamese");
        for (Word w : Dictionary.listWord) {
            System.out.println(count + " \t | " + w.getWord_target() + " \t | " + w.getWord_explain());
            count++;
        }
    }

    public void dictionaryBasic() {
        DictionaryManagement dManagement = new DictionaryManagement();
        dManagement.insertFromCommandline();

        DictionaryCommandline dCommandline = new DictionaryCommandline();
        dCommandline.showAllWords();
    }
}
