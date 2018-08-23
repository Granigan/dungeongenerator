package dungeon.domain;

import java.io.IOException;

public class Main {

    /**
     * Main method that runs the algorithm to create a dungeon.
     *
     * @param args CLI support for these to be added
     * @throws IOException problem with writing to disk
     */
    public static void main(String[] args) throws IOException {
        int width = 180; // map width, screen max 180
        int height = 50; // map height, screen max 50
        int roomAttempts = 50; // how many times is addRoom() ran
        int minRoomSize = 5; // including walls, so 3 is the minimum
        int maxRoomRandom = 12; // up to this much is added to the minRoomSize
        int maxDoorsPerRoom = 3; // up to this many doors may be added to each room
        int multipleDoorsOdd = 3; // minimum of one, odds to add another door are 1/this

        DungeonMap map = new DungeonMap(height, width, roomAttempts, maxDoorsPerRoom, 
                            multipleDoorsOdd);
        map.initialise();

        map.addRooms(roomAttempts, minRoomSize, maxRoomRandom);

        map.createMaze();

        map.placeDoors();

        map.fillDeadends();

//        System.out.println(map.toDebugString() + map.getAddedRoomCount() 
//                + " rooms placed, corridor segments total: " + map.getCorridorSegments());
//        System.out.println(map.toString() + map.getAddedRoomCount() 
//                + " rooms placed, total segments: " + map.getCorridorSegments());
        map.saveMap();
    }
}
