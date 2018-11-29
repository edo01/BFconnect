/**
* BFCONNECT THE NEW APPLICATION POWERED BY EDOARDO CARRÀ, RICCARDO BOVINELLI and FRANCESCO TADDIA.
*
* BFconnect the new system aplication for the ISS BELLUZZI FIORAVANTI'S OPEN DAY.
* This is the main class linked to the "ACTIVITY MAIN" in "res/layout/activity_main.xml"
*/

package org.altervista.edoardo.bfconnect.activities;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;

import android.nfc.Tag;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import org.altervista.edoardo.bfconnect.BaseActivity;
import org.altervista.edoardo.bfconnect.R;
import org.altervista.edoardo.bfconnect.connectionParser.JSONparser;
import org.altervista.edoardo.bfconnect.nfc.NfcAction;

/***
 * toDO: 26/11/18 stopping animation when bottom menu is clicked
 */
public class Home extends BaseActivity {

    private NfcAction nfc;
    private ActionBar toolbar;
    private ImageView image;
    private FloatingActionButton f1;

    @Override
    public void ActivityPage() {
        nfc = new NfcAction();
        image = (ImageView) findViewById(R.id.logo);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.rotation);
        image.startAnimation(animation);
        f1 = (FloatingActionButton) findViewById(R.id.floatQR);
        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent qrcode=new Intent(Home.this, QrCode.class);
                startActivity(qrcode);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        resolveIntent(intent);
    }

    private void resolveIntent(Intent intent) {
            String action = intent.getAction();

            if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                    || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
                    || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
                Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
                NdefMessage[] msgs;

                if (rawMsgs != null) {
                    msgs = new NdefMessage[rawMsgs.length];

                    for (int i = 0; i < rawMsgs.length; i++) {
                        msgs[i] = (NdefMessage) rawMsgs[i];
                    }

                } else {
                    byte[] empty = new byte[0];
                    byte[] id = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
                    Tag tag = (Tag) intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                    byte[] payload = nfc.dumpTagData(tag).getBytes();
                    NdefRecord record = new NdefRecord(NdefRecord.TNF_UNKNOWN, empty, id, payload);
                    NdefMessage msg = new NdefMessage(new NdefRecord[] {record});
                    msgs = new NdefMessage[] {msg};
                }

                Intent room= new Intent(this, Loading.class);
                String txtNfc=nfc.displayMsgs(msgs);
                if(isNetworkAvailable()){
                    Toast.makeText(this, "Nuovo nfc trovato", Toast.LENGTH_LONG).show();
                }else{
                    doSnackbar(txtNfc);
                }
            }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public int getNavigationMenuItemId() {
        return R.id.reading;
    }



    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    private void doSnackbar(String room){
        Snackbar snackbar = Snackbar.make(findViewById(R.id.home), "NO CONNESSIONE", Snackbar.LENGTH_LONG)
                .setAction("RIPROVA", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isNetworkAvailable()) {
                            Intent intent = new Intent(Home.this, Loading.class);
                            intent.putExtra("nfc_read", room);
                        }else doSnackbar(room);
                    }
                });
        snackbar.show();
    }

}
