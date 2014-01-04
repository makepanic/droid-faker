package de.rndm.droidFaker;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * User: rndm
 * Date: 26.10.13
 * Time: 22:44
 */
public class SettingsActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}