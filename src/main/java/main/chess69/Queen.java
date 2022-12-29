package main.chess69;


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
    public void getAllPossibleMoves(boolean check) throws IOException {

        this.possibleMoves = new ArrayList<>();

        //queen is like having a bishop and a rook
        Bishop bishop = new Bishop(this.position, this.getColor());
        bishop.movement(Game.getInstance().getBoard());
        possibleMoves.addAll(bishop.possibleMoves);
        Rook rook = new Rook(this.position, this.getColor());
        rook.movement(Game.getInstance().getBoard());
        possibleMoves.addAll(rook.possibleMoves);

        if(check)
            removeMovesCreateCheck();

    }

    @Override
    public String toString() {
        return this.color.equals(Color.BLACK) ? "bq" : "wq";
    }


}
