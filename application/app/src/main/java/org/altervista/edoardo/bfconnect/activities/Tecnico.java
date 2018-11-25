package org.altervista.edoardo.bfconnect.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.altervista.edoardo.bfconnect.R;
import org.altervista.edoardo.bfconnect.connectionParser.PdfHandler;

public class Tecnico extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecnico);
        try{
            if(isNetworkAvailable()){
                CharSequence text = "downloading pdf...";
                int duration = Toast.LENGTH_SHORT;
                Toast.makeText(this, text, duration).show();
                new PdfHandler().execute();
            }else{
                Toast.makeText(this, "NO CONNESSIONE", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, Home.class));
            }
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
