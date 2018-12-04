package org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.helpActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import org.iisbelluzzifioravanti.app.bfconnect.R;

public class HQRuno extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_hqruno);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_bf_connect_horizontal_white);

        FloatingActionButton f1 = (FloatingActionButton) findViewById(R.id.fltMenuH1);
        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent qrcode=new Intent(getApplicationContext(), Help.class);
                startActivity(qrcode);
            }
        });

        FloatingActionButton fnext = (FloatingActionButton) findViewById(R.id.fltNextH1);
        fnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent qrcode = new Intent(getApplicationContext(), HQRdue.class);
                startActivity(qrcode);
            }
        });


    }
}
