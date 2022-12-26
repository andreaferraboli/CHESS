package main.chess69;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static main.chess69.GameMain.primaryStage;

public class gameController {
    @FXML
    GridPane board;

    @FXML
    ListView<String> movesListView;

    public void initialize() throws IOException {
        Game game = new Game(board, movesListView);
    }


    @FXML
    private void newGame(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/main/chess69/game.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
//
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void cancelMove(ActionEvent event) throws IOException {
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

        Square squareById = Square.getSquareById(lastMove.row, lastMove.colomn);
        squareById.moveUndo(lastMove.pezzo.lastMove, checkPawn);
        Square.removeCheckEffect();
        squareById.deletePiece();
        squareById.refreshAllPossibleMoves();
        if (currentPlayer.color.equals(Color.black))
            Game.getInstance().setCurrentPlayer(Game.getInstance().white);
        else
            Game.getInstance().setCurrentPlayer(Game.getInstance().black);

    }

}
