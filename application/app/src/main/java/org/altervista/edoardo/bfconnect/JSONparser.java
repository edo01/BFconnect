package org.altervista.edoardo.bfconnect;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

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


public class JSONparser extends AsyncTask<Void, Void, Bitmap> {

    private String content = "";
    private String title = "";
    private String addresses[];
    InputStream in;

    public JSONparser(){
        addresses=new String[3];
        addresses[0] = "https://api.myjson.com/bins/f63g2";
        addresses[1] = "http://192.168.1.71";
        addresses[2] = "https://nadaje.com/static/frontend/img/logo-nadaje.svg";

    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        //in this try-catch we take some JSON datas and we parse them in title,content ecc...
        try {
            URL url=new URL(addresses[0]);
            //URL url = new URL(addresses[1]);
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
            String img_url = (String) jo.get("img");
            url=null;
            in.close();
            br.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ScaricaImmagine(addresses[1]+ "/"+ Rooms.room);
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
    protected void onPostExecute(Bitmap bitmap){
        //setting in the view the datas parsed
        Rooms.txtView.setText(content);
        Rooms.title.setText(title);
        Rooms.image.setImageBitmap( bitmap);
    }

}
