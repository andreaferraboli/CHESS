package main.chess69;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static main.chess69.GameMain.primaryStage;

public class mainController implements Initializable {
    @FXML
    private Button exitButton = new Button();
    @FXML
    private Button playButton = new Button();
    private static mainController instance;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;
        exitButton.setOnAction(event -> {
                    primaryStage.close();
                    System.exit(-1);
                }
        );

    }

    public void playGame(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/main/chess69/game.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();

    }


    public static mainController getInstance() {
        return instance;
    }


}
