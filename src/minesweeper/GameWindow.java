package minesweeper;

import javax.swing.*;
import java.awt.*;


class GameWindow {

    private JFrame window;

    GameWindow(String title) {
        window = new JFrame(title);
    }

    void show() {
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // todo: implement stats and restart
        JPanel statsPanel = new JPanel();
        GridBagLayout statsLayout = new GridBagLayout();
        statsLayout.columnWidths = new int[]{window.getWidth() / 3, window.getWidth() / 3, window.getWidth() / 3};
        statsPanel.setLayout(statsLayout);
        statsPanel.add(new JLabel("099"));
        JButton restart = new JButton("R");
        statsPanel.add(restart);
        statsPanel.add(new JLabel("000"));

        // mainPanel.add(statsPanel);

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridBagLayout());
        MineField mineField = new MineField(15, 15, 25);
        fieldPanel.add(mineField);

        mainPanel.add(fieldPanel);

        window.add(mainPanel);
        window.pack();
        window.setSize(new Dimension(window.getWidth() + 10, window.getHeight() + 10));
    }
}
