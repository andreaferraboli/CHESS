package main.chess69.Pieces;



import main.chess69.Game;
import main.chess69.Position;
import main.chess69.Square;

import java.awt.*;
import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(Position position, Color color) {
        super(position, color);
        getAllPossibleMoves();
    }
    @Override
    public void getAllPossibleMoves() {
        int x = position.row;
        int y = position.colomn;
        this.possibleMoves = new ArrayList<>();
        ArrayList<Position> moves = new ArrayList<>();


        moves.add(new Position(x+1,y));
        moves.add(new Position(x-1,y));
        moves.add(new Position(x,y+1));
        moves.add(new Position(x-1,y+1));
        moves.add(new Position(x+1,y+1));
        moves.add(new Position(x-1,y-1));
        moves.add(new Position(x,y-1));
        moves.add(new Position(x+1,y-1));


        for(Position move : moves){
            if(Square.getSquareById(move.row,move.colomn) != null){
                if(!Square.getSquareById(move.row,move.colomn).occupied && !Square.getSquareById(move.row,move.colomn).getPiece().getColor().equals(Game.currentPlayer.color))
                    this.possibleMoves.add(move);
            }
        }


    }

}
