package main.chess69.Pieces;


import main.chess69.Game;
import main.chess69.Position;
import main.chess69.Square;

import java.util.ArrayList;

public class King extends Type {

    public King() {
    }

    public King(ArrayList<Position> possibleMoves) {
        super(possibleMoves);
    }

    @Override
    public void getAllPossibleMoves(Position position) {
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
            if (Square.getSquareById(move.row, move.colomn) != null) {
                if (Square.getSquareById(move.row, move.colomn).occupied && Square.getSquareById(move.row, move.colomn).getPiece().getColor().equals(Game.currentPlayer.color))
                    continue;
                this.possibleMoves.add(move);
            }
        }


    }
}
