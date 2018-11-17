package org.altervista.edoardo.bfconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Rooms extends AppCompatActivity {
    static TextView txtView, title;
    static ImageView image;
    static String room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_rooms);
        Bundle datapassed = getIntent().getExtras();
        //room = datapassed.getString("nfc_read");//getting the room from what nfc read
        room=""+24;
        title = (TextView)findViewById(R.id.title);
        txtView = (TextView)findViewById(R.id.txtResponse);
        image = (ImageView) findViewById(R.id.imageView2);
        JSONparser js = new JSONparser();
        js.execute();

    }
}
