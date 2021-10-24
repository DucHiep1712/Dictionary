package com.dictionary.functions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class DictionaryCommandline {

    DictionaryManagement dManagement = new DictionaryManagement();

    /**
     * Ham in du lieu ra man hinh.
     */
    public void showAllWords() {
        int count = 1;
        System.out.println("\nNo \t | English \t\t\t | Vietnamese");
        for (Word w : Dictionary.listWord) {
            if (w.getWord_target().length() <= 3) {
                System.out.println(count + " \t | " + w.getWord_target() + " \t\t\t\t | " + w.getWord_explain());
            } else if (w.getWord_target().length() <= 7) {
                System.out.println(count + " \t | " + w.getWord_target() + " \t\t\t | " + w.getWord_explain());
            } else if (w.getWord_target().length() <= 11) {
                System.out.println(count + " \t | " + w.getWord_target() + " \t\t | " + w.getWord_explain());
            } else {
                System.out.println(count + " \t | " + w.getWord_target() + " \t | " + w.getWord_explain());
            }
            count++;
        }
    }

    /**
     * Ham goi main 1.
     */
    public void dictionaryBasic() {
        dManagement.insertFromCommandline();
        this.showAllWords();
    }

    /**
     * Ham goi main 2.
     * @throws FileNotFoundException catch error
     * @throws IOException catch error
     */
    public void dictionaryAdvanced() throws FileNotFoundException, IOException {
<<<<<<< Updated upstream
        dManagement.insertFromFile();
=======
//        dManagement.insertFromFile();
        dManagement.insertFromMySQL();
>>>>>>> Stashed changes
//        dManagement.dictionaryLookup();
//        dManagement.insertWord();
//        dManagement.removeWord();
//        dManagement.modifyWord();
//        this.dictionarySearcher();
//        dManagement.dictionaryExportToFile();
//        this.showAllWords();
    }

    /**
     * Ham tra cuu nhung tu co am dau giong nhau.
     * Vi du: tra -> transport, translate, transform,...
     */
    public void dictionarySearcher() {
        System.out.print("Nhap tu can tra cuu: ");
        Scanner scanner = new Scanner(System.in);
        String stringWord = scanner.nextLine();
        System.out.println("Cac tu bat dau bang '" + stringWord + "': ");
        Dictionary.listWord.forEach((w) -> {
            int index = w.getWord_target().indexOf(stringWord);
            if (index == 0) {
                System.out.println(w.getWord_target() + " \t | " + w.getWord_explain());
            }
        });
    }
}
