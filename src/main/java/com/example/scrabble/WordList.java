package com.example.scrabble;

import java.io.*;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class WordList {
    public static Set<String> setWordsSet() {
        Set<String> wordsSet = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(".idea/scrabbleWords.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    wordsSet.add(word.toLowerCase(Locale.ROOT));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordsSet;
    }
}