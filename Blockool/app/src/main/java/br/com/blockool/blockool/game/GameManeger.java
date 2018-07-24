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
    private final int NUMBER_COLUNS = 10;
    private final int NUMBER_LINES = 20;
    private MoveDownListener moveDownListener;

    public GameManeger(MoveDownListener moveDownListener) {
        this.moveDownListener = moveDownListener;
        this.blocks = new Block[NUMBER_LINES][NUMBER_COLUNS];
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

    public boolean canMoveToRight(Piece piece) {
        return piece.getColum() + 1 < NUMBER_COLUNS && blocks[piece.getLine()][piece.getColum() + 1] == null;
    }

    public boolean canMoveToLeft(Piece piece) {
        return piece.getColum() - 1 >= 0 && blocks[piece.getLine()][piece.getColum() - 1] == null;
    }

    public void canMoveToDown(Piece piece) {
        if(piece.getLine() + 1 < NUMBER_LINES && blocks[piece.getLine() + 1][piece.getColum()] == null) {
            moveDownListener.moveToDown();
        } else {
            if(piece.getLine() == DEFAULT_Y) {
                moveDownListener.gameOver();
            }  else if(haveCombinationBlocks()) {
                moveDownListener.dropCombinationBlocks();
            } else {
                moveDownListener.newPieceOnGame();
            }
        }
    }

    private boolean haveCombinationBlocks() {
        return Boolean.FALSE;
    }

    public interface MoveDownListener {
        void moveToDown();
        void newPieceOnGame();
        void gameOver();
        void dropCombinationBlocks();
    }
}
