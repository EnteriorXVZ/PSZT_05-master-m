package go.logic;

import go.core.ChainOfStone;
import go.core.GameState;
import go.core.Stone;
import go.core.StoneType;
import go.gui.ListOfButtonsSingleton;

import go.utils.GameLogger;
import go.utils.NeighborGeter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Rados³aw on 2016-01-11.
 */
public class Inserter {
    private GameState gameState;
    private Stone stone;
    private InserterHelper helper;

    public Inserter(GameState gameState, Stone stone) {
        this.gameState = gameState;
        this.stone = stone;
        helper = new InserterHelper(gameState,stone, stone.getPoint());
    }

    public void insert(){
        int numberOfChains = gameState.getChainsOfStones().size();
        List<ChainOfStone> chainOfStones = helper.getChainsOfStones();
        if(numberOfChains==0 || chainOfStones.size()==0){
            insertSeparatedStone();
        }else {
            insertAndMergeWithChains(chainOfStones);
        }
        System.out.println("koniec INSERT: ");
        GameLogger.logg(" dlugosc lancuchow po zlaczeniu "+gameState.getChainsOfStones().size()+" -> "+gameState.getChainsOfStones().toString());
    }

    private void insertAndMergeWithChains(List<ChainOfStone> chainOfStones) {
        GameLogger.logg(" lacze z lanuchami ");
        List<ChainOfStone> theSameChains = getChaninOfStoneByType(chainOfStones,stone.getStoneType());
        List<ChainOfStone> difretnChains = getChaninOfStoneByType(chainOfStones,getDifrentStoneType(stone.getStoneType()));
        GameLogger.logg("ilosc lancuchow tego samego koloru "+theSameChains.size()+" -> "+theSameChains.toString());
        GameLogger.logg("ilosc lancuchow innego koloru "+theSameChains.size()+" -> "+difretnChains.toString());
        mergeTheSameColorStones(theSameChains);
        mergeTheSameDifrentColorStones(difretnChains);
    }

    private void mergeTheSameDifrentColorStones(List<ChainOfStone> difretnChains) {

    }

    private void mergeTheSameColorStones(List<ChainOfStone> theSameChains) {
        System.out.println("lacze z tymi samymi lancuchami ");
        ChainOfStone chainOfStone = new ChainOfStone(theSameChains,helper.getEmptyStonesByPoint());
        stone.setChainOfStone(chainOfStone);
        chainOfStone.addStoneToChain(stone);
        gameState.getChainsOfStones().removeAll(theSameChains);
        gameState.addChainOfStone(chainOfStone);
        gameState.getStones()[stone.getPoint().getX()][stone.getPoint().getY()] = stone;
        GameLogger.logg(" dlugosc lancuchow po zlaczeniu "+gameState.getChainsOfStones().size()+" -> "+gameState.getChainsOfStones().toString());
    }

    private StoneType getDifrentStoneType(StoneType stoneType) {
        return stoneType==StoneType.WHITE ? StoneType.BLACK: StoneType.WHITE;
    }


    private void insertSeparatedStone() {
        ChainOfStone chainOfStone = new ChainOfStone(stone.getStoneType());
        List<Stone> stones = helper.getEmptyStonesByPoint();
        chainOfStone.setChainsBreaths(stones);
        stone.setChainOfStone(chainOfStone);
        chainOfStone.addStoneToChain(stone);
        gameState.addChainOfStone(chainOfStone);
        gameState.addStone(stone);
    }

    public List<ChainOfStone> getChaninOfStoneByType(List<ChainOfStone> chainOfStones, StoneType stoneType) {
        return chainOfStones.stream().filter(p -> p.getChainType() == stoneType).collect(Collectors.toCollection(ArrayList::new));
    }

    public GameState getGameState(){
        return this.gameState;
    }
}
