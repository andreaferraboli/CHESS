package main.chess69.Pieces;


import main.chess69.Game;
import main.chess69.Position;
import main.chess69.Square;

import java.util.ArrayList;

public class Rook extends Type {

    public Rook(ArrayList<Position> possibleMoves) {
        super(possibleMoves);
    }

    public Rook() {
    }

    @Override
    public void getAllPossibleMoves(Position position) {
        int x = position.row;
        int y = position.colomn;
        this.possibleMoves = new ArrayList<>();

        int sides = 4;
        boolean nextDiagonal = false;

        for (int i = 1; i <= sides; i++) {
            do {
                switch (i) {
                    case 1:
                        x++;
                        break;
                    case 2:
                        y++;
                        break;
                    case 3:
                        x--;
                        break;
                    case 4:
                        y--;
                        break;
                    default:
                        break;
                }
                if (!Square.getSquareById(x, y).occupied || (Square.getSquareById(x, y).occupied && !Square.getSquareById(x, y).getPiece().getColor().equals(Game.currentPlayer.color))) {
                    this.possibleMoves.add(new Position(x, y));
                    nextDiagonal = true;
                }
            } while (x != 8 || y != 8 || !nextDiagonal);
            x = position.row;
            y = position.colomn;
        }


    }
}
