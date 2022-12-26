package main.chess69;

public class Mossa {

    public Piece pezzo;
    int colomn;
    int row;

    public Mossa(int row, int colomn, Piece pezzo) {
        this.colomn = colomn;
        this.row = row;
        this.pezzo = pezzo;
    }

    public Mossa(int row, int colomn) {
        this.colomn = colomn;
        this.row = row;
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
        return mossa + rowToChar(this.row) + this.colomn;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Mossa move) {
            return this.colomn == move.colomn && this.row == move.row;
        }
        return false;
    }
}
