package br.com.blockool.blockool.game;

import android.widget.Toast;

import br.com.blockool.blockool.entity.Block;
import br.com.blockool.blockool.entity.Piece;
import br.com.blockool.blockool.entity.Scene;

/**
 * Created by tiago.casemiro on 18/07/2018.
 */

public class GameRulesProcess {
    private Scene scene;
    private SceneListener sceneListener;
    private Piece piece;
    private GameManeger gameManeger;

    public GameRulesProcess(SceneListener sceneListener) {
        this.sceneListener = sceneListener;
        this.scene = new Scene();
        this.gameManeger = new GameManeger();
        this.piece = newRandonPiece();
        this.gameManeger.putPieceOnBlocks(piece);
        this.scene.setBlocks(gameManeger.getBlocks());
    }

    public void processRight() {
        if(gameManeger.canMoveToRight(piece)) {
            Integer line = new Integer(piece.getLine());
            Integer oldColum =  piece.getColum();
            Integer newColum = piece.toIncreaseColum();

            gameManeger.changeBlockPosition((line - 2), oldColum, (line - 2), newColum);
            gameManeger.changeBlockPosition((line - 1), oldColum, (line - 1), newColum);
            gameManeger.changeBlockPosition(line, oldColum, line, newColum);
            sceneListener.onScene(scene);
        }
    }

    public void processLeft() {
        if(gameManeger.canMoveToLeft(piece)) {
            Integer line = new Integer(piece.getLine());
            Integer oldColum = piece.getColum();
            Integer newColum = piece.toDecreaseColum();

            gameManeger.changeBlockPosition((line - 2), oldColum, (line - 2), newColum);
            gameManeger.changeBlockPosition((line - 1), oldColum, (line - 1), newColum);
            gameManeger.changeBlockPosition(line, oldColum, line, newColum);
            sceneListener.onScene(scene);
        }
    }

    public void processUp() {
        Integer colum = piece.getColum();
        Integer line = piece.getLine();

        gameManeger.changeBlockPosition((line - 2), colum, (line - 1), colum);
        gameManeger.changeBlockPosition((line - 1), colum, line, colum);

        sceneListener.onScene(scene);
    }

    public void processDown() {
        if(gameManeger.canMoveToDown(piece)) {
            Integer colum = piece.getColum();
            Integer oldLine = piece.getLine();
            Integer newLine = piece.toIncreaseLine();

            gameManeger.changeBlockPosition(oldLine, colum, newLine, colum);
            gameManeger.changeBlockPosition((oldLine - 1), colum, (newLine - 1), colum);
            gameManeger.changeBlockPosition((oldLine - 2), colum, (newLine - 2), colum);
            sceneListener.onScene(scene);
        } else {
            if(piece.getLine() == 2) {
                scene.gameOver();
                sceneListener.onGameOver();
            } else {
                this.piece = newRandonPiece();
                this.gameManeger.putPieceOnBlocks(piece);
                this.scene.setBlocks(gameManeger.getBlocks());
            }
        }
    }

    public void processGame() {
        sceneListener.onScene(scene);
    }

    public interface SceneListener {
        void onScene(Scene scene);
        void onGameOver();
    }

    private Piece newRandonPiece() {
        Piece piece = new Piece();
        piece.setPosition(gameManeger.DEFAULT_X, gameManeger.DEFAULT_Y);
        piece.setTop(new Block());
        piece.setMedium(new Block());
        piece.setBottom(new Block());

        return piece;
    }
}
