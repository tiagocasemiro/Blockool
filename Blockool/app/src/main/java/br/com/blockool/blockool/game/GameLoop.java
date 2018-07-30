package br.com.blockool.blockool.game;

import android.app.Activity;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by tiago.casemiro on 18/07/2018.
 */
public class GameLoop {
    private Timer timer;
    private boolean isRunning = false;
    private LoopListener loopListener;
    private Activity activity;

    public GameLoop(LoopListener loopListener, Activity activity) {
        this.loopListener = loopListener;
        this.activity = activity;
    }

    public void init() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                if (isRunning) {
                    loopListener.onLoop();
                }
            }
        }, 100, 100);
    }

    public void start() {
        isRunning = true;
    }

    public void pause() {
        isRunning = false;
    }

    public void stop() {
        timer.cancel();
        timer.purge();
    }

    public boolean isRunning() {
        return isRunning && timer != null;
    }

    public interface LoopListener {
        void onLoop();
    }
}
