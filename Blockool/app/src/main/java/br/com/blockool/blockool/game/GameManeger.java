package br.com.blockool.blockool.game;

import java.util.ArrayList;
import java.util.List;

import br.com.blockool.blockool.entity.Block;
import br.com.blockool.blockool.entity.Combination;
import br.com.blockool.blockool.entity.Gravity;
import br.com.blockool.blockool.entity.Level;
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
    private Level level;
    private MoveDownListener moveDownListener;
    private GameRulesProcess.GameListener gameListener;
    private int score;

    public GameManeger(MoveDownListener moveDownListener, GameRulesProcess.GameListener gameListener) {
        this.moveDownListener = moveDownListener;
        this.gameListener = gameListener;
        this.blocks = new Block[NUMBER_LINES][NUMBER_COLUNS];
        this.level = new Level();
    }

    public Block[][] getBlocks() {
        return blocks;
    }

    public void changeBlockPosition(int initialLine, int initialColum, int finalLine, int finalColum) {
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

    public void moveToDown(Piece piece) {
        if(piece.getLine() + 1 < NUMBER_LINES && blocks[piece.getLine() + 1][piece.getColum()] == null) {
            moveDownListener.moveToDown();
        } else {
            if(haveCombination()) {
                regroupBlocks();
            } else if(piece.getLine() == DEFAULT_Y) {
                moveDownListener.gameOver();
            } else {
                moveDownListener.newPieceOnGame();
            }
        }
    }

    public void gravityEffect(Piece piece, Gravity gravity) {
        if(piece.getLine() + 1 < NUMBER_LINES && blocks[piece.getLine() + 1][piece.getColum()] == null) {
            if(gravity.acelerationGravity(level)) {
                moveDownListener.moveToDown();
            }
        } else {
            if(haveCombination()) {
                regroupBlocks();
            } else if(piece.getLine() == DEFAULT_Y) {
                moveDownListener.gameOver();
            } else {
                moveDownListener.newPieceOnGame();
            }
        }
    }

    public boolean haveCombination() {
        List<Combination> combinations = new ArrayList<Combination>();

        for(int linha = 0; linha < 20; linha++) {
            for(int coluna = 0; coluna < 10; coluna++) {
                Block current = blocks[linha][coluna];

                //direita
                if(coluna < (NUMBER_COLUNS - 2)) {
                    if (compareBlocks(current, blocks[linha][(coluna + 1)])) {
                        if (compareBlocks(current, blocks[linha][(coluna + 2)])){
                            Combination combination = new Combination();
                            combination.add(current, linha, coluna);
                            combination.add(blocks[linha][(coluna + 1)], linha, (coluna + 1));
                            combination.add(blocks[linha][(coluna + 2)], linha, (coluna + 2));
                            combinations.add(combination);
                        }
                    }
                }

                //inferior direito
                if(coluna < (NUMBER_COLUNS - 2) && linha < (NUMBER_LINES - 2)) {
                    if (compareBlocks(current, blocks[(linha + 1)][(coluna + 1)])) {
                        if (compareBlocks(current, blocks[(linha + 2)][(coluna + 2)])){
                            Combination combination = new Combination();
                            combination.add(current, linha, coluna);
                            combination.add(blocks[(linha + 1)][(coluna + 1)], (linha + 1), (coluna + 1));
                            combination.add(blocks[(linha + 2)][(coluna + 2)], (linha + 2), (coluna + 2));
                            combinations.add(combination);
                        }
                    }
                }

                //baixo
                if(linha < (NUMBER_LINES - 2)) {
                    if (compareBlocks(current, blocks[(linha + 1)][coluna])) {
                        if (compareBlocks(current, blocks[(linha + 2)][coluna])){
                            Combination combination = new Combination();
                            combination.add(current, linha, coluna);
                            combination.add(blocks[(linha + 1)][coluna], (linha + 1), coluna);
                            combination.add(blocks[(linha + 2)][coluna], (linha + 2), coluna);
                            combinations.add(combination);
                        }
                    }
                }

                //inferior esquerdo
                if(coluna > 1 && linha < (NUMBER_LINES - 2)) {
                    if (compareBlocks(current, blocks[(linha + 1)][(coluna - 1)])) {
                        if (compareBlocks(current, blocks[(linha + 2)][(coluna - 2)])){
                            Combination combination = new Combination();
                            combination.add(current, linha, coluna);
                            combination.add(blocks[(linha + 1)][(coluna - 1)], (linha + 1), (coluna - 1));
                            combination.add(blocks[(linha + 2)][(coluna - 2)], (linha + 2), (coluna - 2));
                            combinations.add(combination);
                        }
                    }
                }

                //esquerda
                if(coluna > 1) {
                    if (compareBlocks(current, blocks[linha][(coluna - 1)])) {
                        if (compareBlocks(current, blocks[linha][(coluna - 2)])){
                            Combination combination = new Combination();
                            combination.add(current, linha, coluna);
                            combination.add(blocks[linha][(coluna - 1)], linha, (coluna - 1));
                            combination.add(blocks[linha][(coluna - 2)], linha, (coluna - 2));
                            combinations.add(combination);
                        }
                    }
                }

                //superio esquerdo
                if(linha > 1 && coluna > 1) {
                    if (compareBlocks(current, blocks[(linha - 1)][(coluna - 1)])) {
                        if (compareBlocks(current, blocks[(linha - 2)][(coluna - 2)])){
                            Combination combination = new Combination();
                            combination.add(current, linha, coluna);
                            combination.add(blocks[(linha - 1)][(coluna - 1)], (linha - 1), (coluna - 1));
                            combination.add(blocks[(linha - 2)][(coluna - 2)], (linha - 2), (coluna - 2));
                            combinations.add(combination);
                        }
                    }
                }

                //cima
                if(linha > 1) {
                    if (compareBlocks(current, blocks[linha - 1][coluna])) {
                        if (compareBlocks(current, blocks[linha - 2][coluna])){
                            Combination combination = new Combination();
                            combination.add(current, linha, coluna);
                            combination.add(blocks[(linha - 1)][coluna], (linha - 1), coluna);
                            combination.add(blocks[(linha - 2)][coluna], (linha - 2), coluna);
                            combinations.add(combination);
                        }
                    }
                }

                //Superior direito
                if(linha > 1 && coluna < (NUMBER_COLUNS - 2)) {
                    if (compareBlocks(current, blocks[(linha - 1)][(coluna + 1)])) {
                        if (compareBlocks(current, blocks[(linha - 2)][(coluna + 2)])){
                            Combination combination = new Combination();
                            combination.add(current, linha, coluna);
                            combination.add(blocks[(linha - 1)][(coluna + 1)], (linha - 1), (coluna + 1));
                            combination.add(blocks[(linha - 2)][(coluna + 2)], (linha - 2), (coluna + 2));
                            combinations.add(combination);
                        }
                    }
                }
            }
        }

        int partialScore = 0;

        for (Combination combination: combinations) {
            partialScore = partialScore + combination.totalBlocks();
            combination.getAllPosition(new Combination.All.Position() {
                @Override
                public void on(int line, int colum) {
                    blocks[line][colum] = null;
                }
            });
        }

        if(combinations.size() > 0) {
            score = score + (partialScore / 2);
            level.processScore(score);
            gameListener.onScoreChange(score, level.label());
        }

        return combinations.size() > 0;
    }

    public void regroupBlocks() {
        for(int colum = 0; colum < NUMBER_COLUNS; colum++) {
            for(int line = (NUMBER_LINES - 1); line >= 0; line--) {
                Block current = blocks[line][colum];
                helpLoop : for(int lineHelp = line - 1; lineHelp >= 0; lineHelp--) {
                    Block currentHelp = blocks[lineHelp][colum];
                    if(current == null ) {
                        if(currentHelp != null) {
                            changeBlockPosition(lineHelp, colum, line, colum);
                            break helpLoop;
                        }
                    } else {
                        break helpLoop;
                    }
                }
            }
        }
    }

    private boolean compareBlocks(Block current, Block neighbors) {
        if(current != null && neighbors != null) {
            return current.getColor().equals(neighbors.getColor());
        }

        return Boolean.FALSE;
    }

    public interface MoveDownListener {
        void moveToDown();
        void newPieceOnGame();
        void gameOver();
    }
}