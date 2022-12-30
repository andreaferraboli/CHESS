package main.chess69;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        this.possibleMoves = filteredList;
    }

    private boolean createsDiscoveredCheck(Position position) throws IOException, CloneNotSupportedException {
        //this instanceof Bishop && position.row==4 && position.colomn==5 && this.position.row==3 && this.position.colomn==6
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
        Square.getSquareById(this.position.row, this.position.colomn, copyBoard).tryMovePiece(position, copyBoard);
        List<Square> differences = Utils.getDifferentSquares(originalBoard, copyBoard);
        King king = (King) Square.findKing(copyBoard,this.color).getPiece();
        isChecked = king.isCheck(copyBoard);
        Game.getInstance().setBoard(originalBoard);
        return isChecked;
    }

    protected Piece clone() throws CloneNotSupportedException {
        Piece clone = new Piece();
        // Crea un nuovo oggetto Piece con gli stessi valori dei campi dell'oggetto originale
        clone.setColor(this.getColor());
        try {
            clone.setPosition(new Position(this.getPosition().row, this.getPosition().colomn));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return clone;
    }

    protected Position lastMove() {
        return lastMove;
    }



}

