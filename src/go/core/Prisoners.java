package go.core;

import go.utils.GameLogger;

/**
 * Created by Rados³aw on 2016-01-10.
 */
public class Prisoners implements Cloneable{
    private int blackPrisoners = 0;
    private int whitePrisoners = 0;

    public Prisoners(){
    }

    public Prisoners(int blackPrisoners, int whitePrisoners) {
        this.blackPrisoners = blackPrisoners;
        this.whitePrisoners = whitePrisoners;
    }

    public int getBlackPrisoners() {
        return blackPrisoners;
    }

    public void setBlackPrisoners(int blackPrisoners) {
        this.blackPrisoners = blackPrisoners;
    }

    public int getWhitePrisoners() {
        return whitePrisoners;
    }

    public void setWhitePrisoners(int whitePrisoners) {
        this.whitePrisoners = whitePrisoners;
    }

    public Prisoners copy(){
        return new Prisoners(this.blackPrisoners,this.whitePrisoners);
    }

    public void addWhitePrisoners(int count) {
        this.whitePrisoners+=count;
        GameLogger.logg("ilosc bialych jencow "+whitePrisoners);
    }

    public void addBlackPrisoners(int count) {
        this.whitePrisoners+=count;
        GameLogger.logg("ilosc czarnych jencow "+whitePrisoners);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prisoners prisoners = (Prisoners) o;

        if (blackPrisoners != prisoners.blackPrisoners) return false;
        return whitePrisoners == prisoners.whitePrisoners;

    }

    @Override
    public int hashCode() {
        int result = blackPrisoners;
        result = 31 * result + whitePrisoners;
        return result;
    }

    @Override
    public String toString() {
        return "Prisoners{" +
                "blackPrisoners=" + blackPrisoners +
                ", whitePrisoners=" + whitePrisoners +
                '}';
    }
}
