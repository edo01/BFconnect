package org.altervista.edoardo.belluzzday;

import android.util.Log;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class Connection{

    String url;
    RequestQueue queue;

    public Connection(String url,RequestQueue queue){
        this.url=url;
        this.queue=queue;
    }

    public void Post(final String aula){

        StringRequest postRequest = new StringRequest(com.android.volley.Request.Method.POST, url,
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


}
