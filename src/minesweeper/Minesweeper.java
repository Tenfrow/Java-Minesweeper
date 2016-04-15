package minesweeper;

import javax.swing.*;
import java.awt.*;

public class Minesweeper {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        GridBagLayout layout = new GridBagLayout();
        frame.setLayout(layout);
        MineField mineField = new MineField(20, 20, 0.13);
        frame.add(mineField);
        JLabel label = new JLabel("0");
        label.setPreferredSize(new Dimension(50, 50));
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_END;
        c.gridy = 1;
        frame.add(new JLabel("0"), c);
        frame.pack();
        frame.setMinimumSize(frame.getSize());
    }

}
