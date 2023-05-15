package com.example.scrabble;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BoardController extends Application { //// only choose words that are possible with letters in hand
    // Define the size of the board
    private static final int BOARD_SIZE = 15;
    // Keep track of the previously clicked button
    private Button previousButton = null;
    String chosenLetter = "empty";

   // @Override
    public void start(Stage stage) throws Exception {

    }


}
