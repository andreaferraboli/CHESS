package main.chess69;


import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(Position position, Color color) {
        super(position, color);
    }

    @Override
    public void getAllPossibleMoves(boolean check) throws IOException {
        movement();
        if(check)
            removeMovesCreateCheck();


    }

    public void movement() {
        int x = position.row;
        int y = position.colomn;
        this.possibleMoves = new ArrayList<>();

        ArrayList<Position> moves = new ArrayList<>();
        moves.add(new Position(x + 2, y + 1));
        moves.add(new Position(x - 2, y + 1));
        moves.add(new Position(x + 1, y + 2));
        moves.add(new Position(x - 1, y + 2));
        moves.add(new Position(x + 2, y - 1));
        moves.add(new Position(x - 2, y - 1));
        moves.add(new Position(x + 1, y - 2));
        moves.add(new Position(x - 1, y - 2));


        for (Position move : moves) {
            Square squareById = Square.getSquareById(move.row, move.colomn);
            if (squareById != null) {
                if (!squareById.hasPiece())
                    this.possibleMoves.add(move);
                else if (!squareById.getPiece().getColor().equals(this.color)) {
                    this.possibleMoves.add(move);
                }
            }
        }
    }

    @Override
    public String toString() {
        return this.color.equals(Color.BLACK) ? "bn" : "wn";
    }


}
