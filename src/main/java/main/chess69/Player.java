package main.chess69;


import java.awt.*;

public class Player {
    public Color color;
    public Mossa lastMove;

    public Player(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Mossa getLastMove() {
        return lastMove;
    }

    public void setLastMove(Mossa lastMove) {
        this.lastMove = lastMove;
    }
}
