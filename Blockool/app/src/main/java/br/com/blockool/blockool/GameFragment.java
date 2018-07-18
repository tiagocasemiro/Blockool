package br.com.blockool.blockool;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class GameFragment extends Fragment implements GameLoop.LoopListener{
    private GameLoop gameLoop;
    private DrawnSchene drawnSchene;
    private FrameLayout gameView;
    private Block[][] blocks;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_game, container, false);

        gameView = (FrameLayout) root.findViewById(R.id.gameView);
        gameLoop = new GameLoop(this);
        drawnSchene = new DrawnSchene(getContext());

        blocks = new Block[20][10];

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        gameLoop.start();
    }

    @Override
    public void onLoop() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View view = drawnSchene.getSchene(blocks);
                gameView.removeAllViews();
                gameView.addView(view);
            }
        });
    }
}
