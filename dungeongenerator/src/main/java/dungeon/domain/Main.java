package dungeon.domain;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Main {

    /**
     * Main method that runs the algorithm to create a dungeon.
     *
     * @param args CLI support for these to be added
     * @throws IOException problem with writing to disk
     */
    public static void main(String[] args) throws IOException {
        int width = 180; // map width
        int height = 50; // map height
        int roomAttempts = 150; // how many times is addRoom() ran
        int minRoomSize = 4; // including walls, 3 is thus the absolute minimum
        int maxRoomRandom = 15; // up to this much is added to the minRoomSize

        Random r = new Random();

        int[][] map = initMap(height, width);

        while (roomAttempts > 0) {
            map = addRoom(map, minRoomSize + r.nextInt(maxRoomRandom), minRoomSize + r.nextInt(maxRoomRandom),
                    width, height);
            roomAttempts--;
        }

        System.out.println(mapToString(map, width, height));
        saveMap(mapToString(map, width, height));
    }

    /**
     * Initialises the map by filling it with empty space "1" and creates the
     * outer walls "0".
     *
     * @param height of the map in squares
     * @param width of the map in squares
     * @return open map with outer walls
     */
    public static int[][] initMap(int height, int width) {
        int[][] map = new int[height][width];
        for (int y = 0; y < height; y++) {
            map[y][0] = 0;
            map[y][width - 1] = 0;
        }
        for (int x = 0; x < width; x++) {
            map[0][x] = 0;
            map[height - 1][x] = 0;
        }
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                map[y][x] = 1;
            }
        }
        return map;
    }

    /**
     * Attempts to place a room on the map by first creating a parameter sized
     * room, then choosing random coordinates for it on the map. If it collides
     * with another room or wall, the room is not placed.
     *
     * First iteration is to check each square for collision. This probably can
     * be optimised later on. Also something to consider: should the room's
     * starting location be fully random inside the map area, or limited so that
     * it will always at least fit inside the outer walls. For now, going with
     * the option to limit starting location so that the walls won't be a
     * problem. This I think will be easier, though it means the slight change
     * in how often addRoom will be able to fit the room in.
     *
     * @param map before addition
     * @param rwidth width of the room to be added
     * @param rheight height of the room to be added
     * @param width of the map
     * @param height of the map
     * @return the map with the new room if it was added
     */
    public static int[][] addRoom(int[][] map, int rwidth, int rheight, int width, int height) {
        Random r = new Random();
        int x = r.nextInt(width - rwidth);
        int y = r.nextInt(height - rheight);

        for (int j = 0; j < rheight; j++) {
            for (int i = 0; i < rwidth; i++) {
                if (!checkCollision(map, x + i, y + j)) {
                    return map;
                }
            }
        }

        return buildWalls(map, x, y, rwidth, rheight);
    }

    /**
     * Builds walls for a room starting from coordinate x,y.
     *
     * @param map of the dungeon
     * @param x starting coordinate
     * @param y starting coordinate
     * @param rwidth width of the room, i.e. length of the x walls
     * @param rheight height of the room, i.e. length of the y walls
     * @return map with the new room
     */
    public static int[][] buildWalls(int[][] map, int x, int y, int rwidth, int rheight) {
        for (int j = 0; j < rheight; j++) {
            map[j + y][x] = 0;
            map[j + y][x + rwidth - 1] = 0;
        }
        for (int i = 0; i < rwidth; i++) {
            map[y][i + x] = 0;
            map[y + rheight - 1][i + x] = 0;
        }

        return map;
    }

    /**
     * Checks to see if the square in the given coordinates is 'empty', i.e. 0.
     * Returns true if square is empty, false if square is 'wall', i.e. 1.
     *
     * @param map of the room
     * @param x coordinate
     * @param y coordinate
     * @return true if square is free
     */
    public static boolean checkCollision(int[][] map, int x, int y) {
        if (map[y][x] == 1) {
            return true;
        }
        return false;
    }

    /**
     * Creates a string of the map, and prints it.
     *
     * @param map to be printed
     * @param width of the map
     * @param height of the map
     * @return map as a string
     */
    public static String mapToString(int[][] map, int width, int height) {
        String output = "";
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (map[y][x] == 0) {
                    output += "#";
                } else {
                    output += ".";
                }
            }
            output += "\n";
        }
        return output;
    }

    /**
     * Saves the map to a file on disk, under project directory with name
     * 'generated_map.txt'.
     *
     * @param map in string format
     * @throws IOException problem with writing to disk
     */
    public static void saveMap(String map) throws IOException {
        FileWriter fw = new FileWriter("generated_map.txt");
        PrintWriter pw = new PrintWriter(fw);
        pw.print(map);
        pw.close();
    }
}
