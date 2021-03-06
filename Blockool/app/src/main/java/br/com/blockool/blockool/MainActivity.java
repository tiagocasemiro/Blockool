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
import br.com.blockool.blockool.game.Dialog;
import br.com.blockool.blockool.game.DrawnScene;
import br.com.blockool.blockool.game.GameRulesProcess;
import br.com.blockool.blockool.game.InputGestureProcess;

public class MainActivity extends AppCompatActivity implements GameRulesProcess.GameListener{
    private FragmentManager fragmentManager;
    private GameFragment gameFragment;
    private InputGestureProcess inputGestureProcess;
    private TextView score;
    private TextView level;
    private View nextBlockTop;
    private View nextBlockMedium;
    private View nextBlockBottom;
    private ImageView action;
    private Dialog dialog;
    private GameState gameState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score = (TextView) findViewById(R.id.score);
        level = (TextView) findViewById(R.id.level);
        action = (ImageView) findViewById(R.id.action);
        nextBlockTop = findViewById(R.id.nextBlockTop);
        nextBlockMedium = findViewById(R.id.nextBlockmedium);
        nextBlockBottom = findViewById(R.id.nextBlockBottom);
        fragmentManager = getSupportFragmentManager();
        dialog = new Dialog();
        initGame();
    }

    private void initGame() {
        gameFragment = new GameFragment();
        gameState = GameState.RUNNING;
        inputGestureProcess = new InputGestureProcess(gameFragment);
        action.setImageResource(android.R.drawable.ic_media_play);
        fragmentManager
            .beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.game, gameFragment)
            .commit();
        score.setText("0");
        level.setText("0");
    }

    public void playPause(final View view) {
        if(gameState == GameState.FINISH) {
            initGame();
        } else if(gameState == GameState.RUNNING) {
            gameFragment.onPauseGame();
            action.setImageResource(android.R.drawable.ic_media_play);
            dialog.create(this).button("RESUME").message("PAUSE").listener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gameFragment.onResumeGame();
                    action.setImageResource(android.R.drawable.ic_media_pause);
                    gameState = GameState.RUNNING;
                }
            }).show();
            gameState = GameState.PAUSED;
        } else if (gameState == GameState.PAUSED) {
            gameFragment.onResumeGame();
            action.setImageResource(android.R.drawable.ic_media_pause);
            gameState = GameState.RUNNING;
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
                inputGestureProcess.finishMove();
                break;
            case MotionEvent.ACTION_MOVE:
                inputGestureProcess.process(event.getX(), event.getY());
                break;
            case (MotionEvent.ACTION_CANCEL) :
                inputGestureProcess.finishMove();
                break;
            case (MotionEvent.ACTION_OUTSIDE) :
                inputGestureProcess.finishMove();
                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void onNextPiece(final Piece piece) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DrawnScene.drawn(piece.getTop(), nextBlockTop);
                DrawnScene.drawn(piece.getMedium(), nextBlockMedium);
                DrawnScene.drawn(piece.getBottom(), nextBlockBottom);
            }
        });
    }

    @Override
    public void onGameOver(final Piece piece) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DrawnScene.drawnGameOver(piece.getTop(), nextBlockTop);
                DrawnScene.drawnGameOver(piece.getMedium(), nextBlockMedium);
                DrawnScene.drawnGameOver(piece.getBottom(), nextBlockBottom);
                gameState = GameState.FINISH;
                action.setImageResource(R.drawable.restart);
                dialog.create(MainActivity.this).button("RESTART").message("GAME OVER").listener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initGame();
                    }
                }).show();
            }
        });
    }

    @Override
    public void onScoreChange(final int score, final int level) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MainActivity.this.score.setText(String.valueOf(score));
                MainActivity.this.level.setText(String.valueOf(level));
            }
        });
    }

    public enum GameState {
        RUNNING, PAUSED, FINISH
    }
}