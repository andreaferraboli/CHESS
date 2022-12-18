package main.chess69;

import java.awt.*;
import java.util.ArrayList;

public class Piece {
    public Position position;
    protected Position lastMove;
    public ArrayList<Position> possibleMoves;
    public Color color;

    public Piece() {
    }

    public Piece(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public void getAllPossibleMoves() {
    }

    public Color getColor() {
        return color;
    }

    protected Position lastMove() {
        return lastMove;
    }


}

