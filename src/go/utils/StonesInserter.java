package go.utils;

import go.core.*;
import go.logic.InserterHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by Rados³aw on 2016-01-10.
 */
public class StonesInserter {
    private GameState state;
    private Stone stone;
//    public static Logger logger = Logger.getLogger("INSERT KAMIENI");

    private InserterHelper helper;


    public StonesInserter(GameState state, Stone stone) {
        this.state = state;
        this.stone = stone;
        helper =new InserterHelper(state,stone,stone.getPoint());
    }

    public void insert() {
        if(!emptyPlace())
        if (state.getChainsOfStones().size() == 0) {
            insertSeparatedStone();
        } else {
//            List<ChainOfStone> chainOfStoneList = getNeighboursChain();
            List<ChainOfStone> chainOfStoneList = getNeighboursChain_poprawna();
//            List<ChainOfStone> chainOfStoneList = new NeighborGeter(state,stone.getPoint()).getChainsOfStones();
//           lancuch nie ma wspolnych sasiadow
            if (chainOfStoneList.size() == 0) {
                insertSeparatedStone();
            } else {
                if (stone.getStoneType() == StoneType.BLACK) {
                    mergeBlack(chainOfStoneList);
                } else {
                    mergeWhite(chainOfStoneList);
                }

            }
        }
        this.celanUp();
    }

    private boolean emptyPlace() {
        Point point = stone.getPoint();
        Stone stone = state.getStones()[point.getX()][point.getY()];
        return stone.getStoneType()==StoneType.EMPTY? false: true;
    }

    private List<ChainOfStone> getNeighboursChain_poprawna() {
        List<ChainOfStone> chainOfStones = new ArrayList<>();
        List<Point> pointList = helper.generatePoints();
        for (ChainOfStone chainOfStone: state.getChainsOfStones()){
            for(Stone s: chainOfStone.getStonesInChain()){
                for(Point p: pointList){
                    if(p.equals(s.getPoint())){
                        chainOfStones.add(chainOfStone);
                    }
                }
            }
        }
        return chainOfStones.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
    }

    private void mergeWhite(List<ChainOfStone> chainOfStoneList) {
//        TODO narazie tylko lacze kamienie
        // laczymy czarne lancuchy ze soba
        List<ChainOfStone> blackChainOfStones = getChaninOfStoneByType(chainOfStoneList, StoneType.WHITE);//wybieram
        List<Stone> emptyStonesByPoint = new NeighborGeter(state, stone.getPoint()).getEmptyStonesByPoint();
        GameLogger.logg("puste punkty dla nowego kamienia " + stone.toString() + "->" + emptyStonesByPoint.toString());

        GameLogger.logg("przed usunieciem " + state.getChainsOfStones().size());

        ChainOfStone newChainOfStonses = new ChainOfStone(stone, blackChainOfStones, emptyStonesByPoint);
        this.state.getChainsOfStones().removeAll(blackChainOfStones);
        GameLogger.logg("teraz w stanie mamy tyle list kamieni " + state.getChainsOfStones().size());

        state.addChainOfStone(newChainOfStonses);

        GameLogger.logg("po zmianach w stanie mamy tyle list kamieni " + state.getChainsOfStones().size());
//        polaczenie lancucow

        state.addStone(stone);

        List<ChainOfStone> whiteChain = getChaninOfStoneByType(chainOfStoneList,StoneType.BLACK);
        whiteChain.stream().forEach(p->p.removeChainsBreaths(stone.getPoint()));
//        lista lancucow do usuniecia
        whiteChain = whiteChain.stream().filter(p->p.getNumberOfBreaths()==0).collect(Collectors.toCollection(ArrayList::new));
        List<Point> pointList = new ArrayList<>();

//        zbieranie punktow  z lancuchów
        for (ChainOfStone chainOfStone: whiteChain){
            for(Stone s: chainOfStone.getStonesInChain()){
                pointList.add(s.getPoint());
            }
        }

        state.getPrisoners().addWhitePrisoners(pointList.size());

//        usuniecie z tablicy lancuchow:
        int x,y;
        for(Point p: pointList){
            x = p.getX();
            y = p.getY();
            state.getStones()[x][y] = new Stone(p,StoneType.EMPTY);
        }

//        usuwanie lancuchow z listy lancuchow
        state.getChainsOfStones().removeAll(whiteChain);

//        TODO dodanie oddechow do lancuchow sasiadujcych z nimi

        for(Point p: pointList){
            for(ChainOfStone chainOfStone: new NeighborGeter(state,p).getChainsOfStones()){
                chainOfStone.addBreath(state.getStones()[p.getX()][p.getY()]);
            }
        }

        for(ChainOfStone chainOfStone: state.getChainsOfStones()){
//            chainOfStone.setChainsBreaths(chainOfStone.getChainsBreaths().stream().distinct().collect(Collectors.toCollection(ArrayList::new)));
            chainOfStone.removeDups();
        }

        printAllChain();
    }



