/**
 * @class QrCode.java
 */
package org.iisbelluzzifioravanti.app.bfconnect.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import org.iisbelluzzifioravanti.app.bfconnect.Tools;
import org.iisbelluzzifioravanti.app.bfconnect.R;
import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.helpActivity.Help;
import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.MyRooms;
import org.iisbelluzzifioravanti.app.bfconnect.database.DbBaseColumns;
import org.iisbelluzzifioravanti.app.bfconnect.database.DbTools;
import org.iisbelluzzifioravanti.app.bfconnect.util.ActivityTools;

public class QrCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_qr_code);
        EditText editText = findViewById(R.id.etxtQR);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_bf_connect_horizontal_white);

        FloatingActionButton f1 = findViewById(R.id.fbtnAddRoom);
        f1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String qtxt = editText.getText().toString();
                //if the text is corrected
                if (Tools.CheckId(qtxt)) {
                    //open the db
                    DbTools dbHandler = new DbTools(QrCode.this);
                    if (dbHandler.roomExists(qtxt)) {
                        //room found
                        Log.d("room found in db","open the room");

                        //getting the cursor
                        Cursor cursor = dbHandler.getCursorLineById(qtxt);
                        //open the intent
                        Intent in = new Intent(QrCode.this, Rooms.class);

                        if(!cursor.move(1)) return;

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

                    }else if(ActivityTools.isNetworkAvailable(QrCode.this)){ //if there isn't in the db we open the loading page and download the content
                        Log.d("aula non trovata nel db","creazione dell'aula nel db");
                        //open the page
                        Intent in = new Intent(QrCode.this, Loading.class);
                        in.putExtra("nfc_read", qtxt);
                        startActivity(in);
                    }else{
                        //showing snackbar
                        doSnackbar(qtxt);
                    }
                }else{
                    Toast.makeText(QrCode.this, "Codice non valido!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.three_dots,menu);
        return true;
    }

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
     * This method creates the snackbar when the connection isn't available.
     * @param room
     */
    private void doSnackbar(String room){
        Snackbar snackbar = Snackbar.make(findViewById(R.id.Aqrcode), "NO CONNESSIONE", Snackbar.LENGTH_LONG)
                .setAction("RIPROVA", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(ActivityTools.isNetworkAvailable(QrCode.this)) {
                            Intent intent = new Intent(QrCode.this, Loading.class);
                            intent.putExtra("nfc_read", room);
                            startActivity(intent);
                        }else doSnackbar(room);
                    }
                });
        snackbar.show();
    }

}
