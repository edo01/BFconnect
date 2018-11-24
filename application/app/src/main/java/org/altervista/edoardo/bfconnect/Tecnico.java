package org.altervista.edoardo.bfconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Tecnico extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecnico);
    }

    @Override
    void ActivityPage() {

    }

    @Override
    int getContentViewId() {
        return R.layout.activity_tecnico;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.tecnico;
    }
}
