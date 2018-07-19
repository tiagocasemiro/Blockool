package br.com.blockool.blockool;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class GameFragment extends Fragment implements GameLoop.LoopListener, InputGestureProcess.Listener{
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
        gameRulesProcess =  new GameRulesProcess();

       /* View view = drawnSchene.getSchene(gameRulesProcess.scene().getBlocks());
        gameView.removeAllViews();
        gameView.addView(view);*/


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
    public void onGravityLoop() {
        gameRulesProcess.processGravity();
    }

    @Override
    public void onDown() {
        gameRulesProcess.processInputDown();
    }

    @Override
    public void onUp() {
        gameRulesProcess.processInputUp();
    }

    @Override
    public void onRight() {
        gameRulesProcess.processInputRight();
    }

    @Override
    public void onLeft() {
        gameRulesProcess.processInputLeft();
    }
}
