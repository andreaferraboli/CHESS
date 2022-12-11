package main.chess69;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import main.chess69.Pieces.Piece;


public class Square extends Group {

    int x, y;
    public boolean occupied;
    private Piece piece;
    private ImageView pieceimage;
    private ImageView possibleMove;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        this.occupied = false;
    }

    public void setPieceImage() {
        this.pieceimage = new ImageView("src/main/resources/pieces/" + piece.toString() + ".png");
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public static Square getSquareById(int x, int y) {
        return (Square) gameController.getNodeByCoordinate(x, y);
    }


    public Piece getPiece() {
        return this.piece;
    }
}
