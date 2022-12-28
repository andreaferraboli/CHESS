package main.chess69;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

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
        getAllPossibleMoves(false);
    }

    public void removeMovesCreateCheck() throws IOException {
        for (Position position : this.possibleMoves)
            if (createsDiscoveredCheck(position))
                this.possibleMoves.remove(position);
    }

    private boolean createsDiscoveredCheck(Position position) throws IOException {
        boolean isChecked;
        GridPane gridPane = Game.getInstance().getBoard();
        Square.getSquareById(this.position.row, this.position.colomn).tryMovePiece(position);
        King king=(King) findKing().getPiece();
        isChecked= king.isCheck();
        Square.getSquareById(this.position.row, this.position.colomn).moveUndo(position,-1);
        Game.getInstance().setBoard(gridPane);
        return isChecked;
    }

    protected Position lastMove() {
        return lastMove;
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

