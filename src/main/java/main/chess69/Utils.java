package main.chess69;

import java.util.ArrayList;

public class Utils {
    public static boolean between(int num,int min,int max){
        return num>=min&&num<=max;
    }
    public static boolean hasPosition(ArrayList<Position> array,Position object){
        for(Position position: array)
            if(position.equals(object))
                return true;
        return false;
    }

    public static boolean equalsPosition(Position pos1,Position pos2){
        return pos1.row==pos2.row && pos1.colomn== pos2.colomn;
    }

}
