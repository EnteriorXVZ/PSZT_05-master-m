package go.tests;

import go.core.*;
import go.rools.WinerInstance;

/**
 * Created by Rados³aw on 2016-01-13.
 */
public class MainGetWinnerTest {
    public static void main(String[] args) {
        MainPlate mainPlate = MainPlate.getInstance();

//        mainPlate.insertStone(new Stone(new Point(0,0), StoneType.WHITE));
        mainPlate.insertStone(new Stone(new Point(0,1), StoneType.BLACK));
        mainPlate.insertStone(new Stone(new Point(1,1), StoneType.BLACK));
        mainPlate.insertStone(new Stone(new Point(1,0), StoneType.BLACK));

        mainPlate.insertStone(new Stone(new Point(4,0), StoneType.WHITE));
//        mainPlate.insertStone(new Stone(new Point(3,0), StoneType.WHITE));
        mainPlate.insertStone(new Stone(new Point(4,1), StoneType.WHITE));
        mainPlate.insertStone(new Stone(new Point(4,2), StoneType.WHITE));

        GameState state = mainPlate.getActualGameState();

        System.out.println(" aktualny stan gry ");
        System.out.println(state);
        System.out.println();
        WinerInstance winerInstanceSingleton = new WinerInstance(state);
        System.out.println("oraz zwyciesca "+ winerInstanceSingleton.getWinner());
    }
}
