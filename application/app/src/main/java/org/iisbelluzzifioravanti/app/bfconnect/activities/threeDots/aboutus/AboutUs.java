package org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.aboutus;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.google.android.gms.maps.model.LatLng;

import org.iisbelluzzifioravanti.app.bfconnect.R;
import org.iisbelluzzifioravanti.app.bfconnect.activities.Home;
import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.helpactivity.HNFCuno;
import org.iisbelluzzifioravanti.app.bfconnect.util.MapsActivity;

public class AboutUs extends AppCompatActivity {

    private final LatLng CENTER_POINT=new LatLng(41.91, 12.40);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_about_us);
        //setting mytoolbar as toolbar of the activity
        Toolbar t1 = findViewById(R.id.mytoolbar);
        setSupportActionBar(t1);

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