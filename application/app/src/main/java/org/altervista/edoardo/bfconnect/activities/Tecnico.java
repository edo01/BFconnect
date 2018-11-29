package org.altervista.edoardo.bfconnect.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import org.altervista.edoardo.bfconnect.R;
import org.altervista.edoardo.bfconnect.connectionParser.PdfHandler;

/**
 *  toDo: 29/11/18: DOWNLOAD ERROR.
 */

public class Tecnico extends AppCompatActivity {

    private Button btnElettronica, btnChimica, btnInformatica, btnMeccanica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tecnico);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_bf_connect_horizontal_white);

        btnElettronica = findViewById(R.id.btnElettronica);
        btnInformatica = findViewById(R.id.btnInfo);
        btnChimica = findViewById(R.id.btnChimica);
        btnMeccanica = findViewById(R.id.btnMeccanica);
        try{

        btnElettronica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkAvailable()) {
                    doToast("elettronica");
                }else doSnackbar("elettronica");
            }
        });
        btnInformatica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkAvailable()) {
                    doToast("informatica");
                }else doSnackbar("informatica");
            }
        });
        btnMeccanica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkAvailable()) {
                    doToast("meccanica");
                }else doSnackbar("meccanica");
            }
        });
        btnChimica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkAvailable()) {
                    doToast("chimica");
                }else doSnackbar("chimica");
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

    private void doSnackbar(String pdf){
        Snackbar snackbar = Snackbar.make(findViewById(R.id.tecnico), "NO CONNESSIONE", Snackbar.LENGTH_LONG)
                .setAction("RIPROVA", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isNetworkAvailable()) {
                            doToast(pdf);
                        }else doSnackbar(pdf);
                    }
                });
        snackbar.show();
    }

    private void doToast(String pdf){
        Toast tdonwload = Toast.makeText(Tecnico.this, "downloading pdf..." , Toast.LENGTH_SHORT);
        tdonwload.setGravity(Gravity.CENTER,0,0);
        tdonwload.show();
        new PdfHandler(pdf,Tecnico.this).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.three_dots,menu);
        return true;
    }

}
