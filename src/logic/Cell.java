package logic;

import java.awt.*;

/**
 * Created by Yevhen on 24.06.2015.
 */
public abstract class Cell {
    private int row;
    private int column;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;

        Cell cell = (Cell) o;

        if (row != cell.row) return false;
        return column == cell.column;

    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }

    public abstract void paint(Graphics g);
}
