package main.chess69;

public class Round {
    public int index;
    public Mossa mossa1;
    public Mossa mossa2;

    public Round(int index, Mossa mossa1, Mossa mossa2) {
        this.index = index;
        this.mossa1 = mossa1;
        this.mossa2 = mossa2;
    }
}
