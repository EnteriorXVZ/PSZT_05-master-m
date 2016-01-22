package go.ai;

import go.core.GameState;
import go.core.Point;
import go.core.Stone;
import go.core.StoneType;
import go.rools.PointFilterSingleton;
import go.utils.StonesInserter;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by Rados�aw on 2016-01-20.
 */
public class NodeTree {
    private GameState state;
    private int numberOfVisit;
    private int winVisit;
    static double epsilon = 1e-6;
    private Random random = new Random();
    private StoneType nodeColor;
    private boolean isRoot;
    private boolean leaf;
    private Point insertedPoint;

    public NodeTree(GameState state, StoneType nodeColor) {
        this.state = state;
        this.nodeColor = nodeColor;
    }

    public NodeTree(GameState state, StoneType nodeColor, Point insertedPoint) {
        this.state = state;
        this.nodeColor = nodeColor;
        this.insertedPoint = insertedPoint;
    }

    List<NodeTree> children = new ArrayList<>();



    public void selectAction(){
        List<NodeTree> visited = new LinkedList<>();

        NodeTree cur = this;

        visited.add(this);

        while (!cur.isLeaf()){
            cur = cur.select();
            visited.add(cur);
        }

        cur.expand();

        NodeTree newNode = cur.select();
        visited.add(newNode);

        StoneType winer = winnStone(newNode);

        for(NodeTree nodeTree: visited){
            nodeTree.updateStats(winer);
        }

    }

//    rozrost drzewa dodajemny mu dzieci
    private void expand() {
        StoneType oponentType = getOponentType();
        PointFilterSingleton pointFilterSingleton = PointFilterSingleton.getInstance();
        List<Point> oponents = pointFilterSingleton.filter(state,oponentType);

        if(oponents.size()==0){
            this.leaf=true;
            return;
        }

        GameState tmpState;

        for(Point p: oponents){
            StonesInserter inserter = new StonesInserter((GameState) state.clone(),new Stone(p,oponentType));
            inserter.insert();
            tmpState = inserter.getState();
            NodeTree nodeTree = new NodeTree(tmpState,oponentType,p);
            this.children.add(nodeTree);
        }

    }

    private StoneType getOponentType(){
        return nodeColor==StoneType.BLACK? StoneType.WHITE:StoneType.BLACK;
    }

//    czy jest liscim?
    private boolean isLeaf() {
        return children.isEmpty();
    }


    private NodeTree select() {
        NodeTree selected = null;
        double bestValue = Double.MIN_VALUE;
        for (NodeTree c : children) {
            double uctValue =
                    c.getWinVisit() / (c.getNumberOfVisit() + epsilon) +
                            Math.sqrt(Math.log(getWinVisit()+1) / (c.getWinVisit() + epsilon)) +
                            random.nextDouble() * epsilon;
            // small random number to break ties randomly in unexpanded nodes
            // System.out.println("UCT value = " + uctValue);
            if (uctValue > bestValue) {
                selected = c;
                bestValue = uctValue;
            }
        }
        // System.out.println("Returning: " + selected);
        return selected;
    }


    public StoneType winnStone(NodeTree nodeTree){
        GameSymulator symulator = new GameSymulator(this.state.clone(),getOponentType());
        return symulator.symulation();
    }

    public int getNumberOfVisit() {
        return numberOfVisit;
    }

    public int getWinVisit() {
        return winVisit;
    }

    public void updateStats(StoneType value) {
        numberOfVisit++;
        if(value==StoneType.WHITE){
            winVisit++;
        }
//        winVisit += random.nextInt(2);
    }

    public GameState getState() {
        return state;
    }

    public static double getEpsilon() {
        return epsilon;
    }

    public Random getRandom() {
        return random;
    }

    public StoneType getNodeColor() {
        return nodeColor;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public List<NodeTree> getChildren() {
        return children;
    }

    public List<NodeTree> getWinners(){
        return children.stream().filter(ch->isOk(ch)).collect(Collectors.toCollection(ArrayList::new));
    }

    public NodeTree getWiner(){
        double best = Double.MIN_VALUE;
        NodeTree nodeTree= children.get(0);
        for (NodeTree t: children){
            if(t.getPercent()>best){
                nodeTree = t;
            }
        }
        return nodeTree;
//        return select();
    }

    public double getPercent(){
//        System.out.println("wygrane "+winVisit);
//        System.out.println(" odwiedzone "+numberOfVisit);
        return (double)winVisit/(double)numberOfVisit;
    }

    public boolean isOk(NodeTree ch) {
        return ((double)ch.getWinVisit()/(double)ch.numberOfVisit)>0;
    }

    public String napis(){
        String str = "";
        for(NodeTree t: children){
            str+= " punkt "+t.getInsertedPoint()+" odwiedzien "+t.getNumberOfVisit()+" zwyciestw "+t.getWinVisit()+" procentowo "+getPercent()+" \n";
        }
        return str;
    }

    public Point getInsertedPoint() {
        return insertedPoint;
    }
}
