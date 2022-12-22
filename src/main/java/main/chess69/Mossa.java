package main.chess69;

public class Mossa {

    int colomn;
    int row;

    public Mossa(int row,int colomn) {
        this.colomn = colomn;
        this.row = row;
    }

    public void charToCol(char a){

    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Mossa) {
            Mossa move=(Mossa) obj;
            return this.colomn== move.colomn && this.row== move.row;
        }
        return false;
    }
}
