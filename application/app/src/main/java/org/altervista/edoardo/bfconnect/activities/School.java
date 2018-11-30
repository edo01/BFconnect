/**
 * @class School.java
 */
package org.altervista.edoardo.bfconnect.activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import org.altervista.edoardo.bfconnect.BaseActivity;
import org.altervista.edoardo.bfconnect.R;

/**
 * This class is linked to the "activity_school.xml" in "res/layout/activity_school.xml".
 * @extends BaseActivity
 *
 * This is one of the three main pages.
 */
public class School extends BaseActivity {

    private Button btnTecnico, btnProfessionale;

    @Override
    public void activityPage() {
        btnTecnico = findViewById(R.id.btnTecnico);//this button is inked to Tecnico page.
        btnProfessionale = findViewById(R.id.btnProfessionale);//this button is inked to Professionale page.

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
