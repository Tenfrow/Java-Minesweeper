package minesweeper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

class Cell extends JButton implements MouseListener {

    private static final long serialVersionUID = 4993048163772888881L;
    private boolean hasMine = false;
    private MineField mineField;
    private boolean isDiscovered = false;
    private boolean isFlagged = false;
    private int x;
    private int y;

    Cell(MineField mineField, int x, int y) {
        this.setMargin(new java.awt.Insets(0, 0, 0, 0));
        this.setPreferredSize(new Dimension(32, 32));
        this.setFont(new Font("Consolas", Font.BOLD, 22));
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
            setIcon(new ImageIcon(getClass().getResource("../icons/mine.png")));
        } else {
            this.isDiscovered = true;
        }
    }

    boolean isFlagged() {
        return isFlagged;
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
            setIcon(new ImageIcon(getClass().getResource("../icons/flag.png")));
            isFlagged = true;
        } else {
            setIcon(null);
            isFlagged = false;
        }
    }

    @Override
    public void setEnabled(boolean b) {
//		super.setEnabled(b);
        this.setForeground(Color.decode("#026202"));
        this.setBackground(Color.decode("#eeeeee"));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e) && !isFlagged()) {
            mineField.openCell(this);
        }
        if (SwingUtilities.isRightMouseButton(e) && !isDiscovered()) {
            toggleFlag();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

}
