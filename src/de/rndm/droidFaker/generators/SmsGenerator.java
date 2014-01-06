package de.rndm.droidFaker.generators;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import de.rndm.droidFaker.model.FixtureSingleton;
import de.rndm.droidFaker.model.FixtureType;
import de.rndm.droidFaker.fixtures.Fixture;
import de.rndm.droidFaker.fixtures.Text;
import de.rndm.droidFaker.generators.DataGenerator;

import java.util.Random;

/**
 * User: rndm
 * Date: 26.10.13
 * Time: 21:30
 */
public class SmsGenerator implements DataGenerator {

    private ContentResolver cr;

    public SmsGenerator(ContentResolver cr) {
        this.cr = cr;
    }

    public void insert(String address, String body){
        ContentValues values = new ContentValues();
        values.put("address", address);
        values.put("body", body);
        cr.insert(Uri.parse("content://sms/inbox"), values);

    }

    @Override
    public void generate(Random random, int amount) {
        Fixture nameFixture = FixtureSingleton.getInstance().getFixture(FixtureType.NAME);
        Log.i("SmsGenerator", "generate " + amount);

        for(int i = 0; i < amount; i++) {
            insert(nameFixture.getString(random) + ' ' + nameFixture.getString(random), Text.getText(random, 100));
        }
    }

    @Override
    public void reset() {
        // via http://stackoverflow.com/questions/9443356/how-to-delete-all-sms-from-inbox-programmatically-in-android

        Uri inboxUri = Uri.parse("content://sms/inbox");
        Cursor c = cr.query(inboxUri , null, null, null, null);
        while (c.moveToNext()) {
            // Delete the SMS
            String pid = c.getString(0); // Get id;
            String uri = "content://sms/" + pid;
            cr.delete(Uri.parse(uri), null, null);
        }
    }
}
