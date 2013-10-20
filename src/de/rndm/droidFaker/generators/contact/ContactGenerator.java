package de.rndm.droidFaker.generators.contact;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import de.rndm.droidFaker.fixtures.Name;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: mkp
 * Date: 20.10.13
 * Time: 14:58
 * To change this template use File | Settings | File Templates.
 */
public class ContactGenerator implements DataGenerator{


    private ContentResolver cr;

    public ContactGenerator(ContentResolver cr) {
        this.cr = cr;
    }
    private void insert(Random random) {
        String firstName = Name.getName(random);
        String lastName = Name.getName(random);

        String accountType = null;
        String accountName = null;

        ContentValues values = new ContentValues();
        values.put(RawContacts.ACCOUNT_TYPE, accountType);
        values.put(RawContacts.ACCOUNT_NAME, accountName);
        Uri rawContactUri = cr.insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(rawContactUri);

        values.clear();
        values.put(Data.RAW_CONTACT_ID, rawContactId);
        values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
        values.put(Phone.NUMBER, "0123456789");
        values.put(Phone.TYPE, Phone.TYPE_CUSTOM);
        values.put(Phone.LABEL, "free directory assistance");
        values.put(StructuredName.DISPLAY_NAME, firstName + " " + lastName);
        values.put(StructuredName.GIVEN_NAME, firstName);
        values.put(StructuredName.FAMILY_NAME, lastName);
        values.put(Email.DATA, firstName + "." + lastName + "@droid.faker");
        values.put(Email.TYPE, Email.TYPE_HOME);

        Log.i("ContactGenerator insert", "generated contact for " + firstName + " " + lastName);

        Uri dataUri = cr.insert(Data.CONTENT_URI, values);
    }

    @Override
    public void generate(Random random, int amount) {
        for (int i = 0; i < amount; i++) {
            insert(random);
        }
    }

    /**
     * @see <a href="http://stackoverflow.com/a/7025118">http://stackoverflow.com/a/7025118</a>
     */
    @Override
    public void reset() {
        ContentResolver contentResolver = cr;
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            String lookupKey = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
            Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, lookupKey);
            contentResolver.delete(uri, null, null);
        }
    }
}
