package minesweeper;

import java.util.Observable;

class Game extends Observable {

    private static Game instance = new Game();
    private boolean isPlaying = false;

    static Game getInstance() {
        return instance;
    }

    void start() {
        isPlaying = true;
        setChanged();
        notifyObservers();
    }

    void stop() {
        isPlaying = false;
        setChanged();
        notifyObservers();
    }

    boolean isPlaying() {
        return isPlaying;
    }

}
