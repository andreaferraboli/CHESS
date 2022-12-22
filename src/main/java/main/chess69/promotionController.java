package main.chess69;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

import static main.chess69.GameMain.primaryStage;
import static main.chess69.GameMain.secondaryStage;

public class promotionController implements Initializable {
    @FXML
    private ImageView queen;
    @FXML
    private ImageView rook;
    @FXML
    private ImageView knight;
    @FXML
    private ImageView bishop;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        queen.setOnMouseClicked(event -> {
            Game.getInstance().setSelectedPieceForPromotion("queen");
            Game.getInstance().promotion=true;
            synchronized (Game.getInstance().lock) {
                Game.getInstance().lock.notifyAll();
            }
            secondaryStage.close();
        });
        rook.setOnMouseClicked(event -> {
            Game.getInstance().setSelectedPieceForPromotion("rook");
            Game.getInstance().promotion=true;
            secondaryStage.close();
        });
        knight.setOnMouseClicked(event -> {
            Game.getInstance().setSelectedPieceForPromotion("knight");
            Game.getInstance().promotion=true;
            secondaryStage.close();
        });
        bishop.setOnMouseClicked(event -> {
            Game.getInstance().setSelectedPieceForPromotion("bishop");
            Game.getInstance().promotion=true;
            secondaryStage.close();
        });

    }


}
