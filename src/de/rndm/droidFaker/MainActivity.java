package de.rndm.droidFaker;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import de.rndm.droidFaker.generators.ApkInstaller;
import de.rndm.droidFaker.generators.bookmark.BookmarkGenerator;
import de.rndm.droidFaker.generators.calls.CallsGenerator;
import de.rndm.droidFaker.generators.contact.ContactGenerator;
import de.rndm.droidFaker.generators.history.HistoryGenerator;
import de.rndm.droidFaker.generators.search.SearchGenerator;
import de.rndm.droidFaker.generators.sms.SmsGenerator;
import de.rndm.droidFaker.generators.web.WebGenerator;
import de.rndm.droidFaker.generators.wifi.WifiSettingsGenerator;
import de.rndm.droidFaker.listener.ScenarioItemSelectedListener;
import de.rndm.droidFaker.model.*;
import de.rndm.droidFaker.model.Filter;

import java.io.File;
import java.util.*;

public class MainActivity extends Activity {

    private static final String CFG_DIR = "droid-faker";
    private static final String CFG_SCENARIOS = "scenarios";
    private static final String CFG_APKS = "apks";
    private static final int RESULT_SETTINGS = 1;

    // ui elements
    @InjectView(R.id.viewAnimator) ViewAnimator viewAnimator;
    @InjectView(R.id.execTask) TextView execTask;
    @InjectView(R.id.scenarioSpinner) Spinner scenarioSpinner;
    @InjectView(R.id.apkList) ListView apkFilesListView;

    private AppPreferences appPreferences;

    private ContactGenerator contactGenerator;
    private SmsGenerator smsGenerator;
    private CallsGenerator callsGenerator;
    private WifiSettingsGenerator wifiSettingsGenerator;
    private WebGenerator webGenerator;
    private BookmarkGenerator bookmarkGenerator;
    private HistoryGenerator historyGenerator;
    private SearchGenerator searchGenerator;
    private ApkInstaller apkInstaller;

    private String cfgPath;
    private List<String> apkFiles;
    private String apksPathString;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // basic bootstrap
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.inject(this);


        appPreferences = new AppPreferences(this);

        // access external storage
        String extState = Environment.getExternalStorageState();
        if(!extState.equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(getApplicationContext(), "SDCard nicht gemounted", Toast.LENGTH_LONG).show();
        } else {
            File sd = Environment.getExternalStorageDirectory();
            cfgPath = sd.getAbsolutePath() + "/" + CFG_DIR + "/";

            // load apk files
            FilePath apkPath = new FilePath(cfgPath + "/" + CFG_APKS + "/");
            if (apkPath.toFile().exists()) {
                // create apk Files list and add adapter
                apkFiles = Lists.newArrayList(Collections2.filter(Arrays.asList(apkPath.toFile().list()), Filter.isApk));
                apkFilesListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, apkFiles));
            }

            // scenarios path
            final FilePath scenarioPath = new FilePath(cfgPath + "/" + CFG_SCENARIOS + "/");
            if (scenarioPath.toFile().exists()) {
                final List<String> scenarioFiles = Arrays.asList(scenarioPath.toFile().list());

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, scenarioFiles);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                scenarioSpinner.setAdapter(dataAdapter);
                scenarioSpinner.setOnItemSelectedListener(new ScenarioItemSelectedListener(scenarioFiles, scenarioPath, appPreferences));
            } else {
                Toast.makeText(getApplicationContext(), "Keine Config file gefunden " + scenarioPath.toString(), Toast.LENGTH_LONG).show();
            }
        }

        contactGenerator = new ContactGenerator(getContentResolver());
        smsGenerator = new SmsGenerator(getContentResolver());
        callsGenerator = new CallsGenerator(getContentResolver());
        bookmarkGenerator = new BookmarkGenerator(getContentResolver());
        historyGenerator = new HistoryGenerator(getContentResolver());
        searchGenerator = new SearchGenerator(getContentResolver());
        wifiSettingsGenerator = new WifiSettingsGenerator(this);
        apkInstaller = new ApkInstaller(this);
        webGenerator = new WebGenerator(this);

        final Animation inAnim = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        final Animation outAnim = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);

        viewAnimator.setInAnimation(inAnim);
        viewAnimator.setOutAnimation(outAnim);
    }

    @OnClick(R.id.buttonReset)
    void resetData(){
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected void onPreExecute() {
                execTask.setText("Daten werden gelöscht.");
                viewAnimator.showNext();
            }
            @Override
            protected Void doInBackground(Void... voids) {
                contactGenerator.reset();
                smsGenerator.reset();
                callsGenerator.reset();
                bookmarkGenerator.reset();
                historyGenerator.reset();
                searchGenerator.reset();
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Daten erfolgreich gelöscht",
                        Toast.LENGTH_LONG).show();
                viewAnimator.showPrevious();
            }
        }.execute();
    }

    @OnClick(R.id.buttonCreate)
    void createData(){
        new AsyncTask<Void, Void, Random>(){
            @Override
            protected void onPreExecute() {
                execTask.setText("Daten werden erstellt.");
                viewAnimator.showNext();
            }
            @Override
            protected Random doInBackground(Void... voids) {
                Random random = new Random(appPreferences.getInteger(AppPreferences.VAL_SEED));
                contactGenerator.generate(random, appPreferences.getInteger(ContactSettings.PREF_COUNT, 100));
                smsGenerator.generate(random, appPreferences.getInteger(AppPreferences.COUNT_SMS, 100));
                callsGenerator.generate(random, appPreferences.getInteger(AppPreferences.COUNT_CALLS, 100));
                bookmarkGenerator.generate(random, appPreferences.getInteger(AppPreferences.COUNT_BOOKMARKS, 10));
                historyGenerator.generate(random, appPreferences.getInteger(AppPreferences.COUNT_HISTORY, 10));
                searchGenerator.generate(random, appPreferences.getInteger(AppPreferences.COUNT_SEARCH, 10));
                wifiSettingsGenerator.generate(random, appPreferences.getInteger(AppPreferences.COUNT_WIFI, 2));
                webGenerator.generate(random, appPreferences.getInteger(WebGenerator.PREF_COUNT, 10));

                for (String apkFile : apkFiles) {
                    String apkFilePath = apksPathString + apkFile;
                    apkInstaller.installApk(apkFilePath);
                }

                return random;
            }
            @Override
            protected void onPostExecute(final Random random) {
                Toast.makeText(getApplicationContext(), "Daten erfolgreich erstellt", Toast.LENGTH_LONG).show();
                viewAnimator.showPrevious();
            }
        }.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent i = new Intent(this, SettingsActivity.class);
                startActivityForResult(i, RESULT_SETTINGS);
                break;
        }
        return true;
    }
}
