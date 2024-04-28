package com.example.paccern;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;

public class BlueGhost extends Pane {

    private GameBoard gameBoard;
    private ImageView imageView;
    private int x, y;
    private final int tileSize = 20;
    private final Random random = new Random();

    public BlueGhost(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        Image image = new Image(getClass().getResourceAsStream("/BlueGhost.png")); // Ensure you have a BlueGhost.png
        imageView = new ImageView(image);
        imageView.setFitWidth(tileSize);
        imageView.setFitHeight(tileSize);
        getChildren().add(imageView);
    }

    public void randomMove() {
        // Attempt to move in a random direction
        int attempt = random.nextInt(4); // 0=Up, 1=Right, 2=Down, 3=Left
        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};

        int newX = this.x + dx[attempt];
        int newY = this.y + dy[attempt];

        // Check if the new position is within bounds and not a wall
        if (!gameBoard.isWall(newX, newY) && newX >= 0 && newY >= 0 && newX < gameBoard.getWidth() && newY < gameBoard.getHeight()) {
            this.x = newX;
            this.y = newY;
        }

        updatePosition();
    }

    public int getCurrentX() {
        return (int) imageView.getX() / tileSize;
    }

    public int getCurrentY() {
        return (int) imageView.getY() / tileSize;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        updatePosition();
    }

    private void updatePosition() {
        imageView.setX(x * tileSize);
        imageView.setY(y * tileSize);
    }

}