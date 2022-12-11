package main.chess69.Pieces;


import main.chess69.Game;
import main.chess69.Position;
import main.chess69.Square;

import java.util.ArrayList;

public class Bishop extends Type {

    public Bishop(ArrayList<Position> possibleMoves) {
        super(possibleMoves);
    }

    public Bishop() {
        super();
    }

    @Override
    public void getAllPossibleMoves(Position position) {
        int x = position.row;
        int y = position.colomn;

        this.possibleMoves = new ArrayList<>();

        int diagonals = 4;
        boolean nextDiagonal = false;

        for (int i = 1; i <= diagonals; i++) {
            do {
                switch (i) {
                    case 1:
                        y++;
                        x++;
                        break;
                    case 2:
                        x--;
                        y++;
                        break;
                    case 3:
                        x--;
                        y--;
                        break;
                    case 4:
                        x++;
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

