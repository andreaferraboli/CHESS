package main.chess69;

import java.util.HashMap;

public class Position {
    public static HashMap<Integer, Character> convention = new HashMap<>() {{
        put(0, 'a');
        put(1, 'b');
        put(2, 'c');
        put(3, 'd');
        put(4, 'e');
        put(5, 'f');
        put(6, 'g');
        put(7, 'h');
    }};

    private int row;
    private int column;

    public Position(int row, int column) {
        this.column = column;
        this.row = row;
    }

    public Position(String move) {
        this.column = move.indexOf(0);
        this.row = move.indexOf(1);
    }

    public Position() {
    }

    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position pos) {
            return this.column == pos.getColumn() && this.row == pos.getRow();
        }
        return false;
    }
}
