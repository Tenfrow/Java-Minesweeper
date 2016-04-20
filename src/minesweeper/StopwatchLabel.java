package minesweeper;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

class StopwatchLabel extends JLabel implements Runnable, Observer {

    private int seconds = 0;
    private Thread thread;

    StopwatchLabel() {
        this.thread = new Thread(this);
        this.setNumberText(seconds);
        Game.getInstance().addObserver(this);
    }

    @Override
    public void run() {
        try {
            while (thread == Thread.currentThread()) {
                this.setNumberText(seconds++);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    void start() {
        thread.start();
    }

    void stop() {
        thread = null;
    }

    public void setNumberText(int number) {
        this.setText(String.format("%03d", number));
    }

    @Override
    public void update(Observable o, Object arg) {
        if (((Game)o).isPlaying()) {
            this.start();
        } else {
            this.stop();
        }
    }
}
