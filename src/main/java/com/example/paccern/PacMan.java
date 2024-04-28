package com.example.paccern;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PacMan extends Pane {

    private GameBoard gameBoard;
    private ImageView imageView;
    private final int tileSize = 20;
    private int x;
    private int y;

    public PacMan(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        Image image = new Image(getClass().getResourceAsStream("/pacman.png"));
        imageView = new ImageView(image);
        imageView.setFitWidth(tileSize);
        imageView.setFitHeight(tileSize);
        getChildren().add(imageView);
    }
    public int getCurrentX() {
        return (int) imageView.getX() / tileSize;
    }

    public int getCurrentY() {
        return (int) imageView.getY() / tileSize;
    }


    public int getX () {

        return x;
    }

    public int getY () {

        return y;
    }



    // setter start posisjon
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        updatePosition();
    }

    // Oppdaterer bildet til pacman
    private void updatePosition() {
        imageView.setX(x * tileSize);
        imageView.setY(y * tileSize);
    }

    public void move(int deltaX, int deltaY) {
        int newX = this.x + deltaX;
        int newY = this.y + deltaY;


        if (!gameBoard.isWall(newX, newY)) {

            if (gameBoard.isDot(newX, newY)) {
                gameBoard.removeDot(newX, newY);
            }
            else if (gameBoard.isPowerup(newX, newY)) {
                gameBoard.removePowerup(newX, newY);
            }
            setPosition(newX, newY);
        }
    }

    // Metoder for å bevege pacman
    public void moveUp() {
        y--;
        updatePosition();
    }

    public void moveDown() {
        y++;
        updatePosition();
    }

    public void moveLeft() {
        x--;
        updatePosition();
    }

    public void moveRight() {
        x++;
        updatePosition();
    }
}