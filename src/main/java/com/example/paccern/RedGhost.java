package com.example.paccern;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class RedGhost extends Pane {

    private GameBoard gameBoard;
    private ImageView imageView;
    private int x, y;
    private final int tileSize = 20;


    public RedGhost(GameBoard gameBoard) {
        //vet jeg gjentar kode litt her
        this.gameBoard = gameBoard;
        Image image = new Image(getClass().getResourceAsStream("/RedGhost.png"));
        imageView = new ImageView(image);
        imageView.setFitWidth(tileSize);
        imageView.setFitHeight(tileSize);
        getChildren().add(imageView);
    }

    public void chasePacMan(PacMan pacMan) {
        // sett spøkelse til å jage pacman
        int targetX = pacMan.getCurrentX();
        int targetY = pacMan.getCurrentY();

        int moveX = Integer.compare(targetX, this.x);
        int moveY = Integer.compare(targetY, this.y);

        if (moveX != 0 && !gameBoard.isWall(this.x + moveX, this.y)) {
            this.x += moveX;
        } else if (moveY != 0 && !gameBoard.isWall(this.x, this.y + moveY)) {
            this.y += moveY;
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
