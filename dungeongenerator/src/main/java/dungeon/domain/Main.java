package dungeon.domain;

import dungeon.maptools.MapSaver;
import java.io.IOException;

public class Main {

    /**
     * Main method that runs the algorithm to create a dungeon.
     *
     * @param args CLI support for these to be added
     * @throws java.io.IOException in case saving to file fails
     */
    public static void main(String[] args) throws IOException {
        int width = 180; // map width, screen max 180
        int height = 50; // map height, screen max 50
        int roomAttempts = 50; // how many times is addRoom() ran
        int minRoomSize = 4; // including walls, so 3 is the minimum
        int maxRoomRandom = 7; // up to this much is added to the minRoomSize
        int maxDoorsPerRoom = 2; // up to this many doors may be added to each room
        int multipleDoorsOdd = 4; // minimum of one, odds to add another door are 1/this
        
        /*
        * If saveToFile is true, final map is saved to generated_map.txt.
        * If printOutput AND printDebug are true, all the stages and their perf. times are printed.
        * If printOutput is true but printDebug is false, only the final map is printed.
        * If printDebug is true but printOutput is false, only the perf. times are printed.
        * If printDebug and printOutput are both false, only validity check is printed.
        * Validity check (true || false) is always printed.
        */
        boolean saveToFile = true;
        boolean printOutput = true;
        boolean printDebug = true;

        DungeonMap map = new DungeonMap(height, width, roomAttempts, maxDoorsPerRoom,
                multipleDoorsOdd);

        long startTimer = System.currentTimeMillis();
        map.initialise();
        map.addRooms(roomAttempts, minRoomSize, maxRoomRandom);
        long stopTimer = System.currentTimeMillis();
        if (printDebug) {
            System.out.println("Map init and room placement took: " + (stopTimer - startTimer) + "ms.");
            if (printOutput) {
                System.out.println(map.toString());
            }
        }

        startTimer = System.currentTimeMillis();
        map.createMaze();
        stopTimer = System.currentTimeMillis();
        if (printDebug) {
            System.out.println("Maze creation took: " + (stopTimer - startTimer) + "ms.");
            if (printOutput) {
                System.out.println(map.toString());
            }
        }

        startTimer = System.currentTimeMillis();
        map.placeDoors();
        stopTimer = System.currentTimeMillis();
        if (printDebug) {
            System.out.println("Door placement took: " + (stopTimer - startTimer) + "ms.");
            if (printOutput) {
                System.out.println(map.toString());
            }
        }

        startTimer = System.currentTimeMillis();
        map.fillDeadends();
        stopTimer = System.currentTimeMillis();
        if (printDebug) {
            System.out.println("Corridor sealing took: " + (stopTimer - startTimer) + "ms.");
        }
        if (printOutput) {
            System.out.println(map.toString());
        }
        

        if (saveToFile) {
            MapSaver ms = new MapSaver();
            ms.saveStringToFile(map.toString(), "generated_map");
        }

        startTimer = System.currentTimeMillis();
        System.out.print(map.runCrawlerTest());
        
        stopTimer = System.currentTimeMillis();
        if (printDebug) {
            System.out.println("\nValidity check took: " + (stopTimer - startTimer) + "ms.");
        }
    }
}
