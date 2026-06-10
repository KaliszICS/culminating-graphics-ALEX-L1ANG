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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

// Text File Imports (For The Crossword Presets)
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HelloFX extends Application {

    // Game Size Setting (5x5 Crossword)
    int gameSize = 5;
    
    // Array Lists to store my presets from my text file (crosswords.txt)
    ArrayList<char[][]> allGameBoards = new ArrayList<>();
    ArrayList<String[]> allAcrossClues = new ArrayList<>();
    ArrayList<String[]> allDownClues = new ArrayList<>();

    // State trackers keeping track of the actively loaded puzzle parameters
    char[][] gameBoard;
    String[] acrossClues = new String[5]; // Stores the 5 row clues for the given crossword puzzle
    String[] downClues = new String[5];   // Same thing as above but for the columns
    TextField[][] playerInputs = new TextField[5][5]; // UI framework

    @Override
    public void start(Stage stage) {

        // Loading text file (crosswords.txt) for the game
        loadPuzzlesFromFile();

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
            if (!allGameBoards.isEmpty()) { // Detects if there is a crossword puzzle to being with
                // Takes a random crossword based on index
                int randomIndex = (int) (Math.random() * allGameBoards.size());
                
                // Retrieving the puzzle, and the clues for the selected crossword puzzle
                gameBoard = allGameBoards.get(randomIndex);
                acrossClues = allAcrossClues.get(randomIndex);
                downClues = allDownClues.get(randomIndex);
                
                System.out.println("Starting Game: Randomly Generated Crossword Puzzle");
            } else {
                System.out.println("No puzzles loaded.");
            }
            
            showGameBoardScene(stage); // Change Menu to Game Screen via. method
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

    // Method to take 15 lines for a crossword (board, row and column clues) from the file
    public void loadPuzzlesFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("crosswords.txt"))) {
            String line;
            
            // Loop through blocks of 15 lines as long as complete records exist
            while ((line = br.readLine()) != null) {
                char[][] board = new char[5][5];
                
                // Takes the first row of the crossword (using index 0) for a crossroad preset
                board[0] = line.toCharArray();
                
                // Grabbing rows 2-5 of that same crossword (using index 1-4) for a crossword preset
                for (int i = 1; i < 5; i++) {
                    board[i] = br.readLine().toCharArray();
                }
                
                // Looping 5 times to fetch ROW hints for randomized crossword
                String[] across = new String[5];
                for (int i = 0; i < 5; i++) {
                    across[i] = br.readLine();
                }
                
                // Looping 5 times to fetch COLUMN hints for randomized crossword
                String[] down = new String[5];
                for (int i = 0; i < 5; i++) {
                    down[i] = br.readLine();
                }
                
                // Sorts the board, row & column clues into lists
                allGameBoards.add(board);
                allAcrossClues.add(across);
                allDownClues.add(down);
            }
            System.out.println("Successfully loaded puzzle"); // Confirmation that it worked (testing my file)
        } catch (IOException e) { // Fixing issue where my code would crash (file alignment was wrong?)
            System.out.println("Apologies, there was an error fetching crossword puzzle from text file.");
        }
    }

    // Method to switch from menu to game screen (refer to line 41)
    public void showGameBoardScene(Stage stage) {
        VBox gameLayout = new VBox(20);
        gameLayout.setAlignment(Pos.CENTER);
        gameLayout.setStyle("-fx-background-color: #ECF0F1;");

        // Designing Game Title
        Label gameTitle = new Label("Solve the Puzzle!");
        gameTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        HBox cluesContainer = new HBox(40);
        cluesContainer.setAlignment(Pos.CENTER);

        // Pulling Row Clues (across)
        VBox acrossBox = new VBox(3);
        Label acrossTitle = new Label("ACROSS");
        acrossTitle.setStyle("-fx-font-weight: bold; -fx-text-fill: #2980B9; -fx-font-size: 14px;");
        acrossBox.getChildren().add(acrossTitle);
        
        for (int i = 0; i < 5; i++) {
            // Takes clues from the array list for row clues (across)
            Label clue = new Label((i + 1) + ". " + acrossClues[i]);
            clue.setStyle("-fx-font-size: 11px; -fx-text-fill: #2C3E50;");
            acrossBox.getChildren().add(clue);
        }

        // Pulling Column Clues (down)
        VBox downBox = new VBox(3);
        Label downTitle = new Label("DOWN");
        downTitle.setStyle("-fx-font-weight: bold; -fx-text-fill: #8E44AD; -fx-font-size: 14px;");
        downBox.getChildren().add(downTitle);
        
        for (int i = 0; i < 5; i++) {
            // Takes clues from the array list for column clues (down)
            Label clue = new Label((i + 1) + ". " + downClues[i]);
            clue.setStyle("-fx-font-size: 11px; -fx-text-fill: #2C3E50;");
            downBox.getChildren().add(clue);
        }

        cluesContainer.getChildren().addAll(acrossBox, downBox);

        // Game Result Title
        Label gameResultLabel = new Label("Fill in the board to reveal your result.");
        gameResultLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #34495E;");

        // Grid Layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER); // Centering grid position
        grid.setHgap(5); // Horizontal width of gaps between columns
        grid.setVgap(5); // Vertical width of gaps between rows

        // Creating Individual Boxes For Grid
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                TextField box = new TextField();
                box.setPrefWidth(45);
                box.setPrefHeight(45);
                box.setAlignment(Pos.CENTER);
                box.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

                // Designing black tiles --> null = empty, . = black tile symbol
                if (gameBoard != null && gameBoard[i][j] == '.') {
                    box.setEditable(false);   
                    box.setFocusTraversable(false); 
                    box.setStyle("-fx-background-color: #2C3E50; -fx-border-color: #2C3E50;"); 
                    box.setText(""); 
                } else {
                    box.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-background-color: #FFFFFF; -fx-border-color: #BDC3C7;");
                
                // Listener to limit each box to one UPPERCASED character
                box.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue.length() > 1) {
                        box.setText(oldValue); // Reject the new input and revert to the single letter
                    } else if (!newValue.isEmpty() && !newValue.equals(newValue.toUpperCase())) { // Detecting for lack of input
                box.setText(newValue.toUpperCase()); // Forces all inputs to be uppercased
                    }
                });
            }
                playerInputs[i][j] = box;
                grid.add(box, j, i);
            }
        }

        // Player Board to Game Board Comparison (Win Checking)
        Button checkButton = new Button("Check Answer");
        checkButton.setStyle("-fx-font-size: 14px; -fx-padding: 8px 15px;");

        checkButton.setOnAction(e -> {
            boolean win = true; // Setting win as true, then detecting mistakes --> set win to false

            for (int i = 0; i < gameSize; i++) { // Looping x values (row)
                for (int j = 0; j < gameSize; j++) { // Looping y values (column)
                    String text = playerInputs[i][j].getText().trim(); // Fetches player's input, removes additional spaces
                    char playerLetter = ' '; // Converts to character

                    // Validation 
                    if (gameBoard != null && gameBoard[i][j] == '.') { // If tile is not blank & is a black tile...
                        if (!text.isEmpty()) {
                            win = false; // Checking that black tiles remain blank/untouched
                        }
                    } else { // Means that the tile was not black (therefore it's a letter tile)
                        if (!text.isEmpty()) {
                            playerLetter = text.toUpperCase().charAt(0);
                        }
                        if (gameBoard == null || playerLetter != gameBoard[i][j]) {
                            win = false; 
                        }
                    }
                }
            }

            if (win) {
                gameResultLabel.setText("Congratulations, you've beaten the Mini Crossword!");
                gameResultLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #27AE60; -fx-font-weight: bold;");
            } else {
                gameResultLabel.setText("Sorry, some letters are incorrect.");
                gameResultLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #C0392B; -fx-font-weight: bold;");
            }
        });

        // Return to Menu
        Button backButton = new Button("Back to Menu"); // Back to menu button 
        backButton.setOnAction(e -> start(stage)); // Functionality of back to menu button

        gameLayout.getChildren().addAll(gameTitle, cluesContainer, grid, checkButton, gameResultLabel, backButton);

        Scene gameScene = new Scene(gameLayout, 650, 525);
        stage.setScene(gameScene);
    }

    public static void main(String[] args) {

        launch();
    }

}