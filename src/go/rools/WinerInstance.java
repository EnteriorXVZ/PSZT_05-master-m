package go.rools;

import go.core.GameState;
import go.core.Prisoners;
import go.core.Stone;
import go.core.StoneType;

/**
 * Created by Rados³aw on 2016-01-13.
 */
public class WinerInstance {
    private GameState gameState;

    public WinerInstance(GameState gameState) {
        this.gameState = gameState;
    }

    public WinerInstance(){

    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public StoneType getWinner() {
        int pointForBlack = 0;
        int pointForWhite = 0;
//        dodajemy do wyniku jencow zdobytych w grze
        Prisoners prisoners = gameState.getPrisoners();
        pointForBlack += prisoners.getWhitePrisoners();
        pointForWhite += prisoners.getBlackPrisoners();

//        przegladamy dana plansze
        Stone stone;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                stone = gameState.getStones()[i][j];
                if(stone.getStoneType()==StoneType.BLACK){
                    pointForBlack++;
                }else if(stone.getStoneType()==StoneType.WHITE){
                    pointForWhite++;
                }
            }
        }

//        dodajemy punkty wolne mozliwe dla kazdego z kolorow
        pointForWhite += PointFilterSingleton.getInstance().filter(gameState,StoneType.WHITE).size();
        pointForBlack += PointFilterSingleton.getInstance().filter(gameState,StoneType.BLACK).size();


        return selectWinner(pointForBlack, pointForWhite);
    }
//    jezeli jest remis to wynik korzystny dla czarnego
    private StoneType selectWinner(int pointForBlack, int pointForWhite) {
//        System.out.println(" punkty bialego "+pointForWhite);
//        System.out.println(" punkty czarnego "+ pointForBlack);
        if(pointForBlack==pointForWhite){
            return StoneType.EMPTY;
        }
        return pointForBlack > pointForWhite ? StoneType.BLACK : StoneType.WHITE;
    }

}
