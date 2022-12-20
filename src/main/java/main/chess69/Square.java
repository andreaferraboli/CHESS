package main.chess69;

import javafx.scene.Group;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.File;


public class Square extends StackPane {

    int row, col;
    public boolean occupied, selected;
    private Piece piece;
//    private ImageView pieceimage;
//    private ImageView possibleMove;
//
//    private ImageView color;


    public Square(int row, int col, Piece piece) {
        this.row = row;
        this.col = col;
        this.piece = piece;
        this.occupied = false;
    }



    public void setPieceImage() {
        Node child = this.getChildren().get(1);
        if (child instanceof ImageView && this.piece.getClass() != Piece.class) {
            ImageView imageView = (ImageView) child;
            imageView.setImage(new Image(getClass().getResource("/main/chess69/pieces/" + this.piece.toString() + ".png").toExternalForm(), true));
        }
    }

    public void setPieceImage(Image image) {
        Node child = this.getChildren().get(1);
        if (child instanceof ImageView) {
            ImageView imageView = (ImageView) child;
            imageView.setImage(image);
        }
    }

    public void setColorOfSquare() {

            String image;
            if ((this.col + this.row) % 2 == 0)
                image = "dark";
            else
                image = "light";
        Node child = this.getChildren().get(0);
        if (child instanceof ImageView) {
            ImageView imageView = (ImageView) child;
            imageView.setImage(new Image(getClass().getResource("/main/chess69/board/" + image + ".jpg").toExternalForm(), true));
        }
    }
        public void setPiece(Piece piece) {
            this.piece = piece;
            setPieceImage();
    }

    public void onClick() {
        Square selectedSquare = Game.getInstance().getSelectedSquare();
        if (selectedSquare == null) {
            Game.getInstance().setSelectedSquare(this);
        } else if (!selectedSquare.equals(this)) {
            selectedSquare.movePiece(this.getPosition());
            Game.getInstance().setSelectedSquare(this);
        } else {
            Game.getInstance().setSelectedSquare(null);
        }


    }

    private void movePiece(Position position) {
        getSquareById(position.row, position.colomn).setPiece(this.getPiece());
        deletePiece();
    }

    private void deletePiece() {
        this.piece = null;
        this.setPieceImage(null);
    }

    private Position getPosition() {
        return new Position(this.row, this.col);
    }

    public static Square getSquareById(int x, int y) {
        return Game.getNodeByCoordinate(x, y);
    }


    public Piece getPiece() {
        return this.piece;
    }

    @Override
    public String toString() {
        return "Square{" +
                "x=" + row +
                ", y=" + col +
                ", occupied=" + occupied +
                ", piece=" + piece +
                '}';
    }

    public boolean equals(Square obj) {
        return (this.row == obj.row && this.col == obj.col);
    }
}
