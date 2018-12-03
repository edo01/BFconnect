/**
 * @class QrCode.java
 */
package org.iisbelluzzifioravanti.app.bfconnect.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
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
                if (!editText.getText().toString().equals("")) {
                    Intent in = new Intent(QrCode.this, Loading.class);
                    in.putExtra("nfc_read", editText.getText().toString());
                    startActivity(in);
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

}
