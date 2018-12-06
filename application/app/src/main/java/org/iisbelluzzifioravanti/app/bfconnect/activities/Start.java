/**
 * @class Start.java
 */
package org.iisbelluzzifioravanti.app.bfconnect.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import org.iisbelluzzifioravanti.app.bfconnect.R;

/**
 * the first activity with the bf connect logo.
 * Splash activity
 */
public class Start extends AppCompatActivity {

    private final int timeout = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) { //if your device hasn't the NFC the application will advise you with a toast
            Toast.makeText(this, "SEMBRA CHE TU NON ABBIA L'NFC SUL TELEFONO, USA IL QR CODE", Toast.LENGTH_LONG).show();
        }

        //starting the main page after 2 seconds of loading
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent in = new Intent(Start.this, Home.class);
                startActivity(in);
                finish();
            }
        }, timeout);
    }
}
