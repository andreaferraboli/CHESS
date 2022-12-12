package main.chess69;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.chess69.Pieces.*;

import java.awt.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class gameController implements Initializable {
    @FXML
    private static GridPane board;

    private static gameController instance;

    private static Stage primaryStage;
    private static Stage secondaryStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;
        fillBoard();
//        board.getChildren().forEach(
//                (child)->{
//                    child.setOnAction(event -> {
//                                primaryStage.close();
//                                System.exit(-1);
//                            }
//                    );
//                }
//        );
    }

    public static gameController getInstance() {
        return instance;
    }


    public void addPiece(Piece piece) {
        System.out.println(getNodeByCoordinate(piece.position.row,piece.position.colomn).toString());
        Objects.requireNonNull(getNodeByCoordinate(piece.position.row, piece.position.colomn)).setPieceImage();
        Objects.requireNonNull(getNodeByCoordinate(piece.position.row, piece.position.colomn)).setPiece(piece);
    }

    public static Square getNodeByCoordinate(int row, int column) {
        for (Node node : board.getChildren()) {
            if (GridPane.getColumnIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                return (Square) node;
            }
        }
        return null;
    }

    private void fillBoard() {
//        //create rooks
        addPiece(new Piece(new Position(0, 0), new Type(), Color.WHITE));
//        addPiece(new Piece(new Position(0, 7), new Rook(), Color.white));
//        addPiece(new Piece(new Position(7, 0), new Rook(), Color.black));
//        addPiece(new Piece(new Position(7, 7), new Rook(), Color.black));
//        //create bishops
//        addPiece(new Piece(new Position(0, 5), new Bishop(), Color.white));
//        addPiece(new Piece(new Position(0, 2), new Bishop(), Color.white));
//        addPiece(new Piece(new Position(7, 5), new Bishop(), Color.black));
//        addPiece(new Piece(new Position(7, 2), new Bishop(), Color.black));
//        //crete knights
//        addPiece(new Piece(new Position(0, 6), new Knight(), Color.white));
//        addPiece(new Piece(new Position(0, 1), new Knight(), Color.white));
//        addPiece(new Piece(new Position(7, 6), new Knight(), Color.black));
//        addPiece(new Piece(new Position(7, 1), new Knight(), Color.black));
//        //create kings
//        addPiece(new Piece(new Position(7, 4), new King(), Color.black));
//        addPiece(new Piece(new Position(0, 4), new King(), Color.white));
//        //create queens
//        addPiece(new Piece(new Position(7, 3), new Queen(), Color.black));
//        addPiece(new Piece(new Position(0, 3), new Queen(), Color.white));
//        //create pawns
//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < 8; j++) {
//                if (i == 0)
//                    addPiece(new Piece(new Position(1, j), new Pawn(), Color.white));
//                else
//                    addPiece(new Piece(new Position(6, j), new Pawn(), Color.black));
//
//            }
//
//        }
//
    }
}
