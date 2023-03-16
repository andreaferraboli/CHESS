package main.chess69;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import main.chess69.pieces.King;
import main.chess69.pieces.Piece;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Utils {
    public static boolean between(int num, int min, int max) {
        return num >= min && num <= max;
    }

    public static boolean hasPosition(ArrayList<Position> array, Position object) {
        for (Position position : array)
            if (position.equals(object))
                return true;
        return false;
    }

    public static void tryMovePiece(Square square,Position position, GridPane gridPane) {

        // Aggiorna la posizione del pezzo
        if (square.getPiece() != null) {
            square.getPiece().trySetPosition(position);
            // Imposta il pezzo nella nuova posizione
            trySetPiece(Square.getSquareById(position.getRow(), position.getColumn(), gridPane),square.getPiece());

            trySetPiece(square,null);
        }
    }

    public static void trySetPiece(Square square,Piece piece) {
        try {
            square.setPiece(piece);
        } catch (IOException | CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Square getNodeByCoordinate(int row, int col, GridPane grid) {
        ObservableList<Node> children = grid.getChildren();

        for (Node node : children) {
            if (node instanceof Square square) {
                if (square.col == col && square.row == row) {
                    return square;
                }
            }
        }
        return null;
    }

    public static Square findKing(GridPane gridPane, Color color) {
        // Cerca la casella con il re dello stesso colore
        for (Node node : gridPane.getChildren()) {
            Square square = (Square) node;
            if (square.hasPiece() && square.getPiece() instanceof King && square.getPiece().getColor().equals(color)) {
                return square;
            }
        }
        return null;
    }

}
