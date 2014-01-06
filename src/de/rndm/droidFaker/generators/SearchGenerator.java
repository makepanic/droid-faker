package de.rndm.droidFaker.generators;

import android.content.ContentResolver;
import android.provider.Browser;
import de.rndm.droidFaker.fixtures.Text;
import de.rndm.droidFaker.generators.DataGenerator;

import java.util.Random;

/**
 * User: rndm
 * Date: 27.10.13
 * Time: 22:47
 */
public class SearchGenerator implements DataGenerator {

    private ContentResolver cr;

    public SearchGenerator(ContentResolver cr) {
        this.cr = cr;
    }

    /**
     * Inserts a query to the browser search database
     * @param query browser search query
     */
    public void insert(String query){
        Browser.addSearchUrl(cr, query);
    }

    /**
     * Generates a given amount of random browser search items
     * @param random Random object
     * @param amount number of objects to insert
     */
    @Override
    public void generate(Random random, int amount) {

        for (int i = 0; i < amount; i++) {
            insert(Text.getText(random, 10) + " ");
        }
    }

    /**
     * clears the browser search database
     */
    @Override
    public void reset() {
        Browser.clearSearches(cr);
    }
}
