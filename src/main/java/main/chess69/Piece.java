package main.chess69;

import javafx.scene.Node;

import java.awt.*;
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

    public void getAllPossibleMoves(boolean check) {
    }

    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
        getAllPossibleMoves(true);
    }
    public void tryPosition(Position position) {
        this.position = position;
        getAllPossibleMoves(false);
    }
    public void removeMovesCreateCheck(){
        for (Position position:this.possibleMoves)
            if(createsDiscoveredCheck(position))
                this.possibleMoves.remove(position);
    }
    protected Position lastMove() {
        return lastMove;
    }

    public boolean createsDiscoveredCheck(Position newPosition) {
        // Salva la posizione attuale del pezzo
        Position currentPosition = this.getPosition();
        // Sposta il pezzo alla nuova posizione
        this.tryPosition(newPosition);
        // Trova il re avversario
        Square kingSquare = findKing();
        King king = null;
        if (kingSquare != null) {
            king = (King) kingSquare.getPiece();
            // Verifica se il re avversario è sotto scacco
            boolean check = king.isCheck();
            // Riporta il pezzo alla sua posizione originale
            this.tryPosition(currentPosition);
            // Restituisci il risultato della verifica
            return check;
        }
        return true;
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

