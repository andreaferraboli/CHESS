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

    public static Position posToIndex(String move) {
        Position position = new Position();
        position.column = move.indexOf(0);
        position.row = move.indexOf(1);
        return position;
    }


    public String getPos() {
        return convention.get(this.getColumn()) + Integer.toString(column);
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

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position pos) {
            return this.column == pos.getColumn() && this.row == pos.getRow();
        }
        return false;
    }
}
