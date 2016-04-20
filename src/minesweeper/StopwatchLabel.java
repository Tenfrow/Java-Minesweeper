package minesweeper;

import javax.swing.*;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

class StopwatchLabel extends JLabel implements Runnable, Observer {

    private int seconds = 0;
    private double time = 0;
    private Thread thread;

    StopwatchLabel() {
        this.thread = new Thread(this);
        this.setNumberText(0.0);
        Game.getInstance().addObserver(this);
    }

    @Override
    public void run() {
        try {
            while (thread == Thread.currentThread()) {
//                this.setNumberText(seconds++);
                this.setNumberText(time);
                time += 0.1;
                Thread.sleep(100);
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

    private void setNumberText(int number) {
        this.setText(String.format("%03d", number));
    }

    private void setNumberText(double number) {
        this.setText(String.format(Locale.ENGLISH, "%05.1f", number));

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
