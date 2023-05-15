package com.example.scrabble;

import java.util.Random;

public class TurnController {
    static char[] letterHandArrayP1 = new char[5];
    static char[] letterHandArrayP2 = new char[5];

    public static void generateLetterHands() {
        for (int i = 0; i < 10; i++) {
            letterHandArrayP1[i] = getRandomLetter();
        }
        for (int i = 0; i < 10; i++) {
            letterHandArrayP2[i] = getRandomLetter();
        }
    }

    static char getRandomLetter() {
        Random random = new Random();
        char letter = (char)(random.nextInt(26) + 'A');
        return letter;
    }

    public static char getLetterFromHand(int player, int letter) {
        if (player == 1) {
            return letterHandArrayP1[letter - 1];
        } if (player == 2) {
            return letterHandArrayP2[letter - 1];
        } else {
            return ' ';
        }
    }
}
