package com.kynsof.shift.application.command.turn.create;

import java.util.Random;

public class RandomNumberGenerator {

    public static int generateRandomNumber(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Min must be less than or equal to max");
        }
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }


}