package main.chess69;

import javafx.scene.Group;
import javafx.scene.image.ImageView;


public class Square extends Group {

    int row, col;
    public boolean occupied;
    private Piece piece;
    private ImageView pieceimage;
    private ImageView possibleMove;

    private ImageView color;

    public Square(int row, int col) {
        this.row = row;
        this.col = col;
        this.occupied = false;
    }

    public Square(Piece piece, ImageView pieceimage, ImageView possibleMove,ImageView color, int row, int col){
        this.row = row;
        this.col = col;
        this.occupied = false;
        this.piece=piece;
        this.pieceimage=pieceimage;
        this.possibleMove=possibleMove;
        this.color=color;
    }

    public void setPieceImage() {
        this.pieceimage = new ImageView("src/main/resources/pieces/" + piece.toString() + ".png");
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
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
}
