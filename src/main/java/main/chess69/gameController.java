package main.chess69;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static main.chess69.GameMain.primaryStage;

public class gameController {
    private static gameController instance;

    @FXML
    GridPane board;

    @FXML
    Button newGameButton;
    @FXML
    Button cancelMoveButton;
    @FXML
    ListView<String> movesListView;

    public static gameController getInstance() {
        return instance;
    }

    public static void setInstance(gameController instance) {
        gameController.instance = instance;
    }

    public void initialize() throws IOException, CloneNotSupportedException {
        instance = this;
        Game game = new Game(board, movesListView);
        instance.newGameButton.setOnAction(actionEvent -> {
            URL url = null;
            try {
                url = new File("src/main/resources/main/chess69/game.fxml").toURI().toURL();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            Parent root = null;
            try {
                root = FXMLLoader.load(url);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        });
        instance.cancelMoveButton.setOnAction(actionEvent -> {
            GridPane board = Game.getInstance().getBoard();
            Game currentgame = Game.getInstance();
            Player currentPlayer = Game.getInstance().getCurrentPlayer();
            Mossa lastMove;
            int checkPawn;
            int index = Game.getInstance().movesListView.getItems().size() - 1;
            if (currentPlayer.color.equals(Color.black)) {
                lastMove = Game.getInstance().white.lastMove;
                checkPawn = 6;
                Game.getInstance().movesListView.getItems().remove(index);
                Game.getInstance().mossePartita.remove(index);
            } else {
                lastMove = Game.getInstance().black.lastMove;
                checkPawn = 1;
                Game.getInstance().mossePartita.get(index).setMossa2(null);
                Game.getInstance().movesListView.getItems().set(index, Game.getInstance().mossePartita.get(index).toString());
            }

            Square squareById = Square.getSquareById(lastMove.pezzo.position.getRow(), lastMove.pezzo.position.getColumn());
            try {
                squareById.moveUndo(lastMove.pezzo.lastMove, checkPawn);
            } catch (IOException | CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
            Square.removeCheckEffect();
            squareById.refreshAllPossibleMoves(false);
            if (currentPlayer.color.equals(Color.black))
                Game.getInstance().setCurrentPlayer(Game.getInstance().white);
            else
                Game.getInstance().setCurrentPlayer(Game.getInstance().black);
            instance.cancelMoveButton.setDisable(true);

        });
    }

    public Button getCancelMoveButton() {
        return cancelMoveButton;
    }

    public void setCancelMoveButton(Button cancelMoveButton) {
        this.cancelMoveButton = cancelMoveButton;
    }
}
