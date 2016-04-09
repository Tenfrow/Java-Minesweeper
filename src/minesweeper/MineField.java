package minesweeper;

import java.awt.GridLayout;
import java.awt.Panel;
import java.util.Random;

public class MineField extends Panel {
	
	private static final long serialVersionUID = -6201984664074599724L;
	private int rows;
	private int cols;
	private int minesCount = 0;
	private Cell[][] field;
	private boolean isMinesSet = false;
	
	public MineField(int rows, int cols, int mines)
	{
		this.setLayout(new GridLayout(rows, cols));
		this.rows = rows;
		this.cols = cols;
		this.minesCount = mines;
		this.field = new Cell[rows][cols];
	}
	
	public void placeMines()
	{
		int maxCount = rows * cols;
		int count = (this.minesCount > maxCount) ? maxCount : this.minesCount;
		Random rand = new Random();
		while (count > 0) {
			if (setMine(rand.nextInt(rows), rand.nextInt(cols))) {
				count--;	
			} else {
				continue;
			}
		}
		this.isMinesSet = true;
	}
	
	public void openCell(int x, int y) 
	{
		if (!isMinesSet) {
			//TODO prevent setting mines in current cell
			placeMines();
		}
		Cell cell = field[x][y];
		if(cell.hasMine()) {
			//TODO KABOOOM!
			cell.setText("M");
		} else {
			//TODO set around mines count
		}
	}
	
	public int getRows() 
	{
		return rows;
	}
	
	public int getCols()
	{
		return cols;
	}
	
	private boolean setMine(int x, int y) 
	{
		if (field[x][y].setMine()) {
			return false;
		}
		return true;
	}
	
}
