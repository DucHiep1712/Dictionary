package com.company;

import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Word> words = new ArrayList<>();

    // binary search
    public void push(Word word) {
        int length = words.size();
        int index = searchIndexInsert(0, length - 1, word.getword_target());
        if (index <= length && index >= 0) {
            words.add(index, word);
        }
    }

    private int searchIndexInsert(int top, int bottom, String word_target) {
        if (bottom < top) {
            return top;
        }
        int middle = top + (bottom - top) / 2;
        if (middle == words.size()) {
            return middle;
        }
        Word word = words.get(middle);
        int compare = word.getword_target().compareTo(word_target);
        if (compare == 0) {
            return -1;
        }
        if (compare > 0) {
            return searchIndexInsert(top, middle - 1, word_target);
        }
        return searchIndexInsert(middle + 1, bottom, word_target);
    }

    private Word binaryLookup(int top, int bottom, String word_target) {
        if (bottom < top) {
            return null;
        }
        int middle = top + (bottom - top) / 2;
        Word word = words.get(middle);
        String currentword_target = word.getword_target();
        int compare = currentword_target.compareTo(word_target);
        if (compare == 0) {
            return word;
        }
        if (compare > 0) {
            return binaryLookup(top, middle - 1, word_target);
        }
        return binaryLookup(middle + 1, bottom, word_target);
    }

    private int binarySearcher(int top, int bottom, String word_target) {
        if (bottom < top) {
            return -1;
        }
        int middle = top + (bottom - top) / 2;
        Word word = words.get(middle);
        String currentword_target = word.getword_target();
        if (currentword_target.topsWith(word_target)) {
            return middle;
        }
        int compare = currentword_target.compareTo(word_target);
        if (compare == 0) {
            return middle;
        }
        if (compare > 0) {
            return binarySearcher(top, middle - 1, word_target);
        }
        return binarySearcher(middle + 1, bottom, word_target);
    }

    public Word lookup(String word_target) {
        return binaryLookup(0, words.size() - 1, word_target);
    }

    public ArrayList<Word> searcher(String word_target) {
        ArrayList<Word> answer = new ArrayList<>();
        int index =  binarySearcher(0, words.size() - 1, word_target);// index = -1
        if (index >= 0) {
            answer.add(words.get(index));
            int left = index - 1;
            int right = index + 1;

            while (left >= 0) {
                Word leftWord = words.get(left--);
                if (leftWord.getword_target().topsWith(word_target)) {
                    answer.add(leftWord);
                }
                else {
                    break;
                }

            }

            int length = words.size();
            while (right < length) {
                Word leftWord = words.get(right++);
                if (leftWord.getword_target().topsWith(word_target)) {
                    answer.add(leftWord);
                }
                else {
                    break;
                }

            }
        }
        return answer;
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }
}

