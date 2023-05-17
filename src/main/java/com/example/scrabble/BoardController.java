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

import static java.lang.Character.toLowerCase;

public class BoardController extends Application {
    @Override
    public void start(Stage stage) throws Exception {

    } //// only choose words that are possible with letters in hand

    public void revertToSavedBoard() {
        ArrayList<Integer> recentPressesXAxis = Driver.recentPressesXAxis;
        ArrayList<Integer> recentPressesYAxis = Driver.recentPressesYAxis;

        for (int i=0; i<recentPressesYAxis.size(); i++) {
            //Driver.board.GetChildren.clear() /////////////////////////////////////
        }
    }

    ////// when button pressed save   Done
    ////// Clear grid new changes
    ////// redraw grid blank spots with buttons
}

