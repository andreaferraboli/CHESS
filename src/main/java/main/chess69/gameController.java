package main.chess69;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
        ArrayList<Square> squares = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ImageView color=new ImageView();

                    String imageUrl;
                    if ((i + j) % 2 == 0) {
                        imageUrl="C:\\Users\\andre\\IdeaProjects\\CHESS69\\src\\main\\resources\\main\\chess69\\board\\dark.jpg";
                    } else {
                        imageUrl="C:\\Users\\andre\\IdeaProjects\\CHESS69\\src\\main\\resources\\main\\chess69\\board\\light.jpg";
                    }
                    color.setImage(new Image(imageUrl));
                Square square = new Square(new Piece(),new ImageView(),new ImageView(),color,i,j);
                squares.add(square);
                instance.getBoard().add(square, j, i);
            }
        }
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
//        Objects.requireNonNull(getNodeByCoordinate(piece.position.row, piece.position.colomn)).setPieceImage();
//        Objects.requireNonNull(getNodeByCoordinate(piece.position.row, piece.position.colomn)).setPiece(piece);
    }

    public static Square getNodeByCoordinate( int row, int col) {
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

    public GridPane getBoard() {
        return board;
    }

    public void fillBoard() {
//        //create rooks
        addPiece(new Rook(new Position(0, 0), Color.white));
        addPiece(new Rook(new Position(0, 7),  Color.white));
        addPiece(new Rook(new Position(7, 0),  Color.black));
        addPiece(new Rook(new Position(7, 7),  Color.black));
        //create bishops
        addPiece(new Bishop(new Position(0, 5),  Color.white));
        addPiece(new Bishop(new Position(0, 2),  Color.white));
        addPiece(new Bishop(new Position(7, 5),  Color.black));
        addPiece(new Bishop(new Position(7, 2),  Color.black));
        //crete knights
        addPiece(new Knight(new Position(0, 6),  Color.white));
        addPiece(new Knight(new Position(0, 1),  Color.white));
        addPiece(new Knight(new Position(7, 6),  Color.black));
        addPiece(new Knight(new Position(7, 1),  Color.black));
        //create kings
        addPiece(new King(new Position(7, 4),  Color.black));
        addPiece(new King(new Position(0, 4),  Color.white));
        //create queens
        addPiece(new Queen(new Position(7, 3),  Color.black));
        addPiece(new Queen(new Position(0, 3),  Color.white));
        //create pawns
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0)
                    addPiece(new Pawn(new Position(1, j),  Color.white));
                else
                    addPiece(new Pawn(new Position(6, j),  Color.black));

            }

        }

    }
}
