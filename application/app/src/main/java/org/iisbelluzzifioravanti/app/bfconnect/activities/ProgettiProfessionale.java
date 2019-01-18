package org.iisbelluzzifioravanti.app.bfconnect.activities;

import android.content.Intent;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import org.iisbelluzzifioravanti.app.bfconnect.R;
import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.aboutus.AboutUs;
import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.helpactivity.HNFCuno;
import org.iisbelluzzifioravanti.app.bfconnect.connection.PdfHandler;
import org.iisbelluzzifioravanti.app.bfconnect.util.ActivityTools;

public class ProgettiProfessionale extends AppCompatActivity {

    private CardView toyota, magneti, opus, texa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_progetti_professionale);

        //setting mytoolbar as toolbar of the activity
        Toolbar t1 = findViewById(R.id.mytoolbar);
        setSupportActionBar(t1);
        //with this we can open the pdf
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        //getting the button of the specialization

        toyota = findViewById(R.id.cwToyota);
        opus = findViewById(R.id.cwOpusProf);
        texa = findViewById(R.id.cwTexaProf);
        magneti = findViewById(R.id.cwMagneti);

        //setting the onClickListener of the button, if the network isn't available it shows the snackbar
        try{

            opus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ActivityTools.isNetworkAvailable(ProgettiProfessionale.this)) {
                        startDownload("opus");
                    }else doSnackbar("opus");
                }
            });
            toyota.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ActivityTools.isNetworkAvailable(ProgettiProfessionale.this)){
                        startDownload("toyota");
                    }else doSnackbar("toyota");
                }
            });
            texa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ActivityTools.isNetworkAvailable(ProgettiProfessionale.this)) {
                        startDownload("texa");
                    }else doSnackbar("texa");
                }
            });
            magneti.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ActivityTools.isNetworkAvailable(ProgettiProfessionale.this)) {
                        startDownload("magneti");
                    }else doSnackbar("magneti");
                }
            });

        }catch(Exception ex){
            Toast.makeText(this, "Please give your permission.", Toast.LENGTH_LONG).show();
        }
    }

    private void doSnackbar(String pdf){
        Snackbar snackbar = Snackbar.make(findViewById(R.id.AprogettiProfessionale), "NO CONNESSIONE", Snackbar.LENGTH_LONG)
                .setAction("RIPROVA", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(ActivityTools.isNetworkAvailable(ProgettiProfessionale.this)) {
                            startDownload(pdf);
                        }else doSnackbar(pdf);
                    }
                });
        snackbar.show();
    }

    //starting download the pdf
    private void startDownload(String pdf){
        new PdfHandler(pdf, ProgettiProfessionale.this).execute();
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
            case R.id.about_us:
                startActivity(new Intent(this, AboutUs.class));
                return true;
            case R.id.help:
                startActivity(new Intent(this, HNFCuno.class));
                return true;
            case R.id.work_school:
                if(ActivityTools.isNetworkAvailable(this)) {
                    new PdfHandler("work_school",this).execute();
                }else doSnackbar("work_school");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
