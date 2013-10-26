package de.rndm.droidFaker.fixtures;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: mkp
 * Date: 26.10.13
 * Time: 21:49
 * To change this template use File | Settings | File Templates.
 */
public class Text {

    public static String alphabet = "qwertzuiopasdfghjklyxcvbnm ";

    public static String getText(Random random, int maxLength){
        String result = "";
        int alphabetLength = alphabet.length();
        int max = random.nextInt(maxLength);

        for(int i = 0; i < max; i++) {
            result += alphabet.charAt(random.nextInt(alphabetLength));
        }

        return result;
    }
}
