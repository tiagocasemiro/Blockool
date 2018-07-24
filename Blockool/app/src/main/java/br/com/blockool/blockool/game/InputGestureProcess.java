package br.com.blockool.blockool.game;

/**
 * Created by tiago.casemiro on 19/07/2018.
 */

public class InputGestureProcess {
    private Listener listener;
    private Float oldX;
    private Float oldY;

    private final float TOLERANCE = 80f;

    public InputGestureProcess(Listener listener) {
        this.listener = listener;
    }

    public void init(float  x, float y) {
        oldX = x;
        oldY = y;
    }

    public void process(float x, float y) {
        if(oldX != null && oldY != null) {
            if (x + TOLERANCE < oldX) {
                listener.onInputLeft();
            }
            if (x - TOLERANCE > oldX) {
                listener.onInputRight();
            }
            if (y + TOLERANCE  < oldY) {
                listener.onInputUp();
            }
            if (y - TOLERANCE > oldY) {
                listener.onInputDown();
            }
        }
        oldY = null;
        oldX = null;
    }

    public interface Listener {
        void onInputDown();
        void onInputUp();
        void onInputRight();
        void onInputLeft();
    }
}
