package de.rndm.droidFaker.fixtures;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: mkp
 * Date: 20.10.13
 * Time: 21:02
 * To change this template use File | Settings | File Templates.
 */
public class Number {

    private static final int DEFAULT_LENGTH = 5;

    public static String getOne(Random random) {
        return getOne(random, DEFAULT_LENGTH);
    }

    public static String getOne(Random random, int length) {
        String code = "";
        for (int i = 0; i < length; i++) {
            code += random.nextInt(10);
        }
        return code;
    }
}
