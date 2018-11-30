package org.altervista.edoardo.bfconnect.connectionParser;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import org.altervista.edoardo.bfconnect.activities.Home;
import org.altervista.edoardo.bfconnect.activities.Rooms;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @// TODO: 26/11/18 :
 */

public class JSONparser extends AsyncTask<Void, Void, Boolean> {

    private String content = "";
    private String title = "";
    private Bitmap bitmap;
    private String room;
    private Context c;
    private ProgressDialog pDialog;

    /*the address must has this form "https://ip/?room=N&image=false" for
     * if you want an image don't put 'false' but the number of your image
     */
    private final String address = "http://192.168.1.71:80";
    //private final String address = "http://taddia.sytes.net:6002";
    InputStream in;

    public JSONparser(String room, Context c){
        this.room = room;
        this.c = c;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        JSONparser jsonParser = this;
        pDialog = new ProgressDialog(c,
                ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
        pDialog.setTitle("Please wait");
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setMessage("Loading data...");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(true);
        pDialog.setInverseBackgroundForced(true);
        pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                jsonParser.cancel(true);
                Toast tdonwload = Toast.makeText(c, "DOWNLOAD INTERROTTO" , Toast.LENGTH_SHORT);
                tdonwload.setGravity(Gravity.CENTER,0,0);
                tdonwload.show();
                Intent intent = new Intent( c, Home.class);
                c.startActivity(intent);
            }
        });
        pDialog.show();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        //in this try-catch we take some JSON datas and we parse them in title,content ecc...
        try {
            URL url=new URL(address + "/?room="+ room + "&image=false&pdf=false");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            in = httpURLConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line="";
            while(line!=null){
                content +=line;
                line=br.readLine();
            }
            JSONObject jo= new JSONObject(content);
            title= (String) jo.get("title");
            content = (String) jo.get("content");
            in.close();
            br.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        try {
            bitmap = ScaricaImmagine(address + "/?room="+ room +"&image=1&pdf=false");
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private Bitmap ScaricaImmagine(String URL)
    {
        Bitmap bitmap = null;
        InputStream in = null;
        try {
            in = ApriConnessioneHttp(URL);
            bitmap = BitmapFactory.decodeStream(in);

            in.close();
        } catch (IOException e1) {
            Log.d("Servizio web", e1.getLocalizedMessage());
        }
        return bitmap;
    }

    private InputStream ApriConnessioneHttp(String urlString) throws IOException
    {
        InputStream in = null;
        int risposta = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if (!(conn instanceof HttpURLConnection))
            throw new IOException("No connessione HTTP");

        try{
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            risposta = httpConn.getResponseCode();
            if (risposta == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }

        }
        catch (Exception ex)
        {
            Log.d("Connessione", ex.getLocalizedMessage());
            throw new IOException("Errore connessione");
        }
        return in;
    }

    @Override
    protected void onPostExecute(Boolean check){
        //setting in the view the datas parsed
        super.onPostExecute(check);
        if (check) {
            Intent in = new Intent(c, Rooms.class);
            try {
                pDialog.dismiss();
                in.putExtra("content", content);
                in.putExtra("title", title);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                in.putExtra("image", byteArray);
                c.startActivity(in);
            } catch (Exception ex) {
                Log.e("ERROR IN LOADING DATA", ex.getMessage());
            }
        }else {
            pDialog.dismiss();
            Toast tdonwload = Toast.makeText(c, "C'È STATO UN ERRORE. CONTATTA L'AMMINISTRATORE" , Toast.LENGTH_LONG);
            tdonwload.setGravity(Gravity.CENTER,0,0);
            tdonwload.show();
            Intent intent = new Intent( c, Home.class);
            c.startActivity(intent);
        }
    }

}
