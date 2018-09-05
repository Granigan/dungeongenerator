package dungeon.domain;

import dungeon.maptools.MapSaver;
import java.io.IOException;

public class Main {

    /**
     * Main method that runs the algorithm to create a dungeon.
     *
     * @param args CLI support for these to be added
     */
    public static void main(String[] args) throws IOException {
        int width = 180; // map width, screen max 180
        int height = 50; // map height, screen max 50
        int roomAttempts = 50; // how many times is addRoom() ran
        int minRoomSize = 5; // including walls, so 3 is the minimum
        int maxRoomRandom = 12; // up to this much is added to the minRoomSize
        int maxDoorsPerRoom = 3; // up to this many doors may be added to each room
        int multipleDoorsOdd = 3; // minimum of one, odds to add another door are 1/this
        boolean saveToFile = true; // whether or not to save the output to a file
        boolean printOutput = false; // whether or not to print the output

        DungeonMap map = new DungeonMap(height, width, roomAttempts, maxDoorsPerRoom,
                multipleDoorsOdd);
        map.initialise();
        map.addRooms(roomAttempts, minRoomSize, maxRoomRandom);
        map.createMaze();
        map.placeDoors();
        map.fillDeadends();

        if (printOutput) {
            System.out.println(map.toString());
        }

//        if(saveToFile) {
//            MapSaver ms = new MapSaver();
//            ms.saveStringToFile(map.toString(), "generated_map");
//        }

        // run crawler in dungeonmap (and find first 

        if (saveToFile) {
            MapSaver ms = new MapSaver();
            ms.saveStringToFile(map.toString(), "generated_map");
        }
        
        System.out.print(map.runCrawlerTest());
    }
}
