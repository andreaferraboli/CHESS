package main.chess69.Pieces;


import main.chess69.Position;

import java.util.ArrayList;

public class Type {
    protected ArrayList<Position> possibleMoves;


    public Type(ArrayList<Position> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }

    public Type() {
    }

    public void getAllPossibleMoves(Position position) {}
}
