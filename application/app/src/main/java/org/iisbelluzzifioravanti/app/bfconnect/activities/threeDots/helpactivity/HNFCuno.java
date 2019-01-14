package org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.helpactivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import org.iisbelluzzifioravanti.app.bfconnect.R;
import org.iisbelluzzifioravanti.app.bfconnect.activities.Home;
import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.aboutus.AboutUs;
import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.myrooms.MyRooms;

public class HNFCuno extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_hnfcuno);
//setting mytoolbar as toolbar of the activity
        Toolbar t1 = findViewById(R.id.mytoolbar);
        setSupportActionBar(t1);


        FloatingActionButton fnext = (FloatingActionButton) findViewById(R.id.fltNextQR1);
        fnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent qrcode = new Intent(getApplicationContext(), HNFCdue.class);
                ActivityOptions options =
                        ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fadein, R.anim.fadeout);
                startActivity(qrcode, options.toBundle());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.help_three_dots,menu);
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
                startActivity(new Intent(this, AboutUs.class));
                return true;
            case R.id.toHome:
                startActivity(new Intent(this, Home.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        startActivity(new Intent(this, Home.class));
    }
}
