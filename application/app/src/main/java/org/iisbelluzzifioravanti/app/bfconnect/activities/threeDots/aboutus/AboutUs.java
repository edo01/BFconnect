package org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.aboutus;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.iisbelluzzifioravanti.app.bfconnect.R;
import org.iisbelluzzifioravanti.app.bfconnect.activities.Home;
import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.helpactivity.HNFCuno;

public class AboutUs extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap gmap;

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_about_us);

        //setting mytoolbar as toolbar of the activity
        Toolbar t1 = findViewById(R.id.mytoolbar);
        setSupportActionBar(t1);

        CardView siteSchool = findViewById(R.id.cardViewIisBF);
        siteSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW",
                        Uri.parse("http://www.iisbelluzzifioravanti.gov.it/"));
                startActivity(viewIntent);
            }
        });
        CardView siteMaker = findViewById(R.id.cardViewMaker);
        siteMaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW",
                        Uri.parse("https://www.schoolmakerday.it/"));
                startActivity(viewIntent);
            }
        });
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }
        mapView = findViewById(R.id.map);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }
    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.setMinZoomPreference(13);
        gmap.setMaxZoomPreference(16);
        LatLng bel = new LatLng(44.489236, 11.284999599999992);
        gmap.addMarker(new MarkerOptions().position(bel).title("IIS Belluzzi Fioravanti").snippet("Via G. D. Cassini, 3"));
        gmap.moveCamera(CameraUpdateFactory.newLatLng(bel));
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