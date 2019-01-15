package org.iisbelluzzifioravanti.app.bfconnect.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import org.iisbelluzzifioravanti.app.bfconnect.BaseActivity;
import org.iisbelluzzifioravanti.app.bfconnect.R;
import org.iisbelluzzifioravanti.app.bfconnect.database.DbBaseColumns;
import org.iisbelluzzifioravanti.app.bfconnect.database.DbTools;

import java.util.Vector;

public class MyRooms extends BaseActivity {

    private String[] rooms;
    private CardView informatica;

    @Override
    public void activityPage() {

        DbTools dbHandler = new DbTools(this);
        dbHandler.setReadable();
        Cursor cursor = dbHandler.getCursor();
        informatica = findViewById(R.id.cardViewProgettiTecnico);
        informatica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
            String id = cursor.getString(cursor.getColumnIndexOrThrow(DbBaseColumns.KEY_ROOMID));
            //getting the content of the room

            //closing the db
            dbHandler.close();
            Intent in = new Intent(getApplicationContext(), Rooms.class);
            //putting the content inside the intent
            in.putExtra("id", id );
            //starting activity
            ActivityOptions options =
                    ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fadein, R.anim.fadeout);
            startActivity(in , options.toBundle());
        }
    };



    @Override
    public int getContentViewId() {
        return R.layout.activity_my_rooms;
    }

    @Override
    public int getNavigationMenuItemId() {
        return R.id.prenotation;
    }

}


