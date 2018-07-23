package br.com.blockool.blockool.game;

import br.com.blockool.blockool.entity.Block;
import br.com.blockool.blockool.entity.Piece;

/**
 * Created by tiago.casemiro on 23/07/2018.
 */

public class GameManeger {
    private Block[][] blocks;
    public final int DEFAULT_X = 4;
    public final int DEFAULT_Y = 2;

    public GameManeger() {
        blocks = new Block[20][10];

        for(int linha = 0; linha < 20; linha++) {
            for(int coluna = 0; coluna < 10; coluna++) {
                blocks[linha][coluna] = null;
            }
        }
    }

    public Block[][] getBlocks() {
        return blocks;
    }

    public void changeBlockPosition(int initialLine, int initialColum, int finalLine, int finalColum)  {
        if(initialLine >= 0 && initialLine < 20 && finalLine >= 0 && finalLine < 20 && initialColum >= 0 && initialColum < 10 && finalColum >= 0 && finalColum < 10 ) {
            Block temporaryFinalBlock = blocks[finalLine][finalColum];
            Block temporaryInitialBlock = blocks[initialLine][initialColum];
            blocks[finalLine][finalColum] = temporaryInitialBlock;
            blocks[initialLine][initialColum] = temporaryFinalBlock;
        }
    }

    public void putPieceOnBlocks(Piece piece) {
        Integer line = new Integer(DEFAULT_Y);
        blocks[(line - 2)][DEFAULT_X] = piece.getTop();
        blocks[(line - 1)][DEFAULT_X] = piece.getMedium();
        blocks[line][DEFAULT_X] = piece.getBottom();
    }
}
