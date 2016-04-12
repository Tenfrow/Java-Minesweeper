package minesweeper;


import java.awt.FlowLayout;

import javax.swing.JFrame;


public class Minesweeper {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Minesweeper");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(new FlowLayout());
		MineField mineField = new MineField(12, 12, 20);
		frame.add(mineField);
		frame.pack();
	}
	
}
