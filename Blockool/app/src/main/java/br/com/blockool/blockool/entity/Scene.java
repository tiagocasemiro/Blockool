package br.com.blockool.blockool.entity;

/**
 * Created by tiago.casemiro on 19/07/2018.
 */

public class Scene {
    private Block[][] blocks;
    private Integer point;

    public Block[][] getBlocks() {
        return blocks;
    }

    public void setBlocks(Block[][] blocks) {
        this.blocks = blocks;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}
