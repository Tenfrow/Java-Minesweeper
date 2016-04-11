package minesweeper;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class MineField extends JPanel {
	
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
		setupCells();
	}

	public void placeMines()
	{
		int maxCount = rows * cols;
		int count = (minesCount > maxCount) ? maxCount : minesCount;
		Random rand = new Random();
		while (count > 0) {
			int[] cellCoords = {rand.nextInt(rows), rand.nextInt(cols)};
			if (setMine(cellCoords[0], cellCoords[1])) {
				count--;
			} else {
				continue;
			}
		}
		isMinesSet = true;
	}
	
	
	public void openCell(Cell cell) 
	{
		if (!isMinesSet()) {
			placeMines();
		}
		cell.setEnabled(false);
		if (cell.hasMine()) {
			//TODO KABOOOM!
			cell.setBackground(new Color(255, 100, 100));
			cell.setText("☼");	
		} else {
			int count = countMines(cell);
			if (count > 0) {
				cell.setText(Integer.toString(count));
			} else {
				for (Cell cellToOpen : getNotCheckedSurroundingCells(cell)) {
					if(!cellToOpen.isFlagged())
						openCell(cellToOpen);
				}
			}
		}
	}
	
	public boolean isMinesSet()
	{
		return isMinesSet;
	}
	
	private int countMines(Cell cell) {
		int count = 0;
		cell.check();
		for (Cell checkingCell : getNotCheckedSurroundingCells(cell)) {
			if (checkingCell.hasMine()) {
				count++;
			}
		}
		return count;
	}
	
	private ArrayList<Cell> getNotCheckedSurroundingCells(Cell cell)
	{
		int[] coords = cell.getCoords();
		ArrayList<Cell> cells = new ArrayList<Cell>();
		for (int dx = -1; dx <= 1; dx++) {
			for (int dy = -1; dy <= 1; dy++) {
				try {
					Cell cellToAdd = field[coords[0] + dx][coords[1] + dy];
					if (!cellToAdd.isChecked()) {
						cells.add(cellToAdd);
					}
				} catch (ArrayIndexOutOfBoundsException exc) { }
			}
		}
		return cells;
	}

	private boolean setMine(int x, int y) 
	{
		Cell cell = field[x][y];
		if (cell.setMine()) {
			
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
