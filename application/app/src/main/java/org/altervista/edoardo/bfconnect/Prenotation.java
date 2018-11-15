/**
 * the activity which takes care about the client prenotation.
 **/
package org.altervista.edoardo.bfconnect;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.CalendarView;

import java.nio.channels.FileLock;
import java.util.Calendar;

public class Prenotation extends BaseActivity {
    private FloatingActionButton f1,f2;

    @Override
    void ActivityPage() {
        f1 = (FloatingActionButton) findViewById(R.id.fbtnFirst); // get the reference of CalendarView
        f2 = (FloatingActionButton) findViewById(R.id.fbtnSecond);
        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://goo.gl/forms/7NvZWXkBmSJQpb1E2"));
                startActivity(viewIntent);
            }
        });
        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://goo.gl/forms/7NvZWXkBmSJQpb1E2"));
                startActivity(viewIntent);
            }
        });
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
