package de.rndm.droidFaker.generators.search;

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

    public void insert(String query){
        Browser.addSearchUrl(cr, query);
    }

    @Override
    public void generate(Random random, int amount) {

        for (int i = 0; i < amount; i++) {
            insert(Text.getText(random, 10) + " ");
        }
    }

    @Override
    public void reset() {
        Browser.clearSearches(cr);
    }
}
