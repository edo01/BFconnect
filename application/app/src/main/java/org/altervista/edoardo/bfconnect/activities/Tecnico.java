package org.altervista.edoardo.bfconnect.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.altervista.edoardo.bfconnect.R;
import org.altervista.edoardo.bfconnect.connectionParser.PdfHandler;

public class Tecnico extends AppCompatActivity {

    private Button btnElettronica, btnChimica, btnInformatica, btnMeccanica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecnico);
        btnElettronica = findViewById(R.id.btnElettronica);
        btnInformatica = findViewById(R.id.btnInfo);
        btnChimica = findViewById(R.id.btnChimica);
        btnMeccanica = findViewById(R.id.btnMeccanica);
        try{
            if(!isNetworkAvailable()){
                Toast.makeText(this, "NO CONNESSIONE", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, Home.class));
            }

        btnElettronica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence text = "downloading pdf...";
                int duration = Toast.LENGTH_SHORT;
                Toast.makeText(Tecnico.this, text, duration).show();
                new PdfHandler("elettronica").execute();
            }
        });
        btnInformatica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence text = "downloading pdf...";
                int duration = Toast.LENGTH_SHORT;
                Toast.makeText(Tecnico.this, text, duration).show();
                new PdfHandler("informatica").execute();
            }
        });
        btnMeccanica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence text = "downloading pdf...";
                int duration = Toast.LENGTH_SHORT;
                Toast.makeText(Tecnico.this, text, duration).show();
                new PdfHandler("meccanica").execute();
            }
        });
        btnChimica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence text = "downloading pdf...";
                int duration = Toast.LENGTH_SHORT;
                Toast.makeText(Tecnico.this, text, duration).show();
                new PdfHandler("chimica").execute();
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

}
