package com.example.scrabble;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * The Driver class represents the entry point for the Scrabble game application. It initializes the game, sets up the
 * user interface, and manages the game state.
 * The class contains various static variables that store game-related information, such as player turns, points, the game
 * board, and word data. It also includes methods for handling events and updating the UI.
 *
 * @author Zachary Arbuthnot
 * @version 05/17/23
 */
public class Driver extends Application {
    public static final int BOARD_SIZE = 15;
    /**
     * The current player's turn.
     */
    static int playerTurn = 1;

    /**
     * Points scored by player 1.
     */
    static int player1Points = 0;

    /**
     * Points scored by player 2.
     */
    static int player2Points = 0;

    /**
     * Points scored by the current player.
     */
    static int currPlayerPoints;

    /**
     * Points earned by the last word played.
     */
    static int wordPoints = 0;

    /**
     * The letter chosen by the player.
     */
    static String chosenLetter = "empty";

    /**
     * The most recent word played.
     */
    static String recentWord;

    /**
     * The status of the most recent word ("Valid" or "Invalid").
     */
    static String wordStatus = "";

    /**
     * The 2D grid representing the Scrabble board.
     */
    static char[][] grid = new char[BOARD_SIZE][BOARD_SIZE];

    /**
     * A set of valid words in the game.
     */
    static Set<String> wordsSet = new HashSet<>();

    /**
     * An array of labels representing the letters in the player's hand.
     */
    static Label[] handLetters = new Label[10];

    /**
     * The x-axis positions of the most recent button presses.
     */
    static ArrayList<Integer> recentPressesXAxis = new ArrayList<>();

    /**
     * The y-axis positions of the most recent button presses.
     */
    static ArrayList<Integer> recentPressesYAxis = new ArrayList<>();

    /**
     * The labels representing the most recent button presses.
     */
    static ArrayList<Label> recentPresses = new ArrayList<>();

    /**
     * The main entry point for the application.
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes the game and sets up the UI.
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) {
        HandController.generateLetterHands();

        // Create a new GridPane to hold the buttons
        GridPane board = new GridPane();

        // Add some padding around the buttons
        board.setPadding(new Insets(10));

        // Add some spacing between the buttons
        board.setHgap(5);
        board.setVgap(5);

        // Set wordsSet
        wordsSet = WordList.setWordsSet();

        // Create 2D grid
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                grid[i][j] = ' ';
            }
        }

        // Loop through the rows and columns of the board and create a new button for each spot
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Button button = new Button();

                // Set the size of the button
                button.setPrefSize(50, 50);

                // Add the button to the GridPane at the current row and column
                board.add(button, col, row);

                // Attach event handlers to the button
                button.setOnKeyPressed(e -> BoardController.setButtonEventHandlers(button, board));
                button.setOnMouseClicked(event -> BoardController.setButtonEventHandlers(button, board));
            }
        }

        // Create hand of letters
        for (int i = 0; i < 5; i++) {
            handLetters[i] = new Label(Character.toString(HandController.getLetterFromHand(playerTurn, i + 1)));
            board.add(handLetters[i], i + 5, BOARD_SIZE);
        }

        // Create a new Scene with the GridPane as the root node
        Scene scene = new Scene(board);

        // Set the title of the window
        stage.setTitle("Scrabble Board");

        // Set the size of the window
        stage.setWidth(800);
        stage.setHeight(1000);

        // Set the Scene for the Stage
        stage.setScene(scene);

        // Show the Stage
        stage.show();
    }

}


