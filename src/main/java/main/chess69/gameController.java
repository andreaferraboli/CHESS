package main.chess69;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.chess69.Pieces.*;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class gameController implements Initializable {
    @FXML
    private static GridPane board;

    private static gameController instance;

    private static Stage primaryStage;
    private static Stage secondaryStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;
        fillBoard();
    }

    public static gameController getInstance() {
        return instance;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        gameController.primaryStage = primaryStage;
    }

    public static Stage getSecondaryStage() {
        return secondaryStage;
    }

    public static void setSecondaryStage(Stage secondaryStage) {
        gameController.secondaryStage = secondaryStage;
    }

    public void addPiece(Piece piece) {
        Objects.requireNonNull(getNodeByCoordinate(piece.position.row, piece.position.colomn)).setPieceImage();
        Objects.requireNonNull(getNodeByCoordinate(piece.position.row, piece.position.colomn)).setPiece(piece);
    }

    public static Square getNodeByCoordinate(int row, int column) {
        for (Node node : board.getChildren()) {
            if (board.getColumnIndex(node) == row && board.getColumnIndex(node) == column) {
                return (Square) node;
            }
        }
        return null;
    }

    private void fillBoard() {
        //create rooks
        instance.addPiece(new Piece(new Position(0, 0), new Rook(), Color.WHITE));
        instance.addPiece(new Piece(new Position(0, 7), new Rook(), Color.white));
        instance.addPiece(new Piece(new Position(7, 0), new Rook(), Color.black));
        instance.addPiece(new Piece(new Position(7, 7), new Rook(), Color.black));
        //create bishops
        instance.addPiece(new Piece(new Position(0, 5), new Bishop(), Color.white));
        instance.addPiece(new Piece(new Position(0, 2), new Bishop(), Color.white));
        instance.addPiece(new Piece(new Position(7, 5), new Bishop(), Color.black));
        instance.addPiece(new Piece(new Position(7, 2), new Bishop(), Color.black));
        //crete knights
        instance.addPiece(new Piece(new Position(0, 6), new Knight(), Color.white));
        instance.addPiece(new Piece(new Position(0, 1), new Knight(), Color.white));
        instance.addPiece(new Piece(new Position(7, 6), new Knight(), Color.black));
        instance.addPiece(new Piece(new Position(7, 1), new Knight(), Color.black));
        //create kings
        instance.addPiece(new Piece(new Position(7, 4), new King(), Color.black));
        instance.addPiece(new Piece(new Position(0, 4), new King(), Color.white));
        //create queens
        instance.addPiece(new Piece(new Position(7, 3), new Queen(), Color.black));
        instance.addPiece(new Piece(new Position(0, 3), new Queen(), Color.white));
        //create pawns
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0)
                    instance.addPiece(new Piece(new Position(1, j), new Pawn(), Color.white));
                else
                    instance.addPiece(new Piece(new Position(6, j), new Pawn(), Color.black));

            }

        }

    }
}
