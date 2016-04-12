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
	private ArrayList<Cell> minedCells = new ArrayList<Cell>();
	private boolean isMinesSet = false;
	
	public MineField(int rows, int cols, int mines)
	{
		this.setLayout(new GridLayout(rows, cols));
		this.rows = rows;
		this.cols = cols;
		this.minesCount = mines;
		this.field = new Cell[rows][cols];
		setupCells();
		placeMines();
	}
	
	public boolean isMinesSet()
	{
		return isMinesSet;
	}
	
	public int openCell(Cell cell)
	{
		int research = discoverCell(cell);
		if (research < 0) {
			//TODO KABOOOM!
			discoverAllMines();
			cell.setBackground(Color.decode("#ff0000"));
		}
		return research;
	}
	
	private int discoverCell(Cell cell) 
	{
		cell.discover();
		int surroundingMines = countMines(cell);
		if (surroundingMines > 0) {
			cell.setText(Integer.toString(surroundingMines));
		}
		if (surroundingMines == 0) {
			for (Cell cellToOpen : getCoveredSurroundingCells(cell)) {
				if(!cellToOpen.isFlagged()) {
					discoverCell(cellToOpen);
				}
			}
		}
		
		return surroundingMines;
	}
	
	/**
	 * Returns number of surrounding mines or -1 if cell itself contains mine
	 * @param cell
	 * @return
	 */
	private int countMines(Cell cell) {
		if (cell.hasMine()) {
			return -1;
		}
		
		int count = 0;
		for (Cell checkingCell : getCoveredSurroundingCells(cell)) {
			if (checkingCell.hasMine()) {
				count++;
			}
		}
		
		return count;
	}
	
	/**
	 * Returns not discovered surrounding cells
	 * @param cell
	 * @return
	 */
	private ArrayList<Cell> getCoveredSurroundingCells(Cell cell)
	{
		int[] coords = cell.getCoords();
		ArrayList<Cell> cells = new ArrayList<Cell>();
		for (int dx = -1; dx <= 1; dx++) {
			for (int dy = -1; dy <= 1; dy++) {
				try {
					Cell cellToAdd = field[coords[0] + dx][coords[1] + dy];
					if (!cellToAdd.isDiscovered()) {
						cells.add(cellToAdd);
					}
				} catch (ArrayIndexOutOfBoundsException exc) { }
			}
		}
		return cells;
	}
	
	private void placeMines()
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

	private boolean setMine(int x, int y) 
	{
		Cell cell = field[x][y];
		if (cell.setMine()) {
			minedCells.add(cell);
			return true;
		}
		return false;
	}
	
	private void discoverAllMines()
	{
		for (Cell cell : minedCells) {
			cell.discover();
		}
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
