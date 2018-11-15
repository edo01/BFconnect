/**
* CLASS CONNECTION.
* The class which implements the http POST with a volley request.
*/

package org.altervista.edoardo.bfconnect;

import android.util.Log;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

/**
@deprecated Attention this class doesn't work in background it's better to use JSONparser.java
 */
public class Connection{

    String url;
    RequestQueue queue;

    public Connection(String url,RequestQueue queue){
        this.url=url;
        this.queue=queue;
    }

    public void Post(final String aula){    //method POST(like argument the String to pass in the connection)

        StringRequest postRequest = new StringRequest(com.android.volley.Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        startActivity(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("aula", aula);
                return params;
            }
        };
        queue.add(postRequest);

    }

    void startActivity(String respose){

    }

    public String getDate(){

        StringRequest postRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) ;
        queue.add(postRequest);
        return "16/11/2018";
    }

}


