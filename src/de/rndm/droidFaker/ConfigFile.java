package de.rndm.droidFaker;

import android.util.Log;
import com.google.gson.Gson;
import de.rndm.droidFaker.generators.web.WebGenerator;
import de.rndm.droidFaker.model.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ConfigFile {
    private String path;
    private Config cfg;

    public ConfigFile(String path) {
        this.path = path;
    }

    public void load(String path) {
        this.path = path;
        this.load();
    }

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

    public void apply(AppPreferences appPreferences){
        Log.i("cfg.apply", this.cfg.toString());
        appPreferences.set(ContactSettings.PREF_COUNT, cfg.getContacts());
        appPreferences.set(SmsSettings.PREF_COUNT, cfg.getSms());
        appPreferences.set(CallsSettings.PREF_COUNT, cfg.getCalls());
        appPreferences.set(BookmarkSettings.PREF_COUNT, cfg.getBookmarks());
        appPreferences.set(HistorySettings.PREF_COUNT, cfg.getHistory());
        appPreferences.set(SearchSettings.PREF_COUNT, cfg.getSearch());
        appPreferences.set(WifiSettings.PREF_COUNT, cfg.getWifi());
        appPreferences.set(WebGenerator.PREF_COUNT, cfg.getWebsites());
        appPreferences.set("seed", cfg.getSeed());
    }
}
