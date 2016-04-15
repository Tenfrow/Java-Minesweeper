package minesweeper;

import javax.swing.*;
import java.awt.*;

public class Minesweeper {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel statsPanel = new JPanel();
        //stats.setBorder();
        GridBagLayout statsLayout = new GridBagLayout();
        statsPanel.setLayout(statsLayout);

        statsPanel.add(new JLabel("099"));
        JButton restart = new JButton("R");
        restart.setPreferredSize(new Dimension(40, 40));
        statsPanel.add(restart);
        statsPanel.add(new JLabel("000"));

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridBagLayout());
        MineField mineField = new MineField(10, 10, 20);
        fieldPanel.add(mineField);

        statsLayout.columnWidths = new int[]{frame.getWidth()/3, frame.getWidth()/3, frame.getWidth()/3};
        mainPanel.add(statsPanel);
        mainPanel.add(fieldPanel);

        frame.add(mainPanel);
        frame.pack();

        frame.setMinimumSize(frame.getSize());

    }

}
