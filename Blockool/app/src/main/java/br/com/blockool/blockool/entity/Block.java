package br.com.blockool.blockool.entity;

/**
 * Created by tiago.casemiro on 18/07/2018.
 */

public class Block {
    private Color color;

    public Block() {
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
}
