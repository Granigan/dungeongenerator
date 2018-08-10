package dungeon.maptools;

import dungeon.domain.Direction;
import java.util.ArrayList;
import java.util.Collections;
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
        ArrayList<Direction> directions = new ArrayList<Direction>();
        directions.add(Direction.N);
        directions.add(Direction.E);
        directions.add(Direction.S);
        directions.add(Direction.W);
        Collections.shuffle(directions);

        while (!directions.isEmpty()) {
            map = buildWalls(map, directions.remove(0));
        }
        return map;
    }

    public int[][] buildWalls(int[][] map, Direction direction) {
        switch (direction) {
            case N:
                if (map[y - 1][x] == 1) {
                    map[y - 1][x] = 0;
                    neighbouringWalls.add(new Coordinates(x, y - 1));
                }
            case E:
                if (map[y][x + 1] == 1) {
                    map[y][x + 1] = 0;
                    neighbouringWalls.add(new Coordinates(x + 1, y));
                }
            case S:
                if (map[y + 1][x] == 1) {
                    map[y + 1][x] = 0;
                    neighbouringWalls.add(new Coordinates(x, y + 1));
                }
            case W:
                if (map[y][x - 1] == 1) {
                    map[y][x - 1] = 0;
                    neighbouringWalls.add(new Coordinates(x - 1, y));
                }
        }
        return map;
    }

    public int[][] findNextCorridorSquare(int[][] map) {
        while (!neighbouringWalls.isEmpty()) {
//            int ri = r.nextInt(neighbouringWalls.size());
            int ri = neighbouringWalls.size() - 1;
            x = neighbouringWalls.get(ri).getX();
            y = neighbouringWalls.remove(ri).getY();

//            System.out.println("current location: " + x + "," + y + "\n"
//                    + toDebugString(map)
//            );

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
        if (map[y][x + 1] > 1) {
            connections++;
        }
        if (map[y][x - 1] > 1) {
            connections++;
        }

        if (connections == 1) {
            return true;
        }

        return false;
    }

    public int[][] sealDeadEnds(int[][] map) {
        boolean runAgain = true;
        while (runAgain) {
            runAgain = false;
            for (int j = 1; j < height - 1; j++) {
                for (int i = 1; i < width - 1; i++) {
                    if (map[j][i] > roomCount) {
                        if (countSurroundingWalls(map, i, j) > 2) {
                            map[j][i] = 0;
                            runAgain = true;
                        }
                    }
                }
            }
        }
        return map;
    }

    public int countSurroundingWalls(int[][] map, int i, int j) {
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
        return surroundingWalls;
    }

    public String toDebugString(int[][] map) {
        String output = "";
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (map[y][x] == 0) {
                    output += "#";
                } else {
                    output += "" + map[y][x];
                }
            }
            output += "\n";
        }
        return output;
    }

}
