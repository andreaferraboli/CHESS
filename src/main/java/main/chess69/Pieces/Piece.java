package main.chess69.Pieces;


import main.chess69.Position;
import java.awt.*;
public class Piece {
    public Position position;
    public Type type;
    private Color color;

    public Piece() {
    }

    public Piece(Position position, Type type, Color color) {
        this.position = position;
        this.type = type;
        this.color = color;
    }

    public void getAllPossibleMoves() {
        this.type.getAllPossibleMoves(this.position);
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        String piece="";
        if (this.color.equals(Color.BLACK))
            piece+="b";
        else
            piece+="w";
        switch (type.getClass().getName()) {
            case "Bishop" -> piece += "b";
            case "Queen" -> piece += "q";
            case "Rook" -> piece += "r";
            case "King" -> piece += "k";
            case "Pawn" -> piece += "p";
            case "Knight" -> piece += "n";
        }

        return piece;

    }
}
