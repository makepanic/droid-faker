package de.rndm.droidFaker.generators.bookmark;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Browser;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.util.Log;
import de.rndm.droidFaker.FixtureSingleton;
import de.rndm.droidFaker.FixtureType;
import de.rndm.droidFaker.fixtures.*;
import de.rndm.droidFaker.fixtures.Number;
import de.rndm.droidFaker.generators.DataGenerator;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: mkp
 * Date: 27.10.13
 * Time: 22:47
 * To change this template use File | Settings | File Templates.
 */
public class BookmarkGenerator implements DataGenerator {

    private ContentResolver cr;

    public BookmarkGenerator(ContentResolver cr) {
        this.cr = cr;
    }

    private void insert(Random random){
        Fixture fixture = FixtureSingleton.getInstance().getFixture(FixtureType.URL);

        final ContentValues bookmarkValues = new ContentValues();
        bookmarkValues.put(Browser.BookmarkColumns.TITLE, fixture.getString(random));
        bookmarkValues.put(Browser.BookmarkColumns.URL, fixture.getString(random));
        bookmarkValues.put(Browser.BookmarkColumns.BOOKMARK, 1);

        final Uri newBookmark = cr.insert(Browser.BOOKMARKS_URI, bookmarkValues);

    }

    @Override
    public void generate(Random random, int amount) {
        Log.i("CallsGenerator", "generate " + amount);

        for (int i = 0; i < amount; i++) {
            insert(random);
        }
    }

    @Override
    public void reset() {
        try {
            cr.delete(Browser.BOOKMARKS_URI, "bookmark >= 0", null);

        } catch(IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
