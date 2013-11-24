package de.rndm.droidFaker.generators.wifi;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.util.Log;
import de.rndm.droidFaker.fixtures.Name;
import de.rndm.droidFaker.fixtures.Text;
import de.rndm.droidFaker.generators.DataGenerator;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: mkp
 * Date: 26.10.13
 * Time: 21:30
 * To change this template use File | Settings | File Templates.
 * @via http://stackoverflow.com/a/4374934
 */
public class WifiSettingsGenerator implements DataGenerator {

    private ContentResolver cr;
    private Context ctx;

    public WifiSettingsGenerator(Context ctx) {
        this.ctx = ctx;
    }

    public void createWepConfig() {
        WifiManager wifi = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
        WifiConfiguration wc = new WifiConfiguration();
        wc.SSID = "\"SSID_NAME\""; //IMP! This should be in Quotes!!
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

        wc.wepKeys[0] = "\"aaabbb1234\""; //This is the WEP Password
        wc.wepTxKeyIndex = 0;

        WifiManager  wifiManag = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
        boolean res1 = wifiManag.setWifiEnabled(true);
        int res = wifi.addNetwork(wc);
        Log.d("WifiPreference", "add Network returned " + res );
        boolean es = wifi.saveConfiguration();
        Log.d("WifiPreference", "saveConfiguration returned " + es );
        boolean b = wifi.enableNetwork(res, true);
        Log.d("WifiPreference", "enableNetwork returned " + b );
    }

    @Override
    public void generate(Random random, int amount) {
        Log.i("SmsGenerator", "generate " + amount);

        for(int i = 0; i < amount; i++) {
            createWepConfig();
        }
    }

    @Override
    public void reset() {

    }
}
