package dungeon.domain;

import dungeon.maptools.DoorBuilder;
import dungeon.maptools.DungeonMap;
import java.io.IOException;

public class Main {

    /**
     * Main method that runs the algorithm to create a dungeon.
     *
     * @param args CLI support for these to be added
     * @throws IOException problem with writing to disk
     */
    public static void main(String[] args) throws IOException {
        int width = 40; // map width, screen max 180
        int height = 15; // map height, screen max 50
        int roomAttempts = 8; // how many times is addRoom() ran
        int minRoomSize = 3; // including walls, so 3 is the minimum
        int maxRoomRandom = 8; // up to this much is added to the minRoomSize

        DungeonMap map = new DungeonMap(height, width, roomAttempts);
        map.initialise();
        
        map.addRooms(roomAttempts, minRoomSize, maxRoomRandom);
        
        map.createMaze();
        
        


        map.placeDoors();
        
        map.fillDeadends();

        System.out.println(map.toDebugString() + map.getAddedRoomCount() + " rooms placed");
        System.out.println(map.toString() + map.getAddedRoomCount() + " rooms placed");
//        map.saveMap();

    }
}
