/**
 * @class Tecnico
 */
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
import android.widget.Button;
import android.widget.Toast;

import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.aboutus.AboutUs;
import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.helpactivity.HNFCuno;
import org.iisbelluzzifioravanti.app.bfconnect.R;
import org.iisbelluzzifioravanti.app.bfconnect.connection.PdfHandler;
import org.iisbelluzzifioravanti.app.bfconnect.util.ActivityTools;

public class Tecnico extends AppCompatActivity {

    private CardView btnElettronica, btnChimica, btnInformatica, btnMeccanica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tecnico);

        //setting mytoolbar as toolbar of the activity
        Toolbar t1 = findViewById(R.id.mytoolbar);
        setSupportActionBar(t1);

        //with this we can open the pdf
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        //getting the specialization button
        btnElettronica = findViewById(R.id.btnElettronica);
        btnInformatica = findViewById(R.id.btnInformatica);
        btnChimica = findViewById(R.id.btnChimica);
        btnMeccanica = findViewById(R.id.btnMeccanica);

        //setting the onClickListener of the button
        try{

        btnElettronica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityTools.isNetworkAvailable(Tecnico.this)) {
                    startDownload("elettronica");
                }else doSnackbar("elettronica");
            }
        });
        btnInformatica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityTools.isNetworkAvailable(Tecnico.this)) {
                    startDownload("informatica");
                }else doSnackbar("informatica");
            }
        });
        btnMeccanica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityTools.isNetworkAvailable(Tecnico.this)) {
                    startDownload("meccanica");
                }else doSnackbar("meccanica");
            }
        });
        btnChimica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityTools.isNetworkAvailable(Tecnico.this)) {
                    startDownload("chimica");
                }else doSnackbar("chimica");
            }
        });
        }catch(Exception ex){
            Toast.makeText(this, "Please give your permission.", Toast.LENGTH_LONG).show();
        }
    }

    private void doSnackbar(String pdf){
        Snackbar snackbar = Snackbar.make(findViewById(R.id.Atecnico), "NO CONNESSIONE", Snackbar.LENGTH_LONG)
                .setAction("RIPROVA", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(ActivityTools.isNetworkAvailable(Tecnico.this)) {
                            startDownload(pdf);
                        }else doSnackbar(pdf);
                    }
                });
        snackbar.show();
    }

    private void startDownload(String pdf){
        new PdfHandler(pdf,Tecnico.this).execute();
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
