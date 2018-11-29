package org.altervista.edoardo.bfconnect.connectionParser;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.altervista.edoardo.bfconnect.activities.Rooms;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @// TODO: 26/11/18 : Showing pdf download status;
 */

public class JSONparser extends AsyncTask<Void, Void, Void> {

    private String content = "";
    private String title = "";
    private Bitmap bitmap;
    private String room;
    private Context c;

    /*the address must has this form "https://192.168.1.71/?room=N&image=false" for
     * if you want an image don't put 'false' but the number of your image
     */
    private final String address = "http://taddia.sytes.net:6002";
    InputStream in;

    public JSONparser(String room, Context c){
        this.room = room;
        this.c = c;
    }

    @Override
    protected Void doInBackground(Void... voids) {
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        bitmap = ScaricaImmagine(address + "/?room="+ room +"&image=1&pdf=false");
        return null;
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
    protected void onPostExecute(Void aVoids){
        //setting in the view the datas parsed
        super.onPostExecute(aVoids);
        Intent in = new Intent(c, Rooms.class);
        in.putExtra("content", content);
        in.putExtra("title", title);
        in.putExtra("image", bitmap);
        c.startActivity(in);
    }

}
