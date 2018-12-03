package org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.iisbelluzzifioravanti.app.bfconnect.R;
import org.iisbelluzzifioravanti.app.bfconnect.database.DbBaseColumns;
import org.iisbelluzzifioravanti.app.bfconnect.database.DbTools;

import java.util.Vector;

public class MyRooms extends AppCompatActivity {

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

            String[] rooms;
            if(!vector.isEmpty()) {
                rooms = new String[vector.size()];
                for (int i = 0; i < vector.size(); i++) {
                    rooms[i] = "" + vector.elementAt(i);
                    Log.i("ELEMENT at  :", i + " " + vector.elementAt(i));
                }
            }else rooms = new String[]{"NON HAI ANCORA TROVATO DELLE AULE!!"}; //if the db hasn't anything

            //creating listView
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.row, rooms);
            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
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

        }
    };
}


