package org.iisbelluzzifioravanti.app.bfconnect.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import org.iisbelluzzifioravanti.app.bfconnect.R;
import org.iisbelluzzifioravanti.app.bfconnect.activities.Home;
import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.aboutus.AboutUs;
import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.helpactivity.HNFCuno;

public class Fisica extends AppCompatActivity {

    public static ImageView[] images;
    private static CardView[] cards;
    private boolean isPlaying1 = false, isPlaying2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_fisica);
        Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        ScrollView scr = findViewById(R.id.scrollFisica);
        scr.post(new Runnable() {
            public void run() {
                scr.scrollTo(0,0);
            }
        });
        VideoView video1 = findViewById(R.id.videoView1);
        MediaController media = new MediaController(this);
        video1.setVideoPath("android.resource://"+getPackageName()+"/"+ R.raw.carrello);
        media.setAnchorView(video1);
        video1.setMediaController(media);

        VideoView video2 = findViewById(R.id.videoView2);
        MediaController media2 = new MediaController(this);
        video2.setVideoPath("android.resource://"+getPackageName()+"/"+ R.raw.pallone);
        media2.setAnchorView(video2);
        video2.setMediaController(media2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        video1.start();
                        video2.start();
                    }
                });
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        video1.pause();
                        video2.pause();
                    }
                });
            }
        }).start();
        CardView c1 = findViewById(R.id.cardvideoLink1);
        CardView c2 = findViewById(R.id.cardvideoLink2);
        CardView c3 = findViewById(R.id.cardvideoLink3);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW",
                        Uri.parse("https://www.youtube.com/watch?v=mhIOylZMg6Q"));
                startActivity(viewIntent);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW",
                        Uri.parse("https://www.youtube.com/watch?v=jMiQUStPvNA"));
                startActivity(viewIntent);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW",
                        Uri.parse("https://www.youtube.com/watch?v=UojV7fzQ5qc&list=PLmKUwJ0KJQnXuHJMYU08kXwh8BMKiBqMg"));
                startActivity(viewIntent);
            }
        });
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
