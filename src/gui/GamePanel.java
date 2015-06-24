package gui;

import logic.*;
import logic.Box;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * Created by Yevhen on 24.06.2015.
 */
public class GamePanel extends JPanel{
    BufferedImage img;

    public GamePanel() {
        try {
            img = ImageIO.read(new File("img1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final static int CELL_SIZE = 25;

    private GameLevel level;

    public GameLevel getLevel() {
        return level;
    }

    public void setLevel(GameLevel level) {
        this.level = level;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(img,0,0,null);
        paintLevel(level, g);
    }

    private void paintLevel(GameLevel level, Graphics g) {
        paintWalls(level, g);
        paintBoxes(level, g);
        paintTargets(level, g);
        paintMan(level, g);
    }

    private void paintMan(GameLevel level, Graphics g) {
        Man man = level.getMan();
        g.setColor(Color.RED);
        g.fillOval(man.getColumn() * CELL_SIZE, man.getRow() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    private void paintTargets(GameLevel level, Graphics g) {
        List<Target> targets = level.getTargets();
        for (Target target : targets) {
            g.setColor(new Color(0, 255, 0, 100));
            g.fillRect(target.getColumn()*CELL_SIZE,target.getRow()*CELL_SIZE, CELL_SIZE, CELL_SIZE);
            g.setColor(Color.WHITE);
            g.drawRect(target.getColumn() * CELL_SIZE, target.getRow() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }

    }

    private void paintBoxes(GameLevel level, Graphics g) {
        List<Box> boxes = level.getBoxes();
        for (Box box : boxes) {
            g.setColor(Color.BLUE);
            g.fillRect(box.getColumn()*CELL_SIZE, box.getRow()*CELL_SIZE, CELL_SIZE, CELL_SIZE);
            g.setColor(Color.WHITE);
            g.drawRect(box.getColumn() * CELL_SIZE, box.getRow() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
    }

    private void paintWalls(GameLevel level, Graphics g) {
        List<Wall> walls = level.getWalls();
        for (Wall wall : walls) {
            g.setColor(Color.BLACK);
            g.fillRect(wall.getColumn()*CELL_SIZE, wall.getRow()*CELL_SIZE, CELL_SIZE, CELL_SIZE);
            g.setColor(Color.LIGHT_GRAY);
            g.drawRect(wall.getColumn() * CELL_SIZE, wall.getRow() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
    }


    public boolean moveMan(int dx, int dy) {
        Man man = level.getMan();
        boolean moved = man.move(dx,dy, level);
        return moved;
    }

    public boolean checkEnd() {
        return level.checkEnd();
    }
}
