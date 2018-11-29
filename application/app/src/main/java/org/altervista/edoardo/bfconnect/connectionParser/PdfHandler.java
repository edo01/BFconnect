package org.altervista.edoardo.bfconnect.connectionParser;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import org.altervista.edoardo.bfconnect.activities.Professionale;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.ContentValues.TAG;

/**
 * @// TODO: 26/11/18 :
 */

public class PdfHandler extends AsyncTask<Void, Void, Void> {
    private String pdf;
    private int nPdf;
    private final String address = "http://taddia.sytes.net:6002";
    private Context context;

    public PdfHandler(String indirizzo,Context context){
        this.context = context;
        switch(indirizzo){
            case "elettronica":
                pdf = "15_12_2018_elettronica_elettrotecnica.pdf";
                nPdf=9;
                break;
            case "informatica":
                pdf = "15_12_2018__informatica_telecomunicazioni.pdf";
                nPdf=1;
                break;
            case "meccanica":
                pdf = "15_12_2018_meccanica_mecatronicaedenergia.pdf";
                nPdf=2;
                break;
            case "chimica":
                pdf = "15_12_2018_chimica_materiali_biotecnologie.pdf";
                nPdf=3;
                break;
            case "seraliApparati":
                pdf = "15_12_2018_serale_manutenzioneMezziDiTrasporto.pdf";
                nPdf=4;
                break;
            case "seraliMezzi":
                pdf = "15_12_2018_serale_manutenzioneMezziDiTrasporto.pdf";
                nPdf=5;
                break;
            case "qualifiche":
                pdf = "15_12_2018_qualificheRegionali.pdf";
                nPdf=6;
                break;
            case "manutenzione":
                pdf = "15_12_2018_manutenzione_assistenzaTecnica.pdf";
                nPdf=7;
                break;
            case "apparati":
                pdf = "15_12_2018_apparati_impianti_serviziTecniciIndustriali.pdf";
                nPdf=8;
                break;
            case "mezzi":
                pdf = "15_12_2018_manutenzioneMezziDiTrasporto.pdf";
                nPdf=10;
                break;
        }
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        try {
            File downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File outputFile = new File(downloadPath + "/" + pdf);

            //Create New File if not present
            if (!outputFile.exists()) {
                URL url = new URL(address + "/?room=false&image=false&pdf=" + nPdf);//Create Download URl
                HttpURLConnection c = (HttpURLConnection) url.openConnection();//Open Url Connection
                c.setRequestMethod("GET");//Set Request Method to "GET" since we are grtting data
                c.connect();//connect the URL Connection
                c.getContent();
                //If Connection response is not OK then show Logs
                if (c.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return null;
                }
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
                c.disconnect();
            }else {
                Log.d("file exist","passing over");
            }
        } catch (Exception e) {
            //Read exception if something went wrong
            e.printStackTrace();
            Log.e(TAG, "Download Error Exception " + e.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast tdonwload = Toast.makeText(context, "pdf scaricato" , Toast.LENGTH_SHORT);
        tdonwload.setGravity(Gravity.CENTER,0,0);
        tdonwload.show();
    }
}
