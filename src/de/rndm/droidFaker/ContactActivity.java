package de.rndm.droidFaker;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

/**
 * Created with IntelliJ IDEA.
 * User: mkp
 * Date: 14.10.13
 * Time: 18:51
 * To change this template use File | Settings | File Templates.
 */
public class ContactActivity extends Activity {

    private static final int MIN_VAL = 1;
    private static final int MAX_VAL = 100;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);

    }
}
