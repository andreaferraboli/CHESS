package main.chess69;


import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import javafx.geometry.Insets;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Game {

    public ArrayList<Round> mossePartita = new ArrayList<>();
    public Player white;
    public Player black;
    private GridPane board;
    private Player currentPlayer;
    private Square selectedSquare;
    private static Game instance;


    public Game(GridPane chessBoard) {
        instance=this;
        instance.board=chessBoard;
        instance.currentPlayer=new Player(Color.white);
        instance.selectedSquare=null;
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
                possibleMoves.setFitHeight(65);
                possibleMoves.setFitWidth(65);
                Square square = new Square(i, j);
                square.getChildren().add(color);
                square.getChildren().add(possibleMoves);
                square.getChildren().add(pieceImage);
                square.setColorOfSquare();
                squares.add(square);
                instance.board.add(square, i, j);
            }
        }
        fillBoard();

        instance.board.getChildren().forEach(node -> {
            Square square=(Square)node;
            square.setOnMouseClicked(event -> {
                if (!(selectedSquare == null && square.getPiece() == null) || square.getPiece().getColor().equals(instance.currentPlayer.color))
                    square.onClick();
            });
        });
    }

    public void fillBoard() {
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

    public void addPiece(Piece piece) {
        Square squareById = Square.getSquareById(piece.position.row, piece.position.colomn);
        squareById.setPiece(piece);
        squareById.setColorOfSquare();
        squareById.occupied=true;
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
    private void setBoard(GridPane board) {
        this.board=board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public static Game getInstance() {
        return instance;
    }


    public GridPane getBoard() {
        return board;
    }

}
