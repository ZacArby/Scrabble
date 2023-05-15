package com.example.scrabble;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.*;



/// save the board after each turn
//revert to save if word invalid
public class WordProcessor {

    public static String checkForWord(char[][] grid, Set<String> words) {
        String inputWord = "";
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            throw new IllegalArgumentException("Grid must not be null and must have at least one row and column.");
        }

        String foundWord = "";

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

                        if ((word.equals(rowString) || word.equals(colString)) && word.length() > foundWord.length()) {

                            foundWord = word;
                        }
                    }
                }
            }
        }
        words.remove(foundWord);
        return foundWord;
    }

    public static int calculatePoints(String word) {
        int points = 0;
        String curLetter = "";
        if (word != null) {
            for (int i = 0; i < word.length(); i++) { //String.valueOf(example.charAt(0));
                curLetter = String.valueOf(word.charAt(i));
                System.out.println(String.valueOf(word.charAt(i)));
                if (curLetter.equals("a") || curLetter.equals("e") || curLetter.equals("i") || curLetter.equals("l") ||
                        curLetter.equals("n") || curLetter.equals("o") || curLetter.equals("r") || curLetter.equals("s")
                        || curLetter.equals("t") || curLetter.equals("u")) {
                    points += 1;
                } else if (curLetter.equals("d") || curLetter.equals("g")) {
                    points += 2;
                } else if (curLetter.equals("b") || curLetter.equals("c") || curLetter.equals("m") || curLetter.equals("p")) {
                    points += 3;
                } else if (curLetter.equals("f") || curLetter.equals("h") || curLetter.equals("v") || curLetter.equals("w") || curLetter.equals("y")) {
                    points += 4;
                } else if (curLetter.equals("s")) {
                    points += 5;
                } else if (curLetter.equals("j") || curLetter.equals("x")) {
                    points += 8;
                } else if (curLetter.equals("q") || curLetter.equals("z")) {
                    points += 10;
                }
            }
        }

        return points;
    }




}
