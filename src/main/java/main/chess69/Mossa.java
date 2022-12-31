package main.chess69;

import main.chess69.pieces.*;

public class Mossa{

    public Piece pezzo;
    private int column;
    private int row;
    public String specialMove;

    public Mossa(int row, int column, String specialMove) {
        this.column = column;
        this.row = row;
        this.specialMove=specialMove;
    }

    public Mossa(int row, int column,Piece pezzo) {
        this.column = column;
        this.row = row;
        this.pezzo = pezzo;
    }

    public Mossa(int row, int column) {
        this.column = column;
        this.row = row;
    }

    public boolean isSpecialMove(){
        return this.specialMove != null;
    }
    public static String rowToChar(int row) {
        return String.valueOf(Position.convention.get(row));
    }

    @Override
    public String toString() {
        String mossa = "";
        if (this.pezzo instanceof Rook)
            mossa += "R";
        else if (this.pezzo instanceof Bishop) {
            mossa += "B";
        } else if (this.pezzo instanceof Knight) {
            mossa += "N";
        } else if (this.pezzo instanceof King) {
            mossa += "K";
        } else if (this.pezzo instanceof Queen) {
            mossa += "Q";
        }
        return mossa + rowToChar(this.row) + this.column;
    }

    public int getColumn() {
        return this.column;
    }


    public void setColumn(int column) {
        this.column = column;
    }


    public int getRow() {
        return this.row;
    }


    public void setRow(int row) {
        this.row = row;
    }

    public Position toPosition(){
        return new Position(this.row,this.column);
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Mossa move) {
            return this.column == move.getColumn() && this.row == move.getRow();
        }
        return false;
    }
}
