package main.chess69;

import java.util.HashMap;

public class Position {
    public static HashMap convention=new HashMap<Integer,Character>(){{
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

    public Position(int row,int colomn) {
        this.colomn = colomn;
        this.row = row;
    }
    public Position(String move){
        this.colomn=move.indexOf(0);
        this.row=move.indexOf(1);
    }

    public Position() {
    }


    public String getPos(){
        return convention.get(this.colomn) + Integer.toString(colomn);
    }

    public static Position posToIndex(String move){
        Position position=new Position();
        position.colomn=move.indexOf(0);
        position.row=move.indexOf(1);
        return position;
    }


}
