package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DictionaryManagement {

    public void insertFromCommandline() {
        Scanner scanner = new Scanner(System.in);
        int numberOfWord;
        System.out.print("Nhap so luong tu: ");
        numberOfWord = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numberOfWord; i++) {
            Word myWord = new Word();
            System.out.print("Nhap tu tieng Anh: ");
            myWord.setWord_target(scanner.nextLine());
            System.out.print("Nhap nghia tieng Viet: ");
            myWord.setWord_explain(scanner.nextLine());
            Dictionary.listWord.add(myWord);
        }
    }

    public void insertFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("dictionaries.txt"));
        while (scanner.hasNext()) {
            String stringWord = scanner.nextLine();
            Scanner scan = new Scanner(stringWord).useDelimiter("s*\ts*");
            Word myWord = new Word();
            myWord.setWord_target(scan.next());
            myWord.setWord_explain(scan.next());
            Dictionary.listWord.add(myWord);
        }
    }

    public void dictionaryLookup() {
        String stringWord = "";
        System.out.print("Nhap tu muon tra: ");
        Scanner scanner = new Scanner(System.in);
        stringWord = scanner.nextLine();
        for (Word w : Dictionary.listWord) {
            if (w.getWord_target().equals(stringWord)) {
                System.out.println(stringWord + " nghia la: " + w.getWord_explain());
            }
        }
    }
}
