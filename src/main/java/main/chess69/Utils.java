package main.chess69;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

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

    public static List<Square> getDifferentSquares(GridPane gridPane1, GridPane gridPane2) {
        List<Square> differentSquares = new ArrayList<>();
        // Confronta ogni casella dei due GridPane
        for (Node node1 : gridPane1.getChildren()) {
            Square square1 = (Square) node1;
            Square square2 = Square.getSquareById(square1.row, square1.col,gridPane2);
            // Se le caselle sono diverse, aggiungile alla lista delle caselle differenti
            if (!square1.equals(square2)) {
                differentSquares.add(square1);
            }
        }
        return differentSquares;
    }
}
