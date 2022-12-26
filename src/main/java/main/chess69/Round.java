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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Mossa getMossa1() {
        return mossa1;
    }

    public void setMossa1(Mossa mossa1) {
        this.mossa1 = mossa1;
    }

    public Mossa getMossa2() {
        return mossa2;
    }

    public void setMossa2(Mossa mossa2) {
        this.mossa2 = mossa2;
    }

    @Override
    public String toString() {
        String string = String.valueOf(this.index);
        if (mossa1 != null)
            string += " " + mossa1;
        if (mossa2 != null)
            string += " " + mossa2;

        return string;

    }
}
