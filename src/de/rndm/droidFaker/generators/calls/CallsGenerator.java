package de.rndm.droidFaker.generators.calls;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.provider.CallLog;
import android.util.Log;
import de.rndm.droidFaker.fixtures.Name;
import de.rndm.droidFaker.fixtures.Number;
import de.rndm.droidFaker.fixtures.Text;
import de.rndm.droidFaker.generators.DataGenerator;

import java.util.Date;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: mkp
 * Date: 27.10.13
 * Time: 22:47
 * To change this template use File | Settings | File Templates.
 */
public class CallsGenerator implements DataGenerator {

    private ContentResolver cr;

    public CallsGenerator(ContentResolver cr) {
        this.cr = cr;
    }

    private void insert(Random random){
        //To change body of implemented methods use File | Settings | File Templates.
        ContentValues values = new ContentValues();
        values.put(CallLog.Calls.NUMBER, Number.getOne(random, 10));
        values.put(CallLog.Calls.DATE, Long.valueOf(Number.getOne(random, 13)));
        values.put(CallLog.Calls.DURATION, Long.valueOf(Number.getOne(random, 3)));
        values.put(CallLog.Calls.TYPE, CallLog.Calls.OUTGOING_TYPE);
        values.put(CallLog.Calls.NEW, 1);
        values.put(CallLog.Calls.CACHED_NAME, Name.getName(random));
        values.put(CallLog.Calls.CACHED_NUMBER_TYPE, 0);
        values.put(CallLog.Calls.CACHED_NUMBER_LABEL, Text.getText(random, 10));
        cr.insert(CallLog.Calls.CONTENT_URI, values);
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
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
