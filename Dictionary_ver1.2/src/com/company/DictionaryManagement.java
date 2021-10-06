package com.company;

import java.io.*;
import java.util.Scanner;

public class DictionaryManagement {

    /**
     * Ham nhap du lieu tu ban phim.
     */
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

    /**
     * Ham lay du lieu tu file dictionaries.txt.
     * @throws FileNotFoundException catch error
     */
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

    /**
     * Ham tra cuu tu vung.
     */
    public void dictionaryLookup() {
        String stringWord = "";
        System.out.print("Nhap tu muon tra cuu: ");
        Scanner scanner = new Scanner(System.in);
        stringWord = scanner.nextLine();
        for (Word w : Dictionary.listWord) {
            if (w.getWord_target().equals(stringWord)) {
                System.out.println(stringWord + " nghia la: " + w.getWord_explain());
            }
        }
    }

    /**
     * Ham them tu vung.
     * @throws FileNotFoundException catch error
     * @throws IOException catch error
     */
    public void insertWord() throws FileNotFoundException, IOException {
        System.out.print("Nhap tu can them: ");
        Scanner scanner = new Scanner(System.in);
        String addWord = scanner.nextLine();
        boolean check = false;

        for (Word w : Dictionary.listWord) {
            if (w.getWord_target().equalsIgnoreCase(addWord)) {
                System.out.println("Tu nay da co trong tu dien!");
                check = true;
                break;
            }
        }
        if (!check) {
            System.out.print("Nhap nghia cua tu: ");
            String explainWord = scanner.nextLine();
            Dictionary.listWord.add(new Word(addWord, explainWord));
            System.out.println("Them tu thanh cong!");
        }
    }

    /**
     * Ham xoa tu vung.
     * @throws FileNotFoundException catch error
     * @throws IOException catch error
     */
    public void removeWord() throws FileNotFoundException, IOException {
        String undoWord;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap tu can xoa: ");
        undoWord = scanner.nextLine();
        boolean check = false;

        for (Word w : Dictionary.listWord) {
            if (w.getWord_target().equalsIgnoreCase(undoWord)) {
                Dictionary.listWord.remove(w);
                check = true;
                break;
            }
        }
        if (check) {
            System.out.println("Xoa tu thanh cong!");
        } else {
            System.out.println("Tu nay khong co trong tu dien!");
        }
    }

    /**
     * Ham sua doi tu vung.
     * @throws FileNotFoundException catch error
     * @throws IOException catch error
     */
    public void modifyWord() throws FileNotFoundException, IOException {
        System.out.print("Nhap tu can sua: ");
        Scanner scanner = new Scanner(System.in);
        String editWord = scanner.nextLine();
        boolean check = false;
        for (Word w : Dictionary.listWord) {
            if (w.getWord_target().equalsIgnoreCase(editWord)) {
                System.out.print("Nhap tu moi: ");
                String fixedWord = scanner.nextLine();
                w.setWord_target(fixedWord);
                System.out.print("Nhap nghia moi: ");
                String explainWord = scanner.nextLine();
                w.setWord_explain(explainWord);
                check = true;
                break;
            }
        }
        if (check) {
            System.out.println("Sua tu thanh cong!");
        } else {
            System.out.println("Tu nay khong co trong tu dien!");
        }
    }

    /**
     * Ham xuat du lieu ra file dictionaries_new.txt.
     * @throws FileNotFoundException catch error
     * @throws IOException catch error
     */
    public void dictionaryExportToFile() throws FileNotFoundException, IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("dictionaries_new.txt");
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
            int count = 1;
            String firstLine = "No \t | English \t | Vietnamese";
            bufferedOutputStream.write(firstLine.getBytes());
            bufferedOutputStream.write(System.lineSeparator().getBytes());
            for (Word w : Dictionary.listWord) {
                String stringLine = count + " \t | " + w.getWord_target() + " \t | " + w.getWord_explain();
                bufferedOutputStream.write(stringLine.getBytes());
                bufferedOutputStream.write(System.lineSeparator().getBytes());
                count++;
            }
        }
    }
}
