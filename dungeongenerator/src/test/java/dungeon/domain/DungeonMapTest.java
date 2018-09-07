package dungeon.domain;

import dungeon.maptools.TestDoorBuilder;
import dungeon.maptools.TestMazeBuilder;
import dungeon.maptools.TestRoomBuilder;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tgtapio
 */
public class DungeonMapTest {

    DungeonMap dm = new DungeonMap(3, 3, 100, 1, 1);

    public int[][] mapCreator(int filler, int size) {
        int[][] map = new int[size][size];
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                map[j][i] = filler;
            }
        }
        return map;
    }

    public DungeonMapTest() {
    }

    @Test
    public void initialiseTest1() {
        int[][] target = mapCreator(0, 3);
        target[1][1] = 1;
        dm.initialise();
        assertArrayEquals(target, dm.getMap());
    }

    @Test
    public void toDebugStringTest1() {
        int[][] allInOne = mapCreator(1, 3);
        allInOne[1][1] = 5;
        allInOne[1][0] = 0;
        dm.setMap(allInOne);
        assertEquals("111\n#51\n111\n", dm.toDebugString());
    }

    @Test
    public void toStringTest1() {
        int[][] allInOne = mapCreator(1, 3);
        allInOne[1][1] = 5;
        allInOne[1][0] = 0;
        dm.setMap(allInOne);
        assertEquals("+++\n█ +\n+++\n", dm.toString());
    }

    @Test
    public void addRoomsTest1() {
        dm.setRb(new TestRoomBuilder());
        dm.setRoomAttempts(5);
        int[][] map = mapCreator(9, 3);
        dm.setMap(map);
        int[][] target = mapCreator(9, 3);
        target[0][0] = 5;
        target[1][0] = 3;
        target[0][1] = 2;
        dm.addRooms(5, 3, 2);
        assertArrayEquals(map, dm.getMap());
    }

    @Test
    public void createMazeTest1() {
        dm.setRb(new TestRoomBuilder());
        dm.setMb(new TestMazeBuilder());
        int[][] map = mapCreator(0, 3);
        int[][] target = mapCreator(0, 3);
        target[0][0] = 5;
        target[1][1] = 5_000_000;
        dm.setMap(map);
        dm.createMaze();
        assertArrayEquals(target, dm.getMap());        
    }
    
    @Test
    public void placeDoorsTest1() {
        dm.setDb(new TestDoorBuilder());
        dm.setRb(new TestRoomBuilder());
        dm.setMap(mapCreator(0, 2));
        dm.placeDoors();
        
        int[][] target = mapCreator(0, 2);
        target[0][0] = -1;
        target[1][1] = -1;
        
        assertArrayEquals(target, dm.getMap());
    }
}
