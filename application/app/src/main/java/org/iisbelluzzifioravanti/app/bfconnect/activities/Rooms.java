package org.iisbelluzzifioravanti.app.bfconnect.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import org.iisbelluzzifioravanti.app.bfconnect.R;
import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.aboutus.AboutUs;
import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.helpactivity.HNFCuno;
import org.iisbelluzzifioravanti.app.bfconnect.database.DbBaseColumns;
import org.iisbelluzzifioravanti.app.bfconnect.database.DbTools;

public class Rooms extends AppCompatActivity {
    public static TextView content, title;
    public static ImageView[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_rooms);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*
        title =findViewById(R.id.txtTitle);
        images = new ImageView[6];

        content = (TextView)findViewById(R.id.txtResponse);
        images[0] = (ImageView) findViewById(R.id.imageOne);
        images[1] = (ImageView) findViewById(R.id.imageTwo);
        images[2] = (ImageView) findViewById(R.id.imageThree);
        images[3] = (ImageView) findViewById(R.id.imageFour);
        images[4] = (ImageView) findViewById(R.id.imageFive);
        images[5] = (ImageView) findViewById(R.id.imageSix);

        try {
            Bundle datapassed = getIntent().getExtras();
            DbTools dbHandler = new DbTools(getApplicationContext());
            Cursor cursor = dbHandler.getCursorLineById(datapassed.getString("id"));

            if(!cursor.move(1)) return;

            byte[][] byteArray= new byte[6][];
            //getting the content of the room
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DbBaseColumns.KEY_TITLE));
            String content = cursor.getString(cursor.getColumnIndexOrThrow(DbBaseColumns.KEY_CONTENT));
            byteArray[0] = cursor.getBlob(cursor.getColumnIndexOrThrow(DbBaseColumns.KEY_IMAGE));
            byteArray[1] = cursor.getBlob(cursor.getColumnIndexOrThrow(DbBaseColumns.KEY_IMAGE2));
            byteArray[2]= cursor.getBlob(cursor.getColumnIndexOrThrow(DbBaseColumns.KEY_IMAGE3));
            byteArray[3] = cursor.getBlob(cursor.getColumnIndexOrThrow(DbBaseColumns.KEY_IMAGE4));
            byteArray[4] = cursor.getBlob(cursor.getColumnIndexOrThrow(DbBaseColumns.KEY_IMAGE5));
            byteArray[5] = cursor.getBlob(cursor.getColumnIndexOrThrow(DbBaseColumns.KEY_IMAGE6));
            this.content.setText(content);
            this.title.setText(title.toUpperCase());
            for (int i=0 ;i<images.length;i++) {
                try{
                    Bitmap bmp = BitmapFactory.decodeByteArray(byteArray[i], 0, byteArray[i].length);
                    images[i].setImageBitmap(bmp);
                }catch (Exception ex){
                    Log.d("immagine nulla","immagine nulla");
                    images[i].setVisibility(View.INVISIBLE);
                    images[i].setImageResource(0);
                }

            }
        }catch(Exception ex){
            Log.e("ERROR",ex.getMessage());
        }*/

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

    @Override
    protected void onRestart() {
        super.onRestart();
        startActivity(new Intent(this, Home.class));
    }
}