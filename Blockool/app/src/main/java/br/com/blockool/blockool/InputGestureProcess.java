package br.com.blockool.blockool;

/**
 * Created by tiago.casemiro on 19/07/2018.
 */

public class InputGestureProcess {
    private Listener listener;

    private Float oldX;
    private Float oldY;

    private final float TOLERANCE = 15f;


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
                listener.onLeft();
            }

            if (x - TOLERANCE > oldX) {
                listener.onRight();
            }

            if (y + TOLERANCE  < oldY) {
                listener.onUp();
            }

            if (y - TOLERANCE > oldY) {
                listener.onDown();
            }
        }

        oldY = null;
        oldX = null;
    }

    public interface Listener {
        void onDown();
        void onUp();
        void onRight();
        void onLeft();
    }
}
