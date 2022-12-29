package main.chess69;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static main.chess69.GameMain.primaryStage;


public class Square extends StackPane {

    int row, col;

    private Piece piece;


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

    public Square() {

    }

    public static Square getSquareById(int x, int y) {
        return Game.getNodeByCoordinate(x, y);
    }
    public static Square getSquareById(int x, int y,GridPane grid) {
        return Game.getNodeByCoordinate(x, y,grid);
    }

    public static void removeCheckEffect() {
        // Verifica se la casella contiene un Re
        Game.getInstance().getBoard().getChildren().forEach(node -> {

            Square square = (Square) node;
            if (square.getPiece() instanceof King) {
                // Verifica se la casella ha l'effetto di scacco
                ImageView imageView = (ImageView) square.getChildren().get(2);
                if (imageView.getImage() != null && imageView.getImage().getUrl().contains("check.png")) {
                    // Rimuovi l'effetto di scacco dalla casella
                    imageView.setImage(null);
                }
            }
        });
    }

    public void setPieceImage() {
        Node child = this.getChildren().get(3);
        if (child instanceof ImageView imageView && this.piece.getClass() != Piece.class) {
            imageView.setImage(new Image(getClass().getResource("/main/chess69/pieces/" + this.piece.toString() + ".png").toExternalForm(), true));
        }
    }

