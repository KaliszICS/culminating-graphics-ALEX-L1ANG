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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

// Text File Imports (For The Crossword Presets)
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class HelloFX extends Application {

    // Game Size Setting (5x5 Crossword)
    final int gameSize = 5;

    // Storing game asset arrays
    char[][] gameBoard;
    String[] acrossClues = new String[5]; // Stores the 5 row clues for the given crossword puzzle
    String[] downClues = new String[5];   // Same thing as above but for the columns
    TextField[][] playerInputs = new TextField[5][5]; // UI framework

    Scanner input = null;

    @Override
    public void start(Stage stage) {
        File crosswords = new File("crosswords.txt");
        try {
            input = new Scanner(crosswords);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  

        // Loading text file (crosswords.txt) for the game
        loadPuzzlesFromFile();

        // Title & Subtitle Designs
        Label titleLabel = new Label("MINI CROSSWORD");
        titleLabel.setFont(new Font("Arial", 32));
        
        Label subtitleLabel = new Label("A simple and compact puzzle to test your knowledge!");
        subtitleLabel.setFont(new Font("Arial", 32));

        // Menu Buttons & Design
        Button startButton = new Button("Play Crossword");
        Button exitButton = new Button("Exit Game");

        String menuButtonStyle = "-fx-font-size: 16px; -fx-padding: 10px 20px; -fx-min-width: 150px;";
        startButton.setStyle(menuButtonStyle);
        exitButton.setStyle(menuButtonStyle);

              

        startButton.setOnAction(e -> {
            showGameBoardScene(stage); // Change Menu to Game Screen via. method
        });
        
        exitButton.setOnAction(e -> {
            stage.close(); // Close Application via. button
        });

        VBox menuLayout = new VBox(20); 
        menuLayout.setAlignment(Pos.CENTER);

        menuLayout.getChildren().addAll(titleLabel, subtitleLabel, startButton, exitButton);

        Scene scene = new Scene(menuLayout, 640, 480);
        stage.setScene(scene);
        stage.setTitle("Crossword Game - Main Menu");
        stage.show();
    }

    // Method to take 15 lines for a crossword (board, row and column clues) from the file
    public void loadPuzzlesFromFile() {
            String line;
            int number = input.nextInt();
            Random random = new Random();
            input.nextLine();
            int lineSkips = (15 * random.nextInt(5));
            System.out.println(lineS)
            for (int i = 0; i < lineSkips; i++) {
                System.out.println(input.nextLine())
                ;
            }


            // Loop through blocks of 15 lines as long as complete records exist
                char[][] board = new char[gameSize][gameSize];
                
                // Grabbing rows 2-5 of that same crossword (using index 1-4) for a crossword preset
                for (int i = 0; i < 5; i++) {
                    String nextString = input.nextLine();

                    for (int j = 0; j < 5; j++) {
                        board[i][j] = nextString.charAt(j);
                    }
                }
                
                // Looping 5 times to fetch ROW hints for randomized crossword
                String[] across = new String[5];
                for (int i = 0; i < 5; i++) {
                    across[i] = input.nextLine();
                }
                
                // Looping 5 times to fetch COLUMN hints for randomized crossword
                String[] down = new String[5];
                for (int i = 0; i < 5; i++) {
                    down[i] = input.nextLine();
                }
                
                // Sorts the board, row & column clues into lists
                gameBoard = board;
                acrossClues = across;
                downClues = down;
            
    }

    // Method to switch from menu to game screen (refer to line 41)
    public void showGameBoardScene(Stage stage) {
        VBox gameLayout = new VBox(20);
        gameLayout.setAlignment(Pos.CENTER);

        // Designing Game Title
        Label gameTitle = new Label("Solve the Puzzle!");
        gameTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        HBox cluesContainer = new HBox(40);
        cluesContainer.setAlignment(Pos.CENTER);

        // Pulling Row Clues (across)
        VBox acrossBox = new VBox(3);
        Label acrossTitle = new Label("ACROSS");
        acrossTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        acrossBox.getChildren().add(acrossTitle);
        
        for (int i = 0; i < 5; i++) {
            // Takes clues from the array list for row clues (across)
            Label clue = new Label((i + 1) + ". " + acrossClues[i]);
            clue.setFont(new Font("Arial", 11));
            acrossBox.getChildren().add(clue);
        }

        // Pulling Column Clues (down)
        VBox downBox = new VBox(3);
        Label downTitle = new Label("DOWN");
        downTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        downBox.getChildren().add(downTitle);
        
        for (int i = 0; i < 5; i++) {
            // Takes clues from the array list for column clues (down)
            Label clue = new Label((i + 1) + ". " + downClues[i]);
            clue.setFont(new Font("Arial", 11));
            downBox.getChildren().add(clue);
        }

        cluesContainer.getChildren().addAll(acrossBox, downBox);

        // Game Result Title
        Label gameResultLabel = new Label("Fill in the board to reveal your result.");
        gameResultLabel.setFont(new Font("Arial", 11));

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
                box.setFont(Font.font("Arial", FontWeight.BOLD, 16));

                // Designing black tiles --> null = empty, . = black tile symbol
                if (gameBoard[i][j] == '.') {
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
                    if (gameBoard[i][j] == '.') { // If tile is not blank & is a black tile...
                        if (!text.isEmpty()) {
                            win = false; // Checking that black tiles remain blank/untouched
                        }
                    } else { // Means that the tile was not black (therefore it's a letter tile)
                        if (!text.isEmpty()) {
                            playerLetter = text.toUpperCase().charAt(0);
                        }
                        if (playerLetter != gameBoard[i][j]) {
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
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }

}