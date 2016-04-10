package minesweeper;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JPanel;

public class MineField extends JPanel {
	
	private static final long serialVersionUID = -6201984664074599724L;
	private int rows;
	private int cols;
	private int minesCount = 0;
	private Cell[][] field;
	private boolean isMinesSet = false;
	
	private boolean debug = false;
	
	public MineField(int rows, int cols, int mines)
	{
		this.setLayout(new GridLayout(rows, cols));
		this.rows = rows;
		this.cols = cols;
		this.minesCount = mines;
		this.field = new Cell[rows][cols];
		setupCells();
	}

	public void placeMines()
	{
		int maxCount = rows * cols;
		int count = (this.minesCount > maxCount) ? maxCount : this.minesCount;
		Random rand = new Random();
		while (count > 0) {
			int[] cellCoords = {rand.nextInt(rows), rand.nextInt(cols)};
			if (setMine(cellCoords[0], cellCoords[1])) {
				count--;
			} else {
				continue;
			}
		}
		this.isMinesSet = true;
	}
	
	public int getRows()
	{
		return rows;
	}
	
	public int getCols()
	{
		return cols;
	}
	
	public void openCell(Cell cell) 
	{
		if (!isMinesSet()) {
			placeMines();
		}
		if(cell.hasMine()) {
			//TODO KABOOOM!
			cell.setBackground(new Color(255, 100, 100));
			cell.setText("M");
		} else {
			int count = countMines(cell);
			if (count > 0) {
				cell.setText(Integer.toString(count));
			}
		}
	}
	
	private int countMines(Cell cell) {
		int[] coords = cell.getCoords();
		int count = 0;
		cell.check();
		for (int dx = -1; dx <= 1; dx++) {
			for (int dy = -1; dy <= 1; dy++) {
				try {
					Cell checkingCell = field[coords[0] + dx][coords[1] + dy];
					if (!checkingCell.isChecked()) {
						if (checkingCell.hasMine()) {
							count++;
						}
					}
				} catch (ArrayIndexOutOfBoundsException exc) { }
			}
		}	

		return count;
	}

	public boolean isMinesSet()
	{
		return isMinesSet;
	}

	private boolean setMine(int x, int y) 
	{
		Cell cell = field[x][y];
		if (cell.setMine()) {
			if (debug) {
				cell.setText("M");
			}
			return true;
		}
		return false;
	}
	
	private void setupCells() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Cell cell = field[i][j] = new Cell(this, i, j);
				this.add(cell);
			}
		}
	}
	
}
