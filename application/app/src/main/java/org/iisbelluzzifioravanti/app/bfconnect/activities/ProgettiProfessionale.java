package org.iisbelluzzifioravanti.app.bfconnect.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import org.iisbelluzzifioravanti.app.bfconnect.R;

public class ProgettiProfessionale extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_progetti_professionale);

    }

}
