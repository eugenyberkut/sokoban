package logic;

import java.awt.*;

/**
 * Created by Yevhen on 24.06.2015.
 */
public class Box extends Cell {
    public Box(int row, int column) {
        super(row, column);
    }

    @Override
    public void paint(Graphics g) {

    }

    public boolean move(int dx, int dy, GameLevel level) {
        int row = getRow()+dy;
        int column = getColumn()+dx;
        if (level.getWalls().contains(new Wall(row,column))) {
            return false;
        }
        int boxIndex = level.getBoxes().indexOf(new Box(row, column));
        if (boxIndex!=-1) {
            return false;
        }
        setRow(row);
        setColumn(column);
        return true;
    }
}
