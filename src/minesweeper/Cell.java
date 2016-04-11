package minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Cell extends JButton implements ActionListener {

	private static final long serialVersionUID = 4993048163772888881L;
	private boolean hasMine = false;
	private MineField mineField;
	private boolean isChecked = false;
	private boolean isFlagged = false;
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
	
	public int[] getCoords()
	{
		int[] coords = {x, y};
		return coords;
	}

	@Override
	public void actionPerformed(ActionEvent e)	
	{
		if (!isFlagged) {
			mineField.openCell(this);	
		}
	}

	public boolean isChecked()
	{
		return isChecked;
	}
	
	public void check() 
	{
		this.isChecked = true;
	}
	
	public boolean isFlagged()
	{
		return isFlagged;
	}

	public void flag()
	{
		if (isFlagged) {
			setText(null);
			isFlagged = false;
		} else {
			setText("F");
			isFlagged = true;
		}
	}
	
}
