package br.com.blockool.blockool.game;

/**
 * Created by tiago.casemiro on 19/07/2018.
 */

public class InputGestureProcess {
    private Listener listener;
    private Float oldX;
    private Float oldY;
    private final float TOLERANCE_UP = 110f;
    private final float TOLERANCE_DOWN = 40f;
    private final float TOLERANCE_RIGHT = 55f;
    private final float TOLERANCE_LEFT = 55f;

    public InputGestureProcess(Listener listener) {
        this.listener = listener;
    }

    public void init(float  x, float y) {
        oldX = x;
        oldY = y;
    }

    public void process(float x, float y) {
        if(oldX != null && oldY != null) {
            if (x + TOLERANCE_LEFT < oldX) {
                oldX = oldX - TOLERANCE_LEFT;
                listener.onInputLeft();
            }
            if (x - TOLERANCE_RIGHT > oldX) {
                oldX = oldX + TOLERANCE_RIGHT;
                listener.onInputRight();
            }
            if (y + TOLERANCE_UP  < oldY) {
                oldY = oldY - TOLERANCE_UP;
                listener.onInputUp();
            }
            if (y - TOLERANCE_DOWN > oldY) {
                oldY = oldY + TOLERANCE_DOWN;
                listener.onInputDown();
            }
        }
    }

    public void finishMove() {
        oldX = null;
        oldY = null;
    }

    public interface Listener {
        void onInputDown();
        void onInputUp();
        void onInputRight();
        void onInputLeft();
        void onPauseGame();
        void onResumeGame();
    }
}
