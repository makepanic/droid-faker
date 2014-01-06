package de.rndm.droidFaker.model;

import java.util.HashMap;

/**
 * User: rndm
 * Date: 04.01.14
 * Time: 20:34
 */
public interface ReflectedTask {
    /**
     * constructor like method to initialize the task
     * @param map map of parameters that are available from the json object
     */
    public void init(HashMap<String, Object> map);
}
