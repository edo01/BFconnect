/**
 * the activity which takes care about the client prenotation.
 **/
package org.altervista.edoardo.bfconnect;

import android.content.Intent;
import android.net.Uri;
import android.widget.CalendarView;

import java.util.Calendar;

public class Prenotation extends BaseActivity {
    private CalendarView calendarView;

    private long setOpenDay(String date){
        String parts[] = date.split("/");

        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        return  calendar.getTimeInMillis();
    }

    @Override
    void startThread() {
        calendarView = (CalendarView) findViewById(R.id.calendarView); // get the reference of CalendarView
        String date = "16/11/2018";
        calendarView.setDate (setOpenDay(date), true, true);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                if(month==11 && dayOfMonth==16){
                    Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://goo.gl/forms/7NvZWXkBmSJQpb1E2"));
                    startActivity(viewIntent);
                }
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
