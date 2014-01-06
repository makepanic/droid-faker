package de.rndm.droidFaker;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * User: rndm
 * Date: 20.10.13
 * Time: 17:17
 */

/**
 * {@link android.content.SharedPreferences} wrapper
 */
public class AppPreferences {
    public static final String VAL_SEED = "seed.val";
    public static final String COUNT_BOOKMARKS = "bookmarks.count";
    public static final String COUNT_EMAIL = "email.count";
    public static final String COUNT_CALLS = "calls.count";
    public static final String COUNT_CONTACT = "contact.count";
    public static final String COUNT_HISTORY = "history.count";
    public static final String COUNT_SEARCH = "search.count";
    public static final String COUNT_SMS = "sms.count";
    public static final String COUNT_WEB = "web.count";
    public static final String COUNT_WIFI = "wifi.count";

    private static final String APP_SHARED_PREFS = "de.rndm.droidFaker";

    private final SharedPreferences appSharedPrefs;
    private final SharedPreferences.Editor prefsEditor;

    public AppPreferences(Context context)
    {
        this.appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.prefsEditor = appSharedPrefs.edit();
    }

    String getString(String key, String defValue){
        String value = "";
        if(key != null && !key.equals("")){
            value = appSharedPrefs.getString(key, "");
        }
        return value;
    }

    public String getString(String key){
        return getString(key, "");
    }

    public boolean getBoolean(String key, boolean defValue){
        boolean value = defValue;
        if(key != null && !key.equals("")){
            value = appSharedPrefs.getBoolean(key, defValue);
        }
        return value;
    }
    public boolean getBoolean(String key){
        return getBoolean(key, false);
    }

    public int getInteger(String key, int defValue){
        int value = defValue;
        if(key != null && !key.equals("")){
            value = Integer.valueOf(appSharedPrefs.getString(key, "" + defValue));
        }
        return value;
    }
    public int getInteger(String key){
        return getInteger(key, 0);
    }

    public void set(String key, String value){
        if(key != null && value != null && !key.equals("") && !value.equals("")){
            prefsEditor.putString(key, value);
            prefsEditor.commit();
        }
    }
    public void set(String key, int value){
        if(key != null && !key.equals("")){
            prefsEditor.putString(key, "" + value);
            prefsEditor.commit();
        }
    }
    public void set(String key, boolean value){
        if(key != null && !key.equals("")){
            prefsEditor.putBoolean(key, value);
            prefsEditor.commit();
        }
    }
}