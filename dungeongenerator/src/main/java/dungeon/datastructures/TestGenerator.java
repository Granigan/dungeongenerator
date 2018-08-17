package dungeon.datastructures;

import dungeon.interfaces.RandomGenerator;

/**
 * Testing class that can replace random number generation with specified list
 * of numbers.
 *
 * @author tgtapio
 */
public class TestGenerator implements RandomGenerator {

    private int[] numbers;
    private int i;

    /**
     * Constructor sets next 100 "random" numbers to be 0.
     */
    public TestGenerator() {
        i = 0;
        numbers = new int[100];
        for (int x = 0; x < 100; x++) {
            numbers[x] = 0;
        }
    }

    /**
     * For testing use. By default, first 100 times return 0. Alter this by
     * setting your own int[] numbers with the setter.
     *
     * @return the next number from the numbers array. 0 by default, up to 100
     * numbers.
     */
    @Override
    public int nextInt() {
        int number = numbers[i];
        i++;
        return number;
    }

    /**
     * Calls the nextInt(), does not respect the maximum parameter. You must
     * instead set suitable numbers to the numbers array with the setter.
     *
     * @param max does not affect the returned number.
     * @return the next number from the numbers array.
     */
    @Override
    public int nextInt(int max) {
        return nextInt();
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    public void setI(int i) {
        this.i = i;
    }

}
