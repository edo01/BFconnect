package org.iisbelluzzifioravanti.app.bfconnect.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbTools {

    private Context context;
    private SQLiteDatabase db;
    private DbHandler dbHandler;
    private boolean isReadable;

    public DbTools(Context context){
        dbHandler = new DbHandler(context);
        db = dbHandler.getReadableDatabase();
        this.context = context;
        isReadable = false;
    }

    public void setReadable(){
        if(!isReadable)db.close();
        db = dbHandler.getReadableDatabase();
        isReadable = true;
    }

    public void setWriteable(){
        if(isReadable)db.close();
        db = dbHandler.getWritableDatabase();
        isReadable = false;
    }

    public void close(){
        db.close();
        dbHandler.close();
    }

    public  boolean roomExists(String title){
        String selection = DbBaseColumns.KEY_TITLE + " = ?";
        String[] selectionArgs = { title };
        setReadable();

        Cursor cursor = db.query(
                DbBaseColumns.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        return cursor.move(1);
    }

    public Cursor getCursor(){

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        /*
        String[] projection = {
                BaseColumns._ID,
                DbBaseColumns.KEY_TITLE,
                DbBaseColumns.KEY_CONTENT,
                DbBaseColumns.KEY_IMAGE
        };*/

        // Filter results WHERE "title" = 'My Title'
    /*
        String selection = DbBaseColumns.KEY_TITLE + " = ?";
        String[] selectionArgs = { "ITIS BELLUZZI" };
    */
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
        return cursor;
    }

    public void insert(ContentValues values) {
        db.insert(DbBaseColumns.TABLE_NAME, null, values);
    }
}
