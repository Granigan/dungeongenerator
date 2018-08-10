package dungeon.maptools;

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

    /**
     * Constructor that stores the size of the map and creates counters for
     * segment ids (mazes & rooms).
     *
     * @param height of the map, including walls
     * @param width of the map, including walls
     * @param mazeId inherits room count, for numbering segments
     */
    public MazeBuilder(int height, int width, int mazeId) {
        this.height = height;
        this.width = width;
        this.mazeId = mazeId;
        this.roomCount = mazeId;
        this.r = new Random();
        neighbouringWalls = new ArrayList<>();
    }

    /**
     * Locates the first empty square (1) on the map, starting from upper left
     * corner.
     *
     * @param map being worked on
     * @return false if no more empty squares (1) are found
     */
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

    /**
     * Places a corridor (id = current maze id) in the current location defined
     * by private x and y. Then checks the four neighbours in random order to
     * find possible routes to continue/place walls.
     *
     * @param map being worked on
     * @return map being worked on
     */
    public int[][] placeCorridorWithWalls(int[][] map) {
        map[y][x] = mazeId;
        ArrayList<Direction> directions = new ArrayList<Direction>();
        directions.add(Direction.N);
        directions.add(Direction.E);
        directions.add(Direction.S);
        directions.add(Direction.W);
        Collections.shuffle(directions);

        while (!directions.isEmpty()) {
            map = placeWall(map, directions.remove(0));
        }
        return map;
    }

    /**
     * Checks given direction from current x,y coordinates and if found empty
     * (1), places a wall and adds the coordinates to the list for potential
     * corridor areas.
     *
     * @param map being worked on
     * @param direction to check
     * @return map being worked on
     */
    public int[][] placeWall(int[][] map, Direction direction) {
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

    /**
     * The main loop that runs the maze builder. Goes through the
     * neighbouringWalls list (or stack) and checks whether the square should be
     * a corridor or not. Calls to build walls if yes.
     *
     * @param map being worked on
     * @return map being worked on
     */
    public int[][] findNextCorridorSquare(int[][] map) {
        while (!neighbouringWalls.isEmpty()) {
//            int ri = r.nextInt(neighbouringWalls.size());
            int ri = neighbouringWalls.size() - 1;
            x = neighbouringWalls.get(ri).getX();
            y = neighbouringWalls.remove(ri).getY();

//            System.out.println("current location: " + x + "," + y + "\n"
//                    + toDebugString(map)
//            );
            if (checkIfSquareConnectsToOneCorridor(map)) {
                map = placeCorridorWithWalls(map);
            }
        }
        return map;
    }

    /**
     * Run after doors have been placed. Will fill in all the dead end corridors
     * for visual appeal.
     *
     * @param map being worked on
     * @return map being worked on
     */
    /* not yet in use, though should work
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
     */
    /**
     * Checks the four neighbouring squares for corridors. If exactly one is
     * corridor, this square should also be a corridor.
     *
     * @param map being worked on
     * @return true if there's exactly one neighbouring corridor, otherwise
     * false
     */
    public boolean checkIfSquareConnectsToOneCorridor(int[][] map) {
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

    /**
     * Counts walls surrounding the given i,j coordinates and returns the
     * amount.
     *
     * @param map being worked on
     * @param i x coordinate of the square to be checked
     * @param j x coordinate of the square to be checked
     * @return number of neighbouring walls.
     */
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

    /**
     * For debug use, creates a string of the current map with ssegment ids
     * instead of ASCII graphics.
     *
     * @param map being worked on
     * @return map as string, with numbers for segments
     */
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

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public ArrayList<Coordinates> getNeighbouringWalls() {
        return neighbouringWalls;
    }

    public void setNeighbouringWalls(ArrayList<Coordinates> neighbouringWalls) {
        this.neighbouringWalls = neighbouringWalls;
    }

}
