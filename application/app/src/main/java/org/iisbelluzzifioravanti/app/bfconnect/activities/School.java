/**
 * @class School.java
 */
package org.iisbelluzzifioravanti.app.bfconnect.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;

import org.iisbelluzzifioravanti.app.bfconnect.BaseActivity;
import org.iisbelluzzifioravanti.app.bfconnect.R;

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

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_bf_connect_horizontal_white);

        btnTecnico = findViewById(R.id.btnTecnico);//this button is inked to Tecnico page.
        btnProfessionale = findViewById(R.id.btnProfessionale);//this button is inked to Professionale page.

        btnTecnico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(School.this, Tecnico.class);
                ActivityOptions options =
                        ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fadein, R.anim.fadeout);
                startActivity(intent , options.toBundle());
            }
        });
        btnProfessionale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(School.this, Professionale.class);
                ActivityOptions options =
                        ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fadein, R.anim.fadeout);
                startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public int getContentViewId() { return R.layout.activity_school; }

    @Override
    public int getNavigationMenuItemId() { return R.id.school; }
}
