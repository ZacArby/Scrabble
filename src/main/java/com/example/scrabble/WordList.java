package com.example.scrabble;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * The WordList class provides methods to read and process a word list file.
 *
 * @author Zachary Arbuthnot
 * @version 05/17/23
 */
public class WordList {

    /**
     * Reads the word list file and returns a set of words.
     *
     * @return the set of words from the word list file
     */
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
