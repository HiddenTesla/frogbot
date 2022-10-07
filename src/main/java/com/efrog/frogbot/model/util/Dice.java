package com.efrog.frogbot.model.util;

import java.util.Random;

public class Dice {

    private Random random = new Random();

    public boolean roll(int n) {
        return roll(1, n);
    }

    public boolean roll(int d, int n) {
        int k = random.nextInt(n);
        return k < d;
    }
}
