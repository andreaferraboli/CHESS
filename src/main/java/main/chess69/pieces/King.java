package main.chess69.pieces;


import javafx.scene.layout.GridPane;
import main.chess69.Mossa;
import main.chess69.Position;
import main.chess69.Square;
import main.chess69.Utils;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class King extends Piece {

    public boolean checked;

    public King(Position position, Color color) {
        super(position, color);
        checked = false;
    }

    public King() {
    }

    @Override
    public void getAllPossibleMoves(boolean check) throws IOException, CloneNotSupportedException {
        movement();
        if (check)
            removeMovesCreateCheck();
    }

    private void movement() {
        int x = position.getRow();
        int y = position.getColumn();
        ArrayList<Position> moves = new ArrayList<>();
        this.possibleMoves = new ArrayList<>();

        moves.add(new Position(x + 1, y));
        moves.add(new Position(x - 1, y));
        if (this.lastMove == null && !this.checked) {
            moves.add(new Position(x + 2, y));
            moves.add(new Position(x - 2, y));
        }
        moves.add(new Position(x, y + 1));
        moves.add(new Position(x - 1, y + 1));
        moves.add(new Position(x + 1, y + 1));
        moves.add(new Position(x - 1, y - 1));
        moves.add(new Position(x, y - 1));
        moves.add(new Position(x + 1, y - 1));


        for (Position move : moves) {
            if (Utils.between(move.getRow(), 0, 7) && Utils.between(move.getColumn(), 0, 7)) {
                if (Square.getSquareById(move.getRow(), move.getColumn()).hasPiece()) {
                    if (!Square.getSquareById(move.getRow(), move.getColumn()).getPiece().getColor().equals(this.color))
                        this.possibleMoves.add(move);
                } else {
                    //check arrocco
                    if ((move.getRow() == x - 2 || move.getRow() == x + 2) && (this.color.equals(Color.black) ? move.getColumn() == 0 : move.getColumn() == 7)) {
                        Rook rookShort = (Rook) Square.getSquareById(7, y).getPiece();
                        Rook rookLong = (Rook) Square.getSquareById(0, y).getPiece();
                        if (this.lastMove == null)
                            if (rookShort != null)
                                if (rookShort.lastMove == null && !Square.getSquareById(6, y).hasPiece() && !Square.getSquareById(5, y).hasPiece())
                                    //arrocco corto
                                    this.possibleMoves.add(move);
                                else if (rookLong != null) {
                                    //arrocco lungo
                                    if (rookLong.lastMove == null && !Square.getSquareById(1, y).hasPiece() && !Square.getSquareById(2, y).hasPiece() && !Square.getSquareById(3, y).hasPiece())
                                        this.possibleMoves.add(move);
                                }


                    } else {
                        this.possibleMoves.add(move);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return this.color.equals(Color.BLACK) ? "bk" : "wk";
    }

    protected King clone() throws CloneNotSupportedException {
        King clone = new King();
        // Crea un nuovo oggetto Piece con gli stessi valori dei campi dell'oggetto originale
        clone.setColor(this.getColor());
        try {
            clone.setPosition(new Position(this.getPosition().getRow(), this.getPosition().getColumn()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return clone;
    }

    public boolean isCheck(GridPane gridPane) throws IOException {
        //reverse check
        Knight knight = new Knight(this.position, this.color);
        knight.movement(gridPane);
        for (Position position : knight.possibleMoves) {
            if (Square.getSquareById(position.getRow(), position.getColumn(), gridPane).hasPiece())
                if (!Square.getSquareById(position.getRow(), position.getColumn(), gridPane).getPiece().getColor().equals(this.color))
                    if (Square.getSquareById(position.getRow(), position.getColumn(), gridPane).getPiece() instanceof Knight)
                        return true;
        }
        Bishop bishop = new Bishop(this.position, this.color);
        bishop.movement(gridPane);
        for (Position position : bishop.possibleMoves) {
            if (Square.getSquareById(position.getRow(), position.getColumn(), gridPane).hasPiece())
                if (!Square.getSquareById(position.getRow(), position.getColumn(), gridPane).getPiece().getColor().equals(this.color))
                    if (Square.getSquareById(position.getRow(), position.getColumn(), gridPane).getPiece() instanceof Bishop || Square.getSquareById(position.getRow(), position.getColumn(), gridPane).getPiece() instanceof Queen)
                        return true;
        }
        Rook rook = new Rook(this.position, this.color);
        rook.movement(gridPane);
        for (Position position : rook.possibleMoves) {
            if (Square.getSquareById(position.getRow(), position.getColumn(), gridPane).hasPiece())
                if (!Square.getSquareById(position.getRow(), position.getColumn(), gridPane).getPiece().getColor().equals(this.color))
                    if (Square.getSquareById(position.getRow(), position.getColumn(), gridPane).getPiece() instanceof Rook || Square.getSquareById(position.getRow(), position.getColumn(), gridPane).getPiece() instanceof Queen)
                        return true;
        }
        King king = new King(this.position, this.color);
        king.lastMove = new Mossa(this.position.getRow(), this.position.getColumn());
        king.movement();
        for (Position position : king.possibleMoves) {
            if (Square.getSquareById(position.getRow(), position.getColumn(), gridPane).hasPiece())
                if (!Square.getSquareById(position.getRow(), position.getColumn(), gridPane).getPiece().getColor().equals(this.color))
                    if (Square.getSquareById(position.getRow(), position.getColumn(), gridPane).getPiece() instanceof King)
                        return true;
        }
        Pawn pawn = new Pawn(this.position, this.color);
        pawn.movement();
        for (Position position : pawn.possibleMoves) {
            if (Square.getSquareById(position.getRow(), position.getColumn(), gridPane).hasPiece())
                if (!Square.getSquareById(position.getRow(), position.getColumn(), gridPane).getPiece().getColor().equals(this.color))
                    if (Square.getSquareById(position.getRow(), position.getColumn(), gridPane).getPiece() instanceof Pawn && this.position.getRow() != position.getRow())
                        return true;
        }
        king=null;
        knight=null;
        rook=null;
        bishop=null;
        pawn=null;
        return false;
    }


    public void setChecked(boolean checked) {
        this.checked = checked;
    }


}
