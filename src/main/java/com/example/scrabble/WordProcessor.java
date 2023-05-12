package com.example.scrabble;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.*;

public class WordProcessor {

    public static String checkForWord(char[][] grid, Set<String> words) {
        String inputWord = "";
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            throw new IllegalArgumentException("Grid must not be null and must have at least one row and column.");
        }

        for (String word : words) {
            if (word.length() > grid.length || word.length() > grid[0].length) {
                continue;
            }

            for (int i = 0; i <= grid.length - word.length(); i++) {
                for (int j = 0; j <= grid[0].length - word.length(); j++) {
                    if (grid[i][j] == word.charAt(0)) {
                        char[] row = Arrays.copyOfRange(grid[i], j, j + word.length());
                        char[] col = new char[word.length()];

                        for (int k = 0; k < word.length(); k++) {
                            col[k] = grid[i + k][j];
                        }

                        String rowString = new String(row);
                        String colString = new String(col);

                        if (word.equals(rowString) || word.equals(colString)) {
                            return word;
                        }
                    }
                }
            }
        }

        return null;
    }

    public static int calculatePoints(String word) {
        int points = 0;
        if (word != null) {
            for (int i = 0; i < word.length(); i++) { //String.valueOf(example.charAt(0));
                if (String.valueOf(word.charAt(i)) == "a" || word == "e" || word == "i" || word == "l" || word == "n" || word == "o" || word == "r" || word == "s" || word == "t" || word == "u") { ////////////////////////////////////////////////////////not recognising words for some reason
                    points += 1;
                } else if (String.valueOf(word.charAt(i)) == "d" || word == "g") {
                    points += 2;
                } else if (String.valueOf(word.charAt(i)) == "b" || word == "c" || word == "m" || word == "p") {
                    points += 3;
                } else if (String.valueOf(word.charAt(i)) == "f" || word == "h" || word == "v" || word == "w" || word == "y") {
                    points += 4;
                } else if (String.valueOf(word.charAt(i)) == "k") {
                    points += 5;
                } else if (String.valueOf(word.charAt(i)) == "j" || word == "x") {
                    points += 8;
                } else if (String.valueOf(word.charAt(i)) == "q" || word == "z") {
                    points += 10;
                }
            }
        }

        return points;
    }




}
