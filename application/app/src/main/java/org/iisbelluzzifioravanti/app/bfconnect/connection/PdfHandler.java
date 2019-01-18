/**
 * @class PdfHandler.java
 */
package org.iisbelluzzifioravanti.app.bfconnect.connection;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.ContentValues.TAG;

/**
 * this class downloads the pdf from the server
 */

public class PdfHandler extends AsyncTask<Void, Void, Boolean> {
    private String pdf;
    private int nPdf;

    private final String address = "http://192.168.43.99:8080";
    //private final String address = "http://taddia.sytes.net:6002";

    private Context context;
    private ProgressDialog pDialog;
    private File outputFile;

    /**
     * @// TODO: 12/13/18 : replace all with this keys.
     */
    public static String elettronica = "elettronica";
    public static String informatica = "informatica";
    public static String meccanica = "meccanica";
    public static String chimica = "elettronica";
    public static String serale = "serale";
    public static String qualifiche = "qualifiche";
    public static String manutenzione = "manutenzione";
    public static String apparati = "apparati";
    public static String mezzi = "mezzi";
    public static String alternanza = "work_school";

    //first choosing the name of the pdf( in base of the specialization clicked)
    public PdfHandler(String indirizzo,Context context){
        this.context = context;
        switch(indirizzo){
            case "elettronica":
                pdf = "elettronica_elettrotecnica.pdf";
                nPdf=4;
                break;
            case "informatica":
                pdf = "informatica_telecomunicazioni.pdf";
                nPdf=1;
                break;
            case "meccanica":
                pdf = "meccanica_mecatronicaedenergia.pdf";
                nPdf=2;
                break;
            case "chimica":
                pdf = "chimica_materiali_biotecnologie.pdf";
                nPdf=3;
                break;
            case "seraliApparati":
                pdf = "BF_serale.pdf";
                nPdf=5;
                break;
            case "seraliMezzi":
                pdf = "BF_serale.pdf";
                nPdf=5;
                break;
            case "qualifiche":
                pdf = "qualificheRegionali.pdf";
                nPdf=6;
                break;
            case "manutenzione":
                pdf = "manutenzione_assistenzaTecnica.pdf";
                nPdf=7;
                break;
            case "apparati":
                pdf = "apparati_impianti_serviziTecniciIndustriali.pdf";
                nPdf=8;
                break;
            case "mezzi":
                pdf = "manutenzioneMezziDiTrasporto.pdf";
                nPdf=9;
                break;
            case "work_school":
                pdf = "Alternanza_scuola_Lavoro_BF.pdf";
                nPdf=10;
                break;
            case "mast":
                pdf = "Mast_BF.pdf";
                nPdf=11;
                break;
            case "ducati":
                pdf = "Ducati_BF.pdf";
                nPdf=12;
                break;
            case "opus":
                pdf = "Opus_facere_BF.pdf";
                nPdf=13;
                break;
            case "magneti":
                pdf = "Magneti_Marelli_BF.pdf";
                nPdf=14;
                break;
            case "filosofia":
                pdf = "Filosofia_BF.pdf";
                nPdf=15;
                break;
            case "carpigiani":
                pdf = "Carpigiani_BF.pdf";
                nPdf=16;
                break;
            case "stem":
                pdf = "stem_BF.pdf";
                nPdf=17;
                break;
            case "texa":
                pdf = "texa_BF.pdf";
                nPdf=18;
                break;
            case "toyota":
                pdf = "toyota_BF.pdf";
                nPdf=19;
                break;
            default:
                pdf = "orari_del_tecnico.pdf";
                nPdf=0;
        }

        Log.i("DownloadTask","Constructor done");

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        PdfHandler pdfHandler = this;
        //creating the process dialog bar
        pDialog = new ProgressDialog(context,
                ProgressDialog.THEME_DEVICE_DEFAULT_LIGHT);
        pDialog.setTitle("Please wait");
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setMessage("Loading data...");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(true);
        pDialog.setInverseBackgroundForced(true);

        //setting the cancel listener of the process dialog
        pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                pdfHandler.cancel(true);
                Toast tdonwload = Toast.makeText(context, "DOWNLOAD INTERROTTO" , Toast.LENGTH_SHORT);
                tdonwload.setGravity(Gravity.CENTER,0,0);
                tdonwload.show();
            }
        });
        pDialog.show();
    }

    @Override
    protected Boolean doInBackground(Void... arg0) {
        //getting the pdf from the server
        try {
            File downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            outputFile = new File(downloadPath + "/" + pdf);

            //Create New File if not present
            if (!outputFile.exists()) {
                URL url = new URL(address + "/?pdf=" + nPdf);//Create Download URl
                HttpURLConnection c = (HttpURLConnection) url.openConnection();//Open Url Connection
                c.setRequestMethod("GET");//Set Request Method to "GET" since we are grtting data
                c.connect();//connect the URL Connection
                //If Connection response is not OK then show Logs
                if (c.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return false;
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
            return false;
        }

        return true;
    }

    @Override
    protected void onPostExecute(Boolean check) {
        super.onPostExecute(check);
        if (check){
            Toast tdonwload = Toast.makeText(context, "pdf scaricato con successo" , Toast.LENGTH_SHORT);
            tdonwload.setGravity(Gravity.CENTER,0,0);
            tdonwload.show();
            pDialog.dismiss();

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(outputFile), "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            context.startActivity(intent);
        }else{
            Toast tdonwload = Toast.makeText(context, "C'È STATO UN ERRORE DURANTE IL DOWNLOAD" , Toast.LENGTH_LONG);
            tdonwload.setGravity(Gravity.CENTER,0,0);
            tdonwload.show();
            pDialog.dismiss();
        }
    }
}
