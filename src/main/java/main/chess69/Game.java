package main.chess69;


import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Game {

    private static Game instance;
    public final Object lock = new Object();
    public ArrayList<Round> mossePartita = new ArrayList<>();
    public ListView<String> movesListView;
    public String selectedPieceForPromotion;
    public boolean promotion;
    public Player white;
    public Player black;
    private GridPane board;
    private Player currentPlayer;
    private Square selectedSquare;


    public Game(GridPane chessBoard, ListView<String> movesListView) throws IOException {
        instance = this;
        instance.movesListView = movesListView;
        instance.mossePartita = new ArrayList<>();
        instance.board = chessBoard;
        instance.black = new Player(Color.BLACK);
        instance.white = new Player(Color.WHITE);
        instance.setCurrentPlayer(instance.white);
        ArrayList<Square> squares = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            instance.board.getRowConstraints().add(new RowConstraints(65));
            instance.board.getColumnConstraints().add(new ColumnConstraints(65));
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                ImageView color = new ImageView();
                color.setFitHeight(65);
                color.setFitWidth(65);
                ImageView pieceImage = new ImageView();
                pieceImage.setFitHeight(65);
                pieceImage.setFitWidth(65);
                ImageView possibleMoves = new ImageView();
                possibleMoves.setFitHeight(40);
                possibleMoves.setFitWidth(40);
                ImageView check = new ImageView();
                check.setFitHeight(65);
                check.setFitWidth(65);
                Square square = new Square(i, j);
                square.getChildren().add(color);
                square.getChildren().add(possibleMoves);
                square.getChildren().add(check);
                square.getChildren().add(pieceImage);
                square.setColorOfSquare();
                squares.add(square);
                instance.board.add(square, i, j);
            }
        }
        fillBoard();

        instance.board.getChildren().forEach(node -> {
            Square square = (Square) node;
            square.setOnMouseClicked(event -> {
                if (square.hasPiece() || selectedSquare != null) {
                    try {
                        square.onClick();
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else
                    System.out.println("cella selezionata vuota" + square.row + " " + square.col);
            });
        });
    }

    public static Square getNodeByCoordinate(int row, int col) {
        int colNode, rowNode;
        ObservableList<Node> children = instance.getBoard().getChildren();

        for (Node node : children) {
            if (node instanceof Square square) {
                if (square.col == col && square.row == row) {
                    return square;
                }
            }
        }
        return null;
    }

    public static Game getInstance() {
        return instance;
    }

    public void fillBoard() throws IOException {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0)
                    addPiece(new Pawn(new Position(j, 6), java.awt.Color.white));
                else
                    addPiece(new Pawn(new Position(j, 1), Color.black));

            }

        }
        //create bishops
        addPiece(new Bishop(new Position(5, 7), java.awt.Color.white));
        addPiece(new Bishop(new Position(2, 7), java.awt.Color.white));
        addPiece(new Bishop(new Position(5, 0), java.awt.Color.black));
        addPiece(new Bishop(new Position(2, 0), java.awt.Color.black));
        //crete knights
        addPiece(new Knight(new Position(6, 7), java.awt.Color.white));
        addPiece(new Knight(new Position(1, 7), java.awt.Color.white));
        addPiece(new Knight(new Position(6, 0), java.awt.Color.black));
        addPiece(new Knight(new Position(1, 0), java.awt.Color.black));
//        //create rooks
        addPiece(new Rook(new Position(0, 7), java.awt.Color.white));
        addPiece(new Rook(new Position(7, 7), java.awt.Color.white));
        addPiece(new Rook(new Position(0, 0), java.awt.Color.black));
        addPiece(new Rook(new Position(7, 0), java.awt.Color.black));
        //create kings
        addPiece(new King(new Position(4, 0), java.awt.Color.black));
        addPiece(new King(new Position(4, 7), java.awt.Color.white));
        //create queens
        addPiece(new Queen(new Position(3, 0), java.awt.Color.black));
        addPiece(new Queen(new Position(3, 7), java.awt.Color.white));
        //create pawns

    }

    public Square getSelectedSquare() {
        return selectedSquare;
    }

    public void setSelectedSquare(Square selectedSquare) {
        this.selectedSquare = selectedSquare;
    }

    public void addPiece(Piece piece) throws IOException {
        Square squareById = Square.getSquareById(piece.position.row, piece.position.colomn);
        squareById.setPiece(piece);
        squareById.setColorOfSquare();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public GridPane getBoard() {
        return board;
    }

    private void setBoard(GridPane board) {
        this.board = board;
    }

    public String getSelectedPieceForPromotion() {
        return this.selectedPieceForPromotion;
    }

    public void setSelectedPieceForPromotion(String selectedPieceForPromotion) {
        this.selectedPieceForPromotion = selectedPieceForPromotion;
    }
}
