package org.altervista.edoardo.bfconnect;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JSONparser extends AsyncTask<Void,Void,Void> {

    private String content = "";
    private String title = "";
    private String addresses[];
    InputStream in;

    public JSONparser(){
        addresses=new String[3];
        addresses[0] = "https://api.myjson.com/bins/qseuy";
        addresses[1] = "http://192.168.1.71";
        addresses[2] = "https://nadaje.com/static/frontend/img/logo-nadaje.svg";

    }

    @Override
    protected Void doInBackground(Void... voids) {
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
            url = new URL(addresses[1]+ "/"+ Rooms.room);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 2. Apro la connessione
            conn.connect();
            in = conn.getInputStream();
            in.close();
            // 3. Download e decodifico l'immagine bitmap
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid){
        super.onPostExecute(aVoid);
        //setting in the view the datas parsed
        Rooms.txtView.setText(content);
        Rooms.title.setText(title);
        Rooms.image.setImageBitmap( BitmapFactory.decodeStream(in));
    }

}
