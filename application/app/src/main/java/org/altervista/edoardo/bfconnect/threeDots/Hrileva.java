package org.altervista.edoardo.bfconnect.threeDots;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import org.altervista.edoardo.bfconnect.BaseActivity;
import org.altervista.edoardo.bfconnect.R;

public class Hrileva extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_hrileva);
    }

    @Override
    public void ActivityPage() {

    }

    @Override
    public int getContentViewId() {
        return 0;
    }

    @Override
    public int getNavigationMenuItemId() {
        return 0;
    }
}
