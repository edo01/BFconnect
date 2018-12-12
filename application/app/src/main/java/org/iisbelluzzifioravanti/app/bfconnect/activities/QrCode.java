/**
 * @class QrCode.java
 */
package org.iisbelluzzifioravanti.app.bfconnect.activities;

import android.app.ActivityOptions;
import android.content.Intent;
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

import org.iisbelluzzifioravanti.app.bfconnect.util.Tools;
import org.iisbelluzzifioravanti.app.bfconnect.R;
import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.helpactivity.HNFCuno;
import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.myrooms.MyRooms;
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
                        Log.d("room found in db","opening the room");

                        Intent in = new Intent(QrCode.this, Rooms.class);

                        //closing the db
                        dbHandler.close();
                        //putting the content inside the intent
                        in.putExtra("id", qtxt);
                        //starting activity
                        ActivityOptions options =
                                ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fadein, R.anim.fadeout);
                        startActivity(in , options.toBundle());

                    }else if(ActivityTools.isNetworkAvailable(QrCode.this)){ //if there isn't in the db we open the loading page and download the content
                        Log.d("room not found in db","creating room in db");
                        //open the page

                        dbHandler.close();
                        Intent in = new Intent(QrCode.this, Loading.class);
                        in.putExtra("id", qtxt);

                        ActivityOptions options =
                                ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fadein, R.anim.fadeout);
                        startActivity(in , options.toBundle());
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
                startActivity(new Intent(this, HNFCuno.class));
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
