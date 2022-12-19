package main.chess69;

import javafx.scene.Group;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;


public class Square extends Group {

    int row, col;
    public boolean occupied, selected;
    private Piece piece;
    private ImageView pieceimage;
    private ImageView possibleMove;

    private ImageView color;

    public Square(int row, int col) {
        this.row = row;
        this.col = col;
        this.occupied = false;
    }

    public Square(Piece piece, ImageView pieceimage, ImageView possibleMove, ImageView color, int row, int col) {
        this.row = row;
        this.col = col;
        this.occupied = false;
        this.selected = false;
        this.piece = piece;
        this.pieceimage = pieceimage;
        this.possibleMove = possibleMove;
        this.color = color;
    }

    public void setPieceImage() {
       this.pieceimage.setImage(new Image(new File("/main/chess69/pieces/"+this.piece.toString()+".png").toURI().toString()));
    }
    public void setPieceImage(Image image){
        this.pieceimage.setImage(image);
    }

    public void setColorOfSquare() {
        String image;
        if ((this.col + this.row) % 2 == 0)
            image = "dark";
        else
            image = "light";
        this.color.setImage(new Image(new File("/src/main/resources/main/chess69/pieces/" + image + ".png").toURI().toString()));
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        setPieceImage();
    }

    public void onClick() {
        Square selectedSquare = gameController.getInstance().getSelectedSquare();
        if (selectedSquare == null) {
            gameController.getInstance().setSelectedSquare(this);
        }else if (!selectedSquare.equals(this)) {
            selectedSquare.movePiece(this.getPosition());
            gameController.getInstance().setSelectedSquare(this);
        } else {
            gameController.getInstance().setSelectedSquare(null);
        }


    }

    private void movePiece(Position position) {
        getSquareById(position.row, position.colomn).setPiece(this.getPiece());
        deletePiece();
    }

    private void deletePiece() {
        this.piece=null;
        this.setPieceImage(null);
    }

    private Position getPosition() {
        return new Position(this.row, this.col);
    }

    public static Square getSquareById(int x, int y) {
        return gameController.getNodeByCoordinate(x, y);
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
                ", pieceimage=" + pieceimage +
                ", possibleMove=" + possibleMove +
                '}';
    }

    public boolean equals(Square obj) {
        return (this.row == obj.row && this.col == obj.col);
    }
}
