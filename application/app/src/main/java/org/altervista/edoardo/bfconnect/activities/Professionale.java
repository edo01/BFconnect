package org.altervista.edoardo.bfconnect.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.altervista.edoardo.bfconnect.R;
import org.altervista.edoardo.bfconnect.connectionParser.PdfHandler;

public class Professionale extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R
                .layout.activity_professionale);
        try{
            CharSequence text = "downloading pdf...";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(this, text, duration).show();
            new PdfHandler().execute();
        }catch(Exception ex){
            Toast.makeText(this, "Please give your permission.", Toast.LENGTH_LONG).show();
        }
    }

}
