package minesweeper;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Cell extends JButton implements ActionListener {

	private static final long serialVersionUID = 4993048163772888881L;
	private boolean hasMine = false;
	private MineField mineField;
	private int x;
	private int y;
	
	public Cell(MineField mineField, int x, int y)
	{
		this.mineField = mineField;
		this.x = x;
		this.y = y;
		this.addActionListener(this);
	}
	
	public boolean setMine()
	{
		if (this.hasMine) {
			return false;
		}
		this.hasMine = true;
		return true;
	}
	
	public boolean hasMine()
	{
		return this.hasMine;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setEnabled(false);
		openCell();
	}

	public void openCell() {
		if (!mineField.isMinesSet()) {
			//TODO prevent setting mines in current cell
			mineField.placeMines();
		}
		if(hasMine()) {
			//TODO KABOOOM!
			setBackground(new Color(255, 100, 100));
			setText("M");
		} else {
			countMines();
		}	
	}

	private void countMines() {
		// TODO Auto-generated method stub
		
	}

	
}
