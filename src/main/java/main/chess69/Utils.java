package main.chess69;

import javafx.scene.layout.GridPane;
import main.chess69.pieces.Piece;

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



}
