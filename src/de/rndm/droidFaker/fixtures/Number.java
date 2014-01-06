package de.rndm.droidFaker.fixtures;

import java.util.Random;

/**
 * @author mkp
 *
 * Fixture that returns a random concatenation of numbers.
 */
public class Number extends Fixture {

    private static final int DEFAULT_LENGTH = 5;

    public Number(String[] fixtures) {
        super(fixtures);
    }

    /**
     * @see de.rndm.droidFaker.fixtures.Fixture
     * @param random
     * @return
     */
    public static String getOne(Random random) {
        return getOne(random, DEFAULT_LENGTH);
    }

    /**
     * Returns a concatenated String with a given length using {@link Number}.getOne(Random random)
     * @param random
     * @param length
     * @return
     */
    public static String getOne(Random random, int length) {
        String code = "";
        for (int i = 0; i < length; i++) {
            code += random.nextInt(10);
        }
        return code;
    }
}
