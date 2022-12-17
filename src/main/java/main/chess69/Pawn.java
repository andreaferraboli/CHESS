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
            y -= 1;
        else
            y += 1;
        x -= 1;
        //TODO: implement en passant
        for (int i = 0; i < 3; i++, x++) {
            Square squareById = Square.getSquareById(x, y);
            if (x != position.row) {
                if(squareById.occupied)
                    if (!squareById.getPiece().getColor().equals(Game.currentPlayer.color))
                        possibleMoves.add(new Position(x, y));
                else {
                        squareById = Square.getSquareById(x, this.color.equals(Color.black) ? y + 1 : y - 1);
                        if (squareById.getPiece() instanceof Pawn && Position.posToIndex(squareById.getPiece().lastMove().getPos()).equals(new Position(x, squareById.col - 2)))
                            possibleMoves.add(new Position(x, y));
                    }
            } else {
                if (!squareById.occupied) {
                    possibleMoves.add(new Position(x, y));
                    Square nextSquare = Square.getSquareById(x, this.color.equals(Color.black) ? y - 1 : y + 1);
                    if (!nextSquare.occupied)
                        possibleMoves.add(new Position(x,this.color.equals(Color.black) ? y - 1 : y + 1));
                }
            }
        }

    }


}

