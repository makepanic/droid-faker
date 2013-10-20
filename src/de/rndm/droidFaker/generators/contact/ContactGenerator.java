package de.rndm.droidFaker.generators.contact;

import android.content.*;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import de.rndm.droidFaker.fixtures.*;
import de.rndm.droidFaker.fixtures.Number;

import java.util.ArrayList;
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

        // via http://matrix-examplecode.blogspot.de/2012/03/add-contact-details-in-android.html
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        int rawContactInsertIndex = ops.size();

        ops.add(ContentProviderOperation.newInsert(RawContacts.CONTENT_URI)
            .withValue(RawContacts.ACCOUNT_TYPE, null)
            .withValue(RawContacts.ACCOUNT_NAME, null).build());

        //Phone Number
        ops.add(ContentProviderOperation
            .newInsert(ContactsContract.Data.CONTENT_URI)
            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,
                    rawContactInsertIndex)
            .withValue(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
            .withValue(Phone.NUMBER, Number.getOne(random, 12))
            .withValue(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
            .withValue(Phone.TYPE, Phone.TYPE_HOME).build());

        //Display name/Contact name
        ops.add(ContentProviderOperation
            .newInsert(ContactsContract.Data.CONTENT_URI)
            .withValueBackReference(Data.RAW_CONTACT_ID,
                    rawContactInsertIndex)
            .withValue(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE)
            .withValue(StructuredName.DISPLAY_NAME, firstName + " " + lastName)
            .build());

        //Email details
        ops.add(ContentProviderOperation
            .newInsert(ContactsContract.Data.CONTENT_URI)
            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,
                    rawContactInsertIndex)
            .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
            .withValue(ContactsContract.CommonDataKinds.Email.DATA, firstName + "." + lastName + "@droid.faker")
            .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
            .withValue(ContactsContract.CommonDataKinds.Email.TYPE, Email.TYPE_WORK).build());

        //Postal Address
        ops.add(ContentProviderOperation
            .newInsert(ContactsContract.Data.CONTENT_URI)
            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,
                    rawContactInsertIndex)
//            .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE)
//            .withValue(ContactsContract.CommonDataKinds.StructuredPostal.POBOX, "Postbox")

            .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE)
            .withValue(ContactsContract.CommonDataKinds.StructuredPostal.STREET, Street.getName(random))

            .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE)
            .withValue(ContactsContract.CommonDataKinds.StructuredPostal.CITY, City.getName(random))

//            .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE)
//            .withValue(ContactsContract.CommonDataKinds.StructuredPostal.REGION, "region")

            .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE)
            .withValue(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE, Number.getOne(random))

            .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE)
            .withValue(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY, Country.getName(random))

            .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE)
            .withValue(ContactsContract.CommonDataKinds.StructuredPostal.TYPE, ContactsContract.CommonDataKinds.StructuredPostal.TYPE_OTHER)
            .build());

        //Organization details
        ops.add(ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(Data.RAW_CONTACT_ID,
                        rawContactInsertIndex)
                .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Organization.COMPANY, Company.getName(random))
                .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Organization.TITLE, Title.getName(random))
                .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
                .build());

        //IM details
        ops.add(ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(Data.RAW_CONTACT_ID,
                        rawContactInsertIndex)
                .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Im.DATA, Nickname.getName(random))
                .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE )
                .withValue(ContactsContract.CommonDataKinds.Im.DATA5, ContactsContract.CommonDataKinds.Im.TYPE_WORK)
                .build());

        try {
            ContentProviderResult[] res = cr.applyBatch(
                    ContactsContract.AUTHORITY, ops);
        } catch (RemoteException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (OperationApplicationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
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
