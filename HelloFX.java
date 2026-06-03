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

        // Hints
        System.out.println("Across");
        System.out.println("Down");
        
        // Game Board
        char[][] crossword = {
            {=},
            {},
            {},
            {},
            {}
        };

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}