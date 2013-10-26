package de.rndm.droidFaker.generators.sms;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import de.rndm.droidFaker.fixtures.Name;
import de.rndm.droidFaker.fixtures.Text;
import de.rndm.droidFaker.generators.DataGenerator;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: mkp
 * Date: 26.10.13
 * Time: 21:30
 * To change this template use File | Settings | File Templates.
 */
public class SmsGenerator implements DataGenerator {

    private ContentResolver cr;

    public SmsGenerator(ContentResolver cr) {
        this.cr = cr;
    }

    @Override
    public void generate(Random random, int amount) {
        Log.i("SmsGenerator", "generate " + amount);

        for(int i = 0; i < amount; i++) {
            ContentValues values = new ContentValues();
            values.put("address", Name.getName(random) + ' ' + Name.getName(random));
            values.put("body", Text.getText(random, 100));
            cr.insert(Uri.parse("content://sms/inbox"), values);
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
