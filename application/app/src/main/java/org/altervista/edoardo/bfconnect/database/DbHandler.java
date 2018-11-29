package org.altervista.edoardo.bfconnect.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHandler {

    static final String KEY_RIGAID = "room";
    static final String KEY_TITLE = "title";
    static final String KEY_CONTENT = "content";
    static final String KEY_IMAGE = "image";
    static final String TAG = "GestioneDB";
    static final String DATABASE_NOME = "RoomDB";
    static final String DATABASE_TABELLA = "rooms";
    static final int DATABASE_VERSIONE = 1;

    static final String DATABASE_CREAZIONE1 =
            "CREATE TABLE room (id integer primary key autoincrement, "
                    + "nome text not null, indirizzo text not null);";

    static final String DATABASE_CREAZIONE =
            "CREATE TABLE rooms (room integer primary key autoincrement, "
                    + "photo BLOB, title text not null,content text not null);";

    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DbHandler(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NOME, null, DATABASE_VERSIONE);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DATABASE_CREAZIONE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(DatabaseHelper.class.getName(), "Aggiornamento database dalla versione " + oldVersion + " alla "
                    + newVersion + ". I dati esistenti verranno eliminati.");
            db.execSQL("DROP TABLE IF EXISTS rooms");
            onCreate(db);
        }

    }

    public DbHandler open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }


    public void close() {
        DBHelper.close();
    }


    public long insert(byte[] image, String title, String content) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_CONTENT, content);
        initialValues.put(KEY_IMAGE, image);
        return db.insert(DATABASE_TABELLA, null, initialValues);
    }


    public boolean deleteRoom(long rigaId) {
        return db.delete(DATABASE_TABELLA, KEY_RIGAID + "=" + rigaId, null) > 0;
    }


    public Cursor allRooms() {
        return db.query(DATABASE_TABELLA, new String[]{KEY_IMAGE, KEY_TITLE, KEY_CONTENT}, null, null, null, null, null);
    }

    /* to read using this method:
    Cursor c = db.getRoom();
        if (c.moveToFirst()) {
            do {
                immagine =  c.getString(0)
                title = c.getString(1)
                content = c.getString(2),
            } while (c.moveToNext());
        }
     */
    public Cursor getRoom(long rigaId) throws SQLException {
        Cursor mCursore = db.query(true, DATABASE_TABELLA, new String[]{KEY_IMAGE, KEY_TITLE, KEY_CONTENT, }, KEY_RIGAID + "=" + rigaId, null, null, null, null, null);
        if (mCursore != null) {
            mCursore.moveToFirst();
        }
        return mCursore;
    }


    public boolean aggiornaCliente(long rigaId, String name, String email) {
        ContentValues args = new ContentValues();
        args.put(KEY_TITLE, name);
        args.put(KEY_CONTENT, email);
        return db.update(DATABASE_TABELLA, args, KEY_RIGAID + "=" + rigaId, null) > 0;
    }
}