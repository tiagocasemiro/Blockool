package br.com.blockool.blockool.entity;

/**
 * Created by tiago.casemiro on 26/07/2018.
 */

public class Level {
    private final int START_SCORE = 0;
    private final int INCREMENT_LEVEL = 20;
    private final int MAX_LEVEL = 10;
    private final int FIRST_LEVEL = 1;
    private int value;

    public Level() {
        processScore(START_SCORE);
    }

    public void processScore(int score) {
        if((score / INCREMENT_LEVEL) < MAX_LEVEL) {
            value = MAX_LEVEL - ((score / INCREMENT_LEVEL));
        }
    }

    public int value() {
        return value;
    }

    public int label() {
        return  (MAX_LEVEL - value) + FIRST_LEVEL;
    }
}
