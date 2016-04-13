package minesweeper;

import javax.swing.*;
import java.awt.*;

public class Minesweeper {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new GridBagLayout());
        MineField mineField = new MineField(15, 15, 30);
        frame.add(mineField);
        frame.pack();
        frame.setMinimumSize(frame.getSize());
    }

}
