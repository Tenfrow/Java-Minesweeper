package minesweeper;

class Game {

    private static Game instance = null;

    static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    void start() {

    }

    void end() {

    }

}
