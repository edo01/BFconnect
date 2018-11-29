package org.altervista.edoardo.bfconnect.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import org.altervista.edoardo.bfconnect.R;


public class Rooms extends AppCompatActivity {
    public static TextView content, title;
    public static ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_rooms);

        title = (TextView)findViewById(R.id.title);
        content = (TextView)findViewById(R.id.txtResponse);
        image = (ImageView) findViewById(R.id.imageOne);

        Bundle datapassed = getIntent().getExtras();
        title.setText(datapassed.getString("title"));
        content.setText(datapassed.getString("content"));
        image.setImageBitmap(getIntent().getExtras().getParcelable("image"));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_bf_connect_horizontal_white);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.three_dots,menu);
        return true;
    }
}
