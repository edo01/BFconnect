package org.altervista.edoardo.bfconnect.activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import org.altervista.edoardo.bfconnect.BaseActivity;
import org.altervista.edoardo.bfconnect.R;

public class School extends BaseActivity {

    private Button btnTecnico, btnProfessionale;

    @Override
    public void ActivityPage() {
        btnTecnico = findViewById(R.id.tecnico);
        btnProfessionale = findViewById(R.id.proff);
        btnTecnico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent(School.this, Tecnico.class);
                startActivity(viewIntent);
            }
        });
        btnProfessionale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent(School.this, Professionale.class);
                startActivity(viewIntent);
            }
        });
    }

    @Override
    public int getContentViewId() { return R.layout.activity_school; }

    @Override
    public int getNavigationMenuItemId() { return R.id.school; }
}