    private void mergeBlack(List<ChainOfStone> chainOfStoneList) {
//        TODO narazie tylko lacze kamienie
        // laczymy czarne lancuchy ze soba
            List<ChainOfStone> blackChainOfStones = getChaninOfStoneByType(chainOfStoneList, StoneType.BLACK);//wybieram
            List<Stone> emptyStonesByPoint = new NeighborGeter(state, stone.getPoint()).getEmptyStonesByPoint();
            GameLogger.logg("puste punkty dla nowego kamienia " + stone.toString() + "->" + emptyStonesByPoint.toString());

            GameLogger.logg("przed usunieciem " + state.getChainsOfStones().size());

            ChainOfStone newChainOfStonses = new ChainOfStone(stone, blackChainOfStones, emptyStonesByPoint);
            this.state.getChainsOfStones().removeAll(blackChainOfStones);
            GameLogger.logg("teraz w stanie mamy tyle list kamieni " + state.getChainsOfStones().size());

            state.addChainOfStone(newChainOfStonses);

            GameLogger.logg("po zmianach w stanie mamy tyle list kamieni " + state.getChainsOfStones().size());
//        polaczenie lancucow

            state.addStone(stone);

        List<ChainOfStone> whiteChain = getChaninOfStoneByType(chainOfStoneList,StoneType.WHITE);
        whiteChain.stream().forEach(p->p.removeChainsBreaths(stone.getPoint()));
//        lista lancucow do usuniecia
        whiteChain = whiteChain.stream().filter(p->p.getNumberOfBreaths()==0).collect(Collectors.toCollection(ArrayList::new));
        List<Point> pointList = new ArrayList<>();

//        zbieranie punktow  z lancuchów
        for (ChainOfStone chainOfStone: whiteChain){
            for(Stone s: chainOfStone.getStonesInChain()){
                pointList.add(s.getPoint());
            }
        }

        state.getPrisoners().addWhitePrisoners(pointList.size());

//        usuniecie z tablicy lancuchow:
        int x,y;
        for(Point p: pointList){
            x = p.getX();
            y = p.getY();
            state.getStones()[x][y] = new Stone(p,StoneType.EMPTY);
        }

//        usuwanie lancuchow z listy lancuchow
        state.getChainsOfStones().removeAll(whiteChain);

//        TODO dodanie oddechow do lancuchow sasiadujcych z nimi

        for(Point p: pointList){
            for(ChainOfStone chainOfStone: new NeighborGeter(state,p).getChainsOfStones()){
                chainOfStone.addBreath(state.getStones()[p.getX()][p.getY()]);
            }
        }

        for(ChainOfStone chainOfStone: state.getChainsOfStones()){
//            chainOfStone.setChainsBreaths(chainOfStone.getChainsBreaths().stream().distinct().collect(Collectors.toCollection(ArrayList::new)));
            chainOfStone.removeDups();
        }

        printAllChain();
    }

    private void printAllChain() {
        System.out.println("wypisuje wszystkie listy kamieni z rozmarem"+state.getChainsOfStones().size());
        for(ChainOfStone ch: state.getChainsOfStones()){
            System.out.println(ch);
        }
        System.out.println("wypisuje wszystkie listy kamieni koniec ");
    }

    public List<ChainOfStone> getChaninOfStoneByType(List<ChainOfStone> chainOfStones, StoneType stoneType) {
        return chainOfStones.stream().filter(p -> p.getChainType() == stoneType).collect(Collectors.toCollection(ArrayList::new));
    }

    private List<ChainOfStone> getNeighboursChain() {
//        GameLogger.logg("wchodze do szukania sasiadow");
        List<ChainOfStone> chainOfStones = new ArrayList<>();
        for (ChainOfStone chainOfStone : state.getChainsOfStones()) {
            if (chainOfStone.getChainsBreaths().stream().anyMatch(p -> p.getPoint().equals(this.stone.getPoint()))) {
                chainOfStones.add(chainOfStone);
            }
        }
        GameLogger.logg("ilosc lancuchow sasiadow dla nowego kamienia " + chainOfStones.size());
        return chainOfStones;
    }

    public void insertSeparatedStone() {
        ChainOfStone chainOfStone = new ChainOfStone(stone.getStoneType());
        List<Stone> breatchs = new NeighborGeter(state, stone.getPoint()).getEmptyStonesByPoint();
        GameLogger.logg("wielkosc lity z pustymi oddachemi dla pustego kamienia " + stone.toString() + " " + breatchs.size() + " to pkt " + breatchs.toString());
        chainOfStone.setChainsBreaths(breatchs);
        stone.setChainOfStone(chainOfStone);
        chainOfStone.addStoneToChain(stone);
        state.addChainOfStone(chainOfStone);
        state.addStone(stone);
    }

    public void celanUp(){
        for(ChainOfStone chainOfStone: state.getChainsOfStones()){
            if(chainOfStone.getNumberOfBreaths()==0){
                clean(chainOfStone);
            }
        }
        state.setChainOfStones(state.getChainsOfStones().stream().filter(p->p.getNumberOfBreaths()>0).collect(Collectors.toCollection(ArrayList::new)));
    }

    private void clean(ChainOfStone chainOfStone) {
        List<Point> pointList  = chainOfStone.getStonesInChain().stream().map(p->p.getPoint()).collect(Collectors.toCollection(ArrayList::new));
        int x,y;
        for(Point p: pointList){
            x = p.getX();
            y = p.getY();
            state.getStones()[x][y] = new Stone(p,StoneType.EMPTY);
        }

//        usuwanie lancuchow z listy lancuchow
//        state.getChainsOfStones().remove(chainOfStone);

//        TODO dodanie oddechow do lancuchow sasiadujcych z nimi

        for(Point p: pointList){
            for(ChainOfStone chainOfStonee: new NeighborGeter(state,p).getChainsOfStones()){
                chainOfStone.addBreath(state.getStones()[p.getX()][p.getY()]);
            }
        }

        for(ChainOfStone chainOfStonee: state.getChainsOfStones()){
//            chainOfStone.setChainsBreaths(chainOfStone.getChainsBreaths().stream().distinct().collect(Collectors.toCollection(ArrayList::new)));
            chainOfStone.removeDups();
        }
    }


    public GameState getState() {
        return this.state;
    }
}
