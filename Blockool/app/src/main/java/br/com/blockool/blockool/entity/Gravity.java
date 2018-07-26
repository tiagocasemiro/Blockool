package br.com.blockool.blockool.entity;

/**
 * Created by tiago.casemiro on 26/07/2018.
 */

public class Gravity {
    private int controller;

    public void processGravity(){
        controller++;
    }

    public boolean acelerationGravity(Level level) {
        if(controller % level.value() == 0) {
            controller = 0;
            return true;
        }

        return false;
    }


}
