package main.chess69.pieces;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.chess69.Game;
import main.chess69.Mossa;
import main.chess69.Position;
import main.chess69.Square;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Piece {
    public Position position;
    public ArrayList<Position> possibleMoves;
    public Color color;
    public Mossa lastMove;

    public Piece() {
    }

    public Piece(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public void getAllPossibleMoves(boolean check) throws IOException, CloneNotSupportedException {
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) throws IOException, CloneNotSupportedException {
        this.position = position;
        getAllPossibleMoves(false);
    }

    public void trySetPosition(Position position) {
        this.position = position;
    }

    public void removeMovesCreateCheck() throws IOException, CloneNotSupportedException {
        ArrayList<Position> filteredList = new ArrayList<>();
        for (Position position : this.possibleMoves)
            if (!createsDiscoveredCheck(position))
                filteredList.add(position);

        //controllo arrocco, se il re è sotto scacco durante il movimento non può fare l'arrocco
        if (this instanceof King && this.lastMove == null) {
            if (!filteredList.contains(new Position(this.position.getRow() + 1, this.position.getColumn())))
                filteredList.remove(new Position(this.position.getRow() + 2, this.position.getColumn()));
            if (!filteredList.contains(new Position(this.position.getRow() - 1, this.position.getColumn())))
                filteredList.remove(new Position(this.position.getRow() - 2, this.position.getColumn()));
        }

        this.possibleMoves = filteredList;
    }

    private boolean createsDiscoveredCheck(Position position) throws IOException, CloneNotSupportedException {
        //this instanceof Bishop && position.getRow()==4 && position.getColumn()==5 && this.position.getRow()==3 && this.position.getColumn()==6
        boolean isChecked;
        GridPane originalBoard = Game.getInstance().getBoard();
        GridPane copyBoard = new GridPane();
        for (Node node : originalBoard.getChildren()) {
            Square square = (Square) node;
            Square squareCopy = new Square(square.row, square.col, square.hasPiece() ? square.getPiece().clone() : null);
            for (Node child : square.getChildren()) {
                ImageView originalImageView = (ImageView) child;
                ImageView copyImageView = new ImageView();
                copyImageView.setImage(originalImageView.getImage());
                copyImageView.setFitHeight(originalImageView.getFitHeight());
                copyImageView.setFitWidth(originalImageView.getFitWidth());
                copyImageView.setPreserveRatio(originalImageView.isPreserveRatio());
                squareCopy.getChildren().add(copyImageView);
            }
            copyBoard.add(squareCopy, square.row, square.col);
        }
        Square.getSquareById(this.position.getRow(), this.position.getColumn(), copyBoard).tryMovePiece(position, copyBoard);
        King king = (King) Square.findKing(copyBoard, this.color).getPiece();
        isChecked = king.isCheck(copyBoard);
        Game.getInstance().setBoard(originalBoard);
        return isChecked;
    }

    protected Piece clone() throws CloneNotSupportedException {
        Piece clone = new Piece();
        // Crea un nuovo oggetto Piece con gli stessi valori dei campi dell'oggetto originale
        clone.setColor(this.getColor());
        try {
            clone.setPosition(new Position(this.getPosition().getRow(), this.getPosition().getColumn()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return clone;
    }

    protected Mossa lastMove() {
        return lastMove;
    }


}

