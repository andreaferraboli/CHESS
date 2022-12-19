package main.chess69;


import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(Position position, Color color) {
        super(position, color);
        getAllPossibleMoves();
    }

    @Override
    public void getAllPossibleMoves() {
        int x = position.row;
        int y = position.colomn;
        this.possibleMoves = new ArrayList<>();

        if (this.color.equals(Color.black))
            x -= 1;
        else
            x += 1;
        y -= 1;

        for (int i = 0; i < 3; i++, y++) {
            if (Utils.between(x, 0, 7) && Utils.between(y, 0, 7)) {
                Square squareById = Square.getSquareById(x, y);
                if (y != position.colomn) {
                    if (squareById.occupied)
                        if (!squareById.getPiece().getColor().equals(Game.currentPlayer.color))
                            possibleMoves.add(new Position(x, y));
                    else {
                        //check en passant
                            squareById = Square.getSquareById(this.color.equals(Color.black) ? x + 1 : x - 1, y);
                            if (squareById.getPiece() instanceof Pawn && Position.posToIndex(squareById.getPiece().lastMove().getPos()).equals(new Position(this.color.equals(Color.black) ? x + 2 : x - 2, squareById.col)))
                                possibleMoves.add(new Position(x, y));
                        }
                } else {
                    if (!squareById.occupied) {
                        possibleMoves.add(new Position(x, y));
                        Square nextSquare = Square.getSquareById(this.color.equals(Color.black) ? x - 1 : x + 1, y);
                        if (!nextSquare.occupied)
                            possibleMoves.add(new Position(this.color.equals(Color.black) ? x - 1 : x + 1, y));
                    }
                }
            }
        }

    }
    @Override
    public String toString() {
        return this.color.equals(Color.BLACK) ? "bp" : "wp";
    }


}

