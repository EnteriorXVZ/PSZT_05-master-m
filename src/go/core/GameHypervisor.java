package go.core;

import go.ai.ComputerPlayer;
import go.utils.GameLogger;

/**
 * Created by Rados³aw on 2016-01-10.
 */
public class GameHypervisor {
    private static GameHypervisor hypervisor;

    private GameHypervisor(){

    }

    public static GameHypervisor getInstance() {
        if(hypervisor==null){
            hypervisor = new GameHypervisor();
        }
        return hypervisor ;
    }
    public void playWhite(Point p){
        ComputerPlayer.getInstance().play(p);
    }

    public void playBlack(Point p){
//        TODO implementacja logiki gry dla czarnych
        insertBlackStone(p);
        GameLogger.getInstance().loggGameState();// wypisuje stan gry:
        playWhite(p);
    }

    private void insertBlackStone(Point p) {
        MainPlate.getInstance().insertStone(new Stone(p,StoneType.BLACK));
        System.out.println(MainPlate.getInstance().getActualGameState());
        System.out.println();
    }
}
