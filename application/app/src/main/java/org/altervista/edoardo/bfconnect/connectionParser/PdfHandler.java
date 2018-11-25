package org.altervista.edoardo.bfconnect.connectionParser;


import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.ContentValues.TAG;

/**
 * @// TODO: 25/11/18 : understand why the name of the pdf is like this.
 */

public class PdfHandler extends AsyncTask<Void, Void, Void> {
    private String[] pdf = new String[3];
    private final String address = "http://192.168.43.99";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pdf[0] = "orariTecnico.pdf";
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        try {
            URL url = new URL(address + "/?room=false&image=false&pdf=true");//Create Download URl
            HttpURLConnection c = (HttpURLConnection) url.openConnection();//Open Url Connection
            c.setRequestMethod("GET");//Set Request Method to "GET" since we are grtting data
            c.connect();//connect the URL Connection

            //If Connection response is not OK then show Logs
            if (c.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            File downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File outputFile = new File(downloadPath + pdf[0]);

            //Create New File if not present
            if (!outputFile.exists()) {
                outputFile.createNewFile();
                Log.d(TAG, outputFile.getPath());
                Log.d(TAG, "File Created");

                FileOutputStream fos = new FileOutputStream(outputFile);//Get OutputStream for NewFile Location

                InputStream is = c.getInputStream();//Get InputStream for connection

                byte[] buffer = new byte[1024];//Set buffer type
                int len1 = 0;//init length
                while ((len1 = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, len1);//Write new file
                }

                //Close all connection after doing task
                fos.flush();
                fos.close();
                is.close();
            }
        } catch (Exception e) {
            //Read exception if something went wrong
            e.printStackTrace();
            Log.e(TAG, "Download Error Exception " + e.getMessage());
        }

        return null;
    }

}
