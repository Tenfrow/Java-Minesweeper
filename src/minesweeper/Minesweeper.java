package minesweeper;


import java.awt.*;

import javax.swing.JFrame;


public class Minesweeper {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Minesweeper");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(new GridBagLayout());
		MineField mineField = new MineField(15, 15, 20);
		frame.add(mineField);
		frame.pack();
		frame.setMinimumSize(frame.getSize());
	}
	
}
