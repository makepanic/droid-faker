package de.rndm.droidFaker;

import android.util.Log;
import com.google.gson.Gson;
import de.rndm.droidFaker.model.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Class that represents the droid-faker config file
 */
public class ConfigFile {
    private String path;
    private Config cfg;

    public ConfigFile(String path) {
        this.path = path;
        this.load();
    }

    public void load(String path) {
        this.path = path;
        this.load();
    }

    /**
     * Loads the config file from the object path variable using {@link com.google.gson.Gson}
     */
    public void load(){
        Gson gson = new Gson();

        try {
            BufferedReader br = new BufferedReader(new FileReader(this.path));
            this.cfg = gson.fromJson(br, Config.class);

            Log.i("cfg", "loaded");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * applies config properties to the given {@link de.rndm.droidFaker.AppPreferences} object
     * @param appPreferences object where all properties are going to be set
     */
    public void applyConfig(AppPreferences appPreferences){
        Log.i("cfg.apply", this.cfg.toString());
        appPreferences.set(AppPreferences.VAL_SEED, cfg.getSeed());
        appPreferences.set(AppPreferences.COUNT_CONTACT, cfg.getContacts());
        appPreferences.set(AppPreferences.COUNT_SMS, cfg.getSms());
        appPreferences.set(AppPreferences.COUNT_CALLS, cfg.getCalls());
        appPreferences.set(AppPreferences.COUNT_BOOKMARKS, cfg.getBookmarks());
        appPreferences.set(AppPreferences.COUNT_HISTORY, cfg.getHistory());
        appPreferences.set(AppPreferences.COUNT_SEARCH, cfg.getSearch());
        appPreferences.set(AppPreferences.COUNT_WIFI, cfg.getWifi());
        appPreferences.set(AppPreferences.COUNT_EMAIL, cfg.getEmail());
        appPreferences.set(AppPreferences.COUNT_WEB, cfg.getWebsites());

        FixtureSingleton.init(cfg.buildFixturesHolder());
        TaskHolderSingleton.init(cfg.buildTaskHolder());
    }
}
