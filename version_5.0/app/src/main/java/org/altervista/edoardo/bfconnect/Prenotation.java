/**
 * the activity which takes care about the client prenotation.
 **/
package org.altervista.edoardo.bfconnect;

public class Prenotation extends BaseActivity {

    @Override
    void startThread() {}

    @Override
    int getContentViewId() {
        return R.layout.activity_prenotation;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.prenotation;
    }
}
