package go.rools;

import go.core.GameState;
import go.core.Point;
import go.core.Stone;
import go.core.StoneType;
import javafx.scene.PointLight;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rados³aw on 2016-01-11.
 */
public class EmptyStonesPointFilter implements MovePointFilter{
    private MovePointFilter movePointFilter;

    public EmptyStonesPointFilter(MovePointFilter movePointFilter) {
        this.movePointFilter = movePointFilter;
    }

    @Override
    public List<Point> getPoints(List<Point> points,GameState state, StoneType stoneType) {
//        TODO implementacja
        List<Point> pointList = new ArrayList<>();
        Stone stone;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                stone = state.getStones()[i][j];
                if(stone.getStoneType()==StoneType.EMPTY){
                    pointList.add(stone.getPoint());
                }
            }
        }
        if(movePointFilter==null){
            return pointList;
        }else {
            return movePointFilter.getPoints(pointList,state,stoneType);
        }
    }
}
