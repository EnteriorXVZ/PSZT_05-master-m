package go.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Rados³aw on 2016-01-13.
 */
public class RandomGeneratorSingleton {
    private static RandomGeneratorSingleton generator;
    private Random random;
    private List<Integer> inserterList = new ArrayList<>();


    private RandomGeneratorSingleton(){
        random = new Random(System.currentTimeMillis());
        init();
    }

    private void init() {
        for (int i = 0; i < 1000; i++) {
            inserterList.add(random.nextInt());
        }
    }

    public int getRandomInt(){
        Random random = new Random();
        int val = inserterList.get(random.nextInt(inserterList.size()-1));
        return val;
    }

    public static RandomGeneratorSingleton getInstance() {
        if(generator == null){
            generator = new RandomGeneratorSingleton();
        }
        return generator;
    }
}
