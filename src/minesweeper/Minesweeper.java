package minesweeper;

import java.awt.GridLayout;
import javax.swing.JFrame;
public class Minesweeper {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Minesweeper");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(new GridLayout(1, 1));
		
		MineField mineField = new MineField(10, 10, 10);
		frame.add(mineField);
		
		frame.setBounds(100, 100, 500, 500);
	}
	
}
