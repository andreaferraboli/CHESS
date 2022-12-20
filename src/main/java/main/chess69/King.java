package main.chess69;


import java.awt.*;
import java.util.ArrayList;

public class King extends Piece {

    public King(Position position, Color color) {
        super(position, color);
        getAllPossibleMoves();
    }

    @Override
    public void getAllPossibleMoves() {
        int x = position.row;
        int y = position.colomn;
        ArrayList<Position> moves = new ArrayList<>();
        this.possibleMoves = new ArrayList<>();

        moves.add(new Position(x + 1, y));
        moves.add(new Position(x - 1, y));
        moves.add(new Position(x, y + 1));
        moves.add(new Position(x - 1, y + 1));
        moves.add(new Position(x + 1, y + 1));
        moves.add(new Position(x - 1, y - 1));
        moves.add(new Position(x, y - 1));
        moves.add(new Position(x + 1, y - 1));


        for (Position move : moves) {
            if (Utils.between(move.row, 0, 7) && Utils.between(move.colomn, 0, 7)) {
                if (Square.getSquareById(move.row, move.colomn).hasPiece()) {
                    if (!Square.getSquareById(move.row, move.colomn).getPiece().getColor().equals(this.color))
                        this.possibleMoves.add(move);
                }
                else
                    this.possibleMoves.add(move);
            }
        }


    }
    @Override
    public String toString() {
        return this.color.equals(Color.BLACK) ? "bk" : "wk";
    }
    public void setPosition(Position position) {
        this.position = position;
        getAllPossibleMoves();
    }
}
