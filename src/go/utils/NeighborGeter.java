package go.utils;

import go.core.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Rados³aw on 2016-01-10.
 */
public class NeighborGeter {
    private GameState state;
    private Point point;

    public NeighborGeter(GameState state, Point point) {
        this.state = state;
        this.point = point;
    }


    public List<ChainOfStone> getChainsOfStones() {
        List<Stone> stones = getStonesByPoint(point);
        List<ChainOfStone> chainOfStones = new ArrayList<>();
        ChainOfStone tmpChainOfStone;
        for (Stone s : stones) {
            tmpChainOfStone = s.getChainOfStone();
            if (tmpChainOfStone != null) {
                chainOfStones.add(tmpChainOfStone);
            }
        }

        GameLogger.getInstance().logg("dlugosc listy ");

        return chainOfStones.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Stone> getEmptyStonesByPoint() {
        return getStonesByPoint(point).stream().filter(p -> p.getStoneType() == StoneType.EMPTY).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Stone> getStonesByPoint(Point point) {
        List<Point> neighborPoints = generatePoints(point);
        List<Stone> stones = new ArrayList<>();
        int x, y;
        for (Point p : neighborPoints) {
            x = p.getX();
            y = p.getY();
            stones.add(state.getStones()[x][y]);
        }
//        GameLogger.getInstance().logg("Lista sasiednich kamieni " + stones.toString());
        return stones;
    }

    private List<Point> generatePoints(Point point) {
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


}
