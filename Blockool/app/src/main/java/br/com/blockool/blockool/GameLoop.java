package br.com.blockool.blockool;

import android.app.Activity;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by tiago.casemiro on 18/07/2018.
 */
public class GameLoop {
    private Timer timer;
    private boolean isRunning = true;
    private LoopListener loopListener;

    public GameLoop(LoopListener loopListener) {
        this.loopListener = loopListener;
    }

    public void start() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                if (isRunning) {
                    loopListener.onLoop();
                }
            }
        }, 1000, 1000);
    }

    public void resume() {
        isRunning = true;
    }

    public void pause() {
        isRunning = false;
    }

    public void stop() {
        timer.cancel();
    }

    public interface LoopListener {
        void onLoop();
    }
}
