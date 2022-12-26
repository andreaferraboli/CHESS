package main.chess69;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameMain extends Application {
    public static Stage primaryStage;
    public static Stage secondaryStage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameMain.class.getResource("start.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 960, 540);
        stage.setScene(scene);
        stage.show();
        primaryStage = stage;
        secondaryStage = new Stage();

    }
}