package de.rndm.droidFaker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import de.rndm.droidFaker.model.ContactSettings;

/**
 * Created with IntelliJ IDEA.
 * User: mkp
 * Date: 14.10.13
 * Time: 18:51
 * To change this template use File | Settings | File Templates.
 */
public class ContactActivity extends Activity {

    private ContactSettings contactSettings;
    private ContactSettings updatedContactSettings;

    private AppPreferences appPreferences;
    private Button submitSettings;
    private ImageButton resetSettings;
    private EditText contactCountText;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);

        appPreferences = new AppPreferences(this);

        contactCountText = (EditText) findViewById(R.id.editCount);
        resetSettings = (ImageButton) findViewById(R.id.resetContactSettings);
        submitSettings = (Button) findViewById(R.id.contactSettingsSubmit);

        // create ContactSettings from preferences
        contactSettings = new ContactSettings(appPreferences);
        // create local working copy from created object
        updatedContactSettings = contactSettings.clone();

        // reset button clicked
        resetSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatedContactSettings = contactSettings.clone();
                updateForm(updatedContactSettings);

            }
        });

        // save button clicked
        submitSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.valueOf(contactCountText.getText().toString());
                updatedContactSettings.setCount(count);
                updatedContactSettings.persist(appPreferences);

                finish();
            }
        });

        updateForm(updatedContactSettings);
    }

    /**
     * Updates the form with a given ContactSettings object
     * @param contactSettings Object should update the form
     */
    private void updateForm(ContactSettings contactSettings) {
        contactCountText.setText("" + contactSettings.getCount());

    }
}
