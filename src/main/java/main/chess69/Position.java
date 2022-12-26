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

    public int row;
    public int colomn;

    public Position(int row, int column) {
        this.colomn = column;
        this.row = row;
    }

    public Position(String move) {
        this.colomn = move.indexOf(0);
        this.row = move.indexOf(1);
    }

    public Position() {
    }

    public static Position posToIndex(String move) {
        Position position = new Position();
        position.colomn = move.indexOf(0);
        position.row = move.indexOf(1);
        return position;
    }

    public String getPos() {
        return convention.get(this.colomn) + Integer.toString(colomn);
    }

    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", colomn=" + colomn +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position pos) {
            return this.colomn == pos.colomn && this.row == pos.row;
        }
        return false;
    }
}
