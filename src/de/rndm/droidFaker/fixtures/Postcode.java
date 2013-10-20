package de.rndm.droidFaker.fixtures;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: mkp
 * Date: 20.10.13
 * Time: 21:02
 * To change this template use File | Settings | File Templates.
 */
public class Postcode {

    private static final int POSTCODE_LENGTH = 5;

    public static String getOne(Random random) {
        String code = "";

        for (int i = 0; i < 5; i++) {
            code += random.nextInt(10);
        }

        return code;
    }
}
