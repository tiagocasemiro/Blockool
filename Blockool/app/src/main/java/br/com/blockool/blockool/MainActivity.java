package br.com.blockool.blockool;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private GameFragment gameFragment;
    private InputGestureProcess inputGestureProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        gameFragment = new GameFragment();

        inputGestureProcess = new InputGestureProcess(gameFragment);

        fragmentManager
            .beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.game, gameFragment)
            .commit();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
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
}
















