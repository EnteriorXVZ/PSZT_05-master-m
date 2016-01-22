package go.ai;

import com.sun.xml.internal.bind.v2.TODO;
import go.core.*;
import go.rools.PointFilterSingleton;
import go.utils.RandomPointGenerator;
import go.utils.StateBuilder;

import javax.swing.tree.TreeNode;
import java.util.List;
import java.util.Random;

/**
 * Created by Rados³aw on 2016-01-10.
 */
public class ComputerPlayer {
    private static ComputerPlayer player;

    private ComputerPlayer(){}

    public static ComputerPlayer getInstance() {
        if(player==null){
            player = new ComputerPlayer();
        }
        return player;
    }

//    byc moze jest ok
    public void play(Point p){

        GameState before = MainPlate.getInstance().getActualGameState();

        long begintTime = System.nanoTime();
        long endTime ;
//        GameState state = MainPlate.getInstance().getActualGameState();
//        GameState clone = state.clone();
//        System.out.println(before);
//        NodeTree nodeTree = new NodeTree(, StoneType.BLACK);
//
//
//        do{
//            nodeTree.selectAction();
//            endTime = System.nanoTime();
//        }while ((endTime-begintTime)<3000);
////        MainPlate.getInstance().restoreFromState(nodeTree.getState().clone());
//        NodeTree tmp = nodeTree.getWiner();
//        Point point = tmp.getInsertedPoint();
//        if(point==null){
//            System.out.println("null");
//        }


//        MainPlate.getInstance().restoreFromState(clone);
//        System.out.println(clone);

//        MainPlate.getInstance().insertStone(new Stone(point,StoneType.WHITE));
//        MainPlate.getInstance().insertStone(new Stone(p,StoneType.BLACK));]]


//        MainPlate.getInstance().restoreFromState(before);


        Random random = new Random();

        List<Point> pointList = PointFilterSingleton.getInstance().filter(before,StoneType.WHITE);

        RandomPointGenerator randomPointGenerator = new RandomPointGenerator();
        Point pp  = randomPointGenerator.getRandomPoint(pointList);

        if(!pp.equals(new Point(-1,-1)))
        MainPlate.getInstance().insertStone(new Stone(pp,StoneType.WHITE));

        GameState tmpState = MainPlate.getInstance().getActualGameState();
        System.out.println(tmpState);

        System.out.println();
//        TODO implementacja logiki gry komputra
    }
}
