package br.com.blockool.blockool;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.blockool.blockool.entity.Piece;
import br.com.blockool.blockool.game.DrawnSchene;
import br.com.blockool.blockool.game.GameRulesProcess;
import br.com.blockool.blockool.game.InputGestureProcess;

public class MainActivity extends AppCompatActivity {
    public static final String LISTENER = "LISTENER";

    private FragmentManager fragmentManager;
    private GameFragment gameFragment;
    private InputGestureProcess inputGestureProcess;
    private TextView score;
    private TextView level;
    private View nextBlockTop;
    private View nextBlockMedium;
    private View nextBlockBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score = (TextView) findViewById(R.id.score);
        level = (TextView) findViewById(R.id.level);
        nextBlockTop = findViewById(R.id.nextBlockTop);
        nextBlockMedium = findViewById(R.id.nextBlockmedium);
        nextBlockBottom = findViewById(R.id.nextBlockBottom);
        fragmentManager = getSupportFragmentManager();
        gameFragment = new GameFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(LISTENER, new GameRulesProcess.GameListener(){
            @Override
            public void onNextPiece(Piece piece) {
                nextPiece(piece);
            }

            @Override
            public void onScoreChange(int score, int level) {
                scoreChange(score, level);
            }
        });
        gameFragment.setArguments(bundle);
        inputGestureProcess = new InputGestureProcess(gameFragment);
        fragmentManager
            .beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.game, gameFragment)
            .commit();
    }

    public void playPause(View view) {
        if(gameFragment.isPlaying()) {
            gameFragment.onPauseGame();
            ((ImageView) view).setImageResource(android.R.drawable.ic_media_play);
        } else {
            gameFragment.onResumeGame();
            ((ImageView) view).setImageResource(android.R.drawable.ic_media_pause);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                inputGestureProcess.init(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                inputGestureProcess.process(event.getX(), event.getY());
                break;
        }

        return super.onTouchEvent(event);
    }

    public void nextPiece(Piece piece) {
        DrawnSchene drawnSchene = new DrawnSchene(this);
        drawnSchene.drawn(piece.getTop(), nextBlockTop);
        drawnSchene.drawn(piece.getMedium(), nextBlockMedium);
        drawnSchene.drawn(piece.getBottom(), nextBlockBottom);
    }

    public void scoreChange(int score, int level) {
        this.score.setText(String.valueOf(score));
        this.level.setText(String.valueOf(level));
    }
}