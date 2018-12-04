package org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.helpActivity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import org.iisbelluzzifioravanti.app.bfconnect.R;
import org.iisbelluzzifioravanti.app.bfconnect.activities.Home;

public class HQRdue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_hqrdue);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_bf_connect_horizontal_white);


        FloatingActionButton fprev = (FloatingActionButton) findViewById(R.id.fltPrevH2);
        fprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent qrcode=new Intent(getApplicationContext(), HQRuno.class);
                startActivity(qrcode);
            }
        });

        FloatingActionButton fnext = (FloatingActionButton) findViewById(R.id.fltNextH2);
        fnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent qrcode = new Intent(getApplicationContext(), HNFCuno.class);
                startActivity(qrcode);
            }
        });

        FloatingActionButton fhome = (FloatingActionButton) findViewById(R.id.fltMenuH2);
        fhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent qrcode = new Intent(getApplicationContext(), Help.class);
                startActivity(qrcode);
            }
        });
    }
}
