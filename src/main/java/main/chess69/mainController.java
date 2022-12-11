package main.chess69;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {
    @FXML
    private Button exitButton;
    @FXML
    private Button playButton;
    private static mainController instance;

    private static Stage primaryStage;
    private static Stage secondaryStage;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;
        exitButton.setOnAction(event -> {
                    primaryStage.close();
                    System.exit(-1);
                }
        );
        playButton.setOnAction(event -> {
            FXMLLoader fxmlLoader = new FXMLLoader(GameMain.class.getResource("game.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 960, 540);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            primaryStage.setScene(scene);
            primaryStage.show();
            System.exit(-1);
        });

    }

    public static mainController getInstance() {
        return instance;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        mainController.primaryStage = primaryStage;
    }

    public static Stage getSecondaryStage() {
        return secondaryStage;
    }

    public static void setSecondaryStage(Stage secondaryStage) {
        mainController.secondaryStage = secondaryStage;
    }


}
