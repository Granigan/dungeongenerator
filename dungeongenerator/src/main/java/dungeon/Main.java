package dungeon;

public class Main {

    public static void main(String[] args) {
        /**
         * PARAMETERS
         * These will eventually be gotten from the CLI command.
         */
        int width = 22; // map width
        int height = 8; // map height
        int roomAttempts = 15; // how many times is addRoom() ran

        /**
         * MAP INITIALISATION
         * 0 is an empty square
         * 1 is a wall
         * 
         * Edges will be wall, everything inside will be empty.
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
         * Print out the final map.
         */
        printMap(map, width, height);

    }

    /**
     * Attempts to place a room on the map by first creating a parameter sized
     * room, then choosing random coordinates for it on the map. If it collides
     * with another room or wall, the room is not placed.
     * 
     * @param map before addition
     * @param width for the room to be added
     * @param height for the room to be added
     * @return the map with the new room if it was added
     */
    public static int[][] addRoom(int[][] map, int width, int height) {
        
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
        if(map[x][y] == 0) {
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
    public static void printMap(int [][] map, int width, int height) {
        String output = "";
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                if(map[y][x] == 0) {
                    output += ".";
                } else {
                    output += "X";
                }
            }
            output += "\n";
        }
        System.out.println(output);
    }
}
