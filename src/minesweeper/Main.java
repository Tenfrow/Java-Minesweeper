package minesweeper;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {
		GridLayout fl = new GridLayout(10, 10);
		JFrame window = new JFrame("Minesweeper");
		window.setVisible(true);
		window.setSize(500, 500);
		window.setResizable(false);
		
	}
	
}
