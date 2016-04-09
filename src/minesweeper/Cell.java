package minesweeper;

import javax.swing.JButton;

public class Cell extends JButton {

	private static final long serialVersionUID = 1L;
	private boolean _hasMine = false;

	public Cell(boolean hasMine) 
	{
		this._hasMine = hasMine;
	}
	
	public boolean setMine()
	{
		if (this._hasMine) {
			return false;
		}
		this._hasMine = true;
		return true;
	}
	
	public boolean getHasMine()
	{
		return this._hasMine;
	}
	
	
}
