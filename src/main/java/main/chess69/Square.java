package main.chess69;

import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.awt.*;


public class Square extends StackPane {

    int row, col;
    public boolean selected;
    private Piece piece;
//    private ImageView pieceimage;
//    private ImageView possibleMove;
//
//    private ImageView color;


    public Square(int row, int col, Piece piece) {
        this.row = row;
        this.col = col;
        this.piece = piece;
    }

    public Square(int row, int col) {
        this.row = row;
        this.col = col;
        this.piece = null;
    }


    public void setPieceImage() {
        Node child = this.getChildren().get(2);
        if (child instanceof ImageView && this.piece.getClass() != Piece.class) {
            ImageView imageView = (ImageView) child;
            imageView.setImage(new Image(getClass().getResource("/main/chess69/pieces/" + this.piece.toString() + ".png").toExternalForm(), true));
        }
    }

    public void setPieceImage(Image image) {
        Node child = this.getChildren().get(2);
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
            if (this.piece.getColor().equals(Game.getInstance().getCurrentPlayer().color)) {
                Game.getInstance().setSelectedSquare(this);
                this.isSelected();
            }
        } else if (!selectedSquare.equals(this)) {
            if(Game.getInstance().getCurrentPlayer().color.equals(this.hasPiece()?this.piece.color:Game.getInstance().getCurrentPlayer().color) || Utils.hasPosition(selectedSquare.getPiece().possibleMoves,this.getPosition())) {
                if(selectedSquare.movePiece(this.getPosition()))
                {
//                    selectedSquare.deleteEffects();
                    Player currentPlayer = Game.getInstance().getCurrentPlayer();
                    if (currentPlayer.color.equals(Color.black)) {
                        Game.getInstance().black.lastMove=new Mossa(this.row, this.col);
                        Game.getInstance().setCurrentPlayer(Game.getInstance().white);
                    } else {
                        Game.getInstance().white.lastMove=new Mossa(this.row, this.col);
                        Game.getInstance().setCurrentPlayer(Game.getInstance().black);
                    }
                    selectedSquare.deletePiece();
                    refreshAllPossibleMoves();
//                    Game.getInstance().setSelectedSquare(null);
                }
            }
        } else {
            Game.getInstance().setSelectedSquare(null);
            deleteEffects();
        }


    }

    private void refreshAllPossibleMoves() {
        Game.getInstance().getBoard().getChildren().forEach(
                node -> {
                    Square square = (Square) node;
                    if (square.hasPiece())
                        ((Square) node).getPiece().getAllPossibleMoves();
                }
        );
    }

    public boolean hasPiece() {
        return this.piece != null;
    }

    private void isSelected() {
        Node child = this.getChildren().get(0);
        if (child instanceof ImageView) {
            ImageView imageView = (ImageView) child;
            ColorAdjust color_adjust = new ColorAdjust();
            // set hue, saturation, brightness, and contrast
            color_adjust.setHue(0.4);
            color_adjust.setBrightness(0.6);
            color_adjust.setContrast(0.8);
            color_adjust.setSaturation(0.1);
            imageView.setEffect(color_adjust);
            showPossibleMoves(true);
        }
    }

    private void showPossibleMoves(boolean bool) {
        for (Position move : this.piece.possibleMoves) {
            getSquareById(move.row, move.colomn).showSquareAsPossibleMove(bool);
        }
    }

    private void showSquareAsPossibleMove(boolean bool) {
        Node child = this.getChildren().get(1);
        if (child instanceof ImageView) {
            ImageView imageView = (ImageView) child;
            if (bool)
                imageView.setImage(new Image(getClass().getResource("/main/chess69/board/selected.png").toExternalForm(), true));
            else
                imageView.setImage(null);
        }
    }

    private boolean movePiece(Position position) {
        //TODO: implementare arrocco lungo e corto
        //TODO: implementare scacco
        //TODO: implementare promozione pezzo
        //TODO: implementare vittoria
        Game.getInstance().setSelectedSquare(null);
        deleteEffects();
            if (Utils.hasPosition(this.piece.possibleMoves, position)) {
                if(!getSquareById(position.row, position.colomn).hasPiece() && position.row!=this.row && this.getPiece() instanceof Pawn)
                    //il pezzo ha fatto l'en passant,elimino il pedone
                {
                    getSquareById(position.row,this.getPiece().color.equals(Color.BLACK) ? 4: 3).deletePiece();
                }
                Piece pezzo = this.getPiece();
                pezzo.setPosition(position);
                System.out.println("posizione finale:"+pezzo.position);
                pezzo.lastMove = new Position(this.row, this.col);
                System.out.println("posizione prima:"+pezzo.lastMove);
                getSquareById(position.row, position.colomn).setPiece(pezzo);
                deletePiece();
                return true;
            }
            else
                return false;
    }

    private void deleteEffects() {
        Node child = this.getChildren().get(0);
        if (child instanceof ImageView) {
            ImageView imageView = (ImageView) child;
            ColorAdjust color_adjust = new ColorAdjust();
            // set hue, saturation, brightness, and contrast
            color_adjust.setHue(0);
            color_adjust.setBrightness(0);
            color_adjust.setContrast(0);
            color_adjust.setSaturation(0);
            imageView.setEffect(color_adjust);
        }
        showPossibleMoves(false);
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
                ", piece=" + piece +
                '}';
    }

    public boolean equals(Square obj) {
        return (this.row == obj.row && this.col == obj.col);
    }
}
