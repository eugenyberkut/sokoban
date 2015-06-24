package logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Yevhen on 24.06.2015.
 */
public class LevelReader {
    private List<String> lines;

    public LevelReader() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("soko.txt"));
             lines = reader.lines().collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public GameLevel readLevel(int num) {
        String fmt = String.format("Maze: %d", num);
        int k = 0;
        while (!lines.get(k).startsWith(fmt)) k++;
        int width = Integer.parseInt(lines.get(k + 1).split(": ")[1]);
        int height= Integer.parseInt(lines.get(k + 2).split(": ")[1]);
        String[] levelLines = new String[height];
        for (int i = 0; i < levelLines.length; i++) {
            levelLines[i] = lines.get(i+k+5);
        }
        GameLevel result = new GameLevel();
        for (int i=0; i<levelLines.length; i++) {
            for (int j=0; j<levelLines[i].length(); j++) {
                if (levelLines[i].charAt(j)=='@') {
                    result.setMan(new Man(i, j));
                }
                if (levelLines[i].charAt(j)=='X') {
                    result.addWall(new Wall(i, j));
                }
                if (levelLines[i].charAt(j)=='*') {
                    result.addBox(new Box(i, j));
                }
                if (levelLines[i].charAt(j)=='.') {
                    result.addTarget(new Target(i, j));
                }
                if (levelLines[i].charAt(j)=='&') {
                    result.addBox(new Box(i, j));
                    result.addTarget(new Target(i, j));
                }
            }
        }
        return result;
    }
}
