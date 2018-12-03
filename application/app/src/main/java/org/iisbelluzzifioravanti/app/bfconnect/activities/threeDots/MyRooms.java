package org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.BaseColumns;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.iisbelluzzifioravanti.app.bfconnect.R;
import org.iisbelluzzifioravanti.app.bfconnect.activities.Home;
import org.iisbelluzzifioravanti.app.bfconnect.activities.Rooms;
import org.iisbelluzzifioravanti.app.bfconnect.database.DbHandler;
import org.iisbelluzzifioravanti.app.bfconnect.database.DbBaseColumns;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MyRooms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_my_rooms);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_bf_connect_horizontal_white);

        DbHandler dbHandler = new DbHandler(this);
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.

        String[] projection = {
                BaseColumns._ID,
                DbBaseColumns.KEY_TITLE,
                DbBaseColumns.KEY_CONTENT,
                DbBaseColumns.KEY_IMAGE
        };

// Filter results WHERE "title" = 'My Title'
        String selection = DbBaseColumns.KEY_TITLE + " = ?";
        String[] selectionArgs = { "ITIS BELLUZZI" };

// How you want the results sorted in the resulting Cursor
       /* String sortOrder =
                DbBaseColumns.KEY_CONTENT + " DESC";*/

        Cursor cursor = db.query(
                DbBaseColumns.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        /*
        //getting image from db
        byte[] byteArray = cursor.getBlob(cursor.getColumnIndexOrThrow(DbBaseColumns.KEY_IMAGE));
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ((ImageView)findViewById(R.id.imageView3)).setImageBitmap(bmp);
        */
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
            String[] rooms = new String[vector.size()];
            for (int i = 0; i < vector.size(); i++) {
                rooms[i] = "" + vector.elementAt(i);
                Log.i("ELEMENT at  :",i+" "+ vector.elementAt(i));
            }

            ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.row, rooms);
            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(listener);

        }catch (Exception ex){
            Log.e("exception", ex.getMessage());
            Toast toast = Toast.makeText(this, "NON HAI ANCORA TROVATO DELLE AULE!!" , Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
        cursor.close();
        db.close();
    }

    private AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                long arg3) {

        }
    };
}


