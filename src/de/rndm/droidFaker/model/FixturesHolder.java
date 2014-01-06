package de.rndm.droidFaker.model;

import de.rndm.droidFaker.fixtures.Fixture;

import java.util.HashMap;
import java.util.Iterator;

/**
 * User: rndm
 * Date: 31.12.13
 * Time: 00:33
*/

/**
 * Class that holds a map of {@link de.rndm.droidFaker.fixtures.Fixture}
 */
public class FixturesHolder {
    private final HashMap<String, Fixture> fixtures = new HashMap<String, Fixture>();

    public FixturesHolder(HashMap<String, String[]> fixtures){
        // loop through fixture map
        Iterator it = fixtures.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pairs = (HashMap.Entry)it.next();

            // create fixture using key and value
            this.fixtures.put((String) pairs.getKey(), new Fixture((String[])pairs.getValue()));

            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    /**
     * Returns a fixture from the map using the given {@link de.rndm.droidFaker.model.FixtureType} as key
     * @param what key to look in the map for a fixture
     * @return found Fixture or null if not found
     */
    public Fixture getFixture(FixtureType what){
        return fixtures.get(what.toString());
    }

    @Override
    public String toString() {
        return "FixturesHolder{" +
                "fixtures=" + fixtures +
                '}';
    }
}
