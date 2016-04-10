package minesweeper;

import java.awt.GridLayout;
import java.util.Arrays;
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
		Random rand = new Random(3);
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
	
	public void openCell(Cell cell) 
	{
		cell.openCell();
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
