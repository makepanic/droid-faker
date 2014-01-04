package de.rndm.droidFaker.model.tasks;

import android.content.Context;
import de.rndm.droidFaker.generators.history.HistoryGenerator;
import de.rndm.droidFaker.generators.wifi.WifiSettingsGenerator;
import de.rndm.droidFaker.model.ReflectedTask;

import java.util.HashMap;

/**
 * User: rndm
 * Date: 04.01.14
 * Time: 20:36
 */
public class WifiTask extends Task implements ReflectedTask {
    private String ssid;
    private String pass;

    public WifiTask() {
    }

    @Override
    public void init(HashMap<String, Object> map) {
        this.ssid = (String) map.get("ssid");
        this.pass = (String) map.get("pass");
    }

    @Override
    public void run(Context context) {
        WifiSettingsGenerator wifiSettingsGenerator = new WifiSettingsGenerator(context);
        wifiSettingsGenerator.insert(ssid, pass);
    }
}
