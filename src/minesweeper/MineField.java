package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

class MineField extends JPanel {

    private static final long serialVersionUID = -6201984664074599724L;
    private int rows;
    private int cols;
    private int minesCount = 0;
    private Cell[][] field;
    private ArrayList<Cell> minedCells = new ArrayList<>();
    private boolean isMinesSet = false;

    MineField(int rows, int cols, int mines) {
        this.setLayout(new GridLayout(rows, cols));
        this.rows = rows;
        this.cols = cols;
        this.minesCount = mines;
        this.field = new Cell[rows][cols];
        setupCells();
        placeMines();
    }

    public boolean isMinesSet() {
        return isMinesSet;
    }

    int openCell(Cell cell) {
        int research = discoverCell(cell);
        if (research < 0) {
            //TODO KABOOOM!
            discoverAllMines();
            cell.setBackground(Color.decode("#ff0000"));
        }
        return research;
    }

    private int discoverCell(Cell cell) {
        cell.discover();
        int surroundingMines = countMines(cell);
        if (surroundingMines > 0) {
            cell.setSurroundingMinesAmount(surroundingMines);
        }
        if (surroundingMines == 0) {
            for (Cell cellToOpen : getCoveredSurroundingCells(cell)) {
                if (!cellToOpen.isFlagged()) {
                    discoverCell(cellToOpen);
                }
            }
        }

        return surroundingMines;
    }

    /**
     * Returns number of surrounding mines or -1 if cell itself contains mine
     *
     * @param cell Cell
     * @return int
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
     *
     * @param cell Cell
     * @return int
     */
    private ArrayList<Cell> getCoveredSurroundingCells(Cell cell) {
        int[] coords = cell.getCoords();
        ArrayList<Cell> cells = new ArrayList<>();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                try {
                    Cell cellToAdd = field[coords[0] + dx][coords[1] + dy];
                    if (!cellToAdd.isDiscovered()) {
                        cells.add(cellToAdd);
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
        return cells;
    }

    private void placeMines() {
        int maxCount = rows * cols;
        int count = (minesCount > maxCount) ? maxCount : minesCount;
        Random rand = new Random();
        while (count > 0) {
            int[] cellCoords = {rand.nextInt(rows), rand.nextInt(cols)};
            if (setMine(cellCoords[0], cellCoords[1])) {
                count--;
            }
        }
        isMinesSet = true;
    }

    private boolean setMine(int x, int y) {
        Cell cell = field[x][y];
        if (cell.setMine()) {
            minedCells.add(cell);
            return true;
        }
        return false;
    }

    private void discoverAllMines() {
        minedCells.forEach(Cell::discover);
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
