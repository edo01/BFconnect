package org.altervista.edoardo.bfconnect.activities.threeDots;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import org.altervista.edoardo.bfconnect.R;
import org.altervista.edoardo.bfconnect.database.DbHandler;
import org.altervista.edoardo.bfconnect.database.DbBaseColumns;

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
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
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
        //cursor.getBlob();
        cursor.moveToFirst();
        String s = cursor.getString(cursor.getColumnIndexOrThrow(DbBaseColumns.KEY_TITLE));
        cursor.close();




        Toast tdonwload = Toast.makeText(this, s , Toast.LENGTH_LONG);
        tdonwload.setGravity(Gravity.CENTER,0,0);
        tdonwload.show();
    }
}
