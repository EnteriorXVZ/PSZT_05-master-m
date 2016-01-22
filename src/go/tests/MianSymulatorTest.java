package go.tests;

import go.ai.GameSymulator;
import go.core.*;

import java.util.Random;

/**
 * Created by Rados³aw on 2016-01-13.
 */
public class MianSymulatorTest {
    public static void main(String[] args) {

//        Random random = new Random();
//        int px, py;
//        int liczbaSymulacji = 3000;
//        int licznik = 0;
//        int i = 0;
////        MainPlate plate = MainPlate.getInstance();
////        plate.insertStone(new Stone(new Point(2, 2), StoneType.WHITE));
//        int czarnyWgrane = 0;
//        int bialeWygrane = 0;
//        int remisy = 0;
//        int val = 0;
//        while (val<5) {
//
//            val++;
//
//            px = random.nextInt(5);
//            py = random.nextInt(5);
//
//            i++;
//            long time1 = System.currentTimeMillis();
////            MainPlate mainPlate = MainPlate.getInstance();
////            mainPlate.insertStone(new Stone(new Point(2, 2), StoneType.WHITE));
//            GameState state = new GameState();
//            state.initialize();
//
//            state.addStone(new Stone(new Point(px,py),StoneType.WHITE));
//
//            GameSymulator symulator = new GameSymulator(state, StoneType.BLACK);
//            StoneType winnerType = symulator.symulation();
//            state = symulator.getGameState();
//            if (winnerType == StoneType.BLACK) {
//                czarnyWgrane++;
//            }
//            if (winnerType == StoneType.WHITE) {
//                bialeWygrane++;
//            }
//            if (winnerType == StoneType.EMPTY) {
//                remisy++;
//            }
////            Prisoners prisoners = state.getPrisoners();
////            System.out.println(" teraz wypiszemy plansze ");
////            System.out.println(state);
////            System.out.println();
////            System.out.println(" wygral kolor " + winnerType);
////            System.out.println(prisoners);
//            long time2 = System.currentTimeMillis();
//            long tiem3 = time2 - time1;
//            licznik += tiem3;
////            System.out.println(" czas symulacji " + (time2 - time1));
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//
//            }
//        }
//        System.out.println("ilosc symulacji " + i);
//        System.out.println("biale wygrane "+ bialeWygrane);
//        System.out.println("czarne wygrane "+ czarnyWgrane);
//        System.out.println(" remisy "+remisy);

        MainPlate plate = MainPlate.getInstance();
        plate.insertStone(new Stone(new Point(2,2),StoneType.WHITE));
        GameState state = plate.getActualGameState();
        GameSymulator symulator = new GameSymulator(state,StoneType.BLACK);
        StoneType type = symulator.symulation();
        state = symulator.getGameState();
        System.out.println(" wynik po symulacji ");
        System.out.println(state);
        System.out.println("wygrany "+ type);
        System.out.println("wiezniowie "+state.getPrisoners());
    }
}
