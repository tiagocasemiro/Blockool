package br.com.blockool.blockool.game;

import android.app.Activity;
import android.os.Handler;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by tiago.casemiro on 18/07/2018.
 */
public class GameLoop {
    private Timer timer;
    private boolean isRunning = true;
    private LoopListener loopListener;
    private Activity activity;

    public GameLoop(LoopListener loopListener, Activity activity) {
        this.loopListener = loopListener;
        this.activity = activity;
    }

    public void start() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                if (isRunning) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        loopListener.onLoop();
                        }
                    });
                }
            }
        }, 500, 500);
    }

    public void resume() {
        isRunning = true;
    }

    public void pause() {
        isRunning = false;
    }

    public void stop() {
        timer.cancel();
        timer.purge();
    }

    public interface LoopListener {
        void onLoop();
    }
}
