package org.altervista.edoardo.bfconnect;

import android.os.AsyncTask;

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
    private String data="";
    private String title="";
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url=new URL("https://api.myjson.com/bins/qseuy");
            //URL url = new URL("http://192.168.1.71");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream in = httpURLConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            /*OutputStream ot = httpURLConnection.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(ot));
            bw.write(Rooms.room);*/
            String line="";
            while(line!=null){
                data+=line;
                line=br.readLine();
            }
            JSONObject jo= new JSONObject(data);
            title= (String) jo.get("title");
            data = (String) jo.get("content");

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
        Rooms.txtView.setText(data);
        Rooms.title.setText(title);
    }

}
