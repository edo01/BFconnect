package org.altervista.edoardo.bfconnect.activities;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.altervista.edoardo.bfconnect.R;
import org.altervista.edoardo.bfconnect.connectionParser.JSONparser;


public class Rooms extends AppCompatActivity {
    public static TextView txtView, title;
    public static ImageView image;
    public static String room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_rooms);
        Bundle datapassed = getIntent().getExtras();
        room = datapassed.getString("nfc_read");//getting the room from what nfc read
        title = (TextView)findViewById(R.id.title);
        txtView = (TextView)findViewById(R.id.txtResponse);
        image = (ImageView) findViewById(R.id.imageOne);
        if(isNetworkAvailable()){
            new JSONparser().execute();
        }else{
            Toast.makeText(this, "NO CONNESSIONE", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, Home.class));
        }

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
