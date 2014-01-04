package de.rndm.droidFaker.generators.wifi;

import android.content.ContentResolver;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.util.Log;
import de.rndm.droidFaker.model.FixtureSingleton;
import de.rndm.droidFaker.model.FixtureType;
import de.rndm.droidFaker.fixtures.*;
import de.rndm.droidFaker.fixtures.Number;
import de.rndm.droidFaker.generators.DataGenerator;

import java.util.Random;

/**
 * User: rndm
 * Date: 26.10.13
 * Time: 21:30
 * @via http://stackoverflow.com/a/4374934
 */
public class WifiSettingsGenerator implements DataGenerator {

    private ContentResolver cr;
    private Context ctx;

    public WifiSettingsGenerator(Context ctx) {
        this.ctx = ctx;
    }

    public void insert(String ssid, String pass){
        WifiManager wifi = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
        WifiConfiguration wc = new WifiConfiguration();
        wc.SSID = "\"" + ssid + "\""; //IMP! This should be in Quotes!!
        wc.hiddenSSID = true;
        wc.status = WifiConfiguration.Status.DISABLED;
        wc.priority = 40;
        wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        wc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
        wc.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
        wc.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
        wc.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
        wc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        wc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
        wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);

        wc.wepKeys[0] = "\"" + pass + "\""; //This is the WEP Password
        wc.wepTxKeyIndex = 0;

        int res = wifi.addNetwork(wc);
        boolean es = wifi.saveConfiguration();

    }

    @Override
    public void generate(Random random, int amount) {
        Log.i("SmsGenerator", "generate " + amount);
        Fixture ssidFixture = FixtureSingleton.getInstance().getFixture(FixtureType.SSID);

        for(int i = 0; i < amount; i++) {
            insert(
                    ssidFixture.getString(random),
                    Number.getOne(random, 10));
        }
    }

    @Override
    public void reset() {

    }
}
