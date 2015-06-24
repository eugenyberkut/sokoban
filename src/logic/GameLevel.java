package logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yevhen on 24.06.2015.
 */
public class GameLevel {
    public GameLevel(Man man, List<Box> boxes, List<Target> targets, List<Wall> walls) {
        this.man = man;
        this.boxes = boxes;
        this.targets = targets;
        this.walls = walls;
    }

    public GameLevel() {
        boxes = new ArrayList<>();
        targets = new ArrayList<>();
        walls = new ArrayList<>();
    }

    private Man man;
    private List<Box> boxes;
    private List<Target> targets;
    private List<Wall> walls;

    public void setMan(Man man) {
        this.man = man;
    }

    public void setBoxes(List<Box> boxes) {
        this.boxes = boxes;
    }

    public void setTargets(List<Target> targets) {
        this.targets = targets;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public Man getMan() {
        return man;
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public List<Target> getTargets() {
        return targets;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public void addWall(Wall w) {
        walls.add(w);
    }

    public void addBox(Box b) {
        boxes.add(b);
    }

    public void addTarget(Target t) {
        targets.add(t);
    }

    public boolean checkEnd() {
        return targets.containsAll(boxes);
    }
}
