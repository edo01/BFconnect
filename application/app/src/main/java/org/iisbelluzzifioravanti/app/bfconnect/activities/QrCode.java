/**
 * @class QrCode.java
 */
package org.iisbelluzzifioravanti.app.bfconnect.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import org.iisbelluzzifioravanti.app.bfconnect.R;
import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.Help;
import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.MyRooms;

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
                if (!qtxt.equals("")) {

                    if(isNetworkAvailable()){
                        Intent in = new Intent(QrCode.this, Loading.class);
                        in.putExtra("nfc_read", qtxt);
                        startActivity(in);

                    }else{
                        //showing snackbar.
                        doSnackbar(qtxt);
                    }
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
     * @return true if the network is available and false if is not
     */
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
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
                        if(isNetworkAvailable()) {
                            Intent intent = new Intent(QrCode.this, Loading.class);
                            intent.putExtra("nfc_read", room);
                            startActivity(intent);
                        }else doSnackbar(room);
                    }
                });
        snackbar.show();
    }

}
