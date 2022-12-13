package main.chess69;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.chess69.Pieces.Piece;
import main.chess69.Pieces.Rook;

import java.awt.*;
import java.net.URL;
import java.security.cert.PolicyNode;
import java.util.Objects;
import java.util.ResourceBundle;

public class gameController implements Initializable {
    @FXML
    private GridPane board=new GridPane();

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

    public Square getNodeByCoordinate(Integer row, Integer col) {
        for (Node node : getBoard().getChildren())
            if (GridPane.getColumnIndex(node) != null
                    && GridPane.getColumnIndex(node) != null
                    && Objects.equals(GridPane.getRowIndex(node), row)
                    && Objects.equals(GridPane.getColumnIndex(node), col))
                return (Square) node;
        return null;
    }

    public GridPane getBoard() {
        return board;
    }

    public void fillBoard() {
//        //create rooks
        System.out.println( getNodeByCoordinate(2,3).toString());
//        r1.getAllPossibleMoves(new Position("a0"));
//        addPiece(new Piece(new Position(0, 0), r1, Color.WHITE));
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
