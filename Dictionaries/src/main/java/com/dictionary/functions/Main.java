package com.dictionary.functions;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

//        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
//        dictionaryCommandline.dictionaryBasic();
//        dictionaryCommandline.dictionaryAdvanced();
        DictionaryApplication dictionaryApplication = new DictionaryApplication();
        dictionaryApplication.runApplication(args);
    }
}
