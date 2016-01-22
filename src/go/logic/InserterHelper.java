package go.logic;

import go.core.*;
import go.utils.GameLogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Rados³aw on 2016-01-11.
 */
public class InserterHelper {
    private GameState state;
    private Stone stone;
    private Point point;

    public InserterHelper(GameState state, Stone stone, Point point) {
        this.state = state;
        this.stone = stone;
        this.point = point;
    }


    public List<Stone> getStonesByPoint() {
        List<Stone> stones = new ArrayList<>();
        int x, y;
        for (Point p : generatePoints()) {
            x = p.getX();
            y = p.getY();
            stones.add(state.getStones()[x][y]);
        }
//        GameLogger.logg("dla punktu: "+point.toString()+" sasiedzi: "+stones.size()+" -> "+stones.toString());
        return stones;
    }

    public List<Point> generatePoints() {
        int x = point.getX();
        int y = point.getY();
        return Arrays.asList(
                new Point(x - 1, y),
                new Point(x + 1, y),
                new Point(x, y - 1),
                new Point(x, y + 1)
        ).stream().filter(p -> isInRange(p)).collect(Collectors.toCollection(ArrayList::new));
    }

    private boolean isInRange(Point p) {
        int x = p.getX();
        int y = p.getY();
        return (x >= 0 && x < 5) && (y >= 0 && y < 5);
    }

    public List<ChainOfStone> getChainsOfStones() {
        List<ChainOfStone> result = new ArrayList<>();
        for(Stone stone: getStonesByPoint()){
            if(stone.getChainOfStone()!=null){
                result.add(stone.getChainOfStone());
            }
        }
        GameLogger.logg("dla punktu "+point.toString()+" wszystkie lancuchy w rozmiarze "+result.size()+" -> "+result.toString());
        return result;
    }


    public List<Stone> getEmptyStonesByPoint() {
        return getStonesByPoint().stream().filter(p -> p.getStoneType() == StoneType.EMPTY).collect(Collectors.toCollection(ArrayList::new));
    }
}
