package com.example.scrabble;

import java.util.Random;

public class TurnController {
    static char[] letterHandArrayP1 = new char[5];
    static char[] letterHandArrayP2 = new char[5];

    public static void generateLetterHands() {
        for (int i = 0; i < 5; i++) {
            letterHandArrayP1[i] = getRandomLetter();
        }
        for (int i = 0; i < 5; i++) {
            letterHandArrayP2[i] = getRandomLetter();
        }
    }

    static char getRandomLetter() {
        Random random = new Random();
        char letter = (char) (random.nextInt(26) + 'A');
        return letter;
    }

    public static char getLetterFromHand(int player, int letter) {
        if (player == 1) {
            return letterHandArrayP1[letter - 1];
        }
        if (player == 2) {
            return letterHandArrayP2[letter - 1];
        } else {
            return ' ';
        }
    }

    public static void deleteLetterFromHand(String letter, int player) {
        for (int i = 0; i < 5; i++) {
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
        return;
    }

    static boolean letterHandContains(String letter, int player) {
        for (int i = 0; i < 5; i++) {
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