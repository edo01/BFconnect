package org.iisbelluzzifioravanti.app.bfconnect.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import org.iisbelluzzifioravanti.app.bfconnect.BaseActivity;
import org.iisbelluzzifioravanti.app.bfconnect.R;
import org.iisbelluzzifioravanti.app.bfconnect.database.DbBaseColumns;
import org.iisbelluzzifioravanti.app.bfconnect.database.DbTools;
import org.iisbelluzzifioravanti.app.bfconnect.util.MyAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class MyRooms extends BaseActivity {

    private String[] rooms, info, chimica, meccanica, elettronica, mappe;
    private CardView informatica;
    MyAdapter listAdapter;
    ExpandableListView expListView;
    List<Bitmap> listDataHeader;
    HashMap<Bitmap, List<String>> listDataChild;

    @Override
    public void activityPage() {

        DbTools dbHandler = new DbTools(this);
        dbHandler.setReadable();
        Cursor cursor = dbHandler.getCursor();
//STARTTTTT
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.expandableListView);

        // preparing list data
        prepareListData();

        listAdapter = new MyAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
//ENDDDDD
        /*informatica = findViewById(R.id.cardViewProgettiTecnico);
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

            //commenta
            String content = cursor.getString(cursor.getColumnIndexOrThrow(DbBaseColumns.KEY_CONTENT));
            byte[] byteArray = cursor.getBlob(cursor.getColumnIndexOrThrow(DbBaseColumns.KEY_IMAGE));
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            //commenta

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
        */
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

    private void prepareListData() {
        listDataHeader = new ArrayList<Bitmap>();
        listDataChild = new HashMap<Bitmap, List<String>>();

        // Adding child data

        listDataHeader.add(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.informatica_banner));
        listDataHeader.add(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.chimica_banner));
        listDataHeader.add(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.meccanica_banner));
        listDataHeader.add(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.elettronica_banner));
        listDataHeader.add(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.mappe_banner));

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
        listDataChild.put(listDataHeader.get(3), comingSoon);
        listDataChild.put(listDataHeader.get(4), comingSoon);
    }


    @Override
    public int getContentViewId() {
        return R.layout.activity_my_rooms;
    }

    @Override
    public int getNavigationMenuItemId() {
        return R.id.prenotation;
    }

}


