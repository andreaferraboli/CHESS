package main.chess69.pieces;


import main.chess69.Game;
import main.chess69.Position;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Queen extends Piece {
    public Queen() {
    }

    public Queen(Position position, Color color) {
        super(position, color);

    }

    @Override
    public void getAllPossibleMoves(boolean check) throws IOException, CloneNotSupportedException {

        this.possibleMoves = new ArrayList<>();

        //queen is like having a bishop and a rook
        Bishop bishop = new Bishop(this.position, this.getColor());
        bishop.movement(Game.getInstance().getBoard());
        possibleMoves.addAll(bishop.possibleMoves);
        Rook rook = new Rook(this.position, this.getColor());
        rook.movement(Game.getInstance().getBoard());
        possibleMoves.addAll(rook.possibleMoves);

        if (check)
            removeMovesCreateCheck();

    }

    protected Queen clone() throws CloneNotSupportedException {
        Queen clone = new Queen();
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
        return this.color.equals(Color.BLACK) ? "bq" : "wq";
    }


}
