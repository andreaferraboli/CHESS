package main.chess69;

import java.awt.*;
import java.util.ArrayList;

public class Piece {
    public Position position;
    public ArrayList<Position> possibleMoves;
    public Color color;
    protected Position lastMove;

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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
        getAllPossibleMoves();
    }

    protected Position lastMove() {
        return lastMove;
    }
}

