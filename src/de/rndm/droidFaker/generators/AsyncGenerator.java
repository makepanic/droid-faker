package de.rndm.droidFaker.generators;

import de.rndm.droidFaker.Callback;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: mkp
 * Date: 20.10.13
 * Time: 17:24
 * To change this template use File | Settings | File Templates.
 */
public interface AsyncGenerator{
    public void generate(Random random, int amount, Callback callback);
    public void reset();
}
