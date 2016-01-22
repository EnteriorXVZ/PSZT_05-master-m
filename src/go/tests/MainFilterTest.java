package go.tests;

import go.core.*;
import go.rools.PointFilterSingleton;

import java.util.List;

/**
 * Created by Rados³aw on 2016-01-12.
 */
public class MainFilterTest {
    public static void main(String[] args) {
        MainPlate plate = MainPlate.getInstance();
        plate.insertStone(new Stone(new Point(0, 1), StoneType.BLACK));
        plate.insertStone(new Stone(new Point(1, 1), StoneType.BLACK));
        plate.insertStone(new Stone(new Point(1,0), StoneType.BLACK));
//        plate.insertStone(new Stone(new Point(4, 4), StoneType.BLACK));
        GameState state = plate.getActualGameState();
//        System.out.println(" testowe wstawianie ");
        System.out.println(state.getChainsOfStones().toString());
        System.out.println();
        System.out.println(state);
        PointFilterSingleton singleton = PointFilterSingleton.getInstance();
        List<Point> pointList = singleton.filter(state,StoneType.WHITE);
        System.out.println(
        );
        System.out.println("lista z dostepnymi punktami w rozmiarze "+ pointList.size()+" -> "+pointList.toString());
    }
}
