package main.chess69;


import javafx.scene.layout.GridPane;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(Position position, Color color) {
        super(position, color);
    }

    public Bishop() {
        super();
    }

    @Override
    public void getAllPossibleMoves(boolean check) throws IOException, CloneNotSupportedException {
        movement(Game.getInstance().getBoard());
        if(check)
            removeMovesCreateCheck();

    }

    public void movement(GridPane gridPane) {
        int x = position.row;
        int y = position.colomn;

        this.possibleMoves = new ArrayList<>();
        int diagonals = 4;
        boolean nextDiagonal = false;

        for (int i = 1; i <= diagonals; i++) {
            do {
                switch (i) {
                    case 1:
                        if (x != 7 && y != 7) {
                            x++;
                            y++;
                        } else
                            nextDiagonal = true;
                        break;
                    case 2:
                        if (x != 0 && y != 7) {
                            x--;
                            y++;
                        } else
                            nextDiagonal = true;
                        break;
                    case 3:
                        if (x != 0 && y != 0) {
                            x--;
                            y--;
                        } else
                            nextDiagonal = true;
                        break;
                    case 4:
                        if (x != 7 && y != 0) {
                            x++;
                            y--;
                        } else
                            nextDiagonal = true;
                        break;
                    default:
                        break;
                }
                if (!nextDiagonal) {
                    Square squareById = Square.getSquareById(x, y,gridPane);

                    if (squareById.getPiece() == null)
                        this.possibleMoves.add(new Position(x, y));
                    else if (!squareById.getPiece().getColor().equals(this.color)) {
                        this.possibleMoves.add(new Position(x, y));
                        nextDiagonal = true;
                    } else
                        nextDiagonal = true;
                }
            } while ((x != 7 && x >= 0 && y >= 0 && y != 7) && !nextDiagonal);
            x = position.row;
            y = position.colomn;
            nextDiagonal = false;
        }
    }


    protected Bishop clone() throws CloneNotSupportedException {
        Bishop clone = new Bishop();
        // Crea un nuovo oggetto Piece con gli stessi valori dei campi dell'oggetto originale
        clone.setColor(this.getColor());
        try {
            clone.setPosition(new Position(this.getPosition().row, this.getPosition().colomn));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return clone;
    }
    @Override
    public String toString() {
        return this.color.equals(Color.BLACK) ? "bb" : "wb";
    }


}

