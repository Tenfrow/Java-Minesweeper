package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class Cell extends JButton implements MouseListener {

    private boolean hasMine = false;
    private MineField mineField;
    private boolean isDiscovered = false;
    private boolean isFlagged = false;
    private int x;
    private int y;

    Cell(MineField mineField, int x, int y) {
        this.setMargin(new java.awt.Insets(0, 0, 0, 0));
        this.setPreferredSize(new Dimension(23, 23));
        this.setFont(new Font("Consolas", Font.BOLD, 19));
        this.mineField = mineField;
        this.x = x;
        this.y = y;
        this.addMouseListener(this);
    }

    boolean setMine() {
        if (this.hasMine()) {
            return false;
        }
        this.hasMine = true;
        return true;
    }

    boolean hasMine() {
        return this.hasMine;
    }

    int[] getCoords() {
        return new int[]{x, y};
    }

    boolean isDiscovered() {
        return isDiscovered;
    }

    void discover() {
        setEnabled(false);
        if (hasMine()) {
            setIcon(ResourceManager.getIcon("mine"));
            if (isFlagged) {
                setBackground(Color.green);
            }
        } else {
            this.isDiscovered = true;
        }
    }

    boolean isFlagged() {
        return isFlagged;
    }

    void setNumber(int number) {
        if (number > 0) {
            this.setText(Integer.toString(number));
            this.setForeground(ResourceManager.getNumberColor(number));
        }
    }

    @Override
    public void setEnabled(boolean b) {
        this.setForeground(Color.decode("#026202"));
        this.setBackground(Color.decode("#eeeeee"));
    }

    private void toggleFlag() {
        if (isFlagged) {
            setFlag(false);
        } else {
            setFlag(true);
        }
    }

    private void setFlag(boolean flag) {
        if (flag) {
            setIcon(ResourceManager.getIcon("flag"));
            isFlagged = true;
        } else {
            setIcon(null);
            isFlagged = false;
        }
    }

    private void openCell() {
        mineField.openCell(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e) && !isFlagged()) {
            openCell();
        }
        if (SwingUtilities.isRightMouseButton(e) && !isDiscovered()) {
            toggleFlag();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

}
