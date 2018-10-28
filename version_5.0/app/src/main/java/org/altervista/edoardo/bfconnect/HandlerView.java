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
        navigation.getMenu().getItem(0).setIcon(R.drawable.ic_prenotation_before);
        navigation.getMenu().getItem(2).setIcon(R.drawable.ic_school_before);
        navigation.getMenu().getItem(1).setIcon(R.drawable.logo);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        	@Override
        	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
           		switch (item.getItemId()) {
               			case R.id.reading:
               			    navigation.getMenu().getItem(0).setIcon(R.drawable.ic_prenotation_before);
                            navigation.getMenu().getItem(2).setIcon(R.drawable.ic_school_before);
                            item.setIcon(R.drawable.logo);
                            return true;
               			case R.id.school:
                            navigation.getMenu().getItem(1).setIcon(R.drawable.logo_before);
                            navigation.getMenu().getItem(0).setIcon(R.drawable.ic_prenotation_before);
                            item.setIcon(R.drawable.ic_school);
                            return true;
               			case R.id.prenotation:
                            navigation.getMenu().getItem(1).setIcon(R.drawable.logo_before);
                            navigation.getMenu().getItem(2).setIcon(R.drawable.ic_school_before);
                            item.setIcon(R.drawable.ic_prenotation);
               			    return true;
           		}
           		return false;
        	}
        });
    }
   


}
