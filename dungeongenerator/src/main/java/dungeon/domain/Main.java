package dungeon.domain;

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
        int width = 180; // map width
        int height = 50; // map height
        int roomAttempts = 150; // how many times is addRoom() ran
        int minRoomSize = 4; // including walls, 3 is thus the absolute minimum
        int maxRoomRandom = 15; // up to this much is added to the minRoomSize

        DungeonMap map = new DungeonMap(height, width);
        map.initMap();
        map.addRooms(roomAttempts, minRoomSize, maxRoomRandom);

        System.out.println(map.toString() + map.getRoomCount() + " rooms placed");
        map.saveMap();

    }
}
