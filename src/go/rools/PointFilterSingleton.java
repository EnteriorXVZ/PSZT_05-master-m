package go.rools;

import go.core.GameState;
import go.core.Point;
import go.core.StoneType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rados³aw on 2016-01-11.
 */
public class PointFilterSingleton {
    private static PointFilterSingleton pointFilterSingleton;

    private MovePointFilter movePointFilter;

    private PointFilterSingleton(){
        initialize();
    }

//    TODO rozszerzanie lancucha odpowiedzialnosci filtrow
    private void initialize()
    {
        MovePointFilter filter1 = new SuicidePointFilter(null);
        movePointFilter = new EmptyStonesPointFilter(filter1);
    }

    public static PointFilterSingleton getInstance() {
        if (pointFilterSingleton==null){
            pointFilterSingleton = new PointFilterSingleton();
        }
        return pointFilterSingleton;
    }

    public List<Point> filter(GameState state, StoneType stoneType){
        return movePointFilter.getPoints(new ArrayList<>(),state,stoneType);
    }
}
