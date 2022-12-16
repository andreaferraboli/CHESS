package main.chess69;


import java.awt.*;
import java.util.ArrayList;

public class Rook extends Piece {


    public Rook(Position position, Color color) {
        super(position, color);
        getAllPossibleMoves();
    }

    @Override
    public void getAllPossibleMoves() {
        int x = this.position.row;
        int y = this.position.colomn;
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
                Square squareById = Square.getSquareById(x, y);

                if (!squareById.occupied || !squareById.getPiece().getColor().equals(Game.currentPlayer.color)) {
                    this.possibleMoves.add(new Position(x, y));
                    nextDiagonal = true;
                }
            } while (x != 7 && y != 7 || !nextDiagonal);
            x = position.row;
            y = position.colomn;
        }


    }
}
