package minesweeper;

import java.awt.GridLayout;
import java.awt.Panel;
import java.util.Random;

public class MineField extends Panel {
	
	private int rows;
	private int cols;
	private int minesAmount;
	private Cell[][] field;
	
	
	public MineField(int rows, int cols)
	{
		this.setLayout(new GridLayout(rows, cols));
		this.rows = rows;
		this.cols = cols;
		this.field = new Cell[rows][cols];
	}
	
	public void setMines(int count)
	{
		int maxCount = this.rows * this.cols;
		count = count > maxCount ? maxCount : count;
		Random rand = new Random();
		while (count > 0) {
			if (this.setMine(rand.nextInt(this.rows), rand.nextInt(this.cols))) {
				count--;	
			} else {
				continue;
			}
		}
	}
	
	public int getRows() 
	{
		return this.rows;
	}
	
	public int getCols()
	{
		return this.cols;
	}
	
	private boolean setMine(int x, int y) 
	{
		if (this.field[x][y].setMine()) {
			return false;
		}
		return true;
	}
	
}
