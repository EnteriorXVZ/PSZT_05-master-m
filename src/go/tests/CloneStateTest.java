package go.tests;

import go.core.GameState;
import go.core.Point;
import go.core.Stone;
import go.core.StoneType;
import go.utils.StateBuilder;

/**
 * Created by Rados³aw on 2016-01-21.
 */
public class CloneStateTest {
    public static void main(String[] args) {
        StateBuilder builder = new StateBuilder();
        GameState state = builder.getState();

        GameState copy = state.clone_2();

        copy.addStone(new Stone(new Point(0, 0), StoneType.BLACK));
        System.out.println(state);
        System.out.println(copy);

        int i = 0;
    }
}
