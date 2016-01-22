package go.utils;

import go.core.Point;

import java.util.List;
import java.util.Random;

/**
 * Created by Rados³aw on 2016-01-12.
 */
public class RandomPointGenerator {
    private Random random;

    public RandomPointGenerator(){
        int val = RandomGeneratorSingleton.getInstance().getRandomInt();
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {

        }
        random = new Random(System.currentTimeMillis());
    }



    public Point getRandomPoint(List<Point> points){
        if(points.size() == 0){
            return new Point(-1,-1);
        }else {
            int x = getRandomNumber(points.size());
            return points.get(x);
        }
    }

    private int getRandomNumber(int size) {
        return random.nextInt(size);
    }
}
