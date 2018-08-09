package dungeon.maptools;

/**
 * Used to create a maze of corridors that join the rooms together.
 *
 * @author tgtapio
 */
public class MazeBuilder {

    private final int height;
    private final int width;
    private final int mazeId;

    // x and y mark the current location
    private int x;
    private int y;

    public MazeBuilder(int height, int width, int mazeId) {
        this.height = height;
        this.width = width;
        this.mazeId = mazeId;
    }

    public boolean findFirstEmpty(int[][] map) {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (map[j][i] == 1) {
                    x = i;
                    y = j;
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
        }
        if (map[y + 1][x] == 1) {
            map[y + 1][x] = 0;
        }
        if (map[y][x - 1] == 1) {
            map[y][x - 1] = 0;
        }
        if (map[y][x + 1] == 1) {
            map[y][x + 1] = 0;
        }
        return map;
    }

    public int[][] findCorridor(int[][] map) {
        if (map[y + 1][x] == 1) {
            y = y + 1;
            map[y][x] = mazeId;
        } else if (map[y - 1][x] == 1) {
            y = y - 1;
            map[y][x] = mazeId;
        } else if (map[y][x + 1] == 1) {
            x = x + 1;
            map[y][x] = mazeId;
        } else if (map[y][x - 1] == 1) {
            x = x - 1;
            map[y][x] = mazeId;
        } else {
            // back up through the stack
        }
        return map;
    }
}
