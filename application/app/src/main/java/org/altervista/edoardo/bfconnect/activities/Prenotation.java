/**
 * the activity which takes care about the client prenotation.
 **/
package org.altervista.edoardo.bfconnect.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.CalendarView;

import org.altervista.edoardo.bfconnect.BaseActivity;
import org.altervista.edoardo.bfconnect.R;

import java.nio.channels.FileLock;
import java.util.Calendar;

public class Prenotation extends BaseActivity {
    private FloatingActionButton f1,f2;

    @Override
    public void ActivityPage() {
        f1 = (FloatingActionButton) findViewById(R.id.fbtnFirst); // get the reference of CalendarView
        f2 = (FloatingActionButton) findViewById(R.id.fbtnSecond);
        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW",
                        Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSc6oP6CHRBADXfERIzI8DDKjcV087KvWHsmSQdrJN3N2wj3GQ/viewform"));
                startActivity(viewIntent);
            }
        });
        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW",
                        Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSe_MQDuYjkzygCHnvbtcYisnL_hg3UAX4BcDorsfKDmHzxChg/viewform"));
                startActivity(viewIntent);
            }
        });
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_prenotation;
    }

    @Override
    public int getNavigationMenuItemId() {
        return R.id.prenotation;
    }
}
