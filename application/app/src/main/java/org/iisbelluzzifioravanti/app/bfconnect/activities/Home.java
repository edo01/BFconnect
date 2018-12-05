/**
 * @class Home.java
*/

package org.iisbelluzzifioravanti.app.bfconnect.activities;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;

import android.nfc.Tag;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import org.iisbelluzzifioravanti.app.bfconnect.BaseActivity;
import org.iisbelluzzifioravanti.app.bfconnect.Tools;
import org.iisbelluzzifioravanti.app.bfconnect.R;
import org.iisbelluzzifioravanti.app.bfconnect.database.DbBaseColumns;
import org.iisbelluzzifioravanti.app.bfconnect.database.DbTools;
import org.iisbelluzzifioravanti.app.bfconnect.nfc.NfcAction;
import org.iisbelluzzifioravanti.app.bfconnect.util.ActivityTools;

/**
 * toDO: 26/11/18 stopping animation when bottom menu is clicked
 *
 * This is the main class linked to the "activity_main.xml" in "res/layout/activity_main.xml".
 * @extends BaseActivity
 *
 * This is one of the three main pages.
 */
public class Home extends BaseActivity {

    private NfcAction nfc;
    private ActionBar toolbar;
    private ImageView image;
    private FloatingActionButton f1;

    @Override
    public void activityPage() {

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);

        nfc = new NfcAction();//create an NfcAction object for maneging the nfc reader
        image = (ImageView) findViewById(R.id.logo);

        //loading rotation animation from "res/anim/rotation.xml"
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.rotation);
        image.startAnimation(animation);

        //this floating button is linked to the page of the qrcode
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
                    NdefMessage msg = new NdefMessage(new NdefRecord[]{record});
                    msgs = new NdefMessage[]{msg};
                }
                //the message read by our nfc reader
                String txtNfc = nfc.displayMsgs(msgs);
                //cleaning the text inside the nfc
                txtNfc = txtNfc.substring(0, 5);
                Log.d("nfc read", txtNfc);
                openRoom(txtNfc);
            }

    }

    private void openRoom(String txtNfc) {
        //checking the id of the nfc
        if (Tools.CheckId(txtNfc)) {
            //opening the db
            DbTools dbHandler = new DbTools(this);
            if (dbHandler.roomExists(txtNfc)) {
                Log.d("aula trovata nel db", "apertura dell'aula");

                Cursor cursor = dbHandler.getCursorLineById(txtNfc);

                if(!cursor.move(1)) return;
                //creating intent
                Intent in = new Intent(Home.this, Rooms.class);
                //getting the content of the room
                String title = cursor.getString(cursor.getColumnIndexOrThrow(DbBaseColumns.KEY_TITLE));
                String content = cursor.getString(cursor.getColumnIndexOrThrow(DbBaseColumns.KEY_CONTENT));
                byte[] byteArray = cursor.getBlob(cursor.getColumnIndexOrThrow(DbBaseColumns.KEY_IMAGE));
                //closing the db
                dbHandler.close();

                //putting the content inside the intent
                in.putExtra("content", content);
                in.putExtra("title", title);
                //compressing the image to pass, if this is too large the application will crash
                in.putExtra("image", byteArray);
                //starting activity
                startActivity(in);
            } else if (ActivityTools.isNetworkAvailable(this)) {
                Log.d("aula non trovata nel db", "salvataggio dell'aula nel db");

                dbHandler.close();
                Toast.makeText(Home.this, "Nuovo nfc trovato", Toast.LENGTH_SHORT).show();

                /* opening the loading page and passing the number of the room read.(if the
                 *  number is different from null
                 */
                if (!txtNfc.equals("")) {
                    Intent in = new Intent(Home.this, Loading.class);
                    in.putExtra("nfc_read", txtNfc);
                    startActivity(in);
                }
            } else {
                //showing snackbar.
                doSnackbar(txtNfc);
            }
        }else{
            Toast.makeText(Home.this, "Nfc non valido!", Toast.LENGTH_SHORT).show();
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


    /**
     * This method creates the snackbar when the connection isn't available.
     * @param room
     */
    private void doSnackbar(String room){
        Snackbar snackbar = Snackbar.make(findViewById(R.id.home), "NO CONNESSIONE", Snackbar.LENGTH_LONG)
                .setAction("RIPROVA", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(ActivityTools.isNetworkAvailable(Home.this)) {
                            Intent intent = new Intent(Home.this, Loading.class);
                            intent.putExtra("nfc_read", room);
                            startActivity(intent);
                        }else doSnackbar(room);
                    }
                });
        snackbar.show();
    }

}
