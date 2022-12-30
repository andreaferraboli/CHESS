package main.chess69;


import javafx.scene.layout.GridPane;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(Position position, Color color) {
        super(position, color);
    }

    public Knight() {

    }

    @Override
    public void getAllPossibleMoves(boolean check) throws IOException, CloneNotSupportedException {
        movement(Game.getInstance().getBoard());
        if (check)
            removeMovesCreateCheck();


    }

    public void movement(GridPane gridPane) {
        int x = position.row;
        int y = position.colomn;
        this.possibleMoves = new ArrayList<>();

        ArrayList<Position> moves = new ArrayList<>();
        moves.add(new Position(x + 2, y + 1));
        moves.add(new Position(x - 2, y + 1));
        moves.add(new Position(x + 1, y + 2));
        moves.add(new Position(x - 1, y + 2));
        moves.add(new Position(x + 2, y - 1));
        moves.add(new Position(x - 2, y - 1));
        moves.add(new Position(x + 1, y - 2));
        moves.add(new Position(x - 1, y - 2));


        for (Position move : moves) {
            Square squareById = Square.getSquareById(move.row, move.colomn, gridPane);
            if (squareById != null) {
                if (!squareById.hasPiece())
                    this.possibleMoves.add(move);
                else if (!squareById.getPiece().getColor().equals(this.color)) {
                    this.possibleMoves.add(move);
                }
            }
        }
    }

    protected Knight clone() throws CloneNotSupportedException {
        Knight clone = new Knight();
        // Crea un nuovo oggetto Piece con gli stessi valori dei campi dell'oggetto originale
        clone.setColor(this.getColor());
        try {
            clone.setPosition(new Position(this.getPosition().row, this.getPosition().colomn));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return clone;
    }

    @Override
    public String toString() {
        return this.color.equals(Color.BLACK) ? "bn" : "wn";
    }


}
