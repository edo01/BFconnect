package org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots;

import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.iisbelluzzifioravanti.app.bfconnect.R;
import org.iisbelluzzifioravanti.app.bfconnect.activities.Home;
import org.iisbelluzzifioravanti.app.bfconnect.activities.Rooms;
import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.helpActivity.Help;
import org.iisbelluzzifioravanti.app.bfconnect.database.DbBaseColumns;
import org.iisbelluzzifioravanti.app.bfconnect.database.DbTools;
import org.iisbelluzzifioravanti.app.bfconnect.util.MyAdapter;

import java.util.Vector;

public class MyRooms extends AppCompatActivity {

    private String[] rooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_my_rooms);


        DbTools dbHandler = new DbTools(this);
        dbHandler.setReadable();
        Cursor cursor = dbHandler.getCursor();

        try{
            Vector<String> vector = new Vector<>();
            while(cursor.moveToNext()) {
                    vector.add(cursor.getString(cursor.getColumnIndexOrThrow(DbBaseColumns.KEY_TITLE)));
            }

            /*
            String content = cursor.getString(cursor.getColumnIndexOrThrow(DbBaseColumns.KEY_CONTENT));
            byte[] byteArray = cursor.getBlob(cursor.getColumnIndexOrThrow(DbBaseColumns.KEY_IMAGE));
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            */

            if(!vector.isEmpty()) {
                rooms = new String[vector.size()];
                for (int i = 0; i < vector.size(); i++) {
                    rooms[i] = "" + vector.elementAt(i);
                    Log.i("ELEMENT at  :", i + " " + vector.elementAt(i));
                }
            }else rooms = new String[]{"NON HAI ANCORA TROVATO DELLE AULE!!"}; //if the db hasn't anything
            //creating listView
            //the class my adapter customize the row of the list view
            MyAdapter myAdapter = new MyAdapter(this, rooms);
            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(myAdapter);
            listView.setOnItemClickListener(listener);

        }catch (Exception ex){
            Log.e("problem whit the db", ex.getMessage());
            Toast toast = Toast.makeText(this, "NON HAI ANCORA TROVATO DELLE AULE!!" , Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
        cursor.close();
        dbHandler.close();
    }

    private AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                long arg3) {
            DbTools dbHandler = new DbTools(getApplicationContext());
            Cursor cursor = dbHandler.getCursorLineByTitle(rooms[position]);

            if(!cursor.move(1)) return;

            //getting the content of the room
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DbBaseColumns.KEY_TITLE));
            String content = cursor.getString(cursor.getColumnIndexOrThrow(DbBaseColumns.KEY_CONTENT));
            byte[] byteArray = cursor.getBlob(cursor.getColumnIndexOrThrow(DbBaseColumns.KEY_IMAGE));

            //closing the db
            dbHandler.close();
            Intent in = new Intent(getApplicationContext(), Rooms.class);
            //putting the content inside the intent
            in.putExtra("content", content);
            in.putExtra("title", title);
            //compressing the image to pass, if this is too large the application will crash
            in.putExtra("image", byteArray);
            //starting activity
            ActivityOptions options =
                    ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fadein, R.anim.fadeout);
            startActivity(in , options.toBundle());
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.myrooms_three_dots,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.help:
                startActivity(new Intent(this, Help.class));
                return true;
            case R.id.about_us:
                startActivity(new Intent(this, Home.class));
                return true;
            case R.id.toHome:
                startActivity(new Intent(this, Home.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


