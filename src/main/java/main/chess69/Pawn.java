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
            y += 1;
        else
            y -= 1;
        x -= 1;

        for (int i = 0; i < 3; i++, x++) {
            if (Utils.between(x, 0, 7) && Utils.between(y, 0, 7)) {
                Square squareById = Square.getSquareById(x, y);
                //non sono nella stessa colonna del pezzo
                if (i != 1) {
                    //controllo se posso mangiare
                    if (squareById.occupied)
                        if (!squareById.getPiece().getColor().equals(this.color))
                            possibleMoves.add(new Position(x, y));
                        else {
                            //check en passant right
                            squareById = Square.getSquareById(x - 1, y);
                            if (squareById.getPiece() instanceof Pawn && squareById.getPiece().lastMove().equals(new Position(squareById.row, squareById.getPiece().getColor().equals(Color.black) ? squareById.col - 2 : squareById.col + 2)))
                                possibleMoves.add(new Position(y, x));
                            //check en passant left
                            squareById = Square.getSquareById(x + 1, y);
                            if (squareById.getPiece() instanceof Pawn && squareById.getPiece().lastMove().equals(new Position(squareById.row, squareById.getPiece().getColor().equals(Color.black) ? squareById.col - 2 : squareById.col + 2)))
                                possibleMoves.add(new Position(y, x));
                        }
                } else {
                    if (!squareById.occupied) {
                        possibleMoves.add(new Position(x, y));
                        Square nextSquare = Square.getSquareById(x, this.color.equals(Color.black) ? y + 1 : y - 1);
                        if (this.lastMove == null && nextSquare.getPiece() == null)
                            possibleMoves.add(new Position(x, this.color.equals(Color.black) ? y + 1 : y - 1));

                    }
                }
            }
        }

    }

    @Override
    public String toString() {
        return this.color.equals(Color.BLACK) ? "bp" : "wp";
    }

    public void setPosition(Position position) {
        this.position = position;
        getAllPossibleMoves();
    }
}

