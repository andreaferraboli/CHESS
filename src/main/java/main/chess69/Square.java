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
import main.chess69.pieces.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static main.chess69.GameMain.primaryStage;


public class Square extends StackPane {

    public int row, col;

    private Piece piece;
    private Piece lastPiece;


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

    public static Square getSquareById(int x, int y, GridPane grid) {
        return Game.getNodeByCoordinate(x, y, grid);
    }

    public static void removeCheckEffect() {
        // Verifica se la casella contiene un Re
        for (Node node : Game.getInstance().getBoard().getChildren()) {
            Square square = (Square) node;
            ImageView imageView = (ImageView) square.getChildren().get(2);
            if (square.getPiece() instanceof King) {
                // Verifica se la casella ha l'effetto di scacco
                if (imageView.getImage() != null && imageView.getImage().getUrl().contains("check.png")) {
                    // Rimuovi l'effetto di scacco dalla casella
                    King king = (King) square.getPiece();
                    king.setChecked(false);
                    imageView.setImage(null);
                }
            } else {
                imageView.setImage(null);
            }
        }
    }

    public static Square findKing(GridPane gridPane, Color color) {
        // Cerca la casella con il re dello stesso colore
        for (Node node : gridPane.getChildren()) {
            Square square = (Square) node;
            if (square.hasPiece() && square.getPiece() instanceof King && square.getPiece().getColor().equals(color)) {
                return square;
            }
        }
        return null;
    }

