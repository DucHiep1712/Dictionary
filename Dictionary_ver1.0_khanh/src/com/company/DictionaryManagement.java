package com.company;

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
}
