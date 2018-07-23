package br.com.blockool.blockool;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import br.com.blockool.blockool.entity.Scene;
import br.com.blockool.blockool.game.DrawnSchene;
import br.com.blockool.blockool.game.GameLoop;
import br.com.blockool.blockool.game.GameRulesProcess;
import br.com.blockool.blockool.game.InputGestureProcess;

public class GameFragment extends Fragment implements GameLoop.LoopListener, InputGestureProcess.Listener, GameRulesProcess.SceneListener{
    private GameLoop gameLoop;
    private DrawnSchene drawnSchene;
    private FrameLayout gameView;
    private GameRulesProcess gameRulesProcess;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_game, container, false);

        gameView = (FrameLayout) root.findViewById(R.id.gameView);
        gameLoop = new GameLoop(this, getActivity());
        drawnSchene = new DrawnSchene(getContext());
        gameRulesProcess = new GameRulesProcess(this);
        gameRulesProcess.processGame();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        gameLoop.start();
    }

    @Override
    public void onLoop() {
        gameRulesProcess.processGame();
    }

    @Override
    public void onInputDown() {
        gameRulesProcess.processInputDown();
    }

    @Override
    public void onInputUp() {
        gameRulesProcess.processInputUp();
    }

    @Override
    public void onInputRight() {
        gameRulesProcess.processInputRight();
    }

    @Override
    public void onInputLeft() {
        gameRulesProcess.processInputLeft();
    }

    @Override
    public void onScene(Scene scene) {
        View view = drawnSchene.getSchene(scene.getBlocks());
        gameView.removeAllViews();
        gameView.addView(view);
    }
}
