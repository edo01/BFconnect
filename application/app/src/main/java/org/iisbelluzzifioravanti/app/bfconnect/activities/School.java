/**
 * @class School.java
 */
package org.iisbelluzzifioravanti.app.bfconnect.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.VideoView;

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
    private VideoView video;
    private boolean isPlaying = false;

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
                scr.scrollTo(0,scr.getHeight()/2);
            }
        });
*/
        video = findViewById(R.id.videoView);
        //MediaController media = new MediaController(this);
        video.setVideoPath("android.resource://"+getPackageName()+"/"+ R.raw.school);
        //media.setAnchorView(video);
        //video.setMediaController(media);
        video.start();
        isPlaying = true;
        CardView CVTecnico = findViewById(R.id.cardViewIndirizziTecnico);//this button is inked to Tecnico page.
        CardView CVProfessionale = findViewById(R.id.cardViewIndirizziProfessionale);//this button is inked to Professionale page.
        CardView CVIisBelluzzi = findViewById(R.id.cardViewIisBF);
        CardView CVprogettiTecnico = findViewById(R.id.cardViewProgettiTecnico);
        CardView CVprogettiProfessionale = findViewById(R.id.cardViewProgettiProfessionale);

        CVprogettiProfessionale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(School.this, ProgettiProfessionale.class);
                ActivityOptions options =
                        ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fadein, R.anim.fadeout);
                startActivity(intent , options.toBundle());
            }
        });

        CVprogettiTecnico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(School.this, ProgettiTecnico.class);
                ActivityOptions options =
                        ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fadein, R.anim.fadeout);
                startActivity(intent , options.toBundle());
            }
        });

        CVIisBelluzzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPlaying){video.pause();isPlaying=false;}
                else{video.start();isPlaying=true;}
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
    protected void onRestart() {
        super.onRestart();
        video.start();
        isPlaying = true;
    }

    @Override
    public int getContentViewId() { return R.layout.activity_school; }

    @Override
    public int getNavigationMenuItemId() { return R.id.school; }
}
