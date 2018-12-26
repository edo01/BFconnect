/**
 * @class QrCode.java
 */
package org.iisbelluzzifioravanti.app.bfconnect.activities;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import org.iisbelluzzifioravanti.app.bfconnect.util.Tools;
import org.iisbelluzzifioravanti.app.bfconnect.R;
import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.helpactivity.HNFCuno;
import org.iisbelluzzifioravanti.app.bfconnect.activities.threeDots.myrooms.MyRooms;
import org.iisbelluzzifioravanti.app.bfconnect.database.DbTools;
import org.iisbelluzzifioravanti.app.bfconnect.util.ActivityTools;

import java.io.IOException;

public class QrCode extends AppCompatActivity {

    SurfaceView surfaceView;
    CameraSource camera;
    BarcodeDetector detector;
    private String qtxt="null";
    private boolean pass = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_qr_code);

        //setting mytoolbar as toolbar of the activity
        Toolbar t1 = findViewById(R.id.mytoolbar);
        setSupportActionBar(t1);

        surfaceView = findViewById(R.id.scanner);
        detector = new BarcodeDetector.Builder(this).
                setBarcodeFormats(Barcode.QR_CODE).build();

        camera = new CameraSource.Builder(this, detector).setAutoFocusEnabled(true).
                setRequestedPreviewSize(640, 480).build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                try {
                    camera.start(holder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                camera.stop();
            }
        });

        detector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
                camera.release();
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                SparseArray<Barcode> qrcode = detections.getDetectedItems();
                if (qrcode.size() != 0 && pass) {
                   qtxt= qrcode.valueAt(0).displayValue;
                   onQRCodeFound();
                }
            }
        });
    }

    private void onQRCodeFound(){
        Log.e("new qrcode found:", qtxt);
        //if the text is corrected
        if (Tools.CheckId(qtxt)) {
            //open the db
            pass = false;
            DbTools dbHandler = new DbTools(QrCode.this);
            if (dbHandler.roomExists(qtxt)) {
                //room found
                Log.d("room found in db", "opening the room");

                Intent in = new Intent(QrCode.this, Rooms.class);

                //closing the db
                dbHandler.close();
                //putting the content inside the intent
                in.putExtra("id", qtxt);
                //starting activity
                ActivityOptions options =
                        ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fadein, R.anim.fadeout);
                startActivity(in, options.toBundle());
            } else if (ActivityTools.isNetworkAvailable(QrCode.this)) { //if there isn't in the db we open the loading page and download the content
                Log.d("room not found in db", "creating room in db");
                //open the page

                dbHandler.close();
                Intent in = new Intent(QrCode.this, Loading.class);
                in.putExtra("id", qtxt);

                ActivityOptions options =
                        ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fadein, R.anim.fadeout);
                startActivity(in, options.toBundle());
            }
        } else {
            Toast.makeText(getApplicationContext(), "Codice non valido!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.three_dots,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.myrooms:
                startActivity(new Intent(this, MyRooms.class));
                return true;
            case R.id.about_us:
                startActivity(new Intent(this, Home.class));
                return true;
            case R.id.help:
                startActivity(new Intent(this, HNFCuno.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        startActivity(new Intent(this, Home.class));
    }
}
