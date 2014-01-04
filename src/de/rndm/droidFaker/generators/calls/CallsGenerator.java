package de.rndm.droidFaker.generators.calls;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.provider.CallLog;
import android.util.Log;
import de.rndm.droidFaker.model.FixtureSingleton;
import de.rndm.droidFaker.model.FixtureType;
import de.rndm.droidFaker.fixtures.Fixture;
import de.rndm.droidFaker.fixtures.Number;
import de.rndm.droidFaker.fixtures.Text;
import de.rndm.droidFaker.generators.DataGenerator;

import java.util.Random;

/**
 * User: rndm
 * Date: 27.10.13
 * Time: 22:47
 */
public class CallsGenerator implements DataGenerator {

    private ContentResolver cr;

    public CallsGenerator(ContentResolver cr) {
        this.cr = cr;
    }

    public void insert(String number, long date, long duration, String cachedName, String cachedNumberLabel){
        //To change body of implemented methods use File | Settings | File Templates.
        ContentValues values = new ContentValues();
        values.put(CallLog.Calls.NUMBER, number);
        values.put(CallLog.Calls.DATE, date);
        values.put(CallLog.Calls.DURATION, duration);
        values.put(CallLog.Calls.TYPE, CallLog.Calls.OUTGOING_TYPE);
        values.put(CallLog.Calls.NEW, 1);
        values.put(CallLog.Calls.CACHED_NAME, cachedName);
        values.put(CallLog.Calls.CACHED_NUMBER_TYPE, 0);
        values.put(CallLog.Calls.CACHED_NUMBER_LABEL, cachedNumberLabel);
        cr.insert(CallLog.Calls.CONTENT_URI, values);
    }

    @Override
    public void generate(Random random, int amount) {
        Log.i("CallsGenerator", "generate " + amount);
        Fixture fixture = FixtureSingleton.getInstance().getFixture(FixtureType.NAME);

        for (int i = 0; i < amount; i++) {
            insert( Number.getOne(random, 10),
                    Long.valueOf(Number.getOne(random, 13)),
                    Long.valueOf(Number.getOne(random, 3)),
                    fixture.getString(random),
                    Text.getText(random, 10));
        }
    }

    @Override
    public void reset() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
