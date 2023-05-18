package com.example.scrabble;

import java.util.*;

/**
 * The WordProcessor class provides methods for word processing in a Scrabble game.
 * It allows checking for valid words in a grid and calculating the points for a given word.
 *
 * @author Zachary Arbuthnot
 * @version 05/17/23
 */
public class WordProcessor {

    /**
     * Checks if a word can be formed in the given grid and returns the longest found word.
     *
     * @param grid  The grid of characters.
     * @param words The set of words to check against.
     * @return The longest word found in the grid, or an empty string if no word is found.
     * @throws IllegalArgumentException if the grid is null or has zero rows or columns.
     */
    public static String checkForWord(char[][] grid, Set<String> words) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            throw new IllegalArgumentException("Grid must not be null and must have at least one row and column.");
        }

        String foundWord = "";

        for (String word : words) {
            // Check if the word length is within the grid's dimensions
            if (word.length() > grid.length || word.length() > grid[0].length) {
                continue;
            }

            // Iterate over each cell in the grid
            for (int i = 0; i <= grid.length - word.length(); i++) {
                for (int j = 0; j <= grid[0].length - word.length(); j++) {
                    if (grid[i][j] == word.charAt(0)) {
                        // Check if the word can be formed horizontally (row) or vertically (column)
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

        // Remove the found word from the set of words
        words.remove(foundWord);

        return foundWord;
    }

    /**
     * Calculates the points for a given word based on Scrabble letter values.
     *
     * @param word The word for which to calculate the points.
     * @return The total points for the word.
     */
    public static int calculatePoints(String word) {
        int points = 0;

        if (word != null) {
            for (int i = 0; i < word.length(); i++) {
                String curLetter = String.valueOf(word.charAt(i));

                // Assign points based on letter values
                switch (curLetter) {
                    case "a", "e", "i", "l", "n", "o", "r", "s", "t", "u" -> points += 1;
                    case "d", "g" -> points += 2;
                    case "b", "c", "m", "p" -> points += 3;
                    case "f", "h", "v", "w", "y" -> points += 4;
                    case "k" -> points += 5;
                    case "j", "x" -> points += 8;
                    case "q", "z" -> points += 10;
                }
            }
        }

        return points;
    }
}

