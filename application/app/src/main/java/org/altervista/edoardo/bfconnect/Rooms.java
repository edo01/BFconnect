package org.altervista.edoardo.bfconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class Rooms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_rooms);
        Bundle datapassed=getIntent().getExtras();
        String nfcRead=datapassed.getString("nfc_read");
        TextView txtView = (TextView)findViewById(R.id.txtResponse);
        txtView.setText(nfcRead);
    }
}