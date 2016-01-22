package go.rools;

import go.core.GameState;
import go.core.Point;
import go.core.StoneType;

import java.util.List;

/**
 * Created by Rados³aw on 2016-01-11.
 */
public interface MovePointFilter {
    List<Point> getPoints(List<Point> list, GameState state, StoneType stoneType);
}
