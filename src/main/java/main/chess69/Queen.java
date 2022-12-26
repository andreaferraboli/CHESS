package main.chess69;


import java.awt.*;
import java.util.ArrayList;

public class Queen extends Piece {
    public Queen() {
    }

    public Queen(Position position, Color color) {
        super(position, color);
        getAllPossibleMoves();
    }

    @Override
    public void getAllPossibleMoves() {

        this.possibleMoves = new ArrayList<>();

        //queen is like having a bishop and a rook
        Bishop bishop = new Bishop(this.position, this.getColor());
        possibleMoves.addAll(bishop.possibleMoves);
        Rook rook = new Rook(this.position, this.getColor());
        possibleMoves.addAll(rook.possibleMoves);


    }

    @Override
    public String toString() {
        return this.color.equals(Color.BLACK) ? "bq" : "wq";
    }

    public void setPosition(Position position) {
        this.position = position;
        getAllPossibleMoves();
    }
}
