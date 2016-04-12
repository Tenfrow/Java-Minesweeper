package minesweeper;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

public  class Cell extends JButton implements MouseListener {

	private static final long serialVersionUID = 4993048163772888881L;
	private boolean hasMine = false;
	private MineField mineField;
	private boolean isChecked = false;
	private boolean isFlagged = false;
	private int x;
	private int y;
	
	public Cell(MineField mineField, int x, int y)
	{
		this.setMargin(new java.awt.Insets(0, 0, 0, 0));
		this.setFont(new Font("Consolas", Font.BOLD, 22));
		this.setForeground(Color.yellow);
		this.mineField = mineField;
		this.x = x;
		this.y = y;
		this.addMouseListener(this);
	}
	
	public boolean setMine()
	{
		if (this.hasMine()) {
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
			setIcon(null);
			isFlagged = false;
		} else {
			setIcon(new ImageIcon(getClass().getResource("../icons/flag.png")));
			isFlagged = true;
		}
	}
	
	@Override
	public void setEnabled(boolean b)
	{
//		super.setEnabled(b);
		this.setForeground(Color.decode("#026202"));
		this.setBackground(Color.decode("#eeeeee"));
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e)
	{

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{

	}

	@Override
	public void mouseExited(MouseEvent e)
	{

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		if (SwingUtilities.isLeftMouseButton(e) && !isFlagged()){
			mineField.openCell(this);
		}
		if (SwingUtilities.isRightMouseButton(e) && !isChecked()){
			flag();
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
	
	}
	
}
