package main.chess69;


import java.awt.*;
import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(Position position, Color color) {
        super(position, color);
        getAllPossibleMoves();
    }
    public Bishop() {
        super();
    }

    @Override
    public void getAllPossibleMoves() {
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

