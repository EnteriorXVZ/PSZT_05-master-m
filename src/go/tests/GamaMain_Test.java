package go.tests;

import go.ai.NodeTree;
import go.core.*;

import javax.swing.tree.TreeNode;

/**
 * Created by Rados³aw on 2016-01-21.
 */
public class GamaMain_Test {
    public static void main(String[] args) {
        MainPlate mainPlate = MainPlate.getInstance();
        mainPlate.insertStone(new Stone(new Point(0,0), StoneType.BLACK));
        GameState state = mainPlate.getActualGameState();
        System.out.println(state);


        NodeTree tree = new NodeTree(state.clone_2(),StoneType.BLACK);

        for (int i = 0; i < 10; i++) {
            tree.selectAction();
        }

        NodeTree tr = tree.getWiner();

        Point p = tr.getInsertedPoint();


        mainPlate.insertStone(new Stone(p,StoneType.WHITE));

        state = mainPlate.getActualGameState();

        System.out.println(state);
        System.out.println(p);
    }
}
