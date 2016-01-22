package go.utils;

import go.core.*;

/**
 * Created by Rados³aw on 2016-01-20.
 */
public class StateBuilder {
    private GameState state = new GameState();

    public StateBuilder() {
        init();
    }

    private void init() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                state.getStones()[i][j] = new Stone(new Point(i,j), StoneType.EMPTY);
            }
        }
        state.setPrisoners(new Prisoners(0,0));
    }

    public GameState getState() {
        return state;
    }
}
