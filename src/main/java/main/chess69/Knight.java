package main.chess69;



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
            Square squareById = Square.getSquareById(move.row, move.colomn);
            if(squareById != null){
                if (!squareById.occupied)
                    this.possibleMoves.add(move);
                else if (!squareById.getPiece().getColor().equals(Game.currentPlayer.color)) {
                    this.possibleMoves.add(move);
                }
            }
        }


    }

}
