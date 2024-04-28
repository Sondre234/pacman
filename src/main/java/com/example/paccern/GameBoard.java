package com.example.paccern;

import javafx.animation.PauseTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GameBoard extends Pane {

    private final int tileSize = 20;
    private final int width = 20;
    private final int height = 20;
    boolean powerupMode = false;
    //banen
    private final int [] [] grid = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,2,2,2,2,2,2,2,2,2,2,3,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,2,2,2,2,2,2,2,1,1,1,1,1,2,2,2,2,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,2,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,2,2,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    };

    private int score = 0;

    public void setPowerUpMode(boolean isActive) {
        this.powerupMode = isActive;
        // Optionally, set a timer to turn off power-up mode after a fixed duration
        if (isActive) {
            // Power-up mode lasts for 10 seconds
            PauseTransition powerUpDuration = new PauseTransition(Duration.seconds(10));
            powerUpDuration.setOnFinished(event -> this.powerupMode = false);
            powerUpDuration.play();
        }
    }



    public boolean isDot(int x, int y) {
        return grid[y][x] == 2;
    }

    public void removeDot(int x, int y) {
        if (isDot(x, y)) {
            grid[y][x] = 0;
            score++;
            drawBoard();
        }
    }

    public boolean isPowerup(int x, int y) {
        return grid[y][x] == 3;
    }

    public void removePowerup(int x, int y) {
        if (isPowerup(x, y)) {
            grid[y][x] = 0;
            setPowerUpMode(true);
            drawBoard();
        }
    }

    public int getScore() {
        return score;
    }

    public boolean isWall(int x, int y) {
        return grid[y][x] == 1;
    }

    public GameBoard() {

        drawBoard();
    }

     private void drawBoard() {

         for (int y = 0; y < height; y++) {
             for (int x = 0; x < width; x++) {

                 Rectangle rect = new Rectangle(tileSize, tileSize);
                 Rectangle dot = new Rectangle(tileSize, tileSize);
                 rect.setX(x * tileSize);
                 rect.setY(y * tileSize);

                 switch (grid[y][x]) {
                     case 0:
                         rect.setFill(Color.BLACK);
                         break;
                     case 1:
                         rect.setFill(Color.BLUE);
                         break;
                     case 2:
                         rect.setFill(Color.BLACK);
                         dot.setFill(Color.WHITE);
                         dot.setX(x * tileSize + tileSize * 0.6);
                         dot.setY(y * tileSize + tileSize * 0.6);
                         break;
                     case 3:
                         rect.setFill(Color.BLACK);
                         dot.setFill(Color.BROWN);
                         dot.setX(x * tileSize + tileSize * 0.375);
                         dot.setY(y * tileSize + tileSize * 0.375);
                        break;

                 }
                 this.getChildren().add(rect);
                 this.getChildren().add(dot);

             }
         }

     }

}
