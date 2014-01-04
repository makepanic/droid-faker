package de.rndm.droidFaker.generators;

import de.rndm.droidFaker.Callback;

import java.util.Random;

/**
 * User: rndm
 * Date: 20.10.13
 * Time: 17:24
 */
public interface AsyncGenerator{
    public void generate(Random random, int amount, Callback callback);
    public void reset();
}
