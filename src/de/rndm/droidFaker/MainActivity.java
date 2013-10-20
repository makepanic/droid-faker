package de.rndm.droidFaker;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ViewAnimator;
import de.rndm.droidFaker.generators.contact.ContactGenerator;

import java.util.Date;
import java.util.Random;

public class MainActivity extends Activity {

    private ImageButton buttonGenerateContact;
    private ImageButton buttonResetContact;
    private ImageButton buttonContactSettings;
    private EditText seedText;
    private AppPreferences appPreferences;
    private ViewAnimator viewAnimator;

    private long seed = 0;

    private ContactGenerator cg;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        appPreferences = new AppPreferences(this);

        cg = new ContactGenerator(getContentResolver());

        Date seedDate = new Date();
        seedText = (EditText) findViewById(R.id.seed);
        seedText.setText("" + seedDate.getTime());

        viewAnimator = (ViewAnimator) findViewById(R.id.viewAnimator);
        final Animation inAnim = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        final Animation outAnim = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);

        viewAnimator.setInAnimation(inAnim);
        viewAnimator.setOutAnimation(outAnim);

        buttonResetContact = (ImageButton) findViewById(R.id.buttonResetContacts);
        buttonResetContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<Void, Void, Void>(){
                    @Override
                    protected void onPreExecute() {
                        viewAnimator.showNext();
                    }
                    @Override
                    protected Void doInBackground(Void... voids) {
                        seed = Long.valueOf(seedText.getText().toString());
                        cg.reset();
                        return null;
                    }
                    @Override
                    protected void onPostExecute(Void aVoid) {
                        viewAnimator.showPrevious();
                    }
                }.execute();
            }
        });

        buttonGenerateContact = (ImageButton) findViewById(R.id.buttonCreateContacts);
        buttonGenerateContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AsyncTask<Void, Void, Void>(){
                    @Override
                    protected void onPreExecute() {
                        viewAnimator.showNext();
                    }
                    @Override
                    protected Void doInBackground(Void... voids) {
                        seed = Long.valueOf(seedText.getText().toString());
                        cg.generate(new Random(seed), appPreferences.getInteger(ContactGenerator.PREF_COUNT, 100));
                        return null;
                    }
                    @Override
                    protected void onPostExecute(Void aVoid) {
                        viewAnimator.showPrevious();
                    }
                }.execute();
            }
        });

        buttonContactSettings = (ImageButton) findViewById(R.id.buttonContactsSettings);
        buttonContactSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(i);
            }
        });
    }
}
