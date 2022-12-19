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
                        if (x != 7)
                            x++;
                        else
                            nextDiagonal = true;
                        break;
                    case 2:
                        if (y != 7)
                            y++;
                        else
                            nextDiagonal = true;
                        break;
                    case 3:
                        if (x != 0)
                            x--;
                        else
                            nextDiagonal = true;
                        break;
                    case 4:
                        if (y != 0)
                            y--;
                        else
                            nextDiagonal = true;
                        break;
                    default:
                        break;
                }
                if (!nextDiagonal) {
                    Square squareById = Square.getSquareById(x, y);

                    if (!squareById.occupied || !squareById.getPiece().getColor().equals(Game.currentPlayer.color))
                        this.possibleMoves.add(new Position(x, y));
                    else
                        nextDiagonal = true;
                }
            } while ((x != 7 && x >= 0 && y >= 0 && y != 7) && !nextDiagonal);
            x = position.row;
            y = position.colomn;
            nextDiagonal = false;
        }
    }

    @Override
    public String toString() {
        return this.color.equals(Color.BLACK) ? "br" : "wr";
    }
}
