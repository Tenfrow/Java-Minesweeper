package minesweeper;

import javax.swing.JButton;

public class Cell extends JButton {

	private static final long serialVersionUID = 1L;
	private boolean hasMine = false;

	public Cell(boolean hasMine) 
	{
		this.hasMine = hasMine;
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
	
}
