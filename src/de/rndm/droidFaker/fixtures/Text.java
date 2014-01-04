package de.rndm.droidFaker.fixtures;

import java.util.Random;

/**
 * User: rndm
 * Date: 26.10.13
 * Time: 21:49
 */
public class Text {

    public static String alphabet = "qwertzuiopasdfghjklyxcvbnm ";

    public static String getText(Random random, int maxLength){
        String result = "";
        int alphabetLength = alphabet.length();
        int max = random.nextInt(maxLength);

        for(int i = 1; i < max + 1; i++) {
            result += alphabet.charAt(random.nextInt(alphabetLength));
        }

        return result;
    }
}
