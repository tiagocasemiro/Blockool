package br.com.blockool.blockool.game;

import br.com.blockool.blockool.entity.Block;
import br.com.blockool.blockool.entity.Scene;

/**
 * Created by tiago.casemiro on 18/07/2018.
 */

public class GameRulesProcess {
    private Block[][] blocks;
    private Scene scene;
    private SceneListener sceneListener;

    int selectedLinePosition;
    int selectedColumPosition;

    public GameRulesProcess(SceneListener sceneListener) {
        this.sceneListener = sceneListener;
        this.scene = new Scene();

        Block block = new Block();
        block.setColor(Block.Color.BLUE);
        blocks = new Block[20][10];
        blocks[selectedLinePosition][selectedColumPosition] = block;

        for(int linha = 1; linha < 20; linha++) {
            for(int coluna = 1; coluna < 10; coluna++) {
                blocks[linha][coluna] = new Block();
            }
        }
        scene.setBlocks(blocks);
    }

    public void processInputRight() {
        if(selectedColumPosition + 1 < 10) {
            chageBlockPosition(selectedLinePosition, selectedColumPosition, selectedLinePosition, ++selectedColumPosition);
            sceneListener.onScene(scene);
        }
    }

    public void processInputLeft() {
        if(selectedColumPosition - 1 >= 0) {
            chageBlockPosition(selectedLinePosition, selectedColumPosition, selectedLinePosition, --selectedColumPosition);
            sceneListener.onScene(scene);
        }
    }

    public void processInputUp() {
        if(selectedLinePosition - 1 >= 0) {
            chageBlockPosition(selectedLinePosition, selectedColumPosition, --selectedLinePosition, selectedColumPosition);
            sceneListener.onScene(scene);
        }
    }

    public void processInputDown() {
        if(selectedLinePosition + 1 < 20) {
            chageBlockPosition(selectedLinePosition, selectedColumPosition, ++selectedLinePosition, selectedColumPosition);
            sceneListener.onScene(scene);
        }
    }

    public void processGame() {
        //sceneListener.onScene(scene);
    }

    public void processGravity() {
        //sceneListener.onScene(scene);
    }

    private void chageBlockPosition(int initialLine, int initialColum, int finalLine, int finalColum)  {
       if(initialLine >= 0 && initialLine < 20 && finalLine >= 0 && finalLine < 20 && initialColum >= 0 && initialColum < 10 && finalColum >= 0 && finalColum < 10 ) {
           Block temporaryFinalBlock = blocks[finalLine][finalColum];
           Block temporaryInitialBlock = blocks[initialLine][initialColum];
           blocks[finalLine][finalColum] = temporaryInitialBlock;
           blocks[initialLine][initialColum] = temporaryFinalBlock;
       }
    }

    public interface SceneListener {
        void onScene(Scene scene);
    }
}
