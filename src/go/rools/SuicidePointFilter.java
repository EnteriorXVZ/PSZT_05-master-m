package go.rools;

import go.core.*;
import go.logic.InserterHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Rados³aw on 2016-01-12.
 */
public class SuicidePointFilter implements MovePointFilter{
    private MovePointFilter movePointFilter;

    public SuicidePointFilter(MovePointFilter movePointFilter) {
        this.movePointFilter = movePointFilter;
    }

    @Override
    public List<Point> getPoints(List<Point> list, GameState state, StoneType stoneType) {
        List<Point> result = list.stream().filter(p->!isSuicidePoint(state,p,stoneType)).collect(Collectors.toCollection(ArrayList::new));
        if(movePointFilter==null){
            return result;
        }else {
            return movePointFilter.getPoints(result,state,stoneType);
        }
    }

    private boolean isSuicidePoint(GameState state, Point p, StoneType stoneType) {
        InserterHelper helper = new InserterHelper(state,new Stone(p,stoneType),p);
        List<Stone> pointList = helper.getEmptyStonesByPoint();
        if(pointList.size()>0){
            return false;
        }else {
            return capturesOtherStones(helper, state, p, stoneType);
//            return true;
        }
    }
//TODO do zaimplementowania
    private boolean capturesOtherStones(InserterHelper helper, GameState state, Point p, StoneType stoneType) {
//        Prisoners beforePrisoners = state.getPrisoners();
//        StonesInserter inserter = new StonesInserter(state, new Stone(p,stoneType));
//        inserter.insert();
//        GameState state1 = inserter.getState();
//        Prisoners afterProsoners = state.getPrisoners();
//        return beforePrisoners.equals(afterProsoners);
        return true;
    }

}
