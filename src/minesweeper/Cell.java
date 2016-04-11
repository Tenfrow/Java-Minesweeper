package minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class Cell extends JButton implements ActionListener, MouseListener {

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
		this.addMouseListener(this);
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
	
	public void openCell() {
		mineField.openCell(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)	
	{
	
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
		if (isFlagged()) {
			setText(null);
			isFlagged = false;
		} else {
			setText("►");
			isFlagged = true;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1 && !isFlagged()){
			openCell();
		}
		if(e.getButton() == MouseEvent.BUTTON3 && !isChecked){
			flag();
	    }
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
