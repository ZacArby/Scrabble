package com.example.scrabble;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Character.toLowerCase;

public class Driver extends Application {
    // Define the size of the board
    private static final int BOARD_SIZE = 15;
    public static GridPane board = new GridPane();
    // Keep track of the previously clicked button
    private Button previousButton = null;
    String chosenLetter = "empty";
    static char[][] grid = new char[BOARD_SIZE][BOARD_SIZE]; // create a 2D grid
    static Set<String> wordsSet = new HashSet<>(); // create a set of words
    String recentWord;
    String wordStatus = "Invalid";
    int wordPoints = 0;
    Label[] handLetters = new Label[10];

    int playerTurn = 1;

    static ArrayList<Integer> recentPressesXAxis = new ArrayList<>();
    static ArrayList<Integer> recentPressesYAxis = new ArrayList<>();


   // @Override
    public void start(Stage stage) throws Exception {
        TurnController.generateLetterHands();

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
        ///



        // Loop through the rows and columns of the board and create a new button for each spot
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Button button = new Button();

                // Set the size of the button
                button.setPrefSize(50, 50);

                // Add the button to the GridPane at the current row and column
                board.add(button, col, row);
                // Check if inputed word is valid
                button.setOnKeyPressed(e -> {
                    if (e.getCode() == KeyCode.ENTER) {
                        // Word process
                        recentWord = (WordProcessor.checkForWord(grid, wordsSet));
                        wordPoints = WordProcessor.calculatePoints(recentWord);

                        System.out.println(recentWord);
                        Stage pointCountStage = new Stage();

                        if (recentWord != "") {
                            wordStatus = "Valid";
                        } else {
                            wordStatus = "Invalid";
                            BoardController.revertToSavedBoard(); //////////////////////
                        }

                        // Create a label and set its text
                        Label label = new Label("Your word is " + wordStatus + "\n Points: " + wordPoints + "\n Word: " + recentWord);


                        // Create a new scene with the label and set it as the stage's scene
                        Scene scene = new Scene(label, 200, 100);
                        pointCountStage.setScene(scene);

                        // Set the window's title and show it
                        pointCountStage.setTitle("Word Checker");
                        pointCountStage.show();
                    }

                });


                // Create hand of letters
                for (int i = 0; i < 5; i++){
                    handLetters[i] = new Label(Character.toString(TurnController.getLetterFromHand(1, i+1)));
                    board.add(handLetters[i], i+5, BOARD_SIZE);
                }


                button.setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        recentPressesXAxis.add(board.getRowIndex(button));
                        recentPressesYAxis.add(board.getColumnIndex(button));
                        // Save Board

                        // Create pop-up
                        // Create a new Stage for the pop-up
                        Stage popupStage = new Stage(); /////////////////////////////////////////// only have letters in hand appear on virtual keyboard

                        // Create a new GridPane to hold the buttons
                        GridPane keyboard = new GridPane();

                        // Add some padding around the buttons
                        keyboard.setPadding(new Insets(10));

                        // Add some spacing between the buttons
                        keyboard.setHgap(5);
                        keyboard.setVgap(5);

                        // Loop through the alphabet and create a new button for each letter
                        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                        int index = 0;
                        for (int rowp = 0; rowp < 3; rowp++) {
                            for (int colp = 0; colp < 9; colp++) {
                                if (index >= alphabet.length()) {
                                    break;
                                }

                                String letter = alphabet.substring(index, index + 1);
                                Button buttonp = new Button(letter);

                                // Set the size of the button
                                buttonp.setPrefSize(50, 50);

                                // Add the button to the GridPane at the current row and column
                                if (TurnController.letterHandContains(letter, playerTurn)) {
                                    keyboard.add(buttonp, colp, rowp);
                                }

                                // Add an action event handler to the button to close the pop-up
                                buttonp.setOnAction(eventp -> {
                                    chosenLetter = ((Button) eventp.getSource()).getText();
                                    Label label = new Label(chosenLetter);
                                    TurnController.deleteLetterFromHand(chosenLetter, playerTurn);
                                    popupStage.close();
                                });

                                index++;
                            }
                        }

                        // Create a new Scene with the GridPane as the root node
                        Scene popupScene = new Scene(keyboard);

                        // Set the title of the pop-up window
                        popupStage.setTitle("Choose a letter");

                        // Set the size of the pop-up window
                        popupStage.setWidth(450);
                        popupStage.setHeight(150);

                        // Set the Scene for the pop-up Stage
                        popupStage.setScene(popupScene);
                        //(Button)event.getSource ()).getText ();
                        // Show the pop-up Stage
                        popupStage.showAndWait();
                        // Create a new label with the inputted letter
                        Label label = new Label(chosenLetter);
                        // Set the size of the label to match the button
                        label.setPrefSize(button.getWidth(), button.getHeight());

                        // Replace the button with the label
                        board.getChildren().remove(button);
                        board.add(label, GridPane.getColumnIndex(button), GridPane.getRowIndex(button));

                        grid[GridPane.getRowIndex(button)][GridPane.getColumnIndex(button)] = toLowerCase((chosenLetter.charAt(0)));

                        // Keep track of the previously clicked button
                        previousButton = button;


                    }
                });
            }
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

