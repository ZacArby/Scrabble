package com.example.scrabble;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Objects;
import static com.example.scrabble.Driver.*;
import static java.lang.Character.toLowerCase;


/**
 * The BoardController class controls the behavior of the Scrabble board and its interaction with the user.
 * It handles actions such as placing and replacing tiles, displaying letter selection pop-ups, validating words,
 * calculating points, and managing the player's turn.
 *
 * @author Zachary Arbuthnot
 * @version 05/17/23
 */
public class BoardController extends Application {
    @Override
    public void start(Stage stage) {}
    public static void revertToSavedBoard(GridPane board) {
        ArrayList<Integer> recentPressesXAxis = Driver.recentPressesXAxis;
        ArrayList<Integer> recentPressesYAxis = Driver.recentPressesYAxis;

        for (int i = 0; i < recentPressesYAxis.size(); i++) {
            int x = recentPressesXAxis.get(i);
            int y = recentPressesYAxis.get(i);
            Node target = getNodeFromGridPane(board, x, y);
            grid[x][y] = ' ';

            board.getChildren().remove(target);

            Button button = new Button();
            button.setPrefSize(50, 50);
            board.add(button, y, x);

            // Set the event handlers for the button
            button.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    // Keep track of the previously clicked button
                    setButtonEventHandlers(button, board);
                }
            });

            // Remove the coordinates from the lists after adding the button back
            recentPressesYAxis.remove(i);
            recentPressesXAxis.remove(i);
            i--;
        }
    }

    private static Node getNodeFromGridPane(GridPane gridPane, int row, int col) {
        for (Node node : gridPane.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer colIndex = GridPane.getColumnIndex(node);
            if (rowIndex != null && colIndex != null && rowIndex == row && colIndex == col) {
                return node;
            }
        }
        return null;
    }

    static void setButtonEventHandlers(Button button, GridPane board) {
        button.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                recentPressesXAxis.add(GridPane.getRowIndex(button));
                recentPressesYAxis.add(GridPane.getColumnIndex(button));

                // Create a new Stage for the pop-up
                Stage popupStage = new Stage();

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
                        if (HandController.letterHandContains(letter, playerTurn)) {
                            keyboard.add(buttonp, colp, rowp);
                        }

                        // Add an action event handler to the button to handle letter selection
                        buttonp.setOnAction(eventp -> {
                            chosenLetter = ((Button) eventp.getSource()).getText();
                            Label label = new Label(chosenLetter);
                            HandController.deleteLetterFromHand(chosenLetter, playerTurn);
                            popupStage.close();
                            recentPresses.add(label);
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
                popupStage.showAndWait();

                // Create a new label with the selected letter
                Label label = new Label(chosenLetter);
                // Set the size of the label to match the button
                label.setPrefSize(button.getWidth(), button.getHeight());

                // Replace the button with the label
                board.getChildren().remove(button);
                board.add(label, GridPane.getColumnIndex(button), GridPane.getRowIndex(button));

                grid[GridPane.getRowIndex(button)][GridPane.getColumnIndex(button)] = toLowerCase(chosenLetter.charAt(0));
            }
        });

        button.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                HandController.drawNewLetters(playerTurn);
                recentWord = WordProcessor.checkForWord(grid, wordsSet);
                wordPoints = WordProcessor.calculatePoints(Driver.recentWord);

                // Create a new Stage for displaying word validation and points
                Stage pointCountStage = new Stage();

                if (!Objects.equals(recentWord, "")) {
                    wordStatus = "Valid";
                } else {
                    wordStatus = "Invalid";
                    BoardController.revertToSavedBoard(board);

                    for (int i = 0; i < recentPressesYAxis.size(); i++) {
                        int row = recentPressesXAxis.get(i);
                        int col = recentPressesYAxis.get(i);

                        // Remove the label
                        Label label = recentPresses.get(i);
                        board.getChildren().remove(label);

                        // Create a new button
                        Button newButton = new Button();

                        // Add the button to the grid
                        board.add(button, col, row);
                        setButtonEventHandlers(newButton, board);
                    }
                }

                for (int i = 0; i < 5; i++) {
                    board.getChildren().remove(handLetters[i]);
                    handLetters[i] = new Label(Character.toString(HandController.getLetterFromHand(playerTurn, i + 1)));
                    board.add(handLetters[i], i + 5, BOARD_SIZE);
                }

                if (playerTurn == 1) {
                    player1Points += wordPoints;
                    currPlayerPoints = player1Points;
                } else {
                    player2Points += wordPoints;
                    currPlayerPoints = player2Points;
                }

                // Create a label and set its text
                Label label = new Label("Player: " + playerTurn + "\nYour word is " + wordStatus + "\nPoints: " + wordPoints + "\nWord: " + recentWord + "\nTotal Points: " + currPlayerPoints);

                if (Objects.equals(wordStatus, "Valid")) {
                    if (playerTurn == 1) {
                        playerTurn = 2;
                    } else {
                        playerTurn = 1;
                    }
                    // Reset savedBoard state
                    recentPressesYAxis = new ArrayList<>();
                    recentPressesXAxis = new ArrayList<>();
                }

                // Create a new scene with the label and set it as the stage's scene
                Scene scene = new Scene(label, 200, 100);
                pointCountStage.setScene(scene);

                // Set the window's title and show it
                pointCountStage.setTitle("Word Checker");
                pointCountStage.show();
            }
        });
    }
}

