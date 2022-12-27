package main.chess69;

import javafx.scene.Node;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Piece {
    public Position position;
    public ArrayList<Position> possibleMoves;
    public Color color;
    protected Position lastMove;

    public Piece() {
    }

    public Piece(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public void getAllPossibleMoves(boolean check) throws IOException {
    }

    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) throws IOException {
        this.position = position;
        getAllPossibleMoves(true);
    }
    public boolean tryPosition(Position position) throws IOException {
        Position startPosition = this.position;
        Piece piece=this;
        Square.getSquareById(startPosition.row, startPosition.colomn).movePiece(position);
        boolean bool=Square.getSquareById(startPosition.row, startPosition.colomn).pieceMakeCheck();
        Square.getSquareById(position.row, position.colomn).moveUndo(startPosition,-1);
        return bool;
    }
    public void removeMovesCreateCheck() throws IOException {
        for (Position position:this.possibleMoves)
            if(createsDiscoveredCheck(position))
                this.possibleMoves.remove(position);
    }
    protected Position lastMove() {
        return lastMove;
    }

    public boolean createsDiscoveredCheck(Position newPosition) throws IOException {
        // Salva la posizione attuale del pezzo
        // Sposta il pezzo alla nuova posizione
        return this.tryPosition(newPosition);

    }

    private Square findKing() {
        // Cerca la casella con il re dello stesso colore
        for (Node node : Game.getInstance().getBoard().getChildren()) {
            Square square = (Square) node;
            if (square.hasPiece() && square.getPiece() instanceof King && square.getPiece().getColor() == this.color) {
                return square;
            }
        }
        return null;
    }
}

