package de.rndm.droidFaker.model;

import de.rndm.droidFaker.AppPreferences;

/**
 * Created with IntelliJ IDEA.
 * User: mkp
 * Date: 20.10.13
 * Time: 18:52
 * To change this template use File | Settings | File Templates.
 */
public class ContactSettings implements SettingsModel{
    public final static String PREF_COUNT = "contact.count";

    private int count;

    public ContactSettings(int count) {
        this.count = count;
    }

    public ContactSettings(AppPreferences preferences) {
        this.count = preferences.getInteger(ContactSettings.PREF_COUNT);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean equals(ContactSettings other){
        boolean equal = true;

        equal = equal && (other.count == this.count);

        return equal;
    }

    @SuppressWarnings("CloneDoesntCallSuperClone")
    public ContactSettings clone(){
        ContactSettings clone = new ContactSettings(this.count);
        return clone;
    }

    @Override
    public void persist(AppPreferences preferences) {
        preferences.set(ContactSettings.PREF_COUNT, this.count);
    }
}
