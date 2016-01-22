package go.tests;

import go.ai.GameSymulator;
import go.core.GameState;
import go.core.Point;
import go.core.Stone;
import go.core.StoneType;
import go.rools.WinerInstance;
import go.utils.StateBuilder;
import go.utils.StonesInserter;
import javafx.embed.swt.SWTFXUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rados³aw on 2016-01-20.
 */
public class SymulatorTest {
    public static void main(String[] args) {
//        StateBuilder builder = new StateBuilder();
//        GameState state = builder.getState();
//        GameState clone;
//
//        StoneType win1;
//        StoneType win2;
//
//        GameSymulator symulator = new GameSymulator(state,StoneType.BLACK);
//        win1=symulator.symulation();
//
//
//        StateBuilder builder1 = new StateBuilder();
//        clone = state.clone_2();
//
//        GameSymulator symulator1 = new GameSymulator(clone, StoneType.WHITE);
//        win2=symulator1.symulation();
//
//        System.out.println("wygrany 1"+win1);
//        System.out.println("wygrany 2"+win2);

        StateBuilder builder = new StateBuilder();
        GameState state = builder.getState();
        GameSymulator symulator;
        StoneType type;
        int black = 0;
        int white = 0;
        int empty = 0;

        List<GameState> states = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            states.add(state.clone());
        }

        int i = 0;

        while (i++ < 10) {
            builder = new StateBuilder();
//            state = builder.getState();
            state = states.get(i-1);
            symulator = new GameSymulator(state, StoneType.WHITE);
            type = symulator.symulation();
            if (type == StoneType.BLACK) {
                black++;
            }
            if (type == StoneType.WHITE) {
                white++;
            }
            if (type == StoneType.EMPTY) {
                empty++;
            }


        }
        System.out.println("biale " + white);
        System.out.println("czarne " + black);
        System.out.println(" remisy " + empty);
    }


}

