/**
* HandlerView extends MainActivity.
* The class which handls the interface of the main application.
*/
package org.altervista.edoardo.bfconnect;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

class HandlerView extends MainActivity{


    HandlerView(BottomNavigationView bnv, final Context actual){
        final BottomNavigationView navigation = bnv;
        navigation.getMenu().getItem(0).setChecked(false);
        navigation.getMenu().getItem(2).setChecked(false);
        navigation.getMenu().getItem(1).setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        	@Override
        	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
           		switch (item.getItemId()) {
               			case R.id.reading:
                            navigation.getMenu().getItem(0).setChecked(false);
                            navigation.getMenu().getItem(2).setChecked(false);
               			    item.setChecked(true);
                            item.setIntent(new Intent(actual, MainActivity.class));
                            return true;
               			case R.id.school:
                            navigation.getMenu().getItem(1).setChecked(false);
                            navigation.getMenu().getItem(0).setChecked(false);
                            item.setChecked(true);
                            return true;
               			case R.id.prenotation:
                            navigation.getMenu().getItem(1).setChecked(false);
                            navigation.getMenu().getItem(2).setChecked(false);
               			    item.setChecked(true);
                            item.setIntent(new Intent(actual, Prenotation.class));
               			    return true;
           		}
           		return false;
        	}
        });
    }
   


}
