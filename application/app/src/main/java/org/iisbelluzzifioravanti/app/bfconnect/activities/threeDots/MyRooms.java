package org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import org.iisbelluzzifioravanti.app.bfconnect.R;
import org.iisbelluzzifioravanti.app.bfconnect.database.DbHandler;
import org.iisbelluzzifioravanti.app.bfconnect.database.DbBaseColumns;

import java.util.ArrayList;
import java.util.List;

public class MyRooms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_my_rooms);
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
        List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(DbBaseColumns._ID));
            itemIds.add(itemId);
        }

        cursor.moveToFirst();
        cursor.moveToLast();

        /*
        //getting image from db
        byte[] byteArray = cursor.getBlob(cursor.getColumnIndexOrThrow(DbBaseColumns.KEY_IMAGE));
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ((ImageView)findViewById(R.id.imageView3)).setImageBitmap(bmp);
        */
        ImageView image = ((ImageView)findViewById(R.id.imageView3));
        String s = "";
        try{
            s = cursor.getString(cursor.getColumnIndexOrThrow(DbBaseColumns.KEY_TITLE));
            byte[] byteArray = cursor.getBlob(cursor.getColumnIndexOrThrow(DbBaseColumns.KEY_IMAGE));
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            image.setImageBitmap(bmp);
            image.setVisibility(View.VISIBLE);
            Toast tdonwload = Toast.makeText(this, s , Toast.LENGTH_LONG);
            tdonwload.setGravity(Gravity.CENTER,0,0);
            tdonwload.show();

        }catch (Exception ex){
            Toast tdonwload = Toast.makeText(this, "NON HAI ANCORA TROVATO DELLE AULE!!" , Toast.LENGTH_LONG);
            tdonwload.setGravity(Gravity.CENTER,0,0);
            tdonwload.show();
        }
        cursor.close();

    }
}
