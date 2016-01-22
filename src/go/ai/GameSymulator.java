package go.ai;

import go.core.GameState;
import go.core.Point;
import go.core.Stone;
import go.core.StoneType;
import go.rools.PointFilterSingleton;
import go.rools.WinerInstance;
import go.utils.RandomPointGenerator;
import go.utils.StonesInserter;

import java.util.List;

/**
 * Created by Rados³aw on 2016-01-13.
 */
public class GameSymulator {
    private GameState gameState;
    private StoneType whoseTurn;
    private WinerInstance winerInstance;
    private RandomPointGenerator pointGenerator;
    private PointFilterSingleton pointFilterSingleton;
    private volatile boolean symulationFlag = true;


    public GameSymulator(GameState gameState, StoneType whoseTurn) {
        this.gameState = gameState;
        this.whoseTurn = whoseTurn;
        winerInstance = new WinerInstance();
//        pointGenerator = new RandomPointGenerator();
        pointFilterSingleton = PointFilterSingleton.getInstance();
    }

    public StoneType symulation() {
        while (symulationFlag) {
            pointGenerator = new RandomPointGenerator();
            List<Point> pointList = pointFilterSingleton.filter(gameState, whoseTurn);
            Point point = pointGenerator.getRandomPoint(pointList);
            if (verifyPoint(point)) {
                symulationFlag = false;
                break;
            }
            StonesInserter inserter = new StonesInserter(gameState, new Stone(point, whoseTurn));
            inserter.insert();
            gameState = inserter.getState();
            whoseTurn = changeStoneColor();
        }
//        pobieranie zzwyciescy
        winerInstance.setGameState(gameState);
        return winerInstance.getWinner();
    }

    private StoneType changeStoneColor() {
        return whoseTurn == StoneType.BLACK ? StoneType.WHITE : StoneType.BLACK;
    }


    private boolean verifyPoint(Point point) {
        return point.equals(new Point(-1, -1));
    }

    public GameState getGameState() {
        return gameState;
    }
}
