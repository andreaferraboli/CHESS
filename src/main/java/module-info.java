module main.chess69 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.datatransfer;
    requires java.desktop;


    opens main.chess69 to javafx.fxml;
    exports main.chess69;
    exports main.chess69.pieces;
    opens main.chess69.pieces to javafx.fxml;
}