    public void setPieceImage(Image image) {
        Node child = this.getChildren().get(3);
        if (child instanceof ImageView imageView) {
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
        if (child instanceof ImageView imageView) {
            imageView.setImage(new Image(getClass().getResource("/main/chess69/board/" + image + ".jpg").toExternalForm(), true));
        }
    }

    public void winGame() throws IOException {
        if (this.piece instanceof King && !this.piece.color.equals(Game.getInstance().getCurrentPlayer().color)) {
            URL url = new File("src/main/resources/main/chess69/end.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
//
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    public void onClick() throws IOException, InterruptedException, CloneNotSupportedException {
        Square selectedSquare = Game.getInstance().getSelectedSquare();
        if (selectedSquare == null) {
            if (this.piece.getColor().equals(Game.getInstance().getCurrentPlayer().color)) {
                refreshAllPossibleMoves(false);
                //this.piece instanceof Bishop && this.piece.position.row==3
                this.piece.getAllPossibleMoves(true);
                Game.getInstance().setSelectedSquare(this);
                this.isSelected();
            }
        } else if (!selectedSquare.equals(this)) {
            if (Game.getInstance().getCurrentPlayer().color.equals(this.hasPiece() ? this.piece.color : Game.getInstance().getCurrentPlayer().color) || Utils.hasPosition(selectedSquare.getPiece().possibleMoves, this.getPosition())) {
                if (selectedSquare.movePiece(this.getPosition())) {
                    removeCheckEffect();
                    Player currentPlayer = Game.getInstance().getCurrentPlayer();
                    ArrayList<Round> mossePartita = Game.getInstance().mossePartita;
                    if (currentPlayer.color.equals(Color.black)) {
                        Game.getInstance().black.lastMove = new Mossa(this.row, this.col, this.piece);
                        mossePartita.get(mossePartita.size() - 1).setMossa2(Game.getInstance().black.lastMove);
                        Game.getInstance().movesListView.getItems().set(mossePartita.size() - 1, mossePartita.get(mossePartita.size() - 1).toString());
                        Game.getInstance().setCurrentPlayer(Game.getInstance().white);
                    } else {
                        Game.getInstance().white.lastMove = new Mossa(this.row, this.col, this.piece);
                        mossePartita.add(new Round(mossePartita.size() + 1, Game.getInstance().white.lastMove, null));
                        Game.getInstance().movesListView.getItems().add(mossePartita.size() - 1, mossePartita.get(mossePartita.size() - 1).toString());
                        Game.getInstance().setCurrentPlayer(Game.getInstance().black);
                    }
                    //todo:implementa presa del pezzo come mossa e fix reverso con pezzo mangiante

                    if (isDraw()) {
                        URL url = new File("src/main/resources/main/chess69/draw.fxml").toURI().toURL();
                        Parent root = FXMLLoader.load(url);
//
                        Scene scene = new Scene(root);
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    }
                }
            }
        } else {
            Game.getInstance().setSelectedSquare(null);
            deleteEffects();
        }


    }

    private boolean isDraw() {

        int numberPiecesWhite = piecesOfColor(Color.white)[0];
        int numberPiecesBlack = piecesOfColor(Color.black)[0];
        int numberKnightWhite = piecesOfColor(Color.white)[1];
        int numberKnightBlack = piecesOfColor(Color.black)[1];
        int numberBishopWhite = piecesOfColor(Color.white)[2];
        int numberBishopBlack = piecesOfColor(Color.black)[2];

        //pareggio se sono solo due re
        if (numberPiecesWhite == 1 && numberPiecesBlack == 1)
            return true;
        //pareggio se solo c'è un cavallo con i due re
        if ((numberKnightBlack == 1 && numberPiecesBlack == 2 && numberPiecesWhite == 1) || (numberKnightWhite == 1 && numberPiecesWhite == 2 && numberPiecesBlack == 1))
            return true;
        //pareggio se solo c'è un cavallo con i due re
        return (numberBishopBlack == 1 && numberPiecesBlack == 2 && numberPiecesWhite == 1) || (numberBishopWhite == 1 && numberPiecesWhite == 2 && numberPiecesBlack == 1);
        //todo:implementare ulteriori situazioni di stallo
    }

    private int[] piecesOfColor(Color color) {
        // AtomicInteger è una classe che contiene un valore intero e permette
        // che le operazioni di lettura e scrittura del valore intero sono thread-safe,
        // il che significa che più thread possono accedere allo stesso oggetto AtomicInteger
        // in modo sicuro senza dover sincronizzare manualmente l'accesso
        AtomicInteger[] counters = new AtomicInteger[]{new AtomicInteger(0), new AtomicInteger(0), new AtomicInteger(0)};
        Game.getInstance().getBoard().getChildren().forEach(node -> {
            Square square = (Square) node;
            if (square.getPiece() != null) {
                if (square.getPiece().color.equals(color))
                    counters[0].getAndIncrement();
                if (square.getPiece() instanceof Knight)
                    counters[1].getAndIncrement();
                if (square.getPiece() instanceof Bishop)
                    counters[2].getAndIncrement();
            }
        });
        int[] array = new int[counters.length];
        for (int i = 0; i < counters.length; i++) {
            array[i] = counters[i].get();
        }
        return array;
    }


    public void refreshAllPossibleMoves(boolean check) {
        for (Node node : Game.getInstance().getBoard().getChildren()) {
            Square square = (Square) node;
            if (square.hasPiece()) {
                System.out.println("prima"+square.getPiece().toString());
                try {
                    square.getPiece().getAllPossibleMoves(check);
                    //todo:after this,the square has not pieces zio porco
                } catch (IOException | CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("dopo"+square.getPiece().toString());
                for (Position move : square.getPiece().possibleMoves) {
                    Square squareById = Square.getSquareById(move.row, move.colomn);
                    ImageView imageView = (ImageView) squareById.getChildren().get(2);
                    if (squareById.getPiece() instanceof King king && !squareById.getPiece().color.equals(square.getPiece().color)) {
                        king.setChecked(true);
                        imageView.setImage(new Image(getClass().getResource("/main/chess69/board/check.png").toExternalForm(), true));
                    } else
                        imageView.setImage(null);
                }

            }
        }

    }

    public boolean hasPiece() {
        return this.piece != null;
    }

    private void isSelected() {
        Node child = this.getChildren().get(0);
        if (child instanceof ImageView imageView) {
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
        if (this.piece != null)
            for (Position move : this.piece.possibleMoves) {
                getSquareById(move.row, move.colomn).showSquareAsPossibleMove(bool);
            }
    }

    private void showSquareAsPossibleMove(boolean bool) {
        Node child = this.getChildren().get(1);
        if (child instanceof ImageView imageView) {
            if (bool) {
                imageView.setImage(new Image(getClass().getResource("/main/chess69/board/selected.png").toExternalForm(), true));
            } else
                imageView.setImage(null);
        }
    }

    public boolean movePiece(Position position) throws IOException, CloneNotSupportedException {

        Game.getInstance().setSelectedSquare(null);
        deleteEffects();
        Piece pezzo = this.getPiece();
        if (pezzo != null) {
            if (Utils.hasPosition(pezzo.possibleMoves, position)) {
                //controllo arrocco
                if ((position.row == 6 || position.row == 2) && this.getPiece() instanceof King && this.getPiece().lastMove == null) {
                    int y;
                    if (this.getPiece().color.equals(Color.black)) {
                        y = 0;
                    } else {
                        y = 7;
                    }
                    if (position.row == 2) {
                        Square.getSquareById(0, y).deletePiece();
                        Square.getSquareById(3, y).setPiece(new Rook(new Position(3, y), this.getPiece().color));
                    } else {
                        Square.getSquareById(7, y).deletePiece();
                        Square.getSquareById(5, y).setPiece(new Rook(new Position(5, y), this.getPiece().color));
                    }
                }
                //controllo l'en passant
                if (!getSquareById(position.row, position.colomn).hasPiece() && this.getPiece() instanceof Pawn) {
                    if (position.row != this.row)
                        getSquareById(position.row, this.getPiece().color.equals(Color.BLACK) ? 4 : 3).deletePiece();
                    else if (position.colomn == 7 || position.colomn == 0) {
                        //promozione
                        pezzo = new Queen(position, this.getPiece().color);
                    }
                }
                pezzo.setPosition(position);
                pezzo.lastMove = new Position(this.row, this.col);
                getSquareById(position.row, position.colomn).setPiece(pezzo);
                deletePiece();
                return true;
            } else
                return false;
        }
        return false;
    }

    public void tryMovePiece(Position position,GridPane gridPane) throws IOException {

        // Aggiorna la posizione del pezzo
        if(this.getPiece()!=null) {
            this.getPiece().trySetPosition(position);
            // Imposta il pezzo nella nuova posizione
            Square.getSquareById(position.row, position.colomn,gridPane).trySetPiece(this.getPiece());

            this.trySetPiece(null);
        }
    }

    public void moveUndo(Position position, int checkPawn) throws IOException, CloneNotSupportedException {
        Game.getInstance().setSelectedSquare(null);
        deleteEffects();
        if (this.getPiece() != null) {
            Piece pezzo = this.getPiece();
            pezzo.setPosition(position);
            if (pezzo instanceof Pawn && pezzo.lastMove.colomn == checkPawn)
                pezzo.lastMove = null;
            else
                pezzo.lastMove = new Position(this.row, this.col);
            getSquareById(position.row, position.colomn).setPiece(pezzo);
            deletePiece();
        }
    }
    public void tryMoveUndo(Position position) throws IOException, CloneNotSupportedException {
        if (this.getPiece() != null) {
            Piece pezzo = this.getPiece();
            pezzo.setPosition(position);
            getSquareById(position.row, position.colomn).setPiece(pezzo);
            deletePiece();
        }
    }
    private void deleteEffects() {
        Node child = this.getChildren().get(0);
        if (child instanceof ImageView imageView) {
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

    public void deletePiece() throws IOException {
        winGame();
        this.piece = null;
        this.setPieceImage(null);
    }

    private Position getPosition() {
        return new Position(this.row, this.col);
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece piece) throws IOException {
        winGame();
        this.piece = piece;
        setPieceImage();
    }
    public void trySetPiece(Piece piece){
        this.piece=piece;
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
        if(this.hasPiece() != obj.hasPiece())
            return false;
        else
            return (this.row == obj.row && this.col == obj.col);
    }
}
