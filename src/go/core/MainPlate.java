package go.core;

import go.logic.Inserter;
import go.utils.StonesInserter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rados³aw on 2016-01-10.
 */
public class MainPlate {
    private static MainPlate mainPlate;
    private Stone[][] stoneTable;
    private Prisoners prisoners;
    private List<ChainOfStone> chainOfStones;
    private Point lastMove;

    private MainPlate(){
        prisoners = new Prisoners();
        chainOfStones = new ArrayList<>();
        stoneTable = new Stone[5][5];
        initialize();
    }

    private void initialize() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                stoneTable[i][j] = new Stone(new Point(i,j),StoneType.EMPTY);
            }
        }
    }


    public static MainPlate getInstance() {
        if (mainPlate==null){
            mainPlate= new MainPlate();
        }
        return mainPlate;
    }

    public GameState getActualGameState(){
//        TODO aktualizajca tej metody


        GameState gameState = new GameState();
        gameState.setStones(stoneTable.clone());
        gameState.setPrisoners(prisoners.copy());
        gameState.setLastMove(lastMove);
        gameState.setChainOfStones(new ArrayList<>(this.chainOfStones));
        return gameState;
    }

    public void insertStone(Stone stone){

//        int x = stone.getPoint().getX();
//        int y = stone.getPoint().getY();
//        stoneTable[x][y] = stone;
        lastMove = stone.getPoint();
        manage(stone);
    }

    private void manage(Stone stone) {
//        TODO ta metoda po wstawieniu porzadkuje ustawienia kamieni
        StonesInserter stonesInserter = new StonesInserter(this.getActualGameState().clone(),stone);
        stonesInserter.insert();
//        System.out.println("stan na dzis ");
//        System.out.println(stonesInserter.getState());
//        System.out.println();

//        Inserter inserter = new Inserter(getActualGameState(),stone);
//        inserter.insert();
        restoreFromState(stonesInserter.getState());
    }

    public void restoreFromState(GameState state){
        Stone s;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                s = state.getStoneByPoint(new Point(i,j));
                stoneTable[i][j] = new Stone(new Point(i,j),s.getStoneType());
            }
        }
        prisoners = state.getPrisoners().copy();
        chainOfStones = new ArrayList<>(state.getChainsOfStones());
        this.lastMove = state.getLastMove();
    }




}
