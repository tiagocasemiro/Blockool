package br.com.blockool.blockool;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import br.com.blockool.blockool.entity.Scene;
import br.com.blockool.blockool.game.DrawnScene;
import br.com.blockool.blockool.game.GameLoop;
import br.com.blockool.blockool.game.GameRulesProcess;
import br.com.blockool.blockool.game.InputGestureProcess;

public class GameFragment extends Fragment implements GameLoop.LoopListener, InputGestureProcess.Listener, GameRulesProcess.SceneListener{
    private GameLoop gameLoop;
    private DrawnScene drawnSchene;
    private FrameLayout gameView;
    private GameRulesProcess gameRulesProcess;
    private GameRulesProcess.GameListener gameListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            gameListener = (GameRulesProcess.GameListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement GameRulesProcess.GameListener ");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_game, container, false);
        gameView = (FrameLayout) root.findViewById(R.id.gameView);
        gameLoop = new GameLoop(this, getActivity());
        drawnSchene = new DrawnScene(getContext(), gameView);
        gameRulesProcess = new GameRulesProcess(this, gameListener);
        gameRulesProcess.processGame();
        gameLoop.init();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        gameLoop.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        gameLoop.pause();
    }

    @Override
    public void onLoop() {
        gameRulesProcess.processGravity();
        gameRulesProcess.processGame();
    }

    @Override
    public void onInputDown() {
        gameRulesProcess.processDown();
    }

    @Override
    public void onInputUp() {
        gameRulesProcess.processUp();
    }

    @Override
    public void onInputRight() {
        gameRulesProcess.processRight();
    }

    @Override
    public void onInputLeft() {
        gameRulesProcess.processLeft();
    }

    @Override
    public void onPauseGame() {
        gameLoop.pause();
    }

    @Override
    public void onResumeGame() {
        gameLoop.start();
    }

    @Override
    public void onScene(final Scene scene) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(scene.isGameRuuning())
                    drawnSchene.getScene(scene.getBlocks());
            }
        });
    }

    @Override
    public void onGameOver(final Scene scene) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gameLoop.pause();
                gameLoop.stop();
                drawnSchene.gameOverScene(scene.getBlocks());
            }
        });
    }

    public boolean isPlaying() {
        return gameLoop.isRunning();
    }
 }
