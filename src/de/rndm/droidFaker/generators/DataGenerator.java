package de.rndm.droidFaker.generators;

import android.content.ContentResolver;

import java.util.Random;

/**
 * User: rndm
 * Date: 20.10.13
 * Time: 17:24
 */
public interface DataGenerator {
    public void generate(Random random, int amount);
    public void reset();
}
