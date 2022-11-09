package util;

import java.util.Random;

public class RandomInstance {
    private static RandomInstance instance = new RandomInstance();
    private Random rnd = new Random();

    public static RandomInstance get() { return instance; }

    public Random getRnd() {
        return rnd;
    }
}
