package br.com.blockool.blockool.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tiago.casemiro on 24/07/2018.
 */

public class Combination {
    Map<String, Block> blocks;

    public Combination() {
        this.blocks = new HashMap<>();
    }

    public void add(Block block, int line, int colum) {
        String positionLine = String.valueOf(line);
        String positionColum = String.valueOf(colum);
        if(positionLine.length() < 2) {
            positionLine = "0" + positionLine;
        }
        if(positionColum.length() < 2) {
            positionColum = "0" + positionColum;
        }
        String position = positionLine + positionColum;

        if(!blocks.containsKey(position)) {
            blocks.put(position, block);
        }
    }

    public void getAllPosition(All.Position all) {
        for (Map.Entry<String, Block> entry : blocks.entrySet()) {
            String position = entry.getKey();
            String line = position.substring(0, 2);
            String colum = position.substring(2);

            all.on(Integer.parseInt(line),  Integer.parseInt(colum));
        }
    }

    public int totalBlocks() {
        return blocks.size();
    }

    public interface All {
        interface Position {
            void on(int line, int colum);
        }
    }
}