/*
File Name: Culminating Project
Author: Alex Liang
Date Created: June 1, 2026
Date Last Modified: June 1, 2026
*/

// Design imports
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Code imports
import java.util.Scanner;

public class HelloFX extends Application {

    @Override
    public void start(Stage stage) {
        // Title & Subtitle Designs
        Label titleLabel = new Label("MINI CROSSWORD");
        titleLabel.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-text-fill: #2C3E50;");
        
        Label subtitleLabel = new Label("A simple and compact puzzle to test your knowledge!");
        subtitleLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #7F8C8D;");

        // Menu Buttons & Design
        Button startButton = new Button("Play Crossword");
        Button exitButton = new Button("Exit Game");

        String menuButtonStyle = "-fx-font-size: 16px; -fx-padding: 10px 20px; -fx-min-width: 150px;";
        startButton.setStyle(menuButtonStyle);
        exitButton.setStyle(menuButtonStyle);

        startButton.setOnAction(e -> {
            showGameBoardScene(stage); // Change Menu to Game Screen via. method (in the works)
            System.out.println("Starting Game: Generating Random Crossword Puzzle");
        });
        
        exitButton.setOnAction(e -> {
            stage.close(); // Close Application via. button
        });

        VBox menuLayout = new VBox(20); 
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setStyle("-fx-background-color: #ECF0F1;");

        menuLayout.getChildren().addAll(titleLabel, subtitleLabel, startButton, exitButton);

        Scene scene = new Scene(menuLayout, 640, 480);
        stage.setScene(scene);
        stage.setTitle("Crossword Game - Main Menu");
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }

}