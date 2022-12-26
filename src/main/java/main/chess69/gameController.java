package main.chess69;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class gameController {
    @FXML
    GridPane board;

    @FXML
    ListView<String> movesListView;

    public void initialize() throws IOException {

        // Themes are Coral, Dusk, Wheat, Marine, Emerald, Sandcastle

        Game game = new Game(board, movesListView);

    }


    @FXML
    private void newGame(ActionEvent event) {
        System.out.println("lollolol");
    }

    @FXML
    private void cancelMove(ActionEvent event) {

    }

}