    public void setPieceImage() {
        Node child = this.getChildren().get(3);
        if (this.piece != null)
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

    public void gameIsEnd() throws IOException {
        int numberPiecesWhite = piecesOfColor(Color.white)[0];
        int numberPiecesBlack = piecesOfColor(Color.black)[0];
        int numberKnightWhite = piecesOfColor(Color.white)[1];
        int numberKnightBlack = piecesOfColor(Color.black)[1];
        int numberBishopWhite = piecesOfColor(Color.white)[2];
        int numberBishopBlack = piecesOfColor(Color.black)[2];

        int numberOfWhitePossibleMoves = numberOfPossibleMoves(Color.white);
        int numberOfBlackPossibleMoves = numberOfPossibleMoves(Color.black);
        King whiteKing = (King) Objects.requireNonNull(findKing(Game.getInstance().getBoard(), Color.white)).getPiece();
        King blackKing = (King) Objects.requireNonNull(findKing(Game.getInstance().getBoard(), Color.black)).getPiece();
        if (numberOfBlackPossibleMoves == 0 && blackKing.checked)
            winGame(Color.white);
        if (numberOfWhitePossibleMoves == 0 && whiteKing.checked)
            winGame(Color.black);
        if (numberOfBlackPossibleMoves == 0 && !blackKing.checked)
            drawGame();
        if (numberOfWhitePossibleMoves == 0 && !whiteKing.checked)
            drawGame();
        //pareggio se sono solo due re
        if (numberPiecesWhite == 1 && numberPiecesBlack == 1)
            drawGame();
        //pareggio se solo c'è un cavallo con i due re
        if ((numberKnightBlack == 1 && numberPiecesBlack == 2 && numberPiecesWhite == 1) || (numberKnightWhite == 1 && numberPiecesWhite == 2 && numberPiecesBlack == 1))
            drawGame();
        //pareggio se solo c'è un cavallo con i due re
        if ((numberBishopBlack == 1 && numberPiecesBlack == 2 && numberPiecesWhite == 1) || (numberBishopWhite == 1 && numberPiecesWhite == 2 && numberPiecesBlack == 1))
            drawGame();
    }

    private void drawGame() throws IOException {
        URL url = new File("src/main/resources/main/chess69/winWhite.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void winGame(Color color) throws IOException {
        URL url = new File("src/main/resources/main/chess69/win" + (color.toString().contains("r=255,g=255,b=255") ? "White" : "Black") + ".fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private int numberOfPossibleMoves(Color color) {
        int numberofAllPossibleMoves = 0;
        for (Node node : Game.getInstance().getBoard().getChildren()) {
            Square square = (Square) node;
            if (square.hasPiece())
                if (square.getPiece().getColor().equals(color))
                    numberofAllPossibleMoves += square.getPiece().possibleMoves.size();
        }
        return numberofAllPossibleMoves;
    }

    public void onClick() throws IOException, InterruptedException, CloneNotSupportedException {
        Square selectedSquare = Game.getInstance().getSelectedSquare();
        if (selectedSquare == null) {
            if (this.piece.getColor().equals(Game.getInstance().getCurrentPlayer().color)) {
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
                        Game.getInstance().movesListView.scrollTo(Game.getInstance().movesListView.getItems().size() - 1);
                        Game.getInstance().setCurrentPlayer(Game.getInstance().white);
                    } else {
                        Game.getInstance().white.lastMove = new Mossa(this.row, this.col, this.piece);
                        mossePartita.add(new Round(mossePartita.size() + 1, Game.getInstance().white.lastMove, null));
                        Game.getInstance().movesListView.getItems().add(mossePartita.size() - 1, mossePartita.get(mossePartita.size() - 1).toString());
                        Game.getInstance().movesListView.scrollTo(Game.getInstance().movesListView.getItems().size() - 1);
                        Game.getInstance().setCurrentPlayer(Game.getInstance().black);
                    }

                    refreshAllPossibleMoves(true);
                    gameIsEnd();

                    gameController.getInstance().cancelMoveButton.setDisable(false);
                }
            }
        } else {
            Game.getInstance().setSelectedSquare(null);
            deleteEffects();
        }


    }

    private int[] piecesOfColor(Color color) {
        // AtomicInteger è una classe che contiene un valore intero e permette
        // che le operazioni di lettura e scrittura del valore intero sono thread-safe,
        // il che significa che più thread possono accedere allo stesso oggetto AtomicInteger
        // in modo sicuro senza dover sincronizzare manualmente l'accesso
        AtomicInteger[] counters = new AtomicInteger[]{new AtomicInteger(0), new AtomicInteger(0), new AtomicInteger(0), new AtomicInteger(0)};
        Game.getInstance().getBoard().getChildren().forEach(node -> {
            Square square = (Square) node;
            if (square.getPiece() != null && square.getPiece().color.equals(color)) {
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
                try {
                    square.getPiece().getAllPossibleMoves(check);
                } catch (IOException | CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
                for (Position move : square.getPiece().possibleMoves) {
                    Square squareById = Square.getSquareById(move.getRow(), move.getColumn());
                    ImageView imageView = (ImageView) squareById.getChildren().get(2);
                    if (squareById.getPiece() instanceof King king) {
                        if (!squareById.getPiece().color.equals(square.getPiece().color)) {
                            king.setChecked(true);
                            imageView.setImage(new Image(getClass().getResource("/main/chess69/board/check.png").toExternalForm(), true));
                        } else {
                            king.setChecked(false);
                            imageView.setImage(null);
                        }
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
                getSquareById(move.getRow(), move.getColumn()).showSquareAsPossibleMove(bool);
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
                boolean mossaSpeciale = false;
                //controllo arrocco
                if ((position.getRow() == 6 || position.getRow() == 2) && this.getPiece() instanceof King && this.getPiece().lastMove == null) {
                    int y;
                    if (this.getPiece().color.equals(Color.black)) {
                        y = 0;
                    } else {
                        y = 7;
                    }
                    if (position.getRow() == 2) {
                        mossaSpeciale = true;
                        pezzo.lastMove = new Mossa(this.row, this.col, "arrocco_lungo");
                        Square.getSquareById(0, y).deletePiece();
                        Square.getSquareById(3, y).setPiece(new Rook(new Position(3, y), this.getPiece().color));
                    } else {
                        mossaSpeciale = true;
                        pezzo.lastMove = new Mossa(this.row, this.col, "arrocco_corto");
                        Square.getSquareById(7, y).deletePiece();
                        Square.getSquareById(5, y).setPiece(new Rook(new Position(5, y), this.getPiece().color));
                    }
                }
                //controllo l'en passant
                if (!getSquareById(position.getRow(), position.getColumn()).hasPiece() && this.getPiece() instanceof Pawn) {
                    if (position.getRow() != this.row) {
                        mossaSpeciale = true;
                        pezzo.lastMove = new Mossa(this.row, this.col, "en_passant");
                        getSquareById(position.getRow(), this.getPiece().color.equals(Color.BLACK) ? 4 : 3).deletePiece();
                    } else if (position.getColumn() == 7 || position.getColumn() == 0) {
                        //promozione
                        pezzo = new Queen(position, this.getPiece().color);
                    }
                }

                if (!mossaSpeciale)
                    pezzo.lastMove = new Mossa(this.row, this.col);
                getSquareById(position.getRow(), position.getColumn()).setPiece(pezzo);
                this.lastPiece = pezzo;
                deletePiece();
                return true;
            } else
                return false;
        }
        return false;
    }

    public void tryMovePiece(Position position, GridPane gridPane){

        // Aggiorna la posizione del pezzo
        if (this.getPiece() != null) {
            this.getPiece().trySetPosition(position);
            // Imposta il pezzo nella nuova posizione
            Square.getSquareById(position.getRow(), position.getColumn(), gridPane).trySetPiece(this.getPiece());

            this.trySetPiece(null);
        }
    }

    public void moveUndo(Mossa pos, int checkPawn) throws IOException, CloneNotSupportedException {
        Game.getInstance().setSelectedSquare(null);
        deleteEffects();
        if (this.getPiece() != null) {
            Piece pezzo = this.getPiece();
            pezzo.setPosition(pos.toPosition());
            //verifica per ridare al pedone le due mosse disponibili
            if (pezzo instanceof Pawn && pezzo.lastMove != null) {
                if (pezzo.lastMove.getColumn() == checkPawn) {
                    int column = pezzo.lastMove.getColumn();
                    pezzo.lastMove = null;
                    getSquareById(this.row, column).setPiece(pezzo);
                    this.deletePiece();
                }
            }
            boolean specialMoveDone = false;
            if (pezzo.lastMove != null) {
                if (pezzo.lastMove.specialMove != null) {
                    switch (pezzo.lastMove.specialMove) {
                        case "arrocco_lungo":
                            specialMoveDone = true;
                            // Codice da eseguire per il caso "arrocco_lungo"
                            pezzo.lastMove = null;
                            getSquareById(4, this.col).setPiece(this.getPiece());
                            getSquareById(0, this.col).setPiece(getSquareById(3, this.col).getPiece());
                            Square.getSquareById(1, this.col).deletePiece();
                            Square.getSquareById(2, this.col).deletePiece();
                            Square.getSquareById(3, this.col).deletePiece();
                            getSquareById(4, this.col).getPiece().lastMove = null;
                            getSquareById(0, this.col).getPiece().lastMove = null;
                            break;
                        case "arrocco_corto":
                            specialMoveDone = true;

                            // Codice da eseguire per il caso "arrocco_corto"
                            pezzo.lastMove = null;
                            getSquareById(4, this.col).setPiece(this.getPiece());
                            getSquareById(7, this.col).setPiece(getSquareById(5, this.col).getPiece());
                            Square.getSquareById(5, this.col).deletePiece();
                            Square.getSquareById(6, this.col).deletePiece();
                            getSquareById(4, this.col).getPiece().lastMove = null;
                            getSquareById(7, this.col).getPiece().lastMove = null;
                            break;
                        case "en_passant":
                            specialMoveDone = true;
                            Square.getSquareById(this.piece.lastMove.getRow(), this.piece.lastMove.getColumn()).setPiece(this.piece);
                            this.setPiece(this.lastPiece);
                            Square.getSquareById(this.row, this.getPiece().getColor().equals(Color.BLACK) ? this.col - 1 : this.col + 1).setPiece(Square.getSquareById(this.row, this.getPiece().getColor().equals(Color.BLACK) ? this.col - 1 : this.col + 1).lastPiece);
                            // Codice da eseguire per il caso "en_passant"

                            break;
                        default:
                            // Codice da eseguire se nessuno dei casi precedenti è stato soddisfatto
                            break;
                    }
                }
                if (!specialMoveDone) {
                    int row = this.piece.lastMove.getRow();
                    int column = this.piece.lastMove.getColumn();
                    getSquareById(row, column).setPiece(pezzo);
                    if (this.lastPiece != null) {
                        this.setPiece(this.lastPiece);
                        this.lastPiece = null;
                    } else {
                        this.deletePiece();
                    }
//                    refreshAllPossibleMoves(true);
                    Piece piece = getSquareById(row, column).getPiece();
                    // se è la prima mossa del re devo settare la sua ultima mossa a null
                    if (piece instanceof King) {
                        int counter = 0;
                        for (Round round : Game.getInstance().mossePartita) {
                            if (piece.color.equals(Color.black)) {
                                if (round.mossa2.toString().contains("K"))
                                    counter++;
                            } else {
                                if (round.mossa1.toString().contains("K"))
                                    counter++;
                            }
                        }
                        piece.lastMove = null;
                    }

                    piece.getAllPossibleMoves(true);

                }
            }


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

    public void deletePiece(){
        if (this.piece != null)
            this.lastPiece = this.piece;
        this.piece = null;
        this.setPieceImage(null);
    }

    private Position getPosition() {
        return new Position(this.row, this.col);
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece piece) throws IOException, CloneNotSupportedException {
        if (this.piece != null)
            this.lastPiece = this.piece;
        else
            this.lastPiece = null;
        if (piece != null)
            piece.setPosition(this.getPosition());
        this.piece = piece;
        setPieceImage();
    }

    public void trySetPiece(Piece piece) {
        this.piece = piece;
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
        if (this.hasPiece() != obj.hasPiece())
            return false;
        else
            return (this.row == obj.row && this.col == obj.col);
    }


}
