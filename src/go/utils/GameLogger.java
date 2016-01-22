package go.utils;

import go.core.GameState;
import go.core.MainPlate;

/**
 * Created by Rados³aw on 2016-01-10.
 */
public class GameLogger {
    private static GameLogger logger;

    public static GameLogger getInstance() {
        if(logger==null){
            logger=new GameLogger();
        }
        return logger;
    }

    public static void logg(String mess){
        System.out.println();
        System.out.println(mess);
        System.out.println();
    }
    public void logg1(String mess){
        System.out.println();
        System.out.println(mess);
    }

    public void loggGameState(){
        GameState state = MainPlate.getInstance().getActualGameState();
        System.out.println(state);
    }
}
