package main.chess69;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import static main.chess69.GameMain.primaryStage;
import static main.chess69.GameMain.secondaryStage;

public class mainController implements Initializable {
    @FXML
    private Button exitButton = new Button();
    @FXML
    private Button playButton = new Button();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exitButton.setOnAction(event -> {
                    primaryStage.close();
                    System.exit(-1);
                }
        );
        playButton.setOnAction(event -> {
            URL urlFile = null;
            try {
                urlFile = new File("src/main/resources/main/chess69/game.fxml").toURI().toURL();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            Parent root = null;
            try {
                root = FXMLLoader.load(urlFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            secondaryStage.close();

        });

    }


}
