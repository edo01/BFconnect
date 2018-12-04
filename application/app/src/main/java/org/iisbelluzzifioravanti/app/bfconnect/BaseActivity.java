/**
 * @class BaseActivity.java
 */

package org.iisbelluzzifioravanti.app.bfconnect;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.cardemulation.HostNfcFService;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.helpActivity.*;

import org.iisbelluzzifioravanti.app.bfconnect.activities.Home;
import org.iisbelluzzifioravanti.app.bfconnect.activities.Prenotation;
import org.iisbelluzzifioravanti.app.bfconnect.activities.School;
import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.MyRooms;

/**
 * @// TODO: 15/11/18 : null
 *
 * This is the base activity for a correct navigation bottom menu. The class is implemented by
 * School.java, Prenotation.java and Home.java.
 */
public abstract class BaseActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    protected BottomNavigationView navigationView;
    protected NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(getContentViewId());

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_bf_connect_horizontal_white);

        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        /*if (nfcAdapter == null) { //if your device hasn't the NFC the application will advise you with a toast
            Toast.makeText(this, "No NFC", Toast.LENGTH_SHORT).show();
        }*/
        pendingIntent = PendingIntent.getActivity(this, 0,// creating a new intent that will be used for reading NFC
                new Intent(this, this.getClass())
                        .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        activityPage();
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateNavigationBarState();
    }

    // Remove inter-activity transition to avoid screen tossing on tapping bottom navigation items
    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (nfcAdapter != null) {
            if (!nfcAdapter.isEnabled())
                showNFCSettings();
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId != this.getNavigationMenuItemId()){
            navigationView.postDelayed(() -> {

                if (itemId == R.id.reading) {
                    startActivity(new Intent(this, Home.class));
                } else if (itemId == R.id.prenotation) {
                    startActivity(new Intent(this, Prenotation.class));
                } else if (itemId == R.id.school) {
                    startActivity(new Intent(this, School.class));
                }
                finish();
            }, 310);
        }

        return true;
    }

    private void showNFCSettings() {
        Toast.makeText(this, "È necessario abilitare NFC", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Settings.ACTION_NFC_SETTINGS);
        startActivity(intent);
    }

    private void updateNavigationBarState(){
        int actionId = getNavigationMenuItemId();
        selectBottomNavigationBarItem(actionId);
    }

    void selectBottomNavigationBarItem(int itemId) {
        MenuItem item = navigationView.getMenu().findItem(itemId);
        item.setChecked(true);
    }

    /**
     * find and fix the mneu on the view(three dots on the right of the top)
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.three_dots,menu);
        return true;

    }

    /**
     * the listener of the three dots menu
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.myrooms:
                startActivity(new Intent(this, MyRooms.class));
                return true;
            case R.id.about_us:
                startActivity(new Intent(this, Home.class));
                return true;
            case R.id.help:
                startActivity(new Intent(this, Help.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * implements activityPage() and put the code of your page activity, this method is called at
     * the end of this.onCreate()
    */
    public abstract void activityPage();

    /**
     * This method is called by this.onCreate() and set the activity layout of the son class
     * which implements this class.
     * @return the id of the page content
     */
    public abstract int getContentViewId();

    /**
     * This method is called by this.onCreate() and set the navigation bottom menu of the son class
     * which implements this class.
     *
     * @return the id of the bottom navigation menu.
     */
    public abstract int getNavigationMenuItemId();

}