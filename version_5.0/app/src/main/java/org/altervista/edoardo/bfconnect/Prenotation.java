/**
 * the activity which takes care about the client prenotation.
 **/
package org.altervista.edoardo.bfconnect;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.Window;
import android.view.WindowManager;

public class Prenotation extends BaseActivity {

    @Override
    void startThread() {

    }

    @Override
    int getContentViewId() {
        return R.layout.activity_prenotation;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.prenotation;
    }
}
