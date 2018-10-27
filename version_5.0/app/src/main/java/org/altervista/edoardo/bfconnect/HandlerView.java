/*
* HandlerView extends MainActivity.
* The class which handls the interface of the main application.
*/
package org.altervista.edoardo.bfconnect;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

class HandlerView extends MainActivity{

    TextView text;

    HandlerView(BottomNavigationView bnv){
        final BottomNavigationView navigation = bnv;
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        	@Override
        	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
           		switch (item.getItemId()) {
               			case R.id.reading:
                            item.setIcon(ContextCompat.getDrawable(HandlerView.super.getContext(), R.drawable.logo));
                            return true;
               			case R.id.school:
                            item.setIcon(ContextCompat.getDrawable(HandlerView.super.getContext(), R.drawable.ic_school));
                            return true;
               			case R.id.prenotation:
                            item.setIcon(ContextCompat.getDrawable(HandlerView.super.getContext(), R.drawable.ic_prenotation));
                            return true;
           		}
           		return false;
        	}
        });
    }
   


}
