package main.chess69.Pieces;


import main.chess69.Position;

import java.util.ArrayList;

public class Queen extends Type {
    public Queen() {
    }

    public Queen(ArrayList<Position> possibleMoves) {
        super(possibleMoves);
    }

    @Override
    public void getAllPossibleMoves(Position position) {

        this.possibleMoves = new ArrayList<>();

        //queen is like having a bishop and a rook
        Bishop bishop=new Bishop();
        bishop.getAllPossibleMoves(position);
        possibleMoves.addAll(bishop.possibleMoves);
        Rook rook=new Rook();
        rook.getAllPossibleMoves(position);
        possibleMoves.addAll(rook.possibleMoves);


    }
}
