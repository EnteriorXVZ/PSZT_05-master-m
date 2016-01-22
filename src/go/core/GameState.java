package go.core;

import go.logic.Inserter;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Rados³aw on 2016-01-10.
 */
public class GameState implements Cloneable{
    private Stone[][] stones = new Stone[5][5];
    private Prisoners prisoners;
    private List<ChainOfStone> chainOfStones = new ArrayList<>();
    private Point lastMove;

    public Prisoners getPrisoners() {
        return prisoners;
    }

    public void setPrisoners(Prisoners prisoners) {
        this.prisoners = prisoners;
    }

    public Stone[][] getStones() {
        return stones;
    }

    public void setStones(Stone[][] stones) {
        this.stones = stones;
    }

    public List<ChainOfStone> getChainsOfStones() {
        return chainOfStones;
    }

    public void setChainOfStones(List<ChainOfStone> chainOfStones) {
        this.chainOfStones = chainOfStones;
    }

    @Override
    public String toString() {
        String tmp = "";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tmp+=stones[i][j]+" ";
            }
            tmp+='\n';
        }
        return tmp;
    }

    public Stone getStoneByPoint(Point point){
        int x = point.getX();
        int y = point.getY();
        return stones[x][y];
    }

    public Point getLastMove() {
        return lastMove;
    }

    public void setLastMove(Point lastMove) {
        this.lastMove = lastMove;
    }

    public void setStoneByPoint(Stone stone){
        int x = stone.getPoint().getX();
        int y = stone.getPoint().getY();
        stones[x][y] = stone;
    }

    public void addChainOfStone(ChainOfStone chainOfStone){
        this.chainOfStones.add(chainOfStone);
    }

    public void addStone(Stone stone){
        int x = stone.getPoint().getX();
        int y = stone.getPoint().getY();
        this.stones[x][y] = stone;
    }

    public void initialize() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                stones[i][j] = new Stone(new Point(i,j),StoneType.EMPTY);
            }
        }
    }

    public GameState clone(){
        GameState state = new GameState();
        Stone stone;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                stone = this.stones[i][j];
                state.addStone(new Stone(new Point(i,j),stone.getStoneType()));
            }
        }
        state.setPrisoners(new Prisoners(this.prisoners.getBlackPrisoners(),this.prisoners.getWhitePrisoners()));
        state.setChainOfStones(new ArrayList<>(this.chainOfStones));
        return state;
    }


//    public GameState clone(){
//        try {
//            return (GameState) super.clone();
//        } catch (CloneNotSupportedException e) {
//
//        }
//        return null;
//    }

    public GameState clone_2(){
        GameState state = new GameState();
        Stone stone;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                stone = this.stones[i][j];
                state.addStone(new Stone(new Point(i,j),stone.getStoneType()));
            }
        }
        Integer blackP = new Integer(this.prisoners.getBlackPrisoners());
        Integer whiteP = new Integer(this.prisoners.getWhitePrisoners());
//        state.initialize();
        state.setPrisoners(new Prisoners(blackP,whiteP));
        state.setChainOfStones(new LinkedList<>());
        return state;
    }
}
