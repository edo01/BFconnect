/**
 * @class School.java
 */
package org.iisbelluzzifioravanti.app.bfconnect.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.CardView;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

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
        //for center the image
        ScrollView scr = (ScrollView)findViewById(R.id.scrolling);

        //scr.setBackgroundResource(R.drawable.school);

        //to set scrolling start
        /*Display display = getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);
        scr.post(new Runnable() {
            public void run() {
                scr.scrollTo(0,findViewById(R.id.bfBel).getHeight()-(p.y/2)+findViewById(R.id.navigation).getHeight());
            }
        });*/

        CardView CVTecnico = findViewById(R.id.cardViewIndirizziTecnico);//this button is inked to Tecnico page.
        CardView CVProfessionale = findViewById(R.id.cardViewIndirizziProfessionale);//this button is inked to Professionale page.
        CardView CVIisBelluzzi = findViewById(R.id.cardViewIisBF);
        CardView CVprogettiTecnico = findViewById(R.id.cardViewProgettiTecnico);
        CardView CVprogettiProfessionale = findViewById(R.id.cardViewProgettiProfessionale);

        CVprogettiProfessionale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        CVprogettiTecnico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        CVIisBelluzzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW",
                        Uri.parse("http://www.iisbelluzzifioravanti.gov.it/"));
                startActivity(viewIntent);
            }
        });
        CVTecnico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(School.this, Tecnico.class);
                ActivityOptions options =
                        ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fadein, R.anim.fadeout);
                startActivity(intent , options.toBundle());
            }
        });
        CVProfessionale.setOnClickListener(new View.OnClickListener() {
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
