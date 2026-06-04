/*
File Name: Culminating Project
Author: Alex Liang
Date Created: June 1, 2026
Date Last Modified: June 1, 2026
*/

// Design imports
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

// Code imports
import java.util.Scanner;

public class HelloFX extends Application {

    @Override
    public void start(Stage stage) {
        // Graphic Design
        Label l = new Label("test");
        Scene scene = new Scene(new StackPane(l), 640, 480);

        // Game Code
        
        // Game Board
        char[][] gameBoard = {
            {'G', 'U', 'A', 'F', 'F'},
            {'U', 'N', 'T', 'I', 'L'},
            {'T', 'I', 'T', 'L', 'E'},
            {'S', 'T', 'E', 'E', 'D'},
            {'Y', 'A', 'R', 'D', '#'}
        };

        // Player Board
        char[][] playerBoard = {
            {' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' '}
        };

        // Hints
        System.out.println("Across");
        System.out.println("American tennis player Coco, winner of the 2025 French Open");
        System.out.println("No later than");
        System.out.println("Prizefighter's quest");
        System.out.println("Pegasus, for one");
        System.out.println("Lawn party locale");
        
        System.out.println("Down");
        System.out.println("Full of moxie");
        System.out.println(" \"Sweet love\" singer Baker");
        System.out.println("This is _____ nonsense!");
        System.out.println("Submitted a tax return");
        System.out.println("Skipped town in a hurry");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}