package de.rndm.droidFaker.fixtures;

import java.util.Random;

/**
 * User: rndm
 * Date: 26.10.13
 * Time: 21:49
 */
public class Text {

    private static String ALPHABET = "qwertzuiopasdfghjklyxcvbnm ";

    /**
     * Returns a randomized string with a given length using a Random object
     * @param random Random object that picks chars from the ALPHABET
     * @param maxLength maximum length for the randomized string
     * @return randomized string
     */
    public static String getText(Random random, int maxLength){
        String result = "";
        int alphabetLength = ALPHABET.length();
        int max = random.nextInt(maxLength);

        for(int i = 1; i < max + 1; i++) {
            result += ALPHABET.charAt(random.nextInt(alphabetLength));
        }

        return result;
    }
}
