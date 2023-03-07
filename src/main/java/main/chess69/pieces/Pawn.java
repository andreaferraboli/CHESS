package main.chess69.pieces;


import main.chess69.*;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(Position position, Color color) {
        super(position, color);
    }

    public Pawn() {
    }

    @Override
    public void getAllPossibleMoves(boolean check) throws IOException, CloneNotSupportedException {
        movement();
        if (check)
            removeMovesCreateCheck();


    }

    protected void movement() {
        int x = position.getRow();
        int y = position.getColumn();
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
                    if (squareById.hasPiece()) {
                        if (!squareById.getPiece().getColor().equals(this.color))
                            possibleMoves.add(new Position(x, y));
                    } else {
                        //check en passant
                        //TODO::non va en passant per il nero
                        if ((this.getColor().equals(Color.WHITE) && this.position.getColumn() == 3)) {
                            squareById = Square.getSquareById(x, 2);
                            if (!squareById.hasPiece()) {
                                squareById = Square.getSquareById(x, 3);
                                if (squareById.getPiece() instanceof Pawn && squareById.getPiece().color.equals(Color.BLACK) && Game.getInstance().black.lastMove.equals(new Mossa(squareById.getPiece().position.getRow(), squareById.getPiece().position.getColumn())))
                                    if (squareById.getPiece().lastMove != null)
                                        if (squareById.getPiece().lastMove.equals(new Mossa(x, 1)))
                                            possibleMoves.add(new Position(x, 2));
                            }
                        } else if (this.getColor().equals(Color.BLACK) && this.position.getColumn() == 4) {
                            squareById = Square.getSquareById(x, 5);
                            if (!squareById.hasPiece()) {
                                squareById = Square.getSquareById(x, 4);
                                if (squareById.getPiece() instanceof Pawn && squareById.getPiece().color.equals(Color.WHITE) && Game.getInstance().white.lastMove.equals(new Mossa(squareById.getPiece().position.getRow(), squareById.getPiece().position.getColumn())))
                                    if (squareById.getPiece().lastMove != null)
                                        if (squareById.getPiece().lastMove.equals(new Position(x, 6)))
                                            possibleMoves.add(new Position(x, 5));
                            }
                        }
                    }
                } else {
                    if (!squareById.hasPiece()) {
                        possibleMoves.add(new Position(x, y));
                        Square nextSquare = Square.getSquareById(x, this.color.equals(Color.black) ? y + 1 : y - 1);
                        if (nextSquare != null)
                            if (this.lastMove == null && nextSquare.getPiece() == null)
                                possibleMoves.add(new Position(x, this.color.equals(Color.black) ? y + 1 : y - 1));

                    }
                }
            }
        }
    }

    protected Pawn clone() throws CloneNotSupportedException {
        Pawn clone = new Pawn();
        // Crea un nuovo oggetto Piece con gli stessi valori dei campi dell'oggetto originale
        clone.setColor(this.getColor());
        try {
            clone.setPosition(new Position(this.getPosition().getRow(), this.getPosition().getColumn()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return clone;
    }

    @Override
    public String toString() {
        return this.color.equals(Color.BLACK) ? "bp" : "wp";
    }


}

