package main.chess69;


import javafx.scene.layout.GridPane;

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
        int x = position.row;
        int y = position.colomn;
        ArrayList<Position> moves = new ArrayList<>();
        this.possibleMoves = new ArrayList<>();

        moves.add(new Position(x + 1, y));
        moves.add(new Position(x - 1, y));
        if (this.lastMove == null) {
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
            if (Utils.between(move.row, 0, 7) && Utils.between(move.colomn, 0, 7)) {
                if (Square.getSquareById(move.row, move.colomn).hasPiece()) {
                    if (!Square.getSquareById(move.row, move.colomn).getPiece().getColor().equals(this.color))
                        this.possibleMoves.add(move);
                } else {
                    //check arrocco
                    if ((move.row == x - 2 || move.row == x + 2) && (this.color.equals(Color.black) ? move.colomn == 0 : move.colomn == 7)) {
                        Rook rookShort = (Rook) Square.getSquareById(7, y).getPiece();
                        Rook rookLong = (Rook) Square.getSquareById(0, y).getPiece();
                        if (this.lastMove == null)
                            if (rookShort != null)
                                if (rookShort.lastMove == null)
                                    //arrocco corto
                                    this.possibleMoves.add(move);
                                else if (rookLong != null) {
                                    //arrocco lungo
                                    if (rookLong.lastMove == null && !Square.getSquareById(move.row - 3, move.colomn).hasPiece())
                                        this.possibleMoves.add(move);
                                }


                    } else {
                        this.possibleMoves.add(move);
                    }
                }
            }
        }
        if (check)
            removeMovesCreateCheck();


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
            clone.setPosition(new Position(this.getPosition().row, this.getPosition().colomn));
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

            if (Square.getSquareById(position.row, position.colomn, gridPane).hasPiece())
                if (!Square.getSquareById(position.row, position.colomn, gridPane).getPiece().getColor().equals(this.color))
                    if (Square.getSquareById(position.row, position.colomn, gridPane).getPiece() instanceof Knight)
                        return true;
        }
        Bishop bishop = new Bishop(this.position, this.color);
        bishop.movement(gridPane);
        for (Position position : bishop.possibleMoves) {
            if (Square.getSquareById(position.row, position.colomn, gridPane).hasPiece())
                if (!Square.getSquareById(position.row, position.colomn, gridPane).getPiece().getColor().equals(this.color))
                    if (Square.getSquareById(position.row, position.colomn, gridPane).getPiece() instanceof Bishop || Square.getSquareById(position.row, position.colomn, gridPane).getPiece() instanceof Queen)
                        return true;
        }
        Rook rook = new Rook(this.position, this.color);
        rook.movement(gridPane);
        for (Position position : rook.possibleMoves) {
            if (Square.getSquareById(position.row, position.colomn, gridPane).hasPiece())
                if (!Square.getSquareById(position.row, position.colomn, gridPane).getPiece().getColor().equals(this.color))
                    if (Square.getSquareById(position.row, position.colomn, gridPane).getPiece() instanceof Rook || Square.getSquareById(position.row, position.colomn, gridPane).getPiece() instanceof Queen)
                        return true;
        }
        return false;
    }


    public void setChecked(boolean checked) {
        this.checked = checked;
    }


}
