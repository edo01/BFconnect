/**
 * @class Prenotation.java
 **/
package org.iisbelluzzifioravanti.app.bfconnect.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;

import org.iisbelluzzifioravanti.app.bfconnect.BaseActivity;
import org.iisbelluzzifioravanti.app.bfconnect.R;

/**
 * This is the main class linked to the "activity_prenotation.xml" in "res/layout/activity_prenotation.xml".
 * the activity which takes care about the client prenotations.
 *
 * This is one of the three main pages.
 *
 * @extends BaseActivity
 */
public class Prenotation extends BaseActivity {
    private Button f1,f2;

    /*
    * the link to the form of the first open day(15/12/18) is: s"https://docs.google.com/forms/d/e/1FAIpQLSc6oP6CHRBADXfERIzI8DDKjcV087KvWHsmSQdrJN3N2wj3GQ/viewform"
     * the link to the form of the second open day(19/1/19) is: "https://docs.google.com/forms/d/e/1FAIpQLSe_MQDuYjkzygCHnvbtcYisnL_hg3UAX4BcDorsfKDmHzxChg/viewform"
    */
    @Override
    public void activityPage() {

        f1 = (Button) findViewById(R.id.btnDic); // this one is linked to the first open  day
        f2 = (Button) findViewById(R.id.btnGen); // this to the second
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
