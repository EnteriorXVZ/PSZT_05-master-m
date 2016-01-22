package go.core;

import go.utils.GameLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Rados³aw on 2016-01-10.
 */
public class ChainOfStone {
    private List<Stone> stonesInChain = new ArrayList<>();
    private List<Stone> chainsBreaths = new ArrayList<>();
    private StoneType chainType;

    public ChainOfStone(StoneType chainType) {
        this.chainType = chainType;
    }

    public ChainOfStone(List<ChainOfStone> theSameChains, List<Stone> emptyStonesByPoint) {
        List<Stone> breats = new ArrayList<>();
        List<Stone> stones = new ArrayList<>();
        breats.addAll(emptyStonesByPoint);
        for (ChainOfStone chainOfStone: theSameChains){
//            this.chainsBreaths.addAll(chainOfStone.getChainsBreaths());
//            this.stonesInChain.addAll(chainOfStone.getStonesInChain());
            breats.addAll(chainOfStone.getChainsBreaths());
            stones.addAll(chainOfStone.getStonesInChain());
        }
        this.chainsBreaths = stones.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
        this.chainsBreaths = stones.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
//        stone.setChainOfStone(this);
//        this.stonesInChain.add(stone);
//        this.chainsBreaths.addAll(emptyStonesByPoint);
//        this.chainsBreaths = this.chainsBreaths.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
//        this.stonesInChain = this.stonesInChain.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
//        this.chainType = stone.getStoneType();
        removeDups();
    }

    public List<Stone> getStonesInChain() {
        return stonesInChain;
    }

    public void setStonesInChain(List<Stone> stonesInChain) {
        this.stonesInChain = stonesInChain;
    }

    public List<Stone> getChainsBreaths() {
        return chainsBreaths;
    }

    public void setChainsBreaths(List<Stone> chainsBreaths) {
        this.chainsBreaths = chainsBreaths;
    }

    public StoneType getChainType() {
        return chainType;
    }

    public void setChainType(StoneType chainType) {
        this.chainType = chainType;
    }

    public int getNumberOfBreaths(){
        return chainsBreaths.size();
    }

    public void addStoneToChain(Stone stone){
        this.stonesInChain.add(stone);
    }

    public void addBreath(Stone stone){
        this.chainsBreaths.add(stone);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Kamienie : "+stonesInChain.toString());
        stringBuilder.append("Oddechy "+chainsBreaths.toString());
        stringBuilder.append("libcza oddechow " + getNumberOfBreaths());
        return stringBuilder.toString();
    }

    //    @Override
//    public String toString() {
//        return "ChainOfStone{" +
//                "stonesInChain=" + stonesInChain +
//                ", chainsBreaths=" + chainsBreaths +
//                ", chainType=" + chainType +
//                '}';
//    }

    public ChainOfStone(Stone stone, List<ChainOfStone> chainOfStones,List<Stone> emptyStonesByPoint){
        for (ChainOfStone chainOfStone: chainOfStones){
            this.chainsBreaths.addAll(chainOfStone.getChainsBreaths());
            this.stonesInChain.addAll(chainOfStone.getStonesInChain());
        }
        stone.setChainOfStone(this);
        this.stonesInChain.add(stone);
        this.chainsBreaths.addAll(emptyStonesByPoint);
        this.chainsBreaths = this.chainsBreaths.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
        this.stonesInChain = this.stonesInChain.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
        this.chainType = stone.getStoneType();
    }

    public void removeChainsBreaths(Point point){
        this.chainsBreaths.remove(new Stone(point,StoneType.EMPTY));
    }

    public void removeDups() {
        List<Point> pointList = new ArrayList<>();
        for(Stone s1: stonesInChain){
            for(Stone s2: chainsBreaths){
                if(s1.getPoint().equals(s2.getPoint())){
                    pointList.add(s1.getPoint());
                }
            }
        }

        for (Point p: pointList){
            removeChainsBreaths(p);
            GameLogger.logg("usunieto duplikat punktu "+p);
        }
    }
}
