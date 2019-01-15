/**
 * @class JsonParser
 */
package org.iisbelluzzifioravanti.app.bfconnect.connection;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import org.iisbelluzzifioravanti.app.bfconnect.R;
import org.iisbelluzzifioravanti.app.bfconnect.activities.Rooms;
import org.iisbelluzzifioravanti.app.bfconnect.activities.Home;
import org.iisbelluzzifioravanti.app.bfconnect.database.DbBaseColumns;
import org.iisbelluzzifioravanti.app.bfconnect.database.DbTools;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @// TODO: 26/11/18 :
 *
 * In this class we take some JSON data and we parse them inside our view( the context passed).
 * the form of the JSON data MUST be like this:
 * {
 * "title":"ITIS BELLUZZI",
 * "content":"Benvenuti all'open day del Belluzzi Fioravanti"
 * }
 *
 */

public class JsonParser extends AsyncTask<Void, Void, Boolean> {

    private String title = "", content = "", type = "";
    private Bitmap bitmap;
    private String room;
    private Context c;
    private ProgressDialog pDialog;
    private DbTools dbHandler;

    private byte[][] byteArray = new byte[6][];
    private int nimage;

    /*the address must has this form "https://ip/?room=N&image=false" for
     * if you want an image don't put 'false' but the number of your image
     */

    private final String address = "http://192.168.43.99:8080";
    //private final String address = "http://taddia.sytes.net:6002"; //put here the server address

    InputStream in;

    public JsonParser(String room, Context c){
        this.room = room;
        this.c = c;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        JsonParser jsonParser = this;
        //creating the progress bar of the download
        pDialog = new ProgressDialog(c,
                ProgressDialog.THEME_DEVICE_DEFAULT_LIGHT);
        pDialog.setTitle("Please wait");
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setMessage("Loading data...");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(true);
        pDialog.setInverseBackgroundForced(true);
        //setting the cancel listener of the progress bar
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
          DownloadContent(address + "/?room="+ room);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        try {
            //download the image
            if (nimage>6) return false;
            for (int i=1; i<nimage+1; i++){
                bitmap = DownloadImage(address + "/?room="+ room +"&image="+i);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byteArray[i-1] = stream.toByteArray();
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * download the content in JSON data format
     * @param address
     * @throws IOException
     * @throws JSONException
     */
    private void DownloadContent(String address) throws IOException, JSONException {
        URL url=new URL(address);
        //opening the connection to the host
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        //getting the stream with the host
        in = httpURLConnection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line = "";
        while(line != null){
            content += line;
            line = br.readLine();//reading byte in the stream
        }
        //parsing the json data
        JSONObject jo= new JSONObject(content);
        title= (String) jo.get("title");
        content = (String) jo.get("content");
        type = (String) jo.get("type");
        nimage = (int) jo.get("image");
        in.close();
        br.close();
    }

    /**
     * download the image
     * @param address of the host
     * @return the image in BitMap downloaded
     */
    private Bitmap DownloadImage(String address)
    {
        Bitmap bitmap = null;
        InputStream in = null;

        try {
            int risposta = -1;

            URL url = new URL(address);
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
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e1) {
            Log.d("Servizio web", e1.getLocalizedMessage());
        }
        return bitmap;
    }

    /**
     * if the execute is done properly shows the room class else it shows toast error
     * @param check
     */
    @Override
    protected void onPostExecute(Boolean check){
        //setting in the view the datas parsed
        super.onPostExecute(check);
        //stooping pdialog
        pDialog.dismiss();

        if (check) {
            Intent in = new Intent(c, Rooms.class);
            try {
                //open db
                dbHandler = new DbTools(c);

                //selection only the row with the title which it's downloaded
                //compression the image

                //if we are here the room isn't inside the db so we save it
                dbHandler.setWriteable();
                ContentValues values = new ContentValues();
                values.put(DbBaseColumns.KEY_ROOMID,room);
                values.put(DbBaseColumns.KEY_TITLE, title);
                values.put(DbBaseColumns.KEY_TYPE, type);
                values.put(DbBaseColumns.KEY_CONTENT, content);
                values.put(DbBaseColumns.KEY_IMAGE, byteArray[0]);
                values.put(DbBaseColumns.KEY_IMAGE2, byteArray[1]);
                values.put(DbBaseColumns.KEY_IMAGE3, byteArray[2]);
                values.put(DbBaseColumns.KEY_IMAGE4, byteArray[3]);
                values.put(DbBaseColumns.KEY_IMAGE5, byteArray[4]);
                values.put(DbBaseColumns.KEY_IMAGE6, byteArray[5]);
                dbHandler.insert(values);

                Toast toast = Toast.makeText(c,  title + " salvata nelle tue aule.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                //closing the db
                dbHandler.close();

                //putting the content inside the intent
                in.putExtra("id", room);
                Log.e("la tua stanza è", room);
                //starting activity
                ActivityOptions options =
                        ActivityOptions.makeCustomAnimation(c.getApplicationContext(), R.anim.fadein, R.anim.fadeout);
                c.startActivity(in , options.toBundle());

            } catch (Exception ex) {
                Log.e("ERROR IN LOADING DATA", ex.getMessage());
            }
        }else {
            //show error toast
            Toast toast = Toast.makeText(c, "C'È STATO UN ERRORE NEL CARIMENTO DELLA PAGINA" , Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            //returning to home
            Intent intent = new Intent( c, Home.class);
            c.startActivity(intent);
        }
    }

}
