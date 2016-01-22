package go;

import go.core.MainPlate;
import go.core.Point;
import go.core.Stone;
import go.core.StoneType;
import go.gui.MainFrame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
//        System.out.println("Hello World!");
//        List list = Arrays.asList(1,2,3,4,4);
//        ArrayList<Integer> a = (ArrayList<Integer>) list.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
//        a.stream().forEach(o -> System.out.println(o));
//        System.out.println("koniec");


        MainPlate mainPlate = MainPlate.getInstance();
//        mainPlate.insertStone(new Stone(new Point(1,2), StoneType.WHITE));
//        mainPlate.insertStone(new Stone(new Point(0,2), StoneType.WHITE));
        System.out.println("initial ");

        System.out.println(mainPlate.getActualGameState());

        System.out.println("end initial");

        MainFrame frame = new MainFrame();
    }
}
