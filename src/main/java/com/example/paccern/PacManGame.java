package com.example.paccern;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PacManGame extends Application {

    GameBoard gameBoard = new GameBoard();


    private static final int Høyde= 400;
    private static final int Lengde = 400;
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, Høyde, Lengde);

        root.getChildren().add(gameBoard);

        RedGhost redGhost = new RedGhost(gameBoard);
        PacMan pacman = new PacMan(gameBoard);
        BlueGhost blueGhost = new BlueGhost(gameBoard);
        blueGhost.setPosition(6,6);
        pacman.setPosition(1,1);
        redGhost.setPosition(10,10);
        root.getChildren().add(blueGhost);
        root.getChildren().add(pacman);
        root.getChildren().add(redGhost);




        //beveging av pacman

        scene.setOnKeyPressed(e -> {

            if (e.getCode() == KeyCode.UP) {
                pacman.move(0,-1);
            }
            else if (e.getCode() == KeyCode.DOWN) {

                pacman.move(0,1);
            }
            else if (e.getCode() == KeyCode.LEFT) {

                pacman.move(-1,0);
            }
            else if (e.getCode() == KeyCode.RIGHT) {

                pacman.move(1, 0);
            }
        });
        //loopen av spillet for spøkelsene og finne når spillet skal lukkes



        AnimationTimer gameLoop = new AnimationTimer() {

            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 200_000_000) { // Update every 0.2 seconds
                    redGhost.chasePacMan(pacman);
                    blueGhost.randomMove();
                    lastUpdate = now;

                    System.out.println("Score: " + gameBoard.getScore());

                    // Collision with RedGhost
                    if (pacman.getCurrentX() == redGhost.getCurrentX() && pacman.getCurrentY() == redGhost.getCurrentY()) {
                        if (!gameBoard.powerupMode) {
                            System.out.println("Game Over!");
                            this.stop();
                            Platform.exit();
                        } else {
                            root.getChildren().remove(redGhost); //
                            // respawn ghost her
                        }
                    }

                    // Collision with BlueGhost
                    if (pacman.getCurrentX() == blueGhost.getCurrentX() && pacman.getCurrentY() == blueGhost.getCurrentY()) {
                        if (!gameBoard.powerupMode) {
                            System.out.println("Game Over!");
                            this.stop();
                            Platform.exit();
                        } else {
                            root.getChildren().remove(blueGhost); //
                            // respawn ghost her
                        }
                    }

                    if (gameBoard.getScore() == 298) {
                        System.out.println("You Win!");
                        this.stop();
                        Platform.exit();
                    }
                }
            }
        };


        gameLoop.start();

        primaryStage.setTitle("Pac-Man");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public void activatePowerUpMode() {
        gameBoard.powerupMode = true;
        PauseTransition powerUpDuration = new PauseTransition(Duration.seconds(10));
        powerUpDuration.setOnFinished(event -> {
            gameBoard.powerupMode = false;
        });
        powerUpDuration.play();
    }



    public static void main(String[] args) {
        launch(args);
    }
}