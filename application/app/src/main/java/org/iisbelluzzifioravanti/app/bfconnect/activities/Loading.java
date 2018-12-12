/**
 * @class Loading.java
 */
package org.iisbelluzzifioravanti.app.bfconnect.activities;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import org.iisbelluzzifioravanti.app.bfconnect.R;
import org.iisbelluzzifioravanti.app.bfconnect.connection.JsonParser;

/**
 * toDO: 30/11/18 null
 */

public class Loading extends AppCompatActivity {

    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //getting the number of the room from the previous page
        Bundle datapassed = getIntent().getExtras();
        String room = "";
        room = datapassed.getString("id");//getting the room from what nfc read
        if(room.isEmpty())startActivity(new Intent(this, Home.class));//if the nfc is empty passing on the Home page
        setContentView(R.layout.activity_loading);

        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        //getting the data from the server
        try {
            new JsonParser(room, this).execute();
        }catch(Exception ex){
            Log.e("ERROR IN LOADING DATA",ex.getMessage());
        }
    }

    void selectBottomNavigationBarItem(int itemId) {
        MenuItem item = navigationView.getMenu().findItem(itemId);
        item.setChecked(true);
    }
}
