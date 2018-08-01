package dungeon;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {
        /**
         * PARAMETERS
         * These will eventually be gotten from the CLI command.
         */
        int width = 180; // map width
        int height = 50; // map height
        int roomAttempts = 150; // how many times is addRoom() ran
        int minRoomSize = 4; // including walls, 3 is thus the absolute minimum
        int maxRoomRandom = 15; // up to this much is added to the minRoomSize
       
        Random r = new Random();

        /**
         * MAP INITIALISATION
         * 0 is an empty square
         * 1 is a wall
         * 
         * Edges will be wall, everything inside will be empty at start.
         */
        int[][] map = new int[height][width];
        for(int y = 0; y < height; y++) {
            map[y][0] = 1;
            map[y][width - 1] = 1;
        }
        for(int x = 0; x < width; x++) {
            map[0][x] = 1;
            map[height - 1][x] = 1;
        }
        for(int y = 1; y < height - 1; y++) {
            for(int x = 1; x < width - 1; x++) {
                map[y][x] = 0;
            }
        }
        
        
        /**
         * ROOMS
         */
        
        while(roomAttempts > 0) {
            map = addRoom(map, minRoomSize + r.nextInt(maxRoomRandom), minRoomSize + r.nextInt(maxRoomRandom),
                    width, height);
            roomAttempts--;
        }
        
        
        
        /**
         * PRINT out the final map.
         */
        System.out.println(mapToString(map, width, height));
        saveMap(mapToString(map, width, height));
        

    }

    /**
     * Attempts to place a room on the map by first creating a parameter sized
     * room, then choosing random coordinates for it on the map. If it collides
     * with another room or wall, the room is not placed.
     * 
     * First iteration is to check each square for collision. This probably 
     * can be optimised later on. Also something to consider: should the room's
     * starting location be fully random inside the map area, or limited so
     * that it will always at least fit inside the outer walls. For now, going
     * with the option to limit starting location so that the walls won't be a 
     * problem. This I think will be easier, though it means the slight 
     * change in how often addRoom will be able to fit the room in.
     * 
     * @param map before addition
     * @param rwidth width of the room to be added
     * @param rheight height of the room to be added
     * @param width of the map
     * @param height of the map
     * @return the map with the new room if it was added
     */
    public static int[][] addRoom(int[][] map, int rwidth, int rheight, int width, int height) {
//        System.out.println("trying to add a room");
        Random r = new Random();
        int x = r.nextInt(width - rwidth);
        int y = r.nextInt(height - rheight);
//        System.out.println("starting corner is " + x + "," + y + ", and size is " + rwidth + "x" + rheight);

        // test each square for collision
        for(int j = 0; j < rheight; j++) {
            for(int i = 0; i < rwidth; i++) {
                if(!checkCollision(map, x+i, y+j)) {
//                    System.out.println("collision detected");
                    return map;
                }
            }
        }
        
        // build walls for the room
//        System.out.println("building walls");
        for(int j = 0; j < rheight; j++) {
            map[j+y][x] = 1;
            map[j+y][x+rwidth-1] = 1;
        }
        for(int i = 0; i < rwidth; i++) {
            map[y][i+x] = 1;
            map[y+rheight-1][i+x] = 1;
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
        if(map[y][x] == 0) {
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
     */
    public static String mapToString(int [][] map, int width, int height) {
        String output = "";
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                if(map[y][x] == 0) {
                    output += ".";
                } else {
                    output += "#";
                }
            }
            output += "\n";
        }
        return output;
    }
    
    public static void saveMap(String map) throws IOException {
        FileWriter fw = new FileWriter("generated_map.txt");
        PrintWriter pw = new PrintWriter(fw);
        pw.print(map);
        pw.close();
    }
}
