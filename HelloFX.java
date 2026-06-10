/*
File Name: Culminating Project
Author: Alex Liang
Date Created: June 1, 2026
Date Last Modified: June 1, 2026
*/

// Graphic Design Imports
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloFX extends Application {

    // Game Board Test
    int gameSize = 5;
    char[][] gameBoard = {
        {'A', 'B', 'C', 'D', 'E'},
        {'A', 'B', 'C', 'D', 'E'},
        {'A', 'B', 'C', 'D', 'E'},
        {'A', 'B', 'C', 'D', 'E'},
        {'A', 'B', 'C', 'D', 'E'}
    };
    TextField[][] playerInputs = new TextField[5][5];

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

    // Method to switch from menu to game screen (refer to line 41)
    public void showGameBoardScene(Stage stage) {
        VBox gameLayout = new VBox(20);
        gameLayout.setAlignment(Pos.CENTER);
        gameLayout.setStyle("-fx-background-color: #ECF0F1;");

        // Designing Game Title
        Label gameTitle = new Label("Solve the Puzzle!");
        gameTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Grid Layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER); // Centering grid position
        grid.setHgap(5); // Horizontal width of gaps between columns
        grid.setVgap(5); // Vertical width of gaps between rows

        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                TextField box = new TextField();
                box.setPrefWidth(45);
                box.setPrefHeight(45);
                box.setAlignment(Pos.CENTER);
                box.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

                playerInputs[i][j] = box;
                grid.add(box, j, i);
            }
        }

        Button backButton = new Button("Back to Menu"); // Back to menu button 
        backButton.setOnAction(e -> start(stage)); // Functionality of back to menu button

        gameLayout.getChildren().addAll(gameTitle, grid, backButton);

        Scene gameScene = new Scene(gameLayout, 640, 480);
        stage.setScene(gameScene);
    }

    public static void main(String[] args) {

        launch();
    }

}