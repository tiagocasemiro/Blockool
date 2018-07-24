package br.com.blockool.blockool.entity;

import java.util.Random;

/**
 * Created by tiago.casemiro on 18/07/2018.
 */

public class Block {
    private Color color;

    public Block() {
        color = randomColor();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public enum Color {
        BLUE, RED, YELLOW, GREEN, MAGENTA, CYAN;
    }

    private Color randomColor() {
        switch (new Random().nextInt(6)) {
            case 1:
                return Block.Color.BLUE;

            case 2:
                return Block.Color.RED;

            case 3:
                return Block.Color.YELLOW;

            case 4:
                return Block.Color.GREEN;

            case 5:
                return Block.Color.MAGENTA;

            default:
                return Block.Color.CYAN;
        }
    }
}
