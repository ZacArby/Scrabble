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
import java.util.HashSet;
import java.util.Set;

import static java.lang.Character.toLowerCase;

public class BoardController extends Application {

    @Override
    public void start(Stage stage) throws Exception {

    } //// only choose words that are possible with letters in hand

    public static void revertToSavedBoard() {
        ArrayList<Integer> recentPressesXAxis = Driver.recentPressesXAxis;
        ArrayList<Integer> recentPressesYAxis = Driver.recentPressesYAxis;

        for (int i=0; i<recentPressesYAxis.size(); i++) {
            Driver.grid[recentPressesXAxis.get(i)][recentPressesYAxis.get(i)] = ' ';

            for (Node node : Driver.board.getChildren()) {
                Integer columnIndexProperty = GridPane.getColumnIndex(node);
                Integer rowIndexProperty = GridPane.getRowIndex(node);

                if (columnIndexProperty != null && rowIndexProperty != null
                        && columnIndexProperty == recentPressesYAxis.get(i) && rowIndexProperty == recentPressesXAxis.get(i)) {
                    // Remove the node from the GridPane
                    Driver.board.getChildren().remove(node);
                    break;
                }
            }
            Driver.board.add(new Button(), recentPressesXAxis.get(i), recentPressesYAxis.get(i));
        }
        return;
    }
    /*


    ////// when button pressed save   Done
    ////// Clear grid new changes
    ////// redraw grid blank spots with buttons
}
     */

    ////// when button pressed save   Done
    ////// Clear grid new changes
    ////// redraw grid blank spots with buttons
}

