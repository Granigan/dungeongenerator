package dungeon.maptools;

import java.util.ArrayList;
import java.util.Random;

/**
 * Used to create a maze of corridors that join the rooms together.
 *
 * @author tgtapio
 */
public class MazeBuilder {

    private final int height;
    private final int width;
    private int mazeId;
    private int roomCount;

    // x and y mark the current location
    private int x;
    private int y;
    private ArrayList<Coordinates> neighbouringWalls;
    private Random r;

    public MazeBuilder(int height, int width, int mazeId) {
        this.height = height;
        this.width = width;
        this.mazeId = mazeId;
        this.roomCount = mazeId;
        this.r = new Random();
        neighbouringWalls = new ArrayList<>();
    }

    public boolean findFirstEmpty(int[][] map) {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (map[j][i] == 1) {
                    x = i;
                    y = j;
                    mazeId++;
                    return true;
                }
            }
        }
        return false;
    }

    public int[][] placeCorridorWithWalls(int[][] map) {
        map[y][x] = mazeId;
        if (map[y - 1][x] == 1) {
            map[y - 1][x] = 0;
            neighbouringWalls.add(new Coordinates(x, y - 1));
        }
        if (map[y + 1][x] == 1) {
            map[y + 1][x] = 0;
            neighbouringWalls.add(new Coordinates(x, y + 1));
        }
        if (map[y][x - 1] == 1) {
            map[y][x - 1] = 0;
            neighbouringWalls.add(new Coordinates(x - 1, y));
        }
        if (map[y][x + 1] == 1) {
            map[y][x + 1] = 0;
            neighbouringWalls.add(new Coordinates(x + 1, y));
        }
        return map;
    }

    public int[][] findNextCorridorSquare(int[][] map) {
        while (!neighbouringWalls.isEmpty()) {
            int ri = r.nextInt(neighbouringWalls.size());
            x = neighbouringWalls.get(ri).getX();
            y = neighbouringWalls.get(ri).getY();
            neighbouringWalls.remove(ri);

            if (checkIfCorridorNotYetConnected(map)) {
                map = placeCorridorWithWalls(map);
            }
        }
        return map;
    }

    public boolean checkIfCorridorNotYetConnected(int[][] map) {
        int connections = 0;
        if (map[y - 1][x] > 1) {
            connections++;
        }
        if (map[y + 1][x] > 1) {
            connections++;
        }
        if (map[y][x] > 1) {
            connections++;
        }
        if (map[y][x - 1] > 1) {
            connections++;
        }

        if (connections > 1) {
            return false;
        }

        return true;
    }

    public int[][] sealDeadEnds(int[][] map) {
        boolean runAgain = true;
        while (runAgain) {
            runAgain = false;

            for (int j = 1; j < height - 1; j++) {
                for (int i = 1; i < width - 1; i++) {
                    if (map[j][i] > roomCount) {
                        int surroundingWalls = 0;
                        if (map[j + 1][i] == 0) {
                            surroundingWalls++;
                        }
                        if (map[j - 1][i] == 0) {
                            surroundingWalls++;
                        }
                        if (map[j][i + 1] == 0) {
                            surroundingWalls++;
                        }
                        if (map[j][i - 1] == 0) {
                            surroundingWalls++;
                        }
                        if (surroundingWalls > 2) {
                            map[j][i] = 0;
                            runAgain = true;
                        }
                    }
                }
            }
        }
        return map;
    }
}
