package br.com.blockool.blockool;

/**
 * Created by tiago.casemiro on 18/07/2018.
 */

public class GameRulesProcess {
    private Block[][] blocks;
    private Scene scene;
    private SceneListener sceneListener;

    public GameRulesProcess() {
        scene = new Scene();
    }

    public void processInputRight() {

    }

    public void processInputLeft() {

    }

    public void processInputUp() {

    }

    public void processInputDown() {

    }

    public void processGame() {

    }

    public void processGravity() {

    }

    public interface SceneListener {
        void onScene(Scene scene);
    }
}
