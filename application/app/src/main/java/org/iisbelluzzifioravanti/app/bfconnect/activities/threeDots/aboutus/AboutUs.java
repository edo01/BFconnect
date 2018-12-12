package org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.aboutus;

import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import org.iisbelluzzifioravanti.app.bfconnect.R;
import org.iisbelluzzifioravanti.app.bfconnect.activities.Home;
import org.iisbelluzzifioravanti.app.bfconnect.activities.Professionale;
import org.iisbelluzzifioravanti.app.bfconnect.activities.School;
import org.iisbelluzzifioravanti.app.bfconnect.activities.Tecnico;
import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.helpactivity.HNFCuno;
import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.myrooms.MyRooms;
import org.iisbelluzzifioravanti.app.bfconnect.util.MapsActivity;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_about_us);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_bf_connect_horizontal_white);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.map, new MapsActivity() , "map");
        transaction.commit();

        Button btnHome = (Button) findViewById(R.id.btnHomeAu);//this button is inked to Tecnico page.
        Button btnSchool =(Button) findViewById(R.id.btnSchoolSite);//this button is inked to Professionale page.

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutUs.this, Home.class);
                startActivity(intent);
            }
        });
        btnSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW",
                        Uri.parse("http://www.iisbelluzzifioravanti.gov.it/"));
                startActivity(viewIntent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.aboutus_three_dots,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.myrooms:
                startActivity(new Intent(this, MyRooms.class));
                return true;
            case R.id.help:
                startActivity(new Intent(this, HNFCuno.class));
                return true;
            case R.id.toHome:
                startActivity(new Intent(this, Home.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
