/**
* BFCONNECT THE NEW APPLICATION POWERED BY EDOARDO CARRÀ, RICCARDO BOVINELLI and FRANCESCO TADDIA.
*
* BFconnect the new system aplication for the ISS BELLUZZI FIORAVANTI'S OPEN DAY.
* This is the main class linked to the "ACTIVITY MAIN" in "res/layout/activity_main.xml"
*/

package org.altervista.edoardo.bfconnect;


import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;

import android.nfc.Tag;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;

import com.android.volley.toolbox.Volley;


public class Home extends BaseActivity {

    NfcAction nfc;
    //HandlerView myhw;//the class which manipulate the view
    Connection http;
    ActionBar toolbar;

    @Override
    void startThread() {
        //myhw = new HandlerView((BottomNavigationView) findViewById(R.id.navigation), this);
        http = new Connection("http://192.168.1.71:80", Volley.newRequestQueue(this));
        nfc = new NfcAction(http);

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

                nfc.displayMsgs(msgs);
            }
        }

    @Override
    int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.reading;
    }



}
