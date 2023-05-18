package com.example.scrabble;

import java.util.Random;

/**
 * The HandController class is responsible for managing the letter hands of players in the Scrabble game.
 * It provides methods for generating random letters, drawing new letters, retrieving letters from the hand, deleting
 * letters from the hand, and checking if a letter exists in the hand.
 *
 * @author Zachary Arbuthnot
 * @version 05/17/23
 */
public class HandController {

    /**

     The size of the letter hand for each player.
     */
    private static final int HAND_SIZE = 5;
    /**

     The letter hand array for player 1.
     */
    private static final char[] letterHandArrayP1 = new char[HAND_SIZE];
    /**

     The letter hand array for player 2.
     */
    private static final char[] letterHandArrayP2 = new char[HAND_SIZE];
    /**

     Generates random letters for both players' letter hands.
     */
    public static void generateLetterHands() {
        for (int i = 0; i < HAND_SIZE; i++) {
            letterHandArrayP1[i] = getRandomLetter();
        }
        for (int i = 0; i < HAND_SIZE; i++) {
            letterHandArrayP2[i] = getRandomLetter();
        }
    }
    /**

     Draws new letters for a specific player's letter hand.
     @param playerTurn The player's turn (1 or 2).
     */
    public static void drawNewLetters(int playerTurn) {
        if (playerTurn == 1) {
            for(int i = 0; i < HAND_SIZE; i++) {
                if (letterHandArrayP1[i] == ' ') {
                    letterHandArrayP1[i] = getRandomLetter();
                }
            }
        } else if (playerTurn == 2) {
            for(int i = 0; i < HAND_SIZE; i++) {
                if (letterHandArrayP2[i] == ' ') {
                    letterHandArrayP2[i] = getRandomLetter();
                }
            }
        }
    }
    /**

     Generates a random letter from A to Z.
     @return A random letter.
     */
    private static char getRandomLetter() {
        Random random = new Random();
        return (char) (random.nextInt(26) + 'A');
    }
    /**

     Retrieves a specific letter from a player's letter hand.
     @param player The player number (1 or 2).
     @param letter The index of the letter in the hand.
     @return The letter from the specified player's letter hand.
     */
    public static char getLetterFromHand(int player, int letter) {
        if (player == 1) {
            return letterHandArrayP1[letter - 1];
        } else if (player == 2) {
            return letterHandArrayP2[letter - 1];
        } else {
            return ' ';
        }
    }
    /**

     Deletes a specific letter from a player's letter hand.
     @param letter The letter to be deleted.
     @param player The player number (1 or 2).
     */
    public static void deleteLetterFromHand(String letter, int player) {
        for (int i = 0; i < HAND_SIZE; i++) {
            if (player == 1) {
                if (letterHandArrayP1[i] == letter.charAt(0)) {
                    letterHandArrayP1[i] = ' ';
                    return;
                }
            } else {
                if (letterHandArrayP2[i] == letter.charAt(0)) {
                    letterHandArrayP2[i] = ' ';
                    return;
                }
            }
        }
    }
    /**

     Checks if a specific letter exists in a player's letter hand.
     @param letter The letter to be checked.
     @param player The player number (1 or 2).
     @return True if the letter exists in the player's letter hand, false otherwise.
     */
    static boolean letterHandContains(String letter, int player) {
        for (int i = 0; i < HAND_SIZE; i++) {
            if (player == 1) {
                if (letterHandArrayP1[i] == letter.charAt(0)) {
                    return true;
                }
            } else {
                if (letterHandArrayP2[i] == letter.charAt(0)) {
                    return true;
                }
            }
        }
        return false;
    }
}