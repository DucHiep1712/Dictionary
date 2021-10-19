package com.dictionary.functions;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Dictionary {
    public static ArrayList<Word> listWord = new ArrayList<>();

    /**
     * Sap xep lai thu tu cac tu.
     *
     */
    public void sort() {
        int pos = 0;
        String[] word = new String[listWord.size()];
        Map<String, String> explain = new HashMap<>();

        for (Word a : listWord) {
            explain.put(a.getWord_target(), a.getWord_explain());
            word[pos] = a.getWord_target();
            pos++;
        }

        Arrays.sort(word);

        for (int i = 0; i < pos; i++) {
            listWord.get(i).setWord_target(word[i]);
            listWord.get(i).setWord_explain(explain.get(word[i]));
        }
    }

    /**
     * Tim tu su dung chat nhi phan.
     *
     * @param target tu can tim
     * @return nghia cua tu can tim
     */
    public String searchWord(String target) {
        int mid;
        int l = 0;
        int r = listWord.size() - 1;
        this.sort();

        Word temp = new Word();

        while (l <= r) {
            mid = l + (r - l) / 2;
            temp = listWord.get(mid);
            int comp = temp.getWord_target().compareTo(target);
            if (comp == 0) {
                return temp.getWord_explain();
            }
            if (comp <= 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return "Word not found!";
    }
}
