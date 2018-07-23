package br.com.blockool.blockool.entity;

import java.util.Random;

/**
 * Created by tiago.casemiro on 19/07/2018.
 */

public class Piece {
    private Block top;
    private Block medium;
    private Block bottom;
    int line;
    int colum;

    public void setPosition(int colum, int line) {
        this.colum = colum;
        this.line = line;
    }

    public Block getTop() {
        return top;
    }

    public void setTop(Block top) {
        this.top = top;
    }

    public Block getMedium() {
        return medium;
    }

    public void setMedium(Block medium) {
        this.medium = medium;
    }

    public Block getBottom() {
        return bottom;
    }

    public void setBottom(Block bottom) {
        this.bottom = bottom;
    }

    public int getLine() {
        return line;
    }

    public int getColum() {
        return colum;
    }

    public int toDecreaseColum() {
        return --colum;
    }

    public int toIncreaseColum() {
        return ++colum;
    }

    public int toDecreaseLine() {
        return --line;
    }

    public int toIncreaseLine() {
        return ++line;
    }
}
