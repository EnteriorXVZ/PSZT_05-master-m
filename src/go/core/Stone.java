package go.core;

import java.awt.*;

/**
 * Created by Rados³aw on 2016-01-10.
 */
public class Stone {
    private Point point;
    private StoneType stoneType;
    private ChainOfStone chainOfStone;

    public Stone(Point point, StoneType stoneType) {
        this.point = point;
        this.stoneType = stoneType;
    }

    public Stone(Point point, StoneType stoneType, ChainOfStone chainOfStone) {
        this.point = point;
        this.stoneType = stoneType;
        this.chainOfStone = chainOfStone;
    }

    public Stone(Stone stone){
        this.point = stone.getPoint();
        this.stoneType = stone.getStoneType();
        this.chainOfStone = stone.getChainOfStone();
    }

    public Point getPoint() {
        return point;
    }

    public StoneType getStoneType() {
        return stoneType;
    }

    public ChainOfStone getChainOfStone() {
        return chainOfStone;
    }

    public void setChainOfStone(ChainOfStone chainOfStone) {
        this.chainOfStone = chainOfStone;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stone stone = (Stone) o;

        if (point != null ? !point.equals(stone.point) : stone.point != null) return false;
        return stoneType == stone.stoneType;

    }

    @Override
    public int hashCode() {
        int result = point != null ? point.hashCode() : 0;
        result = 31 * result + (stoneType != null ? stoneType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String color;
        if(chainOfStone!=null){
            if(chainOfStone.getChainType()==StoneType.BLACK){
                color = "B";
            }else {
                color = "W";
            }
        }
        else {
            color= "E";
        }
        return "[ ("+point.getX()+","+point.getY()+"),"+this.stoneType+"(CH: "+color+")"+"]";
    }
}
