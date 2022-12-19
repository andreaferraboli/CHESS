package main.chess69;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import javafx.geometry.Insets;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.ArrayList;

public class Game {

    public ArrayList<Round> mossePartita = new ArrayList<>();
    public Player white;
    public Player black;
    public static Player currentPlayer;


    public Game(GridPane chessBoard) {
        ArrayList<Square> squares = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            chessBoard.getRowConstraints().add(new RowConstraints(65));
            chessBoard.getColumnConstraints().add(new ColumnConstraints(65));
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String color=((i+j)%2==0?"dark":"light");
                Image image = new Image(getClass().getResource("/main/chess69/board/"+color +".jpg").toExternalForm(), true);;

                // Aggiungi un'immagine in ogni cella della griglia

                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(65);  // imposta la larghezza dell'immagine visualizzata su 100 pixel
                imageView.setFitHeight(65);  // imposta l'altezza dell'immagine visualizzata su 100 pixel
                chessBoard.add(imageView, i, j);

//                ImageView color = new ImageView();
//                color.setFitHeight(65);
//                color.setFitWidth(65);
//                ImageView pieceImage = new ImageView();
//                color.setFitHeight(65);
//                color.setFitWidth(65);
//                ImageView possibleMoves = new ImageView();
//                color.setFitHeight(65);
//                color.setFitWidth(65);
//                Square square = new Square(i, j, new Piece());
//                squares.add(square);

//                chessBoard.add(square, i, j);
            }
        }
    }
}
