package org.altervista.edoardo.bfconnect.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import org.altervista.edoardo.bfconnect.R;
import org.altervista.edoardo.bfconnect.connectionParser.PdfHandler;

public class Professionale extends AppCompatActivity {

    private Button btnQuaReg, btnApparati, btnMezzi, btnManutenzione,
            btnSeraliMezzi, btnSeraleApparati;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_professionale);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_bf_connect_horizontal_white);

        btnQuaReg = findViewById(R.id.btnQuaReg);
        btnMezzi = findViewById(R.id.btnMezzi);
        btnApparati = findViewById(R.id.btnApparati);
        btnManutenzione = findViewById(R.id.btnManutenzione);
        btnSeraleApparati = findViewById(R.id.btnSeraleApparati);
        btnSeraliMezzi = findViewById(R.id.btnSeraliMezzi);

        try{
            if(!isNetworkAvailable()){
                Toast.makeText(this, "NO CONNESSIONE", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, Home.class));
            }

            btnQuaReg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CharSequence text = "downloading pdf...";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(Professionale.this, text, duration).show();
                    new PdfHandler("qualifiche").execute();
                }
            });
            btnMezzi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CharSequence text = "downloading pdf...";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(Professionale.this, text, duration).show();
                    new PdfHandler("mezzi").execute();
                }
            });
            btnManutenzione.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CharSequence text = "downloading pdf...";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(Professionale.this, text, duration).show();
                    new PdfHandler("manutenzione").execute();
                }
            });
            btnApparati.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CharSequence text = "downloading pdf...";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(Professionale.this, text, duration).show();
                    new PdfHandler("apparati").execute();
                }
            });
            btnSeraleApparati.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CharSequence text = "downloading pdf...";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(Professionale.this, text, duration).show();
                    new PdfHandler("seraliApparati").execute();
                }
            });
            btnSeraliMezzi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CharSequence text = "downloading pdf...";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(Professionale.this, text, duration).show();
                    new PdfHandler("seraliMezzi").execute();
                }
            });
        }catch(Exception ex){
            Toast.makeText(this, "Please give your permission.", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.three_dots,menu);
        return true;
    }

}
