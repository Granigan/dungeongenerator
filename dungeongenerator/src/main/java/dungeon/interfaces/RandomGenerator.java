package dungeon.interfaces;

/**
 * Interface for random generators. Needed for dependency injection testing.
 *
 * @author tgtapio
 */
public interface RandomGenerator {

    /**
     * Get a pseudorandom [0-2^15[ number.
     *
     * @return a pseudorandom, [0-2^15[ number
     */
    public int nextInt();

    /**
     * Get a pseudorandom number, limited by max.
     *
     * @param max limits the return range to be [0-max[
     * @return pseudorandom [0-max[
     */
    public int nextInt(int max);
}
