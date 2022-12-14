package main.chess69.Pieces;


import main.chess69.Game;
import main.chess69.Position;
import main.chess69.Square;
import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Piece{

    public Pawn(Position position, Color color) {
        super(position, color);
        getAllPossibleMoves();
    }

    @Override
    public void getAllPossibleMoves() {
        int x = position.row;
        int y = position.colomn;
        this.possibleMoves = new ArrayList<>();

        if (Square.getSquareById(position.row,position.colomn).getPiece().getColor().equals(Color.BLACK))
            y-=1;
        else
            y+=1;
        x-=1;
        //TODO: implement en passant
        for (int i = 0; i < 3; i++,x++) {
            if (x!= position.row)
                if (!Square.getSquareById(position.row,position.colomn).getPiece().getColor().equals(Game.currentPlayer.color))
                    possibleMoves.add(new Position(x,y));
            else
                if (!Square.getSquareById(position.row,position.colomn).occupied)
                    possibleMoves.add(new Position(x,y));

        }


    }


}
