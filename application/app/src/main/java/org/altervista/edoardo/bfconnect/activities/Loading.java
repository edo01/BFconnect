package org.altervista.edoardo.bfconnect.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.altervista.edoardo.bfconnect.R;
import org.altervista.edoardo.bfconnect.activities.Home;
import org.altervista.edoardo.bfconnect.activities.Rooms;
import org.altervista.edoardo.bfconnect.connectionParser.JSONparser;

/**
 * toDO: 26/11/18 passing bitmap image to the other Activity; adding animation;
 */

public class Loading extends AppCompatActivity {

    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle datapassed = getIntent().getExtras();
        String room = datapassed.getString("nfc_read");//getting the room from what nfc read

        setContentView(R.layout.activity_loading);

        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        try {
            new JSONparser(room, this).execute();
        }catch(Exception ex){
            Log.e("ERROR IN LOADING DATA",ex.getMessage());
        }
    }

    void selectBottomNavigationBarItem(int itemId) {
        MenuItem item = navigationView.getMenu().findItem(itemId);
        item.setChecked(true);
    }
}
