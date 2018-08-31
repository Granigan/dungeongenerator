package dungeon.datastructures;

/**
 *
 * @author tgtapio
 */
public class WallMap {
    
    CoordinatesList[] values;
    int modulus;
    
    public WallMap() {
        values = new CoordinatesList[500];
        modulus = 500;
    }
    
    public int hash(int key) {
        return key*33*17 % modulus;
    }
}
