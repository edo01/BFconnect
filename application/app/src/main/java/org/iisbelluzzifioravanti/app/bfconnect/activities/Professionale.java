/**
 * @class Professionale.java
 */
package org.iisbelluzzifioravanti.app.bfconnect.activities;

import android.content.Intent;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import org.iisbelluzzifioravanti.app.bfconnect.R;
import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.myrooms.MyRooms;
import org.iisbelluzzifioravanti.app.bfconnect.connection.PdfHandler;
import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.helpactivity.Help;
import org.iisbelluzzifioravanti.app.bfconnect.util.ActivityTools;

/**
 * In this class we take all the specializations of the Professionale
 */
public class Professionale extends AppCompatActivity {

    private Button btnQuaReg, btnApparati, btnMezzi, btnManutenzione,
            btnSeraliMezzi, btnSeraleApparati;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_professionale);

        //with this we can open the pdf
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_bf_connect_horizontal_white);

        //getting the button of the specialization
        btnQuaReg = findViewById(R.id.btnQuaReg);
        btnMezzi = findViewById(R.id.btnMezzi);
        btnApparati = findViewById(R.id.btnApparati);
        btnManutenzione = findViewById(R.id.btnManutenzione);
        btnSeraleApparati = findViewById(R.id.btnSeraleApparati);
        btnSeraliMezzi = findViewById(R.id.btnSeraliMezzi);

        //setting the onClickListener of the button, if the network isn't available it shows the snackbar
        try{

            btnQuaReg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ActivityTools.isNetworkAvailable(Professionale.this)) {
                        startDownload("qualifiche");
                    }else doSnackbar("qualifiche");
                }
            });
            btnMezzi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ActivityTools.isNetworkAvailable(Professionale.this)) {
                        startDownload("mezzi");
                    }else doSnackbar("mezzi");
                }
            });
            btnManutenzione.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ActivityTools.isNetworkAvailable(Professionale.this)) {
                        startDownload("manutenzione");
                    }else doSnackbar("manutenzione");
                }
            });
            btnApparati.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ActivityTools.isNetworkAvailable(Professionale.this)){
                        startDownload("apparati");
                    }else doSnackbar("apparati");
                }
            });
            btnSeraleApparati.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ActivityTools.isNetworkAvailable(Professionale.this)) {
                        startDownload("serialiApparati");
                    }else doSnackbar("seraliApparati");
                }
            });
            btnSeraliMezzi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ActivityTools.isNetworkAvailable(Professionale.this)) {
                        startDownload("serialiMezzi");
                    }else doSnackbar("seraliMezzi");
                }
            });
        }catch(Exception ex){
            Toast.makeText(this, "Please give your permission.", Toast.LENGTH_LONG).show();
        }
    }

    private void doSnackbar(String pdf){
        Snackbar snackbar = Snackbar.make(findViewById(R.id.Aprofessionale), "NO CONNESSIONE", Snackbar.LENGTH_LONG)
                .setAction("RIPROVA", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(ActivityTools.isNetworkAvailable(Professionale.this)) {
                            startDownload(pdf);
                        }else doSnackbar(pdf);
                    }
                });
        snackbar.show();
    }

    //starting download the pdf
    private void startDownload(String pdf){
        new PdfHandler(pdf, Professionale.this).execute();
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.three_dots,menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.myrooms:
                startActivity(new Intent(this, MyRooms.class));
                return true;
            case R.id.about_us:
                startActivity(new Intent(this, Home.class));
                return true;
            case R.id.help:
                startActivity(new Intent(this, Help.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
