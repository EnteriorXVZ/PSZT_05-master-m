package go.tests;

import go.ai.NodeTree;
import go.core.GameState;
import go.core.Point;
import go.core.Stone;
import go.core.StoneType;
import go.utils.StateBuilder;
import go.utils.StonesInserter;

import java.util.List;

/**
 * Created by Rados³aw on 2016-01-20.
 */
public class NodeTreeTest {
    public static void main(String[] args) {
        StateBuilder builder = new StateBuilder();
        GameState state = builder.getState();

        StonesInserter inserter = new StonesInserter(state,new Stone(new Point(1,1), StoneType.BLACK));
        inserter.insert();

        state = inserter.getState();

        System.out.println(state);

        NodeTree nodeTree = new NodeTree(state,StoneType.BLACK);

        int val =50;

        for (int i = 0; i < val; i++) {
            nodeTree.selectAction();
        }

        List<NodeTree> children = nodeTree.getChildren();

        System.out.println("rozmiar dzieci naszego noda "+children.size());
        System.out.println("ilosc odwiedzen naszego roota "+nodeTree.getNumberOfVisit());
        System.out.println("ilosc zwycistw dla naszego roota "+nodeTree.getWinVisit());

//        System.out.println("rozmiar wygranych dzieci "+nodeTree.getWinners().size());

        System.out.println("Dziecko w punkcie  "+nodeTree.getWiner().getInsertedPoint()+ " z wynikiem "+nodeTree.getWiner().getPercent());

        System.out.println(nodeTree.napis());
    }
}
