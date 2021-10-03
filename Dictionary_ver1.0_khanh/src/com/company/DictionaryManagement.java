package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    protected Dictionary dictionary = new Dictionary();

    public DictionaryManagement() {
        this.insertFromCommandline();
    }

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
            Dictionary.words.add(myWord);
        }
    }
    public void insertFromFile() { // insert vào file
        IODictionaries res = new IODictionaries();
        ArrayList<Word> adds = res.read();
        for(Word add : adds) {
            dictionary.push(add);
        }
    }

    public void addWord(Word word) {
        dictionary.push(word);
        this.saveWordsToFile();
    }

    public void dictionaryLookup() { // thêm hàm lookup
        Scanner scanner = new Scanner(System.in);
        String word_target = scanner.nextLine();
        Word word = dictionary.lookup(word_target);
        System.out.println(word.getExplain());
    }

    public Word dictionaryLookup(String text) {
        return dictionary.lookup(text);
    }

    public void dictionarySearcher() { // thêm hàm dictionarySearcher
        Scanner scanner = new scanner(System.in);
        String word_target = scanner.nextLine();
        ArrayList(Word) words = dictionary.searcher(word_target);

        for(Word word : words) {
            System.out.println(word.getword_target());
            System.out.println(" " + word.getExplain());
        }
    }

    public ArrayList(Word) dictionarySearcher(String searchText) {
        if(searchText.equals(""))
            return new ArrayList<>();
        return dictionary.searcher(searchText);
    }

    public ArrayList<Word> getWords() {
        return dictionary.getWords();
    }
}
